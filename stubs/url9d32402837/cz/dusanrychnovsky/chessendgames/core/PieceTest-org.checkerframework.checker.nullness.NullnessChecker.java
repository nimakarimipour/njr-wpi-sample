package cz.dusanrychnovsky.chessendgames.core;

import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public abstract class PieceTest {

    @org.checkerframework.dataflow.qual.Pure
    protected  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean containsPosition(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull List<Move> moves, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
        for (Move move : moves) {
            if (position.equals(move.getTo())) {
                return true;
            }
        }
        return false;
    }
}
