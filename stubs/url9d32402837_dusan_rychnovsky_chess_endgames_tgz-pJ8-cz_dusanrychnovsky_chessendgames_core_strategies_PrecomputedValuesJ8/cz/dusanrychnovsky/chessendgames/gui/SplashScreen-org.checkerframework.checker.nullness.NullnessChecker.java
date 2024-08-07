package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class SplashScreen extends JFrame {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    @org.checkerframework.dataflow.qual.Impure
    public SplashScreen() {
        setUndecorated(true);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Loading application data. Please wait ...", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        setPreferredSize(new Dimension(300, 100));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @org.checkerframework.dataflow.qual.Impure
    public void close() {
        setVisible(false);
        dispose();
    }
}
