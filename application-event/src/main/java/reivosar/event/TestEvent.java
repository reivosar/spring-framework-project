package reivosar.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestEvent {

    private final String message;

    public TestEvent(final String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("message must be not null.");
        }
        this.message = message;
    }

    public String toJson()  {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
