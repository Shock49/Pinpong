import java.awt.*;

public class Ball extends Sprite {

    Color color = new Color(0xFFFFFF);
    private float moveX;
    private float moveY;
    private Racket racket;
    private final float stepSpeed = 0.1f;
    public boolean inGame = true;
    private int countTouchRocet;

    Vector vector;

    public Ball(int radius, float x, float y,Racket racket) {
        this.racket = racket;
        halfHeight = radius;
        halfWidth = halfHeight;
        vector = new Vector();
        countTouchRocet = 0;

        this.x = x;
        this.y = y;
        moveX = vector.moveLeft();
        moveY = vector.moveUp();
    }

    @Override
    public void render(GameCanva gameCanvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int)getLeft(),(int)getTop(),(int)getWidth(),(int)getHeight());
    }



    @Override
    public void update(GameCanva gameCanvas) {
        x += moveX;
        y += moveY;
        if(getLeft() <= gameCanvas.getLeft()){
            setLeft(gameCanvas.getLeft());
            moveX = vector.moveRight();
        }
        if(getRight() >= gameCanvas.getRight()){
            setRight(gameCanvas.getRight());
            moveX = vector.moveLeft();
        }
        if(getTop() <= gameCanvas.getTop()){
            setTop(gameCanvas.getTop());
            moveY = vector.moveDown();

        }
        if(getBottom() >= racket.getTop() && (getLeft() <= racket.getRight() & getRight() >= racket.getLeft()) ){
            moveY = vector.moveUp();
            moveX = -moveX;
            vector.setSpeed(vector.getSpeed() + stepSpeed);
            countTouchRocet++;
        }
        if(getBottom() >= gameCanvas.getBottom()){
            inGame = false;
        }
    }

    public int getCountTouchRocet(){
        return countTouchRocet;
    }

    public boolean isInGame() {
        return inGame;
    }
}

