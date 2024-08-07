package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class Position implements Serializable {

    private static final  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull long serialVersionUID = 1L;

    private static final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Map<Pair<Column, Row>, Position> POSITIONS = new HashMap<Pair<Column, Row>, Position>();

    static {
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                Pair<Column, Row> key = Pair.of(column, row);
                Position position = new Position(column, row);
                POSITIONS.put(key, position);
            }
        }
    }

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column column;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row row;

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Collection<Position> getAllPositions() {
        return Collections.unmodifiableCollection(POSITIONS.values());
    }

    /**
     * @param column
     * @param row
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position get(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column column, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row row) {
        return POSITIONS.get(Pair.of(column, row));
    }

    /**
     * @param line
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position get(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable String line) {
        line = line.trim();
        if (line.length() != 2) {
            throw new IllegalArgumentException("Line does not consist of two characters [" + line + "].");
        }
        String columnVal = "C" + line.charAt(0);
        Column column = Column.valueOf(columnVal);
        String rowVal = "R" + line.charAt(1);
        Row row = Row.valueOf(rowVal);
        return Position.get(column, row);
    }

    /**
     * @param column
     * @param row
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    private Position(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column column, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row row) {
        this.column = column;
        this.row = row;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column getColumn() {
        return column;
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row getRow() {
        return row;
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isNeighbour(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position other) {
        if (this.equals(other)) {
            return false;
        }
        return (column.distance(other.column) <= 1) && (row.distance(other.row) <= 1);
    }

    /**
     * Returns a (correctly ordered) range of positions, starting with the
     * represented one and ending with the given one.
     *
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Range to(Position other) {
        return Range.get(this, other);
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int hashCode(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position this) {
        return column.hashCode() + row.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean equals(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        return (column.equals(other.column) && row.equals(other.row));
    }

    @org.checkerframework.dataflow.qual.Pure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String toString(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Position this) {
        return "" + column.toString().charAt(1) + row.toString().charAt(1);
    }
}
