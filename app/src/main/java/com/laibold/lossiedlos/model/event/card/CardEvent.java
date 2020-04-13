package com.laibold.lossiedlos.model.event.card;

import com.laibold.lossiedlos.model.ResourceCard;
import com.laibold.lossiedlos.model.event.Event;

/**
 * Contains {@link CardEventType} and its {@link ResourceCard}
 */
public class CardEvent implements Event {

    private ResourceCard resourceCard;
    private CardEventType type;

    /**
     * Creates new CardEvent
     * @param resourceCard ResourceCard
     * @param type Type of the Event
     */
    public CardEvent(ResourceCard resourceCard, CardEventType type) {
        this.resourceCard = resourceCard;
        this.type = type;
    }

    @Override
    /**
     * Gets image resource ID of containing CustomEventType
     */
    public int getImageResourceID() {
        return resourceCard.getImageResourceID();
    }

    @Override
    /**
     * Gets string resource ID for description of containing CustomEventType
     */
    public int getDescriptionID() {
        return type.getDescriptionID();
    }

    /**
     * @return ID of the string resource of the belonging ResourceCard
     */
    public int getResourceNameID(){
        return resourceCard.getStringResourceID();
    }

    /**
     * @return SymbolString of the belonging type (eg X or x2)
     */
    public String getSymbolString(){
        return type.getSymbolString();
    }

    /**
     * @return ColorID of the belonging type to set color of the symbolString
     */
    public int getColorID() {
        return type.getColorID();
    }

    /**
     * @return CardEventType of the belonging ResourceCard
     */
    public CardEventType getType() {
        return type;
    }
}
