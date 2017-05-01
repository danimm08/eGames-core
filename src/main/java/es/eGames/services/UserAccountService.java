package es.eGames.services;

import es.eGames.forms.RegistrationForm;
import es.eGames.forms.UserUserAccountForm;
import es.eGames.model.Authority;
import es.eGames.model.UserAccount;
import es.eGames.repositories.AuthorityRepository;
import es.eGames.repositories.UserAccountRepository;
import es.eGames.security.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by daniel on 11/02/17.
 */
@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private TokenStore tokenStore;


    public UserAccountService() {
        super();
    }

    public UserAccount save(UserAccount userAccount) {
        UserAccount ua = userAccountRepository.save(userAccount);
        return ua;
    }

    public UserAccount toUserAccount(RegistrationForm registrationForm) {
        UserAccount ua = new UserAccount();
        Set<Authority> authoritySet = new HashSet<Authority>();
        authoritySet.add(authorityRepository.findByAuthority("ROLE_USER"));

        ua.setAuthorities(authoritySet);
        ua.setEmail(registrationForm.getEmail());
        StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(registrationForm.getPassword());
        ua.setPassword(encodedPassword);
        ua.setUsername(registrationForm.getUsername());
        return ua;
    }


    public Boolean editUserAccount(UserUserAccountForm userUserAccountForm, HttpServletRequest request) {
        Boolean res = false;
        StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();

        UserAccount principalUserAccount = userAccountRepository.findByUsername(UserDetailsService.getPrincipal().getUsername());

        if (!userUserAccountForm.getUsername().isEmpty() || !userUserAccountForm.getEmail().isEmpty() || !userUserAccountForm.getPassword().isEmpty()) {
            Assert.isTrue(!userUserAccountForm.getOldPassword().isEmpty());
            if (!passwordEncoder.matches(userUserAccountForm.getOldPassword(), principalUserAccount.getPassword()))
                throw new IllegalArgumentException("La contraseña actual no coincide con la actual, no se pueden realizar los cambios");
            res = true;
            logout(request);


            if (userUserAccountForm.getUsername() != null && !userUserAccountForm.getUsername().isEmpty() && !userUserAccountForm.getUsername().equals(principalUserAccount.getUsername()))
                principalUserAccount.setUsername(userUserAccountForm.getUsername());

            if (userUserAccountForm.getPassword() != null && !userUserAccountForm.getPassword().isEmpty() && !userUserAccountForm.getPassword().equals(principalUserAccount.getPassword())) {
                String encodedPassword = passwordEncoder.encode(userUserAccountForm.getPassword());
                principalUserAccount.setPassword(encodedPassword);
            }

            if (userUserAccountForm.getEmail() != null && !userUserAccountForm.getEmail().isEmpty() && !userUserAccountForm.getEmail().equals(principalUserAccount.getEmail()))
                principalUserAccount.setEmail(userUserAccountForm.getEmail());

            userAccountRepository.save(principalUserAccount);
        }
        return res;
    }

    public void logout(HttpServletRequest request) {
        String token = request.getHeader("authorization");

        if (token != null && token.startsWith("Bearer ")) {

            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[1]);

            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }

        }

    }

    public void remove(UserAccount userAccount) {
        userAccountRepository.delete(userAccount.getId());
    }
}
