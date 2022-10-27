package net.gobbz.browser;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

@SuppressWarnings("serial")
/**
 * 
 * @author endroidmc
 *
 */
public class MeinWeb extends JFrame {
	JTextField  url               = null;
	JLabel      urlLabel          = null;
	JButton     loadWebsite       = null;
	JToolBar    toolBar           = null;
	JEditorPane website           = null;
	JScrollPane browserScrollPane = null;

	static int browserWidth = 1500, browserHeight = 750;
	// Hoehe der ToolBar
	int toolBarSize = 40;

	public MeinWeb() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(browserWidth, browserHeight);
		this.setLocation(50, 50);
		this.setResizable(false);
		this.setTitle("Mein Web");
		// Starte maximiert
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		//Layoutmanager deaktivieren
		this.getContentPane().setLayout(null);

		// Definiere ToolBar
		this.toolBar = new JToolBar();
		this.toolBar.setBounds(0, 0, browserWidth, toolBarSize);

		// Definiere Addressleiste in ToolBar
		this.url = new JTextField("tfobz.net");
		// Füge Label + Shortcut(Alt + s) hinzu
		this.urlLabel = new JLabel("Adresse: ");
		this.urlLabel.setDisplayedMnemonic('S');
		this.urlLabel.setLabelFor(this.url);
		// Wenn Addressleiste ausgewählt wird, wird der ganze Text markiert
		this.url.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				url.selectAll();
			}
		});
		// Füge Addressleiste zu Toolbar hinzu
		this.toolBar.add(urlLabel);
		this.toolBar.add(url);

		// Button zum Laden der Seite
		this.loadWebsite = new JButton();
		this.loadWebsite.setText("→");

		// Funktionalität des Buttons
		this.loadWebsite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadWebsite();
			}
		});

		// Definiere Browser
		this.website = new JEditorPane();
		this.website.setEditable(false);
		this.website.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		this.website.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					url.setText(e.getURL().toString());
					loadWebsite();
				}
			}
		});
		this.browserScrollPane = new JScrollPane(website);
		this.browserScrollPane.setBounds(0, toolBarSize, browserWidth, browserHeight - toolBarSize);

		//Wenn Enter gedrueckt wird, soll Website geladen werden
		loadWebsiteKeyListener loadWebsiteKeyListener = new loadWebsiteKeyListener();
//		website.addKeyListener(loadWebsiteKeyListener);
		url.addKeyListener(loadWebsiteKeyListener);

		// Füge Komponenten zu ToolBar und später zum ContentPane hinzu
		this.toolBar.add(this.urlLabel);
		this.toolBar.add(this.url);
//		this.toolBar.addSeparator();
		this.toolBar.add(this.loadWebsite, BorderLayout.PAGE_END);
		this.getContentPane().add(toolBar);
		this.getContentPane().add(browserScrollPane);
	}

	public static void main(String[] args) {
		MeinWeb browser = new MeinWeb();
		browser.setVisible(true);
	}

	private void loadWebsite() {
//		System.out.println("Load Website");
		try {
//			throw new IOException();
			if(url.getText().isEmpty())
				throw new IOException();
			// Fügt https:// hinzu, wenn sich dies noch nicht in der url befindet
			if (url.getText().length() < 8 || url.getText().substring(0, 8).equals("https://"))
				website.setPage(url.getText());
			else if (url.getText().length() < 8 ||url.getText().substring(0, 7).equals("http://"))
				website.setPage(url.getText());
			else {
				url.setText("https://" + url.getText());
				website.setPage(url.getText());
			}
			// Setze Titel des Fensters
			this.setTitle("Mein Web - " + url.getText());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, "Kann den Link nicht aufrufen", "Fehler", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class loadWebsiteKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				loadWebsite();
		}
		
	}
}
