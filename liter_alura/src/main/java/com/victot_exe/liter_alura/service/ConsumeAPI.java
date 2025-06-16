package com.victot_exe.liter_alura.service;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsumeAPI {

    public String getDataFromURI(String uri){

        System.out.println("Entrou no metodo");

        HttpClient client =HttpClient.newHttpClient();//Criando o client
        HttpRequest request = HttpRequest.newBuilder()//Criando o request
                .uri(URI.create(uri)).build();

        HttpResponse<String> response = null;//Preparando o response

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());//Obtendo o response
        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        System.out.println("Saiu do metodo");

        String json = response.body();//Pegando o body do response e mandando pra frente
        return json;
    }
}
