package com.sabrigunes.games.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private static int dealerScore;
    private static int playerScore;
    private static ArrayList<Card> dealerCards = new ArrayList<>();
    private static ArrayList<Card> playerCards = new ArrayList<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        playGame();
    }

    private static void playGame() {
        Printer.printWelcome();
        while (dealerScore < 10 || playerScore < 10) {
            playTurn();
        }
        if (dealerScore == 10)
            System.out.println("Üzgünüm, buraya kadar. Oyunu krupiyer kazandı.");
        else
            System.out.println("Oyunu siz kazandınız.");
    }

    private static void playTurn() {
        startTurn();
        boolean flag = true;
        while (flag) {
            if (isPlayerReach21()) {
                System.out.println(Card.getCardSum(playerCards) == 21 ? "BLACKJACK !" : "21 sınırı aşıldı.");
                break;
            }
            if (isCardDrawn()) {
                playerCards = drawnNewCard(playerCards, "Oyuncu kart çekiyor.");
                workForDealer();
                Printer.printTurn(dealerCards, playerCards);
            } else
                break;
        }
        if (Card.getCardSum(dealerCards) < 16)
            workForDealerWithLoop();
        finishTurn();
    }

    private static void finishTurn() {
        System.out.println("------------------------------------------------------------------------------------\n\nEl tamamlandı.");
        Printer.printTurn(dealerCards, playerCards, true);
        System.out.println("------------------------------------------------------------------------------------");
        int playerSum = Card.getCardSum(playerCards);
        int dealerSum = Card.getCardSum(dealerCards);

        if (playerSum < dealerSum && dealerSum <= 21 || (playerSum > 21 && dealerSum <= 21)) {
            System.out.println("Eli krupiyer kazandı.");
            ++dealerScore;
        } else if (playerSum == dealerSum || (playerSum > 21 && dealerSum > 21))
            System.out.println("El berabere bitti.");
        else if (dealerSum < playerSum && playerSum <= 21 || (dealerSum > 21 && playerSum <= 21)){
            System.out.println("Eli siz kazandınız.");
            ++playerScore;
        }

        System.out.printf("Skor Durumu%nSiz      : %d%nKrupiyer : %d%n%n", playerScore, dealerScore);
        resetStats();
    }

    private static void resetStats(){
        playerCards = new ArrayList<Card>();
        dealerCards = new ArrayList<Card>();
    }

    private static void workForDealer() {
        if (Card.getCardSum(dealerCards) < 16)
            dealerCards = drawnNewCard(dealerCards, "Krupiyer kart çekiyor");
    }

    private static void workForDealerWithLoop() {
        while (Card.getCardSum(dealerCards) < 16)
            dealerCards = drawnNewCard(dealerCards, "Krupiyer kart çekiyor");
    }


    private static boolean isPlayerReach21() {
        return Card.getCardSum(playerCards) >= 21;
    }

    private static ArrayList<Card> drawnNewCard(ArrayList<Card> cards, String msg) {
        System.out.println(msg);
        cards.add(Card.getRandomCard());
        return cards;
    }


    private static void startTurn() {
        dealerCards.add(Card.getRandomCard());
        dealerCards.add(Card.getRandomCard());
        playerCards.add(Card.getRandomCard());
        playerCards.add(Card.getRandomCard());

        Printer.printTurn(dealerCards, playerCards, false);
    }

    private static boolean isCardDrawn() {
        while (true) {
            System.out.println("Yeni kart çekmek ister misiniz? (Y/N)");
            String s = scanner.nextLine().toLowerCase();
            if (s.equals("y") || s.equals("yes"))
                return true;
            else if (s.equals("n") || s.equals("no"))
                return false;
            System.out.println("Yanlış giriş yaptınız.");
        }
    }
}
