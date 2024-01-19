package MP3;
import java.util.LinkedList;

/**
 * The prupose of this class is to simulate a player of ERS
 * @author Brandon Foley
 * @version 1.0
 */
public class Player {
    int playerNum;
    LinkedList<Card> hand;
    String pattern;


    /**
     * Overloaded constructor to set all the values specified
     * @param pn player number
     * @param h hand
     * @param p pattern
     */
    public Player(int pn, LinkedList<Card> h, String p){
        hand = h;
        pattern = p;
        playerNum = pn;
    }

    /**
     * Plays the first card in a players hand
     * @return a card
     */
    public Card playCard(){
        return hand.removeFirst();
    }

    /**
     * checks wheter or not a player can slap
     * @param pile takes in pile to test
     * @return true if they can slap
     */
    public boolean slaps(LinkedList<Card> pile){
        if(Game.doubles(pile)){
            if(pattern.equals("doubles")){
                return true;
            }
        }if(Game.sandwich(pile)){
            if(pattern.equals("sandwich")){
                return true;
            }
        }if(Game.topBottom(pile)){
            if(pattern.equals("top bottom")){
                return true;
            }
        }
        return false;
    }

    /**
     * Mutator to add card to a hand
     * @param c takes in card to add
     */
    public void addCard(Card c){
        hand.add(c);
    }

    /**
     * Accessor to get hand of player
     * @return linked list of hand
     */
    public LinkedList<Card> getHand(){
        return hand;
    }

    /**
     * Accessor to get player number
     * @return int for player num
     */
    public int getPlayerNum(){
        return playerNum;
    }

    /**
     * Accesor to get if the person is a winner
     * @return true if winner
     */
    public boolean isWinner(){
        return hand.size() == 52;
    }

    /**
     * Accessor to get if the person is a loser
     * @return true if loser
     */
    public boolean isLosser(){
        return hand.size() == 0;
    }

    /**
     * Accesor to get amount of cards in players hand
     * @return size of players hand
     */
    public int getHandSize(){
        return hand.size();
    }

    /**
     * Accessor to see what pattern a player has
     * @return String of pattern they have
     */
    public String getPattern(){
        return pattern;
    }

    /**
     * Tostring to format nicely
     * @return String nicely formatted
     */
    public String toString(){
        return "Player number: " + playerNum + "\nPattern: " + pattern + "\nHand: " + hand;
    }
}
