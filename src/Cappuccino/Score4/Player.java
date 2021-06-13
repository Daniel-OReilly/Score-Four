
package Cappuccino.Score4;

import ca.unbc.cpsc.cappuccino.BeadColour;

public class Player {

    private static BeadColour myColour = null; //only holds player. peg does the actual placing.

    Player() {
    }

    static BeadColour getColour() {
        return myColour;
    }//gets colour

    static void setColour(BeadColour colour) {
        myColour = colour;
    }//sets colour
}
