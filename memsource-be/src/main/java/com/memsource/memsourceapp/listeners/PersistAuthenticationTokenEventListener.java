package com.memsource.memsourceapp.listeners;

import com.memsource.memsourceapp.events.PersistAuthenticationTokenEvent;
import com.memsource.memsourceapp.exceptions.PersistingTokenToFileException;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class PersistAuthenticationTokenEventListener implements ApplicationListener<PersistAuthenticationTokenEvent> {
    @Override
    public void onApplicationEvent(PersistAuthenticationTokenEvent event) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("memsource_application_token"));
            writer.write(event.getApiClientAuthenticationToken());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new PersistingTokenToFileException(e.getMessage());
        }
    }
}
