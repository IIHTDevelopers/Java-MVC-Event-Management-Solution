package com.yaksha.training.events.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.events.entity.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    private static Random rnd = new Random();

    public static Event getEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName(randomStringWithSize(10));
        event.setDescription(randomStringWithSize(10));
        event.setEventDate(LocalDate.now());
        event.setPlace(randomStringWithSize(10));
        return event;
    }

    public static List<Event> getEventList(int size) {
        Long id = 0L;
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Event event = new Event();
            event.setId(++id);
            event.setName(randomStringWithSize(10));
            event.setDescription(randomStringWithSize(10));
            event.setEventDate(LocalDate.now());
            event.setPlace(randomStringWithSize(10));
            events.add(event);
        }
        return events;
    }

    public static List<Event> getEventListBeforeTodayDate(int size) {
        Long id = 0L;
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Event event = new Event();
            event.setId(++id);
            event.setName(randomStringWithSize(10));
            event.setDescription(randomStringWithSize(10));
            event.setEventDate(randomLocalDateBefore());
            event.setPlace(randomStringWithSize(10));
            events.add(event);
        }
        return events;
    }

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static LocalDate randomLocalDateBefore() {
        Random rnd = new Random();
        return LocalDate.now().minusDays(rnd.nextInt());
    }

    public static LocalDate randomLocalDateAfter() {
        Random rnd = new Random();
        return LocalDate.now().plusDays(rnd.nextInt());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
