package graphe;

import algorithms.Welzl;
import algorithms.Naif;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keymaps implements KeyListener {
	private RootPanel rootPanel;

	public Keymaps(RootPanel rootPanel) {
		this.rootPanel = rootPanel;
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent event) {
		Circle c;
		long t;
		switch (event.getKeyChar()) {
			case 'w' ://welzel
				try {
					t = System.currentTimeMillis();
					c = (new Welzl()).calculCercleMin(this.rootPanel.getPoints());
					t = System.currentTimeMillis() - t;
					c.setColor(Color.RED);
					this.rootPanel.addCircleAndT(c, t);
					break;
				} catch (Exception var8) {
					;
				}
			case 'n' ://naif
				try {
					t = System.currentTimeMillis();
					c = (new Naif()).calculCercleMin(this.rootPanel.getPoints());
					t = System.currentTimeMillis() - t;
					c.setColor(Color.GREEN);
					this.rootPanel.addCircleAndT(c, t);
					break;
				} catch (Exception var8) {
					;
				}
			case 'r' :
				try {
					RandomPointsGenerator.main((String[]) null);
					DiamRace.readFile();
					this.rootPanel.refreshLine();
				} catch (Exception var7) {
					;
				}
			default :
				break;
			case 'h' :
				this.rootPanel.shiftLeftAll();
				break;
			case 'j' :
				this.rootPanel.shiftUpAll();
				break;
			case 'k' :
				this.rootPanel.shiftDownAll();
				break;
			case 'l' :
				this.rootPanel.shiftRightAll();
		}

	}
}