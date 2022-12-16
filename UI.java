import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
 
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
 
public class UI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
 
              private JMenuBar menuBar;
 
              // Maps all the UI text
              Map<String, String> displayText;
              JPanel table;
              JPanel columns;
              JPanel topColumns;
              JLayeredPane lp;
              SolitairePanel game;
 
              // Elements to use with drags
              PillarsUI pillars;
              Point mouseOffset;
 
              // UI class constructor
 
              public UI(SolitairePanel game) {
                           this.game = game;
 
                           createTextMap();
 
                           // Window settings
                           setTitle("Solitaire");
                           setSize(930, 1000);
 
                           try {
                                         setContentPane((new JPanelWithBackground(".name of backround image file.")));
                           } catch (IOException e) {
                                         e.printStackTrace();
                           }
 
                           setLayout(new BorderLayout());
 
                           table = new JPanel();
                           table.setOpaque(false);
                           table.setLayout(new BoxLayout(table, BoxLayout.PAGE_AXIS));
 
                           // Center the window
                           setLocationRelativeTo(null);
 
                           // Window close event
                           setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
                           // Add GUI elements
                           createTopMenu();
 
                           // Flow layout to display multiple columns on the same row
                           FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
                           flow.setAlignOnBaseline(true);
 
                           // Add the columns panel
                           columns = new JPanel();
                           columns.setOpaque(false);
                           columns.setLayout(flow);
                           columns.setMinimumSize(new Dimension(200, 900));
 
                           // Add the top columns panel
                           FlowLayout topFlow = new FlowLayout(FlowLayout.LEFT);
                           topFlow.setAlignOnBaseline(true);
 
                           topColumns = new JPanel();
                           topColumns.setOpaque(false);
                           topColumns.setLayout(topFlow);
 
                           table.add(topColumns);
                           table.add(columns);
 
                           // layers.add(dragLayer, JLayeredPane.DRAG_LAYER);
                           add(table);
 
                           // Display the window
                           lp = getLayeredPane();
                           setVisible(true);
 
                           // Auxiliary elements
                           mouseOffset = new Point(0, 0);
 
                           initialize();
              }
 
              /**
              * Add cards from the game to the UI
              */
              private void initialize() {
                           topColumns.removeAll();
                           columns.removeAll();
 
                           // Add a listener for each card
                           for (Card c : SolitairePanel.deck.cards) {
                                         c.addMouseListener(this);
                                         c.addMouseMotionListener(this);
                           }
 
                           game.dealNewGame();
                           for (PillarsUI p : SolitairePanel.pillars) {
                                         columns.add(p);
                           }
 
                           topColumns.add((SolitairePanel.pillars)); //draw pile
                           topColumns.add((SolitairePanel.pillars));  //get piles
 
                           for (PillarsUI p : SolitairePanel.pillars) { //final piles
                                         topColumns.add(p);
                           }
 
                           validate();
              }
 
              /**
              * Resets the whole game
              */
              public void reset() {
                           game.dealNewGame();
                           initialize();
                           repaint();
              }
 
              /**
              * Creates the displayText map
              */
              private void createTextMap() {
                           displayText = new HashMap<String, String>();
                           displayText.put("New", "New");
                           displayText.put("Exit", "Exit");
              }
 
              /**
              * Create the top menu bar
              */
              private void createTopMenu() {
                           menuBar = new JMenuBar();
 
                           menuOption[] fileOptions = new menuOption[] { new menuOption(displayText.get("New"), KeyEvent.VK_N),
                                                       new menuOption(displayText.get("Exit"), KeyEvent.VK_X) };
 
                           for (menuOption option : fileOptions) {
                                         JMenuItem opt = new JMenuItem(option.name);
                                         if (option.shorcut != 0)
                                                       opt.setMnemonic(option.shorcut);
 
                                         opt.addActionListener(this);
                           }
 
                           setJMenuBar(menuBar);
              }
 
              /**
              * Class which stores information about a single menu option
              */
              class menuOption {
                           public String name;
                           public Integer shorcut = 0;
 
                           public menuOption(String name, Integer shorcut) {
                                         this.name = name;
                                         this.shorcut = shorcut;
                           }
              }
 
              /**
              * Function to handle most of the events performed on the UI
              */
              public void actionPerformed(ActionEvent m) {
 
                           // Handle all menu interactions
                           if (m.getSource() instanceof JMenuItem)
                                         handleMenuInteraction(m);
 
              }
 
              /**
              * Handles the activation of any of the menu bar buttons
              * 
               * @param {ActionEvent} m
              */
              private void handleMenuInteraction(ActionEvent m) {
                           JMenuItem item = (JMenuItem) m.getSource();
 
                           if (item.getText().equals(displayText.get("Exit"))) {
                                         this.dispose();
                                         return;
                           }
                           if (item.getText().equals(displayText.get("New"))) {
                                         reset();
                                         return;
                           }
              }
 
              @Override
              public void mouseDragged(MouseEvent m) {
                           if (pillars != null) {
 
                                         Point pos = getLocationOnScreen();
                                         pos.x = m.getLocationOnScreen().x - pos.x - mouseOffset.x;
                                         pos.y = m.getLocationOnScreen().y - pos.y - mouseOffset.y;
 
                                         pillars.setLocation(pos);
                           }
                           repaint();
              }
 
              @Override
              public void mouseMoved(MouseEvent m) {
 
              }
 
              @Override
              public void mouseClicked(MouseEvent m) {
                           if (m.getComponent() instanceof Card) {
                                         Card c = (Card) m.getComponent();
                                         pile p = (pile) c.getParent();
 
                                         switch (p.type) {
                                         case Draw:
                                                       game.drawCard();
                                                       break;
                                         case Normal:
                                                       game.clickPile(p);
                                                       break;
                                         case Get:
                                                       game.turnGetPile();
                                                       break;
                                         }
                                         repaint();
                           }
              }
 
              @Override
              public void mousePressed(MouseEvent m) {
                           if (m.getComponent() instanceof Card) {
                                         Card c = (Card) m.getComponent();
 
                                         // Do nothing if card is reversed
                                         if (c.isReversed)
                                                       return;
 
                                         pile p = (pile) c.getParent();
 
                                         if (p.cards.isEmpty() || p.type == CardPileType.Final)
                                                       return;
 
                                         pillars = p.split(c);
 
                                         lp.add(pillars, JLayeredPane.DRAG_LAYER);
 
                                         Point pos = getLocationOnScreen();
                                         mouseOffset = m.getPoint();
                                         pos.x = m.getLocationOnScreen().x - pos.x - mouseOffset.x;
                                         pos.y = m.getLocationOnScreen().y - pos.y - mouseOffset.y;
 
                                         pillar.setLocation(pos);
 
                                         repaint();
                           }
              }
 
              @Override
              public void mouseReleased(MouseEvent m) {
                           if (pillars != null) {
 
                                         Point mousePos = m.getLocationOnScreen();
                                         boolean match = false;
 
                                         // Check if pile can merge with the pile it is dropped on
                                         ArrayList<PillarsUI> droppable = new ArrayList<PillarsUI>(pillars[i]);
                                         droppable.addAll(game.finalPiles);
 
                                         for (PillarsUI p : droppable) {
                                                       Point pilePos = p.getLocationOnScreen();
                                                       Rectangle r = p.getBounds();
                                                       r.x = pilePos.x;
                                                       r.y = pilePos.y;
 
                                                       if (r.contains(mousePos) && p.acceptsPile(pillars)) {
                                                                    p.merge(pillars);
                                                                    match = true;
                                                                    break;
                                                       }
                                         }
 
                                         // Snap back if no merge is found
                                         if (!match)
                                                       pillars.parent.merge(pillars);
 
                                         lp.remove(pillars);
                                         pillars = null;
 
                                         repaint();
 
                                         if (game.checkWin()) {
                                                       JOptionPane.showMessageDialog(this, "You won! Congrats!");
                                                       reset();
                                         }
                           }
              }
 
              public void mouseEntered(MouseEvent arg0) {
              }
 
              public void mouseExited(MouseEvent arg0) {
              }
 
              public class JPanelWithBackground extends JPanel {
                           private Image backgroundImage;
 
                           // Some code to initialize the background image.
                           // Here, we use the constructor to load the image. This
                           // can vary depending on the use case of the panel.
                           public JPanelWithBackground(String fileName) throws IOException {
                                         URL urlToImage = this.getClass().getResource(fileName);
                                         backgroundImage = ImageIO.read(urlToImage);
                           }
 
                           public void paintComponent(Graphics g) {
                                         super.paintComponent(g);
 
                                         // Draw the background image.
                                         g.drawImage(backgroundImage, 0, 0, this);
                           }
              }
}
 
