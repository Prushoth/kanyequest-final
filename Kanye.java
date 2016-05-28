import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Kanye{

    private double[] coords = new double[2];
    private double ang;
    private BufferedImage[] sprites;
    private Weapon curwep;

    private int speed, hp;
    private boolean shooting;
    private boolean invincible;
    private Powerup[] powerupList;
    public Kanye(double x, double y, BufferedImage[] pics, Weapon curwep){

        this.curwep = curwep;
        sprites = pics;

        coords[0] = x;
        coords[1] = y;
        speed = 5;
        hp = 100;
        powerupList =  new Powerup[2];
        for (int i = 0;i <2; i++){
            powerupList[i] = null;
        }
    }

    public void setShooting(boolean s){
        shooting = s;
    }

    public boolean isShooting(){return shooting;}

    public Powerup[] getPowerupList(){return powerupList;}

    public boolean collide(double x, double y, int dist){
        return(Math.hypot(coords[0]  - x, coords[1]  - y) < dist);

    }

    public void pickup(Powerup p){
        powerupList[p.getNum()] =p;
    }

    public int getHP(){
        return hp;
    }

    public double getX(){ return coords[0]; }

    public double getY(){ return coords[1]; }

    public double getAng(){return ang; }

    public Weapon getWep(){ return curwep; }

    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int n ){speed = n;}

    public void changeAng(double a){ang = a; }

    public void changeHP(int n ){
        if (n< 0){
            if (powerupList[1]!= null){
                hp+=n+2;
            }
            hp+=n;
        }
        hp += n;
        System.out.println(hp);
        hp = (hp < 0) ? 0 : hp;
        hp = (hp > 100) ? 100 : hp;
    }

    public void move(double displacedx, double displacedy){
        //System.out.println("moving" + displacedx + " " + displacedy);
        coords[0] += displacedx * speed;
        coords[1] += displacedy * speed;
    }
    public void updatePlayer(){
        for (int i = 0; i <2; i++){
            if (powerupList[i] != null && powerupList[i].getFinish()){
                powerupList[i] = null;
            }
        }

        speed = powerupList[0] == null ? 5 : 7;

        if(hp <= 0){
            System.out.println("DEAD DEAD DEAD DEAD U SUCK DEAD DEAD DEAD DEAD");
            System.exit(0);
        }
    }
    public void draw(Graphics g, KanyePanel k, int[] offset){

        //is there a way to simplify this shit
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldAT = g2d.getTransform(); //save default transformations
        g2d.translate(coords[0] + offset[0], coords[1] + offset[1]); //move graphics2d object to center of image
        g2d.rotate(ang + Math.toRadians(90)); //rotate around the center of image
        g2d.drawImage(sprites[1], -40, -82, null); //codords are top left of image, gun sticks out 42 pixels
        g2d.setTransform(oldAT); //reset

        //g.drawImage(sprite, (int)Math.round(coords[0]) - 40, (int)Math.round(coords[1]) - 40, k);
        g.setColor(Color.blue);
        g.fillRect(10, 10, hp*3, 10);
    }
}