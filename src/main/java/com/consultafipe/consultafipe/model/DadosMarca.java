package com.consultafipe.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarca(@JsonAlias("codigo") int codigo,
                         @JsonAlias("nome")   String nome){
}
