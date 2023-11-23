import java.util.Random;

public class Card {
    String value;
    String suit;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    static public Card[] initializeDeck() {
        Card[] deck = new Card[52];
        String[] suits = {"♠", "♦", "♣", "♥"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    int counter1 = 0;
    int counter2 = 0;
        for (int i = 0; i < deck.length; i++) {
            if(counter1 == 12){
                counter1 = 0;
            }
            if(counter2 == 3){
                counter2 = 0;
            }
            counter2++;
            counter1++;
            String suit = suits[counter2];
            String value = values[counter1];
            Card card = new Card(suit, value);
            deck[i] = card;
        } return deck;
    }
    public String toString(){
        return this.suit + this.value;
    }
    static public Card getRandomCard(Card[] deck){
        Random rnd = new Random();
        return deck[rnd.nextInt(52)];
    }
}


