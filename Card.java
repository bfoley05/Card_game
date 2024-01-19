package MP3;
/**
 * The prupose is to make a Card for a deck of cards
 * @author Brandon Foley
 * @version 1.0
 */

public class Card {
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;


    public static final int HEARTS = 0;
    public static final int SPADES = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;


    int value;
    int suit;

    /**
     * Default Constructor creates ace of spades
     */
    public Card(){
        value = 14;
        suit = 1;
    }

    /**
     * Overloaded constructor takes in the value of a card and suit
     * @param value takes in the value of a card
     * @param suit takes in the suit of a card
     */
    public Card(int value, int suit){
        this.value = value;
        this.suit = suit;
    }
    /**
     * Copy constructor
     * @param c takes in a card and makes a copy of it
     */
    public Card(Card c){
        value = c.value;
        suit = c.suit;
    }

    /**
     * equals method to return if two cards have the same value
     * @param c takes in another card to compare
     * @return boolean value
     */
    public boolean equals(Card c){
        return value == c.value;
    }
    /**
     * Accessor for the value of a card
     * @return int represnts value of a card
     */
    public int getValue(){
        return value;
    }
    /**
     * Accessor for the suit of a card
     * @return int that represnts the suit of a card
     */
    public int getSuit(){
        return suit;
    }
    /**
     * Setter for the value of a card
     * @param value takes in an int that represnts the value of a card
     */
    public void setValue(int value){
        this.value = value;
    }
    /**
     * Setter for the suit of a card
     * @param suit takes in an int that represnts the suit of a card
     */
    public void setSuit(int suit){
        this.suit = suit;
    }
    /**
     * ToString method to print out a Card in a nice format
     */
    public String toString(){
        String output = "";
        if(value == 11){
            output += "Jack";
        }else if(value == 12){
            output += "Queen";
        }else if(value == 13){
            output += "King";
        }else if(value == 14){
            output += "Ace";
        }else{
            output += value;
        }

        if(suit == 0){
            output += " of Hearts";
        }else if(suit == 1){
            output += " of Spades";
        }else if(suit == 2){
            output += " of Clubs";
        }else if(suit == 3){
            output += " of Diamonds";
        }

        return output;
    }
}
