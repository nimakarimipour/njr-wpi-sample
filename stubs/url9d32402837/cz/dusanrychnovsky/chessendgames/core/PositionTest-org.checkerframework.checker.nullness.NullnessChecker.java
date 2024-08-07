package cz.dusanrychnovsky.chessendgames.core;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class PositionTest {

    @org.checkerframework.dataflow.qual.Impure
    public void canBePrintedInHumanReadableForm() {
        Position pos = Position.get(Column.CF, Row.R8);
        assertEquals("F8", pos.toString());
    }
}
