import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Powerup {
    public String type;
    public int duration, durationLeft;
    public double x, y;
    public BufferedImage sprite;
    public boolean finished;

    public Powerup(String type, double x, double y, int duration, BufferedImage sprite){
        this.type = type;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.duration = duration;
        durationLeft = duration;
        finished = false;
    }
    public void update(){
        durationLeft --;
        if(durationLeft <= 0){
            finished = true;
        }
    }

    public boolean ranOut(){
        return finished;
    }

    public boolean collide(double px, double py, int dist){
        return (Math.hypot(x - px, y  - py) < dist);

    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void changePos(int dx, int dy){
        x += dx;
        y += dy;
    }

    public String getType(){
        return type;
    }

    public boolean isType(String name){
        if(name.equals(type)){
            return true;
        }
        return false;
    }

    public void draw(Graphics g, KanyePanel k, int[] offset){
        g.drawImage(sprite, (int)Math.round(x + offset[0]),(int)Math.round(y + offset[1]), k);
    }
    //public String toString(){}
}

