package view;


import interfaces.ReadService;
import interfaces.RequireReadService;
import interfaces.ViewerService;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tools.GameDefaultParameters;


import java.awt.Point;

import static tools.GameDefaultParameters.*;


public class GamePane extends BorderPane implements RequireReadService, ViewerService {


    private  ReadService gameModel;
    private Canvas canvas;

    public GamePane(){

    }


    @Override
    public void bindReadService(ReadService service){
        this.gameModel=service;

    }

    //Fonctions permettant de dessiner l'ensemble  des objets dans l'interface graphique du jeu
    public  void paint( ){
        GraphicsContext gc=canvas.getGraphicsContext2D();
        //dessiner le fond
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CANEVAS_WIDTH, CANEVAS_HEIGHT);
        //dessiner le "snake"
        paintSnake(gc);

        //dessiner le Food
        gc.setFill(Color.YELLOW);
        Point food = gameModel.getFood();
        gc.fillOval(food.x * Cube_size, food.y * Cube_size, Cube_size, Cube_size);
        if(gameModel.getSnakeSize()>= SNAKE_SIZE_MAX){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font(20));
            gc.fillText("Congratulations! You got the highest score ："+ gameModel.getScore() , 50, 160);
        }

        if(gameModel.gameIsOver()){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font(20));
            gc.fillText("Game Over, presse 'R' to restart", 50, 200);
        }

        gc.setFill(Color.AQUAMARINE);
        gc.setFont(new Font(20));
        gc.fillText("Score:"+ gameModel.getScore()+" ", 40, 40);
    }



    public void paintSnake(GraphicsContext gc){

        Point[] snakeBody = gameModel.getSnakeBody();
        Point head =snakeBody[0];
        gc.setFill(Color.RED);
        //il y a un petit espace entre chaque grid de corps pour qu'on sache le longeur de "snake"
        gc.fillRect(head.x * Cube_size, head.y * Cube_size, Cube_size - 2, Cube_size - 2);
        for(int i=1; i < gameModel.getSnakeSize(); i++){
            Point p = snakeBody[i];
            gc.setFill(Color.BLUE);
            gc.fillRect(p.x * Cube_size, p.y * Cube_size, Cube_size - 2, Cube_size - 2);

            //si la tete a collison avec le corps,le couleur change en vert
            if (head.x == p.x && head.y == p.y) {
                gc.setFill(Color.GREEN);
                gc.fillRect(p.x * Cube_size, p.y * Cube_size, Cube_size - 2, Cube_size - 2);
            }
        }


    }

    @Override
    public void init() {
        if(canvas==null) {
            canvas = new Canvas(CANEVAS_WIDTH, CANEVAS_HEIGHT);
            this.getChildren().add(canvas);

        }
    }


}
