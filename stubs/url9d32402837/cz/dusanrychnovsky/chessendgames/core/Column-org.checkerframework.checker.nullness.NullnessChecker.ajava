package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public enum Column {

    CA,
    CB,
    CC,
    CD,
    CE,
    CF,
    CG,
    CH;

    private static final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull [] values = values();

    /**
     * @param ordinal
     * @return
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column get( @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int ordinal) {
        try {
            return values[ordinal];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid column ordinal [" + ordinal + "].");
        }
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean preceeds(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column other) {
        return ordinal() < other.ordinal();
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull int distance(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column other) {
        return Math.abs(ordinal() - other.ordinal());
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isFirst() {
        return this.equals(Column.CA);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column previous() {
        if (isFirst()) {
            throw new UnsupportedOperationException("No previous column for column CA.");
        }
        int ordinal = ordinal();
        return values[ordinal - 1];
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isLast() {
        return this.equals(Column.CH);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column next() {
        if (isLast()) {
            throw new UnsupportedOperationException("No next column for column CH.");
        }
        int ordinal = ordinal();
        return values[ordinal + 1];
    }

    /**
     * Returns an iterator over the range of columns delimited by the current
     * and the given one, starting with the current column.
     *
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Iterator<Column> to(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column other) {
        if (preceeds(other)) {
            return new ForwardIterator(this, other);
        } else {
            return new BackwardIterator(this, other);
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class BackwardIterator implements Iterator<Column> {

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column toColumn;

        private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Column currColumn;

        /**
         * @param fromColumn
         * @param toColumn
         */
        @org.checkerframework.dataflow.qual.Impure
        public BackwardIterator(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column fromColumn, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column toColumn) {
            if (fromColumn.preceeds(toColumn)) {
                throw new IllegalArgumentException("Cannot iterate backwards from [" + fromColumn + "] to [" + toColumn + "]");
            }
            this.toColumn = toColumn;
            this.currColumn = fromColumn;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean hasNext(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BackwardIterator this) {
            return (currColumn != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Column next(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BackwardIterator this) {
            Column result = currColumn;
            if (currColumn.equals(toColumn)) {
                currColumn = null;
            } else {
                currColumn = currColumn.previous();
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
    private class ForwardIterator implements Iterator<Column> {

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column toColumn;

        private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Column currColumn;

        /**
         * @param fromColumn
         * @param toColumn
         */
        @org.checkerframework.dataflow.qual.Impure
        public ForwardIterator(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column fromColumn, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Column toColumn) {
            if (toColumn.preceeds(fromColumn)) {
                throw new IllegalArgumentException("Cannot iterate forwards from [" + fromColumn + "] to [" + toColumn + "].");
            }
            this.toColumn = toColumn;
            this.currColumn = fromColumn;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean hasNext(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull ForwardIterator this) {
            return (currColumn != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Column next(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull ForwardIterator this) {
            Column result = currColumn;
            if (currColumn.equals(toColumn)) {
                currColumn = null;
            } else {
                currColumn = currColumn.next();
            }
            return result;
        }

        @org.checkerframework.dataflow.qual.SideEffectFree
        public void remove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull ForwardIterator this) {
            throw new UnsupportedOperationException();
        }
    }
}
