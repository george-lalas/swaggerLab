package com.example.swaggerlab.service;


import com.example.swaggerlab.model.PlanetaryData;
import com.example.swaggerlab.repository.PlanetaryDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;


@Service
@Slf4j
public class DBInit implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private PlanetaryDataRepository planetaryDataRepository;


    @Value("classpath:planetaryData.json")
    private Resource planetaryResourceFile;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Initializing database with dummy data...");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PlanetaryData[] planets = objectMapper.readValue(planetaryResourceFile.getFile(), PlanetaryData[].class);
            for(PlanetaryData pD : Arrays.asList(planets)){
                planetaryDataRepository.save(pD);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        log.info("Init service ended!");
    }
}
