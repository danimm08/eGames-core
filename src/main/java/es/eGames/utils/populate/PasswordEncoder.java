package es.eGames.utils.populate;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by daniel on 24/03/17.
 */
public class PasswordEncoder {

    private static final String password = "password10";

    public static void main(String[] args){

        StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println(encodedPassword);
    }
}
