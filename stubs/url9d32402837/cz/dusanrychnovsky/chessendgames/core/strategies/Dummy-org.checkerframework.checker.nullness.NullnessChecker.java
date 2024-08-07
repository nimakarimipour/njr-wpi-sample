package cz.dusanrychnovsky.chessendgames.core.strategies;

import cz.dusanrychnovsky.chessendgames.core.Column;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Piece;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Row;
import cz.dusanrychnovsky.chessendgames.core.Situation;

@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Dummy extends Strategy {

    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Move chooseMove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Dummy this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player player) {
        Rook rook = getRook(player);
        Position from = situation.getPosition(rook);
        Column fromColumn = from.getColumn();
        Row fromRow = from.getRow();
        if (!from.getColumn().isLast()) {
            Position to = Position.get(fromColumn.next(), fromRow);
            if (!situation.isOccupied(to)) {
                return new Move(rook, from, to);
            }
        }
        if (!from.getColumn().isFirst()) {
            Position to = Position.get(fromColumn.previous(), fromRow);
            if (!situation.isOccupied(to)) {
                return new Move(rook, from, to);
            }
        }
        throw new IllegalArgumentException("Cannot move! Surrounded!");
    }

    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook getRook(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player player) {
        for (Piece piece : player.getPieces()) {
            if (piece instanceof Rook) {
                return (Rook) piece;
            }
        }
        throw new IllegalArgumentException("The given player does not have a rook available!");
    }
}
