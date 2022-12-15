public class workspace {
    public static void main(String[] args) {
        // Create a deck of cards
        Deck deck = new Deck();
        // Shuffle the deck
        deck.shuffle();
        // Deal the cards
        Hand hand = new Hand(20,220,7);
        for (int i = 0; i < 5; i++) {
            hand.addCard(deck.dealCard());
        }
        // Print the hand
        System.out.println(hand);
        String x = hand.revealCard().getValue();

        // Print the value of the revealed card

        System.out.println(x);
        
    }
}
