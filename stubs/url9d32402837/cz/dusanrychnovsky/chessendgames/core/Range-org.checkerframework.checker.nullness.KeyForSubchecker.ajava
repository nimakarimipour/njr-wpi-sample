package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class Range implements Iterable<Position>, Serializable {

    private static final   long serialVersionUID = 1L;

    private final  List<Position> positions = new ArrayList<Position>();

    /**
     * Returns a (correctly ordered) range of positions, starting with the
     * first and ending with the second given position.
     *
     * @param from
     * @param to
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public static  Range get( Position from,  Position to) {
        Column fromColumn = from.getColumn();
        Row fromRow = from.getRow();
        Column toColumn = to.getColumn();
        Row toRow = to.getRow();
        if (fromRow.equals(toRow)) {
            return buildRowRange(fromColumn, fromRow, toColumn, toRow);
        }
        if (fromColumn.equals(toColumn)) {
            return buildColumnRange(fromColumn, fromRow, toColumn, toRow);
        }
        if (fromColumn.distance(toColumn) == fromRow.distance(toRow)) {
            return buildDiagonalRange(fromColumn, fromRow, toColumn, toRow);
        }
        throw new IllegalArgumentException("Invalid range - from [" + from + "] to [" + to + "].");
    }

    /**
     * @param fromColumn
     * @param fromRow
     * @param toColumn
     * @param toRow
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private static  Range buildDiagonalRange( Column fromColumn,  Row fromRow,  Column toColumn,  Row toRow) {
        Range result = new Range();
        Iterator<Row> rowIt = fromRow.to(toRow);
        Iterator<Column> columnIt = fromColumn.to(toColumn);
        while (rowIt.hasNext()) {
            Row currRow = rowIt.next();
            Column currColumn = columnIt.next();
            result.addPosition(Position.get(currColumn, currRow));
        }
        return result;
    }

    /**
     * @param fromColumn
     * @param fromRow
     * @param toColumn
     * @param toRow
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private static  Range buildColumnRange( Column fromColumn,  Row fromRow,  Column toColumn,  Row toRow) {
        Range result = new Range();
        Iterator<Row> rowIt = fromRow.to(toRow);
        while (rowIt.hasNext()) {
            Row currRow = rowIt.next();
            result.addPosition(Position.get(fromColumn, currRow));
        }
        return result;
    }

    /**
     * @param fromColumn
     * @param fromRow
     * @param toColumn
     * @param toRow
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private static  Range buildRowRange( Column fromColumn,  Row fromRow,  Column toColumn,  Row toRow) {
        Range result = new Range();
        Iterator<Column> columnIt = fromColumn.to(toColumn);
        while (columnIt.hasNext()) {
            Column currColumn = columnIt.next();
            result.addPosition(Position.get(currColumn, fromRow));
        }
        return result;
    }

    /**
     * @param position
     */
    @org.checkerframework.dataflow.qual.Impure
    private void addPosition( Range this,  Position position) {
        positions.add(position);
    }

    @org.checkerframework.dataflow.qual.Pure
    public  Iterator<Position> iterator( Range this) {
        return positions.iterator();
    }

    @org.checkerframework.dataflow.qual.Pure
    public   int hashCode( Range this) {
        return positions.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public   boolean equals( Range this,  Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        Range other = (Range) obj;
        return positions.equals(other.positions);
    }

    @org.checkerframework.dataflow.qual.Pure
    public  String toString( Range this) {
        return positions.get(0) + "-" + positions.get(positions.size() - 1);
    }
}
