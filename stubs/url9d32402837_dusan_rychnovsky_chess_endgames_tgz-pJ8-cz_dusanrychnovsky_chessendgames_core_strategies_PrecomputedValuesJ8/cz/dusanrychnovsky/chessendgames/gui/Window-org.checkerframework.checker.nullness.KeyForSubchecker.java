package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import cz.dusanrychnovsky.chessendgames.core.Situation;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class Window extends JFrame {

    private static final   long serialVersionUID = 1L;

    private final  Board board;

    private final  StatusBar statusBar;

    /**
     * @param listener
     */
    @org.checkerframework.dataflow.qual.Impure
    public Window( MouseEventListener listener) {
        setTitle("Chess Endgames");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        board = new Board(listener);
        add(board, BorderLayout.CENTER);
        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * @param situation
     */
    @org.checkerframework.dataflow.qual.Impure
    public void setSituation( Window this,  Situation situation) {
        board.setSituation(situation);
    }

    /**
     * @param message
     */
    @org.checkerframework.dataflow.qual.Impure
    public void setStatus( Window this,  String message) {
        statusBar.setStatusMessage(message);
    }
}
