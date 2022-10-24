package net.gobbz.ratenrechner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class TilgungsplanGUI extends JFrame {
	JEditorPane editorPane = null;
	JScrollPane scrollPane = null;

	public TilgungsplanGUI(RatenRechner ratenRechner) {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(700, 800);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setTitle("Tilgungsplan");
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		editorPane.setText(ratenRechner.getTilgungsplan());
		
		this.scrollPane = new JScrollPane(editorPane);
		this.add(scrollPane);
//		System.out.println(ratenRechner.getTilgungsplan());
	}
}
