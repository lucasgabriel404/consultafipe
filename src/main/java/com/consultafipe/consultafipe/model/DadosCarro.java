package com.consultafipe.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCarro(@JsonAlias("Valor") String valor,
                         @JsonAlias("Marca") String marca,
                         @JsonAlias("Modelo")String modelo,
                         @JsonAlias("AnoModelo") int anoModelo) {
}
