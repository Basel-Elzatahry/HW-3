
/** our first trial bot.
  * 
  * @author B&B
  */
public class BotOne implements RoShamBot {
    
    /** Returns the same action as the opponent's previous action.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    int[][] GLOBAL_TABLE = new int[25][5];
    HashMap<String, int> states = new HashMap<String, int>();
    HashMap<Action, String> actions = new HashMap<Action, String>();
    private void setMap(){
        String[] moves = {"paper", "rock", "scissors", "lizard", "spock"};
        int cnt = 0;
        for (int i=0; i < moves.size(); i++){
            for (int k = 0; j < moves.size(); j++){
                String currentState = moves[i]+moves[j];
                states.put(currentState, cnt);
                cnt++;
            }
        }
        actions.put(ROCK, "rock");
        actions.put(PAPER, "paper");
        actions.put(SCISSORS, "scissors");
        actions.put(LIZARD, "lizard");
        actions.put(SPOCK, "spock");
    }
    public Action getNextMove(Action lastOpponentMove) {
        //setMap();
        addToMatrix(lastOpponentMove);
        return lastOpponentMove;
    }
    
    public int[][] getMatrix () {
        return GLOBAL_TABLE;
    }
    
    public void forgetMatrix() {
        int[][] empty_table = new int[][];
        GLOBAL_TABLE = empty_table;
        
    }
    
    public void addToMatrix(Action lastOpponentMove) {
        
        
    }
    
}