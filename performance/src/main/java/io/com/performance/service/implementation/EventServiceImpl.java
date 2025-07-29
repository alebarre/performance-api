package io.com.performance.service.implementation;

import io.com.performance.domain.UserEvent;
import io.com.performance.enumeration.EventType;
import io.com.performance.repository.EventRepository;
import io.com.performance.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Collection<UserEvent> getEventsByUserId(Long UserId) {
        return eventRepository.getEventsByUserId(UserId);
    }

    @Override
    public void addUserEvent(String email, EventType eventType, String device, String ipAddress) {
        eventRepository.addUserEvent(email, eventType, device, ipAddress);
    }

    @Override
    public void addUserEvent(Long UserId, EventType eventType, String device, String ipAddress) {

    }
}
