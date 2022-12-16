import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.event.MouseInputAdapter;

public class GameAction extends MouseInputAdapter {
	
	private Deck deck = SolitairePanel.getDeck();
	private Waste waste = null;
	private Hand hand = null;
	private Pillars pillars = null;
	private Card card = null;

	@Override
	public void mousePressed(MouseEvent e) {
		Component mousePressedComponent = e.getComponent().getComponentAt(e.getPoint());
		if(mousePressedComponent instanceof Pillars) {
			pillars = (Pillars) mousePressedComponent;
			hand = null;
			waste = null;
			card = pillars.topCard();
		}else if(mousePressedComponent instanceof Hand) {
			hand = (Hand) mousePressedComponent;
			waste = null;
			card = hand.getClickedCard(e.getY() - 250);
			for(Pillars Pillars : SolitairePanel.getPillarsArray()) {
				if(hand.canMove(Pillars, card)) {
					hand = null;
					break;
				}
			}
		}else if(mousePressedComponent instanceof Deck) {
			hand = null;
			if(!deck.isEmpty()) {
				Waste waste = SolitairePanel.getWaste();
				waste.push(deck.pop());
				waste.topCard().setFaceUp(true);
			}
		}else if(mousePressedComponent instanceof Waste) {
			hand = null;
			waste = SolitairePanel.getWaste();
			card = waste.topCard();
			if(card != null) {
				for(Pillars pillars : SolitairePanel.getPillarsArray()) {
					pillars.takeCardFromWaste(waste, card);
				}
			}
		}
		
		e.getComponent().repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(card != null) {
			Component mouseReleasedComponent = e.getComponent().getComponentAt(e.getPoint());
			if(mouseReleasedComponent instanceof Hand) {
				if(waste != null) {
					Hand hand = (Hand) mouseReleasedComponent;
					if(!waste.isEmpty()) {
						hand.takeCardFromWaste(waste, card);
					}
					waste.repaint();
				}else if(hand != null) {
					Hand thisHand = hand;
					Hand thatHand = (Hand) mouseReleasedComponent;
					thisHand.moveFromHandToHand(thatHand, card);
					thisHand.repaint();
				}else if(pillars != null) { 
					Pillars pilar = pillars;
					Hand hand = (Hand) mouseReleasedComponent;
					pilar.moveCardToHand(hand, card);
					//Update pillar and hand UI 
					pilar.repaint();
					hand.repaint();
				}
			}
		}
		
		e.getComponent().repaint();
		card = null;
		pillars = null;
		hand = null;
		waste = null;
	}
	
	

}

