package com.yaksha.training.events.boundary;


import com.yaksha.training.events.entity.Event;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Set;

import static com.yaksha.training.events.utils.MasterData.getEvent;
import static com.yaksha.training.events.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.events.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.events.utils.TestUtils.currentTest;
import static com.yaksha.training.events.utils.TestUtils.testReport;
import static com.yaksha.training.events.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testEventNameNotBlank() throws Exception {
        Event event = getEvent();
        event.setName("");
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventNameNotNull() throws Exception {
        Event event = getEvent();
        event.setName(null);
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventNameMinTwo() throws Exception {
        Event event = getEvent();
        event.setName(randomStringWithSize(1));
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventNameMaxForty() throws Exception {
        Event event = getEvent();
        event.setName(randomStringWithSize(41));
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }


    @Test
    public void testEventDescriptionNotBlank() throws Exception {
        Event event = getEvent();
        event.setDescription("");
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventDescriptionNotNull() throws Exception {
        Event event = getEvent();
        event.setDescription(null);
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventDescriptionMinTwo() throws Exception {
        Event event = getEvent();
        event.setDescription(randomStringWithSize(1));
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventDescriptionMaxTwoHundred() throws Exception {
        Event event = getEvent();
        event.setDescription(randomStringWithSize(201));
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventPlaceNotBlank() throws Exception {
        Event event = getEvent();
        event.setPlace("");
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventPlaceNotNull() throws Exception {
        Event event = getEvent();
        event.setPlace(null);
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }


    @Test
    public void testEventDateNotNull() throws Exception {
        Event event = getEvent();
        event.setEventDate(null);
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventDateValidFormat() throws Exception {
        Event event = getEvent();
        event.setEventDate(LocalDate.now());
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? false : true, boundaryTestFile);
    }

}
