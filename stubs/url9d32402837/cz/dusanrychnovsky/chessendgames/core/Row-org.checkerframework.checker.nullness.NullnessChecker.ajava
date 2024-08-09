package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public enum Row {

    R1,
    R2,
    R3,
    R4,
    R5,
    R6,
    R7,
    R8;

    private static final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull [] values = values();

    /**
     * @param ordinal
     * @return
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row get( @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int ordinal) {
        try {
            return values[ordinal];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid row ordinal [" + ordinal + "].");
        }
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean preceeds(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row other) {
        return this.ordinal() < other.ordinal();
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int distance(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row other) {
        return Math.abs(this.ordinal() - other.ordinal());
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isFirst() {
        return this.equals(Row.R1);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row previous() {
        if (this.equals(Row.R1)) {
            throw new UnsupportedOperationException("No previous row for row R1.");
        }
        int ordinal = ordinal();
        return values[ordinal - 1];
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isLast() {
        return this.equals(Row.R8);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row next() {
        if (this.equals(Row.R8)) {
            throw new UnsupportedOperationException("No next row for row R8.");
        }
        int ordinal = ordinal();
        return values[ordinal + 1];
    }

    /**
     * Returns an iterator over the range of rows delimited by the current
     * and the given one, starting with the current row.
     *
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Iterator<Row> to(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row other) {
        if (preceeds(other)) {
            return new ForwardIterator(this, other);
        } else {
            return new BackwardIterator(this, other);
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class BackwardIterator implements Iterator<Row> {

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row toRow;

        private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Row currRow;

        /**
         * @param fromRow
         * @param toRow
         */
        @org.checkerframework.dataflow.qual.Impure
        public BackwardIterator(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row fromRow, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row toRow) {
            if (fromRow.preceeds(toRow)) {
                throw new IllegalArgumentException("Cannot iterate backwards from [" + fromRow + "] to [" + toRow + "]");
            }
            this.toRow = toRow;
            this.currRow = fromRow;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean hasNext(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BackwardIterator this) {
            return (currRow != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Row next(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BackwardIterator this) {
            Row result = currRow;
            if (currRow.equals(toRow)) {
                currRow = null;
            } else {
                currRow = currRow.previous();
            }
            return result;
        }

        @org.checkerframework.dataflow.qual.SideEffectFree
        public void remove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BackwardIterator this) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class ForwardIterator implements Iterator<Row> {

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row toRow;

        private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Row currRow;

        /**
         * @param fromRow
         * @param toRow
         */
        @org.checkerframework.dataflow.qual.Impure
        public ForwardIterator(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row fromRow, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Row toRow) {
            if (toRow.preceeds(fromRow)) {
                throw new IllegalArgumentException("Cannot iterate forwards from [" + fromRow + "] to [" + toRow + "].");
            }
            this.toRow = toRow;
            this.currRow = fromRow;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean hasNext(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull ForwardIterator this) {
            return (currRow != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Row next(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull ForwardIterator this) {
            Row result = currRow;
            if (currRow.equals(toRow)) {
                currRow = null;
            } else {
                currRow = currRow.next();
            }
            return result;
        }

        @org.checkerframework.dataflow.qual.SideEffectFree
        public void remove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull ForwardIterator this) {
            throw new UnsupportedOperationException();
        }
    }
}
