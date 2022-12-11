public class Board extends Player {
	
    public static void main(String[] args) {
//        dealNewGame();
        System.out.println("Test");
      //Game UI
        SolitaireFrame solitaireFrame = new SolitaireFrame();
		solitaireFrame.setVisible(true);
		
    }

    // Deal a new game
//    public static void dealNewGame() {
//        // Create a deck of cards
//        Deck deck = new Deck(20,20);
//        // Create the structures
//        Table table = new Table();
//        Pillars pillars = new Pillars();
//        Hand hand = new Hand(20,220,7);
//        Waste waste = new Waste(110,150);
//        
//        // Deal the cards to the table
//        for (int i = 1; i <= 7; i++) {
//            for (int j = i; j <= 7 ; j++) {
//                //Last card of every column should be face up
//                if (j == i) {
//                    table.addCard(deck.dealCard(), j, true);
//                } else {
//                    table.addCard(deck.dealCard(), j);                    
//                }
//            }
//        }
//
//        // Deal the rest of the cards to the hand
//        while(deck.cardsLeft() > 0){
//            hand.addCard(deck.dealCard());
//        } 
//    }
    
}
