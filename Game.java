package MP3;
import java.util.LinkedList;
import java.util.Random;

/**
 * The prupose of this class is to simulate the gameplay
 * @author Brandon Foley
 * @version 1.0
 */
public class Game {
    LinkedList<Player> players = new LinkedList<Player>();
    LinkedList<Card> pile = new LinkedList<Card>();
    public int numPlayers;
    private int playerTurn = 0;
    Dealer dealer = new Dealer();
    String[] patterns = {"doubles", "sandwich", "top bottom"};


    /**
     * Defualt constructor makes new game of 2 players
     */
    public Game(){
        Random randy = new Random();
        players.add(new Player(1,dealer.deals(26),patterns[(randy.nextInt(3))]));
        players.add(new Player(2,dealer.deals(26),patterns[(randy.nextInt(3))]));
        numPlayers = 2;
    }

    /**
     * Overloaded constructor to create game of specified number of players
     * @param numPlayers takes in number of players
     */
    public Game(int numPlayers){
        this.numPlayers = numPlayers;
        Random randy = new Random();
        int numCardsPer = 52/numPlayers;
        for (int i = 1; i <= numPlayers; i++){
            players.add(new Player(i,dealer.deals(numCardsPer),patterns[(randy.nextInt(3))]));
        }


        int counter = 52%numPlayers;
        while(counter != 0){
            players.get(0).addCard(dealer.deals(1).get(0));
            counter--;
            if(counter != 0){
                players.get(1).addCard(dealer.deals(1).get(0));
                counter--;
            }
            if(counter != 0){
                players.get(2).addCard(dealer.deals(1).get(0));
                counter--;
            }
            if(counter != 0){
                players.get(3).addCard(dealer.deals(1).get(0));
                counter--;
            }
        }
    }

    /**
     * Simulates gameplay of ERS until a winner is found
     * @return the number of the winning player
     */
    public int play(){
        boolean winner = false;
        for(int i = 0; i < players.size(); i++){
                System.out.println("Player " + players.get(i).getPlayerNum() + " pattern: " + players.get(i).getPattern());
            }
        int winnerNum = 0;
        playerTurn = -1;
        while(!winner){
            Player currentPlayer = nextPlayer();
            if(faceCard()){
                playFaceCard(currentPlayer);
            }else if(slap() == -1){
                pile.add(currentPlayer.playCard());
            }
            winner = currentPlayer.isWinner();
            System.out.print("Pile: ");
            System.out.println(pile);
            System.out.println("");
            for(int i = 0; i < players.size(); i++){
                    System.out.println("Player " + players.get(i).getPlayerNum() + " hand size: " + players.get(i).getHandSize());
                }
            System.out.println("");
            boolean loser = currentPlayer.isLosser();
            if(winner){
                winnerNum = currentPlayer.getPlayerNum();
            }
            if(loser){
                players.remove(currentPlayer);
                numPlayers--;
                System.out.println(players);
            }
            if(players.size() == 1){
                System.out.println("MADE IT TO END");
                return players.get(0).getPlayerNum();
            }
        }
        return winnerNum;
    }

    /**
     * If someone has played a facecard before this is called to give them
     * the amount of tries for each face card following the rules until
     * they run out of cars, play a face card, or run out of tries
     * @param p takes in the player that has to do the facecard faceoff
     */
    public void playFaceCard(Player p){
        int numTimes = pile.getLast().getValue()-10;
        while(numTimes > 0){
            if(!p.isLosser()){
                pile.add(p.playCard());
                if(pile.getLast().getValue() >= 11){
                    break;
                }
            }
            numTimes--;
        }
        if(numTimes == 0 && pile.getLast().getValue() <=10){
            while(pile.size() != 0){
                int index = players.indexOf(p)-1;
                if(index < 0){
                    index = players.size()-1;
                }
                players.get(index).addCard(pile.getFirst());
                pile.removeFirst();
            }
        }
            
    }

    /**
     * Tests to see if the most recent card on the pile is a facecard
     * @return whether the top card is a face card or not
     */
    public boolean faceCard(){
        if(!pile.isEmpty() && pile.getLast().getValue() >= 11){
            return true;
        }
        return false;
    }
        

    /**
     * Gets the nextplayer to go
     * @return the next player to go
     */
    public Player nextPlayer(){
        playerTurn++;
        if(playerTurn >= players.size()){
            playerTurn = 0;
        }
        return players.get(playerTurn);
    }

    /**
     * Will have a random player slap the deck if possible if not returns negative one
     * @return the index+1 of the player that slapped else -1
     */
    public int slap(){
        Random randomPlayer = new Random();
        int counter = 0;
        int person;
        while(counter != players.size()){
            person = randomPlayer.nextInt(players.size());  //0-3 for 4 players
            if(!pile.isEmpty()){
                if(players.get(person).slaps(pile)){
                    System.out.println("P" + (person+1) + " Slaps");
                    playerTurn = person-1;
                    while(pile.size() != 0){
                        players.get(person).addCard(pile.getFirst());
                        pile.removeFirst();
                    }
                    return person;
                }
            }
            counter++;
        }
        return -1;
    }

    /**
     * tests to see if topBottom is possible
     * @param pile takes in pile to test
     * @return true if someone can slap this
     */
    static boolean topBottom(LinkedList<Card> pile){
        if(!pile.isEmpty() && pile.size() >=2){
            if(pile.getFirst().getValue() == pile.getLast().getValue()){
                System.out.println("Is topBottom");
                return true;
            }
        }
        return false;
    }

    /**
     * tests to see if doubles is possible
     * @param pile takes in pile to test
     * @return true if someone can slap this
     */
    static boolean doubles(LinkedList<Card> pile){
        if(!pile.isEmpty() && pile.size() >=2){
            if(pile.get(pile.size()-2).getValue() == pile.getLast().getValue()){
                System.out.println("Is doubles");
                return true;
            }
        }
        return false;
    }

    /**
     * tests to see if sandwich is possible
     * @param pile takes in pile to test
     * @return true if someone can slap this
     */
    static boolean sandwich(LinkedList<Card> pile){
        if(!pile.isEmpty() && pile.size() >=3){
            if(pile.get(pile.size()-3).getValue() == pile.getLast().getValue()){
                System.out.println("Is sandwich");
                return true;
            }
        }
        return false;
    }

    /**
     * Accesor to get players
     * @return linked list of players
     */
    public LinkedList<Player> getPlayers(){
        return players;
    }

    /**
     * Accesor to get pile
     * @return linked list of the pile
     */
    public LinkedList<Card> getPile(){
        return pile;
    }

    /**
     * Accessor to get dealer
     * @return the dealer
     */
    public Dealer getDealer(){
        return dealer;
    }

    /**
     * Accessor to get the possible patterns
     * @return the patterns
     */
    public String[] getPatterns(){
        return patterns;
    }
}
