package com.example.swaggerlab.service;

import com.example.swaggerlab.model.PlanetaryData;

import java.util.List;

public interface PlanetaryDataService {
   List<PlanetaryData> listAllPlanetaryData();

    PlanetaryData getPlanetaryDataById(long id);

    PlanetaryData savePlanetaryData(PlanetaryData planetaryData);

    void deletePlanetaryData(long id);
}
