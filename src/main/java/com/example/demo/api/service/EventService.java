package com.example.demo.api.service;

import com.example.demo.api.entity.operation.Event;
import com.example.demo.api.repository.operation.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getEvent() {return eventRepository.findAll();}
}
