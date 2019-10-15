package com.esliceu.service;

import com.eslicue.model.CameraAbbr;
import com.eslicue.model.RoverName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RoverService {
    private static final String API_KEY_NASA = "ShV0umuKcz7vJybpcvHldBMGOLzgb3elRrfbeSJw";
    public void getByMartianSol(int sol, CameraAbbr cameraAbbr, int page, RoverName roverName) throws IOException {
        URL client = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/"+roverName+"/photos?sol="+sol+"&camera="+cameraAbbr+"&api_key="+API_KEY_NASA);
        URLConnection conexion = client.openConnection();
        conexion.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String inputLine;
        String outputLine = "";
        while ((inputLine = in.readLine()) != null) {
            outputLine += inputLine;
        }
        System.out.println(outputLine);
        try {
            JSONParser parser = new JSONParser();
            JSONObject principal = (JSONObject) parser.parse(outputLine);
            JSONObject photoData = (JSONObject) principal.get("photos");



            /*JSONArray meteoritos = (JSONArray) meteoritosData.get(date);
            meteoritos.forEach(meteorito -> {
                System.out.println(meteorito);
            });*/
            //recorrer los meteoritos y guardarlos dentro de un array

        } catch (org.json.simple.parser.ParseException e){
            e.printStackTrace();
        }
        in.close();
    }
}
