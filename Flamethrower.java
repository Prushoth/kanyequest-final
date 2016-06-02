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
    public Flamethrower(BufferedImage icon, int damage, int range){
        super(icon, damage);
        this.range = range;
        weptype = "flamethrower";

    }
    @Override
    public ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls, BufferedImage pic){
        for(int i = 0; i< 50; i++){
            int randP = rn.nextInt(120)- 60;
            int randSpeed = rn.nextInt(15);
            Bullet b = new Bullet(x,y , ang+ (double)randP, pic, 2, 0.8, randSpeed);
            allbuls.add(b);
        }
        return allbuls;
    }

    @Override
    public int getFirerate() {
        return 0;
    }

}