package Cappuccino.Score4;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import ca.unbc.cpsc.cappuccino.Location;
import ca.unbc.cpsc.cappuccino.BeadColour;
import ca.unbc.cpsc.cappuccino.Colour;
import ca.unbc.cpsc.score4.exceptions.PlayerException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PegComponent extends JComponent {

    final int row;
    final int column;

    private BeadColour[] beads;
    private boolean full = false;
    private MousePressListener listener;
    private Referee referee = Referee.getInstance();
    private boolean playerTurn = true;
    private boolean lock;

    PegComponent(int x, int y) {
        this.lock = false;
        beads = new BeadColour[4];
        listener = new MousePressListener(this, beads, referee);
        this.addMouseListener(listener);
        row = x;
        column = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRoundRect(3, 0, 8, 75, 20, 20);
        for (int i = 0; i < beads.length; i++) {
            if (!(beads[i] == null)) {
                switch (beads[i]) {
                    case BLACK:
                        g.setColor(Color.black);
                        g.fillOval(0, 80 - 15 * (i + 1), 15, 15);
                        break;
                    case WHITE:
                        g.setColor(Color.white);
                        g.fillOval(0, 80 - 15 * (i + 1), 15, 15);
                        break;
                }
            }
        }
    }

    public void addBead(Colour opponent) {
        for (int i = 0; i < beads.length; i++) {
            if (!((beads[i] == BeadColour.BLACK) || (beads[i] == BeadColour.WHITE))) {
                if (opponent.isBlack()) {
                    beads[i] = BeadColour.BLACK;
                } else {
                    beads[i] = BeadColour.WHITE;
                }
                repaint();
                break;
            }
        }
    }
    //adds bead 

    public BeadColour getBeadPos(int i) { //sends the bead at bead array position i
        return beads[i];
    }
    
    public void validateFull() { //validate if peg has been filled
        if (!(beads[3] == null)) {
            full = true;
        }
    }

    public boolean getFull() {
        return full;
    }
    //returns full

    public void reset() {
        for (int i = 0; i < beads.length; i++) {
            beads[i] = null;
        }
        repaint();
        full = false;
        lock = false;
    }
    
    public void setLock(boolean b) {
        lock = b;
    }
    //sets lock
    public boolean getLock() {
        return lock;
    }
    //gets lock
    class MousePressListener implements MouseListener {

        BeadColour[] beads;
        PegComponent thisPeg;
        Referee referee;
        //basic mouse listener
        MousePressListener(PegComponent peg, BeadColour[] b, Referee r) {
            beads = b;
            thisPeg = peg;
            referee = r;
        }

        @Override
        public void mouseReleased(MouseEvent event) {
        }
        //basic mouse events
        @Override
        public void mouseClicked(MouseEvent event) {
            if (!lock && !full) { //dont accept clicks if the peg is already full or it isn't the players turn
                for (int i = 0; i < beads.length; i++) {
                    if (beads[i] == null) {
                        beads[i] = Player.getColour();
                        thisPeg.repaint();
                        thisPeg.validateFull();
                        try {
                            referee.playerTurn(new Location(thisPeg.row, thisPeg.column));
                        } catch (PlayerException ex) {
                            Logger.getLogger(PegComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void
                mouseExited(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

    }
}
