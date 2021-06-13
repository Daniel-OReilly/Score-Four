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
public class Location implements ca.unbc.cpsc.score4.interfaces.Location {

    private int[] location = new int[2];

    public Location(int row, int column) { //need to add MAX_ROW/COLUMN in here somewhere i guess
        location[0] = row;
        location[1] = column;
    }

    @Override
    public int getRow() {
       return location[0];
    }

    @Override
    public int getColumn() {
        return location[1];
    }

}
