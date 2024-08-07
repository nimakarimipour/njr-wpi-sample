package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class StatusBar extends JPanel {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull JLabel label;

    /**
     */
    @org.checkerframework.dataflow.qual.Impure
    public StatusBar() {
        setPreferredSize(new Dimension(424, 18));
        setBackground(SystemColor.control);
        setLayout(new BorderLayout());
        label = new JLabel();
        add(label, BorderLayout.CENTER);
    }

    /**
     * @param message
     */
    @org.checkerframework.dataflow.qual.Impure
    public void setStatusMessage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String message) {
        label.setText(message);
    }
}
