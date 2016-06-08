import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public abstract class Enemy {
    public double[] coords = new double[2];
    public double ang;
    public int hp, atkcounter;

    public Enemy(double x, double y, int hp){
        coords[0] = x;
        coords[1] = y;
        this.hp = hp;
        ang = 0;
        atkcounter = 50;

    }
    public double getX(){
        return coords [0];
    }

    public double getY(){
        return coords[1];
    }

    public int getHP(){
        return hp;
    }

    public void changeHP(int n){
        hp += n;
    }

    public void updateSprite(){
    }

    public boolean collide(double x, double y, int dist){
        if(Math.hypot(coords[0]  - x, coords[1]  - y) < dist){
            return true;
        }
        return false;
    }
    public void attack(Kanye k){}



}
