package cz.dusanrychnovsky.chessendgames.core;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class KingTest extends PieceTest {

    private static final  Player player = Player.get(Color.WHITE);

    private static final  King king = new King(player);

    @org.checkerframework.dataflow.qual.Impure
    public void generatesCorrectSetOfMovesWhenLocatedInCenterOfTheBoard() {
        Position position = Position.get(Column.CC, Row.R3);
        List<Move> result = king.generateMoves(position);
        assertEquals(8, result.size());
        assertTrue(containsPosition(result, Position.get(Column.CB, Row.R4)));
        assertTrue(containsPosition(result, Position.get(Column.CC, Row.R4)));
        assertTrue(containsPosition(result, Position.get(Column.CD, Row.R4)));
        assertTrue(containsPosition(result, Position.get(Column.CD, Row.R3)));
        assertTrue(containsPosition(result, Position.get(Column.CD, Row.R2)));
        assertTrue(containsPosition(result, Position.get(Column.CC, Row.R2)));
        assertTrue(containsPosition(result, Position.get(Column.CB, Row.R2)));
        assertTrue(containsPosition(result, Position.get(Column.CB, Row.R3)));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void generatesCorrectSetOfMovesWhenLocatedAtABoundaryColumnOfTheBoard() {
        Position position = Position.get(Column.CH, Row.R5);
        List<Move> result = king.generateMoves(position);
        assertEquals(5, result.size());
        assertTrue(containsPosition(result, Position.get(Column.CG, Row.R6)));
        assertTrue(containsPosition(result, Position.get(Column.CH, Row.R6)));
        assertTrue(containsPosition(result, Position.get(Column.CH, Row.R4)));
        assertTrue(containsPosition(result, Position.get(Column.CG, Row.R4)));
        assertTrue(containsPosition(result, Position.get(Column.CG, Row.R5)));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void generatesCorrectSetOfMovesWhenLocatedAtABoundaryRowOfTheBoard() {
        Position position = Position.get(Column.CB, Row.R1);
        List<Move> result = king.generateMoves(position);
        assertEquals(5, result.size());
        assertTrue(containsPosition(result, Position.get(Column.CA, Row.R2)));
        assertTrue(containsPosition(result, Position.get(Column.CB, Row.R2)));
        assertTrue(containsPosition(result, Position.get(Column.CC, Row.R2)));
        assertTrue(containsPosition(result, Position.get(Column.CC, Row.R1)));
        assertTrue(containsPosition(result, Position.get(Column.CA, Row.R1)));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void generatesCorrectSetOfMovesWhenLocatedInACornerTheBoard() {
        Position position = Position.get(Column.CA, Row.R8);
        List<Move> result = king.generateMoves(position);
        assertEquals(3, result.size());
        assertTrue(containsPosition(result, Position.get(Column.CB, Row.R8)));
        assertTrue(containsPosition(result, Position.get(Column.CB, Row.R7)));
        assertTrue(containsPosition(result, Position.get(Column.CA, Row.R7)));
    }
}
