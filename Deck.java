package MP3;
import java.util.LinkedList;
import java.util.Random;
/**
 * The prupose is to make a Deck of cards
 * @author Brandon Foley
 * @version 1.0
 */

public class Deck {
    LinkedList<Card> m_cards = new LinkedList<Card>();

    /**
     * Constructor that creates a new Deck with 52 cards
     */
    public Deck(){
        for(int i = 0; i < 4; i++){
            for(int j = 2; j < 15; j++){
                m_cards.add(new Card(j,i));
            }
        }
    }
    /**
     * Overloaded constructor that creates a copy of a deck of cards
     * @param d takes in a deck
     */
    public Deck(Deck d){
        m_cards = d.m_cards;
    }
    
    /**
     * Accessor to get the size of a deck
     * @return int the size of the deck
     */
    public int size(){
        return m_cards.size();
    }

    /**
     * Method that deals one random card from the deck
     * @return random card from the deck
     */
    public Card deal(){
        Random randy = new Random();
        int index = randy.nextInt(m_cards.size());
        return m_cards.remove(index);
    }

    /**
     * returns the cards in the Deck
     */
    public String toString(){
        return m_cards.toString();
    }
}
