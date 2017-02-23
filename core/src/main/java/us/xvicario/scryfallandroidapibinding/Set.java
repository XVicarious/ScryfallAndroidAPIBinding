package us.xvicario.scryfallandroidapibinding;

import java.util.Date;

class Set {

    private String setCode;
    private String setName;
    private String setType;
    private Date setReleased;

    Set(String setCode, String setName, String setType, Date setReleased) {
        this.setCode = setCode;
        this.setName = setName;
        this.setType = setType;
        this.setReleased = setReleased;
    }

    public String getSetCode() {
        return this.setCode;
    }

    public String getSetName() {
        return this.setName;
    }

    public String getSetType() {
        return this.setType;
    }

    public Date getReleaseDate() {
        return this.setReleased;
    }

}