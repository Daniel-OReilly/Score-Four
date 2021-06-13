
package ca.unbc.cpsc.cappuccino;

import ca.unbc.cpsc.score4.enums.GameOverStatus;
import ca.unbc.cpsc.score4.exceptions.PlayerException;
import ca.unbc.cpsc.score4.interfaces.Colour;
import java.util.Random;

public class Opponent implements ca.unbc.cpsc.score4.interfaces.Player {

    int myId;
    private static Opponent instance;
    private int[][][] locations = new int[4][4][4]; //ai beads are 2 and player is 1, empty board is 0
    static Colour myColour;
    private Location chosenLocation;

    protected Opponent() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                for (int k = 0; k < locations[0][0].length; k++) {
                    locations[i][j][k] = 0;
                }
            }
        }
    }

    @Override
    public void reset() throws PlayerException {
        myColour = null;
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[0].length; j++) {
                for (int k = 0; k < locations[0][0].length; k++) {
                    locations[i][j][k] = 0;
                }
            }
        }
    }

    @Override
    public void startGameAs(Colour c) throws PlayerException {
        myColour = c;
    }

    @Override
    public void noteOpponentsId(int id) throws PlayerException {
        myId = id;
    }

    @Override
    public void opponentPlays(ca.unbc.cpsc.score4.interfaces.Location ell) throws PlayerException {
        for (int i = 0; i < locations[0][0].length; i++) {
            if (locations[ell.getRow()][ell.getColumn()][i] != 2 && locations[ell.getRow()][ell.getColumn()][i] != 1) {
                locations[ell.getRow()][ell.getColumn()][i] = 2;
                break;
            }
        }
    }

    @Override
    public Location requestMoveLocation() throws PlayerException {
        chosenLocation = decision(locations);
        if (locations[chosenLocation.getRow()][chosenLocation.getColumn()][3] != 0) {
            throw new PlayerException("Tried to place a bead in a full  peg");
        }
        for (int i = 0; i < locations[0][0].length; i++) {
            if (locations[chosenLocation.getRow()][chosenLocation.getColumn()][i] != 2 && locations[chosenLocation.getRow()][chosenLocation.getColumn()][i] != 1) {
                locations[chosenLocation.getRow()][chosenLocation.getColumn()][i] = 1;
                break;
            }
        }
        return chosenLocation;
    }

    @Override
    public Location retry() throws PlayerException {
        return decision(locations);
    }

    @Override
    public void noteGameOver(GameOverStatus whatHappened) throws PlayerException {
        
    }

    public static Opponent getAI() {
        if (instance == null) {
            instance = new Opponent();
        }
        return instance;
    }

    public static Colour getColour() {
        return myColour;
    }

    public static int Priority(String line, char c) { 
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == c) {
                count++;
            }
        }
        System.out.println("Count" + count);
        return count;
        //priority compares to see how many beads will be in the 'designated' line
        //This will be further explained in the design document
        //Checks for either '1' or '2' which is set to the AI and Player
    }

    public static Location ifAllElseFails(int[][][] peg) {
        int numb = new Random().nextInt(peg.length);
        int numb2 = new Random().nextInt(peg.length);
        return new Location(numb, numb);
        //will return a random peg
    }

    public Location decision(int[][][] PegNumb) {

        for (int z = 3; z >= 1; z--) { //sets priority of beads in a row
            if(z==3){
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {

                    if (((Priority(PegNumb[a][b][0] + ""
                            + PegNumb[a][b][1] + ""
                            + PegNumb[a][b][2] + ""
                            + PegNumb[a][b][3], '2') >= z) && (Priority(
                                    PegNumb[a][b][0] + ""
                                    + PegNumb[a][b][1] + ""
                                    + PegNumb[a][b][2] + ""
                                    + PegNumb[a][b][3], '1') == 0))
                            ||(Priority(PegNumb[a][b][0] + "" //it appears that if we add a off with opposite bead priorities
                            + PegNumb[a][b][1] + ""           //it will play offensively
                            + PegNumb[a][b][2] + ""
                            + PegNumb[a][b][3], '1') >= z) && (Priority(
                                    PegNumb[a][b][0] + ""
                                    + PegNumb[a][b][1] + ""
                                    + PegNumb[a][b][2] + ""
                                    + PegNumb[a][b][3], '2') == 0)) {
                        System.out.println("Individual Peg");
                        return new Location(a, b);

                    }
                }
            }
            }//checked pegs individually for a all vertical line
            for (int i = 0; i < 4; i++) {
                int b = 0;
                for (int x = 0; x < 4; x++) {
                    if (((Priority(PegNumb[i][b][x] + ""
                            + PegNumb[i][b + 1][x] + ""
                            + PegNumb[i][b + 2][x] + ""
                            + PegNumb[i][b + 3][x], '2') >= z) && (Priority(
                                    PegNumb[i][b][x] + ""
                                    + PegNumb[i][b + 1][x] + ""
                                    + PegNumb[i][b + 2][x] + ""
                                    + PegNumb[i][b + 3][x], '1') == 0))
                            ||((Priority(PegNumb[i][b][x] + ""
                            + PegNumb[i][b + 1][x] + ""
                            + PegNumb[i][b + 2][x] + ""
                            + PegNumb[i][b + 3][x], '1') >= z) && (Priority(
                                    PegNumb[i][b][x] + ""
                                    + PegNumb[i][b + 1][x] + ""
                                    + PegNumb[i][b + 2][x] + ""
                                    + PegNumb[i][b + 3][x], '2') == 0))) {

                        if (x == 0) {
                            if (PegNumb[i][b][x] == 0) {
                                System.out.println("Across");
                                return new Location(i, b);
                            } else if (PegNumb[i][b + 1][x] == 0) {
                                System.out.println("Across");
                                return new Location(i, b + 1);
                            } else if (PegNumb[i][b + 2][x] == 0) {
                                System.out.println("Across");
                                return new Location(i, b + 2);
                            } else if (PegNumb[i][b + 3][x] == 0) {
                                System.out.println("Across");
                                return new Location(i, b + 3);
                            }
                        } else {
                            if (PegNumb[i][b][x] == 0
                                    && PegNumb[i][b][x - 1] != 0) {
                                System.out.println("Across");
                                return new Location(i, b);
                            } else if (PegNumb[i][b + 1][x] == 0
                                    && PegNumb[i][b + 1][x - 1] != 0) {
                                System.out.println("Across");
                                return new Location(i, b + 1);
                            } else if (PegNumb[i][b + 2][x] == 0
                                    && PegNumb[i][b + 2][x - 1] != 0) {
                                System.out.println("Across");
                                return new Location(i, b + 2);
                            } else if (PegNumb[i][b + 3][x] == 0
                                    && PegNumb[i][b + 3][x - 1] != 0) {
                                System.out.println("Across");
                                return new Location(i, b + 3);
                            }

                        }
                    }
                }
            } //checks for possible wins across

            for (int i = 0; i < 4; i++) {
                int b = 0;
                for (int x = 0; x < 4; x++) {
                    if (((Priority(PegNumb[b][i][x] + ""
                            + PegNumb[b + 1][i][x] + ""
                            + PegNumb[b + 2][i][x] + ""
                            + PegNumb[b + 3][i][x], '2') >= z) && (Priority(
                                    PegNumb[b][i][x] + ""
                                    + PegNumb[b + 1][i][x] + ""
                                    + PegNumb[b + 2][i][x] + ""
                                    + PegNumb[b + 3][i][x], '1') == 0))
                            ||((Priority(PegNumb[b][i][x] + ""
                            + PegNumb[b + 1][i][x] + ""
                            + PegNumb[b + 2][i][x] + ""
                            + PegNumb[b + 3][i][x], '1') >= z) && (Priority(
                                    PegNumb[b][i][x] + ""
                                    + PegNumb[b + 1][i][x] + ""
                                    + PegNumb[b + 2][i][x] + ""
                                    + PegNumb[b + 3][i][x], '2') == 0))) {

                        if (x == 0) {
                            if (PegNumb[b][i][x] == 0) {
                                System.out.println("Down");
                                return new Location(b, i);
                            } else if (PegNumb[b + 1][i][x] == 0) {
                                System.out.println("Down");
                                return new Location(b + 1, i);
                            } else if (PegNumb[b + 2][i][x] == 0) {
                                System.out.println("Down");
                                return new Location(b + 2, i);
                            } else if (PegNumb[b + 3][i][x] == 0) {
                                System.out.println("Down");
                                return new Location(b + 3, i);
                            }
                        } else {
                            if (PegNumb[b][i][x] == 0
                                    && PegNumb[b][i][x - 1] != 0) {
                                System.out.println("Down");
                                return new Location(b, i);
                            } else if (PegNumb[b + 1][i][x] == 0
                                    && PegNumb[b + 1][i][x - 1] != 0) {
                                System.out.println("Down");
                                return new Location(b + 1, i);
                            } else if (PegNumb[b + 2][i][x] == 0
                                    && PegNumb[b + 2][i][x - 1] != 0) {
                                System.out.println("Down");
                                return new Location(b + 2, i);
                            } else if (PegNumb[b + 3][i][x] == 0
                                    && PegNumb[b + 3][i][x - 1] != 0) {
                                System.out.println("Down");
                                return new Location(b + 3, i);
                            }

                        }
                    }
                }
            } //check for possible wins in the "downwards" position

            for (int i = 0; i < 4; i++) {
                if (((Priority(PegNumb[0][0][i] + ""
                        + PegNumb[1][1][i] + ""
                        + PegNumb[2][2][i] + ""
                        + PegNumb[3][3][i], '2') >= z) && (Priority(
                                PegNumb[0][0][i] + ""
                                + PegNumb[1][1][i] + ""
                                + PegNumb[2][2][i] + ""
                                + PegNumb[3][3][i], '1') == 0))
                        ||((Priority(PegNumb[0][0][i] + ""
                        + PegNumb[1][1][i] + ""
                        + PegNumb[2][2][i] + ""
                        + PegNumb[3][3][i], '1') >= z) && (Priority(
                                PegNumb[0][0][i] + ""
                                + PegNumb[1][1][i] + ""
                                + PegNumb[2][2][i] + ""
                                + PegNumb[3][3][i], '2') == 0))) {
                    for (int x = 0; x < 4; x++) {
                        if (PegNumb[x][x][i] == 0) {
                            System.out.println("X-Top");
                            return new Location(x, x);
                        }
                    }
                }
                if (((Priority(PegNumb[0][3][i] + ""
                        + PegNumb[1][2][i] + ""
                        + PegNumb[2][1][i] + ""
                        + PegNumb[3][0][i], '2') >= z) && (Priority(
                                PegNumb[0][3][i] + ""
                                + PegNumb[1][2][i] + ""
                                + PegNumb[2][1][i] + ""
                                + PegNumb[3][0][i], '1') == 0))
                        ||((Priority(PegNumb[0][3][i] + ""
                        + PegNumb[1][2][i] + ""
                        + PegNumb[2][1][i] + ""
                        + PegNumb[3][0][i], '1') >= z) && (Priority(
                                PegNumb[0][3][i] + ""
                                + PegNumb[1][2][i] + ""
                                + PegNumb[2][1][i] + ""
                                + PegNumb[3][0][i], '2') == 0))) {
                    for (int x = 0, y = 3; x < 4; x++, y--) {
                        if (PegNumb[x][y][i] == 0) {
                            System.out.println("X-Top");
                            return new Location(x, y);
                        }
                    }
                }
            } //checks diagonal lines across the "top"

            for (int a = 0; a < 4; a++) {
                int b = 0;
                if (((Priority(PegNumb[a][b][3] + ""
                        + PegNumb[a][b + 1][2] + ""
                        + PegNumb[a][b + 2][1] + ""
                        + PegNumb[a][b + 3][0], '2') >= z) && (Priority(
                                PegNumb[a][b][3] + ""
                                + PegNumb[a][b + 1][2] + ""
                                + PegNumb[a][b + 2][1] + ""
                                + PegNumb[a][b + 3][0], '1') == 0))
                        ||((Priority(PegNumb[a][b][3] + ""
                        + PegNumb[a][b + 1][2] + ""
                        + PegNumb[a][b + 2][1] + ""
                        + PegNumb[a][b + 3][0], '1') >= z) && (Priority(
                                PegNumb[a][b][3] + ""
                                + PegNumb[a][b + 1][2] + ""
                                + PegNumb[a][b + 2][1] + ""
                                + PegNumb[a][b + 3][0], '2') == 0))) {
                    if (PegNumb[a][b][3] == 0
                            && PegNumb[a][b][2] != 0) {
                        System.out.println("X-Side");
                        return new Location(a, b);
                    } else if (PegNumb[a][b + 1][2] == 0
                            && PegNumb[a][b + 1][1] != 0) {
                        System.out.println("X-Side");
                        return new Location(a, b + 1);
                    } else if (PegNumb[a][b + 2][1] == 0
                            && PegNumb[a][b + 2][0] != 0) {
                        System.out.println("X-Side");
                        return new Location(a, b + 2);
                    } else if (PegNumb[a][b + 3][0] == 0) {
                        System.out.println("X-Side");
                        return new Location(a, b + 3);
                    }

                }

                if (((Priority(PegNumb[a][b][0] + ""
                        + PegNumb[a][b + 1][1] + ""
                        + PegNumb[a][b + 2][2] + ""
                        + PegNumb[a][b + 3][3], '2') >= z) && (Priority(
                                PegNumb[a][b][0] + ""
                                + PegNumb[a][b + 1][1] + ""
                                + PegNumb[a][b + 2][2] + ""
                                + PegNumb[a][b + 3][3], '1') == 0))
                        ||((Priority(PegNumb[a][b][0] + ""
                        + PegNumb[a][b + 1][1] + ""
                        + PegNumb[a][b + 2][2] + ""
                        + PegNumb[a][b + 3][3], '1') >= z) && (Priority(
                                PegNumb[a][b][0] + ""
                                + PegNumb[a][b + 1][1] + ""
                                + PegNumb[a][b + 2][2] + ""
                                + PegNumb[a][b + 3][3], '2') == 0))) {
                    if (PegNumb[a][b][0] == 0) {
                        System.out.println("X-Side");
                        return new Location(a, b);
                    } else if (PegNumb[a][b + 1][1] == 0
                            && PegNumb[a][b + 1][0] != 0) {
                        System.out.println("X-Side");
                        return new Location(a, b + 1);
                    } else if (PegNumb[a][b + 2][2] == 0
                            && PegNumb[a][b + 2][1] != 0) {
                        System.out.println("X-Side");
                        return new Location(a, b + 2);
                    } else if (PegNumb[a][b + 3][3] == 0
                            && PegNumb[a][b + 3][2] != 0) {
                        System.out.println("X-Side");
                        return new Location(a, b + 3);
                    }

                }
            } //checks for diagnol lines along an "X" position

            for (int i = 0; i < 4; i++) {
                int a = 0;
                if (((Priority(PegNumb[a][i][0] + ""
                        + PegNumb[a + 1][i][1] + ""
                        + PegNumb[a + 2][i][2] + ""
                        + PegNumb[a + 3][3], '2') >= z) && (Priority(
                                PegNumb[a][i][0] + ""
                                + PegNumb[a + 1][i][1] + ""
                                + PegNumb[a + 2][i][2] + ""
                                + PegNumb[a + 3][i][3], '1') == 0))
                        ||((Priority(PegNumb[a][i][0] + ""
                        + PegNumb[a + 1][i][1] + ""
                        + PegNumb[a + 2][i][2] + ""
                        + PegNumb[a + 3][3], '1') >= z) && (Priority(
                                PegNumb[a][i][0] + ""
                                + PegNumb[a + 1][i][1] + ""
                                + PegNumb[a + 2][i][2] + ""
                                + PegNumb[a + 3][i][3], '2') == 0))) {
                    if (PegNumb[a][i][0] == 0) {
                        System.out.println("X-Other");
                        return new Location(a, i);
                    } else if (PegNumb[a + 1][i][1] == 0
                            && PegNumb[a + 1][i][0] != 0) {
                        System.out.println("X-Other");
                        return new Location(a + 1, i);
                    } else if (PegNumb[a + 2][i][2] == 0
                            && PegNumb[a + 2][i][1] != 0) {
                        System.out.println("X-Other");
                        return new Location(a + 2, i);
                    } else if (PegNumb[a + 3][i][3] == 0
                            && PegNumb[a + 3][i][2] != 0) {
                        System.out.println("X-Other");
                        return new Location(a + 3, i);
                    }
                }

                if (((Priority(PegNumb[a][i][3] + ""
                        + PegNumb[a + 1][i][2] + ""
                        + PegNumb[a + 2][i][1] + ""
                        + PegNumb[a + 3][i][0], '2') >= z) && (Priority(
                                PegNumb[a][i][3] + ""
                                + PegNumb[a + 1][i][2] + ""
                                + PegNumb[a + 2][i][1] + ""
                                + PegNumb[a + 3][i][0], '1') == 0))
                        ||((Priority(PegNumb[a][i][3] + ""
                        + PegNumb[a + 1][i][2] + ""
                        + PegNumb[a + 2][i][1] + ""
                        + PegNumb[a + 3][i][0], '1') >= z) && (Priority(
                                PegNumb[a][i][3] + ""
                                + PegNumb[a + 1][i][2] + ""
                                + PegNumb[a + 2][i][1] + ""
                                + PegNumb[a + 3][i][0], '2') == 0))) {
                    if (PegNumb[a][i][3] == 0
                            && PegNumb[a][i][2] != 0) {
                        System.out.println("X-Other");
                        return new Location(a, i);
                    } else if (PegNumb[a + 1][i][2] == 0
                            && PegNumb[a + 1][i][1] != 0) {
                        System.out.println("X-Other");
                        return new Location(a + 1, i);
                    } else if (PegNumb[a + 2][i][1] == 0
                            && PegNumb[a + 2][i][0] != 0) {
                        System.out.println("X-Other");
                        return new Location(a + 2, i);
                    } else if (PegNumb[a + 3][i][0] == 0) {
                        System.out.println("X-Other");
                        return new Location(a + 3, i);
                    }
                }
            }//checks for diagnol in the "x" side, opposite above

            if (((Priority(PegNumb[0][0][0] + ""
                    + PegNumb[1][1][1] + ""
                    + PegNumb[2][2][2] + ""
                    + PegNumb[3][3][3], '2') >= z) && (Priority(
                            PegNumb[0][0][0] + "" + PegNumb[1][1][1]
                            + "" + PegNumb[2][2][2] + ""
                            + PegNumb[3][3][3], '1') == 0))
                    ||((Priority(PegNumb[0][0][0] + ""
                    + PegNumb[1][1][1] + ""
                    + PegNumb[2][2][2] + ""
                    + PegNumb[3][3][3], '1') >= z) && (Priority(
                            PegNumb[0][0][0] + "" + PegNumb[1][1][1]
                            + "" + PegNumb[2][2][2] + ""
                            + PegNumb[3][3][3], '2') == 0))) {

                if (PegNumb[0][0][0] == 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(0, 0);
                } else if (PegNumb[1][1][1] == 0
                        && PegNumb[1][1][0] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(1, 1);
                } else if (PegNumb[2][2][2] == 0
                        && PegNumb[2][2][1] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(2, 2);
                } else if (PegNumb[3][3][3] == 0
                        && PegNumb[3][3][2] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(3, 3);
                }
            } else if (((Priority(PegNumb[0][0][3] + ""
                    + PegNumb[1][1][2] + ""
                    + PegNumb[2][2][1] + ""
                    + PegNumb[3][3][0], '2') >= z) && (Priority(
                            PegNumb[0][0][3] + "" + PegNumb[1][1][2]
                            + "" + PegNumb[2][2][1] + ""
                            + PegNumb[3][3][0], '1') == 0))
                    ||((Priority(PegNumb[0][0][3] + ""
                    + PegNumb[1][1][2] + ""
                    + PegNumb[2][2][1] + ""
                    + PegNumb[3][3][0], '1') >= z) && (Priority(
                            PegNumb[0][0][3] + "" + PegNumb[1][1][2]
                            + "" + PegNumb[2][2][1] + ""
                            + PegNumb[3][3][0], '2') == 0))) {

                if (PegNumb[0][0][3] == 0
                        && PegNumb[0][0][3] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(0, 0);
                } else if (PegNumb[1][1][2] == 0
                        && PegNumb[1][1][1] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(1, 1);
                } else if (PegNumb[2][2][1] == 0
                        && PegNumb[2][2][0] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(2, 2);
                } else if (PegNumb[3][3][0] == 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(3, 3);
                }
            } else if (((Priority(PegNumb[0][3][0] + ""
                    + PegNumb[1][2][1] + ""
                    + PegNumb[2][1][2] + ""
                    + PegNumb[3][0][3], '2') >= z) && (Priority(
                            PegNumb[0][3][0] + "" + PegNumb[1][2][1]
                            + "" + PegNumb[2][1][2] + ""
                            + PegNumb[3][0][3], '1') == 0))
                    ||((Priority(PegNumb[0][3][0] + ""
                    + PegNumb[1][2][1] + ""
                    + PegNumb[2][1][2] + ""
                    + PegNumb[3][0][3], '1') >= z) && (Priority(
                            PegNumb[0][3][0] + "" + PegNumb[1][2][1]
                            + "" + PegNumb[2][1][2] + ""
                            + PegNumb[3][0][3], '2') == 0))) {

                if (PegNumb[0][3][0] == 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(0, 3);
                } else if (PegNumb[1][2][1] == 0
                        && PegNumb[1][2][0] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(1, 2);
                } else if (PegNumb[2][1][2] == 0
                        && PegNumb[2][1][1] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(2, 1);
                } else if (PegNumb[3][0][3] == 0
                        && PegNumb[3][0][2] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(3, 0);
                }
            } else if (((Priority(PegNumb[0][3][3] + ""
                    + PegNumb[1][2][2] + ""
                    + PegNumb[2][1][1] + ""
                    + PegNumb[3][0][0], '2') >= z) && (Priority(
                            PegNumb[0][3][3] + "" + PegNumb[1][2][2]
                            + "" + PegNumb[2][1][1] + ""
                            + PegNumb[3][0][0], '1') == 0))
                    ||((Priority(PegNumb[0][3][3] + ""
                    + PegNumb[1][2][2] + ""
                    + PegNumb[2][1][1] + ""
                    + PegNumb[3][0][0], '1') >= z) && (Priority(
                            PegNumb[0][3][3] + "" + PegNumb[1][2][2]
                            + "" + PegNumb[2][1][1] + ""
                            + PegNumb[3][0][0], '2') == 0))) {

                if (PegNumb[0][3][3] == 0
                        && PegNumb[0][3][2] != 0) {
                    System.out.println("Diagnal Angle");
                    return new Location(0, 3);
                } else if (PegNumb[1][2][2] == 0
                        && PegNumb[1][2][1] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(1, 2);
                } else if (PegNumb[2][1][1] == 0
                        && PegNumb[2][1][0] != 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(2, 1);
                } else if (PegNumb[3][0][0] == 0) {
                    System.out.println("Diagonal Angle");
                    return new Location(3, 0);
                }
            }//checks for diagonal line in the x direction

        }

        System.out.println("Random");
        return ifAllElseFails(PegNumb);
        //if all else fails it will return a random peg, useful if the AI starts
        /*keep everything within this bracket*/
    }
}
