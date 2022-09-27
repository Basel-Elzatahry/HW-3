
/** our first trial bot.
  * 
  * @author B&B
  */
  import java.util.HashMap;
  public class AdvancedMatrixBot implements RoShamBot {
    
    /** Returns the same action as the opponent's previous action.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    int[][] GLOBAL_TABLE = new int[75][5];
    Action mySecondToLastMove = Action.ROCK;
    Action oppSecondToLastMove = Action. ROCK;
    Action myLastMove = Action.ROCK;
    String secondToLastResult = "w", result = "w";
    int winning = 0;
    int losing = 0;
    int zeroCounter = 0;
    HashMap<String, Integer> states = new HashMap<String, Integer>();
    HashMap<Action, String> actions = new HashMap<Action, String>();
    HashMap<Action, Action[]> counterMove = new HashMap<Action, Action[]>();
    
    private void setMap(){
        String[] moves = {"paper", "rock", "scissors", "lizard", "spock"};
        String[] results = {"l", "w", "d"};
        int cnt = 0;
        for (int i=0; i < moves.length; i++){
            for (int j =0; j < moves.length; j++){
                for (int k =0; k < results.length; k++){
                    String currentState = moves[i]+moves[j]+results[k];
                    states.put(currentState, cnt);
                    cnt++;
                }
                
            }
        }
        actions.put(Action.ROCK, "rock");
        actions.put(Action.PAPER, "paper");
        actions.put(Action.SCISSORS, "scissors");
        actions.put(Action.LIZARD, "lizard");
        actions.put(Action.SPOCK, "spock");
        Action[] Rock = {Action.PAPER, Action.SPOCK};
        Action[] Paper = {Action.SCISSORS, Action.LIZARD};
        Action[] Scissors = {Action.SPOCK, Action.ROCK};
        Action[] Lizard = {Action.SCISSORS, Action.ROCK};
        Action[] Spock = {Action.PAPER, Action.LIZARD};

        counterMove.put(Action.ROCK, Rock);
        counterMove.put(Action.PAPER, Paper);
        counterMove.put(Action.SCISSORS, Scissors);
        counterMove.put(Action.LIZARD, Lizard);
        counterMove.put(Action.SPOCK, Spock);
    }
    public Action getNextMove(Action lastOpponentMove) {
        setMap();
        // we choose our next move here
        String lastMove = actions.get(lastOpponentMove)+actions.get(myLastMove)+secondToLastResult;

        int row = states.get(lastMove);
        int bestIndex = 0;
        int mostFrequent = -1;
        for (int i = 0; i<5; i++){
            if(GLOBAL_TABLE[row][i] > mostFrequent){
                bestIndex = i;
                mostFrequent = GLOBAL_TABLE[row][i];
            }
        }
        HashMap<Integer, Action> moves = new HashMap<Integer, Action>();
        moves.put(0,Action.ROCK);
        moves.put(1,Action.PAPER);
        moves.put(2,Action.SCISSORS);
        moves.put(3,Action.LIZARD);
        moves.put(4,Action.SPOCK);
        Action mostFrequentMove = moves.get(bestIndex);
        int move = 0 + (int)(Math.random() * ((1 - 0) + 1));
        Action nextMove = counterMove.get(mostFrequentMove)[move];
        // we update the result of secondtolast round
        // opponent lost
        if(counterMove.get(oppSecondToLastMove)[0] == mySecondToLastMove ||
        counterMove.get(oppSecondToLastMove)[1] == mySecondToLastMove){
            losing ++;
            result = "w";
        }
        // opponent won
        if(counterMove.get(mySecondToLastMove)[0] == oppSecondToLastMove || 
        counterMove.get(mySecondToLastMove)[1] == oppSecondToLastMove){
            
            winning ++;
            result = "l";
        }
        // draw
        if(oppSecondToLastMove == mySecondToLastMove){
            result = "d";
        }
        if (winning - losing > 0){
            secondToLastResult = "w";
        }
        else if (winning == losing){
            secondToLastResult = "d";
        }
        else{
            secondToLastResult = "l";
        }

        addToMatrix(lastOpponentMove);
        mySecondToLastMove = myLastMove;
        myLastMove = nextMove;
        oppSecondToLastMove = lastOpponentMove;
        
        //if(zeroCounter >= 1000){
            /*for (int i =0; i< 25; i++){
                System.out.print(statesInv.get(i)+ " ");
                for (int j = 0; j <5; j++){
                    System.out.print(GLOBAL_TABLE[i][j] + " ");
                }
                System.out.println();
            }*/
            //forgetMatrix();
        //}
        return nextMove;
    }
    
    // a function to find the best of 2 moves
    
    public void forgetMatrix() {
        for (int i = 0; i < 75; i++){
            for (int j = 0; j < 5; j++){
                GLOBAL_TABLE[i][j] = 0;
            }
        }
        zeroCounter = 0;

        
    }
    
    public void addToMatrix(Action lastOpponentMove) {
        HashMap<Action, Integer> moves = new HashMap<Action, Integer>();
        moves.put(Action.ROCK, 0);
        moves.put(Action.PAPER, 1);
        moves.put(Action.SCISSORS, 2);
        moves.put(Action.LIZARD, 3);
        moves.put(Action.SPOCK, 4);
        String secondToLastState = "";
        secondToLastState += actions.get(oppSecondToLastMove);
        secondToLastState += actions.get(mySecondToLastMove);
        secondToLastState += secondToLastResult;
        int row = states.get(secondToLastState);
        int col = moves.get(lastOpponentMove);
        GLOBAL_TABLE[row][col]+=1;
        
    }
    
}