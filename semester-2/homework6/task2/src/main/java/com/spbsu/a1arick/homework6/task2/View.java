package com.spbsu.a1arick.homework6.task2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View extends Application {

    private static final int N = 3;
    private static final double magicShiftNumber = 20.0 / N;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private Controller controller = new Controller(N);

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
                gridPane.add(b, j * (j + (int) b.getWidth()), i);

                b.minWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber));
            }
        }

        return gridPane;
    }

    private void check() {
        if (controller.check(1)) {
            showMessage("O win!!!");
        } else if (controller.check(2)) {
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

    private void clear() {
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
