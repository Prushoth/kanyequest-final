import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Chainsaw extends Weapon{
    String weptype;
    public Chainsaw(BufferedImage sprite, int damage){
        super(sprite, damage);
        weptype = "chainsaw";


    }

    @Override
    public int getFirerate(){return 0;};
    @Override
    public ArrayList<Bullet> shoot(double x, double y, double ang, ArrayList<Bullet> allbuls, BufferedImage bulpic){return allbuls;} ;


    @Override
    public String getName(){return weptype;};








}
