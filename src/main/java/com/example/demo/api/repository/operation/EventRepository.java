package com.example.demo.api.repository.operation;

import com.example.demo.api.entity.operation.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
