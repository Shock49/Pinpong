import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame implements ActionListener, KeyListener{
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private final GameCanva gameCanva;
    private final Sprite[] gameObject = new Sprite[2];

    private final JPanel panelTop = new JPanel(new GridLayout(1,3));
    private final JButton btnStart = new JButton("Start");
    private final JButton btnExit = new JButton("Exit");
    private Racket racket ;
    private Ball ball;
    private Image imgGameOver;
    private Image imgMain;
    private boolean gameOver;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow());
    }

    GameWindow(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setTitle("PingPong");
        setResizable(false);
        imgGameOver = new ImageIcon(classLoader.getResource( "GameOver.jpg")).getImage();
        imgMain = new ImageIcon(classLoader.getResource("Main.jpg")).getImage();

        btnExit.addActionListener(this);
        btnStart.addActionListener(this);
        btnStart.setFocusable(false);
        btnExit.setFocusable(false);
        super.addKeyListener(this);

        add(panelTop,BorderLayout.NORTH);
        panelTop.add(btnStart);
        panelTop.add(btnExit);

        gameCanva = new GameCanva(this);

        add(gameCanva,BorderLayout.CENTER);
        racket = new Racket(gameCanva.getWidth() / 2f,gameCanva.getHeight() - 25f);
        ball = new Ball(10,gameCanva.getWidth() / 2f,gameCanva.getHeight() / 2f, racket);



        setVisible(true);
    }

    public void initGame(){
        gameObject[0] = racket;
        gameObject[1] = ball;
    }

    public void onDrowFrame(GameCanva gameCanva, Graphics g){
        if(gameOver) g.drawImage(imgGameOver,0,180,null);
        render(gameCanva, g);
        update(gameCanva);
    }

    private void render(GameCanva gameCanva, Graphics g) {
        for (int i = 0; i < gameObject.length; i++) {
            if(gameObject[i] != null) gameObject[i].render(gameCanva,g);
        }
        if(gameObject[0] == null) g.drawImage(imgMain,0,0,null);
    }

    private void update(GameCanva gameCanva) {
        for (int i = 0; i < gameObject.length; i++) {
            if(gameObject[i] == null) continue;
            gameObject[i].update(gameCanva);
            if (gameObject[i] instanceof Ball){
                Ball boll = (Ball)gameObject[i];
                setTitle("PingPong                                                                  " +
                        "                       Score: " + boll.getCountTouchRocet());
                checkLose(boll,i);
            }else {
                Racket racket = (Racket) gameObject[i];

            }
        }
    }

    private void checkLose(Ball ball, int posArr){
        if(!ball.inGame){
            gameObject[posArr] = null;
            gameOver = true;
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == btnExit){
            System.exit(0);
        }
        if(src == btnStart){
            if(!btnStart.getText().equals("Restart")) {
                initGame();
                btnStart.setText("Restart");
            }else {
                gameOver = false;
                gameObject[1] = new Ball(10,gameCanva.getWidth() / 2f,gameCanva.getHeight() / 2f, racket);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(!racket.isAlife()) return;
        if(keyCode == KeyEvent.VK_RIGHT){
            racket.moveRight();
        }
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A){
            racket.moveLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
