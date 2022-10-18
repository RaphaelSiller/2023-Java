import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class fensterInDerMitte {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		Dimension screendDimensons = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = 500, frameHeight = 200;
		f.setBounds(screendDimensons.width/2-(frameWidth /2),screendDimensons.height/2-(frameHeight/2), frameWidth, frameHeight);
		f.setVisible(true);
	}
}
