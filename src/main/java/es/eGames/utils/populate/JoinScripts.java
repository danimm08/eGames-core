package es.eGames.utils.populate;

import java.io.*;

/**
 * Created by daniel on 22/03/17.
 */
public class JoinScripts {
    public static void main(String[] args) throws IOException {
        String root = "/home/daniel/Proyectos/eGames-core/src/main/resources/scripts/";
//        String[] files = {"platform.sql", "game_mode.sql", "genre.sql", "keyword.sql", "game.sql", "game_game_modes.sql", "game_genres.sql", "game_keywords.sql"};
        String[] files = {"authority.sql", "user_account.sql", "user.sql", "user_authority.sql", "user_followeers.sql", "user_followees.sql", "platform.sql", "game_mode.sql", "genre.sql", "keyword.sql", "game.sql", "game_game_modes.sql", "game_genres.sql", "game_keywords.sql", "exchanges.sql", "personal_games.sql", "image.sql", "notes.sql","qualifications.sql", "messages.sql"};
        String line = "";

        File fileToSave = new File("/home/daniel/Proyectos/eGames-core/src/main/resources/import.sql");
        fileToSave.getParentFile().mkdirs();
        if(fileToSave.exists())
            fileToSave.delete();
        FileWriter writer = new FileWriter(fileToSave, true);

        for (String file : files) {
            String csvFile = root + file;
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((line = br.readLine()) != null) {
                    writer.append(line + "\n");
                }
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.close();
    }
}
