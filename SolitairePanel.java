import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;


public class SolitairePanel extends JPanel {
	
	private static Deck deck;
	private static Hand[] hand;
	private static PillarsUI[] pillars;
	private static Waste waste;
	
	public SolitairePanel() {
		super.setLayout(null);
		super.setOpaque(false);
		dealNewGame();
	}
	
	private void dealNewGame() {
		deck = new Deck(20,20);
		add(deck);
		waste = new Waste(170, 20);
		add(waste);
		pillars = new PillarsUI[4];
		int b = 0;
		for(int i = 0; i < 4;i++) {
			pillars[i] = new PillarsUI(410+b,20,i+1);
			add(pillars[i]);
			b+=130;
		}
		int a = 0;
		hand = new Hand[7];
		for(int index = 1; index <= hand.length; ++index) {
			hand[index - 1] = new Hand(20 + a,220,index + 1);
			add(hand[index - 1]);
			a+=130;
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(10, 132, 41));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
	
	public static Deck getDeck() {
		return deck;
	}
}
