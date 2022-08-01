package reivosar.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TestEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Autowired
    public TestEventPublisher(final ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(final TestEvent event) {
        publisher.publishEvent(event);
    }
}
