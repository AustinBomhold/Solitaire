import java.util.*;

/*
 * TODO:
 * 
 * 1. canRemoveSeriesFromColumn()
 * 
 */

public abstract class Checks {
	
	//Revision 2
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
    
	//Revision 2
    // Check if the card can be added to any column
    public static boolean canMoveCardToTable(Card card, HashMap<Integer, LinkedList<Card>> Columns) {
    	for (Integer column : Columns.keySet()) {
    		if (canMoveCardToColumn(card, Columns.get(column))){
        		return true;
    		}
    	}
    	return false;
    }
    
	//Revision 2
    // Check if the series of cards can be added to a given column
    public static boolean canMoveSeriesToColumn(LinkedList<Card> cards, LinkedList<Card> column) {
    	Card first = cards.getFirst();
    	if (canMoveCardToColumn(first, column)) {
    		return true;
    	}
        return false;
    }

	//Revision 2
	// If all suits have a King, then the game has been won
	public static boolean gameIsWon(HashMap<Character, Stack<Card>> Suits) {
		for (Character suit : Suits.keySet()) {
			if (Suits.get(suit).size() != 13) {
				return false;
			}
		}
		return true;
	}
    
	//Revision 2
    // Check if the card can be added to a pillar
    public static boolean canMoveCardToPillar(Card card, Stack<Card> suit) {
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

	////////////////////////////////////
    // Check if a series of cards (Starting from the last card at 1 to numCards) can be removed from a given column
	// It should check if numCards is in bounds and if the series of cards is in valid order (Alternating different colors and correct rank order)
    // Use card.getRank() to compare order
    // Facedown cards cannot be a part of a series (refer to the relevant methods in Card.java)
    public static boolean canRemoveSeriesFromColumn(Integer numCards, LinkedList<Card> column) {
		return true;
		// Card first = cards.getFirst();
    	// if (canRemoveCardFromColumn(first, column)) {
    	// 	return true;
    	// }
        // return false;
    }
    ///////////////////////////////////

}
	
	
	
	
	
	
	
	
	
	
	//Delete?    
    // // Check if the series of cards can be added to any column
    // public static boolean canMoveSeriesToTable(LinkedList<Card> cards, ArrayList<LinkedList<Card>> Columns) {
    // 	Card first = cards.getFirst();
    // 	if (canMoveCardToTable(first, Columns)) {
    // 		return true;
    // 	}
    // 	return false;
    // }
    
	//Delete?
    // // Check if the card can be removed from a given column
    // public static boolean canRemoveCardFromColumn(Card card, LinkedList<Card> column) {
    // 	if (!column.isEmpty()) {
    // 		Card last = column.getLast();
    // 		if (card == last) {
    // 			return true;
    // 		}
    // 	}
    //     return false;
    // }
    
    // // Check if the card can be removed from any column
    // public static boolean canRemoveCardFromTable(Card card, ArrayList<LinkedList<Card>> Columns) {
    // 	for (LinkedList<Card> column : Columns) {
    // 		if (canRemoveCardFromColumn(card, column)) {
    // 			return true;
    // 		}
    // 	}
    // 	return false;
    // }
    
	//Delete?
    // // Check if the series of cards can be removed from any column
    // public static boolean canRemoveSeriesFromTable(LinkedList<Card> cards, ArrayList<LinkedList<Card>> Columns) {
    // 	Card first = cards.getFirst();
    // 	if (canRemoveCardFromTable(first, Columns)) {
    // 		return true;
    // 	}
    //     return false;
    // }

	//Delete?
	// //Revision 2
    // // Check if the card can be moved to any of the pillars
    // public static boolean canMoveCardToPillars(Card card, HashMap<Character, Stack<Card>> Suits) {
    // 	for (Character suit : Suits.keySet()) {
    // 		if (canMoveCardToPillar(card, Suits.get(suit))) {
    // 			return true;
    // 		}
    // 	}
    // 	return false;
    // }

	//Delete?
    // // Check if there are any valid moves remaining
	// // This method only checks if the player can put down or pick up a given card or series. It needs to check if the player can make any more moves.
	// // 
	// // 1. Are there cards in the hand that they could draw? (maybe do or don't include the waste pile, because they'll always be able to draw from the waste pile)
	// // 2. Are there cards cards on the table that they could pick up?
    // public static boolean validMovesLeft(Card card, ArrayList<LinkedList<Card>> Columns, LinkedList<Card> cards, ArrayList<Stack<Card>> Suits) {
    // 	if (!canMoveCardToTable(card, Columns) && !canMoveSeriesToTable(cards, Columns) && !canRemoveCardFromTable(card, Columns) && !canRemoveSeriesFromTable(cards, Columns) && !canMoveCardToPillars(card, Suits)) {
    // 		return false;
    // 	}
    //     return true;
    // }

