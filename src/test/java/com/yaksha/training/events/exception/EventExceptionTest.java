package com.yaksha.training.events.exception;

import com.yaksha.training.events.controller.EventController;
import com.yaksha.training.events.entity.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.events.utils.MasterData.getEvent;
import static com.yaksha.training.events.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.events.utils.TestUtils.currentTest;
import static com.yaksha.training.events.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.events.utils.TestUtils.testReport;
import static com.yaksha.training.events.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class EventExceptionTest {

    @InjectMocks
    private EventController eventController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveEventNameAsNull() throws Exception {
        Event event = getEvent();
        event.setId(null);
        event.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveEventDescAsNull() throws Exception {
        Event event = getEvent();
        event.setId(null);
        event.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveEventPlaceAsNull() throws Exception {
        Event event = getEvent();
        event.setId(null);
        event.setPlace(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveEventDateInvalidFormat() throws Exception {
        Event event = getEvent();
        event.setId(null);
        event.setEventDate(randomStringWithSize(10));
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveEventDateAsNull() throws Exception {
        Event event = getEvent();
        event.setId(null);
        event.setEventDate(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-event-form")), exceptionTestFile);
    }


    @Test
    public void testExceptionUpdateEventNameAsNull() throws Exception {
        Event event = getEvent();
        event.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateEventDescAsNull() throws Exception {
        Event event = getEvent();
        event.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateEventPlaceAsNull() throws Exception {
        Event event = getEvent();
        event.setPlace(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateEventDateInvalidFormat() throws Exception {
        Event event = getEvent();
        event.setEventDate(randomStringWithSize(10));
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateEventDateAsNull() throws Exception {
        Event event = getEvent();
        event.setEventDate(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveEvent")
                .flashAttr("event", event)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-event-form")), exceptionTestFile);
    }

}