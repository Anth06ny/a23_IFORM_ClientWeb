package org.example;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.beans.ErrorBean;
import org.example.beans.PersonBean;
import org.example.beans.WeatherBean;

import java.io.IOException;

public class RequestUtils {

    public static final Gson gson = new Gson();
    public final static OkHttpClient client = new OkHttpClient();

    private static final String URL_API_WEATHER = "https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q=";
    private static final String URL_API_RANDOM_USER = "https://www.amonteiro.fr/api/randomuser";

    public static void main(String[] args) {
        try {
//            WeatherBean res = loadWeather("nice");
//            System.out.println("Il fait " + res.getMain().getTemp() + "° à " + res.getName() + " avec un vent de " + res.getWind().getSpeed() + "km/h");
            for (int i = 0; i < 20; i++) {
                PersonBean person = RequestUtils.loadRandomUser();
                String tel = "Aucun", mail = "Aucun";
                if (person.getCoord() != null) {
                    if (person.getCoord().getMail() != null && !person.getCoord().getMail().isBlank()) {
                        mail = person.getCoord().getMail();
                    }

                    if (person.getCoord().getPhone() != null && !person.getCoord().getPhone().isBlank()) {
                        tel = person.getCoord().getPhone();
                    }
                }

                System.out.println(person.getName() + " âgé de " + person.getAge() + " ans, ses coordonnées :\n-Téléphone : " + tel);
                System.out.println("-mail :" + mail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PersonBean loadRandomUser() throws Exception {

        String json = sendGet(URL_API_RANDOM_USER);
        PersonBean data = new Gson().fromJson(json, PersonBean.class);
        return data;
    }

    public static WeatherBean loadWeather(String city) throws Exception {

        if (city.isBlank() || city.length() < 3) {
            throw new Exception("Il faut au moins 3 caractères");
        }

        String json = sendGet(URL_API_WEATHER + city);
        WeatherBean weather = new Gson().fromJson(json, WeatherBean.class);

        return weather;
    }

    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                //Si j'ai un JSON associé
                String json = response.body().string();
                if (json.contains("{") && json.contains("}")) {
                    ErrorBean error = new Gson().fromJson(json, ErrorBean.class);
                    throw new Exception(error.getMessage());
                }


                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public static String sendPost(String url, String jsonAEnvoyer) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Corps de la requête
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonAEnvoyer);

        //Création de la requête
        Request request = new Request.Builder().url(url).post(body).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }


}
