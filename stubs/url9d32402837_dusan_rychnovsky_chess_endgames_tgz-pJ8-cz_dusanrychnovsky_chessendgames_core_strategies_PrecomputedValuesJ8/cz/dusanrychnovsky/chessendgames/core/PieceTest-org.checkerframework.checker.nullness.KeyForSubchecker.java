package cz.dusanrychnovsky.chessendgames.core;

import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public abstract class PieceTest {

    @org.checkerframework.dataflow.qual.Pure
    protected   boolean containsPosition( PieceTest this,  List<Move> moves,  Position position) {
        for (Move move : moves) {
            if (position.equals(move.getTo())) {
                return true;
            }
        }
        return false;
    }
}
