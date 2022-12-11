import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import images.*;
import solitaire.Card;
public class CardUI {

	public Image getCardBackImage() {
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("images/PNG-cards-1.3/back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon backIcon = new ImageIcon(wPic);
		Image back = backIcon.getImage();
		return back;
	}
	
	public Image getCardImage(String value, String suit) {
		BufferedImage wPic = null;
		String fileName = ""+value+"_of_" + suit + ".png";
		try {
			wPic = ImageIO.read(this.getClass().getResource("images/PNG-cards-1.3/"+ fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon frontIcon = new ImageIcon(wPic);
		Image front = frontIcon.getImage();
		return front;
	}
	
	public Image getPillarImage(int suit) {
		BufferedImage wPic = null;
		String fileName = ""+ suit + ".png";
		try {
			wPic = ImageIO.read(this.getClass().getResource("images/PNG-cards-1.3/"+ fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon frontIcon = new ImageIcon(wPic);
		Image pillar = frontIcon.getImage();
		return pillar;
	}
	
	public Image getCardFrame() {
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("images/PNG-cards-1.3/card_frame.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon frontIcon = new ImageIcon(wPic);
		Image pillar = frontIcon.getImage();
		return pillar;
	}
	
}
