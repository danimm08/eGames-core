package es.eGames.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import es.eGames.model.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by daniel on 1/03/17.
 */
public class CustomFollowersFolloweesSerializer extends JsonSerializer<List<User>> {


    public CustomFollowersFolloweesSerializer() {
        super();
    }


    @Override
    public void serialize(List<User> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        for (User pg : value) {
            jgen.writeStartObject();
            jgen.writeNumberField("id", pg.getId());
            jgen.writeNumberField("version", pg.getVersion());
            jgen.writeStringField("name", pg.getName());
            jgen.writeStringField("surname", pg.getSurname());
            jgen.writeNumberField("reputation", pg.getReputation());
            jgen.writeObjectField("address", pg.getAddress());
            jgen.writeNumberField("nExchanges", pg.getnExchanges());
            jgen.writeObjectField("userAccount", pg.getUserAccount());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();

    }
}
