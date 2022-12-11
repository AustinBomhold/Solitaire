import java.util.*;

public class Pillars extends Checks {

    private Stack<Card> Clubs;
    private Stack<Card> Diamonds;
    private Stack<Card> Hearts;
    private Stack<Card> Spades;
    private HashMap<Character, Stack<Card>> Suits;

    //Revision 2
    // (Zander) Created an arraylist called "Suits" which contains the suit stacks
    public Pillars() {
        Clubs = new Stack<Card>();
        Diamonds = new Stack<Card>();
        Hearts = new Stack<Card>();
        Spades = new Stack<Card>();
        Suits = new HashMap<>();
        Suits.put('H', Hearts);
        Suits.put('D', Diamonds);
        Suits.put('C', Clubs);
        Suits.put('S', Spades);
    }

    //Revision 2
    // Add a card to the pillar
    public void addCard(Card card, char suit) {
        if(canMoveCardToPillar(card, Suits.get(suit))){
            Suits.get(suit).add(card);
        }
    }

    //Revision 2
    // Remove the top card of the pillar
    public Card removeCard(char suit) {
        try {
            return Suits.get(suit).pop();
        } catch (Exception IllegalArgumentException) {
            throw new IllegalArgumentException("Must be a valid suit (C, D, H, S) and a char type not a string");
        }
    }

    //Revision 2
    //Determin if a pillar is empty
    public boolean isEmpty(char suit){
        try {
            return Suits.get(suit).isEmpty();
        } catch (Exception IllegalArgumentException) {
            throw new IllegalArgumentException("Must be a valid suit (C, D, H, S) and a char type not a string");
        }
    }

    //Get Suits
    public HashMap<Character,Stack<Card>> getSuits() {
        return Suits;        
    }

    // Revision 2
    // Peak at all the top cards of the pillars
    public HashMap<Character, Card> peakTopCardsAsHashMap() {
        HashMap<Character, Card> topCards = new HashMap<Character, Card>();
        try{
            topCards.put('C', Clubs.peek());
        } catch (EmptyStackException e) {
            topCards.put('C', null);
        }
        try{
            topCards.put('D', Diamonds.peek());
        } catch (EmptyStackException e) {
            topCards.put('D', null);
        }
        try{
            topCards.put('H', Hearts.peek());
        } catch (EmptyStackException e) {
            topCards.put('H', null);
        }
        try{
            topCards.put('S', Spades.peek());
        } catch (EmptyStackException e) {
            topCards.put('S', null);
        }
        return topCards;
    }

    //Delete?
    // Peak at all the top cards of the pillars
    // public ArrayList<Card> peakCardsAsArrayList() {
    //     ArrayList<Card> cards = new ArrayList<Card>();
    //     cards.add(Clubs.peek());
    //     cards.add(Diamonds.peek());
    //     cards.add(Hearts.peek());
    //     cards.add(Spades.peek());
    //     return cards;
    // }
    




}
