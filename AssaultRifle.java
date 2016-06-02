/**
 * Created by Alex on 2016-05-09.
 */
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public class AssaultRifle extends Weapon {
    private String weptype;
    private int damage, reloadtime, firerate;
    private Image icon;


    public AssaultRifle(BufferedImage icon, int damage, int reload, int firerate){
        super(icon, damage);
        reloadtime = reload;
        this.firerate = firerate;
        weptype = "assaultrifle";
    }
    public int reloadTime(){
        return reloadtime;
    }

    @Override
    public int getFirerate(){
        return firerate;
    }

    @Override
    public ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls, BufferedImage pic){
        allbuls.add(new Bullet(x, y , ang, pic, 1,1,10));
        return allbuls;
    }
}
