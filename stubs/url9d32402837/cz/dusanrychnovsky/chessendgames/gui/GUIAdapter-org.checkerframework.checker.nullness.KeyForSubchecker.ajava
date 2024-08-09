package cz.dusanrychnovsky.chessendgames.gui;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import cz.dusanrychnovsky.chessendgames.core.Game;
import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;
import cz.dusanrychnovsky.chessendgames.core.strategies.PrecomputedValues;
import cz.dusanrychnovsky.chessendgames.core.strategies.Strategy;

/**
 * @author Dušan Rychnovský
 */
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.KeyForSubchecker")
public class GUIAdapter implements MouseEventListener {

    private final  Window window;

    private final  Player whitePlayer = Player.get(Color.WHITE);

    private final  King whiteKing = new King(whitePlayer);

    private final  Player blackPlayer = Player.get(Color.BLACK);

    private final  King blackKing = new King(blackPlayer);

    private final  Rook blackRook = new Rook(blackPlayer);

    private final  Strategy strategy;

    private  Game game;

    private  GameState currentState;

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new GUIAdapter();
    }

    /**
     * @param strategy
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @org.checkerframework.dataflow.qual.Impure
    public GUIAdapter() throws ClassNotFoundException, IOException {
        SplashScreen splashScreen = new SplashScreen();
        InputStream is = PrecomputedValues.class.getResourceAsStream("strategy.dat");
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(is));
        strategy = PrecomputedValues.load(in);
        in.close();
        splashScreen.close();
        window = new Window(this);
        initGame();
    }

    /**
     */
    @org.checkerframework.dataflow.qual.Impure
    private void initGame( GUIAdapter this) {
        currentState = new PlaceBlackKing();
        window.setSituation(currentState.getSituation());
        window.setStatus(currentState.getStatusMessage());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void onMouseClicked( GUIAdapter this,  Position position) {
        currentState = currentState.handle(position);
        Situation currentSituation = currentState.getSituation();
        window.setSituation(currentSituation);
        String statusMessage = currentState.getStatusMessage();
        window.setStatus(statusMessage);
        window.repaint();
    }

    /**
     * @author Dušan Rychnovský
     */
    private abstract class GameState {

        /**
         * @param position
         * @return
         */
        @org.checkerframework.dataflow.qual.Impure
        public abstract  GameState handle( GameState this,  Position position);

        /**
         * @return
         */
        @org.checkerframework.dataflow.qual.Pure
        public abstract  Situation getSituation( GameState this);

        /**
         * @return
         */
        @org.checkerframework.dataflow.qual.Impure
        public abstract  String getStatusMessage( GameState this);
    }

    /**
     * @author Dušan Rychnovský
     */
    private class PlaceBlackKing extends GameState {

        private final  Situation currSituation = new Situation();

        @org.checkerframework.dataflow.qual.Impure
        public  GameState handle( PlaceBlackKing this,  Position position) {
            Situation newSituation = new Situation(currSituation);
            newSituation.addPiece(blackKing, position);
            return new PlaceBlackRook(newSituation);
        }

        @org.checkerframework.dataflow.qual.Pure
        public  Situation getSituation( PlaceBlackKing this) {
            return currSituation;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  String getStatusMessage( PlaceBlackKing this) {
            return "Click on a square to place the opponent's king.";
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class PlaceBlackRook extends GameState {

        private final  Situation currSituation;

        private   boolean inError = false;

        /**
         * @param currSituation
         */
        @org.checkerframework.dataflow.qual.Impure
        public PlaceBlackRook( Situation currSituation) {
            this.currSituation = currSituation;
        }

        @org.checkerframework.dataflow.qual.Impure
        public  GameState handle( PlaceBlackRook this,  Position position) {
            if (currSituation.isOccupied(position)) {
                inError = true;
                return this;
            }
            inError = false;
            Situation newSituation = new Situation(currSituation);
            newSituation.addPiece(blackRook, position);
            return new PlaceWhiteKing(newSituation);
        }

        @org.checkerframework.dataflow.qual.Pure
        public  Situation getSituation( PlaceBlackRook this) {
            return currSituation;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  String getStatusMessage( PlaceBlackRook this) {
            String message = "Click on a square to place the opponent's rook.";
            if (inError) {
                message = "Invalid position. " + message;
            }
            return message;
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class PlaceWhiteKing extends GameState {

        private final  Situation currSituation;

        private   boolean inError = false;

        /**
         * @param currSituation
         */
        @org.checkerframework.dataflow.qual.Impure
        public PlaceWhiteKing( Situation currSituation) {
            this.currSituation = currSituation;
        }

        @org.checkerframework.dataflow.qual.Impure
        public  GameState handle( PlaceWhiteKing this,  Position position) {
            if (!isValid(position)) {
                inError = true;
                return this;
            }
            inError = false;
            Situation newSituation = new Situation(currSituation);
            newSituation.addPiece(whiteKing, position);
            return new MakeMove(newSituation);
        }

        /**
         * @param position
         * @return
         */
        @org.checkerframework.dataflow.qual.Impure
        private   boolean isValid( PlaceWhiteKing this,  Position position) {
            if (currSituation.isOccupied(position)) {
                return false;
            }
            if (position.isNeighbour(currSituation.getPosition(blackKing))) {
                return false;
            }
            // TODO: don't allow to place the king into a check
            // (situation.isValid() ?)
            return true;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  Situation getSituation( PlaceWhiteKing this) {
            return currSituation;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  String getStatusMessage( PlaceWhiteKing this) {
            String message = "Click on a square to place your own king.";
            if (inError) {
                message = "Invalid position. " + message;
            }
            return message;
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class MakeMove extends GameState {

        private   boolean inError = false;

        /**
         * @param situation
         */
        @org.checkerframework.dataflow.qual.Impure
        public MakeMove( Situation situation) {
            game = new Game(strategy, situation, blackPlayer);
        }

        @org.checkerframework.dataflow.qual.Impure
        public  GameState handle( MakeMove this,  Position position) {
            Situation currSituation = game.getCurrentSituation();
            Position from = currSituation.getPosition(whiteKing);
            Move move;
            try {
                move = new Move(whiteKing, from, position);
                if (!move.isValid(currSituation)) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                inError = true;
                return this;
            }
            inError = false;
            Situation nextSituation = game.doMove(move);
            if (nextSituation.isFinal()) {
                return new GameFinished();
            }
            nextSituation = game.playMove();
            if (nextSituation.isFinal()) {
                return new GameFinished();
            }
            return this;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  Situation getSituation( MakeMove this) {
            return game.getCurrentSituation();
        }

        @org.checkerframework.dataflow.qual.Pure
        public  String getStatusMessage( MakeMove this) {
            String message = "Click on a square to make your move.";
            if (inError) {
                message = "Invalid move. " + message;
            }
            return message;
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class GameFinished extends GameState {

        @org.checkerframework.dataflow.qual.Pure
        public  GameState handle( GameFinished this,  Position position) {
            return this;
        }

        @org.checkerframework.dataflow.qual.Pure
        public  Situation getSituation( GameFinished this) {
            return game.getCurrentSituation();
        }

        @org.checkerframework.dataflow.qual.Impure
        public  String getStatusMessage( GameFinished this) {
            return "Game finished - " + game.getCurrentSituation().getResult();
        }
    }
}
