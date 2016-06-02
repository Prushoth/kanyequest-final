/*
TODO - change coordinate storing system to
TODO - GET BULLETS TO COME OUT OF GUN, REQUIRES SLIGHT OFFSET
TODO - add all weapons to a list
TODO - assault rifle, shotgun, rocket launcher, flamethrower, chainsaw
TODO (!!)- make sprites rotate and animate !!!!!!!!!
TODO - make powerups and items
TODO - make all items, weps, and powerups available for pickup randomly and on a timer
TODO - make a custom map, discuss which setting to do first
TODO - add pixel collision to that map using masks and http://forum.codecall.net/topic/65950-pixel-perfect-collision-detection-use-for-your-java-games/
TODO - stop enemies from moving faster when scrolling because map is moving and player is not
TODO - make enemy into a superclass and split into subclass fan enemies and subclass paparazzi
TODO - make more complete HUD and objectives
TODO - menu and settings



 */

import java.awt.*;
import java.util.*;
import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;

public class MainGame extends JFrame implements ActionListener{
	private javax.swing.Timer gameTimer;
	private KanyePanel game;
	public MainGame(){
		super("Kanye Quest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 1000);
		gameTimer = new javax.swing.Timer(10, this);
		game = new KanyePanel(this);
		add(game);
		setResizable(false);
        setVisible(true);
	}

	public void start(){
		gameTimer.start();
	}

	public void actionPerformed(ActionEvent evt){
		game.repaint();
		game.move();
	}

	public static void main(String[] args){
		MainGame frame = new MainGame();
	}
}


class KanyePanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener{
    private final int XVAL = 0;
    private final int YVAL = 1;

	private boolean[] keys;
    private int[] offset, displacement, mapsize; //offset of map for scrolling
    private Weapon[] allweps;

	private ArrayList<Bullet> bullets= new ArrayList<Bullet>();
    private ArrayList<Fans> fans= new ArrayList<Fans>();
    private ArrayList<TripMine> tripmines = new ArrayList<TripMine>();
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    private ArrayList<Paparazzi> paparazzi = new ArrayList<Paparazzi>();
    private ArrayList<Van> vanList = new ArrayList<Van>();
    private ArrayList<Powerup> powerList = new ArrayList<Powerup>();

	private MainGame mainFrame;
    private Random rn = new Random();
	private Kanye player;
	private int mx , my, bullcounter, mainCounter;
	private double ang;

    private BufferedImage map, fanpic, bulletpic, sprite, lefttest, righttest, midtest, trippic, vanpic, yeezypic, vestpic;
    private BufferedImage[] kanyepics;

    private boolean edge;

	public KanyePanel(MainGame m){
		mainFrame = m;
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setSize(1500, 1000);

        keys = new boolean[KeyEvent.KEY_LAST+1];
        offset = new int[]{0, 0};
        displacement = offset;
        bullcounter = 25; //default weapon's firerate is 25
        mainCounter = 0;
        edge = false;

        try{
            map  = ImageIO.read(new File("resources/images/map.jpg"));
            mapsize = new int[]{map.getWidth(), map.getHeight()};
            System.out.println("1");
            fanpic =  ImageIO.read(new File("resources/images/enemy.png"));
            bulletpic = ImageIO.read(new File("resources/images/bullet.png"));
            kanyepics = new BufferedImage[]{ImageIO.read(new File("resources/images/playersprites/leftboot.png")), ImageIO.read(new File("resources/images/playersprites/middle.png")), ImageIO.read(new File("resources/images/playersprites/rightboot.png"))};
            System.out.println("2");
            allweps = new Weapon[]{new AssaultRifle(ImageIO.read(new File("resources/images/m4a4.png")), 10, 3, 20), new Shotgun(ImageIO.read(new File("resources/images/m4a4.png")), 25, 85), new Flamethrower(ImageIO.read(new File("resources/images/m4a4.png")), 10, 30), new RocketLauncher(ImageIO.read(new File("resources/images/m4a4.png")), 30, 20), new LaserBeam(ImageIO.read(new File("resources/images/m4a4.png")),1), new Pistol(ImageIO.read(new File("resources/images/m4a4.png")),10,10 )};
            trippic = ImageIO.read(new File("resources/images/tripmine.png"));
            vanpic = ImageIO.read(new File("resources/images/van.png"));
            System.out.println("3");
            yeezypic = ImageIO.read(new File("resources/images/YeezyBoost.png"));
            vestpic = ImageIO.read(new File("resources/images/armour.png"));
            player = new Kanye(600 + offset[0], 600 + offset[YVAL], kanyepics, allweps[2]);
            System.out.println("SUCCESS");
            //new Flamethrower

        }catch(IOException e){ System.out.println("ERROR"); }
	}

	public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(e.getKeyChar() == 'e'){
            tripmines.add(new TripMine(player.getX(), player.getY(), trippic));
        }
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	public void mouseReleased(MouseEvent e){
		player.setShooting(false);
	}
	public void mousePressed(MouseEvent e){
        System.out.println("clicked");
		player.setShooting(true);
	}
    public void mouseDragged(MouseEvent e){
		mx = e.getX();
		my = e.getY();
        player.changeAng(Math.atan2(my - (player.getY() + offset[YVAL]), mx - (player.getX() + offset[XVAL])));
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
        player.changeAng(Math.atan2(my - (player.getY() + offset[YVAL]), mx - (player.getX() + offset[XVAL])));
	}

    //redesign player movement system to make sure that the player's coordinates are not relative to the screen but relative to the map
    //requires two lists; one of players coords on screen and player coords on map??
    public void move(){
        if(keys[KeyEvent.VK_D]){ //strafe right
            if(player.getX() + offset[0] <= 1460){
                player.move(1, 0);
            }
            else if(offset[XVAL] >= -mapsize[XVAL]) {
                edge = true;
                player.move(1, 0);
                offset[XVAL] -= player.getSpeed();
                displacement[XVAL] = -1;
            }
        }
        if(keys[KeyEvent.VK_A]){ //strafe left
            if(player.getX() + offset[XVAL] >= 40){
                player.move(-1, 0);
            }
            else if(offset[XVAL] < 0) {
                edge = true;
                player.move(-1, 0);
                offset[XVAL] += player.getSpeed();
                displacement[XVAL] = 1;
            }
        }
        if(keys[KeyEvent.VK_W]){ //move UP
            if(player.getY() + offset[YVAL] >= 40){
                player.move(0, -1);
            }
            else if(offset[YVAL] < 0) {
                edge = true;
                player.move(0, -1);
                offset[YVAL] += player.getSpeed();
                displacement[YVAL] = 1;
            }
        }
        if(keys[KeyEvent.VK_S]){ //move DOWN
            System.out.println(player.getX() + offset[0] + " " + (player.getY() + offset[1]));
            if(player.getY() + offset[YVAL] <= 920){
                //System.out.println("shift");
                player.move(0, 1);
            }
            else if(offset[YVAL] >= -mapsize[YVAL]){
                edge = true;
                player.move(0, 1);
                offset[YVAL] -= player.getSpeed();
                displacement[YVAL] = -1;
            }
        }


    	/* MOVE RELATIVE TO THE MOUSE POSITION
    	if (keys[KeyEvent.VK_D]y){ //strafe right
			player.move((5*(Math.cos(ang + Math.toRadians(90)))),(5*(Math.sin(ang + Math.toRadians(90))))); //direction of movement calculated by cos and sin of angle to mouse
    	}
    	if (keys[KeyEvent.VK_A]){ //strafe left
			player.move(((5*(Math.cos(ang - Math.toRadians(90))))),(5*(Math.sin(ang - Math.toRadians(90)))));
    	}
    	if (keys[KeyEvent.VK_W]){ //move forward
    		player.move((5*Math.cos(ang)),(5*Math.sin(ang)));
    	}
    	if (keys[KeyEvent.VK_S]){ //move backwards
			player.move((-5*Math.cos(ang)), (-5*Math.sin(ang)));
    	}*/

		updateGame();

        edge = false;
        displacement = new int[]{0, 0};
    }

	public void updateGame() {
        //System.out.println(my - (player.getY() + offset[XVAL]) + " " + (mx - (player.getX() + offset[YVAL])));
        player.updatePlayer();
        mainCounter ++;
        bullcounter ++;

        //region SPAWNING OBJECTS
        if(mainCounter % 200 == 0){ //tries spawning new powerup at a set interval
            int randP = rn.nextInt(2);
            int[] randpos = getValidPoint();
            if (randP == 0) {
                powerList.add(new Yeezys(randpos[XVAL], randpos[YVAL], yeezypic));
            }
            else if(randP == 1){
                powerList.add(new Vest(randpos[XVAL], randpos[YVAL], vestpic));
            }
        }

		if(player.isShooting()){
            //System.out.println("true");
			if(bullcounter >= player.getWep().getFirerate()){
                //GET BULLETS TO COME OUT OF GUN, REQUIRES SLIGHT OFFSET
                bullets = player.getWep().shoot(player.getX(), player.getY(), player.getAng(), bullets, bulletpic);
				bullcounter = 0;
			}
		}

        if (fans.size() == 0) { //code for testing only
            for (int i  = 0; i < 10; i++) {
                fans.add(new Fans(200, 200, 50, 50, fanpic));
            }
        }

        if(vanList.size() == 0){
            int[] vanpos = getValidPoint();
            vanList.add(new Van(vanpos[XVAL], vanpos[YVAL], vanpic));
            paparazzi.add(new Paparazzi(vanpos[XVAL], vanpos[YVAL], 50, fanpic, vanList.get(0)));
            paparazzi.add(new Paparazzi(vanpos[XVAL], vanpos[YVAL], 50, fanpic, vanList.get(0)));
        }
        //endregion


        //region COLLISION DETECTION BETWEEN OBJECTS
        for (Iterator<Powerup> powerupIterator = powerList.iterator(); powerupIterator.hasNext();){
            Powerup p = powerupIterator.next();
            if (player.collide(p.getX(), p.getY(), 30)){
                player.pickup(p);
                powerupIterator.remove();
                System.out.println("picked up");
            }
        }

        for (Iterator<TripMine> mineiter = tripmines.iterator(); mineiter.hasNext(); ) {
            TripMine t = mineiter.next();
            for(Fans e : fans){
                if(e.collide(t.getX(), t.getY(), 40)){
                    explosions.add(t.blowUP());
                    mineiter.remove();
                }
            }
            if(edge){
                t.changePos(displacement[XVAL] * player.getSpeed(), displacement[YVAL] * player.getSpeed());
            }
        }

        for (Iterator<Bullet> buliter = bullets.iterator(); buliter.hasNext(); ) {
			Bullet b = buliter.next();
            boolean remove = false;
			b.move();

            for(Enemy e : fans){//.addAll(paparazzi)){
                if(e.collide(b.getX(), b.getY(), 35)){
                    if (player.getWep().equals("pistol")){
                          fans.remove(e);

                    }
                    e.changeHP(-player.getWep().getDamage());
                    remove = true;
                    if (b.getType() == 3){
                        explosions.add(new Explosion(b.getX(), b.getY(), 50, 70));
                    }
                }
            }
            for(Enemy e : paparazzi){//.addAll(paparazzi)){
                if(e.collide(b.getX(), b.getY(), 35)){
                    if(player.getWep().equals("pistol")){
                        paparazzi.remove(e);
                    }
                    e.changeHP(-player.getWep().getDamage());
                    if (!player.getWep().equals("laserbeam")) {
                        remove = true;
                    }

                    if (b.getType() == 3){
                        explosions.add(new Explosion(b.getX(), b.getY(), 50, 70));
                    }
                }

            }

			if (b.getX() > mapsize[XVAL] || b.getX() < 0 || b.getY() > mapsize[YVAL] || b.getY() < 0) {
				remove = true;
			}
            if(remove){
                buliter.remove();
            }

		}


        for (Iterator<Explosion> exploiter = explosions.iterator(); exploiter.hasNext(); ) {
            Explosion exp = exploiter.next();
            for(Enemy e : fans){
                e.changeHP((int)Math.round(-exp.boom(e.getX(), e.getY())));
            }
            exploiter.remove();
        }

        System.out.println(player.getX() + " curpos " + player.getY());
        System.out.println(offset[0] + " offset " + offset[1]);


        for(Iterator<Fans> faniter = fans.iterator();  faniter.hasNext();) {
            Fans curenemy  = faniter.next();
            double tmpang = Math.atan2(player.getY() - curenemy.getY(), player.getX() - curenemy.getX());
            double tmpx = 2 * Math.cos(tmpang);
            double tmpy = 2 * Math.sin(tmpang);

            //moving towards player
            double dx = player.getX() - curenemy.getX(); //delta x, total horizontal distance
            double dy = player.getY() - curenemy.getY(); //delta y, total vertical distance
            double dist = Math.max(1, Math.hypot(dx, dy));
            double d2 = Math.pow(dist, 2);
            tmpx -= 130 * dx / d2;
            tmpy -= 130 * dy / d2;

            for (Fans s : fans) {
                if (s != curenemy) { //enemy will always collide with itself
                    dx = curenemy.getX() - s.getX(); //delta x, total horizontal distance
                    dy = curenemy.getY() - s.getY(); //delta y, total vertical distance
                    dist = Math.max(1, Math.hypot(dx, dy));
                    if (dist < 60) {
                        d2 = Math.pow(dist, 2);
                        tmpx += 180 * dx / d2;
                        tmpy += 180 * dy / d2;
                    }
                }
            }

            for (Paparazzi p : paparazzi) {
                dx = curenemy.getX() - p.getX(); //delta x, total horizontal distance
                dy = curenemy.getY() - p.getY(); //delta y, total vertical distance
                dist = Math.max(1, Math.hypot(dx, dy));
                if (dist < 60) {
                    d2 = Math.pow(dist, 2);
                    tmpx += 180 * dx / d2;
                    tmpy += 180 * dy / d2;
                }
            }

            /*if (edge) {//compensate for faster movement due to scrolling at a speed of 5
                tmpx += displacement[XVAL] * (player.getSpeed() - 2);
                tmpy += displacement[YVAL] * (player.getSpeed() - 2);
            }*/
            curenemy.move(tmpx, tmpy, tmpang);
            curenemy.attack(player);

            if(curenemy.getHP() <= 0){
                faniter.remove();
            }
        }

        for(Iterator<Paparazzi> pappIter = paparazzi.iterator();  pappIter.hasNext();){
            Paparazzi p = pappIter.next();

            if(p.checkRemove()){
                vanList.remove(p.getHome());
                pappIter.remove();
                for (int i  = 0; i < 10; i++) {
                    fans.add(new Fans(1000 - 100 * i + offset[XVAL], offset[YVAL], 50, 50, fanpic));
                }
            }

            p.move(player, paparazzi, fans, displacement);
        }

        for(Iterator<Van> vanIter = vanList.iterator();  vanIter.hasNext();){
            Van curvan = vanIter.next();
            if(edge){
                curvan.changePos(displacement[XVAL] * player.getSpeed(), displacement[YVAL] * player.getSpeed());
            }
        }
        //endregion
	}

    public int[] getValidPoint(){
        int randx, randy;
        do{
            randx = rn.nextInt(5000);
            randy = rn.nextInt(5000);
        }while(randx < 0 || randx > 5000 || randy < 0 || randy > 5000); //checks if point is colliding or off the map


        return new int[]{randx, randy};

    }

    /*public boolean isOffscreen(int x, int y){
        if((x < player.getX()))
    }*/

    public boolean isOffscreen(double x, double y){
        boolean valid = true;
        //f()
        return true;
    }


    public void paintComponent(Graphics g){
        g.drawImage(map, offset[XVAL], offset[YVAL], this);

        for(Bullet b : bullets){
			b.draw(g, this, offset);
            if (b.getType() == 4){
                g.drawLine((int)player.getX(), (int)player.getY(), (int)b.getX(), (int)b.getY());
            }

		}

        for(Fans e: fans ){
            e.draw(g, this);
        }

        for (TripMine t : tripmines){
            t.draw(g, this);
        }

        for (Paparazzi p: paparazzi){
            if(isOffscreen(p.getX(), p.getY())){
                p.draw(g, this, false);
            }
            else{
                p.draw(g, this, false);
            }

        }

        for(Van v : vanList){
            v.draw(g, this);
        }
        for (Powerup p :  powerList){
            p.draw(g, this);
        }
        //System.out.println((player.getX()) + " " + (player.getY()));
		player.draw(g, this);

	}

}