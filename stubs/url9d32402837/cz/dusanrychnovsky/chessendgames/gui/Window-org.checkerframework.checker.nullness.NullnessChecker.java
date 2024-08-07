package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import cz.dusanrychnovsky.chessendgames.core.Situation;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Window extends JFrame {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private final @org.checkerframework.checker.initialization.qual.UnderInitialization(cz.dusanrychnovsky.chessendgames.gui.Board.class) @org.checkerframework.checker.nullness.qual.NonNull Board board;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull StatusBar statusBar;

    /**
     * @param listener
     */
    @org.checkerframework.dataflow.qual.Impure
    public Window(@org.checkerframework.checker.initialization.qual.UnderInitialization(java.lang.Object.class) @org.checkerframework.checker.nullness.qual.NonNull MouseEventListener listener) {
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
    public void setSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) {
        board.setSituation(situation);
    }

    /**
     * @param message
     */
    @org.checkerframework.dataflow.qual.Impure
    public void setStatus(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String message) {
        statusBar.setStatusMessage(message);
    }
}
