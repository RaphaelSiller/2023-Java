package net.gobbz.ratenrechner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RatenRechnerGUI extends JFrame {
	private RatenRechner    ratenRechner = null;
	private TilgungsplanGUI tilgungsplan = null;
	private Dimension textFieldSize = new Dimension(100, 20);
	// Komponenten
	JPanel[]     panels;
	ButtonGroup  vornachschuessigGroup = null;
	JRadioButton vorschuessig          = null, nachschuessig = null;
	JTextField   barwert               = null;
	JButton      berechneBarwert       = null;
	JTextField   jahreszinssatz        = null;

	public RatenRechnerGUI() {
		ratenRechner = new RatenRechner();
		ratenRechner.setBarwert("50000");
		ratenRechner.setJahreszinssatz("5");
		ratenRechner.setLaufzeitInJahren("10");
		ratenRechner.setNachschuessig("true");
		ratenRechner.setRate("530.33");
		ratenRechner.setRatenProJahr("12");
		tilgungsplan = new TilgungsplanGUI(ratenRechner);
		tilgungsplan.setVisible(true);
		System.out.println(ratenRechner.getTilgungsplan());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 800);
		this.setLocation(50, 50);
//		this.setResizable(false);
		this.setTitle("Ratenrechner");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		// Panels initialisieren
		panels = new JPanel[8];
		for (int i = 0; i < 8; i++)
			panels[i] = new JPanel();

		// Überschrift
		panels[0].add(new JLabel("<html><h1>Ratenrechner</h1></html>"));

		// Vor- Nachschuessig
		vornachschuessigGroup = new ButtonGroup();
		vorschuessig = new JRadioButton();
		vorschuessig.setText("vorschüssig");
		nachschuessig = new JRadioButton();
		nachschuessig.setText("nachschüssig");
		vornachschuessigGroup.add(vorschuessig);
		vornachschuessigGroup.add(nachschuessig);
		panels[1].add(vorschuessig);
		panels[1].add(nachschuessig);

		// Barwert
		barwert = new JTextField();
		barwert.setMinimumSize(textFieldSize);
		berechneBarwert = new JButton("Berechne Barwert");
		panels[2].add(new JLabel("Barwert:"));
		panels[2].add(barwert);
		panels[2].add(berechneBarwert);

		// Jahreszinssatz
		jahreszinssatz = new JTextField();
		jahreszinssatz.setMinimumSize(textFieldSize);
		panels[3].add(new JLabel("Jahreszinssatz:"));
		panels[3].add(jahreszinssatz);
		
		// Alle Panels zum ContentPane hinzufügen
		for (JPanel panel : panels)
			this.add(panel);

	}

	public static void main(String[] args) {
		RatenRechnerGUI f = new RatenRechnerGUI();
		f.setVisible(true);
	}

}
