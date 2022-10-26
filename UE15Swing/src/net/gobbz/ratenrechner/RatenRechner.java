package net.gobbz.ratenrechner;

import java.util.Arrays;
import java.util.List;

public class RatenRechner {
	// Ob Zahlung nach Ende der Vertragsjahre erfolgt
	private boolean nachschuessig = false;
	// Kapital der Rente
	private double barwert          = Double.NaN;
	private double jahreszinssatz   = Double.NaN;
	private double laufzeitInJahren = Double.NaN;
	private int    ratenProJahr     = 0;
	private double rate             = Double.NaN;

	public static List<String> possibleRatenProJahr = Arrays.asList("1", "4", "6", "12");

	/**
	 * @return nachschuessig "true" oder "false"
	 */
	public String getNachschuessig() /* throws RatenRechnerException */ {
		return "" + nachschuessig;
	}

	/**
	 * 
	 * @param nachschuessig moegliche Werte sind "true" oder "false"
	 * @throws RatenRechnerException, wenn ungültiger Wert übergeben wird
	 */
	public void setNachschuessig(String nachschuessig) throws RatenRechnerException {
		if (nachschuessig.equals("true")) {
			this.nachschuessig = true;
			return;
		}
		if (nachschuessig.equals("false")) {
			this.nachschuessig = false;
			return;
		}
		throw new RatenRechnerException("Parameter muss entweder true oder false sein");
	}

	/**
	 * @return Barwert im format "%.2f", eine Zahl mit zwei Dezimalstellen
	 */
	public String getBarwert() {
		return String.format("%.2f", barwert);
	}

	/**
	 * Setzt Barwert
	 * 
	 * @param barwert muss groesser als 0 sein, ansonsten wird eine Exception
	 *                geworfen
	 * @throws RatenRechnerException, wenn Zahl nicht richtig zu ein Double geparsed
	 *                                werden konnte oder barwert kleiner als 0 ist
	 */
	public void setBarwert(String barwert) throws RatenRechnerException {
		double d_barwert;
		try {
			d_barwert = Double.parseDouble(barwert);
		} catch (NumberFormatException e1) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
		if (d_barwert <= 0)
			throw new RatenRechnerException("Barwert ist kleiner gleich 0");
		this.barwert = d_barwert;
	}

	/**
	 * @return laufzeitInJahren im Format "%.1f", eine Zahl mit einer
	 *         Dezimalstelle
	 */
	public String getLaufzeitInJahren() {
		return String.format("%.1f", laufzeitInJahren);
	}

	/**
	 * Setzt Laufzeit in Jahren
	 * 
	 * @param laufzeitInJahren muss groesser als 0 sein, ansonsten wird eine
	 *                         Exception geworfen
	 * @throws RatenRechnerException, wenn Zahl nicht richtig zu ein Double geparsed
	 *                                werden konnte oder laufzeitInJahren kleiner
	 *                                als 0 ist
	 */
	public void setLaufzeitInJahren(String laufzeitInJahren) throws RatenRechnerException {
		double d_laufzeitInJahren;
		try {
			d_laufzeitInJahren = Double.parseDouble(laufzeitInJahren);
		} catch (NumberFormatException e1) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
		if (d_laufzeitInJahren <= 0)
			throw new RatenRechnerException("Laufzeit ist kleiner gleich 0");
		this.laufzeitInJahren = d_laufzeitInJahren;
	}

	/**
	 * @return ratenProJahr im format "%.2f", Zahl mit zwei Dezimalstellen
	 */
	public String getRatenProJahr() {
		return String.format("%.2f", ratenProJahr);
	}

	/**
	 * Moegliche Werte sind 1, 4, 6, 12
	 * 
	 * @param ratenProJahr
	 * @throws RatenRechnerException, wenn Zahl nicht richtig in einen Integer
	 *                                geparsed werden konnte oder ratenProJahr nicht
	 *                                1, 4, 6 oder 12 ist
	 */
	public void setRatenProJahr(String ratenProJahr) throws RatenRechnerException {
		int d_ratenProJahr = 0;
		try {
			d_ratenProJahr = Integer.parseInt(ratenProJahr);
		} catch (RatenRechnerException e1) {
			throw new RatenRechnerException("Keine gültige Ganzzahl");
		}
		if (possibleRatenProJahr.contains("" + d_ratenProJahr))
			this.ratenProJahr = d_ratenProJahr;
		else
			throw new RatenRechnerException("Übergebener Wert steht nicht zur Auswahl");
	}

	/**
	 * @return ratenProJahr im format "%.2f", Zahl mit zwei Dezimalstellen
	 */
	public String getRate() {
		return String.format("%.2f", rate);
	}

	/**
	 * Setzt Rate
	 * 
	 * @param rate
	 * @throws RatenRechnerException, wenn Zahl nicht richtig in einen Double
	 *                                geparsed werden konnte oder rate kleiner als 0
	 *                                ist
	 */
	public void setRate(String rate) throws RatenRechnerException {
		double d_rate;
		try {
			d_rate = Double.parseDouble(rate);
		} catch (RatenRechnerException e1) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}

		if (d_rate <= 0)
			throw new RatenRechnerException("Laufzeit ist kleiner gleich 0");

		this.rate = d_rate;
	}

	/**
	 * Setzt Jahreszinssatz
	 * 
	 * @param jahreszinssatz
	 * @throws RatenRechnerException, wenn Zahl nicht richtig in einen Double
	 *                                geparsed werden konnte oder jahreszinssatz
	 *                                kleiner als 0 ist
	 */
	public void setJahreszinssatz(String jahreszinssatz) throws RatenRechnerException {
		double d_jahreszinssatz;
		try {
			d_jahreszinssatz = Double.parseDouble(jahreszinssatz) / 100;
		} catch (NumberFormatException e) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}

		if (d_jahreszinssatz <= 0)
			throw new RatenRechnerException("Laufzeit ist kleiner gleich 0");

		this.jahreszinssatz = d_jahreszinssatz;
	}

	/**
	 * Erstellt einen String im HTML-Format, welcher einen Tilgungsplan
	 * repraesentiert.
	 * 
	 * @return String im HTML-Format, welcher einen Tilgungsplan repraesentiert
	 * @throws RatenRechnerException, wenn folgende Werte noch nicht gesetzt wurden:
	 *                                -barwert
	 *                                -jahreszinssatz
	 *                                -ratenProJahr
	 *                                -rate
	 */
	public String getTilgungsplan() throws RatenRechnerException {
		int    nPerioden     = (int) Math.round(laufzeitInJahren * ratenProJahr);
		double zinsen[]      = new double[nPerioden + 1];
		double restKapital[] = new double[nPerioden + 1];
		String htmlTable     = "";

		// Kontrolliere, ob alle Werte gesetzt wurden
		if (barwert == Double.NaN || jahreszinssatz == Double.NaN || ratenProJahr == 0 || rate == Double.NaN) {
			throw new RatenRechnerException("Führen Sie zuerst die Ratenberechnung durch");
		}

		// Berechne alle Werte
		restKapital[0] = barwert;
		zinsen[0] = 0;
		if (nachschuessig) {
			for (int i = 1; i < nPerioden + 1; i++) {
				zinsen[i] = restKapital[i - 1] * jahreszinssatz / ratenProJahr;
				restKapital[i] = restKapital[i - 1] - rate + zinsen[i];
			}
		} else {
			for (int i = 1; i < nPerioden + 1; i++) {
				zinsen[i] = (restKapital[i - 1] - rate) * jahreszinssatz / ratenProJahr;
				restKapital[i] = restKapital[i - 1] - rate + zinsen[i];
			}
		}

		//@formatter:off
		// Formatierung zu HTML
		htmlTable = "<h2>T I L G U N G S P L A N</h2>\n"
				+ "<!-- Uebersicht der gesetzten Daten -->"
				+ "<table border=\"1\">\n"
				+ "	<tr>\n"
				+ "		<td>Zahlungsart:</td>\n"
				+ "		<td>" + (nachschuessig ? "Nachschüssig" : "Vorschüssig") + "</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>Barwert:</td>\n"
				+ "		<td>" + String.format("%.2f", barwert) + "</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>Jahreszinssatz:</td>\n"
				+ "		<td>" + String.format("%.2f", jahreszinssatz) + "</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>Laufzeit in Jahren:</td>\n"
				+ "		<td>" + String.format("%.1f", laufzeitInJahren) + "</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>Rückzahlungsart:</td>\n"
				+ "		<td>" + ratenProJahr + " Raten im Jahr</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>Rate:</td>\n"
				+ "		<td>" + String.format("%.2f", rate) + "</td>\n"
				+ "	</tr>\n"
				+ "</table>\n"
				+ "<br><br><br>"
				+ "<!-- Tilgungsplan -->"
				+ "<table border=\"1\">"
				+ "	<tr>"
				+ "		<th>Periode</th>"
				+ "		<th>Rate</th>"
				+ "		<th>Restkapital</th>"
				+ "		<th>Zinsen</th>"
				+ "	</tr>";
		//@formatter:on
		for (int i = 0; i < nPerioden + 1; i++) {
//			System.out.format("| %3d | %4.2f | %5.2f | %3.2f |\n", i, rate, restKapital[i], zinsen[i]);
			//@formatter:off
			htmlTable = htmlTable.concat(
					  "\t<tr>\n"
					+ "\t\t<td>" + i										+ "</td>\n"
					+ "\t\t<td>" + rate										+ "</td>\n"
					+ "\t\t<td>" + String.format("%.2f", restKapital[i]) 	+ "</td>\n"
					+ "\t\t<td>" + String.format("%.2f", zinsen[i])      	+ "</td>\n"
					+ "\t</tr>\n");
			//@formatter:on
		}
		htmlTable = htmlTable.concat("</table>");

		return htmlTable;
	}

	/**
	 * Berechnet aus den bereits gesetzten Daten den Barwert
	 * Formeln zur Errechnung der Werte stammt aus Rohmaterialien
	 * 
	 * @return neu errechneter Barwert im format "%.2f", eine Zahl mit zwei
	 *         Dezimalstellen
	 * @throws RatenRechnerException, wenn folgende Werte noch nicht gesetzt wurden:
	 *                                -laufzeitInJahren
	 *                                -ratenProJahr
	 *                                -rate
	 *                                -jahreszinssatz
	 */
	public String setBarwertBerechnet() throws RatenRechnerException {
		if (laufzeitInJahren == 0 || !possibleRatenProJahr.contains("" + ratenProJahr) || rate == Double.NaN
				|| jahreszinssatz == Double.NaN)
			throw new RatenRechnerException(
					"Bitte kontrollieren Sie, ob Sie die notwendigen Werte eingegeben haben: \n-Laufzeit In Jahren \n-Raten Pro Jahr \n-Rate \n-Jahreszinssatz");
		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n) * (q - 1.));
		else
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n - 1.) * (q - 1.));
		return String.format("%.2f", barwert);
	}

	/**
	 * Berechnet aus den bereits gesetzten Daten die Laufzeit in Jahren
	 * Formeln zur Errechnung der Werte stammt aus Rohmaterialien
	 * 
	 * @return laufzeitInJahren im Format "%.1f", eine Zahl mit einer
	 *         Dezimalstelle
	 * @throws RatenRechnerException, wenn folgende Werte noch nicht gesetzt wurden:
	 *                                -ratenProJahr
	 *                                -rate
	 *                                -jahreszinssatz
	 *                                -barwert
	 */
	public String setLaufzeitInJahrenBerechnet() throws RatenRechnerException {
		if (!possibleRatenProJahr.contains("" + ratenProJahr) || rate == Double.NaN || jahreszinssatz == Double.NaN
				|| barwert == Double.NaN)
			throw new RatenRechnerException(
					"Bitte kontrollieren Sie, ob Sie die notwendigen Werte eingegeben haben: \n-Raten Pro Jahr \n-Rate \n-Jahreszinssatz \n-Barwert");

		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			laufzeitInJahren = (-Math.log((rate - barwert * (q - 1.)) / rate) / Math.log(q)) / ratenProJahr;
		else
			laufzeitInJahren = (1. - Math.log((q * rate - barwert * (q - 1.)) / rate) / Math.log(q)) / ratenProJahr;
		return String.format("%.1f", laufzeitInJahren);
	}

	/**
	 * Berechnet aus den bereits gesetzten Daten die Rate
	 * Formeln zur Errechnung der Werte stammt aus Rohmaterialien
	 * 
	 * @return ratenProJahr im format "%.2f", Zahl mit zwei Dezimalstellen
	 * @throws RatenRechnerException, wenn folgende Werte noch nicht gesetzt wurden:
	 *                                -laufzeitInJahren
	 *                                -ratenProJahr
	 *                                -jahreszinssatz
	 *                                -barwert
	 */
	public String setRateBerechnet() throws RatenRechnerException {
		if (laufzeitInJahren == 0 || !possibleRatenProJahr.contains("" + ratenProJahr) || jahreszinssatz == Double.NaN
				|| barwert == Double.NaN)
			throw new RatenRechnerException(
					"Bitte kontrollieren Sie, ob Sie die notwendigen Werte eingegeben haben: \n-Laufzeit in Jahren, \n-Raten Pro Jahr, \n-Jahreszinssatz, \n-Barwert");

		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			rate = barwert * (Math.pow(q, n) * (q - 1.)) / (Math.pow(q, n) - 1.);
		else
			rate = barwert * (Math.pow(q, n - 1.) * (q - 1.)) / (Math.pow(q, n) - 1.);

		return String.format("%.2f", rate);
	}
}
