package com.consultafipe.consultafipe.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ObterDadosAPI {

    public String pegaJson(String endereco) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (IOException e){
            throw new RuntimeException();
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
    }
}
