package serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import model.PersonalGame;

import java.io.IOException;
import java.util.List;

/**
 * Created by daniel on 1/03/17.
 */
public class CustomPersonalGameSerializer extends JsonSerializer<List<PersonalGame>> {


     public CustomPersonalGameSerializer() {
        super();
    }


    @Override
    public void serialize(List<PersonalGame> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        for (PersonalGame pg : value) {
            jgen.writeStartObject();


            jgen.writeNumberField("id", pg.getId());
            jgen.writeNumberField("version", pg.getVersion());
            jgen.writeStringField("description", pg.getDescription());
            jgen.writeNumberField("numberOfViews", pg.getNumberOfViews());
            jgen.writeStringField("type", String.valueOf(pg.getType()));
            jgen.writeStringField("distance", String.valueOf(pg.getDistance()));
            jgen.writeFieldName("images");
            jgen.writeStartArray();
            pg.getImages().forEach(image -> {
                try {
                    jgen.writeString(image.getPathUrl());
                } catch (IOException e) {
                    e.getMessage();
                }
            });
            jgen.writeEndArray();
            jgen.writeEndObject();
        }
        jgen.writeEndArray();

    }
}
