package com.sabrigunes.games.blackjack;

import java.util.ArrayList;

class Printer {
    private Printer() {
    }

    public static void printWelcome() {
        System.out.println("  ____  _        _    ____ _  __   _   _    ____ _  __\n" +
                " | __ )| |      / \\  / ___| |/ /  | | / \\  / ___| |/ /\n" +
                " |  _ \\| |     / _ \\| |   | ' /_  | |/ _ \\| |   | ' / \n" +
                " | |_) | |___ / ___ \\ |___| . \\ |_| / ___ \\ |___| . \\ \n" +
                " |____/|_____/_/   \\_\\____|_|\\_\\___/_/   \\_\\____|_|\\_\\\n");
        System.out.println("Blackjack oyununa hoşgeldiniz.\nSkoru ilk 10 olan kazanır, bol şans.\n\n");

    }

    public static String getCard(char cardType, char cardSymbol) {
        return String.format("%n┌───────┐%n|%s      |%n|   %c   |%n|      %c|%n└───────┘%n", cardSymbol, cardType, cardSymbol);
    }

    public static void printlnCard(char cardType, char cardSymbol) {
        System.out.println(getCard(cardType, cardSymbol));
    }

    public static void printlnCards(ArrayList<Card> cards) {
        System.out.println(getCards(cards));
    }

    public static void printlnCards(String prefix, ArrayList<Card> cards) {
        printlnCards(prefix, cards, false);
    }

    public static void printlnCards(String prefix, ArrayList<Card> cards, boolean hideDealerCards) {
        System.out.println(prefix);
        System.out.println(getCards(cards, hideDealerCards));
    }


    public static void printTurn(ArrayList<Card> dealerCards, ArrayList<Card> playerCards, boolean isPrintDealerScore) {
        Printer.printlnCards("Krupiyerin Kartları", dealerCards, !isPrintDealerScore);
        if (isPrintDealerScore)
            System.out.printf("Krupiyerin Elindeki Kartlar : %s%n", Card.getCardSum(dealerCards));


        Printer.printlnCards("Sizin Kartlarınız", playerCards);
        System.out.printf("Elinizdeki Kartlar : %s%n", Card.getCardsSumForPrint(playerCards));
    }

    public static void printTurn(ArrayList<Card> dealerCards, ArrayList<Card> playerCards) {
        printTurn(dealerCards, playerCards, false);
    }

    public static String getCards(ArrayList<Card> cards) {
        return getCards(cards, false);
    }

    public static String getCards(ArrayList<Card> cards, boolean hideDealerCards) {
        String s = "";
        s += getFirstRow(cards.size());
        s += getSecondRow(cards, hideDealerCards);
        s += getThirdRow(cards, hideDealerCards);
        s += getFourthRow(cards, hideDealerCards);
        s += getLastRow(cards.size());
        return s;
    }


    private static String getFirstRow(int len) {
        return "┌───────┐   ".repeat(len) + "\n";
    }


    private static String getSecondRow(ArrayList<Card> cards, boolean hideDealerCards) {
        StringBuilder s = new StringBuilder();
        for (Card card : cards) {
            if (hideDealerCards)
                s.append("|X      |   ");
            else
                s.append(String.format(card.symbol == Symbol.TEN ? "|%s     |   " : "|%s      |   ", card.symbol.cardCorner));

        }
        return s + "\n";
    }

    private static String getThirdRow(ArrayList<Card> cards, boolean hideDealerCards) {
        StringBuilder s = new StringBuilder();
        for (Card card : cards) {
            if (hideDealerCards)
                s.append("|   X   |   ");
            else
                s.append(String.format("|   %c   |   ", card.type.character));

        }
        return s + "\n";
    }

    private static String getFourthRow(ArrayList<Card> cards, boolean hideDealerCards) {
        StringBuilder s = new StringBuilder();
        for (Card card : cards) {
            if (hideDealerCards)
                s.append("|      X|   ");
            else
                s.append(String.format(card.symbol == Symbol.TEN ? "|     %s|   " : "|      %s|   ", card.symbol.cardCorner));
        }
        return s + "\n";
    }

    private static String getLastRow(int len) {
        return "└───────┘   ".repeat(len);
    }

}
