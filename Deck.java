public class Deck {
    private Card[] deck;
    private int cardsUsed;
    public static final char[] SUITS = { 'C', 'D', 'H', 'S' };
    public static final char[] COLORS = { 'B', 'R' };
    public static final char[] VALUES = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

    public Deck() {
        deck = new Card[52];
        int cardCt = 0; // How many cards have been created so far.
            for (char suit : SUITS) {
                if (suit == 'C' || suit == 'S'){
                    for (char value : VALUES){
                        deck[cardCt] = new Card('B', suit, value, false);
                        cardCt++;
                    }
                } else {
                    for (char value : VALUES){
                        deck[cardCt] = new Card('R', suit, value, false);
                        cardCt++;
                    }
                }
            }
        cardsUsed = 0;
    }

    public void shuffle() {
        for (int i = deck.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    public int cardsLeft() {
        return deck.length - cardsUsed;
    }

    public Card dealCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        return deck[cardsUsed - 1];
    }
}
