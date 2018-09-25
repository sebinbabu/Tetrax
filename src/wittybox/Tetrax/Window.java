package wittybox.Tetrax;

import wittybox.Tetrax.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements KeyListener {
	JFrame window = null;
	Game game = null;

	public Window() {
		this.game = new Game();
		this.window = new JFrame("Tetrax");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setSize(this.game.getBoard().getCols() * 20 + 60, this.game.getBoard().getRows() * 20 + 100);
		this.window.setResizable(false);
		this.window.getContentPane().add(this);
		this.window.setVisible(true);
		this.window.addKeyListener(this);
		this.render();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Board board = this.game.getBoard();
		int i, j;
		if(board == null)
			return;
		int cols = board.getCols(), rows = board.getRows();
		for(i = 0; i < rows; i++) {
			for(j = 0; j < cols; j++) {
				if(board.get(i, j)) {
					g.setColor(Color.BLACK);
        			g.fillRect(j * 20 + 30, i * 20 + 30, 20, 20);
				} else {
					g.setColor(Color.WHITE);
        			g.fillRect(j * 20 + 30, i * 20 + 30, 20, 20);
				}
			}
		}

		Point []points = this.game.getActiveShape().getPoints();
		int posX = this.game.getActiveShapePos().getX(), posY = this.game.getActiveShapePos().getY(); 
		for(Point p : points) {
			g.setColor(Color.BLACK);
			g.fillRect((posY + p.getY()) * 20 + 30, (posX + p.getX()) * 20 + 30, 20, 20);
		}
	}

	public void render() {
		this.repaint();
	}

	@Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
		if (code == KeyEvent.VK_DOWN) {
			this.game.moveShapeDown();
		} else if (code == KeyEvent.VK_UP) {
			this.game.rotateShape();
		} else if (code == KeyEvent.VK_LEFT) {
			this.game.moveShapeLeft();
		} else if (code == KeyEvent.VK_RIGHT) {
			this.game.moveShapeRight();
		}
		this.render();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String args[]) {
    	Window w = new Window();
    }


}