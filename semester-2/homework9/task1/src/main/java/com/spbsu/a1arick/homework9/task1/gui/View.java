package com.spbsu.a1arick.homework9.task1.gui;

import com.spbsu.a1arick.homework9.task1.client.ClientRunnable;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.InetAddress;
import java.util.function.BiPredicate;
import java.util.function.Consumer;


/**
 * View for Tic-tac-toe game
 */
public class View extends Application {
    private static final int n = 3;
    private static final double magicShiftNumber = 19.0 / (n + 1);
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[n][n];
    private TextField gameIdField;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        root.getChildren().add(getGrid(primaryStage));
        Scene scene = new Scene(root, (n + 1) * View.width, (n + 1) * View.height);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxWidth(900);
        primaryStage.setMaxWidth(900);
        primaryStage.show();
    }

    private Pane getGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button b = buttons[i][j] = new Button();
                gridPane.add(b, j, 1 + i);
                b.minWidthProperty().bind(primaryStage.widthProperty().divide(n + 1).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(n + 1).subtract(magicShiftNumber));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(n + 1).subtract(magicShiftNumber));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(n + 1).subtract(magicShiftNumber));
            }
        }

        TextField hostField = new TextField();
        hostField.setPromptText("server host name");
        gridPane.add(hostField, n + 1, 1);

        TextField portField = new TextField();
        portField.setPromptText("server port");
        gridPane.add(portField, n + 1, 2);

        this.gameIdField = new TextField();
        gameIdField.setPromptText("game id");
        gridPane.add(gameIdField, n + 1, 3);

        Button findGameButton = new Button("Find game");
        findGameButton.setOnMouseClicked(event -> {
            try {
                InetAddress host = InetAddress.getByName(hostField.getText());
                int port = Integer.parseInt(portField.getText());
                ClientRunnable runnable = new ClientRunnable(host, port, new ViewClient(this));
                new Thread(runnable).start();
                hostField.disableProperty().setValue(true);
                portField.disableProperty().setValue(true);
                gameIdField.disableProperty().setValue(true);
                findGameButton.disableProperty().setValue(true);
            } catch (Exception e) {
                showMessage(e.getMessage());
            }
        });
        gridPane.add(findGameButton, n + 1, 4);

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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button button = buttons[i][j];
                button.setText("");
                button.disableProperty().setValue(true);
            }
        }
    }

    public String getGameId() {
        return gameIdField.getText();
    }

    public void nextTurn(BiPredicate<Integer, Integer> canMakeTurn, Consumer<Pair<Integer, Integer>> consumer) {
        toggleButtons(true);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button b = buttons[i][j];
                int row = i;
                int column = j;
                b.setOnMouseClicked(event -> {
                    if (canMakeTurn.test(row, column)) {
                        toggleButtons(false);
                        consumer.accept(new Pair<>(row, column));
                    }
                });
            }
        }
    }

    private void toggleButtons(boolean enable) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Button b = buttons[i][j];
                if (enable && b.getText().equals("")) {
                    b.disableProperty().setValue(false);
                } else if (!enable) {
                    b.disableProperty().setValue(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean isClosed() {
        return false;
    }
}