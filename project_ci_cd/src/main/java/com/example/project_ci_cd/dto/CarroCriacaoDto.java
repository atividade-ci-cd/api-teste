package com.example.project_ci_cd.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarroCriacaoDto {
    private String cor;
    private String marca;
    private String modelo;
    private String placa;
}
