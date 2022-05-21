package com.memsource.memsourceapp.events;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PersistAuthenticationTokenEvent extends ApplicationEvent {
    private final String apiClientAuthenticationToken;

    public PersistAuthenticationTokenEvent(Object source, String apiClientAuthenticationToken) {
        super(source);
        this.apiClientAuthenticationToken = apiClientAuthenticationToken;
    }
}
