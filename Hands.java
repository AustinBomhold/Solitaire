import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Hands extends Pillar {
	
	public Hands(int x, int y, int initSize) {
		super(x, y);
		super.setSize(110, 500);
		super.setOpaque(false);
		for (int i = 0; i < initSize; ++i) {
			push(SolitairePanel.getDeck().pop());
		}

		if (initSize > 0) {
			topCard().setFaceUp(true);
		}
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.drawLine(0, 0, this.getWidth(), 0);
		g2d.drawLine(0, 0, 0, 96);
		g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, 96);

		g2d.setPaint(new GradientPaint(36, 0, new Color(255, 255, 255, 160), 36, 60, new Color(0, 0, 0, 0)));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		CardUI cardUI = new CardUI();
		int cardYPos = 0;
		if (this.isEmpty()) {
		} else {
			for (Card c : this.cards) {
				if (c.getFaceUp()) {
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
