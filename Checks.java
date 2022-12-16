import java.util.*;

import javax.swing.JPanel;

public abstract class Checks extends JPanel {
	
	// Check if the card can be added to a given column
    public static boolean canMoveCardToColumn(Card card, LinkedList<Card> column) {
    	if (column.isEmpty()) {
    		if (card.getValue() == "kings") {
    			return true;
    		}
    	} else {
    		Card last = column.getLast();
    		if (card.getColor() != last.getColor() && card.getRank() == last.getRank() - 1) {
    			return true;
    		}
	}
    	return false;
    }
    
    // Check if the card can be added to any column
    public static boolean canMoveCardToTable(Card card, HashMap<Integer, LinkedList<Card>> Columns) {
    	for (Integer column : Columns.keySet()) {
    		if (canMoveCardToColumn(card, Columns.get(column))){
        		return true;
    		}
    	}
    	return false;
    }
    
    // Check if the series of cards can be added to a given column
    public static boolean canMoveSeriesToColumn(LinkedList<Card> cards, LinkedList<Card> column) {
    	Card first = cards.getFirst();
    	if (canMoveCardToColumn(first, column)) {
    		return true;
    	}
        return false;
    }

	// If all suits have a King, then the game has been won
	public static boolean gameIsWon(HashMap<Character, Stack<Card>> Suits) {
		for (Character suit : Suits.keySet()) {
			if (Suits.get(suit).size() != 13) {
				return false;
			}
		}
		return true;
	}
    
    // Check if the card can be added to a pillar
    public boolean canMoveCardToPillar(Card card, Stack<Card> suit) {
    	if (suit.isEmpty()) {
    		if (card.getValue() == "ace") {
    			return true;
    		}
    	} else {
    		Card last = suit.lastElement();
    		if (card.getSuit() == last.getSuit() && card.getRank() == last.getRank() - 1) {
    			return true;
    		}
    	}
    	return false;
    }

    // Check if a series of cards (Starting from the last card at 1 to numCards) can be removed from a given column
	public static boolean canRemoveSeriesFromColumn(int numCards, LinkedList<Card> column) {
    	if (column.isEmpty()) {
    		return false;
		} else if (numCards <= 0) {
			return false;
		} else {
			Card card;
			Card prev = null;
			Iterator<Card> lastUp = column.descendingIterator();
			int numValidCards = 0;
	   		while (lastUp.hasNext()) {
				card = lastUp.next();
	   			if (prev != null) {
					if (!card.getFace() || !(card.getRank() == prev.getRank() + 1) || card.getColor() == prev.getColor()) {
						return false;
					}
				} else if (!card.getFace()) {
					return false;
				}
				prev = card;
				numValidCards++;
				if (numCards == numValidCards) {
					break;
				}
			}
			if (numCards != numValidCards) {
				return false;
			}
	    }
	    return true;
    }
}
