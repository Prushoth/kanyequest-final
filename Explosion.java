
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.IOException;
 import java.util.*;
 import java.awt.*;
 import java.awt.geom.*;
 import javax.imageio.ImageIO;
 import javax.swing.*;

public class Explosion {
    public BufferedImage sprite ;
    public double[] coords;
    public int damage;
    public int range;
    public boolean remove= false;

    public Explosion(double x, double y, int damage, int range){
        coords = new double[]{x, y};
        this.damage = damage;
        this.range = range;
        try{
            sprite  = ImageIO.read(new File("resources/images/explosion.gif"));
        } catch(IOException e){}

    }
    public int getDamage() {
        return damage;
    }

    public double getX(){
        return coords[0];
    }

    public double getY(){
        return coords[1];
    }

    public boolean getRemove(){
        return remove;
    }

    public double boom(double ex, double ey){
        double dist = Math.hypot(coords[0] - ex, coords[1] - ey);
        if(dist < range){
            return (dist * -damage / range) + damage; //formula for calculating splash damage, the further away, the less damage

        }
        return 0;


    }

    public int getRange(){
        return range;
    }
    public void draw(Graphics g){
        g.drawImage(sprite, (int)coords[0] , (int)coords[1], null);
    }

    /*public int inBlastRange(double ox, double oy){
        if(hypot whatever inside range){
            return distance from explosion center to object position
        }
    }*/

}
