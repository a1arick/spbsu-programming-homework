package com.spbsu.a1arick.homework9.task1.old;

import com.spbsu.a1arick.homework9.task1.server.GameController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class BotVSBot extends Application implements TicTacToe{
    private static final int N = 3;
    private Bots bot = new Bots(N);
    private static final double magicShiftNumber = 20.0 / N;
    private static final double width = 100.0;
    private static final double height = 100.0;
    private Button[][] buttons = new Button[N][N];
    private GameController controller = new GameController(N);
    private int xWins = 0;
    private int oWins = 0;
    private int draw = 1;
    private int stroke = 1;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        root.getChildren().add(getGrid(primaryStage));
        Scene scene = new Scene(root, N * BotVSBot.width, N * BotVSBot.height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Pane getGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        Button newGame = new Button();
        newGame.setText("Delete result");
        newGame.setOnMouseClicked(e -> deleteResult());
        gridPane.add(newGame,1, 0);

        Button showStats = new Button();
        showStats.setText("Show stats");
        showStats.setOnMouseClicked(e -> showStats());
        gridPane.add(showStats,2, 0);

        Button go = new Button();
        go.setText("Go");
        go.setOnMouseClicked(e -> go());
        gridPane.add(go,0, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Button b = buttons[i][j] = new Button();
                gridPane.add(b, j, 1 + i);
                b.minWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber));
                b.minHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber + 2));
                b.maxWidthProperty().bind(primaryStage.widthProperty().divide(N).subtract(magicShiftNumber ));
                b.maxHeightProperty().bind(primaryStage.heightProperty().divide(N).subtract(magicShiftNumber + 2));
            }
        }
        return gridPane;
    }

    public void makeTurn() {
        if(stroke == 1){
            Pair<Integer, Integer> temp = bot.makeTurn();
            int botI = temp.getKey();
            int botJ = temp.getValue();
            if(controller.canMakeTurn(botI, botJ)){
                controller.makeTurn(botI,botJ);
                buttons[botI][botJ].setText("O");
                stroke = 2;
                check();
                makeTurn();
            }
            else {
                makeTurn();
            }
        }
        else if(stroke == 0){
            clear();
        }
        else{
            Pair<Integer, Integer> temp = bot.makeTurn();
            int botI = temp.getKey();
            int botJ = temp.getValue();
            if(controller.canMakeTurn(botI, botJ)){
                controller.makeTurn(botI,botJ);
                buttons[botI][botJ].setText("X");
                stroke = 1;
                check();
                makeTurn();
            }
            else {
                makeTurn();
            }
        }
    }

    public void check() {
        if (controller.check(1)) {
            oWins++;
            showMessage("O win!!!");
            stop();
        } else if (controller.check(2)) {
            xWins++;
            showMessage("X win!!!");
            stop();
        } else if (controller.isFinished()) {
            showMessage("Draw");
            stop();
        }
    }

    public void showMessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(s);
        alert.showAndWait();
        clear();
    }

    public void showStats() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(String.format("X wins: %d, O Wins: %d, Draw %d", xWins, oWins, draw));
        alert.showAndWait();
    }

    public void stop(){
        stroke = 0;
    }

    public void go(){
        stroke = 1;
        makeTurn();
    }

    public void clear() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                buttons[i][j].setText("");
            }
        }
        controller.clear();
    }

    public void deleteResult(){
        oWins = 0;
        xWins = 0;
        draw = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
