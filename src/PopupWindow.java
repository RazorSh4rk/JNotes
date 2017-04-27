import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupWindow extends JDialog{
	
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	
	public PopupWindow(String msg){
		label.setText(msg);
		panel.setLayout(new FlowLayout());
		panel.add(label);
		this.add(panel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();
	}
}
