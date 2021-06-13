
package Cappuccino.Score4;

import java.awt.*;
import javax.swing.*;

public class BoardComponent extends JComponent{
    
    @Override
    protected void paintComponent(Graphics g){ //this draws the bottom part of the board
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(0, 0, 500, 500, true);
       
    }
    
}
