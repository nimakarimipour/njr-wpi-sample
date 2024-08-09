package cz.dusanrychnovsky.chessendgames.core;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class Win extends Result {

    private final  Player player;

    /**
     * @param player
     */
    @org.checkerframework.dataflow.qual.Impure
    public Win( Player player) {
        this.player = player;
    }

    @org.checkerframework.dataflow.qual.Pure
    public   int hashCode( Win this) {
        return "win".hashCode() + player.hashCode();
    }

    @org.checkerframework.dataflow.qual.Pure
    public   boolean equals( Win this,  Object obj) {
        if (!(obj instanceof Win)) {
            return false;
        }
        Win other = (Win) obj;
        return player.equals(other.player);
    }

    @org.checkerframework.dataflow.qual.Pure
    public  String toString( Win this) {
        return player + " won!";
    }
}
