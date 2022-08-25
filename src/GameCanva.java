import javax.swing.*;
import java.awt.*;

public class GameCanva extends JPanel {
    private GameWindow gameWindow;

    public GameCanva(GameWindow gameWindow) {
        setBackground(Color.BLACK);
        this.gameWindow = gameWindow;
        setSize(gameWindow.getWidth(),gameWindow.getHeight() - 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameWindow.onDrowFrame(this,g);
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }
    public int getLeft(){ return 0;}
    public int getRight(){ return getWidth() - 1;}
    public int getTop(){ return 0;}
    public int getBottom(){ return getHeight() - 1;}
}
