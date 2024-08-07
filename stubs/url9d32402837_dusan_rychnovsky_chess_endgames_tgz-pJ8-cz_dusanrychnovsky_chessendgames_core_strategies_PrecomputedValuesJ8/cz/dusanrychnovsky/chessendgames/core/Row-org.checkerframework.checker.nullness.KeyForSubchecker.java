package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public enum Row {

    R1,
    R2,
    R3,
    R4,
    R5,
    R6,
    R7,
    R8;

    private static final  Row  [] values = values();

    /**
     * @param ordinal
     * @return
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public static  Row get(  int ordinal) {
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
    public   boolean preceeds( Row this,  Row other) {
        return this.ordinal() < other.ordinal();
    }

    /**
     * @param other
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public   int distance( Row this,  Row other) {
        return Math.abs(this.ordinal() - other.ordinal());
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Pure
    public   boolean isFirst( Row this) {
        return this.equals(Row.R1);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  Row previous( Row this) {
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
    public   boolean isLast( Row this) {
        return this.equals(Row.R8);
    }

    /**
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    public  Row next( Row this) {
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
    public  Iterator<Row> to( Row this,  Row other) {
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

        private final  Row toRow;

        private  Row currRow;

        /**
         * @param fromRow
         * @param toRow
         */
        @org.checkerframework.dataflow.qual.Impure
        public BackwardIterator( Row fromRow,  Row toRow) {
            if (fromRow.preceeds(toRow)) {
                throw new IllegalArgumentException("Cannot iterate backwards from [" + fromRow + "] to [" + toRow + "]");
            }
            this.toRow = toRow;
            this.currRow = fromRow;
        }

        @org.checkerframework.dataflow.qual.Pure
        public   boolean hasNext( BackwardIterator this) {
            return (currRow != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public  Row next( BackwardIterator this) {
            Row result = currRow;
            if (currRow.equals(toRow)) {
                currRow = null;
            } else {
                currRow = currRow.previous();
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
    private class ForwardIterator implements Iterator<Row> {

        private final  Row toRow;

        private  Row currRow;

        /**
         * @param fromRow
         * @param toRow
         */
        @org.checkerframework.dataflow.qual.Impure
        public ForwardIterator( Row fromRow,  Row toRow) {
            if (toRow.preceeds(fromRow)) {
                throw new IllegalArgumentException("Cannot iterate forwards from [" + fromRow + "] to [" + toRow + "].");
            }
            this.toRow = toRow;
            this.currRow = fromRow;
        }

        @org.checkerframework.dataflow.qual.Pure
        public   boolean hasNext( ForwardIterator this) {
            return (currRow != null);
        }

        @org.checkerframework.dataflow.qual.Impure
        public  Row next( ForwardIterator this) {
            Row result = currRow;
            if (currRow.equals(toRow)) {
                currRow = null;
            } else {
                currRow = currRow.next();
            }
            return result;
        }

        @org.checkerframework.dataflow.qual.SideEffectFree
        public void remove( ForwardIterator this) {
            throw new UnsupportedOperationException();
        }
    }
}
