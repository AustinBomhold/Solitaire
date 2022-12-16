import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

public class Hand extends Pillar {
    private int count;
    public Hand(int x, int y, int initSize) {
    	super(x, y);
		super.setSize(110, 450);
		super.setOpaque(false);
		for (int i = 0; i < initSize; ++i) {
			push(SolitairePanel.getDeck().pop());
		}

		if (initSize > 0) {
			topCard().setFaceUp(true);
		}
        count = 0;
    }

    public void addCard(Card card) {
        push(card);
        count++;
    }

    public Card revealCard() {
        return topCard();
    }

    public Card removeCard() {
        count--;
        return pop();
    }

    public Card getCard(int index) {
        return getCardByIndex(index);
    }

    public int getCount() {
        return count;
    }
    
	public void takeCardFromWaste(Waste waste, Card card) {
		if (this.canMove(card)) {
			this.push(waste.pop());
		}
		waste = null;
	}

	public boolean canMove(Card card) {
		if (!this.isEmpty()) {
			return Integer.toString(this.topCard().getRank()).equals(Integer.toString(card.getRank()+1))
					&& !(this.topCard().getColor() == card.getColor());
		}
		return card.getValue().equals("king");
	}
	
	public boolean canMove(Pillars pillar, Card card) {
		if (pillar.canMove(card)) {
			pillar.push(this.pop());
			if (!this.isEmpty()) {
				this.topCard().setFaceUp(true);
			}
			return true;
		}
		return false;
	}
	
	public void moveFromHandToHand(Hand hand, Card card) {
		if (!this.isEmpty() || card.getValue().equals("king")) {
			if (hand.canMove(card)) {
                 Deque<Card> deque = new ArrayDeque<>();
                 while(!this.isEmpty()) {
                	 Card temp = this.pop();
                	 deque.push(temp);
                	 if(temp.equals(card)) {
                		 break;
                	 }
                 }
                 while(!deque.isEmpty()) {
                	 hand.push(deque.pop());
                 }
			}
		}
		
		if(!this.isEmpty()) {
			this.topCard().setFaceUp(true);
		}
	}
	
	public Card getClickedCard(int y) {
		int index = y / 20;
		if (index < this.cards.toArray().length && index > 0) {
			Card card = (Card) cards.toArray()[index];
			System.out.println("cc"+card.getValue());
			if (card.getFace()) {
				return card;
			}
		}
		return (Card) cards.toArray()[cards.toArray().length - 1];
	}
	
    public String toString() {
        String str = "";
        for (int i = 0; i < count; i++) {
            str += getCardByIndex(i) + " ";
        }
        return str;
    }
    
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.drawLine(0, 0, this.getWidth(), 0);
		g2d.drawLine(0, 0, 0, 150);
		g2d.drawLine(this.getWidth(), 0, this.getWidth(), 150);
		g2d.setPaint(new GradientPaint(36, 0, new Color(255, 255, 255, 160), 36, 60, new Color(0, 0, 0, 0)));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		CardUI cardUI = new CardUI();
		int cardYPos = 0;
		if (this.isEmpty()) {
			
		} 
		else {
			for (Card c : this.cards) {
				if (c.getFace()) {
					g.drawImage(cardUI.getCardImage(c.getValue(),c.getSuit()), 0, cardYPos, 110, 150, this);
					cardYPos += 40;
				} else {
					g.drawImage(cardUI.getCardBackImage(), 0, cardYPos, 110, 150, this);
					cardYPos += 40;
				}
			}
		}
	}
}

