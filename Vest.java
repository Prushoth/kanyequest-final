import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
public class Vest extends Powerup{
    private BufferedImage sprite;
    private int num;
    public Vest(double x, double y, BufferedImage sprite){
        super(x,y,sprite);
        this.x= x;
        this.y=y;
        this.sprite = sprite;
        num = 1;
    }

    public int getNum(){
        return num;
    }
}
