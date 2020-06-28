package Presentacion;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import Negocio.Auxiliar.LecturaDatos;
import Negocio.Objetos.Nodo;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelNuevoEjemplo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textAtributos;
	private JTextField textRespuesta;

	/**
	 * Create the panel.
	 */
	public PanelNuevoEjemplo() {
		setBorder(new LineBorder(new Color(102, 51, 0)));
		setBackground(new Color(255, 250, 240));

		JLabel lblComprobar = new JLabel("Comprobar para nuevo Ejemplo\r\n");
		lblComprobar.setForeground(new Color(102, 51, 0));
		lblComprobar.setFont(new Font("Sylfaen", Font.BOLD, 18));

		textAtributos = new JTextField();
		textAtributos.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		textAtributos.setBackground(new Color(255, 255, 224));
		textAtributos.setColumns(10);

		JLabel lblExplicacion = new JLabel("Atributos separados por \",\":");
		lblExplicacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblExplicacion.setForeground(new Color(0, 0, 0));
		lblExplicacion.setFont(new Font("Sylfaen", Font.PLAIN, 18));

		JLabel lblRespuesta = new JLabel("RESPUESTA:");
		lblRespuesta.setHorizontalAlignment(SwingConstants.LEFT);
		lblRespuesta.setForeground(Color.BLACK);
		lblRespuesta.setFont(new Font("Sylfaen", Font.PLAIN, 18));

		textRespuesta = new JTextField();
		textRespuesta.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		textRespuesta.setEditable(false);
		textRespuesta.setBackground(new Color(255, 255, 224));
		textRespuesta.setColumns(10);

		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textAtributos.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Debe introducir los valores de los atributos del neuvo ejemplo, separados por coma",
							"Ojo!", JOptionPane.INFORMATION_MESSAGE);
					textRespuesta.setText("");
				} else {
					ArrayList<String> ejemplo = LecturaDatos.leerNuevoEjemplo(textAtributos.getText());
					Nodo raiz = Controlador.getInstance().getArbol();
					if (raiz == null) {
						JOptionPane.showMessageDialog(null,
								"Primero ejecuta el algoritmo, para obtener el árbol de decisión", "Ojo!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						String resultado = Controlador.getInstance().comprobarEjemplo(ejemplo,raiz);
						if (resultado.equals("")) {
							JOptionPane.showMessageDialog(null, "No hay solución alcanzable para este ejemplo",
									"Lo siento", JOptionPane.INFORMATION_MESSAGE);
						} else {
							textRespuesta.setText(resultado);
						}
					}

				}
			}
		});
		btnComprobar.setBackground(new Color(255, 182, 193));
		btnComprobar.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(textAtributos, 263, 263, 263)
										.addContainerGap(23, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblExplicacion, 0, 0, Short.MAX_VALUE).addGap(23))))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblRespuesta, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(textRespuesta, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(71, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(86).addComponent(btnComprobar).addContainerGap(95,
						Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblComprobar)
						.addContainerGap(23, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblComprobar).addGap(18)
				.addComponent(lblExplicacion, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textAtributos, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRespuesta, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRespuesta, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE).addComponent(btnComprobar)
				.addGap(46)));
		setLayout(groupLayout);

	}
}
