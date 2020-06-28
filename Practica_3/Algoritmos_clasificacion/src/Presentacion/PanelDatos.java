package Presentacion;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class PanelDatos extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelDatos() {
		setBackground(new Color(255, 250, 250));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initGui();
	}
	
	public void initGui() {
		this.removeAll();
		for(int i = 0; i < Controlador.getInstance().getNumClases(); i++) {
			JTextArea textArea = new JTextArea();
			textArea.setText(Auxiliar.datos2String(Controlador.getInstance().getDatosByIndiceClase(i)));
			textArea.setEditable(false);
			JScrollPane scroll = new JScrollPane (textArea, 
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setBorder(new TitledBorder("Clase " + Controlador.getInstance().getClases().get(i)));
			add(scroll);
			textArea.setFont(new Font("Arial", Font.PLAIN, 18));
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scroll.getVerticalScrollBar().setValue(0);
			   }
			});
		}
		
	}
	public void refresh() {
		initGui();
}

}
