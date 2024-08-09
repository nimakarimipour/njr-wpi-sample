package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public abstract class Piece implements Serializable {

    private static final   long serialVersionUID = 1L;

    private final  Player player;

    /**
     * @param player
     */
    @org.checkerframework.dataflow.qual.Impure
    public Piece( Player player) {
        this.player = player;
        player.addPiece(this);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  Player getPlayer( Piece this) {
        return player;
    }

    /**
     * Returns a list of all moves that the represented piece could do from the
     * given position if the board was otherwise empty.
     *
     * @param position
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public abstract  List<Move> generateMoves( Piece this,  Position position);

    @org.checkerframework.dataflow.qual.Pure
    public   int hashCode( Piece this) {
        return player.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public   boolean equals( Piece this,  Object obj) {
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) obj;
        return player.equals(other.player);
    }
}
