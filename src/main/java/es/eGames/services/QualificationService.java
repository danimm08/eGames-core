package es.eGames.services;

import es.eGames.forms.QualificationForm;
import es.eGames.model.Exchange;
import es.eGames.model.Qualification;
import es.eGames.model.User;
import es.eGames.repositories.QualificationRepository;
import es.eGames.security.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/04/17.
 */
@Transactional
@Service
public class QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private PersonalGameService personalGameService;

    public QualificationService() {
        super();
    }


    public void save(QualificationForm qualificationForm, Integer exchangeId) {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Exchange exchange = exchangeService.findById(exchangeId);
        List<User> usersInExchange = new ArrayList(exchangeService.usersInExchange(exchangeId));
        Qualification auxQualification = qualificationRepository.findByUserIdAndExchangeId(principal.getId(), exchangeId);

        Assert.isNull(auxQualification);
        Assert.isTrue(exchange.getStatus() == true);
        Assert.isTrue(usersInExchange.contains(principal));

        Qualification qualification = new Qualification();
        qualification.setText(qualificationForm.getText());
        qualification.setMark(qualificationForm.getMark());
        qualification.setExchange(exchange);
        qualification.setUser(principal);
        qualificationRepository.save(qualification);

        User user = usersInExchange.stream().filter(u -> !u.equals(principal)).findFirst().get();
        user.setReputation(userService.calculateReputation(user));
        userService.save(user);
    }

    public List<Qualification> findByUserId(Integer userId) {
        return qualificationRepository.findByUserId(userId);
    }
}
