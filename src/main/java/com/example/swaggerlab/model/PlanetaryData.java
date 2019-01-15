package com.example.swaggerlab.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Data
public class PlanetaryData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated planet ID")
    private long id;
    @Column
    @ApiModelProperty(notes = "The name of the planet", required = true)
    private String planetName;
    @Column
    @ApiModelProperty(notes = "Gravitational data of the planet", required = true)
    private double gravityData;
    @Column
    @ApiModelProperty(notes = "Density data of the planet", required = true)
    private int densityData;
    @Column
    @ApiModelProperty(notes = "Comments")
    private String comments;



}