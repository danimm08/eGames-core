package servicies;

import forms.RegistrationForm;
import model.User;
import model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserAccountRepository;
import repositories.UserRepository;

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
        return u;
    }

}