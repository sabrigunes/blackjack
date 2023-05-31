package com.sabrigunes.games.blackjack;

// TODO: Learn and use
public enum Type {
    CLUBS('\u2664'), DIAMOND('\u2662'), HEARTS('\u2661'), SPADES('\u2667');

    public final char character;

    Type(char ch){
        character = ch;
    }
}