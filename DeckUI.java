import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.management.remote.SubjectDelegationPermission;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DeckUI extends Pillar {
	 public DeckUI(int x, int y) {
		 super(x, y);
		 super.setSize(110,150);
		 Deck deck = new Deck();
		 deck.shuffle();
		 for(Card card : deck.getDeck()) {
			 push(card);
		 }
		 
	 }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke());
		g2d.setColor(Color.WHITE);
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
		CardUI cards= new CardUI();
//		Deck deck = new Deck();
//		deck.shuffle();
//		Card card = deck.dealCard();
//		System.out.println(card);
		g.drawImage(cards.getCardBackImage(),0,0,this.getWidth(),this.getHeight(),this);
//		g.drawImage(cards.getCardImage(card.getValue(),card.getSuit()),0,0,this.getWidth(),this.getHeight(),this);
		
		 g2d.setColor(Color.red);
		 g2d.fillRect(0, 300, 1200, 700);
	}
}
