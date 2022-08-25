import java.util.Random;

public class Vector {

    private float vectorX;
    private float vectorY;
    private float speed;
    private Random random;

    public Vector() {
        random = new Random();
        this.vectorX = (float)random.nextInt(10 - 4) + 4;
        this.vectorY = (float)random.nextInt(10 - 4) + 4;
        this.speed = 1f;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed){this.speed = speed; }

    public float moveLeft() {
        return -vectorX * speed;
    }
    public float moveRight() {
        return  vectorX * speed;
    }
    public float moveUp() {
        return -vectorY * speed;
    }
    public float moveDown() {
        return  vectorY * speed;
    }

    }
