package com.a1arick.spbsu.control;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int N = 4;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private Button chosen = null;
    private Controller controller = new Controller(N);

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        root.getChildren().add(getGrid());
        Scene scene = new Scene(root, N * width, N * height);
        primaryStage.setTitle("Message");
        primaryStage.setScene(scene);
        primaryStage.show();
        clear();
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
                    b.setText(controller.getValue(row, column).toString());
                    b.disableProperty().setValue(true);
                    boolean choose = controller.choose(row, column);
                    if (chosen == null) {
                        chosen = b;
                    } else {
                        if (!choose) {
                            clear(chosen);
                            clear(b);
                        }
                        chosen = null;
                        if (controller.isWin()) {
                            showMessage("Win!");
                            clear();
                        }
                    }
                });
                gridPane.add(b, j * (j + (int) b.getWidth()), i);
            }
        }

        return gridPane;
    }

    private void showMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(s);
        alert.showAndWait();
        clear();
    }

    private void clear(Button button) {
        button.disableProperty().setValue(false);
        button.setText("");
    }

    private void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                clear(buttons[i][j]);
            }
        }
        controller.clear();
        chosen = null;
    }

    public static void main(String[] args) {
        if (N % 2 == 1) {
            System.out.println("Error N");
            return;
        }
        launch(args);
    }
}