package br.com.doghero.dhproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.doghero.dhproject.model.Hero;
import br.com.doghero.dhproject.model.User;

public class MyHeroes {

    public static final String RECENTS_HERO_TYPE = "recents";
    public static final String FAVORITES_HERO_TYPE = "favorites";

    public static List<Hero> getHeroList(String json, String heroType) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        List<Hero> heroList = new ArrayList<>();
        Hero hero;
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(heroType);

            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    hero = gson.fromJson(jsonArray.getJSONObject(i).toString(), Hero.class);
                    hero.setUser(gson.fromJson(jsonArray.getJSONObject(i).get("user").toString(), User.class));
                    heroList.add(hero);
                }
                //heroList = Arrays.asList(gson.fromJson(jsonArray.toString(), Hero[].class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return heroList;
    }

}
