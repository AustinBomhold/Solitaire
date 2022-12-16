import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class SolitaireFrame extends JFrame {
	
	static protected SolitairePanel solitairePanel;
	public final int PANEL_WIDTH = 930, PANEL_HEIGHT= 1000;

	public SolitaireFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		solitairePanel = new SolitairePanel();
		solitairePanel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		add(solitairePanel);
		pack();
		CenteredFrame(this);
	}
	
	public void CenteredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - objFrame.getWidth()) / 2;
        int iCoordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(iCoordX, iCoordY); 
    }

}
