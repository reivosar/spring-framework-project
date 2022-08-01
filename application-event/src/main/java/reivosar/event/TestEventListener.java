package reivosar.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestEventListener.class);

    @Async
    @EventListener
    public void handle(final TestEvent event) {
        LOGGER.info("Message in TestEvent: {}", event.toJson());
    }
}
