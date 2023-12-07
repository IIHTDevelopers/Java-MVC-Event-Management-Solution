package com.yaksha.training.events.service;

import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.events.utils.MasterData.asJsonString;
import static com.yaksha.training.events.utils.MasterData.getEvent;
import static com.yaksha.training.events.utils.MasterData.getEventList;
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
        List<Event> actual = getEventList(5);
        when(eventRepository.findAll()).thenReturn(actual);
        List<Event> expected = eventService.getEvents();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
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
        List<Event> actual = getEventList(5);
        when(eventRepository.findAll()).thenReturn(actual);
        List<Event> expected = eventService.searchEvents(null);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchEventWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        List<Event> actual = getEventList(5);
        when(eventRepository.findByEventNameAndPlace(searchKey)).thenReturn(actual);
        List<Event> expected = eventService.searchEvents(searchKey);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

}
