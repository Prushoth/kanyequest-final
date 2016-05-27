
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Van {
    private double x, y;
    private boolean letsmove;
    private BufferedImage sprite;

    public Van(double x, double y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        letsmove = false;
    }

    public void move(double dx, double dy) {
        if (x <= 400) {
            x += dx;
            y += dy;
        }
    }

    public void changePos(double x, double y){
        this.x += x;
        this.y += y;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }


    public void draw(Graphics g, KanyePanel k) {
        g.drawImage(sprite, (int) Math.round(x) - 100, (int) Math.round(y) - 50, k);

    }

}