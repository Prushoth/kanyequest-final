import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public abstract class Powerup {
    public int counter;
    public double x, y;
    public BufferedImage sprite;
    public boolean finish;
    public Powerup(double x, double y, BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        finish = false;
    }
    public void countdown(){
        counter --;
        if (counter <= 0){
            finish = true;
        }
    }
    public boolean getFinish() {
        return finish;
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

    public void effect(Kanye k){}

    public abstract int getNum();
    public void draw(Graphics g, KanyePanel k){g.drawImage(sprite, (int)x,(int)y, k);}
    //public String toString(){}
}

