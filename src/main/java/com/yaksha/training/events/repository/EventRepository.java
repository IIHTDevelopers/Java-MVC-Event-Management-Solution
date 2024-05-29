package com.yaksha.training.events.repository;

import com.yaksha.training.events.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "Select c from Event c where " +
            "lower(name) like %:keyword% or lower(place) like %:keyword% " +
            "and c.eventDate >= :todayDate")
    Page<Event> findByEventNameAndPlace(@Param("keyword") String keyword,
                                        @Param("todayDate") LocalDate todayDate,
                                        Pageable pageable);

    @Query(value = "Select c from Event c where c.eventDate >= :todayDate ")
    Page<Event> findAllUpcomingEvents(@Param("todayDate") LocalDate todayDate, Pageable pageable);

    @Query(value = "Select c from Event c where " +
            "(lower(name) like %:keyword% or lower(place) like %:keyword%) " +
            "and c.eventDate < :todayDate")
    Page<Event> findByPastEventNameAndPlace(@Param("keyword") String keyword,
                                            @Param("todayDate") LocalDate todayDate,
                                            Pageable pageable);

    @Query(value = "Select c from Event c where c.eventDate < :todayDate ")
    Page<Event> findAllPastEvents(@Param("todayDate") LocalDate todayDate, Pageable pageable);
}
