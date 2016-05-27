/**
 * Created by Alex on 2016-05-09.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public abstract class Weapon {
    public int buldamage;
    public BufferedImage sprite;
    public String weptype;

    public Weapon(BufferedImage i, int damage){
        sprite = i;
        buldamage = damage;
    }

    public abstract int getFirerate();

    public abstract ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls, BufferedImage pic);

    public int getDamage(){
        return buldamage;
    }

    public String getName(){
        return weptype;
    }

}
