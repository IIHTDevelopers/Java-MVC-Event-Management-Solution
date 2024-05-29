package com.yaksha.training.events.controller;

import com.yaksha.training.events.entity.Event;
import com.yaksha.training.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String listEvents(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Event> events = eventService.getEvents(pageable);
        model.addAttribute("events", events.getContent());
        model.addAttribute("theSearchName", "");
        model.addAttribute("totalPage", events.getTotalPages());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
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

    @RequestMapping("/search")
    public String searchEvents(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                               @PageableDefault(size = 5) Pageable pageable,
                               Model theModel) {
        Page<Event> theEvents = eventService.searchEvents(theSearchName, pageable);
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("events", theEvents.getContent());
        theModel.addAttribute("totalPage", theEvents.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-events";
    }

    @RequestMapping("/pastEvents")
    public String pastEventsEvents(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                                   @PageableDefault(size = 5) Pageable pageable,
                                   Model theModel) {
        Page<Event> theEvents = eventService.pastEvents(theSearchName, pageable);
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("events", theEvents.getContent());
        theModel.addAttribute("totalPage", theEvents.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "past-events";
    }
}
