import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Stack;

public class Waste extends Pillar {
    private int count;

    public Waste(int x, int y) {
    	super(x, y);
		super.setSize(110,150);
        count = 0;
    }

    // Add a card to the waste
    public void addCard(Card card) {
        cards.push(card);
        count++;
    }

    // Remove look at the top card of the waste
    public Card revealCard() {
        return cards.peek();
    }

    // Remove the top card of the waste
    public Card removeCard() {
        count--;
        return cards.pop();
    }

    // Empty the waste
    public LinkedList<Card> empty() {
        LinkedList<Card> pile = new LinkedList<Card>();
        while (!cards.isEmpty()) {
            pile.add(cards.pop());
        }
        count = 0;
        return pile;
    }

    // Get the size of the waste
    public int getCount() {
        return count;
    }

    // Check if the waste is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		CardUI cardUI = new CardUI();
		if(this.isEmpty()) {
			g.drawImage(cardUI.getCardFrame(), 0, 0, 110, this.getHeight(), this);
		}else {
			g.drawImage(cardUI.getCardImage(this.topCard().getValue(), this.topCard().getSuit()), 0, 0, 110, this.getHeight(), this);
		}
		
	}
    
}
