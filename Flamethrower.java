import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Flamethrower extends Weapon {
    private int range;
    private Random rn = new Random();
    private String weptype;
    private int firerate;
    public Flamethrower(BufferedImage icon, int damage, int range, int firerate){
        super(icon, damage);
        this.range = range;
        weptype = "flamethrower";
        this.firerate = firerate;



    }
    @Override
    public ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls, BufferedImage pic){
        for(int i = 0; i< 2; i++) {
            //double randP =  Math.PI/6* (rn.nextDouble()*2 -1);
            // /min + (interval)*random23 * Math.PI / 12 + (Math.PI / 12)/
            double randP = -Math.PI/6 + ( Math.PI/3) *(rn.nextDouble());

            int randSpeed = rn.nextInt(30) + 10;
            Bullet b = new Bullet(x, y, ang + randP, pic, 2, 1, randSpeed);
            if (randP >0) {

                b.setDirection(true);
            }

            allbuls.add(b);
        }
        return allbuls;
    }

    @Override
    public int getFirerate() {
        return firerate;
    }

    @Override
    public String getName(){
        return weptype;
    }


}