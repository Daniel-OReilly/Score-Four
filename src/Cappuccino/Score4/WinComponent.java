/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cappuccino.Score4;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author leary
 */
public class WinComponent extends JComponent {

    private String winner = "";
    private boolean victory = false;
    Font stringFont = new Font( "SansSerif", Font.PLAIN, 30 );

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.magenta);
        g.setFont(stringFont);
        if (victory) { //once the game is over draw the string
            g.drawString(winner + " has won", 0, 150);
        }
    }

    public void setWinner(String s) {
        winner = s;
        victory = true;
        repaint();
    }
    public void reset(){
    victory = false;
    }
}
