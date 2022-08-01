package reivosar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reivosar.event.TestEvent;
import reivosar.event.TestEventPublisher;

import java.util.concurrent.CompletableFuture;

@Service
public class TestEventPublishService {

    private final TestEventPublisher testEventPublisher;

    @Autowired
    public TestEventPublishService(final TestEventPublisher testEventPublisher) {
        this.testEventPublisher = testEventPublisher;
    }

    public void sendMessage(final String message) {
        final TestEvent testEvent = new TestEvent(message);
        this.testEventPublisher.publish(testEvent);
    }
}
