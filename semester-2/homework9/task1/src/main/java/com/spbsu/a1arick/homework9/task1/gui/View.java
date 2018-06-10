package com.spbsu.a1arick.homework9.task1.gui;

import com.spbsu.a1arick.homework9.task1.client.ClientRunnable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.function.BiPredicate;


/**
 * View for Tic-tac-toe game
 */
public class View extends Application {
    private static final int N = 3;
    private static final double magicShiftNumber = 20.0 / N;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        root.getChildren().add(getGrid(primaryStage));
        Scene scene = new Scene(root, N * View.width, N * View.height);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            ClientRunnable runnable = new ClientRunnable(InetAddress.getLocalHost(), 1111, new ViewClient(this));
            new Thread(runnable).start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    private Pane getGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        Button newGame = new Button();
        newGame.setText("New game");
        newGame.setOnMouseClicked(e -> clear());
        gridPane.add(newGame,1, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Button b = buttons[i][j] = new Button();
                gridPane.add(b, j, 1 + i);
                b.minWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
            }
        }



        return gridPane;
    }

    public void showMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void set(int i, int j, boolean isCross) {
        buttons[i][j].setText(isCross ? "X" : "O");
    }

    public void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public BlockingQueue<Pair<Integer, Integer>> nextTurn(BiPredicate<Integer, Integer> canMakeTurn) {
        toggleButtons(true);
        SynchronousQueue<Pair<Integer, Integer>> turn = new SynchronousQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Button b = buttons[i][j] = new Button();
                int row = i;
                int column = j;
                b.setOnMouseClicked(event -> {
                    if (canMakeTurn.test(row, column)) {
                        toggleButtons(false);
                        turn.add(new Pair<>(row, column));
                    }
                });
            }
        }
        return turn;
    }

    private void toggleButtons(boolean enable) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Button b = buttons[i][j];
                if (enable && b.getText().equals("")) {
                    b.disableProperty().setValue(false);
                } else if(!enable) {
                    b.disableProperty().setValue(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}