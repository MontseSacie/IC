package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VistaPrincipal extends JFrame {

	private static VistaPrincipal instance;
	private JPanel contentPane;
	private PanelLloyd panelLloyd;
	private PanelBayes panelBayes;
	private PanelKMeans panelKMeans;
	/**
	 * Create the frame.
	 */
	public static VistaPrincipal getInstance() {
		if (instance == null)
			instance = new VistaPrincipal();
		return instance;
	}
	public VistaPrincipal() {
		setBackground(SystemColor.controlText);
		setTitle("Algoritmos de Clasificaci\u00F3n ~ Montserrat Sacie Alc\u00E1zar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1316, 833);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String style = "margin:6;padding:0px;width:50px;height:8px;border-radius:3px;text-align:center;border:none;";
		String html1 = "<html><body style = '" + style + "'>";
		String html2 = "</body></html>";
		
		JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
		pane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Controlador.getInstance().reiniciar();
				panelBayes.refresh();
				panelLloyd.refresh();
				panelKMeans.refresh();
			}
		});
		pane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pane.setForeground(SystemColor.activeCaptionText);
		pane.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		pane.setBackground(new Color(230, 230, 250));
		
		panelKMeans = new PanelKMeans();
		panelBayes = new PanelBayes();
		panelLloyd = new PanelLloyd();
		PanelInicio panelInicio = new PanelInicio();
		
		pane.add(html1 + "Inicio" + html2,panelInicio);
		pane.add(html1 + "K-means" + html2,panelKMeans);
		pane.add(html1 + "Bayes" + html2, panelBayes);
		pane.add(html1 + "Lloyd" + html2,panelLloyd);
		
		this.contentPane.add(pane, BorderLayout.CENTER);
		setLocationRelativeTo(VistaPrincipal.this);
		setResizable(false);
		this.setVisible(true);
	}
	
}
