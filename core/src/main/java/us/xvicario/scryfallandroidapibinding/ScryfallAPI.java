package us.xvicario.scryfallandroidapibinding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ScryfallAPI {

    private static final String API_URL = "https://api.scryfall.com";
    private static final Gson GSON = new Gson();

    /**
     * Search scryfall!
     * check out scryfall's syntax here: https://www.scryfall.com/docs/syntax
     * @param query your query
     * @return a list of card objects that match the given query
     * @throws IOException
     */
    public static ArrayList<Card> searchScryfall(String query) throws IOException {
        String escapeQuery = URLEncoder.encode(query, "UTF-8");
        return getCardsFromUrl(new URL(API_URL + "/cards/search?q=" + escapeQuery));
    }

    /**
     * Fetches a card from the given url
     * @param cardUrl the URL to pull the card from
     * @return the card
     * @throws IOException if the input stream cannot be read
     */
    public static Card getCardFromUrl(URL cardUrl) throws IOException {
        URLConnection connection = cardUrl.openConnection();
        InputStreamReader in = new InputStreamReader(connection.getInputStream(), "UTF-8");
        Card card = GSON.fromJson(in, Card.class);
        in.close();
        return card;
    }

    /**
     * returns an array list of cards from the given url
     * @param cardsUrl the url to get the cards from
     * @return an arraylist of card objects
     * @throws IOException when reading from the input stream fails
     */
    public static ArrayList<Card> getCardsFromUrl(URL cardsUrl) throws IOException {
        ArrayList<Card> cards = new ArrayList<>();
        URLConnection connection = cardsUrl.openConnection();
        InputStreamReader in = new InputStreamReader(connection.getInputStream(), "UTF-8");
        /*JsonObject root;
        try {
            root = (JsonObject) Jsoner.deserialize(in);
        } catch (DeserializationException de) {
            in.close();
            return null;
        }
        in.close();
        JsonArray jsonCards = (JsonArray) root.get("data");
        for (Object card : jsonCards) {
            cards.add(new Card((JsonObject) card));
        }
        if (root.containsKey("has_more") && root.getBoolean("has_more")) {
            String next = root.getString("next_page");
            try {
                Thread.sleep(50); // Scryfall's wait time between queries
            } catch (InterruptedException ie) {}
            cards.addAll(getCardsFromUrl(new URL(next)));
        }*/
        return cards;
    }

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

    public static Card getCardFromMultiverse(int multiverseId) {
        try {
            URL cardURL = new URL(API_URL + "/cards/multiverse/" + multiverseId);
            return getCardFromUrl(cardURL);
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
            URL cardURL = new URL(API_URL + "/cards/multiverse/" + multiverseId + "?format=image");
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
    public static ArrayList<Set> getSets() throws IOException {
        ArrayList<Set> sets = new ArrayList<>();
        URL setsURL = new URL(API_URL + "/sets");
        URLConnection connection = setsURL.openConnection();
        InputStreamReader in = new InputStreamReader(connection.getInputStream(), "UTF-8");
        /*try {
            JsonObject root = (JsonObject) Jsoner.deserialize(in);
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
        } catch (Exception de) {
            in.close();
            return null;
        }*/
        return null;
    }

}
