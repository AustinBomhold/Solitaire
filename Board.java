public class Board extends Player {



    public static void main(String[] args) {
        dealNewGame();
        
    }

    // Deal a new game
    public static void dealNewGame() {
        // Create a deck of cards
        Deck deck = new Deck();
        // Shuffle the deck
        deck.shuffle();
        // Create the structures
        Table table = new Table();
        Pillars pillars = new Pillars();
        Hand hand = new Hand();
        Waste waste = new Waste();

        // Deal the cards to the table
        for (int i = 1; i <= 7; i++) {
            for (int j = i; j <= 7 ; j++) {
                //Last card of every column should be face up
                if (j == i) {
                    table.addCard(deck.dealCard(), j, true);
                } else {
                    table.addCard(deck.dealCard(), j);                    
                }
            }
        }

        // Deal the rest of the cards to the hand
        while(deck.cardsLeft() > 0){
            hand.addCard(deck.dealCard());
        } 
    }
    
}
