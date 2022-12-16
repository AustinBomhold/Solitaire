import java.awt.Graphics;
import java.util.*;

public class Pillars extends Pillar {
    private int suit;
    //Revision 2
    // (Zander) Created an arraylist called "Suits" which contains the suit stacks
    public Pillars(int x, int y, int suit) {
    	super(x, y);
		super.setSize(110, 150);
		this.suit = suit;
    }

    //Revision 2
    // Add a card to the pillar

//    public void addCard(Card card, String suit) {
//        if(canMoveCardToPillar(card, Suits.get(suit))){
//            Suits.get(suit).add(card);
//        }
//    }
//
//    //Revision 2
//    // Remove the top card of the pillar
//    public Card removeCard(String suit) {
//        try {
//            return Suits.get(suit).pop();
//        } catch (Exception IllegalArgumentException) {
//            throw new IllegalArgumentException("Must be a valid suit (C, D, H, S) and a char type not a string");
//        }
//    }
//
//    //Revision 2
//    //Determin if a pillar is empty
//    public boolean isEmpty(String suit){
//        try {
//            return Suits.get(suit).isEmpty();
//        } catch (Exception IllegalArgumentException) {
//            throw new IllegalArgumentException("Must be a valid suit (C, D, H, S) and a char type not a string");
//        }
//    }
//
//    //Get Suits
//    public HashMap<String,Stack<Card>> getSuits() {
//        return Suits;        
//    }
//
//    // Revision 2
//    // Peak at all the top cards of the pillars
//    public HashMap<Character, Card> peakTopCardsAsHashMap() {
//        HashMap<Character, Card> topCards = new HashMap<Character, Card>();
//        try{
//            topCards.put('C', Clubs.peek());
//        } catch (EmptyStackException e) {
//            topCards.put('C', null);
//        }
//        try{
//            topCards.put('D', Diamonds.peek());
//        } catch (EmptyStackException e) {
//            topCards.put('D', null);
//        }
//        try{
//            topCards.put('H', Hearts.peek());
//        } catch (EmptyStackException e) {
//            topCards.put('H', null);
//        }
//        try{
//            topCards.put('S', Spades.peek());
//        } catch (EmptyStackException e) {
//            topCards.put('S', null);
//        }
//        return topCards;
//    }
    public boolean canMove(Card card) {
		if(!this.isEmpty()) {
			return Integer.toString(this.topCard().getRank()).equals(Integer.toString(card.getRank()+1))
					&& this.topCard().getSuit().equals(card.getSuit());
		}
		return card.getValue() == "ace" && checkSuit(card.getSuit());
	}
    
    public void takeCardFromWaste(Waste waste, Card card) {
		if(canMove(card)) {
			this.push(waste.pop());
			waste = null;
		}
	}
	
	public void moveCardToHand(Hand hand, Card card) {
		if(hand.canMove(card)) {
			hand.push(this.pop());
		}
	}
    
    private boolean checkSuit(String suit) {
		if (suit.equals("clubs")) {
			return this.suit == 1;
		} else if (suit.equals("spades")) {
			return this.suit == 4;
		} else if (suit.equals("hearts")) {
			return this.suit == 2;
		} else if (suit.equals("diamonds")) {
			return this.suit == 3;
		}
		return false;
	}
    
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		CardUI cardUI = new CardUI();
		if(this.isEmpty()) {
			g.drawImage(cardUI.getPillarImage(suit), 0, 0,
					this.getWidth(), this.getHeight(), this);
		}else {
			g.drawImage(cardUI.getCardImage(this.topCard().getValue(), this.topCard().getSuit()),
					0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

}
