package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Move implements Serializable {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Piece piece;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Range range;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Position from;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Position to;

    /**
     * @param piece
     * @param from
     * @param to
     */
    @org.checkerframework.dataflow.qual.Impure
    public Move(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Piece piece, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position from, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position to) {
        this.piece = piece;
        this.range = Range.get(from, to);
        this.from = from;
        this.to = to;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Piece getPiece() {
        return piece;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position getTo() {
        return to;
    }

    /**
     * Returns true if and only if the represented move is valid with regards
     * to the given situation.
     *
     * @param situation
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isValid(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) {
        // move is not valid unless it starts from the original piece position
        if (!from.equals(situation.getPosition(piece))) {
            return false;
        }
        // only correct moves with respect to the associated piece are valid
        List<Move> moves = piece.generateMoves(from);
        if (!moves.contains(this)) {
            return false;
        }
        // move is not valid if there is a piece standing at an intermediary
        // move position
        for (Position position : range) {
            if (position.equals(from) || position.equals(to)) {
                continue;
            }
            if (situation.isOccupied(position)) {
                return false;
            }
        }
        // move is not valid if there is that player's piece standing at the
        // final position
        if (situation.isOccupied(to)) {
            Piece targetPiece = situation.getPiece(to);
            if (piece.getPlayer().equals(targetPiece.getPlayer())) {
                return false;
            }
        }
        Situation result = Situation.get(situation, this);
        // a king cannot move into a position where he would face a check
        if (piece instanceof King && result.isCheck((King) piece)) {
            return false;
        }
        // move is not valid if the two kings end up at the same spot or at
        // adjacent locations
        if (piece instanceof King) {
            Position kingsLocation = to;
            King otherKing = piece.getPlayer().getOpponent().getKing();
            Position otherKingsLocation = situation.getPosition(otherKing);
            if (kingsLocation.equals(otherKingsLocation)) {
                return false;
            }
            if (kingsLocation.isNeighbour(otherKingsLocation)) {
                return false;
            }
        }
        return true;
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Move this) {
        return piece.hashCode() + from.hashCode() + to.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Move this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof Move)) {
            return false;
        }
        Move other = (Move) obj;
        return piece.equals(other.piece) && from.equals(other.from) && to.equals(other.to);
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String toString(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Move this) {
        return piece + " " + from + " - " + to;
    }
}
