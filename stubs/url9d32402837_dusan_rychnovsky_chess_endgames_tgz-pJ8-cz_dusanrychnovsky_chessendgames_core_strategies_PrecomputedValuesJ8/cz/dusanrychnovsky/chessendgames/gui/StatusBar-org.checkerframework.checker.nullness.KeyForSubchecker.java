package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class StatusBar extends JPanel {

    private static final   long serialVersionUID = 1L;

    private final  JLabel label;

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
    public void setStatusMessage( StatusBar this,  String message) {
        label.setText(message);
    }
}
