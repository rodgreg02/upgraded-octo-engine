import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {


   static public int play(int playerMoney){
       Card[] deck = Card.initializeDeck();
       boolean quit = false;
       int bet = 0;
       Random rnd = new Random();
       Scanner scanner = new Scanner(System.in);
        Splash.displayBlackjackLogo();
       System.out.println("Place your bets: " + "\t\t\t\t\t\t\t\t" + "Available balance: " + playerMoney + " V bucks.");
       System.out.println(Color.CLEAR_CONSOLE);
       while (!quit){
           boolean handOver = false;
           scanner.reset();
           bet = scanner.nextInt();
           Card[] playerHand = new Card[6];
           Card[] houseHand = new Card[13];
               playerHand[0] = Card.getRandomCard(deck);
               houseHand[0] = Card.getRandomCard(deck);
               playerHand[1] = Card.getRandomCard(deck);
               System.out.println(Color.CLEAR_CONSOLE);
               System.out.println(Color.BLUE + "╦ ╦╔═╗╦ ╦╔═╗╔═╗╔═╗  ╦ ╦╔═╗╔╗╔╔╦╗\n" +
                       "╠═╣║ ║║ ║╚═╗║╣ ╚═╗  ╠═╣╠═╣║║║ ║║\n" +
                       "╩ ╩╚═╝╚═╝╚═╝╚═╝╚═╝  ╩ ╩╩ ╩╝╚╝═╩╝" + Color.RESET);
               Splash.displayCards(houseHand[0]);
               System.out.println(Color.YELLOW + "╦ ╦╔═╗╦ ╦╦═╗  ╦ ╦╔═╗╔╗╔╔╦╗\n" +
                       "╚╦╝║ ║║ ║╠╦╝  ╠═╣╠═╣║║║ ║║\n" +
                       " ╩ ╚═╝╚═╝╩╚═  ╩ ╩╩ ╩╝╚╝═╩╝\n" + Color.RESET);
               for (int i = 0; i < 2; i++) {
                   Splash.displayCards(playerHand[i]);
               }

       }

        return 1;
    }

}
