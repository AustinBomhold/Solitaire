import java.util.*;

/*
 * TODO:
 * 
 * 1. Refactor with new data structures
 * 2. Change to a hashmap of columns (1-7) and a linked list of cards
 * 3. Get rid of switch cases with parseInt
 * 4. 
 */

public class Table extends Checks{
    private LinkedList<Card> Column1;
    private LinkedList<Card> Column2;
    private LinkedList<Card> Column3;
    private LinkedList<Card> Column4;
    private LinkedList<Card> Column5;
    private LinkedList<Card> Column6;
    private LinkedList<Card> Column7;
    private HashMap<Integer, LinkedList<Card>> Columns;
    public String getNumCards;

    //Revision 2
    // Constructor
    // (Zander) Created an arraylist called "Columns" which contains the columns as
    // (Austin) Good idea, but used a HashMap instead
    public Table() {
        Column1 = new LinkedList<Card>();
        Column2 = new LinkedList<Card>();
        Column3 = new LinkedList<Card>();
        Column4 = new LinkedList<Card>();
        Column5 = new LinkedList<Card>();
        Column6 = new LinkedList<Card>();
        Column7 = new LinkedList<Card>();
        Columns = new HashMap<>();
        Columns.put(1, Column1);
        Columns.put(2, Column2);
        Columns.put(3, Column5);
        Columns.put(4, Column6);
        Columns.put(5, Column4);
        Columns.put(6, Column7);
        Columns.put(7, Column3);
    }

    //Revision 2
    // Add a card to the table
    public void addCard(Card card, int column) {
            Columns.get(column).add(card);
    }

    //Revision 2
    // Add a card to the table with a face up (true) or face down (false) boolean
    public void addCard(Card card, int column, boolean faceUp) {
        card.setFaceUp(faceUp);
        addCard(card, column);
    }

    //Revision 2
    // Add a series of cards to the table
    public void addCards(LinkedList<Card> cards, int column) {
        if (canMoveSeriesToColumn(cards, Columns.get(column))) {
            Columns.get(column).addAll(cards);
        } else {
            throw new IllegalArgumentException("Cards cannot be added this column");
        }
    }

    //Revision 2
    // Remove a card from the table
    public Card removeCard(int column) {
        return Columns.get(column).getLast();
    }

    //Revision 2
    // Remove a series of cards from the table
    public LinkedList<Card> removeCard(int column, int numCards) {
        if (canRemoveSeriesFromColumn(numCards, Columns.get(column))) {
            LinkedList<Card> holdingCards = new LinkedList<Card>();
            for (int i = 0; i < numCards; i++) {
                    holdingCards.add(Columns.get(column).removeLast());
            }
            return holdingCards;
        } else {
            throw new IllegalArgumentException("Cards cannot be removed from table");
        }
    }

    //Revision 2
    // (Zander) Get the last card of a given column
    public Card lastCard(int columnNumber) {
        try {
            return Columns.get(columnNumber).getLast();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not get the last card from this column!");
        }
    }

    //Revision 2
    // Print a column of the table
//    public String columntoString(int column) {
//        String temp = "";
//        for (Card card : Columns.get(column)) {
//            temp += card.toShortString() + "\n";
//        }
//        return temp;
//    }

    //Revision 2
    // Get the number of cards in a column
    public int getNumCards(int column) {
        try{
            return Columns.get(column).size();
        } catch (Exception illegealException) {
                throw new IllegalArgumentException("Column must be 1-7");
        }
    }

    //Revision 2
    // Return a series of cards from a column
    public LinkedList<Card> getCards(int column, int numCards) {
        if (canRemoveSeriesFromColumn(numCards, Columns.get(column))) {
            LinkedList<Card> cards = new LinkedList<Card>();
            for (int i = 1; i <= numCards; i++) {
                cards.add(Columns.get(column).removeLast());
            }
            return cards;
        } else {
            throw new IllegalArgumentException("Cards cannot be removed from table");
        }
    }

    //Get Columns
    public HashMap<Integer, LinkedList<Card>> getColumns() {
        return Columns;        
    }
}