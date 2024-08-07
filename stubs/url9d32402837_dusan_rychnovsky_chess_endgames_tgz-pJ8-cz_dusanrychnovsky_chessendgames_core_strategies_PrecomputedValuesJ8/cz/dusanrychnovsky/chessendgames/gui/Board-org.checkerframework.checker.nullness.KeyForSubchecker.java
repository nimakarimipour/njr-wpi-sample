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
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class Board extends JPanel implements MouseListener {

    private static final   long serialVersionUID = 1L;

    private static final   int SQUARE_WIDTH = 53;

    private static final   int SQUARE_HEIGHT = 53;

    private final  Image board;

    private final  Image blackKing;

    private final  Image blackRook;

    private final  Image whiteKing;

    private final  MouseEventListener listener;

    private  Situation currentSituation = null;

    /**
     * @param initialSituation
     * @param listener
     */
    @org.checkerframework.dataflow.qual.Impure
    public Board( MouseEventListener listener) {
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
    private void initPanel( Board this) {
        setPreferredSize(new Dimension(424, 424));
        addMouseListener(this);
    }

    /**
     * @param imagePath
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private  Image loadImage( Board this,  String imagePath) {
        ImageIcon boardIcon = new ImageIcon(this.getClass().getResource(imagePath));
        return boardIcon.getImage();
    }

    /**
     * @param situation
     */
    @org.checkerframework.dataflow.qual.Impure
    public void setSituation( Board this,  Situation situation) {
        this.currentSituation = situation;
    }

    @org.checkerframework.dataflow.qual.Impure
    public void paint( Board this,  Graphics g) {
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
    private void paintBoard( Board this,  Graphics2D g2d) {
        g2d.drawImage(board, 0, 0, null);
    }

    /**
     * @param g2d
     * @param situation
     */
    @org.checkerframework.dataflow.qual.Impure
    private void paintSituation( Board this,  Graphics2D g2d,  Situation situation) {
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
    private void paintPiece( Board this,  Graphics2D g2d,  Piece piece,  Position position) {
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
    private  Image getImage( Board this,  Piece piece) {
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
    private   int getPosY( Board this,  Row row) {
        return row.ordinal() * SQUARE_HEIGHT;
    }

    /**
     * @param column
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private   int getPosX( Board this,  Column column) {
        return column.ordinal() * SQUARE_HEIGHT;
    }

    @org.checkerframework.dataflow.qual.Impure
    public void mouseClicked( Board this,  MouseEvent e) {
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
    public  Column getColumn( Board this,   double posX) {
        int ordinal = (int) posX / SQUARE_WIDTH;
        return Column.get(ordinal);
    }

    /**
     * @param posY
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  Row getRow( Board this,   double posY) {
        int ordinal = (int) posY / SQUARE_HEIGHT;
        return Row.get(ordinal);
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mouseEntered( Board this,  MouseEvent arg0) {
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mouseExited( Board this,  MouseEvent arg0) {
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mousePressed( Board this,  MouseEvent arg0) {
    }

    @org.checkerframework.dataflow.qual.SideEffectFree
    public void mouseReleased( Board this,  MouseEvent arg0) {
    }
}
