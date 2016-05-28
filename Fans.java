import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Fans extends Enemy{
    private double ang;
    private BufferedImage sprite;
    private int atkspeed;

    public Fans(double x, double y, int hp, int atkspeed, BufferedImage sprite){
        super(x, y, hp, sprite);
        coords[0] = x;
        coords[1] = y;
        this.sprite = sprite;
        this.atkspeed = atkspeed;

    }

    public void move(double dx, double dy, double newang){
        coords[0] += dx;
        coords[1] += dy;
        ang = newang;
        //System.out.println(coords[0] + " " + coords[1]);
    }
    @Override
    public void attack(Kanye k){
        if (atkcounter >= atkspeed && Math.hypot(coords[0] - k.getX(), coords[1] - k.getY()) < 70){
            k.changeHP(-3);
            atkcounter = 0;
        }
        atkcounter ++;
    }
    
    public void draw(Graphics g, KanyePanel k, int[] offset){
        double screenx = coords[0] + offset[0]; //position of object relative to screen, not map
        double screeny = coords[1] + offset[1];

        //is there a way to simplify this shit
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldAT = g2d.getTransform(); //save default transformations
        g2d.translate(screenx, screeny); //move graphics2d object to center of image
        g2d.rotate(ang + Math.toRadians(90)); //rotate around the center of image
        g2d.drawImage(sprite, -30, -30, null); //coords are top left of image
        g2d.setTransform(oldAT); //reset

        g.setColor(Color.green);
        g.fillRect((int)screenx - 50,(int)screeny - 40, hp, 10); //hp
    }
}