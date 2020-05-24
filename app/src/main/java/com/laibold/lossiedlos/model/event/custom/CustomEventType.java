package com.laibold.lossiedlos.model.event.custom;

import com.laibold.lossiedlos.R;

/**
 * Type of Event that is not based on a ResourceCard like robber sleeping or none
 */
public enum CustomEventType {
    SLEEPING(R.string.event_sleeping_description, R.drawable.img_sleep),
    NONE(R.string.event_none_description, R.drawable.img_catan),
    NO_TRADE(R.string.event_no_trade_description, R.drawable.img_no_trade);

    private int colorID;
    private int descriptionID;
    private int imageResourceID;

    /**
     * private constructor
     * @param descriptionID descriptionID
     * @param imageResourceID imageResourceID
     */
    CustomEventType(int descriptionID, int imageResourceID){
        this.descriptionID = descriptionID;
        this.imageResourceID = imageResourceID;
    }

    /**
     * Gets ID of the description string
     * @return ID of string resource
     */
    public int getDescriptionID() {
        return descriptionID;
    }

    /**
     * Gets ID of the image resource
     * @return ID of image resource
     */
    public int getImageResourceID() {
        return imageResourceID;
    }
}
