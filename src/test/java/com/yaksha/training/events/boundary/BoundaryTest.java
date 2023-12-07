package com.yaksha.training.events.boundary;


import com.yaksha.training.events.entity.Event;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
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
    public void testEventDateNotBlank() throws Exception {
        Event event = getEvent();
        event.setEventDate("");
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
    public void testEventDateInvalidFormat() throws Exception {
        Event event = getEvent();
        event.setEventDate(randomStringWithSize(10));
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEventDateValidFormat() throws Exception {
        Event event = getEvent();
        event.setEventDate("2023-01-01");
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        yakshaAssert(currentTest(), !violations.isEmpty() ? false : true, boundaryTestFile);
    }

}
