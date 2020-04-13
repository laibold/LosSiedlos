package com.laibold.lossiedlos.model.event.custom;

import com.laibold.lossiedlos.model.event.Event;

/**
 * Class to wrap {@link CustomEventType} in class to handle it like {@link com.laibold.lossiedlos.model.event.card.CardEvent}
 */
public class CustomEvent implements Event {

    private CustomEventType type;

    /**
     *  Creates new CustomEvent
     * @param type Type of the Event
     */
    public CustomEvent(CustomEventType type) {
        this.type = type;
    }

    @Override
    /**
     * Gets image resource ID of containing CustomEventType
     */
    public int getImageResourceID() {
        return type.getImageResourceID();
    }

    @Override
    /**
     * Gets string resource ID for desctription of containing CustomEventType
     */
    public int getDescriptionID() {
        return type.getDescriptionID();
    }
}
