package com.memsource.memsourceapp.listeners;

import com.memsource.memsourceapp.events.PersistAuthenticationTokenEvent;
import com.memsource.memsourceapp.exceptions.PersistingTokenToFileException;
import org.springframework.context.ApplicationListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PersistAuthenticationTokenEventListener implements ApplicationListener<PersistAuthenticationTokenEvent> {
    @Override
    public void onApplicationEvent(PersistAuthenticationTokenEvent event) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("memsource_application_token"));
            writer.write(event.getApiClientAuthenticationToken());
        } catch (IOException e) {
            throw new PersistingTokenToFileException(e.getMessage());
        }
    }
}
