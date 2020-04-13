package com.laibold.lossiedlos.model.event.card;

import com.laibold.lossiedlos.R;

/**
 * Type of Event that is based on a ResourceCard like get double or get none
 */
public enum CardEventType {
    GETNONE("X", R.color.colorEvent_none, R.string.event_getnone_description),
    GETDOUBLE("x2", R.color.colorEvent_double, R.string.event_double_description);

    private String symbolString;
    private int colorID;
    private int descriptionID;

    /**
     * Private constructor
     * @param symbolString SymbolString of the type (eg X or x2)
     * @param colorID ColorID of the belonging type to set color of the symbolString
     * @param descriptionID string resource ID for description
     */
    CardEventType(String symbolString, int colorID, int descriptionID){
        this.symbolString = symbolString;
        this.colorID = colorID;
        this.descriptionID = descriptionID;
    }

    /**
     * @return SymbolString of the type (eg X or x2)
     */
    public String getSymbolString(){
        return symbolString;
    }

    /**
     * @return ColorID of the belonging type to set color of the symbolString
     */
    public int getColorID() {
        return colorID;
    }

    /**
     * @return string resource ID for description
     */
    public int getDescriptionID() {
        return descriptionID;
    }

}