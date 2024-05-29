package com.yaksha.training.events.service;

import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.yaksha.training.events.utils.MasterData.asJsonString;
import static com.yaksha.training.events.utils.MasterData.getEvent;
import static com.yaksha.training.events.utils.MasterData.getEventList;
import static com.yaksha.training.events.utils.MasterData.getEventListBeforeTodayDate;
import static com.yaksha.training.events.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.events.utils.TestUtils.businessTestFile;
import static com.yaksha.training.events.utils.TestUtils.currentTest;
import static com.yaksha.training.events.utils.TestUtils.testReport;
import static com.yaksha.training.events.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetEvents() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Event> actual = getEventList(5);
        Page<Event> pageEvent = new PageImpl<>(actual);
        when(eventRepository.findAllUpcomingEvents(LocalDate.now(), pageable)).thenReturn(pageEvent);
        Page<Event> expected = eventService.getEvents(pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveEvent() throws Exception {
        Event actual = getEvent();
        when(eventRepository.save(actual)).thenReturn(actual);
        Event expected = eventService.saveEvent(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetEvent() throws Exception {
        Event actual = getEvent();
        when(eventRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Event expected = eventService.getEvent(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceDeleteEvent() throws Exception {
        Event actual = getEvent();
        boolean expected = eventService.deleteEvent(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchEventWithNull() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Event> actual = getEventList(5);
        Page<Event> pageEvents = new PageImpl<>(actual);
        when(eventRepository.findAllUpcomingEvents(LocalDate.now(), pageable)).thenReturn(pageEvents);
        Page<Event> expected = eventService.searchEvents(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchEventWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        Pageable pageable = PageRequest.of(0, 5);
        List<Event> actual = getEventList(5);
        Page<Event> pageEvents = new PageImpl<>(actual);
        when(eventRepository.findByEventNameAndPlace(searchKey, LocalDate.now(), pageable)).thenReturn(pageEvents);
        Page<Event> expected = eventService.searchEvents(searchKey, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServicePastEventWithNull() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Event> actual = getEventListBeforeTodayDate(5);
        Page<Event> pageEvents = new PageImpl<>(actual);
        when(eventRepository.findAllPastEvents(LocalDate.now(), pageable)).thenReturn(pageEvents);
        Page<Event> expected = eventService.pastEvents(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServicePastEventWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        Pageable pageable = PageRequest.of(0, 5);
        List<Event> actual = getEventListBeforeTodayDate(5);
        Page<Event> pageEvents = new PageImpl<>(actual);
        when(eventRepository.findByPastEventNameAndPlace(searchKey, LocalDate.now(), pageable)).thenReturn(pageEvents);
        Page<Event> expected = eventService.pastEvents(searchKey, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

}
