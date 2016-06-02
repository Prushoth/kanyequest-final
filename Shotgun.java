/**
 * Created by Alex on 2016-05-09.
 */
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class Shotgun extends Weapon  {
    private int firerate;
    private String weptype;
    public Shotgun(BufferedImage icon, int damage, int firerate){
        super(icon, damage);
        this.firerate = firerate;
        weptype = "shotgun";
    }

    @Override
    public int getFirerate(){
        return firerate;
    }

    @Override
    public ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls, BufferedImage pic){
        for(int i = -2; i <= 2; i ++) {
            allbuls.add(new Bullet(x, y, ang + Math.toRadians(i * 5), pic, 1,1,10));
            System.out.println("shoot");
        }
        return allbuls;
    }
}
