package es.eGames.utils.populate;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by daniel on 24/03/17.
 */
public class PasswordEncoder {

    private static final String[] passwords = {"alvrodlag", "bonbarull", "laucazval", "marcorgue", "sanespmer", "letmonguz", "carramcor", "josbergri", "estadoven", "rafnuñara"};

    public static void main(String[] args) {

        for (String password : passwords) {
            StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            System.out.println(encodedPassword);
        }
    }
}
