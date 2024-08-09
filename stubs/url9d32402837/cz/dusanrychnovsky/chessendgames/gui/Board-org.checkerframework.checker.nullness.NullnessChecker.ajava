package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map.Entry;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import cz.dusanrychnovsky.chessendgames.core.Column;
import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Piece;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Row;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Board extends JPanel implements MouseListener {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int SQUARE_WIDTH = 53;

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int SQUARE_HEIGHT = 53;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Image board;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Image blackKing;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Image blackRook;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Image whiteKing;

    private final @org.checkerframework.checker.initialization.qual.UnderInitialization(java.lang.Object.class) @org.checkerframework.checker.nullness.qual.NonNull MouseEventListener listener;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Situation currentSituation = null;

    /**
     * @param initialSituation
     * @param listener
     */
    @org.checkerframework.dataflow.qual.Impure
    public Board(@org.checkerframework.checker.initialization.qual.UnderInitialization(java.lang.Object.class) @org.checkerframework.checker.nullness.qual.NonNull MouseEventListener listener) {
        this.listener = listener;
        initPanel();
        board = loadImage("empty-board.png");
        blackKing = loadImage("black-king.png");
        blackRook = loadImage("black-rook.png");
        whiteKing = loadImage("white-king.png");
    }

    /**
     */
    @org.checkerframework.dataflow.qual.Impure
    private void initPanel() {
        setPreferredSize(new Dimension(424, 424));
        addMouseListener(this);
    }

    /**
     * @param imagePath
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Image loadImage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String imagePath) {
        ImageIcon boardIcon = new ImageIcon(this.getClass().getResource(imagePath));
        return boardIcon.getImage();
    }

    /**
     * @param situation
     */
    @org.checkerframework.dataflow.qual.Impure
    public void setSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) {
        this.currentSituation = situation;
    }

    @org.checkerframework.dataflow.qual.Impure
    public void paint(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Board this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintBoard(g2d);
        if (currentSituation != null) {
            paintSituation(g2d, currentSituation);
        }
    }

    /**
     * @param g2d
     */
    @org.checkerframework.dataflow.qual.Impure
    private void paintBoard(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Graphics2D g2d) {
        g2d.drawImage(board, 0, 0, null);
    }

    /**
     * @param g2d
     * @param situation
     */
    @org.checkerframework.dataflow.qual.Impure
    private void paintSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Graphics2D g2d, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) {
        for (Entry<Piece, Position> entry : situation) {
            paintPiece(g2d, entry.getKey(), entry.getValue());
        }
    }

    /**
     * @param g2d
     * @param piece
     * @param position
     */
    @org.checkerframework.dataflow.qual.Impure
    private void paintPiece(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Graphics2D g2d, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Piece piece, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position position) {
        int posX = getPosX(position.getColumn());
        int posY = getPosY(position.getRow());
        Image image = getImage(piece);
        g2d.drawImage(image, posX, posY, null);
    }

    /**
     * @param piece
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Image getImage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Piece piece) {
        if (piece instanceof Rook) {
            return blackRook;
        }
        if (piece instanceof King) {
            Player player = piece.getPlayer();
            if (player.equals(Player.get(Color.BLACK))) {
                return blackKing;
            }
            if (player.equals(Player.get(Color.WHITE))) {
                return whiteKing;
            }
        }
        throw new IllegalArgumentException("Unknown piece [" + piece + "].");
    }

    /**
     * @param row
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int getPosY(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row row) {
        return row.ordinal() * SQUARE_HEIGHT;
    }

    /**
     * @param column
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int getPosX(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column column) {
        return column.ordinal() * SQUARE_HEIGHT;
    }

    @org.checkerframework.dataflow.qual.Impure
    public void mouseClicked(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Board this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MouseEvent e) {
        Point point = e.getPoint();
        double posX = point.getX();
        Column column = getColumn(posX);
        double posY = point.getY();
        Row row = getRow(posY);
        Position position = Position.get(column, row);
        listener.onMouseClicked(position);
    }

    /**
     * @param posX
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column getColumn( @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull double posX) {
        int ordinal = (int) posX / SQUARE_WIDTH;
        return Column.get(ordinal);
    }

    /**
     * @param posY
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row getRow( @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull double posY) {
        int ordinal = (int) posY / SQUARE_HEIGHT;
        return Row.get(ordinal);
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mouseEntered(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Board this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MouseEvent arg0) {
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mouseExited(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Board this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MouseEvent arg0) {
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mousePressed(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Board this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MouseEvent arg0) {
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mouseReleased(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Board this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MouseEvent arg0) {
    }
}
