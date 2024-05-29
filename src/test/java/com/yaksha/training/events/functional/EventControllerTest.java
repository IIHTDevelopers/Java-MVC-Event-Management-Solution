package com.yaksha.training.events.functional;

import com.yaksha.training.events.controller.EventController;
import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.service.EventService;
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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.events.utils.MasterData.asJsonString;
import static com.yaksha.training.events.utils.MasterData.getEvent;
import static com.yaksha.training.events.utils.MasterData.getEventList;
import static com.yaksha.training.events.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.events.utils.TestUtils.businessTestFile;
import static com.yaksha.training.events.utils.TestUtils.currentTest;
import static com.yaksha.training.events.utils.TestUtils.testReport;
import static com.yaksha.training.events.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListEventsHome() throws Exception {
        List<Event> expected = getEventList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Event> eventPage = new PageImpl<>(expected);
        when(eventService.getEvents(pageable)).thenReturn(eventPage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-events")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("events")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerListEvents() throws Exception {
        List<Event> expected = getEventList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Event> eventPage = new PageImpl<>(expected);
        when(eventService.getEvents(pageable)).thenReturn(eventPage);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-events")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("events")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/addEventForm")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form"), businessTestFile);
    }

    @Test
    public void testControllerSaveEvent() throws Exception {
        Event event = getEvent();
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/event/list"), businessTestFile);
    }

    @Test
    public void testControllerSaveEventHasError() throws Exception {
        Event event = getEvent();
        event.setId(null);
        event.setName(null);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form"), businessTestFile);
    }

    @Test
    public void testControllerUpdateEventHasError() throws Exception {
        Event event = getEvent();
        event.setName(null);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Event event = getEvent();
        when(eventService.getEvent(event.getId())).thenReturn(event);
        MvcResult result = this.mockMvc.perform(get("/updateEventForm")
                .param("eventId", event.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form"), businessTestFile);
    }

    @Test
    public void testControllerDeleteEvent() throws Exception {
        Event event = getEvent();
        MvcResult result = this.mockMvc.perform(get("/delete")
                .param("eventId", event.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/event/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchEvents() throws Exception {
        String key = randomStringWithSize(2);
        List<Event> expected = getEventList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Event> eventPage = new PageImpl<>(expected);
        when(eventService.searchEvents(key, pageable)).thenReturn(eventPage);
        MvcResult result = this.mockMvc.perform(post("/search")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-events")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("events")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerSearchEventsWithoutSearchName() throws Exception {
        List<Event> expected = getEventList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Event> eventPage = new PageImpl<>(expected);
        when(eventService.searchEvents(null, pageable)).thenReturn(eventPage);
        MvcResult result = this.mockMvc.perform(post("/search")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-events")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("events")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerPastEventsWithSearchValue() throws Exception {
        String key = randomStringWithSize(2);
        List<Event> expected = getEventList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Event> eventPage = new PageImpl<>(expected);
        when(eventService.pastEvents(key, pageable)).thenReturn(eventPage);
        MvcResult result = this.mockMvc.perform(post("/pastEvents")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("past-events")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("events")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerPastEventsWithoutSearchName() throws Exception {
        List<Event> expected = getEventList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Event> eventPage = new PageImpl<>(expected);
        when(eventService.pastEvents(null, pageable)).thenReturn(eventPage);
        MvcResult result = this.mockMvc.perform(post("/pastEvents")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("past-events")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("events")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

}
