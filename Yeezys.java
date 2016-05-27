
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Yeezys extends Powerup {
    private BufferedImage sprite;
    private int num;
    public Yeezys(double x, double y, BufferedImage sprite){
        super(x,y,sprite);
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        num = 0;

    }

    public int getNum(){
        return num;
    }

}
