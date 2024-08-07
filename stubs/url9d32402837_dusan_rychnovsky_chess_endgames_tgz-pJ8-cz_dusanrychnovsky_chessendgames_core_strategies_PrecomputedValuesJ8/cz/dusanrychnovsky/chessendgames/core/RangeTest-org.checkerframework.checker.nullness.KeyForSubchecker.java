package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class RangeTest {

    // ========================================================================
    // TRIVIAL RANGE
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void canBuildATrivialRange() {
        Position from = Position.get(Column.CH, Row.R6);
        Position to = from;
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CH, Row.R6), it.next());
        assertFalse(it.hasNext());
    }

    // ========================================================================
    // HORIZONTAL RANGES
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void canBuildALeftToRightRange() {
        Position from = Position.get(Column.CB, Row.R3);
        Position to = Position.get(Column.CH, Row.R3);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CB, Row.R3), it.next());
        assertEquals(Position.get(Column.CC, Row.R3), it.next());
        assertEquals(Position.get(Column.CD, Row.R3), it.next());
        assertEquals(Position.get(Column.CE, Row.R3), it.next());
        assertEquals(Position.get(Column.CF, Row.R3), it.next());
        assertEquals(Position.get(Column.CG, Row.R3), it.next());
        assertEquals(Position.get(Column.CH, Row.R3), it.next());
        assertFalse(it.hasNext());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void canBuildARightToLeftRange() {
        Position from = Position.get(Column.CH, Row.R8);
        Position to = Position.get(Column.CE, Row.R8);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CH, Row.R8), it.next());
        assertEquals(Position.get(Column.CG, Row.R8), it.next());
        assertEquals(Position.get(Column.CF, Row.R8), it.next());
        assertEquals(Position.get(Column.CE, Row.R8), it.next());
        assertFalse(it.hasNext());
    }

    // ========================================================================
    // VERTICAL RANGES
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void canBuildATopToBottomRange() {
        Position from = Position.get(Column.CD, Row.R6);
        Position to = Position.get(Column.CD, Row.R4);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CD, Row.R6), it.next());
        assertEquals(Position.get(Column.CD, Row.R5), it.next());
        assertEquals(Position.get(Column.CD, Row.R4), it.next());
        assertFalse(it.hasNext());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void canBuildABottomToTopRange() {
        Position from = Position.get(Column.CB, Row.R1);
        Position to = Position.get(Column.CB, Row.R4);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CB, Row.R1), it.next());
        assertEquals(Position.get(Column.CB, Row.R2), it.next());
        assertEquals(Position.get(Column.CB, Row.R3), it.next());
        assertEquals(Position.get(Column.CB, Row.R4), it.next());
        assertFalse(it.hasNext());
    }

    // ========================================================================
    // DIAGONAL RANGES
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void canBuildABottomLeftToTopRightRange() {
        Position from = Position.get(Column.CD, Row.R4);
        Position to = Position.get(Column.CF, Row.R6);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CD, Row.R4), it.next());
        assertEquals(Position.get(Column.CE, Row.R5), it.next());
        assertEquals(Position.get(Column.CF, Row.R6), it.next());
        assertFalse(it.hasNext());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void canBuildATopRightToBottomLeftRange() {
        Position from = Position.get(Column.CC, Row.R6);
        Position to = Position.get(Column.CB, Row.R5);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CC, Row.R6), it.next());
        assertEquals(Position.get(Column.CB, Row.R5), it.next());
        assertFalse(it.hasNext());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void canBuildATopLeftToBottomRightRange() {
        Position from = Position.get(Column.CA, Row.R8);
        Position to = Position.get(Column.CH, Row.R1);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CA, Row.R8), it.next());
        assertEquals(Position.get(Column.CB, Row.R7), it.next());
        assertEquals(Position.get(Column.CC, Row.R6), it.next());
        assertEquals(Position.get(Column.CD, Row.R5), it.next());
        assertEquals(Position.get(Column.CE, Row.R4), it.next());
        assertEquals(Position.get(Column.CF, Row.R3), it.next());
        assertEquals(Position.get(Column.CG, Row.R2), it.next());
        assertEquals(Position.get(Column.CH, Row.R1), it.next());
        assertFalse(it.hasNext());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void canBuildABottomRightToTopLeftRange() {
        Position from = Position.get(Column.CG, Row.R3);
        Position to = Position.get(Column.CE, Row.R5);
        Range range = Range.get(from, to);
        Iterator<Position> it = range.iterator();
        assertEquals(Position.get(Column.CG, Row.R3), it.next());
        assertEquals(Position.get(Column.CF, Row.R4), it.next());
        assertEquals(Position.get(Column.CE, Row.R5), it.next());
        assertFalse(it.hasNext());
    }

    // ========================================================================
    // INVALID RANGES
    // ========================================================================
    @org.checkerframework.dataflow.qual.Impure
    public void cannotBuildANonStraightLineRange() {
        Position from = Position.get(Column.CB, Row.R6);
        Position to = Position.get(Column.CF, Row.R4);
        Range.get(from, to);
    }
}
