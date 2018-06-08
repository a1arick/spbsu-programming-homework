package com.spbsu.a1arick.homework9.task1;

import com.spbsu.a1arick.homework9.task1.server.TicTacToeController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class P1vsP1 extends Application implements TicTacToe {
    private static final int N = 3;
    private static final double magicShiftNumber = 20.0 / N;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private TicTacToeController controller = new TicTacToeController(N);
    private int xWins = 0;
    private int oWins = 0;
    private int draw = 1;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        root.getChildren().add(getGrid(primaryStage));
        Scene scene = new Scene(root, N * P1vsP1.width, N * P1vsP1.height);
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
                b.setOnMouseClicked(event -> {
                    if (!controller.canMakeTurn(row, column)) return;
                    boolean turn = controller.makeTurn(row, column);
                    b.setText(turn ? "X" : "O");
                    check();
                });
                gridPane.add(b, j, 1 + i);
                b.minWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
            }
        }

        return gridPane;
    }

    @Override
    public void check() {
        if (controller.check(1)) {
            oWins++;
            showMessage("O win!!!");
        } else if (controller.check(2)) {
            xWins++;
            showMessage("X win!!!");
        } else if (controller.isFinished()) {
            draw++;
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
        alert.setContentText(String.format("X wins: %d, O Wins: %d, Draw %d", xWins, oWins, draw));
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
