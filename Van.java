
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
    private BufferedImage sprite;

    public Van(double x, double y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void move(double dx, double dy) {
        if (x <= 400) {
            x += dx;
            y += dy;
        }
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }


    public void draw(Graphics g, KanyePanel k, int[] offset) {
        //System.out.println((int) Math.round(x - offset[0] - 100) + " " + (int) Math.round(y - offset[1] - 50));
        g.drawImage(sprite, (int) Math.round(x + offset[0] - 100), (int) Math.round(y + offset[1] - 50), k);

    }

}