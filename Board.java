import java.util.*;

/*
 * TODO:
 * 
 * 1. place down loop
 * 
 */

public class Board extends Player {
    private static Table table;
    private static Pillars pillars;
    private static Hand hand;
    private static Waste waste;

    public static void main(String[] args) {
        table = new Table();
        pillars = new Pillars();
        hand = new Hand();
        waste = new Waste();

        Player player = new Player();
        dealNewGame();
        //displayBoard(pillars, table, hand, waste);

        // Gamplay Loop
        // Get user input
        Scanner input = new Scanner(System.in);
        while (!Checks.gameIsWon(pillars.getSuits())) {
            

            /*
             * 1. Pick up a card from the table
             */
            do {
                //Display Board
                displayBoard(pillars, table, hand, waste);
                // Selct a pile
                String pile = "";
                player.setCard(null);
                player.setCards(null);
                System.out.println();
                System.out.println("Select Pile: ");
                do {
                    System.out.println("Plese enter a valid pile: Hand, 1...7, Waste, C, D, H, S");
                    pile = input.nextLine();
                } while (!pile.equals("Hand") && !pile.equals("Waste") && !pile.equals("C") && !pile.equals("D")
                        && !pile.equals("H") && !pile.equals("S") && !pile.equals("1") && !pile.equals("2")
                        && !pile.equals("3") && !pile.equals("4") && !pile.equals("5") && !pile.equals("6")
                        && !pile.equals("7"));


                switch (pile) {
                    case "1":
                    case "2":
                    case "3":
                    case "4":                    
                    case "5":
                    case "6":
                    case "7":

                        int columnNum = Integer.parseInt(pile);
                        //If FaceDown, Flip and retry
                            if (!table.getColumns().get(columnNum).getLast().isFaceUp){
                                table.getColumns().get(columnNum).getLast().setFaceUp(true);
                                System.out.println("Flipping Card");
                                continue;
                            }
                        // If the player selected a column, get the card number
                        int numberOfCards = 0;
                        System.out.println("Select Card Number: ");
                        do {
                            System.out.println("Please enter an integer between 1 and " + table.getNumCards((columnNum)));
                            try{
                                numberOfCards = Integer.parseInt(input.nextLine());
                            } catch (Exception e){
                                continue;
                            }
                        } while (numberOfCards < 1 || numberOfCards > table.getNumCards(columnNum));

                        if (Checks.canRemoveSeriesFromColumn(numberOfCards, table.getColumns().get(columnNum))) {
                            player.setCards(table.removeCard(columnNum, numberOfCards));
                        } else {
                            System.out.println("You can't remove that series of cards");
                        }
                        break;

                    case "Hand":

                        if (hand.isEmpty()) {
                            System.out.println("Hand is empty");
                        } else {
                            player.setCard(hand.removeCard());
                        }
                        break;

                    case "Waste":

                        if (waste.isEmpty()) {
                            System.out.println("Waste is empty");
                        } else {
                            player.setCard(waste.removeCard());
                        }
                        break;

                    case "C":
                    case "D":
                    case "H":
                    case "S":

                        if (pillars.getSuits().get(pile.toCharArray()[0]).empty() ) {
                            System.out.println("Pillar is empty");
                        } else {
                            player.setCard(pillars.removeCard(pile.toCharArray()[0]));
                        }
                        break;
                                    
                    default:
                        break;
                }

            } while (player.getCard() == null && player.getCards() == null);

            /*
             * 2. Place the card on the table
             */

            do {
                System.out.println();
                if (player.getCard() != null) {
                    System.out.println("You are holding: " + player.getCard().toShortString());
                }
                if (player.getCards() != null) {
                    System.out.println("You are holding: ");
                    for (Card card : player.getCards())
                    System.out.println(card.toShortString());
                }
            } while (false);

        }
        input.close();

    }

    // Deal a new game
    public static void dealNewGame() {
        // Create a new deck
        Deck deck = new Deck();

        // Shuffle the deck
        deck.shuffle();

        // Deal the cards to the table
        for (int i = 1; i <= 7; i++) {
            for (int j = i; j <= 7; j++) {
                // Last card of every column should be face up
                if (j == i) {
                    table.addCard(deck.dealCard(), j, true);
                } else {
                    table.addCard(deck.dealCard(), j);
                }
            }
        }

        // Deal the rest of the cards to the hand
        while (deck.cardsLeft() > 0) {
            hand.addCard(deck.dealCard());
        }
    }

    // Display the board

    public static void displayBoard(Pillars pillars) {
        // Display the pillars
        HashMap<Character, Card> tempPillars = pillars.peakCardsAsHashMap();
        System.out.println("Pillars: ");
        if (tempPillars.containsKey('C')) {
            System.out.println("     " + "Clubs: " + tempPillars.get('C'));
        } else {
            System.out.println("     " + "Clubs: " + "Empty");
        }
        if (tempPillars.containsKey('D')) {
            System.out.println("     " + "Diamonds: " + tempPillars.get('D'));
        } else {
            System.out.println("     " + "Diamonds: " + "Empty");
        }
        if (tempPillars.containsKey('H')) {
            System.out.println("     " + "Hearts: " + tempPillars.get('H'));
        } else {
            System.out.println("     " + "Hearts: " + "Empty");
        }
        if (tempPillars.containsKey('S')) {
            System.out.println("     " + "Spades: " + tempPillars.get('S'));
        } else {
            System.out.println("     " + "Spades: " + "Empty");
        }
        System.out.println();
    }

    public static void displayBoard(Table table) {
        // Display the table
        System.out.println("Table: ");
        for (int i = 1; i <= 7; i++) {
            System.out.println("     " + "Column " + i + ": ");
            System.out.println(table.columntoString(i));
        }
        System.out.println();
    }

    public static void displayBoard(Hand hand) {
        // Display the hand size
        System.out.println("Hand: " + hand.getSize() + " cards");
        System.out.println();
    }

    public static void displayBoard(Waste waste) {
        // Display the waste top card
        System.out.println("Waste: " + waste.revealCardAsString());
        System.out.println();

    }

    public static void displayBoard(Pillars pillars, Table table, Hand hand, Waste waste) {
        displayBoard(hand);
        displayBoard(waste);
        displayBoard(pillars);
        displayBoard(table);

    }
}
