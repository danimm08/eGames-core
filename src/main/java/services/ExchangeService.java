package services;

import forms.ExchangeForm;
import model.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExchangeRepository;
import security.UserDetailsService;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by daniel on 9/03/17.
 */
@Transactional
@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private PersonalGameService personalGameService;

    public ExchangeService() {
        super();
    }

    public ExchangeForm createForm(int personalGameId) {
        ExchangeForm ef = new ExchangeForm();
        String username = UserDetailsService.getPrincipal().getUsername();
        System.out.println(username);
        User u1 = userService.findByUsername(username);
        User u2 = userService.findByPersonalGameId(personalGameId);
        ef.setPersonalGamesUser1(personalGameService.findAllPersonalGameByUser(u1.getId()));
        ef.setPersonalGamesUser2(personalGameService.findAllPersonalGameByUser(u2.getId()));
        ef.setNote(new Note());
        ef.setType(Type.Fijo);
        ef.setWayExchange("");

        return ef;
    }

    public Exchange save(ExchangeForm ef) {

        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Hibernate.initialize(u.getFollowees());
        Hibernate.initialize(u.getFollowers());

        Date now = new Date();

        Exchange exchange = new Exchange();
        exchange.setWayExchange(ef.getWayExchange());
        exchange.setType(ef.getType());
        exchange.setUser(u);
        exchange.setCreationDate(now);
        exchange.setEventDate(null);
        exchange.setLastUpdateDate(now);
        exchange.setStatus(null);
        exchange.setNumberOfAttemps(1);

        Exchange res = exchangeRepository.save(exchange);
        ef.getPersonalGamesUser1().forEach(personalGame -> {
            personalGame.setExchange(res);
            personalGameService.save(personalGame);
        });
        ef.getPersonalGamesUser2().forEach(personalGame -> {
            personalGame.setExchange(res);
            personalGameService.save(personalGame);
        });

        return res;

    }

}
