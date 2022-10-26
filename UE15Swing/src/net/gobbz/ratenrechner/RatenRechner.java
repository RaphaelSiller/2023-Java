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
	 * @return the nachschuessig
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
	 * @return the barwert
	 */
	public String getBarwert() throws RatenRechnerException {
		if (barwert <= 0)
			throw new RatenRechnerException("Barwert ist kleiner gleich 0");
		if (jahreszinssatz == Double.NaN || laufzeitInJahren == Double.NaN || ratenProJahr == Double.NaN
				|| rate == Double.NaN) {
			throw new RatenRechnerException("Jahreszinssatz, Laufzeit, Raten pro Jahr oder Rate nicht gesetzt");
		}
		return String.format("%.2f", barwert);
	}

	/**
	 * @param barwert the barwert to set
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
	 * @return the laufzeitInJahren
	 */
	public String getLaufzeitInJahren() /* throws RatenRechnerException */ {
		double nJahre = 0, uebrigerBetrag = barwert;
		if (nachschuessig) {
			while (uebrigerBetrag > 0) {
				uebrigerBetrag -= rate;
				uebrigerBetrag += (uebrigerBetrag * jahreszinssatz) / ratenProJahr;
				nJahre++;
			}
			return "" + nJahre;
		} else {
			// KP wos vorschuessig isch
		}
		return String.format("%.1f", laufzeitInJahren);
	}

	/**
	 * @param laufzeitInJahren the laufzeitInJahren to set
	 */
	public void setLaufzeitInJahren(String laufzeitInJahren) throws RatenRechnerException {
		double d_laufzeitInJahren;
		try {
			d_laufzeitInJahren = Double.parseDouble(laufzeitInJahren);
		} catch (NumberFormatException e1) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
		this.laufzeitInJahren = d_laufzeitInJahren;
	}

	/**
	 * @return the ratenProJahr
	 */
	public String getRatenProJahr() /* throws RatenRechnerException */ {
		return "" + ratenProJahr;
	}

	/**
	 * Mögliche Werte sind 1, 4, 6, 12
	 * 
	 * @param ratenProJahr the ratenProJahr to set
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
	 * @return the rate
	 */
	public String getRate() /* throws RatenRechnerException */ {
		return "" + rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) throws RatenRechnerException {
		double d_rate;
		try {
			d_rate = Double.parseDouble(rate);
		} catch (RatenRechnerException e1) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
		this.rate = d_rate;
	}

	/**
	 * @return the jahreszinssatz
	 */
	public void setJahreszinssatz(String jahreszinssatz) throws RatenRechnerException {
		try {
			this.jahreszinssatz = Double.parseDouble(jahreszinssatz) / 100;
		} catch (NumberFormatException e) {
			throw new RatenRechnerException("Kein gültiger Gleitkommawert");
		}
	}

	public String getTilgungsplan() throws RatenRechnerException {
		int    nPerioden     = (int) Math.round(laufzeitInJahren * ratenProJahr);
		double zinsen[]      = new double[nPerioden + 1];
		double restKapital[] = new double[nPerioden + 1];
		String htmlTable     = "";

		if (barwert == Double.NaN || jahreszinssatz == Double.NaN || ratenProJahr == 0 || rate == Double.NaN) {
			throw new RatenRechnerException("Führen Sie zuerst die Ratenberechnung durch");
		}

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

	public String setBarwertBerechnet() throws RatenRechnerException{
		if(laufzeitInJahren == 0 || !possibleRatenProJahr.contains("" + ratenProJahr) || rate == Double.NaN || jahreszinssatz == Double.NaN)
			throw new RatenRechnerException("Bitte kontrollieren Sie, ob Sie die notwendigen Werte eingegeben haben: \n-Laufzeit In Jahren \n-Raten Pro Jahr \n-Rate \n-Jahreszinssatz");
		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n) * (q - 1.));
		else
			barwert = rate * (Math.pow(q, n) - 1.) / (Math.pow(q, n - 1.) * (q - 1.));
		return String.format("%.2f", barwert);
	}

	public String setLaufzeitInJahrenBerechnet() {
		if(!possibleRatenProJahr.contains("" + ratenProJahr) || rate == Double.NaN || jahreszinssatz == Double.NaN || barwert == Double.NaN)
			throw new RatenRechnerException("Bitte kontrollieren Sie, ob Sie die notwendigen Werte eingegeben haben: \n-Raten Pro Jahr \n-Rate \n-Jahreszinssatz \n-Barwert");
		
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			laufzeitInJahren = (-Math.log((rate - barwert * (q - 1.)) / rate) / Math.log(q)) / ratenProJahr;
		else
			laufzeitInJahren = (1. - Math.log((q * rate - barwert * (q - 1.)) / rate) / Math.log(q)) / ratenProJahr;
		return String.format("%.1f", laufzeitInJahren);
	}

	public String setRateBerechnet() {
		if(laufzeitInJahren == 0 || !possibleRatenProJahr.contains("" + ratenProJahr) || jahreszinssatz == Double.NaN || barwert == Double.NaN)
			throw new RatenRechnerException("Bitte kontrollieren Sie, ob Sie die notwendigen Werte eingegeben haben: \n-Laufzeit in Jahren, \n-Raten Pro Jahr, \n-Jahreszinssatz, \n-Barwert");
		
		
		final double n = laufzeitInJahren * ratenProJahr;
		final double q = 1. + (jahreszinssatz / ratenProJahr) / 100.;
		if (nachschuessig)
			rate = barwert * (Math.pow(q, n) * (q - 1.)) / (Math.pow(q, n) - 1.);
		else
			rate = barwert * (Math.pow(q, n - 1.) * (q - 1.)) / (Math.pow(q, n) - 1.);

		return String.format("%.2f", rate);
	}
}
