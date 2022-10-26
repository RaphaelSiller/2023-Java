package net.gobbz.ratenrechner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class TilgungsplanGUI extends JFrame {
	JEditorPane editorPane = null;
	JScrollPane scrollPane = null;
	JButton     speichern  = null;

	public TilgungsplanGUI(RatenRechner ratenRechner) {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(700, 800);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setTitle("Tilgungsplan");

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		this.setText(ratenRechner.getTilgungsplan());

		this.scrollPane = new JScrollPane(editorPane);
		this.add(scrollPane);
		// Scrolle hinauf zum Anfang
		editorPane.setCaretPosition(0);
	}

	public TilgungsplanGUI(String tilgungsPlanAsHTML) {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(700, 800);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setTitle("Tilgungsplan");

		// Tilgungsplan selbst
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		this.setText(tilgungsPlanAsHTML);

		this.scrollPane = new JScrollPane(editorPane);

		this.add(scrollPane);

		// Speichern
		speichern = new JButton("Speichern");
		speichern.setPreferredSize(new Dimension(120, 30));
		speichern.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("HTML", "html"));
				if (fileChooser.showSaveDialog(rootPane) == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					if (selectedFile.exists()) {
						if(JOptionPane.showConfirmDialog(fileChooser, "Die Datei ist bereits vorhanden. Überschreiben?", "Meldung", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
							return;
					}
					try {
						FileWriter writer = new FileWriter(selectedFile);
						writer.write(tilgungsPlanAsHTML);
						writer.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Fehler beim Speichern der Datei", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		this.add(speichern, BorderLayout.PAGE_END);

	}

	public void setText(String tilgungsPlanAsHTML) {
		editorPane.setText(tilgungsPlanAsHTML);
		// Scrolle hinauf zum Anfang
		editorPane.setCaretPosition(0);
	}
}
