package reivosar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reivosar.service.TestEventPublishService;

@RestController
public class TestEventController {

    private final TestEventPublishService testEventPublishService;

    @Autowired
    public TestEventController(final TestEventPublishService testEventPublishService) {
        this.testEventPublishService = testEventPublishService;
    }

    @PostMapping("send")
    public ResponseEntity<String> sendMessage(final @RequestParam String message) {
        try {
            this.testEventPublishService.sendMessage(message);
            return new ResponseEntity<>("Message received.[" + message + "]", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
