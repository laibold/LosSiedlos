package com.laibold.lossiedlos.controller.event;

import android.app.Activity;

import com.laibold.lossiedlos.model.event.Event;
import com.laibold.lossiedlos.model.ResourceCard;
import com.laibold.lossiedlos.model.event.card.CardEvent;
import com.laibold.lossiedlos.model.event.card.CardEventType;
import com.laibold.lossiedlos.model.event.custom.CustomEvent;
import com.laibold.lossiedlos.model.event.custom.CustomEventType;

import java.util.Random;

/**
 * Generates New Events based on Random
 */
public class EventGenerator {

    private Random random;
    private Event currEvent;

    private int cardQuantity;
    private int cardEventQuantity;
    private int customEventQuantity;
    private int chanceOfEventChange;
    private Activity activity;

    /**
     * New EventGenerator
     * @param activity Belonging Activity
     * @param chanceOfEventChange chance that Event changes (0-100)
     */
    public EventGenerator(Activity activity, int chanceOfEventChange){
        this.random = new Random();
        this.cardQuantity = ResourceCard.values().length -1; // -1 because question mark not included
        this.cardEventQuantity = CardEventType.values().length;
        this.customEventQuantity = CustomEventType.values().length;
        this.activity = activity;
        this.chanceOfEventChange = chanceOfEventChange;

        generateEvent();
    }

    /**
     * @param chanceOfEventChange chance that Event changes (0-100)
     */
    public void setChanceOfEventChange(int chanceOfEventChange) {
        this.chanceOfEventChange = chanceOfEventChange;
    }

    /**
     * Generates new Event (CustomEvent or CardEvent)
     */
    private void generateEvent() {
        int i = random.nextInt(3); // 0 to 2
        int ordinal;
        if (i < 2){ // 2 out of 3 cases
            ordinal = random.nextInt(customEventQuantity);
            this.currEvent = new CustomEvent(CustomEventType.values()[ordinal]);
        } else {
            ordinal = random.nextInt(cardQuantity);
            ResourceCard card = ResourceCard.values()[ordinal];

            ordinal = random.nextInt(cardEventQuantity);
            CardEventType type = CardEventType.values()[ordinal];

            this.currEvent = new CardEvent(card, type);
        }
    }

    /**
     * @return Current Event (CustomEvent or CardEvent)
     */
    public Event getCurrEvent() {
        generateEvent();
        return currEvent;
    }

    /**
     * @return Description of Current Event
     */
    public String getCurrDescription(){
        String s = activity.getResources().getString(currEvent.getDescriptionID());

        if (currEvent instanceof CardEvent){
            String resourceName = activity.getResources().getString(((CardEvent) currEvent).getResourceNameID());
            s = s.replace("$resource", resourceName);
        }

        return s;
    }

    /**
     * @return true if Event sould be changed based on Random
     */
    public boolean changeEvent(){
        int i = random.nextInt(99);
        return (i < chanceOfEventChange - 1);
    }

    /**
     * @return ResourceID of Image belonging to CurrentEvent
     */
    public int getCurrImageResourceID() {
        return currEvent.getImageResourceID();
    }

}
