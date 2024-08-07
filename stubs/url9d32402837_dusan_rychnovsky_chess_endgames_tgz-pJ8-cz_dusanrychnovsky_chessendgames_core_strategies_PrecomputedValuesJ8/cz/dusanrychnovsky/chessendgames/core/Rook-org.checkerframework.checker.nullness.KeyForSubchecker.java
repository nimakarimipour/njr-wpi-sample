package cz.dusanrychnovsky.chessendgames.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class Rook extends Piece {

    private static final   long serialVersionUID = 1L;

    /**
     * @param player
     */
    @org.checkerframework.dataflow.qual.Impure
    public Rook( Player player) {
        super(player);
    }

    @org.checkerframework.dataflow.qual.Impure
    public  List<Move> generateMoves( Rook this,  Position from) {
        List<Move> result = new ArrayList<Move>();
        List<Move> horizontalMoves = generateHorizontalMoves(from);
        result.addAll(horizontalMoves);
        List<Move> verticalMoves = generateVerticalMoves(from);
        result.addAll(verticalMoves);
        return result;
    }

    /**
     * @param from
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private  List<Move> generateVerticalMoves( Rook this,  Position from) {
        List<Move> result = new ArrayList<Move>();
        for (Row row : Row.values()) {
            if (!row.equals(from.getRow())) {
                Position to = Position.get(from.getColumn(), row);
                result.add(new Move(this, from, to));
            }
        }
        return result;
    }

    /**
     * @param from
     * @return
     */
    @org.checkerframework.dataflow.qual.Impure
    private  List<Move> generateHorizontalMoves( Rook this,  Position from) {
        List<Move> result = new ArrayList<Move>();
        for (Column column : Column.values()) {
            if (!column.equals(from.getColumn())) {
                Position to = Position.get(column, from.getRow());
                result.add(new Move(this, from, to));
            }
        }
        return result;
    }

    @org.checkerframework.dataflow.qual.Pure
    public   int hashCode( Rook this) {
        return super.hashCode() + "Rook".hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public   boolean equals( Rook this,  Object obj) {
        if (!(obj instanceof Rook)) {
            return false;
        }
        return super.equals(obj);
    }

    @org.checkerframework.dataflow.qual.Pure
    public  String toString( Rook this) {
        return getPlayer().toString() + " Rook";
    }
}
