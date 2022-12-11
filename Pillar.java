
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JPanel;


public abstract class Pillar extends JPanel {
	
	protected int x,y;
	protected Stack<Card> cards;

	public Pillar(int x, int y) {
		super.setLocation(x, y);
		cards = new Stack<>();
	}
	
	public Card pop() {
		try {
			return cards.pop();
		}catch(EmptyStackException e) {
			return null;
		}
	}
	
	public void push(Card card) {
		this.cards.push(card);
	}
	
	public Card topCard() {
		if(!this.cards.isEmpty()) {
			return this.cards.peek();
		}
		return null;
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public Card getCardByIndex(int index) {
		return this.cards.get(index);
	}
}
