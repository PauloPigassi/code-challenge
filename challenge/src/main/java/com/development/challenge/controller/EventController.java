package com.development.challenge.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.development.challenge.model.AccessModel;
import com.development.challenge.model.EventModel;
import com.development.challenge.service.EventService;

/**
 * Event endpoint controller
 * @author Paulo Pigassi
 */
@RestController
@RequestMapping("event")
public class EventController {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;
    
    Date date = new Date();

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create/{url}")
    @Transactional
    public ResponseEntity<EventModel> save(HttpServletRequest req, HttpServletResponse response, @PathVariable("url") String url) throws IOException {
    	String userToken = "";
        //Verifies the cookie header
    	String cookie = req.getHeader("cookie");
  		if (cookie == null) {
            //If cookie header is null, creates an unique token and sets the header,then save the userToken
  			Cookie newCookie = new Cookie("cookie-name",LocalDateTime.now().toString());
  			newCookie.setMaxAge(60*60*24*365); //1 year
  	        response.addCookie(newCookie);
  	        userToken = newCookie.getValue().toString();
  		} else {
  	        	userToken = cookie.toString().replace("cookie-name=", "");
  	    }
    	logger.info(String.format(
                "starting save event request"));
        EventModel savedEvent = (EventModel) eventService.save(url, userToken);

        logger.info(String.format("return save event request object: %s", savedEvent.toString()));
        return new ResponseEntity<>(savedEvent, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllEvents( ) {
        logger.info("starting request for all events");

        List<com.development.challenge.model.EventModel> eventList = eventService.getAll();

        logger.info(String.format("return request for all events list: %s", eventList.toString()));
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }
    
    @GetMapping(path = "/{url}")
    public ResponseEntity<?> getAllEventsByUrl( @PathVariable("url") String url) {
        logger.info(String.format("starting request for all events with url: %s", url));

        AccessModel access = eventService.getAllEventsByUrl(url);

        logger.info(String.format("return request for all events object: %s", access));
        return new ResponseEntity<>(access, HttpStatus.OK);
    }
 }
