package cz.dusanrychnovsky.chessendgames.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Rook extends Piece {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    /**
     * @param player
     */
    @org.checkerframework.dataflow.qual.Impure
    public Rook(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player player) {
        super(player);
    }

    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> generateMoves(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position from) {
        List<Move> result = new ArrayList<Move>();
        List<Move> horizontalMoves = generateHorizontalMoves(from);
        result.addAll(horizontalMoves);
        List<Move> verticalMoves = generateVerticalMoves(from);
        result.addAll(verticalMoves);
        return result;
    }

    /**
     * @param from
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> generateVerticalMoves(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position from) {
        List<Move> result = new ArrayList<Move>();
        for (Row row : Row.values()) {
            if (!row.equals(from.getRow())) {
                Position to = Position.get(from.getColumn(), row);
                result.add(new Move(this, from, to));
            }
        }
        return result;
    }

    /**
     * @param from
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> generateHorizontalMoves(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position from) {
        List<Move> result = new ArrayList<Move>();
        for (Column column : Column.values()) {
            if (!column.equals(from.getColumn())) {
                Position to = Position.get(column, from.getRow());
                result.add(new Move(this, from, to));
            }
        }
        return result;
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook this) {
        return super.hashCode() + "Rook".hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook this, @org.checkerframework.checker.initialization.qual.UnknownInitialization(java.lang.Object.class) @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof Rook)) {
            return false;
        }
        return super.equals(obj);
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String toString(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook this) {
        return getPlayer().toString() + " Rook";
    }
}
