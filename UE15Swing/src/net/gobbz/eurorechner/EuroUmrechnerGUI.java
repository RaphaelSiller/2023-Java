package net.gobbz.eurorechner;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class EuroUmrechnerGUI extends JFrame {
	private EuroUmrechner umrechner   = null;
	private JLabel[]      jLabels     = null;
	private JTextField[]  jTextFields = null;

	public EuroUmrechnerGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setTitle("Mein Web");

		this.getContentPane().setLayout(null);

		umrechner = new EuroUmrechner();
		jLabels = new JLabel[13];
		jTextFields = new JTextField[13];
		MeinTastaturAbhoerer meinTastaturAbhoerer = new MeinTastaturAbhoerer();
		for (int i = 0; i < 13; i++) {
			jLabels[i] = new JLabel(umrechner.WAEHRUNGEN[i] + ":");
			jTextFields[i] = new JTextField();

			if (i == 0) { // Euro
				jTextFields[i].setBounds(225, 15, 150, 25);
				jLabels[i].setBounds(25, 15, 195, 25);
			} else { // Andere Währungen
				jTextFields[i].setBounds(225, i * 25 + 35, 150, 25);
				jLabels[i].setBounds(25, i * 25 + 35, 195, 25);
			}
			jLabels[i].setHorizontalAlignment(SwingConstants.RIGHT);

			jTextFields[i].addKeyListener(meinTastaturAbhoerer);

			this.add(jLabels[i]);
			this.add(jTextFields[i]);
		}
	}

	public static void main(String[] args) {
		EuroUmrechnerGUI f = new EuroUmrechnerGUI();
		f.setVisible(true);
	}

	private class MeinTastaturAbhoerer extends KeyAdapter{

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField aktuellesTextFeld = (JTextField) e.getSource();
			// Finde ausgewaehlte Waehrung
			for (int i = 0; i < 13; i++) {
				if (jTextFields[i].equals(aktuellesTextFeld)) {
					umrechner.setWaehrung(i);
				}
			}
			try {
				umrechner.setBetrag(Double.parseDouble(aktuellesTextFeld.getText()));
			} catch (NumberFormatException ex) {

			}

			for (int i = 0; i < 13; i++) {
				if (!jTextFields[i].equals(aktuellesTextFeld))
					jTextFields[i].setText(String.format("%.2f", umrechner.getBetrag(i)));
			}
		}
	}

}
