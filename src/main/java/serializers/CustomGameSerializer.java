package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import model.Game;
import model.PersonalGame;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.PersonalGameRepository;

import java.io.IOException;
import java.util.List;

/**
 * Created by daniel on 1/03/17.
 */
public class CustomGameSerializer extends JsonSerializer<Game> {

    private String orderBy;

    @Autowired
    private PersonalGameRepository personalGameRepository;

    public CustomGameSerializer() {
        super();
    }

    public CustomGameSerializer(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public void serialize(Game value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeObject(value);
        jgen.writeStartArray();
        List<PersonalGame> list = personalGameRepository.findAll();
        for (PersonalGame pg : list) {
            jgen.writeStartObject();

            jgen.writeNumberField("id", pg.getNumberOfViews());


            jgen.writeEndObject();
        }
        jgen.writeEndArray();

    }
}
