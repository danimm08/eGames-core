package es.eGames.services;

import es.eGames.forms.RegistrationForm;
import es.eGames.forms.UserProfileForm;
import es.eGames.model.User;
import es.eGames.model.UserAccount;
import es.eGames.security.UserDetailsService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import es.eGames.repositories.UserRepository;

/**
 * Created by daniel on 11/02/17.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccountService userAccountService;

    public UserService() {
        super();
    }

    public User findById(int id) {
        User user;
        user = userRepository.findOne(id);
        Assert.notNull(user);
        Hibernate.initialize(user.getFollowees());
        Hibernate.initialize(user.getFollowers());
        return user;
    }

    public User save(RegistrationForm registrationForm) {
        UserAccount ua = userAccountService.save(userAccountService.toUserAccount(registrationForm));
        User u = this.toUser(registrationForm);
        u.setUserAccount(ua);
        User udb = userRepository.save(u);
        return udb;
    }

    public User toUser(RegistrationForm registrationForm) {
        User u = new User();
        u.setAddress(registrationForm.getAddress());
        u.setName(registrationForm.getName());
        u.setSurname(registrationForm.getSurname());
        u.setReputation(0.0);
        u.setnExchanges(0);
        return u;
    }

    public User findByUsername(String username) {
        User user;
        user = userRepository.findByUsername(username);
        Assert.notNull(user);
        return user;
    }

    public User findByPersonalGameId(int personalGameId) {
        User user;
        user = userRepository.findByPersonalGameId(personalGameId);
        Assert.notNull(user);
        return user;
    }

    public void update(User principal) {
        Assert.notNull(principal);
        userRepository.save(principal);
    }

    public void editProfile(UserProfileForm userProfileForm) {
        User principal = userRepository.findByUsername(UserDetailsService.getPrincipal().getUsername());

        if (!userProfileForm.getName().isEmpty())
            principal.setName(userProfileForm.getName());

        if (!userProfileForm.getSurname().isEmpty())
            principal.setSurname(userProfileForm.getSurname());

        if (!userProfileForm.getAddress().equals(principal.getAddress()))
            principal.setAddress(userProfileForm.getAddress());

        update(principal);

    }
}
