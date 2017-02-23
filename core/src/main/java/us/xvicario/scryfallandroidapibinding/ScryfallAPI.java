package us.xvicario.scryfallandroidapibinding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScryfallAPI {

    private static final String API_URL = "https://api.scryfall.com";

    /**
     * Retrieves a bitmap of the card from the provided URL
     * @param cardUrl the URL to the card image
     * @return a bitmap containing the card image
     */
    public static Bitmap getCardImageFromURL(URL cardUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) cardUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException ioe) {
            return null;
        }
    }

    /**
     * Returns a bitmap of the card with the given multiverse id
     * @param multiverseId the multiverse id of the card
     * @return bitmap of the card with multiverseId
     */
    public static Bitmap getCardImageFromMultiverse(int multiverseId) {
        try {
            URL cardURL = new URL(API_URL + "/cards/multiverseid/" + multiverseId + "?format=image");
            return getCardImageFromURL(cardURL);
        } catch (MalformedURLException mue) {
            return null;
        }
    }

    /**
     * Returns a bitmap of the card with collectorsNumber in setCode
     * @param setCode the code of the set you want the card from
     * @param collectorsNumber the collectors number of the card
     * @return bitmap of the card collectorsNumber from setCode
     */
    public static Bitmap getCardImageFromSetAndNumber(String setCode, int collectorsNumber) {
        try {
            URL cardURL = new URL(API_URL + "/cards/" + URLEncoder.encode(setCode, "UTF-8") + "/" + collectorsNumber);
            return getCardImageFromURL(cardURL);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns a list of sets
     * @return an arraylist of sets of magic cards
     */
    public static ArrayList<Set> getSets() {
        ArrayList<Set> sets = new ArrayList<>();
        try {
            URL setsURL = new URL(API_URL + "/sets");
            URLConnection connection = setsURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String json = "";
            String line = "";
            while (line != null) {
                json += line;
                line = in.readLine();
            }
            JsonObject root;
            root = (JsonObject) Jsoner.deserialize(json);
            in.close();
            JsonArray jsonSets = (JsonArray) root.get("data");
            for (Object set : jsonSets) {
                JsonObject jsonSet = (JsonObject) set;
                String name = jsonSet.getString("name");
                String code = jsonSet.getString("code");
                String type = jsonSet.getString("set");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(jsonSet.getString("released_at"));
                sets.add(new Set(code, name, type, date));
            }
            return sets;
        } catch (Exception e) {
            return null;
        }
    }

}
