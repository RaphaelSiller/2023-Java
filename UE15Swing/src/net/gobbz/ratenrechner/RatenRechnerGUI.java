package net.gobbz.ratenrechner;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class RatenRechnerGUI extends JFrame {
	private RatenRechner    ratenRechner = null;
	private TilgungsplanGUI tilgungsplan = null;
	// Komponentengroesse
	private Dimension textFieldSize = new Dimension(100, 20);
	private Dimension labelSize     = new Dimension(130, 20);
	private Dimension ButtonSize    = new Dimension(120, 30);
	// Komponenten
	JPanel[] panels;
	JLabel[] labels;
	// vorschuessig/nachschuessig
	ButtonGroup  vornachschuessigGroup = null;
	JRadioButton vorschuessig          = null, nachschuessig = null;
	// Barwert
	JTextField barwert         = null;
	JButton    berechneBarwert = null;
	// Jahreszinssatz
	JTextField jahreszinssatz = null;
	// laufzeitInJahren
	JTextField laufzeitInJahren         = null;
	JButton    berechneLaufzeitInJahren = null;
	// Raten Pro Jahr
	JComboBox<String> ratenProJahr         = null;
	String[]          possibleRatenProJahr = { "1", "4", "6", "12" };
	// Rate
	JTextField rate         = null;
	JButton    berechneRate = null;
	// Zeige Tilgungsplan
	JButton zeigeTilgungsplan = null;

	public RatenRechnerGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setTitle("Ratenrechner");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		// Ratenrechner
		ratenRechner = new RatenRechner();

		// Panels initialisieren und erstellen
		panels = new JPanel[8];
		for (int i = 0; i < 8; i++)
			panels[i] = new JPanel();

		// labels Array initialisieren
		labels = new JLabel[5];

		// Überschrift
		panels[0].add(new JLabel("<html><h1>Ratenrechner</h1></html>"));

		/**
		 * @formatter:off
		 * 
		 * Erstellen aller Komponenten
		 * Normale Vorgehensweise beim erstellen
		 * eines Panels(hier wie eine "Zeile" behandelt) ist:
		 * -Label erstellen
		 * -Labelgroesse setzen
		 * -TextFeld erstellen
		 * -TextFeldgroesse setzen
		 * -TextFeld rechtsbündig einstellen
		 * -Listener für Textfeld setzen
		 * -Wenn Button vorgesehen ist
		 * -Button erstellen
		 * -Buttongroesse setzen
		 * -ActionListener für Button setzen
		 * -Komponenten Hinzufügen (Wenn kein Button vorgesehen ist, wird dieser mit
		 * 							einer Rigid Area, ein Platzhalter, ersetzt)
		 * @formatter:on
		 */

		// Vor- Nachschuessig
		// ButtonChange ist der ActionListener
		ButtonChange buttonChange = new ButtonChange();
		vornachschuessigGroup = new ButtonGroup();
		vorschuessig = new JRadioButton();
		vorschuessig.setText("vorschüssig");
		vorschuessig.addActionListener(buttonChange);
		nachschuessig = new JRadioButton();
		nachschuessig.setText("nachschüssig");
		nachschuessig.addActionListener(buttonChange);
		vornachschuessigGroup.add(vorschuessig);
		vornachschuessigGroup.add(nachschuessig);
		nachschuessig.setSelected(true);
		ratenRechner.setNachschuessig("true");

		panels[1].add(vorschuessig);
		panels[1].add(nachschuessig);

		// Barwert
		labels[0] = new JLabel("Barwert:");
		labels[0].setPreferredSize(labelSize);
		barwert = new JTextField();
		barwert.setPreferredSize(textFieldSize);
		barwert.setHorizontalAlignment(SwingConstants.RIGHT);
		barwert.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					ratenRechner.setBarwert(((JTextComponent) e.getSource()).getText());
				} catch (RatenRechnerException e2) {
					if (!((JTextComponent) e.getSource()).getText().isEmpty())
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		berechneBarwert = new JButton("Berechne Barwert!");
		berechneBarwert.setPreferredSize(ButtonSize);
		berechneBarwert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					barwert.setText(ratenRechner.setBarwertBerechnet());
				} catch (RatenRechnerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panels[2].add(labels[0]);
		panels[2].add(barwert);
		panels[2].add(berechneBarwert);

		// Jahreszinssatz
		labels[1] = new JLabel("Jahreszinssatz:");
		labels[1].setPreferredSize(labelSize);
		jahreszinssatz = new JTextField();
		jahreszinssatz.setPreferredSize(textFieldSize);
		jahreszinssatz.setHorizontalAlignment(SwingConstants.RIGHT);
		jahreszinssatz.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					ratenRechner.setJahreszinssatz(((JTextComponent) e.getSource()).getText());
				} catch (RatenRechnerException e2) {
					if (!((JTextComponent) e.getSource()).getText().isEmpty())
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panels[3].add(labels[1]);
		panels[3].add(jahreszinssatz);
		panels[3].add(Box.createRigidArea(ButtonSize));

		// Laufzeit in Jahren
		labels[2] = new JLabel("Laufzeit in Jahren:");
		labels[2].setPreferredSize(labelSize);
		laufzeitInJahren = new JTextField();
		laufzeitInJahren.setPreferredSize(textFieldSize);
		laufzeitInJahren.setHorizontalAlignment(SwingConstants.RIGHT);
		laufzeitInJahren.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					ratenRechner.setLaufzeitInJahren(((JTextComponent) e.getSource()).getText());
				} catch (RatenRechnerException e2) {
					if (!((JTextComponent) e.getSource()).getText().isEmpty())
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		berechneLaufzeitInJahren = new JButton("Berechne Laufzeit!");
		berechneLaufzeitInJahren.setPreferredSize(ButtonSize);
		berechneLaufzeitInJahren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					laufzeitInJahren.setText(ratenRechner.setLaufzeitInJahrenBerechnet());
				} catch (RatenRechnerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panels[4].add(labels[2]);
		panels[4].add(laufzeitInJahren);
		panels[4].add(berechneLaufzeitInJahren);

		// Raten pro Jahr
		labels[3] = new JLabel("Raten pro Jahr:");
		labels[3].setPreferredSize(labelSize);
		ratenProJahr = new JComboBox<String>(possibleRatenProJahr);
		ratenProJahr.setPreferredSize(textFieldSize);
		ratenProJahr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ratenRechner.setRatenProJahr((String) ratenProJahr.getSelectedItem());
				} catch (RatenRechnerException e2) {
					if (!((JTextComponent) e.getSource()).getText().isEmpty())
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panels[5].add(labels[3]);
		panels[5].add(ratenProJahr);
		panels[5].add(Box.createRigidArea(ButtonSize));

		// Rate
		labels[4] = new JLabel("Rate:");
		labels[4].setPreferredSize(labelSize);
		rate = new JTextField();
		rate.setPreferredSize(textFieldSize);
		rate.setHorizontalAlignment(SwingConstants.RIGHT);
		rate.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					ratenRechner.setRate(((JTextComponent) e.getSource()).getText());
				} catch (RatenRechnerException e2) {
					if (!((JTextComponent) e.getSource()).getText().isEmpty())
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		berechneRate = new JButton("Berechne Rate!");
		berechneRate.setPreferredSize(ButtonSize);
		berechneRate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rate.setText(ratenRechner.setRateBerechnet());
				} catch (RatenRechnerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panels[6].add(labels[4]);
		panels[6].add(rate);
		panels[6].add(berechneRate);

		// Zeige Tilgungsplan
		zeigeTilgungsplan = new JButton("Zeige Tilgungsplan!");
		zeigeTilgungsplan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tilgungsplan == null)
					tilgungsplan = new TilgungsplanGUI(ratenRechner.getTilgungsplan());
				else
					tilgungsplan.setText(ratenRechner.getTilgungsplan());

				tilgungsplan.setVisible(true);
			}
		});
		panels[7].add(zeigeTilgungsplan);

		// Alle Panels zum ContentPane hinzufügen
		for (JPanel panel : panels)
			this.add(panel);

	}

	public static void main(String[] args) {
		RatenRechnerGUI f = new RatenRechnerGUI();
		f.setVisible(true);
	}

	// Listeners and Adapters
	private class ButtonChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(vorschuessig))
				ratenRechner.setNachschuessig("false");
			else
				ratenRechner.setNachschuessig("true");

//			System.out.println(ratenRechner.getNachschuessig());
		}

	}

}
