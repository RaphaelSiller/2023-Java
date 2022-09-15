package figurentest;

import java.awt.*;
import javax.swing.*;
import net.gobbz.geometrischefiguren.*;

public class GeometrischeFigurenGUI extends JFrame{
	public GeometrischeFigurenGUI() {
		super("GeometrischeFigurenGUI");
		setBounds(0,0,500,500);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		
		Punkt p = new Punkt(10,10);
		p.setFarbe(Color.RED);
		p.setBounds(10,10,100,100);
		contentPane.add(p);
		
		Rechteck r = new Rechteck(35,10,30,150);
		r.setFarbe(Color.BLUE);
		contentPane.add(r);
		
		Quadrat q = new Quadrat(85,10,150);
		q.setFarbe(Color.GREEN);
		q.setGefuellt(true);
		contentPane.add(q);
		
		Ellipse e = new Ellipse(10, 300, 50, 30);
		e.setFarbe(Color.CYAN);
		e.setGefuellt(true);
		contentPane.add(e);
		
		Kreis k = new Kreis(85, 300, 60);
		k.setFarbe(Color.MAGENTA);
		contentPane.add(k);
		
		setVisible(true);
		}
}
