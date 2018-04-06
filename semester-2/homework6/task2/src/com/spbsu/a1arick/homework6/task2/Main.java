package com.spbsu.a1arick.homework6.task2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int N = 3;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private int[][] turns = new int[N][N];
    private boolean turn = true;
    private int size = 0;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        root.getChildren().add(getGrid());
        Scene scene = new Scene(root, N * width, N * height);
        primaryStage.setTitle("Message");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane getGrid() {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Button b = buttons[i][j] = new Button();
                b.setPrefHeight(height);
                b.setPrefWidth(width);
                int row = i;
                int column = j;
                b.setOnMouseClicked(event -> {
                    if ("X".equals(b.getText()) || "O".equals(b.getText())) return;
                    turns[row][column] = turn ? 1 : 2;
                    b.setText(turn ? "X" : "O");
                    turn = !turn;
                    size++;
                    check();
                });
                gridPane.add(b, j * (j + (int) b.getWidth()), i);
            }
        }

        return gridPane;
    }

    private void check() {
        if (check(1)) {
            showMessage("X win!!!");
        } else if (check(2)) {
            showMessage("O win!!!");
        } else if (size == N * N) {
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

    @SuppressWarnings("Duplicates")
    private boolean check(int label) {
        boolean answer = true;
        for (int i = 0; i < N; i++) {
            if (turns[i][i] != label) {
                answer = false;
                break;
            }
        }
        if (answer) return true;

        answer = true;
        for (int i = 0; i < N; i++) {
            if (turns[i][N - i - 1] != label) {
                answer = false;
                break;
            }
        }
        if (answer) return true;

        for (int k = 0; k < N; k++) {
            answer = true;
            for (int i = 0; i < N; i++) {
                if (turns[k][i] != label) {
                    answer = false;
                    break;
                }
            }
            if (answer) return true;
        }

        for (int k = 0; k < N; k++) {
            answer = true;
            for (int i = 0; i < N; i++) {
                if (turns[i][k] != label) {
                    answer = false;
                    break;
                }
            }
            if (answer) return true;
        }

        return false;
    }

    private void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttons[i][j].setText("");
            }
        }
        turns = new int[N][N];
        turn = true;
        size = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
