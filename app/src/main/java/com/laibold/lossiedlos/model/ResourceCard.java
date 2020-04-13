package com.laibold.lossiedlos.model;

import com.laibold.lossiedlos.R;

/**
 * Resource card like wood or bricks
 */
public enum ResourceCard {
    BRICK(R.drawable.img_brick, R.string.brick),
    GRAIN(R.drawable.img_grain, R.string.grain),
    ORE(R.drawable.img_ore, R.string.ore),
    SHEEP(R.drawable.img_sheep, R.string.sheep),
    WOOD(R.drawable.img_wood, R.string.wood),
    ANYTHING(R.drawable.img_questionmark, R.string.anything); //must stay at the end

    private final int imageResourceID;
    private final int stringResourceID;

    /**
     * private constructor
     * @param imageResourceID imageResourceID
     * @param stringResourceID stringResourceID
     */
    ResourceCard(int imageResourceID, int stringResourceID){
        this.imageResourceID = imageResourceID;
        this.stringResourceID = stringResourceID;
    }

    /**
     * Gets ID of the image resource
     * @return ID of image resource
     */
    public int getImageResourceID() {
        return imageResourceID;
    }

    /**
     * Gets ID of the card name
     * @return ID of String resource
     */
    public int getStringResourceID() {
        return stringResourceID;
    }
}
