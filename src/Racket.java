import java.awt.*;

public class Racket extends Sprite {

    private final int STEP = 20;
    private final boolean isAlife;

    Color color = new Color(0xFFFFFF);
    Racket(float x, float y){
        halfHeight = 5;
        halfWidth = 30;

        this.x = x;
        this.y = y;
        isAlife = true;
    }

    @Override
    public void render(GameCanva gameCanvas, Graphics g) {
        g.setColor(color);
        g.fillRect((int)getLeft(),(int)getTop(),(int)getWidth(),(int)getHeight());
    }

    @Override
    public void update(GameCanva gameCanvas) {
        int newX = MouseInfo.getPointerInfo().getLocation().x;
        setX(newX);
        if(getLeft() <= gameCanvas.getLeft()) setLeft(gameCanvas.getLeft());
        if(getRight() >= gameCanvas.getRight()) setRight(gameCanvas.getRight());

    }

    public boolean isAlife() {
        return isAlife;
    }

    public void moveLeft(){
        setLeft(getLeft() - STEP);
    }

    public void moveRight(){
        setRight(getRight() + STEP);
    }
    public void setX(int x){
        setLeft(x - halfWidth);
    }
}
