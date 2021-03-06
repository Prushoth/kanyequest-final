import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Bullet {
    private final int REGULAR = 1;
    private final int FLAME = 2;
    private final int ROCKET = 3;


    private double ang;
    private double[] coords = new double[2];
    private BufferedImage sprite;
    private Ellipse2D bounds; //change later to image collision
    private int bulletType, speed;
    private double accel;
    private boolean movingLeft;
    public Bullet(double x, double y, double ang, BufferedImage sprite, int bultype, double accel, int speed ){
        coords[0] = x;
        coords[1] = y;
        this.ang = ang;
        this.sprite = sprite;
        bulletType = bultype;
        this.speed= speed;
        this.accel = accel;
        movingLeft = false;



    }
    public void setDirection(boolean tf){
        movingLeft = tf;
    }
    public int getType(){
        return bulletType;
    }

    public void move(){
        if (bulletType == FLAME){

           if (movingLeft ){
               ang *= -1.00001;
           }

           else{
               ang *= 1.00001;
           }

        }
        coords[0] += speed * Math.cos(ang);
        coords[1] += speed * Math.sin(ang);

    }
    public double getX(){
        return coords[0];
    }
    public double getY(){
        return coords[1];
    }
    public double getSpeed(){ return speed; }

    public boolean collide(double x, double y){
        if(x == coords[0] && y == coords[1]){
            return true;
        }
        return false;
    }

    public void draw(Graphics g, KanyePanel k, int[] offset){
        g.drawImage(sprite, (int)Math.round(coords[0]) - 5 + offset[0], (int)Math.round(coords[1]) - 5 + offset[1], k);
    }
}
