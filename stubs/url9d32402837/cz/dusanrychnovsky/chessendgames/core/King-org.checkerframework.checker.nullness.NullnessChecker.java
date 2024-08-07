package cz.dusanrychnovsky.chessendgames.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class King extends Piece {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    /**
     * @param player
     */
    @org.checkerframework.dataflow.qual.Impure
    public King(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player player) {
        super(player);
    }

    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> generateMoves(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position from) {
        List<Move> result = new ArrayList<Move>();
        Column column = from.getColumn();
        Row row = from.getRow();
        Position to;
        if (!row.isFirst()) {
            if (!column.isFirst()) {
                to = Position.get(column.previous(), row.previous());
                result.add(new Move(this, from, to));
            }
            to = Position.get(column, row.previous());
            result.add(new Move(this, from, to));
            if (!column.isLast()) {
                to = Position.get(column.next(), row.previous());
                result.add(new Move(this, from, to));
            }
        }
        if (!column.isLast()) {
            to = Position.get(column.next(), row);
            result.add(new Move(this, from, to));
        }
        if (!row.isLast()) {
            if (!column.isLast()) {
                to = Position.get(column.next(), row.next());
                result.add(new Move(this, from, to));
            }
            to = Position.get(column, row.next());
            result.add(new Move(this, from, to));
            if (!column.isFirst()) {
                to = Position.get(column.previous(), row.next());
                result.add(new Move(this, from, to));
            }
        }
        if (!column.isFirst()) {
            to = Position.get(column.previous(), row);
            result.add(new Move(this, from, to));
        }
        return result;
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King this) {
        return super.hashCode() + "King".hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King this, @org.checkerframework.checker.initialization.qual.UnknownInitialization(java.lang.Object.class) @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof King)) {
            return false;
        }
        return super.equals(obj);
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String toString(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King this) {
        return getPlayer().toString() + " King";
    }
}
