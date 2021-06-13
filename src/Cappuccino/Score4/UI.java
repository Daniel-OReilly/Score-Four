package Cappuccino.Score4;

import static java.awt.BorderLayout.*;
import javax.swing.*;
import static javax.swing.SwingUtilities.invokeLater;
import ca.unbc.cpsc.cappuccino.BeadColour;
import ca.unbc.cpsc.cappuccino.Opponent;
import ca.unbc.cpsc.score4.exceptions.PlayerException;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;

public class UI {

    private Board board;
    private static UI instance = null;
    private JFrame jf;
    private boolean willReset = false;
    private boolean ready = false;
    private JLabel currentTurn = new JLabel("White");
    private Opponent ai = Opponent.getAI();
    private Referee referee;

    protected UI(Referee r) { //singleton
        referee = r;
    }

    private void runGame() {
        jf = new JFrame("Score4");
        JPanel bottom = new JPanel();
        JButton reset = new JButton("Reset");
        currentTurn = new JLabel("     White     ");
        TitledBorder title = new TitledBorder("Current Turn");
        title.setTitleJustification(TitledBorder.CENTER);
        currentTurn.setBorder(title);
        currentTurn.setPreferredSize(new Dimension(125,35)); //creating currentTurn button
        currentTurn.setHorizontalAlignment(SwingConstants.CENTER); //and adding titles
        bottom.add(currentTurn);
        bottom.add(reset);
        
        reset.addActionListener(ae -> { //adding the popup on reset button
            invokeLater(() -> confirm());
        });
        jf.add(bottom, SOUTH);
        jf.add(board.getPanel());
        
        
        jf.setSize(500, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocation(600, 300);
        jf.setVisible(true);
        ready = true; //notifying referee that colour has been chosen and game is ready to start
    }

    private void selection() {
        JFrame frame = new JFrame("Game Setup");
        
        JRadioButton white = new JRadioButton("White");
        JRadioButton black = new JRadioButton("Black"); //radio colour selections
        ButtonGroup colourSelection = new ButtonGroup();
        colourSelection.add(white);
        white.setSelected(true); //default on white
        colourSelection.add(black);
       
        JPanel popup = new JPanel();
        JButton start = new JButton("Start");
       
        start.addActionListener(ae -> {
            if (black.isSelected()) {
                Player.setColour(BeadColour.BLACK);
            } else {
                Player.setColour(BeadColour.WHITE);
            }
            try {
                ai.startGameAs(Referee.beadColour(Player.getColour()));
            } catch (PlayerException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            board = Board.getInstance();
            board.reset();
            invokeLater(() -> runGame());
            frame.dispose();

        });
        popup.add(white);
        popup.add(black);
        popup.add(start);
        frame.add(popup);
        frame.setLocation(750, 500);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static UI getInstance(Referee r) { //creating singleton
        if (instance == null) {
            instance = new UI(r);
        }
        return instance;
    }

    public void start() {
        invokeLater(() -> selection()); //ui start method
    }

    private void close() {
        jf.dispose();
    }

    private void confirm() {
        JFrame confirm = new JFrame("Confirmation");
        JPanel buttons = new JPanel();
        JButton yes = new JButton("Yes");
        yes.addActionListener(ae -> { //starting the program over again
            try {
                ready = false;
                close();
                board.reset();
                ai.reset();
                start();
                referee.reset();
                confirm.dispose(); //closing the confirm box
            } catch (PlayerException ex) {
            }
        });
        JButton cancel = new JButton("No");
        cancel.addActionListener(ae -> confirm.dispose()); //no button does nothing but close the confirm box
        buttons.add(cancel);
        buttons.add(yes);
        JLabel sure = new JLabel("Are you sure you would like to reset?");
       
        confirm.add(sure);
        confirm.add(buttons, SOUTH);
        confirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        confirm.pack();
        confirm.setLocation(750, 500);
        confirm.setVisible(true);
    }

    public boolean getReady() { //getter method for ready
        return ready;
    }
    public void changeTurn(String b){ //changing the string in the box
    currentTurn.setText(b);
    currentTurn.repaint();
    }
}
