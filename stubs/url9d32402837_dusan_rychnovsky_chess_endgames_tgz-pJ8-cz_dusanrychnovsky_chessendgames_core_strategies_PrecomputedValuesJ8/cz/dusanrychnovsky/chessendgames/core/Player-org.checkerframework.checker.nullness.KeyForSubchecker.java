package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class Player implements Serializable {

    private static final   long serialVersionUID = 1L;

    public enum Color {

        BLACK, WHITE
    }

    private static final  Player whitePlayer = new Player(Color.WHITE);

    private static final  Player blackPlayer = new Player(Color.BLACK);

    static {
        whitePlayer.setOpponent(blackPlayer);
        blackPlayer.setOpponent(whitePlayer);
    }

    private final  Color color;

    private final  List<Piece> pieces = new ArrayList<Piece>();

    private  King king = null;

    private  Player opponent = null;

    /**
     * @param color
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public static  Player get( Color color) {
        if (color.equals(Color.WHITE)) {
            return whitePlayer;
        }
        if (color.equals(Color.BLACK)) {
            return blackPlayer;
        }
        throw new IllegalArgumentException("Unexpected player color [" + color + "].");
    }

    /**
     * @param color
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    private Player( Color color) {
        this.color = color;
    }

    /**
     * @param opponent
     */
    @org.checkerframework.dataflow.qual.Impure
    private void setOpponent( Player this,  Player opponent) {
        if (this.opponent != null) {
            throw new IllegalArgumentException("Opponent has already been set.");
        }
        this.opponent = opponent;
    }

    /**
     * @param piece
     */
    @org.checkerframework.dataflow.qual.Impure
    public void addPiece( Player this,  Piece piece) {
        if (this != piece.getPlayer()) {
            throw new IllegalArgumentException("Cannot add another player's piece.");
        }
        if (piece instanceof King) {
            if (king != null) {
                throw new IllegalArgumentException("Cannot add more than one king.");
            }
            king = (King) piece;
        }
        pieces.add(piece);
    }

    /**
     */
    @org.checkerframework.dataflow.qual.Impure
    public void removeAllPieces( Player this) {
        pieces.clear();
        king = null;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  List<Piece> getPieces( Player this) {
        return Collections.unmodifiableList(pieces);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  King getKing( Player this) {
        return king;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  Player getOpponent( Player this) {
        return opponent;
    }

    @org.checkerframework.dataflow.qual.Pure
    public   int hashCode( Player this) {
        return color.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public   boolean equals( Player this,  Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        return color.equals(other.color);
    }

    @org.checkerframework.dataflow.qual.Pure
    public  String toString( Player this) {
        return color.toString();
    }
}
