package us.xvicario.scryfallandroidapibinding;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a single printing of one card. "Special" cards (transforming/meld cards, for instance),
 * will have one Card object for each state of the card.
 * @author ForOhForError
 */

public class Card {

    @SerializedName("name")
    private String name;
    @SerializedName("mana_cost")
    private String manaCost;
    @SerializedName("converted_mana_cost")
    private Double cmc;
    @SerializedName("type_line")
    private String typeLine;
    @SerializedName("oracle_text")
    private String oracleText;
    @SerializedName("colors")
    private String[] colors;
    @SerializedName("color_identity")
    private String[] colorIdentity;
    @SerializedName("layout")
    private String layout;
    @SerializedName("legalities")
    private HashMap<String, String> legalities;
    @SerializedName("reserved")
    private boolean reserved;
    @SerializedName("id")
    private String scryfallUUID;
    @SerializedName("multiverse_id")
    private Integer multiverseID;
    @SerializedName("mtgo_id")
    private Integer mtgoID;
    @SerializedName("set")
    private String setCode;
    @SerializedName("set_name")
    private String setName;
    @SerializedName("collector_number")
    private String collectorNumber;
    private boolean multiPart;
    @SerializedName("all_parts")
    private ArrayList<CardReference> allParts;
    @SerializedName("rarity")
    private String rarity;
    @SerializedName("digital")
    private boolean digitalOnly;
    @SerializedName("flavor_text")
    private String flavorText;
    @SerializedName("artist")
    private String artist;
    @SerializedName("frame")
    private String frame;
    @SerializedName("border_color")
    private String border;
    @SerializedName("timeshifted")
    private boolean timeShifted;
    @SerializedName("colorshifted")
    private boolean colorShifted;
    @SerializedName("futureshifted")
    private boolean futureShifted;
    @SerializedName("usd")
    private Double priceUsd;
    @SerializedName("tix")
    private Double priceTix;
    @SerializedName("uri")
    private String scryfallUri;
    @SerializedName("image_uri")
    private String imageURI;

    /**
     * Constructs a Card
     */
    public Card() {}

    /**
     * Utility method for reading each part of multipart cards.
     */
    /*private static ArrayList<CardReference> getAllParts(JsonObject data, String key)
    {
        Object obj = data.get(key);
        if(obj==null){
            return null;
        }

        ArrayList<CardReference> refs = new ArrayList<>();

        JsonArray arr = (JsonArray)obj;
        for(Object o:arr)
        {
            JsonObject j = (JsonObject)o;
            //refs.add(new CardReference((String)j.get("name"),(String)j.get("uri"),(String)j.get("id")));
        }
        return refs;
    }*/

    /**
     * Returns a list of CardReference objects referencing all parts of a "special"
     * multipart card. This will include a reference to this card.
     * Will be null if isMultiPart() == false.
     */
    /*public ArrayList<CardReference> getPartReferences(){
        return new ArrayList<>(allParts);
    }*/

    /**
     * Returns the legality of this card in the given format.
     * @param format the format to check. Case insensitive.
     */
    public String getLegality(String format) {
        return legalities.get(format.toLowerCase());
    }

    /**
     * Returns true if the card is strictly legal in the given format, and
     * false otherwise. NOTE: cards that are restricted in the format
     * will return false.
     * @param format the format to check. Case insensitive.
     */
    public boolean isLegal(String format) {
        return "legal".equals(legalities.get(format.toLowerCase()));
    }

    /**
     * Returns this card's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns this card's mana cost.
     */
    public String getManaCost() {
        return manaCost;
    }

    /**
     * Returns this card's converted mana cost.
     */
    public Double getCmc() {
        return cmc;
    }

    /**
     * Returns this card's full type line.
     */
    public String getTypeLine() {
        return typeLine;
    }

    /**
     * Returns this card's oracle text.
     */
    public String getOracleText() {
        return oracleText;
    }

    /**
     * Returns a list of this card's colors.
     */
    public String[] getColors() {
        return colors;
    }

    /**
     * Returns a list of the colors in this card's color identity.
     */
    public String[] getColorIdentity() {
        return colorIdentity;
    }

    /**
     * Returns this card's layout type.
     */
    public String getLayout() {
        return layout;
    }

    /**
     * Returns true if this card is on the reserved list, and false otherwise.
     */
    public boolean isReserved() {
        return reserved;
    }

    /**
     * Returns scryfall's internal id for this card.
     */
    public String getScryfallUUID() {
        return scryfallUUID;
    }

    /**
     * Returns this card's multiverse id.
     */
    public Integer getMultiverseID() {
        return multiverseID;
    }

    /**
     * Returns this card's MTGO id.
     */
    public Integer getMtgoID() {
        return mtgoID;
    }

    /**
     * Returns the 3-character set code for this printing of the card.
     */
    public String getSetCode() {
        return setCode;
    }

    /**
     * Returns the set name for this printing of the card.
     */
    public String getSetName() {
        return setName;
    }

    /**
     * Returns the collector number for this printing of the card.
     */
    public String getCollectorNumber() {
        return collectorNumber;
    }

    /**
     * Returns true if this card has multiple parts, and false otherwise.
     * The other parts are referenced in the list returned by getPartReferences().
     */
    public boolean isMultiPart() {
        return multiPart;
    }

    /**
     * Returns the rarity for this printing of the card.
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Returns true if this printing of the card is only available on MTGO, and
     * false otherwise.
     */
    public boolean isDigitalOnly() {
        return digitalOnly;
    }

    /**
     * Returns the flavor text for this printing of the card.
     */
    public String getFlavorText() {
        return flavorText;
    }

    /**
     * Returns the artist text for this printing of the card.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the type of frame used for this printing of the card.
     */
    public String getFrame() {
        return frame;
    }

    /**
     * Returns color of border used for this printing of the card.
     */
    public String getBorder() {
        return border;
    }

    /**
     * Returns true if this card is "Time Shifted" from the past, and
     * false otherwise.
     */
    public boolean isTimeShifted() {
        return timeShifted;
    }

    /**
     * Returns true if this card is "Color Shifted" from another card, and
     * false otherwise.
     */
    public boolean isColorShifted() {
        return colorShifted;
    }

    /**
     * Returns true if this card is "Future Shifted" from the future (spooky),
     * and false otherwise.
     */
    public boolean isFutureShifted() {
        return futureShifted;
    }

    /**
     * Returns this printing's price in USD. Formatted as a String "Dollars.Cents".
     */
    public Double getPriceUsd() {
        return priceUsd;
    }

    /**
     * Returns this printing's price in MTGO tickets. Formatted as a String "Tickets.Hundreths".
     */
    public Double getPriceTix() {
        return priceTix;
    }

    /**
     * Returns a URI for the scryfall API page for this card.
     */
    public String getScryfallUri() {
        return scryfallUri;
    }

    /**
     * Returns a URI for scryfall's image of this card.
     */
    public String getImageURI() {
        return imageURI;
    }

    /**
     * Returns a simplification of this card, used when printing it.
     */
    public String toString() {
        return "Card [name=" + name + ", setCode=" + setCode + "]";
    }

    /**
     * Hashcode method; works off of name and set code. Auto-generated.
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((setCode == null) ? 0 : setCode.hashCode());
        return result;
    }

    /**
     * Equals method; checks for name and set code equality. Auto-generated.
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (setCode == null) {
            if (other.setCode != null)
                return false;
        } else if (!setCode.equals(other.setCode))
            return false;
        return true;
    }

}