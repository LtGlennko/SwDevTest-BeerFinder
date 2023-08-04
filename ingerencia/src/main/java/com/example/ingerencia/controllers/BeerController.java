package com.example.ingerencia.controllers;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import com.example.ingerencia.services.BeerService;
import com.example.ingerencia.models.BeerModel;

@RestController
@RequestMapping("/beer")
public class BeerController implements CommandLineRunner{
    @Autowired
    BeerService beerService;

    @Override
    public void run(String... args) throws Exception {
        //Ejecuta método al ejecutar el proyecto
        saveAllBeersFromPunkapi();
    }

    public String saveAllBeersFromPunkapi(){
        //Se agrego lo siguiente al link para que se pueda utilizar la busqueda documentada en la api
        String newURL = "https://api.punkapi.com/v2/beers";
        //List<Beer> listBeer = new ArrayList<>();
        try
        { //Se realiza la conexion con la api externa
            URL url = new URL(newURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Se llama al metodo privado para realizar la conexion
            JSONArray array = beerService.connection(conn);
            int lengthArray = array.length();
            for(int i=0; i<lengthArray; i++){
                //Object posee el json recibido, ahora solo se necesita llenar uno por uno los datos al objeto beer
                JSONObject obj = array.getJSONObject(i);
                
                BeerModel beer = new BeerModel(
                    obj.getLong("id"),
                    obj.getString("name"),
                    obj.getString("tagline"),
                    obj.getInt("attenuation_level"));
                this.beerService.saveBeer(beer);
            }
            conn.disconnect();
            return "punkAPI data correctly saved.";
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "Error in punkAPI save data.";
    }

    //Mostrar todas las cervezas disponibles
    @GetMapping()
    public ArrayList<BeerModel> getAllBeers(){
        return beerService.getAllBeers();
    }

    //Búsqueda de cervezas por nombre
    @GetMapping("/query")
    public ArrayList<BeerModel> findByName(@RequestParam("name") String name){
        return this.beerService.findByName(name);
    }

    //Recomendar una cerveza de forma aleatoria
    @GetMapping("/random")
    public BeerModel pickRandom(){
        return this.beerService.pickRandom();
    }
}
