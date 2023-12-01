package com.fullstackbd.student.controller;

import com.fullstackbd.student.common.controller.HomeController;
import com.fullstackbd.student.model.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HomeControllerImplementation implements HomeController<Message> {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    public Message home() {
        LOG.info("hello world ^*^");
        return Message
                .builder()
                .message("Welcome to Student API")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}
