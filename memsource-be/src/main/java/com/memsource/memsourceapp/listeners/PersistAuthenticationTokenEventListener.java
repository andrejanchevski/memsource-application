package com.memsource.memsourceapp.listeners;

import com.memsource.memsourceapp.events.PersistAuthenticationTokenEvent;
import com.memsource.memsourceapp.exceptions.PersistingTokenToFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
public class PersistAuthenticationTokenEventListener implements ApplicationListener<PersistAuthenticationTokenEvent> {
    @Override
    public void onApplicationEvent(PersistAuthenticationTokenEvent event) {
        try {
            log.info("Persisting token to an external file for scheduled processed");
            BufferedWriter writer = new BufferedWriter(new FileWriter("memsource_application_token"));
            writer.write(event.getApiClientAuthenticationToken());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new PersistingTokenToFileException(e.getMessage());
        }
    }
}
