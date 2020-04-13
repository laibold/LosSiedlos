package com.laibold.lossiedlos.controller.event;

import android.app.Activity;

import com.laibold.lossiedlos.model.Config;
import com.laibold.lossiedlos.model.event.Event;
import com.laibold.lossiedlos.model.ResourceCard;
import com.laibold.lossiedlos.model.event.card.CardEvent;
import com.laibold.lossiedlos.model.event.card.CardEventType;
import com.laibold.lossiedlos.model.event.custom.CustomEvent;
import com.laibold.lossiedlos.model.event.custom.CustomEventType;

import java.util.Random;

public class EventGenerator {

    private Random random;
    private Event currEvent;
    private Config config;

    private int cardQuantity;
    private int cardEventQuantity;
    private int customEventQuantity;
    private Activity activity;

    public EventGenerator(Activity activity){
        this.random = new Random();
        this.cardQuantity = ResourceCard.values().length -1; // -1 because question mark not included
        this.cardEventQuantity = CardEventType.values().length;
        this.customEventQuantity = CustomEventType.values().length;
        this.activity = activity;

        this.config = Config.getInstance();

        generateEvent();
    }

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

    public Event getCurrEvent() {
        generateEvent();
        return currEvent;
    }

    public String getCurrDescription(){
        String s = activity.getResources().getString(currEvent.getDescriptionID());

        if (currEvent instanceof CardEvent){
            String resourceName = activity.getResources().getString(((CardEvent) currEvent).getResourceNameID());
            s = s.replace("$resource", resourceName);
        }

        return s;
    }

    public boolean changeEvent(){
        int i = random.nextInt(99); //between 0 and 8
        if (i < config.getChanceOfEventChange() - 1) { // 3 out of 9 cases
            return true;
        } else {
            return false;
        }
    }

    public int getCurrImageResourceID() {
        return currEvent.getImageResourceID();
    }

}
