package net.gobbz.tictactoe.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Diese von JButton abgeleitete Klasse dient zum Anzeigen und Auswählen der Tipps im
 * Spiel TicTacToe. Sie erweitert die Funktionalität indem entweder X oder 0 am Knopf
 * angezeigt wird. Der Text des Knopfes wird autmatisch auf die Größe des Knopfes 
 * skaliert.<br>
 * Beim Anlegen des Knopfes wird ihm die Feldnummer übergeben für welche der Knopf im
 * Spielfeld steht
 * @author Michael Wild
 */
public class TicTacToeJButton extends JButton
{
	/**
	 * Das am Knopf darzustellende Zeichen (entweder X oder 0)
	 */
	private String zeichen = null;
	/**
	 * Die Feldnummer des Feldes für das der Knopf im Spielfeld steht  
	 */
	private int feldnummer = -1;
	
	/**
	 * Konstruktor der die Feldnummer des Feldes für das der Knopf im TicTacToe-Spielfeld
	 * steht
	 * @param feldnummer
	 */
	public TicTacToeJButton(int feldnummer) {
		this.feldnummer = feldnummer;
	}
	
	/**
	 * Diese Methode sorgt dafür, dass das für den Knopf gesetzte Zeichen am Knopf in der
	 * Mitte und in der Größe des Knopfes gezeichnet wird 
	 * @param g der Grafikkontext
	 */
	public void paint(Graphics g) {
		super.paint(g);
		if (this.zeichen != null) {
			// Zeichensatz "Courier New" wird in der Normalschriftart angelegt. Dabei wird die
			// Schriftgröße automatisch auf die Größe des Knopfes angepasst
			g.setFont(new Font("Courier New",Font.PLAIN,this.getHeight()));
			// Breite des Zeichens wird ermittelt
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D r = fm.getStringBounds(this.zeichen,g);
			// Das Zeichen wird in der Mitte des Knopfes ausgegeben, getAscent() liefert die
			// tatsächliche Höhe des Zeichens
			g.drawString(this.zeichen,(int)(this.getWidth() - r.getWidth()) / 2,
				fm.getAscent());
		}
	}
	
	/*
	 * Getter- und Settermethoden
	 */
	/**
	 * Setzt ein "X" als Text im Knopf aber nur dann, wenn noch kein Zeichen gesetzt wurde
	 */
	public void setX() {
		if (this.zeichen == null)
			this.zeichen = "X";
	}
	/**
	 * Setzt ein "0" als Text im Knopf aber nur dann, wenn noch kein Zeichen gesetzt wurde
	 */
	public void set0() {
		if (this.zeichen == null)
			this.zeichen = "0";
	}
	/**
	 * Liefert die Position - also die Feldnummer - des Knopfes im Spielfeld zurück. dabei
	 * werden die Felder in einem 3x3-Spielfeld folgendermaßen durchnummeriert:<br>
	 * 0, 1, 2<br>
	 * 3, 4, 5<br>
	 * 6, 7, 8
	 * @return die Feldnummer des Knopfes
	 */
	public int getFeldnummer() {
		return this.feldnummer;
	}
}
