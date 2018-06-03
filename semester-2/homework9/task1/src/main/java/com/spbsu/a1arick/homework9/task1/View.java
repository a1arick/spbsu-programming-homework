package com.spbsu.a1arick.homework9.task1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;


/**
 * View for Tic-tac-toe game
 */
public class View extends Application implements TicTacToe{
    private static final int N = 3;
    private static final double magicShiftNumber = 20.0 / N;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private Controller controller = new Controller(N);
    private Bots bot1 = new Bots(N);

    private boolean endGame = false;
    private int playerWins = 0;
    private int botWins = 0;
    private int draw = 0;

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
                b.setOnMouseClicked(event -> makeTurnPlayer(row, column));
                gridPane.add(b, j, 1 + i);

                b.minWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
            }
        }



        return gridPane;
    }

    public void makeTurnPlayer(int i, int j) {
        if (!controller.canMakeTurn(i, j)) return;
        boolean turn = controller.makeTurn(i, j);
        buttons[i][j].setText("X");
        check();
        if(endGame)
            endGame = false;
        else
            makeTurnBot();
    }

    public void makeTurnBot(){
        Pair<Integer, Integer> temp = bot1.makeTurn();
        int botI = temp.getKey();
        int botJ = temp.getValue();
        if(controller.canMakeTurn(botI, botJ)){
            controller.makeTurn(botI, botJ);
            buttons[botI][botJ].setText("O");
            check();
        }
        else{
            makeTurnBot();
        }
    }

    @Override
    public void check() {
        if (controller.check(1)) {
            playerWins++;
            endGame = true;
            showMessage("Player wins!!!");
        }
        else if (controller.check(2) ) {
            botWins++;
            endGame = true;
            showMessage("Bot wins!!!");
        }
        else if (controller.isFinished()) {
            draw++;
            endGame = true;
            showMessage("Draw");
        }
    }

    @Override
    public void showMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(s);
        alert.showAndWait();
        clear();
    }

    @Override
    public void showStats() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(String.format("Player wins: %d, Bot Wins: %d, Draw %d", playerWins, botWins, draw));
        alert.showAndWait();
    }

    @Override
    public void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttons[i][j].setText("");
            }
        }
        controller.clear();

    }

    public static void main(String[] args) {
        launch(args);
    }
}