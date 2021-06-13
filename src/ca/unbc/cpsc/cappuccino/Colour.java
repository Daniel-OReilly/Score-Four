/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.unbc.cpsc.cappuccino;

/**
 *
 * @author leary
 */
public class Colour implements ca.unbc.cpsc.score4.interfaces.Colour {

    BeadColour myColour;

    public Colour(BeadColour b) {
        myColour = b;
    }

    @Override
    public boolean isBlack() {
        return myColour == BeadColour.BLACK;
    }

    @Override
    public boolean isWhite() {
        return myColour == BeadColour.WHITE;
    }

}
