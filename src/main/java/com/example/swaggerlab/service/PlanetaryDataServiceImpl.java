package com.example.swaggerlab.service;

import com.example.swaggerlab.model.PlanetaryData;
import com.example.swaggerlab.repository.PlanetaryDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlanetaryDataServiceImpl implements PlanetaryDataService {

    @Autowired
    private PlanetaryDataRepository planetaryDataRepository;

    @Override
    public List<PlanetaryData> listAllPlanetaryData() {
        log.debug("List all planetary data called");
        return planetaryDataRepository.findAll();
    }

    @Override
    public PlanetaryData getPlanetaryDataById(long id) {
        log.debug("getPlanetaryDataById called");
        return planetaryDataRepository.findById(id).orElse(null);
    }

    @Override
    public PlanetaryData savePlanetaryData(PlanetaryData planetaryData) {
        log.debug("savePlanetaryData called");
        return planetaryDataRepository.save(planetaryData);
    }

    @Override
    public void deletePlanetaryData(long id) {
        log.debug("deletePlanetaryData called");
        planetaryDataRepository.deleteById(id);
    }
}
