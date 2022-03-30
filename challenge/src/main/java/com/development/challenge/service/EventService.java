package com.development.challenge.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.development.challenge.model.AccessModel;
import com.development.challenge.model.EventModel;
import com.development.challenge.repository.EventRepository;


@Service
public class EventService {

    private final Logger logger = LoggerFactory.getLogger(EventService.class);

    private EventRepository eventRepository;
    
    Date date = new Date(); 

    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public EventModel save(String url, String userToken) {
        EventModel eventModel = new EventModel();
        eventModel.setAccessDate(LocalDateTime.now());
        eventModel.setUrl(url);
        eventModel.setUserToken(userToken);
        logger.info(String.format("Event object to save: %s", eventModel));
        return this.eventRepository.save(eventModel);
    }

    public List<EventModel> getAll() {
        return StreamSupport.stream(this.eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    
    public AccessModel getAllEventsByUrl(String url){
        //verifyIfUserExists();
        List<Integer> event = eventRepository.getAllEventsByUrl(url);
        logger.info(String.format("transaction list for url: %s - %s", url, event));
        AccessModel access = new AccessModel();
        access.setTimesAccessed(event.get(0).intValue());
        access.setUrl(url);
        return access;
    }
}