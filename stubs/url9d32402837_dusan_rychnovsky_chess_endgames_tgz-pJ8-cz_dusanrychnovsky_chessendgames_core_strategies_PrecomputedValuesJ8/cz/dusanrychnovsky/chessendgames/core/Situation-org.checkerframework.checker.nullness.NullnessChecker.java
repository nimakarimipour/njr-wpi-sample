package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Situation implements Iterable<Entry<Piece, Position>>, Serializable {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Map<Piece, Position> pieces = new HashMap<Piece, Position>();

    /**
     * Returns the situation that is the result of performing the given move on
     * the given situation.
     *
     * @param originalSituation
     * @param move
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation get(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation originalSituation, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Move move) {
        Piece piece = move.getPiece();
        Position to = move.getTo();
        Situation result = new Situation();
        for (Entry<Piece, Position> entry : originalSituation.pieces.entrySet()) {
            if (piece.equals(entry.getKey())) {
                continue;
            }
            if (to.equals(entry.getValue())) {
                continue;
            }
            result.addPiece(entry.getKey(), entry.getValue());
        }
        result.addPiece(piece, to);
        return result;
    }

    /**
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public Situation() {
    }

    /**
     * @param other
     */
    @org.checkerframework.dataflow.qual.Impure
    public Situation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation other) {
        for (Entry<Piece, Position> entry : other) {
            pieces.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * @param piece
     * @param position
     */
    @org.checkerframework.dataflow.qual.Impure
    public void addPiece(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Piece piece, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
        if (pieces.containsKey(piece)) {
            throw new IllegalArgumentException("Piece [" + piece + "] already registered.");
        }
        if (pieces.containsValue(position)) {
            throw new IllegalArgumentException("Another piece already registered at position [" + position + "].");
        }
        pieces.put(piece, position);
    }

    /**
     * @param piece
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position getPosition(@org.checkerframework.checker.initialization.qual.UnknownInitialization(cz.dusanrychnovsky.chessendgames.core.Piece.class) @org.checkerframework.checker.nullness.qual.Nullable Piece piece) {
        if (!pieces.containsKey(piece)) {
            throw new IllegalArgumentException("Unknown piece [" + piece + "].");
        }
        return pieces.get(piece);
    }

    /**
     * @param position
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isOccupied(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
        for (Entry<Piece, Position> entry : pieces.entrySet()) {
            if (position.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param position
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Piece getPiece(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
        for (Entry<Piece, Position> entry : pieces.entrySet()) {
            if (position.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("No piece at position [" + position + "].");
    }

    /**
     * Returns true if and only if the given piece is on the board in the
     * represented situation.
     *
     * @param piece
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isActive(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Piece piece) {
        return pieces.containsKey(piece);
    }

    /**
     * Returns a list of all (mutually different) situations which can be
     * reached using a single valid move of the given player.
     *
     * @param player
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Situation> generateSuccessors(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Player player) {
        List<Situation> result = new ArrayList<Situation>();
        List<Move> validMoves = generateValidMoves(player);
        for (Move move : validMoves) {
            Situation successor = Situation.get(this, move);
            result.add(successor);
        }
        return result;
    }

    /**
     * @param player
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> generateValidMoves(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Player player) {
        List<Move> result = new ArrayList<Move>();
        for (Piece piece : player.getPieces()) {
            if (!isActive(piece)) {
                continue;
            }
            Position currPiecePosition = getPosition(piece);
            for (Move move : piece.generateMoves(currPiecePosition)) {
                if (move.isValid(this)) {
                    result.add(move);
                }
            }
        }
        return result;
    }

    /**
     * Returns true if and only the given king piece is in check in the
     * represented situation.
     *
     * @param king
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isCheck(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable King king) {
        // situation is a check to a king if and only if there exists a valid
        // move for any of the oponent's figures other than his king that would
        // end at the kings location
        Position kingsPosition = getPosition(king);
        Player otherPlayer = king.getPlayer().getOpponent();
        King otherKing = otherPlayer.getKing();
        for (Piece piece : otherPlayer.getPieces()) {
            if (!isActive(piece)) {
                continue;
            }
            if (piece.equals(otherKing)) {
                continue;
            }
            Position currPosition = getPosition(piece);
            List<Move> moves = piece.generateMoves(currPosition);
            for (Move move : moves) {
                if (move.getTo().equals(kingsPosition) && move.isValid(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if and only the given king piece is in stalemate in the
     * represented situation.
     *
     * @param king
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isStalemate(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable King king) {
        return !isCheck(king) && !canMove(king);
    }

    /**
     * Returns true if and only if the represented situation is a draw, i.e.
     * if only the kings remain active or if one of the kings is in stalemate.
     *
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isDraw() {
        if (onlyKingsRemainActive()) {
            return true;
        }
        for (King king : getKings()) {
            if (isStalemate(king)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean onlyKingsRemainActive() {
        for (Piece piece : pieces.keySet()) {
            if (!(piece instanceof King)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if and only the given king piece is in checkmate in the
     * represented situation.
     *
     * @param king
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isCheckmate(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable King king) {
        return isCheck(king) && !canMove(king);
    }

    /**
     * @param king
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean canMove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable King king) {
        Position from = pieces.get(king);
        List<Move> moves = king.generateMoves(from);
        for (Move move : moves) {
            if (move.isValid(this)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if and only if the given situation is final, i.e. if it is
     * either a draw or if one of the kings is in checkmate.
     *
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isFinal() {
        if (isDraw()) {
            return true;
        }
        for (King king : getKings()) {
            if (isCheckmate(king)) {
                return true;
            }
        }
        return false;
    }

    /**
     * If the represented situation is final, returns the appropriate game
     * result. Otherwise throws an {@link IllegalStateException}.
     *
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Result getResult() {
        if (isDraw()) {
            return new Draw();
        }
        for (King king : getKings()) {
            if (isCheckmate(king)) {
                Player winner = king.getPlayer().getOpponent();
                return new Win(winner);
            }
        }
        throw new UnsupportedOperationException("Non-final situations do not have results.");
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<King> getKings() {
        List<King> result = new ArrayList<King>();
        for (Piece piece : pieces.keySet()) {
            if (piece instanceof King) {
                result.add((King) piece);
            }
        }
        if (result.size() != 2) {
            throw new IllegalStateException("A king piece is missing or superfluous.");
        }
        return result;
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Iterator<Entry<Piece, Position>> iterator(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation this) {
        return pieces.entrySet().iterator();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation this) {
        return pieces.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof Situation)) {
            return false;
        }
        Situation other = (Situation) obj;
        return pieces.equals(other.pieces);
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String toString(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation this) {
        return pieces.toString();
    }
}
