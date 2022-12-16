import java.awt.*;
import java.util.*;


public class PillarsUI extends Pillar {

	private int suit;

	public PillarsUI(int x, int y, int suit) {
		super(x, y);
		super.setSize(110, 150);
		this.suit = suit;
	}


	@Override
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
