package Presentacion;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

import javax.swing.JTextPane;

public class PanelReglas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @param reglas
	 */
	public PanelReglas(ArrayList<ArrayList<String>> reglas) {
		setBorder(new LineBorder(new Color(139, 69, 19)));
		setBackground(new Color(255, 250, 240));

		JTextPane textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setBackground(new Color(255, 204, 204));
		JScrollPane jsp = new JScrollPane(textArea);
		jsp.setBackground(new Color(255, 204, 204));
		textArea.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		textArea.setBorder(null);
		textArea.setOpaque(false);

		textArea.setText(escribirReglas(reglas));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE).addContainerGap()));
		setLayout(groupLayout);
	}

	private String escribirReglas(ArrayList<ArrayList<String>> reglas) {
		// TODO Auto-generated method stub
		String s = "";
		int num = 0;
		if (reglas != null) {
			s = "Reglas\n";
			for (int j = 1; j < reglas.size(); j++) {
				num += 1;
				s += " " + num + ". ";
				if (reglas.get(j).size() == 2) {
					if (reglas.get(j).get(0).equals("Siempre")) {
						s += reglas.get(j).get(0) + " " + reglas.get(j).get(1);
					} else {
						s += "Si " + reglas.get(j).get(0) + " entonces " + reglas.get(j).get(1);
					}
					s += "\n";
				} else {
					s += "Si ";
					for (int z = 0; z < reglas.get(j).size() - 1; z++) {
						if (z == reglas.get(j).size() - 2 && z != 0) {
							s += " y ";
						} else if (z > 0) {
							s += ", ";
						}
						s += reglas.get(j).get(z);
					}
					s += " entonces ";
					s += reglas.get(j).get(reglas.get(j).size() - 1);
					s += "\n";
				}
			}
		}
		return s;
	}

}
