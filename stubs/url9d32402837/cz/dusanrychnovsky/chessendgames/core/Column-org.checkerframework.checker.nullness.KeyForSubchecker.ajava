package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public enum Column {

    CA,
    CB,
    CC,
    CD,
    CE,
    CF,
    CG,
    CH;

    private static final  Column  [] values = values();

    /**
     * @param ordinal
     * @return
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public static  Column get(  int ordinal) {
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
    public   boolean preceeds( Column this,  Column other) {
        return ordinal() < other.ordinal();
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public   int distance( Column this,  Column other) {
        return Math.abs(ordinal() - other.ordinal());
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public   boolean isFirst( Column this) {
        return this.equals(Column.CA);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  Column previous( Column this) {
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
    public   boolean isLast( Column this) {
        return this.equals(Column.CH);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  Column next( Column this) {
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
    public  Iterator<Column> to( Column this,  Column other) {
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

        private final  Column toColumn;

        private  Column currColumn;

        /**
         * @param fromColumn
         * @param toColumn
         */
        @org.checkerframework.dataflow.qual.Impure
        public BackwardIterator( Column fromColumn,  Column toColumn) {
            if (fromColumn.preceeds(toColumn)) {
                throw new IllegalArgumentException("Cannot iterate backwards from [" + fromColumn + "] to [" + toColumn + "]");
            }
            this.toColumn = toColumn;
            this.currColumn = fromColumn;
        }

        @org.checkerframework.dataflow.qual.Pure
        public   boolean hasNext( BackwardIterator this) {
            return (currColumn != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public  Column next( BackwardIterator this) {
            Column result = currColumn;
            if (currColumn.equals(toColumn)) {
                currColumn = null;
            } else {
                currColumn = currColumn.previous();
            }
            return result;
        }

        @org.checkerframework.dataflow.qual.SideEffectFree
        public void remove( BackwardIterator this) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class ForwardIterator implements Iterator<Column> {

        private final  Column toColumn;

        private  Column currColumn;

        /**
         * @param fromColumn
         * @param toColumn
         */
        @org.checkerframework.dataflow.qual.Impure
        public ForwardIterator( Column fromColumn,  Column toColumn) {
            if (toColumn.preceeds(fromColumn)) {
                throw new IllegalArgumentException("Cannot iterate forwards from [" + fromColumn + "] to [" + toColumn + "].");
            }
            this.toColumn = toColumn;
            this.currColumn = fromColumn;
        }

        @org.checkerframework.dataflow.qual.Pure
        public   boolean hasNext( ForwardIterator this) {
            return (currColumn != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public  Column next( ForwardIterator this) {
            Column result = currColumn;
            if (currColumn.equals(toColumn)) {
                currColumn = null;
            } else {
                currColumn = currColumn.next();
            }
            return result;
        }

        @org.checkerframework.dataflow.qual.SideEffectFree
        public void remove( ForwardIterator this) {
            throw new UnsupportedOperationException();
        }
    }
}
