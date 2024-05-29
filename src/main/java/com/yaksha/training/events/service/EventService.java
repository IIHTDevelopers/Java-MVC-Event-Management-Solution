package com.yaksha.training.events.service;

import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAllUpcomingEvents(LocalDate.now(), pageable);
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

    public Page<Event> searchEvents(String theSearchName, Pageable pageable) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return eventRepository.findByEventNameAndPlace(theSearchName, LocalDate.now(), pageable);
        } else {
            return eventRepository.findAllUpcomingEvents(LocalDate.now(), pageable);
        }
    }

    public Page<Event> pastEvents(String theSearchName, Pageable pageable) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return eventRepository.findByPastEventNameAndPlace(theSearchName, LocalDate.now(), pageable);
        } else {
            return eventRepository.findAllPastEvents(LocalDate.now(), pageable);
        }
    }
}
