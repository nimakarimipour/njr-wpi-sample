package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public abstract class Piece implements Serializable {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player player;

    /**
     * @param player
     */
    @org.checkerframework.dataflow.qual.Impure
    public Piece(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player player) {
        this.player = player;
        player.addPiece(this);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player getPlayer() {
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
    public abstract @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> generateMoves(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position position);

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Piece this) {
        return player.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Piece this, @org.checkerframework.checker.initialization.qual.UnknownInitialization(java.lang.Object.class) @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) obj;
        return player.equals(other.player);
    }
}
