package com.yaksha.training.events.service;

import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).get();
    }

    public boolean deleteEvent(Long id) {
        eventRepository.deleteById(id);
        return true;
    }

    public List<Event> searchEvents(String theSearchName) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return eventRepository.findByEventNameAndPlace(theSearchName);
        } else {
            return eventRepository.findAll();
        }
    }
}
