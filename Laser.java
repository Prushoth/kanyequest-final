import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Laser {
    double x,y,ang;
    boolean collide;
    public Laser(double x, double y, double ang){
        this.x =x;
        this.y = y;
        this.ang = ang;
        collide = false;
    }

    public boolean onLine(double ex, double ey, double r){

        double newang = Math.atan2(x- ex, y - ey);
        double range = Math.atan2(r, Math.hypot(ex-x, ey- y));
        return newang - range < ang && ang<  newang + range;
    }




}
