package controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import App.GreedySnake;
import tools.GameDefaultParameters;

public class HomePageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeWindow_ModeEasy() throws Exception {
            GreedySnake greedySnake =new GreedySnake();
            greedySnake.initial_game(GameDefaultParameters.EASY);
            greedySnake.showGameWindow();
    }


    public void changeWindow_ModeMedium() throws Exception {
        GreedySnake greedySnake =new GreedySnake();
        greedySnake.initial_game(GameDefaultParameters.MEDIUM);
        greedySnake.showGameWindow();
    }


    public void changeWindow_ModeHard() throws Exception {
        GreedySnake greedySnake =new GreedySnake();
        greedySnake.initial_game(GameDefaultParameters.HARD);
        greedySnake.showGameWindow();
    }

}
