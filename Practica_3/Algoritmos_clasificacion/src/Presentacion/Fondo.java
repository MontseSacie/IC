package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Fondo extends JPanel {

	/**
	 * Create the panel.
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imagen;

	public Fondo(String nombre) {
		super();
		imagen = new ImageIcon(getClass().getResource(nombre));
		setSize(1232, 792);
		initialize();
	}

	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(imagen.getImage(), 0, 0, d.width, d.height, null);
		this.setOpaque(false);
		super.paintComponent(g);
	}

	private void initialize() {
		this.setSize(1200, 750);
	}
}
