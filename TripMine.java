import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class TripMine {
    private double x, y;
    private BufferedImage sprite;
    private int damage, range;

    public TripMine(double x, double y, BufferedImage sprite) {
        this.x = x;
        this.y = y;

        this.sprite = sprite;

    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void changePos(double x, double y){
        this.x += x;
        this.y += y;
    }

    public Explosion blowUP(){
        return new Explosion(x, y, 25, 75);
    }

    public void draw(Graphics g, KanyePanel k){
        g.drawImage(sprite, (int)Math.round(x), (int)Math.round(y), k);
        //g.drawImage(sprite, -40 , -40, k);
    }
}



