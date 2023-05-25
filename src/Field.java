
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Field extends JPanel {
    private boolean paused;
    private BouncingBall ball;

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            repaint();
        }
    });
    public Field() {
        ball = new BouncingBall(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.WHITE);
        repaintTimer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.paint((Graphics2D) g);
    }
    public void addBall() {
        ball = new BouncingBall(this);
    }
    public void deacrease(int dx){
        ball.setDeacrease(dx);
    }
    public synchronized void pause() {
        paused = true;
    }
    public synchronized void resume() {
        paused = false;
        notifyAll();
    }
    public synchronized void canMove(BouncingBall ball) throws
            InterruptedException {
        if (paused) {
            wait();
        }
    }
}