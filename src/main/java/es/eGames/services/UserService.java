package es.eGames.services;

import es.eGames.forms.RegistrationForm;
import es.eGames.forms.UserProfileForm;
import es.eGames.model.Qualification;
import es.eGames.model.User;
import es.eGames.model.UserAccount;
import es.eGames.repositories.UserRepository;
import es.eGames.security.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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

    @Autowired
    private QualificationService qualificationService;

    public UserService() {
        super();
    }

    public User findById(int id) {
        User user;
        user = userRepository.findOne(id);
        Assert.notNull(user);
        return user;
    }

    public User register(RegistrationForm registrationForm) {
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

        if (userProfileForm.getName() != null && !userProfileForm.getName().isEmpty())
            principal.setName(userProfileForm.getName());

        if (userProfileForm.getSurname() != null && !userProfileForm.getSurname().isEmpty())
            principal.setSurname(userProfileForm.getSurname());

        if (userProfileForm.getAddress() != null && !userProfileForm.getAddress().equals(principal.getAddress()))
            principal.setAddress(userProfileForm.getAddress());

        update(principal);

    }

    public List<User> search(String toSearch) {
        List<User> users;
        users = userRepository.search(toSearch);
        return users;
    }

    public void followOrUnfollow(Integer userId) {
        User principal = userRepository.findByUsername(UserDetailsService.getPrincipal().getUsername());
        User toFollow = userRepository.findOne(userId);
        if (!principal.getFollowees().contains(toFollow)) {
            principal.getFollowees().add(toFollow);
            toFollow.getFollowers().add(principal);
        } else {
            principal.getFollowees().remove(toFollow);
            toFollow.getFollowers().remove(principal);
        }
    }

    public User save(User u) {
        return userRepository.save(u);
    }

    public Double calculateReputation(User user) {
        List<Qualification> qualifications = qualificationService.findByUserId(user.getId());
        Double reputation = qualifications.stream().mapToDouble(Qualification::getMark).average().getAsDouble();
        return reputation;
    }
}

