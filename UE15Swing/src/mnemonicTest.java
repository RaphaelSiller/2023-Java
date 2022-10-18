import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class mnemonicTest {
	public static void main(String[] args) {
	    JTextField firstField = new JTextField(10);
	    JTextField middleField = new JTextField(10);
	    JTextField lastField = new JTextField(10);

	    JLabel firstLabel = new JLabel("First Name", JLabel.RIGHT);
	    firstLabel.setDisplayedMnemonic('F');
	    firstLabel.setLabelFor(firstField);

	    JLabel middleLabel = new JLabel("Middle Initial", JLabel.RIGHT);
	    middleLabel.setDisplayedMnemonic('I');
	    middleLabel.setDisplayedMnemonicIndex(7);
	    middleLabel.setLabelFor(middleField);

	    JLabel lastLabel = new JLabel("Last Name", JLabel.RIGHT);
	    lastLabel.setDisplayedMnemonic('L');
	    lastLabel.setLabelFor(lastField);

	    JPanel p = new JPanel();
	    p.setLayout(new GridLayout(3, 2, 5, 5));
	    p.add(firstLabel);
	    p.add(firstField);
	    p.add(middleLabel);
	    p.add(middleField);
	    p.add(lastLabel);
	    p.add(lastField);

	    JFrame f = new JFrame("MnemonicLabels");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(p);
	    f.pack();
	    f.setVisible(true);
	  }
}
