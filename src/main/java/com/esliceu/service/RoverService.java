package com.esliceu.service;

import com.eslicue.model.CameraAbbr;
import com.eslicue.model.Photo;
import com.eslicue.model.RoverName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoverService {
    private static final String API_KEY_NASA = "ShV0umuKcz7vJybpcvHldBMGOLzgb3elRrfbeSJw";

    public List<Photo> getByMartianSol(int sol, CameraAbbr cameraAbbr, int page, RoverName roverName) throws IOException {
        URL client = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/" + roverName + "/photos?sol=" + sol + "&camera=" + cameraAbbr + "&api_key=" + API_KEY_NASA);
        URLConnection conexion = client.openConnection();
        conexion.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String inputLine;
        String outputLine = "";
        List<Photo> photos = new ArrayList<>();
        while ((inputLine = in.readLine()) != null) {
            outputLine += inputLine;
        }
        try {
            JSONParser parserNasa = new JSONParser();
            JSONObject objNasa;
            objNasa = (JSONObject) parserNasa.parse(outputLine);
            JSONArray photoObjects = (JSONArray) objNasa.get("photos");
            System.out.println(objNasa);
            for (Object object : photoObjects) {
                JSONObject photoObject = (JSONObject) object;
                String url = (String) photoObject.get("img_src");
                String earthDate = (String) photoObject.get("earth_date");
                String[] descompossedDate = earthDate.split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(descompossedDate[0]), Integer.parseInt(descompossedDate[1]), Integer.parseInt(descompossedDate[2]));
                Photo photo = new Photo(date, url);
                photos.add(photo);

            }
        } catch (ParseException ignored) {
        }
        in.close();
        return photos;
    }
}
