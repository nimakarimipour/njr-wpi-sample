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
@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class GUIAdapter implements MouseEventListener {

    private final @org.checkerframework.checker.initialization.qual.UnderInitialization(cz.dusanrychnovsky.chessendgames.gui.Window.class) @org.checkerframework.checker.nullness.qual.NonNull Window window;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player whitePlayer = Player.get(Color.WHITE);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King whiteKing = new King(whitePlayer);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player blackPlayer = Player.get(Color.BLACK);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King blackKing = new King(blackPlayer);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook blackRook = new Rook(blackPlayer);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Strategy strategy;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull Game game;

    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.MonotonicNonNull GameState currentState;

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
    private void initGame() {
        currentState = new PlaceBlackKing();
        window.setSituation(currentState.getSituation());
        window.setStatus(currentState.getStatusMessage());
    }

    @org.checkerframework.dataflow.qual.Impure
    public void onMouseClicked(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GUIAdapter this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
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
        public abstract @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameState handle(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position);

        /**
         * @return
         */
        @org.checkerframework.dataflow.qual.Pure
        public abstract @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation getSituation();

        /**
         * @return
         */
        @org.checkerframework.dataflow.qual.Impure
        public abstract @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String getStatusMessage();
    }

    /**
     * @author Dušan Rychnovský
     */
    private class PlaceBlackKing extends GameState {

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation currSituation = new Situation();

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameState handle(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceBlackKing this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
            Situation newSituation = new Situation(currSituation);
            newSituation.addPiece(blackKing, position);
            return new PlaceBlackRook(newSituation);
        }

        @org.checkerframework.dataflow.qual.Pure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation getSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceBlackKing this) {
            return currSituation;
        }

        @org.checkerframework.dataflow.qual.Pure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String getStatusMessage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceBlackKing this) {
            return "Click on a square to place the opponent's king.";
        }
    }

    /**
     * @author Dušan Rychnovský
     */
    private class PlaceBlackRook extends GameState {

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation currSituation;

        private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean inError = false;

        /**
         * @param currSituation
         */
        @org.checkerframework.dataflow.qual.Impure
        public PlaceBlackRook(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation currSituation) {
            this.currSituation = currSituation;
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameState handle(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceBlackRook this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
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
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation getSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceBlackRook this) {
            return currSituation;
        }

        @org.checkerframework.dataflow.qual.Pure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String getStatusMessage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceBlackRook this) {
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

        private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation currSituation;

        private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean inError = false;

        /**
         * @param currSituation
         */
        @org.checkerframework.dataflow.qual.Impure
        public PlaceWhiteKing(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation currSituation) {
            this.currSituation = currSituation;
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameState handle(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceWhiteKing this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
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
        private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean isValid(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
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
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation getSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceWhiteKing this) {
            return currSituation;
        }

        @org.checkerframework.dataflow.qual.Pure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String getStatusMessage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull PlaceWhiteKing this) {
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

        private  @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull boolean inError = false;

        /**
         * @param situation
         */
        @org.checkerframework.dataflow.qual.Impure
        public MakeMove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) {
            game = new Game(strategy, situation, blackPlayer);
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameState handle(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MakeMove this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
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
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation getSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MakeMove this) {
            return game.getCurrentSituation();
        }

        @org.checkerframework.dataflow.qual.Pure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String getStatusMessage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull MakeMove this) {
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
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameState handle(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameFinished this, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position position) {
            return this;
        }

        @org.checkerframework.dataflow.qual.Pure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation getSituation(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameFinished this) {
            return game.getCurrentSituation();
        }

        @org.checkerframework.dataflow.qual.Impure
        public @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String getStatusMessage(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull GameFinished this) {
            return "Game finished - " + game.getCurrentSituation().getResult();
        }
    }
}
