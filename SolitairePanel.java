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

import solitaire.Deck;
import solitaire.Foundation;
import solitaire.Tableau;
import solitaire.Waste;

public class SolitairePanel extends JPanel {
	
	private static DeckUI deck;
	private static Hands[] hands;
	private static PillarsUI[] pillars;
	private static Waste waste;
	

	public SolitairePanel() {
		super.setLayout(null);
//		deck = new DeckUI(20, 20);
		super.setOpaque(false);
		initializePiles();
//		add(deck);
//		DeckUI deck1= new DeckUI(0, 300);
//		add(deck1);
//		pillars();
//		handAndWaste();
//		table();
	}
	public void pillars() {
		DeckUI deck1;
		int a =0;
		for(int i=0; i<4;i++) {
			
			int x=410+a; 
			deck1= new DeckUI(x,20);
			add(deck1);
			
			a= a+130;
		}
	}
	public void handAndWaste() {
		int a=0;
		for(int j=0;j<2;j++) {
			int x=20+a;
			deck= new DeckUI(x, 20);
			add(deck);
			a= a+150;
		}
	}
	
	private void initializePiles() {
		deck = new DeckUI(20,20);
		add(deck);
		waste = new Waste(170, 20);
		add(waste);
//		foundationPiles = new Foundation[4];
		pillars = new PillarsUI[4];
		int b = 0;
		for(int i = 0; i < 4;i++) {
			pillars[i] = new PillarsUI(410+b,20,i+1);
			add(pillars[i]);
			b+=130;
		}
//		for(int i = 0; i < foundationPiles.length; ++i) {
//			foundationPiles[i] = new Foundation(20 + XShift * i, 20, i + 1);
//			add(foundationPiles[i]);
//		}
		int a = 0;
		hands = new Hands[7];
		for(int tableauIndex = 1; tableauIndex <= hands.length; ++tableauIndex) {
			hands[tableauIndex - 1] = new Hands(20 + a,220,tableauIndex + 1);
			add(hands[tableauIndex - 1]);
			a+=130;
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
	
	public static DeckUI getDeck() {
		return deck;
	}
}
