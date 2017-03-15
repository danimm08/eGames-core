package services;

import forms.RegistrationForm;
import model.Authority;
import model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.AuthorityRepository;
import repositories.UserAccountRepository;

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



}
