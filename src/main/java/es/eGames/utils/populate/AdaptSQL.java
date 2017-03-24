package es.eGames.utils.populate;

import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by daniel on 22/03/17.
 */
public class AdaptSQL {
    public static void main(String[] args) throws IOException {

        String csvFile = "/home/daniel/Escritorio/aux/game_keywords.sql";
        String line = "";

        File fileToSave = new File("/home/daniel/Escritorio/aux/special/game_keywords.sql");
        fileToSave.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(fileToSave, true);
        int i = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {

                line = line.replace("game_keywords (id, ", "game_keywords (");
                line = line.replace("keyword_id", "keywords_id");
                line = line.replace("videogame_id", "game_id");
                Pattern p = Pattern.compile("VALUES \\(\\d+,");
                line = p.matcher(line).replaceAll("VALUES (");
                writer.append(line + "\n");
                i++;
//                if(i>3){
//                    break;
//                }

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
