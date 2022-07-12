package interfaces;


import tools.User;

import java.awt.*;

public interface ReadService {
    Point[] getSnakeBody();
    Point getFood();
    int getScore();
    boolean gameIsOver();
    User.COMMAND getDirection();
    int getSnakeSize();
    Point getSnakeHead();

}
