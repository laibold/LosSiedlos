package com.laibold.lossiedlos.controller.trading;

import com.laibold.lossiedlos.model.ResourceCard;
import java.util.Random;

/**
 * Generates trading rates for MainActivity
 */
public class TradingGenerator {

    private Random random;

    private ResourceCard giveCard;
    private ResourceCard getCard;
    private int giveRate;
    private int cardQuantity;
    private int chanceOfTradingChange;

    /**
     * New TradingGenerator
     * @param chanceOfTradingChange chance that trading changes (0-100)
     */
    public TradingGenerator(int chanceOfTradingChange){
        this.random = new Random();
        this.cardQuantity = ResourceCard.values().length;

        this.giveCard = generateGiveCard();
        this.getCard = generateGetCard();
        this.giveRate = generateGiveRate();
        this.chanceOfTradingChange = chanceOfTradingChange;
    }

    /**
     * @param chanceOfTradingChange chance that trading changes (0-100)
     */
    public void setChanceOfTradingChange(int chanceOfTradingChange) {
        this.chanceOfTradingChange = chanceOfTradingChange;
    }

    /**
     * Generates a card that is given for trading. Is never the same as the card you get by trading
     * @return ResourceCard
     */
    private ResourceCard generateGiveCard(){
        int ordinal;
        if (getCard != null){
            ordinal = getCard.ordinal();
            while (getCard == null || ordinal == getCard.ordinal()){
                ordinal = random.nextInt(cardQuantity); //between 0 and 4
            }
        } else{
            ordinal = random.nextInt(cardQuantity);
        }

        return ResourceCard.values()[ordinal];
    }

    /**
     * Generates a card that is get by trading. Is never the same as the card you give for trading
     * @return ResourceCard
     */
    private ResourceCard generateGetCard(){
        int ordinal;
        if (giveCard != null){
            ordinal = giveCard.ordinal();
            while (giveCard == null || ordinal == giveCard.ordinal()){
                ordinal = random.nextInt(cardQuantity); //between 0 and 4
            }
        } else{
            ordinal = random.nextInt(cardQuantity);
        }

        return ResourceCard.values()[ordinal];
    }

    /**
     * generates a rate between 1 and 3 inclusively while 3 is more likely
     * @return trading rate
     */
    private int generateGiveRate(){
        int i = random.nextInt(10); //between 0 and 9
        if (i < 1) { // 1 out of 10 cases
            return 1;
        } else if (i < 5) { // 5 out of 10 cases
            return 3;
        } else {
            return 2; // 4 out of 10 cases
        }
    }

    /**
     * @return Card to be given at trading
     */
    public ResourceCard getGiveCard() {
        this.giveCard = generateGiveCard();
        return giveCard;
    }

    /**
     * @return Card to get for trading
     */
    public ResourceCard getGetCard() {
        this.getCard = generateGetCard();
        return getCard;
    }

    /**
     * @return Trading rate between 1 and 3 inclusively
     */
    public int getGiveRate() {
        this.giveRate = generateGiveRate();
        return giveRate;
    }

    /**
     * Returns if trading should be changed based on Random and chanceOfTradingChange in config
     * @return true if trading should be changed
     */
    public boolean changeTrading(){
        int i = random.nextInt(99);
        return (i < chanceOfTradingChange - 1);
    }
}
