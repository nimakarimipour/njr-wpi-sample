package cz.dusanrychnovsky.chessendgames.core;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class SituationTest {

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull King blackKing;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Player whitePlayer;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull King whiteKing;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Rook whiteRook;

    @org.checkerframework.dataflow.qual.Impure
    public void setUp() {
        Player blackPlayer = Player.get(Color.BLACK);
        blackPlayer.removeAllPieces();
        blackKing = new King(blackPlayer);
        whitePlayer = Player.get(Color.WHITE);
        whitePlayer.removeAllPieces();
        whiteKing = new King(whitePlayer);
        whiteRook = new Rook(whitePlayer);
    }

    // ========================================================================
    // MOVE EXECUTION
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void createsANewSituationResultingFromTheOriginalOneByApplyingTheMove() {
        Situation origSituation = new Situation();
        origSituation.addPiece(blackKing, Position.get(Column.CD, Row.R5));
        origSituation.addPiece(whiteKing, Position.get(Column.CF, Row.R3));
        origSituation.addPiece(whiteRook, Position.get(Column.CH, Row.R1));
        Move move = new Move(whiteRook, Position.get(Column.CH, Row.R1), Position.get(Column.CH, Row.R8));
        Situation newSituation = Situation.get(origSituation, move);
        assertEquals(Position.get(Column.CD, Row.R5), newSituation.getPosition(blackKing));
        assertEquals(Position.get(Column.CF, Row.R3), newSituation.getPosition(whiteKing));
        assertEquals(Position.get(Column.CH, Row.R8), newSituation.getPosition(whiteRook));
    }

    // ========================================================================
    // IS CHECK?
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsACheckIfTheKingIsAttackedByAnOpponentsFiggure() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CD, Row.R5));
        situation.addPiece(whiteKing, Position.get(Column.CF, Row.R3));
        situation.addPiece(whiteRook, Position.get(Column.CG, Row.R5));
        assertTrue(situation.isCheck(blackKing));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsNotACheckIfTheKingIsNotAttacked() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CD, Row.R7));
        situation.addPiece(whiteKing, Position.get(Column.CA, Row.R5));
        situation.addPiece(whiteRook, Position.get(Column.CE, Row.R3));
        assertFalse(situation.isCheck(blackKing));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsNotACheckIfAnotherFigureStandsBetweenTheKingAndTheAttackingFigure() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CD, Row.R7));
        situation.addPiece(whiteKing, Position.get(Column.CD, Row.R5));
        situation.addPiece(whiteRook, Position.get(Column.CD, Row.R3));
        assertFalse(situation.isCheck(blackKing));
    }

    // ========================================================================
    // IS STALEMATE?
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsAStalemateIfTheKingIsNotInCheckButCannotMove() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CA, Row.R8));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CB, Row.R7));
        assertTrue(situation.isStalemate(blackKing));
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsNotAStalemateIfTheKingCanMove() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CA, Row.R8));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CC, Row.R7));
        assertFalse(situation.isStalemate(blackKing));
    }

    // ========================================================================
    // IS CHECKMATE?
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsACheckmateIfTheKingIsInCheckAndCannotMove() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CA, Row.R8));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CC, Row.R8));
        assertTrue(situation.isCheckmate(blackKing));
    }

    // ========================================================================
    // FINAL SITUATIONS AND THE RESPECTIVE RESULTS
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void nonFinalSituationsDoNotHaveResultsAssigned() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CD, Row.R4));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CE, Row.R5));
        assertFalse(situation.isFinal());
        situation.getResult();
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsADrawIfOnlyTheTwoKingsRemainActive() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CD, Row.R4));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        assertTrue(situation.isFinal());
        assertEquals(new Draw(), situation.getResult());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsADrawIfOneOfTheKingsIsInStalemate() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CA, Row.R8));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CB, Row.R7));
        assertTrue(situation.isFinal());
        assertEquals(new Draw(), situation.getResult());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void aSituationIsAWinForAPlayerIfTheOppositePlayerIsInCheckmate() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CA, Row.R8));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R6));
        situation.addPiece(whiteRook, Position.get(Column.CC, Row.R8));
        assertTrue(situation.isFinal());
        assertEquals(new Win(whitePlayer), situation.getResult());
    }

    // ========================================================================
    // SUCCESSORS
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void generatesSuccessorSituations() {
        Situation situation = new Situation();
        situation.addPiece(blackKing, Position.get(Column.CC, Row.R7));
        situation.addPiece(whiteKing, Position.get(Column.CB, Row.R5));
        situation.addPiece(whiteRook, Position.get(Column.CB, Row.R2));
        List<Situation> successors = situation.generateSuccessors(whitePlayer);
        assertEquals(16, successors.size());
    }
}
