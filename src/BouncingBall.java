import java.awt.*;
import java.awt.geom.Ellipse2D;
public class BouncingBall implements Runnable {
    private static final int MAX_RADIUS = 40;
    private static final int MAX_SPEED = 15;
    private Field field;
    private int radius = 1;
    private int dx;
    private Color color;
    private double x;
    private double y;
    private int speed;
    private double speedX;
    private double speedY;
    public BouncingBall(Field field) {
        this.field = field;
            radius = new Double(Math.random() * MAX_RADIUS).intValue();
            speed = new Double(Math.round(5 * MAX_SPEED / radius)).intValue();
            if (speed > MAX_SPEED) {
                speed = MAX_SPEED;
            }
            double angle = Math.random() * 2 * Math.PI;
            speedX = 3 * Math.cos(angle);
            speedY = 3 * Math.sin(angle);
            color = new Color((float) Math.random(), (float) Math.random(),
                    (float) Math.random());
            x = Math.random() * (field.getSize().getWidth() - 2 * radius) + radius;
            y = Math.random() * (field.getSize().getHeight() - 2 * radius) + radius;
            Thread thisThread = new Thread(this);
            thisThread.start();
    }
    public void run() {
        try {
            while(true || radius > 0) {
                field.canMove(this);
                if (x + speedX <= radius) {
                    radius -= dx;
                    speedX = -speedX;
                    x = radius;
                } else
                if (x + speedX >= field.getWidth() - radius) {
                    radius -= dx;
                    speedX = -speedX;
                    x = new Double(field.getWidth()-radius).intValue();
                } else
                if (y + speedY <= radius) {
                    radius -= dx;
                    speedY = -speedY;
                    y = radius;
                } else
                if (y + speedY >= field.getHeight() - radius) {
                    radius -= dx;
                    speedY = -speedY;
                    y=new Double(field.getHeight()-radius).intValue();
                } else {
                    x += speedX;
                    y += speedY;
                }
                Thread.sleep(16-speed);
            }
            if(radius == 0){
                this.field = null;
            }
        } catch (InterruptedException ex) {
        }
    }
    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(x-radius, y-radius,
                2*radius, 2*radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }
    public void setDeacrease(int dx){
        this.dx = dx;
    }
}
