package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Player implements Serializable {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    public enum Color {

        BLACK, WHITE
    }

    private static final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player whitePlayer = new Player(Color.WHITE);

    private static final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player blackPlayer = new Player(Color.BLACK);

    static {
        whitePlayer.setOpponent(blackPlayer);
        blackPlayer.setOpponent(whitePlayer);
    }

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Color color;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Piece> pieces = new ArrayList<Piece>();

    private @org.checkerframework.checker.initialization.qual.UnderInitialization(cz.dusanrychnovsky.chessendgames.core.Piece.class) @org.checkerframework.checker.nullness.qual.Nullable King king = null;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Player opponent = null;

    /**
     * @param color
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player get(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Color color) {
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
    private Player(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Color color) {
        this.color = color;
    }

    /**
     * @param opponent
     */
    @org.checkerframework.dataflow.qual.Impure
    private void setOpponent(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player opponent) {
        if (this.opponent != null) {
            throw new IllegalArgumentException("Opponent has already been set.");
        }
        this.opponent = opponent;
    }

    /**
     * @param piece
     */
    @org.checkerframework.dataflow.qual.Impure
    public void addPiece(@org.checkerframework.checker.initialization.qual.UnderInitialization(cz.dusanrychnovsky.chessendgames.core.Piece.class) @org.checkerframework.checker.nullness.qual.NonNull Piece piece) {
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
    public void removeAllPieces() {
        pieces.clear();
        king = null;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.UnderInitialization(cz.dusanrychnovsky.chessendgames.core.Piece.class) @org.checkerframework.checker.nullness.qual.Nullable King getKing() {
        return king;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Player getOpponent() {
        return opponent;
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player this) {
        return color.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        Player other = (Player) obj;
        return color.equals(other.color);
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String toString(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player this) {
        return color.toString();
    }
}
