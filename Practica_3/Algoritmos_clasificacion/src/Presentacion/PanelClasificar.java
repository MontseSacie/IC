package Presentacion;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.TextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Scrollbar;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelClasificar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaEjemplos;
	/**
	 * Create the panel.
	 */
	public PanelClasificar() {
		//666 x 464
		setBackground(new Color(255, 250, 250));
		
		textAreaEjemplos = new JTextArea();
		textAreaEjemplos.setEditable(false);
		JScrollPane jsp = new JScrollPane(textAreaEjemplos);
		textAreaEjemplos.setFont(new Font("Arial", Font.PLAIN,18));
		JButton btnAadirEjemplo = new JButton("A\u00F1adir Ejemplo");
		btnAadirEjemplo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Controlador.getInstance().getLloyd() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					String añadir = JOptionPane.showInputDialog(
							   null,
							   "Nuevo ejemplo:",
							   "Añadir ejemplo",
							   JOptionPane.PLAIN_MESSAGE);
					
					if(añadir != null){
						String[] lineas = añadir.split(",");
						ArrayList<String> aux = new ArrayList<>();
				    	for(String dato : lineas){		    		
				    		aux.add(dato);
				    	}	
					    try{
					    	double[] ejemplo = new double[aux.size()];
					   
						    for(int i = 0; i < aux.size(); i++){			
								ejemplo[i] = Double.parseDouble(aux.get(i));
							}			    
						    
						    if(ejemplo.length == Controlador.getInstance().getDatos().get(0).length){
						      	Controlador.getInstance().addEjemplo(ejemplo);
						      	refresh();
						    }
						    else {
						    	JOptionPane.showMessageDialog(null, "Formato incorrecto del ejemplo", "ERROR",
						            JOptionPane.ERROR_MESSAGE);
						    }
					    } catch( NumberFormatException a){
					    	JOptionPane.showMessageDialog(null, "Formato incorrecto del ejemplo", "ERROR",
						            JOptionPane.ERROR_MESSAGE);
					    }
					}
				}				
			}
		});
		btnAadirEjemplo.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		btnAadirEjemplo.setForeground(Color.WHITE);
		btnAadirEjemplo.setBackground(Color.BLACK);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(284, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(btnAadirEjemplo)
					.addContainerGap(331, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAadirEjemplo)
					.addGap(12))
		);
		setLayout(groupLayout);
	}
	
	public void refresh(){
		textAreaEjemplos.setEditable(true);
		textAreaEjemplos.setText(Auxiliar.ejemplos2String(Controlador.getInstance().getEjemplos()));
		textAreaEjemplos.setEditable(false);
		revalidate();
      	repaint();
	}

}
