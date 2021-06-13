
package Cappuccino.Score4;

import javax.swing.*;
import ca.unbc.cpsc.cappuccino.*;

public class Board {

    private static Board instance = null;
    private PegComponent first;
    private PegComponent second;
    private PegComponent third;
    private PegComponent fourth;
    private PegComponent first2;
    private PegComponent second2;
    private PegComponent third2;
    private PegComponent fourth2;
    private PegComponent first3;
    private PegComponent second3;
    private PegComponent third3;
    private PegComponent fourth3;
    private PegComponent first4;
    private PegComponent second4;
    private PegComponent third4;
    private PegComponent fourth4;
    private PegComponent[][] pegArray;
    private WinComponent winComponent;
    private boolean win = false;

    JPanel jf;

    protected Board() { //singleton

        first = new PegComponent(0, 0);
        second = new PegComponent(0, 1);
        third = new PegComponent(0, 2);
        fourth = new PegComponent(0, 3);
        first2 = new PegComponent(1, 0);
        second2 = new PegComponent(1, 1);
        third2 = new PegComponent(1, 2);
        fourth2 = new PegComponent(1, 3);
        first3 = new PegComponent(2, 0);
        second3 = new PegComponent(2, 1);
        third3 = new PegComponent(2, 2);
        fourth3 = new PegComponent(2, 3);
        first4 = new PegComponent(3, 0);
        second4 = new PegComponent(3, 1);
        third4 = new PegComponent(3, 2);
        fourth4 = new PegComponent(3, 3);
        winComponent = new WinComponent();
        pegArrayInit();

    }

    public JPanel getPanel() {
        BoardComponent bottom = new BoardComponent();
        jf = new JPanel();
        bottom.setBounds(0, 0, 500, 500);
        first.setBounds(100, 50, 90, 100);
        second.setBounds(200, 50, 90, 100);
        third.setBounds(300, 50, 90, 100);
        fourth.setBounds(400, 50, 90, 100);
        first2.setBounds(100, 150, 90, 100);
        second2.setBounds(200, 150, 90, 100);
        third2.setBounds(300, 150, 90, 100);
        fourth2.setBounds(400, 150, 90, 100);
        first3.setBounds(100, 250, 90, 100);
        second3.setBounds(200, 250, 90, 100);
        third3.setBounds(300, 250, 90, 100);
        fourth3.setBounds(400, 250, 90, 100);
        first4.setBounds(100, 350, 90, 100);
        second4.setBounds(200, 350, 90, 100);
        third4.setBounds(300, 350, 90, 100);
        fourth4.setBounds(400, 350, 90, 100);
        winComponent.setBounds(140, 100, 500, 200);
        jf.setLayout(null);
        jf.add(winComponent);
        jf.add(first);
        jf.add(second); //adding all the pegs to the board
        jf.add(third);
        jf.add(fourth);
        jf.add(first2);
        jf.add(second2);
        jf.add(third2);
        jf.add(fourth2);
        jf.add(first3);
        jf.add(second3);
        jf.add(third3);
        jf.add(fourth3);
        jf.add(first4);
        jf.add(second4);
        jf.add(third4);
        jf.add(fourth4);
        jf.add(bottom); //needs to be last
        jf.repaint();
        return jf;
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    private void pegArrayInit() {
        pegArray = new PegComponent[4][4];
        pegArray[0][0] = first;
        pegArray[0][1] = second;
        pegArray[0][2] = third;
        pegArray[0][3] = fourth;
        pegArray[1][0] = first2;
        pegArray[1][1] = second2;
        pegArray[1][2] = third2;
        pegArray[1][3] = fourth2;
        pegArray[2][0] = first3;
        pegArray[2][1] = second3;
        pegArray[2][2] = third3;
        pegArray[2][3] = fourth3;
        pegArray[3][0] = first4;
        pegArray[3][1] = second4;
        pegArray[3][2] = third4;
        pegArray[3][3] = fourth4;
    }

    public void reset() {
        for (int i = 0; i < pegArray.length; i++) {
            for (int j = 0; j < pegArray[0].length; j++) {
                if (pegArray[i][j] != null) {
                    pegArray[i][j].reset();
                }
            }
        }
        winComponent.reset();
    } //will reset the game

    public void placeBead(Location l) {
        pegArray[l.getRow()][l.getColumn()].addBead((Colour) Opponent.getColour());
    } //places bead based off given location

    public void lockPegs(boolean b) {
        for (int i = 0; i < pegArray.length; i++) {
            for (int j = 0; j < pegArray[0].length; j++) {
                pegArray[i][j].setLock(b);
            }
        }
    } //sets pegs to lock based off boolean

    public PegComponent[][] getPegArray() {
        return pegArray;
    }//get method
public void setWin(String b){
winComponent.setWinner(b);
jf.repaint();
}
}//sets winner
