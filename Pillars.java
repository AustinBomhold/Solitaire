import java.util.*;

public class Pillars extends Checks {

    private Stack<Card> Clubs;
    private Stack<Card> Diamonds;
    private Stack<Card> Hearts;
    private Stack<Card> Spades;

    public Pillars() {
        Clubs = new Stack<Card>();
        Diamonds = new Stack<Card>();
        Hearts = new Stack<Card>();
        Spades = new Stack<Card>();
    }

    // Add a card to the pillar
    public void addCard(Card card, char suit) {
        if(canMoveCardToPillar(card, suit)){
            if(suit == 'C'){
                Clubs.push(card);
            }
            else if(suit == 'D'){
                Diamonds.push(card);
            }
            else if(suit == 'H'){
                Hearts.push(card);
            }
            else if(suit == 'S'){
                Spades.push(card);
            }
        }
    }

    // Remove the top card of the pillar
    public Card removeCard(char suit) {
        if(suit == 'C'){
            return Clubs.pop();
        } else if(suit == 'D'){
            return Diamonds.pop();
        } else if(suit == 'H'){
            return Hearts.pop();
        } else if(suit == 'S'){
            return Spades.pop();
        } else {
            throw new IllegalArgumentException("Must be a valid suit (C, D, H, S) and a char type not a string");
        }
    }

    // Peak at all the top cards of the pillars
    public HashMap<Character, Card> peakCardsAsHashMap() {
        HashMap<Character, Card> cards = new HashMap<Character, Card>();
        cards.put('C', Clubs.peek());
        cards.put('D', Diamonds.peek());
        cards.put('H', Hearts.peek());
        cards.put('S', Spades.peek());
        return cards;
    }

    // Peak at all the top cards of the pillars
    public ArrayList<Card> peakCardsAsArrayList() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(Clubs.peek());
        cards.add(Diamonds.peek());
        cards.add(Hearts.peek());
        cards.add(Spades.peek());
        return cards;
    }
    
}
