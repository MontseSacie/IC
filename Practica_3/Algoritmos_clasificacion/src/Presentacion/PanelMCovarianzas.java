package Presentacion;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelMCovarianzas extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelMCovarianzas() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		initGui();
	}
	public void initGui() {
		this.removeAll();
		for(int i = 0; i < Controlador.getInstance().getNumMCovarianzas(); i++) {
			JTextArea textArea = new JTextArea();
			String clase =  Controlador.getInstance().getClases().get(i);
			textArea.setText(Auxiliar.matrix2String(Controlador.getInstance().getBayes().getmCovarianzas().get(clase)));
			textArea.setEditable(false);
			JScrollPane scroll = new JScrollPane (textArea, 
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setBorder(new TitledBorder("Clase " + Controlador.getInstance().getClases().get(i)));
			add(scroll);
			textArea.setFont(new Font("Arial", Font.PLAIN, 18));
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scroll.getHorizontalScrollBar().setValue(0);
			   }
			});
		}
	}
	public void refresh() {
		initGui();
}

}
