package com.sabrigunes.games.blackjack;

import java.util.Random;

class Card {
    public static final char SPADES = '\u2667';
    public static final char HEARTS = '\u2661';
    public static final char DIAMOND = '\u2662';
    public static final char CLUBS = '\u2664';

    public static final String[] TYPES = {"CLUBS", "DIAMOND", "HEARTS", "SPADES"};
    public static final char[] SYMBOLS = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    private static boolean[] ms_cards = new boolean[52]; // order asc: clubs-13   diamond-13   hearts-13   spades-13
    private static final Random random = new Random();


    public Type type;
    public Symbol symbol;
    public Symbol value;

    public Card(Type type, Symbol symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public int getIndex() {
        return type.ordinal() * 13 + symbol.ordinal();
    }

    public static boolean isAvailable(int index) {
        return !ms_cards[index];
    }

    public static void setNotAvailable(int index) {
        ms_cards[index] = true;
    }

    public static int getCardSum(Card[] cards) {
        int sum = 0;
        for (Card card : cards)
            sum += card.symbol.value;
        return sum;
    }

    public static String getCardsSumForPrint(Card[] cards) {
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
            if(Card.isAvailable(index)){
                flag = false;
                Card.setNotAvailable(index);
            }


        } while (flag);

        return card;
    }

}
