package model_data;

import interfaces.DataService;
import tools.GameDefaultParameters;
import tools.User;


import java.awt.*;

public class GameModel implements DataService {
    private Point food;
    private Snake snake;
    private Boolean gameOver;
    private User.COMMAND direction;
    private int score;


    public GameModel(){ }





    public boolean gameIsOver(){
        return this.gameOver;
    }

    public int getScore(){
        return this.score;
    }

    @Override
    public void init() {
        snake = new Snake();
        food =  new Point(5,5);
        gameOver = false;
        direction = User.COMMAND.NONE;
        score = 0;
    }

    @Override
    public Point[] getSnakeBody() {
        return this.snake.getBody();
    }

    @Override
    public Point getFood() {
        return this.food;
    }
    @Override
    public  void  setFood(Point p){
        this.food=p;
    }
    @Override
    public void setGameOver(Boolean value){
        this.gameOver=value;
    }

    public void  incrementScore(int value){
        this.score=score+value;
    }

    public  User.COMMAND getDirection(){
        return this.direction;
    }

    @Override
    public int getSnakeSize() {
        return this.snake.getSize();
    }

    public  Point getSnakeHead(){
       return snake.getHead();
    }
    public void setDirection(User.COMMAND c){
        this.direction=c;
    }

    @Override
    public void incrementSize() {
            this.snake.incrementSize();
    }


}
