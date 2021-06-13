package Cappuccino.Score4;

import ca.unbc.cpsc.cappuccino.*;
import ca.unbc.cpsc.score4.exceptions.PlayerException;

public class Referee {

    private static Referee instance = null;
    private Board board;
    private Player player;
    private Opponent ai;
    private boolean playerTurnComplete = false;
    private UI program;
    static boolean playing = false;
    private WinCondition checker;
    private boolean reset;

    protected Referee() {
        player = new Player();
        ai = Opponent.getAI();
        program = UI.getInstance(this);
        program.start();
    }

    public void start() throws PlayerException {
        board = Board.getInstance();
        checker = new WinCondition();
        while (!(program.getReady())) {
            System.out.print(""); //forcing this to wait for the swing to get playerColour
        }

        while (true) {
            playing = true;
            startPlaying();
            while (!reset) {
                System.out.print(""); //waiting. this stops the thread from ending while reseting
            }
            reset = false;
            while (!(program.getReady())) {
                System.out.print(""); //forcing this to wait for the swing to get playerColour
            }
        }
    }

    private void startPlaying() throws PlayerException {
        while (playing) {
            if (Player.getColour() == BeadColour.WHITE) {
                playerFirst();
            } else {
                AIFirst();
            }
        }
    }

    public void playerTurn(Location l) throws PlayerException {
        playerTurnComplete = true;
        ai.opponentPlays(l);
    }

    public static Referee getInstance() { //creating the singleton
        if (instance == null) {
            instance = new Referee();
        }
        return instance;
    }

    public static Colour beadColour(BeadColour b) {
        if (b == BeadColour.BLACK) {
            return new Colour(BeadColour.WHITE);
        } else {
            return new Colour(BeadColour.BLACK);
        }
    }

    private void AIFirst() throws PlayerException {
        board.lockPegs(true);
        program.changeTurn("White");
        board.placeBead(ai.requestMoveLocation());
        checkPlaying("White");
        if (playing) { //if the previous turn won don't allow the next turn to happen
            board.lockPegs(false);
            program.changeTurn("Black");
            while (!playerTurnComplete) {
                System.out.print(""); //forcing this to wait for the player
            }
            checkPlaying("Black");
            playerTurnComplete = false;

        }
        board.lockPegs(true);
        program.changeTurn("White");
    }

    private void playerFirst() throws PlayerException {
        program.changeTurn("White");
        while (!playerTurnComplete) {
            System.out.print(""); //forcing this to wait for the player
        }
        
        checkPlaying("White");
        playerTurnComplete = false;
        board.lockPegs(true);
        if (playing) { //if the previous turn won don't allow the next turn to happen
            program.changeTurn("Black");
            board.placeBead(ai.requestMoveLocation());
            board.lockPegs(false);
            checkPlaying("Black");
        }
    }

    private void checkPlaying(String s) {
        if (checker.check()) {
            playing = false; //turning off the turn swapping
            board.lockPegs(true);
            win(s);
        }
    }

    private void win(String s) {
        board.setWin(s); //passing the win string along
    }

    public void reset() {
        reset = true;
    }
}
