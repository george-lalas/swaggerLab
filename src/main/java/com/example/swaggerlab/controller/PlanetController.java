package com.example.swaggerlab.controller;


import com.example.swaggerlab.model.PlanetaryData;
import com.example.swaggerlab.service.PlanetaryDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planet")
@Api(value="planetaryData", description="Operations related to planetary data")
public class PlanetController {

    @Autowired
    private PlanetaryDataService planetaryDataService;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "View a list of available planetary data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<PlanetaryData>> getAllPlanetaryData(){
        return ResponseEntity.status(HttpStatus.OK).body(planetaryDataService.listAllPlanetaryData());
    }

    @ApiOperation(value = "Search a planet with an ID",response = PlanetaryData.class)
    @GetMapping(value = "/show/{id}", produces = "application/json")
    public ResponseEntity showPlanetByID(@PathVariable Integer id){
        PlanetaryData planetaryData = planetaryDataService.getPlanetaryDataById(id);
        if(planetaryData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(planetaryData);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Planet is not found");

    }

    @ApiOperation(value = "Add a planet")
    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity saveProduct(@RequestBody PlanetaryData planetaryData){
        planetaryDataService.savePlanetaryData(planetaryData);
        return ResponseEntity.status(HttpStatus.OK).body("Planet Saved Successfully");
    }

    @ApiOperation(value = "Update a planet")
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody PlanetaryData planetaryData){
        try {
            PlanetaryData storedPlanetaryData = planetaryDataService.getPlanetaryDataById(id);
            storedPlanetaryData.setPlanetName(planetaryData.getPlanetName());
            storedPlanetaryData.setGravityData(planetaryData.getGravityData());
            storedPlanetaryData.setDensityData(planetaryData.getDensityData());
            storedPlanetaryData.setComments(planetaryData.getComments());
            planetaryDataService.savePlanetaryData(storedPlanetaryData);
            return ResponseEntity.status(HttpStatus.OK).body("Planet Updated Successfully");
        }catch (NullPointerException exception){
            return ResponseEntity.status(HttpStatus.OK).body("Planet is not found");
        }
    }

    @ApiOperation(value = "Delete a planet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted planet"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @DeleteMapping(value="/delete/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            planetaryDataService.deletePlanetaryData(id);
            return ResponseEntity.status(HttpStatus.OK).body("Planet Deleted Successfully");
        }catch (EmptyResultDataAccessException exception){
            return ResponseEntity.status(HttpStatus.OK).body("Planet is not found");
        }
    }

}
