

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
public class RocketLauncher extends Weapon{
    private int firerate;
    private String weptype = "rocketlauncher";
    public RocketLauncher(BufferedImage icon, int damage, int firerate){
        super(icon, damage);
        this.firerate = firerate;
    }
    @Override
    public int getFirerate(){
        return firerate;
    }
    @Override
    public ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls , BufferedImage bulpic){
        allbuls.add(new Bullet(x,y, ang, bulpic,3,1,10));

        return allbuls;
    }

    @Override
    public String getName(){
        return weptype;
    }

}
