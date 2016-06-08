

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
public class LaserBeam extends Weapon {
    private String weptype;
    private Laser laser;
    public LaserBeam(BufferedImage sprite, int damage){
        super(sprite, damage);
        weptype = "laserbeam";


    }
    @Override
    public ArrayList<Bullet> shoot(double x,double y, double ang, ArrayList<Bullet> allbuls, BufferedImage bulpic){return allbuls;}
    @Override
    public int getFirerate(){
        return 0;
    }


    @Override
    public String getName(){
        return weptype;
    }



}
