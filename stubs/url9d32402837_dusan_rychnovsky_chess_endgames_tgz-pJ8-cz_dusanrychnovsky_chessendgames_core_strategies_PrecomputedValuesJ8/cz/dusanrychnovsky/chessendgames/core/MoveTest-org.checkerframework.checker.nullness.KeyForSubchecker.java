package cz.dusanrychnovsky.chessendgames.core;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class MoveTest {

    private  King blackKing;

    private  King whiteKing;

    private  Rook whiteRook;

    @org.checkerframework.dataflow.qual.Impure
    public void setUp() {
        Player blackPlayer = Player.get(Color.BLACK);
        blackPlayer.removeAllPieces();
        blackKing = new King(blackPlayer);
        Player whitePlayer = Player.get(Color.WHITE);
        whitePlayer.removeAllPieces();
        whiteKing = new King(whitePlayer);
        whiteRook = new Rook(whitePlayer);
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aRookCannotCrossItsOwnKing() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CE, Row.R5));
        situation.addPiece(whiteKing, Position.get(Column.CD, Row.R3));
        situation.addPiece(whiteRook, Position.get(Column.CF, Row.R3));
        Move move = new Move(whiteRook, Position.get(Column.CF, Row.R3), Position.get(Column.CB, Row.R3));
        assertFalse(move.isValid(situation));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aKingCannotMoveNearTheOtherKing() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CE, Row.R5));
        situation.addPiece(whiteKing, Position.get(Column.CD, Row.R3));
        situation.addPiece(whiteRook, Position.get(Column.CF, Row.R3));
        Move move = new Move(whiteKing, Position.get(Column.CD, Row.R3), Position.get(Column.CD, Row.R4));
        assertFalse(move.isValid(situation));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aKingCannotTakeTheRookIfHeWouldEndUpNearTheOtherKing() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CA, Row.R8));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CB, Row.R7));
        Move move = new Move(blackKing, Position.get(Column.CA, Row.R8), Position.get(Column.CB, Row.R7));
        assertFalse(move.isValid(situation));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aKingCannotBeExposedToAttack() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CE, Row.R5));
        situation.addPiece(whiteKing, Position.get(Column.CD, Row.R3));
        situation.addPiece(whiteRook, Position.get(Column.CB, Row.R4));
        Move move = new Move(blackKing, Position.get(Column.CE, Row.R5), Position.get(Column.CE, Row.R4));
        assertFalse(move.isValid(situation));
    }
}
