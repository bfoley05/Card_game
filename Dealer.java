package MP3;
import java.util.LinkedList;
/**
 * The prupose is to have a Dealer that deals cards to a person
 * @author Brandon Foley
 * @version 1.0
 */

public class Dealer {
    LinkedList<Card> hand = new LinkedList<Card>();
    Deck m_deck;
    
    /**
     * Constructor that creates a new deck of cards
     */
    public Dealer(){
        m_deck = new Deck();
    }

    /**
     * deals method that returns the linked list of a specified number of random cards
     * @param n takes in an int for amount of cards in the hand
     * @return the linked list to represnt a hand
     */
    public LinkedList<Card> deals(int n){
        hand = new LinkedList<Card>();
        while(n != 0){
            if(m_deck.size() == 0){
                return hand;
            }
            hand.add(m_deck.deal());
            n--;
        }
        return hand;
    }

    /**
     * Accessor that returns the size of the deck
     * @return int the size of the deck
     */
    public int size(){
        return m_deck.size();
    }
    /**
     * returns what cards are still in the deck
     */
    public String toString(){
        return m_deck.toString();
    }
}
