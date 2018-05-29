package com.spbsu.a1arick;

import com.spbsu.a1arick.bot.Bot;
import com.spbsu.a1arick.bot.StupidBot;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;


/**
 * View for Tic-tac-toe game
 */
public class View extends Application {
    private static final int N = 3;
    private static final double magicShiftNumber = 20.0 / N;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private Controller controller = new Controller(N);
    private Bot bot = new StupidBot(N);
    private boolean botIsActive = false;

    private int xWins = 0;
    private int oWins = 0;
    private int botWins = 0;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        root.getChildren().add(getGrid(primaryStage));
        Scene scene = new Scene(root, N * View.width, N * View.height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane getGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        CheckBox activateBot = new CheckBox();
        activateBot.setText("Activate bot");
        activateBot.setSelected(false);
        activateBot.setOnAction(event -> botIsActive = !botIsActive);
        gridPane.add(activateBot, 0, 0);

        Button newGame = new Button();
        newGame.setText("New game");
        newGame.setOnMouseClicked(e -> clear());
        gridPane.add(newGame,1, 0);

        Button showStats = new Button();
        showStats.setText("Show stats");
        showStats.setOnMouseClicked(e -> showStats());
        gridPane.add(showStats,2, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Button b = buttons[i][j] = new Button();
                int row = i;
                int column = j;
                b.setOnMouseClicked(event -> makeTurn(row, column, b, false));
                gridPane.add(b, j, 1 + i);

                b.minWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
            }
        }



        return gridPane;
    }

    public void makeTurn(int i, int j, Button b, boolean isBotTurn) {
        if (!controller.canMakeTurn(i, j)) return;
        boolean turn = controller.makeTurn(i, j);
        b.setText(turn ? "X" : "O");
        check();
        if (botIsActive && !isBotTurn) {
            bot.set(i, j);
            Pair<Integer, Integer> turnPair = bot.nextTurn(controller::canMakeTurn);
            int botI = turnPair.getKey();
            int botJ = turnPair.getValue();
            makeTurn(botI, botJ, buttons[botI][botJ], true);
        }
    }

    private void check() {
        if (controller.check(1)) {
            oWins++;
            showMessage("O win!!!");
        } else if (controller.check(2)) {
            if (botIsActive) {
                botWins++;
            } else {
                xWins++;
            }
            showMessage("X win!!!");
        } else if (controller.isFinished()) {
            showMessage("Draw");
        }
    }

    private void showMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(s);
        alert.showAndWait();
        clear();
    }

    private void showStats() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(String.format("X wins: %d, O Wins: %d, Bot wins: %d", xWins, oWins, botWins));
        alert.showAndWait();
    }

    private void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttons[i][j].setText("");
            }
        }
        controller.clear();
        bot.clearState();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
