/**
 * Created by Alex Xie on 6/2/2016.
 */

import java.awt.image.BufferedImage;
public class Animation {
    private int curFrame, animateLength, curLength;
    private Spritesheet sprites;
    private String name;
    private boolean loop, playing = false, playForward;

    public Animation(String name, int animateLength, Spritesheet sprites){
        this.name = name; //what the animation is used for
        this.animateLength = animateLength; //how long each image is shown on screen
        this.sprites = sprites;
        playForward = true;
        loop = true;
        curFrame = 0;
    }

    public void start(){
        playing = true;
    }

    public void stop(){
        playing = false;
    }

    public void restart(){
        curFrame = 0;
        playing = true;
    }

    public void update(){
        curLength ++;
        if(curLength >= animateLength){
            curLength = 0;
            curFrame += (playForward ? 1 : -1);
            if(curFrame > sprites.getNumSprites() || curFrame < 0){
                curFrame = 0;
            }
        }
    }

    public boolean isPlaying(){
        return playing;
    }

    public boolean isLooping(){
        return loop;
    }

    public BufferedImage getCurSprite(){
        return sprites.getSprite(curFrame);
    }
}
