package com.consultafipe.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(@JsonAlias("codigo") String codigo,
                           @JsonAlias("nome") String nome) {
}
