import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Paparazzi extends Enemy {
    private BufferedImage sprite;
    private double ang, returnang;
    private boolean takenpic, capturing, shouldRemove;
    private Van home;
    private int capturetime, speed;

    public Paparazzi(double x, double y, int hp, Van home) {
        super(x, y, hp);
        coords[0] = x;
        coords[1] = y;
        this.home = home;
        this.sprite = sprite;
        shouldRemove = false;
        speed = 2;
    }

    public void draw(Graphics g, KanyePanel k, int[] offset, boolean offscreen, BufferedImage[] sprites, double px, double py){

        double screenx = coords[0] + offset[0]; //position of object relative to screen, not map
        double screeny = coords[1] + offset[1];

        if(!offscreen){
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform oldAT = g2d.getTransform(); //save default transformations
            g2d.translate(screenx, screeny); //move graphics2d object to center of image
            g2d.rotate(ang); //rotate around the center of image
            if(capturing){
                g2d.drawImage(sprites[4], -30, -30, null); //change sprite to sprite of taking picture
            }
            else{
                g2d.drawImage(sprites[3], -30, -30, null); //coords are top left of image
            }

            g2d.setTransform(oldAT); //reset

            if(takenpic){
                //draw camera flash on entire screen, then fade
            }else{
                g.setColor(Color.yellow);
                g.fillRect((int)screenx - 30,(int)screeny + 40, (300 - capturetime) / 5, 10); //hp
            }
            //g.drawLine(500, 500, (int)Math.round(1 * Math.cos(ang + Math.toRadians(90))), (int)Math.round(1 * Math.sin(ang + Math.toRadians(90))));
        }
        else{ //no need to waste time drawing the object if its offscreen
            //how to draw line at edge of screen closes to obj??
            //g.drawLine(1000, 500, (int)Math.round(1 * Math.cos(ang)), (int)Math.round(1 * Math.sin(ang)));
            double newx,newy;
            double newang = Math.atan2(py-coords[1], px- coords[0]);
            if (newang> Math.toRadians(45) && newang< Math.toRadians(135) || newang> Math.toRadians(225) && newang< Math.toRadians(315)){
                if(px > coords[0]){
                    newx= px- coords[0] -px/coords[1]-py * (py-10);
                }
                else{
                    newx= px +coords[0] -px/coords[1]-py * (py-10);
                }
                if ( newang> Math.toRadians(225) && newang< Math.toRadians(315)) {
                    newy = 990;
                }
                else{
                    newy = 10;
                }
            }
            else{
                if(py > coords[1]){
                    newx= px- coords[0] -px/coords[1]-py * (py-10);
                }
                else{
                    newx= px +coords[0] -px/coords[1]-py * (py-10);
                }
                if ( newang> Math.toRadians(225) && newang< Math.toRadians(315)) {
                    newy = 990;
                }
                else{
                    newy = 10;
                }
            }



        }
        g.setColor(new Color(34, 139, 34));
        g.fillRect((int)screenx - 50,(int)screeny - 40, hp, 10); //hp


    }
    public boolean inRadius(Kanye k){
        if (Math.hypot(getX() - k.getX(), getY() - k.getY()) <= 500)  {
            return true;
        }
        return false;
    }
    @Override
    public void changeHP(int n){
        if(!capturing){
            hp += n;
        }
    }


    public void move(Kanye k, ArrayList<Paparazzi> plist, ArrayList<Fan> flist){
        //moving towards player
        double dx = k.getX() - coords[0];
        double dy = k.getY() - coords[1];
        double tmpang = Math.atan2(dy, dx);
        if (takenpic) { //angle is no longer towards player, is towards van
            tmpang = Math.atan2(home.getY() - coords[1], home.getX() - coords[0]);
        }

        ang = tmpang;
        double tmpx = speed * Math.cos(tmpang);
        double tmpy = speed * Math.sin(tmpang);
        double dist = Math.max(1, Math.hypot(dx, dy));
        double d2 = Math.pow(dist, 2);
        tmpx -= 130 * dx / d2;
        tmpy -= 130 * dy / d2;

        for (Paparazzi p : plist) {
            if (p != this) { //enemy will always collide with itself
                dx = coords[0] - p.getX(); //delta x, total horizontal distance
                dy = coords[1] - p.getY(); //delta y, total vertical distance
                dist = Math.max(1, Math.hypot(dx, dy));
                if (dist < 60) {
                    d2 = Math.pow(dist, 2);
                    tmpx += 180 * dx / d2;
                    tmpy += 180 * dy / d2;
                }
            }
        }

        for (Fan f : flist) {
            dx = coords[0] - f.getX(); //delta x, total horizontal distance
            dy = coords[1] - f.getY(); //delta y, total vertical distance
            dist = Math.max(1, Math.hypot(dx, dy));
            if (dist < 60) {
                d2 = Math.pow(dist, 2);
                tmpx += 180 * dx / d2;
                tmpy += 180 * dy / d2;
            }
        }

        //compensate for faster movement due to scrolling at a speed of 5
        //tmpx += displacement[0] * (k.getSpeed() - 2);
        //tmpy += displacement[1] * (k.getSpeed() - 2);

        if((Math.hypot(home.getX() - coords[0], home.getY() - coords[1]) < 10 && takenpic) || hp <= 0){
            shouldRemove = true;
        }

        if(takenpic || !inRadius(k)){
            coords[0] += tmpx;
            coords[1] += tmpy;
            capturing = false;
            capturetime = 300;
        }
        else{
            if(capturing){
                capturetime --;
                if(capturetime == 0){
                    takenpic = true;
                    capturing = false;
                    speed = 3;
                }
            }
            else{
                capturetime = 300;
                capturing = true;
            }
        }
    }

    public boolean picTaken(){return takenpic; }
    public boolean checkRemove(){
        return shouldRemove;
    }

    public Van getHome(){
        return home;
    }

    public void changePos(double x, double y){
        coords[0] += x;
        coords[1] += y;
    }
}
