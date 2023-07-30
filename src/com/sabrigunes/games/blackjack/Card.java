package com.sabrigunes.games.blackjack;

import java.util.ArrayList;
import java.util.Random;

class Card {
    private static final boolean[] ms_cards = new boolean[52];
    // order: 13 cards clubs(index:0-12)    13 cards diamond(index:13-25)   13 cards hearts(index:26-38)   13 cards spades(index:39-51)

    private static final Random random = new Random();


    public Type type;
    public Symbol symbol;
    public Symbol value;

    public Card(Type type, Symbol symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    private int getIndex() {
        return type.ordinal() * 13 + symbol.ordinal();
    }

    private static boolean isAvailable(int index) {
        return !ms_cards[index];
    }

    private static void setNotAvailable(int index) {
        ms_cards[index] = true;
    }

    public static int getCardSum(ArrayList<Card> cards) {
        int sum = 0;
        for (Card card : cards)
            sum += card.symbol.value;
        return sum;
    }

    public static String getCardsSumForPrint(ArrayList<Card> cards) {
        int sum = 0;
        boolean flag = false;
        for (Card card : cards) {
            if (card.symbol.ordinal() == 0)
                flag = true;
            sum += card.symbol.value;
        }
        return flag && sum + 10 < 21 ? String.format("%d / %d", sum, sum + 10) : sum + "";
    }

    public static Card getRandomCard() {
        boolean flag = true;
        Card card;
        do {
            Type type = Type.values()[random.nextInt(4)];
            Symbol symbol = Symbol.values()[random.nextInt(13)];
            card = new Card(type, symbol);
            int index = card.getIndex();
            if (Card.isAvailable(index)) {
                flag = false;
                Card.setNotAvailable(index);
            }


        } while (flag);

        return card;
    }

}
