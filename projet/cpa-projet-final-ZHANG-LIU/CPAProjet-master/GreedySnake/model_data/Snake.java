package model_data;

import tools.GameDefaultParameters;

import java.awt.*;

import static tools.GameDefaultParameters.SNAKE_SIZE_MAX;

public class Snake {

    //length of snake
    private int len;

    // the body of snake is a table of Point
    private  Point[] body;



    public Snake(){
        len = GameDefaultParameters.INITIAL_SIZE;
        body = new Point[SNAKE_SIZE_MAX];
        // Initialisation de la position du corps du serpent
        for(int i=0; i<len;i++){
            body[i] = new Point(len-i+1,0);
        }
    }

    public Point getHead(){
        return this.body[0];
    }

    public int getSize(){ return this.len; }
    public void incrementSize(){
        this.len++;
    }

    public Point[] getBody(){
        return this.body;
    }



}
