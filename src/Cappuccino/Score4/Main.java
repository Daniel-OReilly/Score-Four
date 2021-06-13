
package Cappuccino.Score4;

import ca.unbc.cpsc.score4.exceptions.PlayerException;

public class Main {

    public static void main(String[] args) throws PlayerException, InterruptedException {
        Referee referee = Referee.getInstance();
        referee.start();
        //runs game
    }
}
