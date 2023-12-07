package com.yaksha.training.events.controller;

import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/event", "/"})
public class EventController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private EventService eventService;

    @GetMapping(value = {"/list", "/"})
    public String listEvents(Model model) {
        List<Event> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "list-events";
    }

    @GetMapping("/addEventForm")
    public String showFormForAdd(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "add-event-form";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@Valid @ModelAttribute("event") Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (event.getId() != null) {
                return "update-event-form";
            }
            return "add-event-form";
        } else {
            eventService.saveEvent(event);
            return "redirect:/event/list";
        }
    }

    @GetMapping("/updateEventForm")
    public String showFormForUpdate(@RequestParam("eventId") Long id, Model model) {
        Event event = eventService.getEvent(id);
        model.addAttribute("event", event);
        return "update-event-form";
    }

    @GetMapping("/delete")
    public String deleteEvent(@RequestParam("eventId") Long id) {
        eventService.deleteEvent(id);
        return "redirect:/event/list";
    }

    @PostMapping("/search")
    public String searchEvents(@RequestParam("theSearchName") String theSearchName,
                               Model theModel) {

        List<Event> theEvents = eventService.searchEvents(theSearchName);
        theModel.addAttribute("events", theEvents);
        return "list-events";
    }
}
