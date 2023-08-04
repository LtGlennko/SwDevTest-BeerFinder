package com.example.ingerencia.services;
import com.example.ingerencia.repositories.BeerRepository;
import com.example.ingerencia.repositories.LogRepository;
import com.example.ingerencia.models.BeerModel;
import com.example.ingerencia.models.LogModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class BeerService {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    LogRepository logRepository;

    public JSONArray connection(HttpURLConnection conn) throws IOException {
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200)
        {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1)
        {
            sb.append((char) cp);
        }
        String output = sb.toString();
        JSONArray array = new JSONArray(output);
        return array;
    }

    public ArrayList<BeerModel> getAllBeers(){
        //Tiempo inicial
        long startTime = System.nanoTime();
        //Búsqueda
        ArrayList<BeerModel> result = (ArrayList<BeerModel>) beerRepository.findAll();
        //Tiempo final
        long endTime = System.nanoTime();
        logRepository.save((LogModel) new LogModel("getAllBeers",(endTime-startTime)/10e6));
        return result;
    }

    public BeerModel saveBeer(BeerModel beer){
        return beerRepository.save(beer);
    }

    public ArrayList<BeerModel> findByName(String name){
        //Tiempo inicial
        long startTime = System.nanoTime();
        //Búsqueda
        ArrayList<BeerModel> result = beerRepository.findByvName(name);
        //Tiempo final
        long endTime = System.nanoTime();
        logRepository.save((LogModel) new LogModel("findByName",(endTime-startTime)/10e6));
        return result;
    }

    public BeerModel pickRandom(){
        //Tiempo inicial
        long startTime = System.nanoTime();
        //Búsqueda
        ArrayList<BeerModel> result = beerRepository.findByRandom();
        //Tiempo final
        long endTime = System.nanoTime();
        logRepository.save((LogModel) new LogModel("pickRandom",(endTime-startTime)/10e6));
        if(result.isEmpty())
            return null;
        else
            return result.get(0);
    }

}
