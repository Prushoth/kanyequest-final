

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
    public LaserBeam(BufferedImage sprite, int damage){
        super(sprite, damage);
        weptype = "laserbeam";
    }
    @Override
    public ArrayList<Bullet> shoot(double x,double y, double ang, ArrayList<Bullet> allbuls, BufferedImage bulpic){
        Bullet b = new Bullet(x,y, ang, bulpic, 4, 2,20);

        allbuls.add(b);
        return allbuls;

    }
    @Override
    public int getFirerate(){
        return 0;
    }
}
