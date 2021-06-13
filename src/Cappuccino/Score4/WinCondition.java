package Cappuccino.Score4;

import static ca.unbc.cpsc.cappuccino.BeadColour.BLACK;
import static ca.unbc.cpsc.cappuccino.BeadColour.WHITE;

public class WinCondition {

    Board board = Board.getInstance();

    public boolean check() {
        PegComponent[][] WinPeg = board.getPegArray();
        draw();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (WinPeg[i][j].getBeadPos(0) != null
                        && WinPeg[i][j].getBeadPos(0) == WinPeg[i][j].getBeadPos(1)
                        && WinPeg[i][j].getBeadPos(2) == WinPeg[i][j].getBeadPos(3)
                        && WinPeg[i][j].getBeadPos(0) == WinPeg[i][j].getBeadPos(3)) {
                    System.out.println("Indiv Peg");
                    return true;
                    //will check for a win on an individual (a of 16) peg
                }
            }
        }

        for (int b = 0; b < 4; b++) {
            if ((WinPeg[0][0].getBeadPos(b) != null
                    && WinPeg[0][0].getBeadPos(b) == WinPeg[1][0].getBeadPos(b)
                    && WinPeg[2][0].getBeadPos(b) == WinPeg[3][0].getBeadPos(b)
                    && WinPeg[1][0].getBeadPos(b) == WinPeg[2][0].getBeadPos(b))
                    || (WinPeg[0][1].getBeadPos(b) != null
                    && WinPeg[0][1].getBeadPos(b) == WinPeg[1][1].getBeadPos(b)
                    && WinPeg[2][1].getBeadPos(b) == WinPeg[3][1].getBeadPos(b)
                    && WinPeg[1][1].getBeadPos(b) == WinPeg[2][1].getBeadPos(b))
                    || (WinPeg[0][2].getBeadPos(b) != null
                    && WinPeg[0][2].getBeadPos(b) == WinPeg[1][2].getBeadPos(b)
                    && WinPeg[2][2].getBeadPos(b) == WinPeg[3][2].getBeadPos(b)
                    && WinPeg[1][2].getBeadPos(b) == WinPeg[2][2].getBeadPos(b))
                    || (WinPeg[0][3].getBeadPos(b) != null
                    && WinPeg[0][3].getBeadPos(b) == WinPeg[1][3].getBeadPos(b)
                    && WinPeg[2][3].getBeadPos(b) == WinPeg[3][3].getBeadPos(b)
                    && WinPeg[1][3].getBeadPos(b) == WinPeg[2][3].getBeadPos(b))) {
                System.out.println("Vertical Line");
                return true;
                //will check for a win in a vertical line
            }
        }
        for (int b = 0; b < 4; b++) {
            if ((WinPeg[0][0].getBeadPos(b) != null
                    && WinPeg[0][0].getBeadPos(b) == WinPeg[0][1].getBeadPos(b)
                    && WinPeg[0][2].getBeadPos(b) == WinPeg[0][3].getBeadPos(b)
                    && WinPeg[0][1].getBeadPos(b) == WinPeg[0][2].getBeadPos(b))
                    || (WinPeg[1][0].getBeadPos(b) != null
                    && WinPeg[1][0].getBeadPos(b) == WinPeg[1][1].getBeadPos(b)
                    && WinPeg[1][2].getBeadPos(b) == WinPeg[1][3].getBeadPos(b)
                    && WinPeg[1][1].getBeadPos(b) == WinPeg[1][2].getBeadPos(b))
                    || (WinPeg[2][0].getBeadPos(b) != null
                    && WinPeg[2][0].getBeadPos(b) == WinPeg[2][1].getBeadPos(b)
                    && WinPeg[2][2].getBeadPos(b) == WinPeg[2][3].getBeadPos(b)
                    && WinPeg[2][1].getBeadPos(b) == WinPeg[2][2].getBeadPos(b))
                    || (WinPeg[3][0].getBeadPos(b) != null
                    && WinPeg[3][0].getBeadPos(b) == WinPeg[3][1].getBeadPos(b)
                    && WinPeg[3][2].getBeadPos(b) == WinPeg[3][3].getBeadPos(b)
                    && WinPeg[3][1].getBeadPos(b) == WinPeg[3][2].getBeadPos(b))) {
                System.out.println("Horizontal Line");
                return true;
                //will check for a win in a horizontal line
            }
        }
        for (int b = 0; b < 4; b++) {
            if ((WinPeg[0][0].getBeadPos(b) != null
                    && WinPeg[0][0].getBeadPos(b) == WinPeg[1][1].getBeadPos(b)
                    && WinPeg[2][2].getBeadPos(b) == WinPeg[3][3].getBeadPos(b)
                    && WinPeg[1][1].getBeadPos(b) == WinPeg[2][2].getBeadPos(b))
                    || (WinPeg[0][3].getBeadPos(b) != null
                    && WinPeg[0][3].getBeadPos(b) == WinPeg[1][2].getBeadPos(b)
                    && WinPeg[2][1].getBeadPos(b) == WinPeg[3][0].getBeadPos(b)
                    && WinPeg[1][2].getBeadPos(b) == WinPeg[2][1].getBeadPos(b))) {

                System.out.println("Diagonal X Top");
                return true;
                //will check for a win diagonally, in the "X" position
            }
        }
        for (int a = 0; a < 4; a++) {
            if ((WinPeg[a][0].getBeadPos(3) != null
                    && WinPeg[a][0].getBeadPos(3) == WinPeg[a][1].getBeadPos(2)
                    && WinPeg[a][2].getBeadPos(1) == WinPeg[a][3].getBeadPos(0)
                    && WinPeg[a][1].getBeadPos(2) == WinPeg[a][2].getBeadPos(1))
                    || (WinPeg[a][0].getBeadPos(0) != null
                    && WinPeg[a][0].getBeadPos(0) == WinPeg[a][1].getBeadPos(1)
                    && WinPeg[a][2].getBeadPos(2) == WinPeg[a][3].getBeadPos(3)
                    && WinPeg[a][1].getBeadPos(1) == WinPeg[a][2].getBeadPos(2))) {

                System.out.println("Diag X Side");
                return true;
                //will check for a win diagonlly, going down the "side"
            }
        }
        if ((WinPeg[0][0].getBeadPos(0) != null
                && WinPeg[0][0].getBeadPos(0) == WinPeg[1][1].getBeadPos(1)
                && WinPeg[2][2].getBeadPos(2) == WinPeg[3][3].getBeadPos(3)
                && WinPeg[1][1].getBeadPos(1) == WinPeg[2][2].getBeadPos(2))
                || (WinPeg[0][0].getBeadPos(3) != null
                && WinPeg[0][0].getBeadPos(3) == WinPeg[1][1].getBeadPos(2)
                && WinPeg[2][2].getBeadPos(1) == WinPeg[3][3].getBeadPos(0)
                && WinPeg[1][1].getBeadPos(2) == WinPeg[2][2].getBeadPos(1))
                || (WinPeg[0][3].getBeadPos(0) != null
                && WinPeg[0][3].getBeadPos(0) == WinPeg[1][2].getBeadPos(1)
                && WinPeg[2][1].getBeadPos(2) == WinPeg[3][0].getBeadPos(3)
                && WinPeg[1][2].getBeadPos(1) == WinPeg[2][1].getBeadPos(2))
                || (WinPeg[0][3].getBeadPos(3) != null
                && WinPeg[0][3].getBeadPos(3) == WinPeg[1][2].getBeadPos(2)
                && WinPeg[2][1].getBeadPos(1) == WinPeg[3][0].getBeadPos(0)
                && WinPeg[1][2].getBeadPos(2) == WinPeg[2][1].getBeadPos(1))) {

            System.out.println("Diag X Angle");
            return true;
        }

        for (int a = 0; a < 4; a++) {
            if ((WinPeg[0][a].getBeadPos(3) != null
                    && WinPeg[0][a].getBeadPos(3) == WinPeg[1][a].getBeadPos(2)
                    && WinPeg[2][a].getBeadPos(1) == WinPeg[3][a].getBeadPos(0)
                    && WinPeg[1][a].getBeadPos(2) == WinPeg[2][a].getBeadPos(1))
                    || ((WinPeg[0][a].getBeadPos(0) != null
                    && WinPeg[0][a].getBeadPos(0) == WinPeg[1][a].getBeadPos(1)
                    && WinPeg[2][a].getBeadPos(2) == WinPeg[3][a].getBeadPos(3)
                    && WinPeg[1][a].getBeadPos(1) == WinPeg[2][a].getBeadPos(2)))) {

                System.out.println("Diag X Other");
                return true;
                //checks for a win in the other x direction
            }
        }

        return false;
    }

    public boolean draw() {
        PegComponent[][] WinPeg = board.getPegArray();
        int count = 0;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if ((WinPeg[a][b].getBeadPos(0) == WHITE || WinPeg[a][b].getBeadPos(1) == WHITE
                        || WinPeg[a][b].getBeadPos(2) == WHITE || WinPeg[a][b].getBeadPos(3) == WHITE)
                        && (WinPeg[a][b].getBeadPos(0) == BLACK || WinPeg[a][b].getBeadPos(1) == BLACK
                        || WinPeg[a][b].getBeadPos(2) == BLACK || WinPeg[a][b].getBeadPos(3) == BLACK)) {

                    count++;

                }
            }

        }

        for (int a = 0; a < 4; a++) {
            {
                int b = 0;
                for (int c = 0; c < 4; c++) {

                    if ((WinPeg[a][b].getBeadPos(c) == WHITE || WinPeg[a][b + 1].getBeadPos(c) == WHITE
                            || WinPeg[a][b + 2].getBeadPos(c) == WHITE || WinPeg[a][b + 3].getBeadPos(c) == WHITE)
                            && (WinPeg[a][b].getBeadPos(c) == BLACK || WinPeg[a][b + 1].getBeadPos(c) == BLACK
                            || WinPeg[a][b + 2].getBeadPos(c) == BLACK || WinPeg[a][b + 3].getBeadPos(c) == BLACK)) {
                        count++;
                    }
                }

            }
        }

        for (int b = 0; b < 4; b++) {
            int a = 0;
            for (int c = 0; c < 4; c++) {
                if ((WinPeg[a][b].getBeadPos(c) == WHITE || WinPeg[a + 1][b].getBeadPos(c) == WHITE
                        || WinPeg[a + 2][b].getBeadPos(c) == WHITE || WinPeg[a + 3][b].getBeadPos(c) == WHITE)
                        && (WinPeg[a][b].getBeadPos(c) == BLACK || WinPeg[a + 1][b].getBeadPos(c) == BLACK
                        || WinPeg[a + 2][b].getBeadPos(c) == BLACK || WinPeg[a + 3][b].getBeadPos(c) == BLACK)) {
                    count++;
                }
            }

        }

        for (int a = 0; a < 4; a++) {
            if (((WinPeg[0][0].getBeadPos(a) == WHITE
                    || WinPeg[1][1].getBeadPos(a) == WHITE
                    || WinPeg[2][2].getBeadPos(a) == WHITE
                    || WinPeg[3][3].getBeadPos(a) == WHITE)
                    && (WinPeg[0][0].getBeadPos(a) == BLACK
                    || WinPeg[1][1].getBeadPos(a) == BLACK
                    || WinPeg[2][2].getBeadPos(a) == BLACK
                    || WinPeg[3][3].getBeadPos(a) == BLACK))) {
                count++;

            }
            if (((WinPeg[0][3].getBeadPos(a) == WHITE
                    || WinPeg[1][2].getBeadPos(a) == WHITE
                    || WinPeg[2][1].getBeadPos(a) == WHITE
                    || WinPeg[3][0].getBeadPos(a) == WHITE)
                    && (WinPeg[0][3].getBeadPos(a) == BLACK
                    || WinPeg[1][2].getBeadPos(a) == BLACK
                    || WinPeg[2][1].getBeadPos(a) == BLACK
                    || WinPeg[3][0].getBeadPos(a) == BLACK))) {
                count++;
            }
        }
        for (int a = 0; a < 4; a++) {
            int b = 0;
            if (((WinPeg[a][b].getBeadPos(3) == WHITE
                    || WinPeg[a][b + 1].getBeadPos(2) == WHITE
                    || WinPeg[a][b + 2].getBeadPos(1) == WHITE
                    || WinPeg[a][b + 3].getBeadPos(0) == WHITE)
                    && (WinPeg[a][b].getBeadPos(3) == BLACK
                    || WinPeg[a][b + 1].getBeadPos(2) == BLACK
                    || WinPeg[a][b + 2].getBeadPos(1) == BLACK
                    || WinPeg[a][b + 3].getBeadPos(0) == BLACK))) {
                count++;
            }
            if (((WinPeg[a][b].getBeadPos(0) == WHITE
                    || WinPeg[a][b + 1].getBeadPos(1) == WHITE
                    || WinPeg[a][b + 2].getBeadPos(2) == WHITE
                    || WinPeg[a][b + 3].getBeadPos(3) == WHITE)
                    && (WinPeg[a][b].getBeadPos(0) == BLACK
                    || WinPeg[a][b + 1].getBeadPos(1) == BLACK
                    || WinPeg[a][b + 2].getBeadPos(2) == BLACK
                    || WinPeg[a][b + 3].getBeadPos(3) == BLACK))) {
                count++;
            }

        }
        for (int b = 0; b < 4; b++) {
            int a = 0;
            if (((WinPeg[a][b].getBeadPos(3) == WHITE
                    || WinPeg[a + 1][b].getBeadPos(2) == WHITE
                    || WinPeg[a + 2][b].getBeadPos(1) == WHITE
                    || WinPeg[a + 3][b].getBeadPos(0) == WHITE)
                    && (WinPeg[a][b].getBeadPos(3) == BLACK
                    || WinPeg[a + 1][b].getBeadPos(2) == BLACK
                    || WinPeg[a + 2][b].getBeadPos(1) == BLACK
                    || WinPeg[a + 3][b].getBeadPos(0) == BLACK))) {
                count++;
            }
            if (((WinPeg[a][b].getBeadPos(0) == WHITE
                    || WinPeg[a + 1][b].getBeadPos(1) == WHITE
                    || WinPeg[a + 2][b].getBeadPos(2) == WHITE
                    || WinPeg[a + 3][b].getBeadPos(3) == WHITE)
                    && (WinPeg[a][b].getBeadPos(0) == BLACK
                    || WinPeg[a + 1][b].getBeadPos(1) == BLACK
                    || WinPeg[a + 2][b].getBeadPos(2) == BLACK
                    || WinPeg[a + 3][b].getBeadPos(3) == BLACK))) {
                count++;
            }

        }
        if (((WinPeg[0][0].getBeadPos(3) == WHITE
                || WinPeg[1][1].getBeadPos(2) == WHITE
                || WinPeg[2][2].getBeadPos(1) == WHITE
                || WinPeg[3][3].getBeadPos(0) == WHITE)
                && (WinPeg[0][0].getBeadPos(3) == BLACK
                || WinPeg[1][1].getBeadPos(2) == BLACK
                || WinPeg[2][2].getBeadPos(1) == BLACK
                || WinPeg[3][3].getBeadPos(0) == BLACK))) {
            count++;

        }
        if (((WinPeg[0][0].getBeadPos(0) == WHITE
                || WinPeg[1][1].getBeadPos(1) == WHITE
                || WinPeg[2][2].getBeadPos(2) == WHITE
                || WinPeg[3][3].getBeadPos(3) == WHITE)
                && (WinPeg[0][0].getBeadPos(0) == BLACK
                || WinPeg[1][1].getBeadPos(1) == BLACK
                || WinPeg[2][2].getBeadPos(2) == BLACK
                || WinPeg[3][3].getBeadPos(3) == BLACK))) {
            count++;

        }

        if (((WinPeg[0][3].getBeadPos(3) == WHITE
                || WinPeg[1][2].getBeadPos(2) == WHITE
                || WinPeg[2][1].getBeadPos(1) == WHITE
                || WinPeg[3][0].getBeadPos(0) == WHITE)
                && (WinPeg[0][3].getBeadPos(3) == BLACK
                || WinPeg[1][2].getBeadPos(2) == BLACK
                || WinPeg[2][1].getBeadPos(1) == BLACK
                || WinPeg[3][0].getBeadPos(0) == BLACK))) {
            count++;
        }
        if (((WinPeg[0][3].getBeadPos(0) == WHITE
                || WinPeg[1][2].getBeadPos(1) == WHITE
                || WinPeg[2][1].getBeadPos(2) == WHITE
                || WinPeg[3][0].getBeadPos(3) == WHITE)
                && (WinPeg[0][3].getBeadPos(0) == BLACK
                || WinPeg[1][2].getBeadPos(1) == BLACK
                || WinPeg[2][1].getBeadPos(2) == BLACK
                || WinPeg[3][0].getBeadPos(3) == BLACK))) {
            count++;
        }
        if (count == 76) {
            System.out.println("DRAW");
            return true;
        } else {
            return false;
        }

    }
}
/*the draw condition works by checking for individual lines, then adds to the counter
when a line is no longer possible to win, and there are 76 lines the game ends
in a draw*/