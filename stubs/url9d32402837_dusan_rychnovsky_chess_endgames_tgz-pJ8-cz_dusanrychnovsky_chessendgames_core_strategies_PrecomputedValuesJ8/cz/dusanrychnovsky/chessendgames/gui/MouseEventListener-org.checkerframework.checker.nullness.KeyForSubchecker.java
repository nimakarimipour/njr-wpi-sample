package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.core.Position;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public interface MouseEventListener {

    /**
     * @param position
     */
    @org.checkerframework.dataflow.qual.Impure
    public abstract void onMouseClicked( MouseEventListener this,  Position position);
}
