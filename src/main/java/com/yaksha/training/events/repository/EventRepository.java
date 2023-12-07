package com.yaksha.training.events.repository;

import com.yaksha.training.events.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "Select c from Event c where lower(name) like %:keyword% or lower(place) like %:keyword%")
    List<Event> findByEventNameAndPlace(@Param("keyword") String keyword);
}
