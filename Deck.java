import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;

public class Deck extends Pillar {
    private Card[] deck;
    private int cardsUsed;
//    public static final char[] SUITS = { 'C', 'D', 'H', 'S' };
//    public static final char[] COLORS = { 'B', 'R' };
//    public static final char[] VALUES = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
     public static final String[] SUITS = { "clubs", "diamonds", "hearts", "spades" };
     public static final char[] COLORS = { 'B', 'R' };
     public static final String[] VALUES = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };

    public Deck(int x, int y) {
    	super(x, y);
		super.setSize(110, 150);
        for (String suit : SUITS) {
        	for (String value : VALUES){
	            if (suit == "clubs" || suit == "spades"){	                
	            	push(new Card('B', suit, value, false));
	          
	            } else {
	            	push(new Card('R', suit, value, false));
	            }
        	}
        }
        Collections.shuffle(cards);
        deck = new Card[52];
        int cardCt = 0; // How many cards have been created so far.
            for (String suit : SUITS) {
                if (suit == "clubs" || suit == "spades"){
                    for (String value : VALUES){
                        deck[cardCt] = new Card('B', suit, value, false);
                        cardCt++;
                    }
                } else {
                    for (String value : VALUES){
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
    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke());
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		CardUI cards= new CardUI();
		g.drawImage(cards.getCardBackImage(),0,0,this.getWidth(),this.getHeight(),this);
		
		 g2d.setColor(Color.red);
		 g2d.fillRect(0, 300, 1200, 700);
	}

    public Card[] getDeck() {
		return deck;
	}
	
}
