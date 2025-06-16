package com.victot_exe.liter_alura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ConvertDataImpl implements IConvertData{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> clazz) {

        try{
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getDataToList(String json, Class<T> clazz){
        try{
            JsonNode root = mapper.readTree(json);
            JsonNode resultsNode = root.get("results");
            return mapper.readerForListOf(clazz).readValue(resultsNode);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
