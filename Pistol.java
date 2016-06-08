

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
public class Pistol extends Weapon{
    private int firerate;
    private String weptype;
    public Pistol(BufferedImage sprite, int damage, int firerate){
        super(sprite, damage);
        this.firerate = firerate;
        weptype = "pistol";

    }
    @Override
    public ArrayList<Bullet> shoot(double x , double y, double ang , ArrayList<Bullet> allbuls, BufferedImage bulpic){
        allbuls.add(new Bullet(x,y, ang, bulpic,1, 1, 10));
        return allbuls;
    }
    @Override
    public int getFirerate(){
        return firerate;
    }

    @Override
    public String getName(){
        return weptype;
    }



}
