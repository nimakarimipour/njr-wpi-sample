package cz.dusanrychnovsky.chessendgames;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import cz.dusanrychnovsky.chessendgames.core.Game;
import cz.dusanrychnovsky.chessendgames.core.King;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Piece;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Result;
import cz.dusanrychnovsky.chessendgames.core.Rook;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.core.Player.Color;
import cz.dusanrychnovsky.chessendgames.core.strategies.PrecomputedValues;
import cz.dusanrychnovsky.chessendgames.core.strategies.Strategy;

@org.checkerframework.framework.qual.AnnotatedFor("org.checkerframework.checker.nullness.NullnessChecker")
public class CLIAdapter {

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BufferedReader in;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BufferedWriter out;

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player whitePlayer = Player.get(Color.WHITE);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King whiteKing = new King(whitePlayer);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Player blackPlayer = Player.get(Color.BLACK);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull King blackKing = new King(blackPlayer);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Rook blackRook = new Rook(blackPlayer);

    private final @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Strategy strategy;

    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @org.checkerframework.dataflow.qual.Impure
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Strategy strategy = loadStrategy();
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
            CLIAdapter adapter = new CLIAdapter(strategy, in, out);
            adapter.run();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    @org.checkerframework.dataflow.qual.Impure
    private static @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Strategy loadStrategy() throws IOException, ClassNotFoundException {
        InputStream is = null;
        ObjectInputStream in = null;
        try {
            is = PrecomputedValues.class.getResourceAsStream("strategy.dat");
            in = new ObjectInputStream(new BufferedInputStream(is));
            return PrecomputedValues.load(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * @param strategy
     * @param in
     * @param out
     */
    @org.checkerframework.dataflow.qual.SideEffectFree
    public CLIAdapter(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Strategy strategy, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BufferedReader in, @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull BufferedWriter out) {
        this.strategy = strategy;
        this.in = in;
        this.out = out;
    }

    /**
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    public void run() throws IOException {
        printIntroduction();
        Situation situation = setUpInitialSituation();
        printLine("Current situation: " + situation);
        Game game = new Game(strategy, situation, blackPlayer);
        while (!situation.isFinal()) {
            Move move = getMove(situation);
            situation = game.doMove(move);
            if (situation.isFinal()) {
                break;
            }
            situation = game.playMove();
            printLine("Current situation: " + situation);
        }
        Result result = situation.getResult();
        printLine("Result: " + result);
    }

    /**
     * @param situation
     * @return
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Move getMove(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) throws IOException {
        printLine("Please give your next move ([from] - [to]):");
        while (true) {
            String line = in.readLine();
            try {
                String[] tokens = line.split("-");
                if (tokens.length != 2) {
                    throw new IllegalArgumentException("Invalid move definition [" + line + "].");
                }
                Position from = Position.get(tokens[0]);
                Piece piece = situation.getPiece(from);
                Position to = Position.get(tokens[1]);
                return new Move(piece, from, to);
            } catch (IllegalArgumentException ex) {
                printLine("The given move is invalid. Try again, please.");
            }
        }
    }

    /**
     * @return
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation setUpInitialSituation() throws IOException {
        Situation initialSituation = new Situation();
        setUpWhiteKing(initialSituation);
        setUpBlackKing(initialSituation);
        setUpBlackRook(initialSituation);
        return initialSituation;
    }

    /**
     * @param situation
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private void setUpBlackRook(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) throws IOException {
        printLine("Please give black rook coordinates:");
        Position position = readPosition();
        situation.addPiece(blackRook, position);
    }

    /**
     * @param situation
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private void setUpBlackKing(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) throws IOException {
        printLine("Please give black king coordinates:");
        Position position = readPosition();
        situation.addPiece(blackKing, position);
    }

    /**
     * @param situation
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private void setUpWhiteKing(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull Situation situation) throws IOException {
        printLine("Please give white king coordinates:");
        Position position = readPosition();
        situation.addPiece(whiteKing, position);
    }

    /**
     * @return
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private @org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.Nullable Position readPosition() throws IOException {
        while (true) {
            String line = in.readLine();
            try {
                Position result = Position.get(line);
                return result;
            } catch (IllegalArgumentException ex) {
                printLine("The given position is invalid. Try again, please.");
            }
        }
    }

    /**
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private void printIntroduction() throws IOException {
        printLine("Wellcome to Chess Endgames!");
    }

    /**
     * @param line
     * @throws IOException
     */
    @org.checkerframework.dataflow.qual.Impure
    private void printLine(@org.checkerframework.checker.initialization.qual.Initialized @org.checkerframework.checker.nullness.qual.NonNull String line) throws IOException {
        out.write(line);
        out.newLine();
        out.flush();
    }
}
