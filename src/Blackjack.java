import java.util.Scanner;

public class Blackjack {


    static public int play(int playerMoney) throws Exception {
        try {
            Card[] deck = Card.initializeDeck();
            boolean quit = false;
            int bet = 0;
            Scanner scanner = new Scanner(System.in);
            Splash.displayBlackjackLogo();
            Thread.sleep(3500);
            while (!quit) {
                System.out.println(Color.CLEAR_CONSOLE);
                System.out.println("Place your bets: " + "\t\t\t\t\t\t\t\t" + "Available balance: " + playerMoney + " V bucks.\n0) quit");
                int playerScore = 0;
                int houseScore = 0;
                boolean handOver = false;
                scanner.reset();
                bet = scanner.nextInt();
                if (bet == 0) {
                    return playerMoney;
                }
                playerMoney -= bet;
                Card[] playerHand = new Card[6];
                Card[] houseHand = new Card[13];
                int acesMinusPlayer = 0;
                int acesMinusHouse = 0;
                int acesPlayer = 0;
                int acesHouse = 0;
                playerHand[0] =  Card.getRandomCard(deck);
                houseHand[0] = Card.getRandomCard(deck);
                playerHand[1] = Card.getRandomCard(deck);
                for (int i = 0; i < 2; i++) {
                    playerScore += countValue(playerHand[i], playerScore);
                }
                houseScore = countValue(houseHand[0], houseScore);
                System.out.println(Color.CLEAR_CONSOLE);
                System.out.println(Color.BLUE + "╦ ╦╔═╗╦ ╦╔═╗╔═╗╔═╗  ╦ ╦╔═╗╔╗╔╔╦╗\n" +
                        "╠═╣║ ║║ ║╚═╗║╣ ╚═╗  ╠═╣╠═╣║║║ ║║\n" +
                        "╩ ╩╚═╝╚═╝╚═╝╚═╝╚═╝  ╩ ╩╩ ╩╝╚╝═╩╝" + Color.RESET);
                Splash.displayCards(houseHand[0]);
                System.out.println("House has: " + houseScore);
                System.out.println(Color.YELLOW + "╦ ╦╔═╗╦ ╦╦═╗  ╦ ╦╔═╗╔╗╔╔╦╗\n" +
                        "╚╦╝║ ║║ ║╠╦╝  ╠═╣╠═╣║║║ ║║\n" +
                        " ╩ ╚═╝╚═╝╩╚═  ╩ ╩╩ ╩╝╚╝═╩╝\n" + Color.RESET);
                for (int i = 0; i < 2; i++) {
                    Splash.displayCards(playerHand[i]);
                }
                System.out.println("Player has: " + playerScore);
                while (!handOver) {
                    boolean double2 = false;
                    boolean hit = false;
                    boolean bust = false;
                    scanner.reset();
                    boolean playerMoveDone = false;
                    while (!playerMoveDone) {
                        if (playerHand[0].value.equals("A") && (playerHand[1].value.equals("K") || playerHand[1].value.equals("Q") || playerHand[1].value.equals("J") || playerHand[1].value.equals("10"))) {
                            int counter = 0;
                            for (int i = 0; i < playerHand.length; i++) {
                                if (playerHand[i] != null) {
                                    counter++;
                                }
                            }
                            for (int i = 0; i < counter; i++) {
                                Splash.displayCards(playerHand[i]);
                            }
                            Thread.sleep(1000);
                            Splash.displayBlackjack();
                            System.out.println(bet * 2.5 + " V Bucks has been awarded to your balance");
                            Thread.sleep(1500);
                            handOver = true;
                            bust = true;
                            playerMoney += bet * 2.5;
                            break;
                        } else if (playerHand[1].value.equals("A") && (playerHand[0].value.equals("K") || playerHand[0].value.equals("Q") || playerHand[0].value.equals("J") || playerHand[0].value.equals("10"))) {
                            for (int i = 0; i < 2; i++) {
                                Splash.displayCards(playerHand[i]);
                            }
                            Thread.sleep(1000);
                            Splash.displayBlackjack();
                            System.out.println(bet * 2.5 + " V Bucks has been awarded to your balance");
                            handOver = true;
                            playerMoney += bet * 2.5;
                            break;
                        }
                        System.out.println("h)hit\t\t\t" + ((hit) ? "" : "d)double") + "\t\t\ts)stand\t\t\t");
                        switch (scanner.next()) {
                            case "h":
                                System.out.println(Color.CLEAR_CONSOLE);
                                int counter = 0;
                                for (int i = 0; i < playerHand.length; i++) {
                                    if (playerHand[i] != null) {
                                        counter++;
                                    }
                                    hit = true;
                                }
                                playerHand[counter] = Card.getRandomCard(deck);
                                acesPlayer += (playerHand[counter].equals("A") ? 1 : 0);
                                playerScore += countValue(playerHand[counter], playerScore);
                                Splash.displayCards(houseHand[0]);
                                System.out.println("House has: " + houseScore);
                                for (int i = 0; i < counter + 1; i++) {
                                    Splash.displayCards(playerHand[i]);
                                }
                                System.out.println("Player has: " + playerScore);
                                break;
                            case "d":
                                double2 = true;
                                playerMoney -= bet;
                                if (!hit) {
                                    System.out.println(Color.CLEAR_CONSOLE);
                                    playerHand[2] = Card.getRandomCard(deck);
                                    playerScore += countValue(playerHand[2], playerScore);
                                    Splash.displayCards(houseHand[0]);
                                    System.out.println("House has: " + houseScore);
                                    for (int i = 0; i < 2; i++) {
                                        Splash.displayCards(playerHand[i]);
                                    }
                                    System.out.println("Player has: " + playerScore);
                                    playerMoveDone = true;
                                }
                                System.out.println(Color.CLEAR_CONSOLE);
                                break;
                            case "s":
                                System.out.println(Color.CLEAR_CONSOLE);
                                playerMoveDone = true;
                                break;
                            default:
                                break;
                        }
                        if (playerScore > 21) {
                            if(acesMinusPlayer != 0) {
                                playerScore = Player.checkAces(acesPlayer, playerScore);
                                acesMinusPlayer-= acesPlayer;
                            }
                            Splash.displayBust();
                            Thread.sleep(5000);
                            bust = true;
                            handOver = true;
                            playerMoveDone = true;
                        }
                    }
                    while (houseScore < 17 && !bust) {
                        int counter = 0;
                        for (int i = 0; i < houseHand.length; i++) {
                            counter += (houseHand[i] != null) ? 1 : 0;
                        }
                        houseHand[counter] = Card.getRandomCard(deck);
                        houseScore += countValue(houseHand[counter], houseScore);
                        for (int i = 0; i < counter + 1; i++) {
                            Splash.displayCards(houseHand[i]);
                            Thread.sleep(1500);
                        }
                        if(acesMinusHouse != 0) {
                            houseScore = Player.checkAces(acesHouse, houseScore);
                            acesMinusHouse -= acesHouse;
                        }
                        System.out.println("House has: " + houseScore);

                        if (houseScore > 21 && !bust) {
                            bust = true;
                            Splash.displayWinner();
                            System.out.println(bet * ((double2)?4:2) + " V bucks added to your balance");
                            Thread.sleep(5000);
                            playerMoney += bet * ((double2)?4:2);
                            handOver = true;
                            break;
                        }
                        int counter2 = 0;
                        for (int i = 0; i < playerHand.length; i++) {
                            counter2 += (playerHand[i] != null) ? 1 : 0;
                        }
                        for (int i = 0; i < counter2; i++) {
                            Splash.displayCards(playerHand[i]);
                            Thread.sleep(400);
                        }
                        System.out.println("Player has: " + playerScore);
                        Thread.sleep(1500);
                        System.out.println(Color.CLEAR_CONSOLE);
                    }
                    if (playerScore > houseScore && !bust) {
                        Splash.displayWinner();
                        System.out.println(bet * 2 + " V bucks added to your balance");
                        playerMoney += bet * ((double2)?4:2);
                        handOver = true;
                        Thread.sleep(5000);
                    } else if (houseScore == playerScore && !bust) {
                        Splash.displayPush();
                        handOver = true;
                        playerMoney += bet;
                        Thread.sleep(5000);
                    } else if (houseScore > playerScore && !bust) {
                        Splash.displayLost();
                        handOver = true;
                        Thread.sleep(5000);
                    }

                }
            }

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return playerMoney;
    }


    static public int countValue(Card card, int values) {
        switch (card.value) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "Q":
                return 10;
            case "K":
                return 10;
            case "J":
                return 10;
            case "A":
                return 11;
        }
        return 0;
    }
}
