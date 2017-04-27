import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class NoteSelector extends JFrame implements MouseListener{
	ScrollPaneLayout layout = new ScrollPaneLayout();
	JScrollPane panel = new JScrollPane();
	JComboBox colorCB = new JComboBox();
	JPanel comboP = new JPanel();
	
	public NoteSelector(){
		comboP.setLayout(new FlowLayout());
		comboP.add(new JLabel("Color of the note: "));
		comboP.add(colorCB);
		colorCB.addItem("RED");
		colorCB.addItem("GREEN");
		colorCB.addItem("BLUE");
		JPanel p = new JPanel();
		BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
		p.setLayout(box);
		
		int layoutH = 0;
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
			Statement st = conn.createStatement();){
				ResultSet rs = st.executeQuery("SELECT * FROM notes");
				JPanel tmp = new JPanel();
				Box vBox = Box.createVerticalBox();
					
				System.out.println(layoutH);
					
				while(rs.next()){
					layoutH++;
					System.out.println(rs.getInt("id") + " " + rs.getString("text"));
					tmp.add(new JLabel(rs.getInt("id") + ""));
					JLabel txtLabel = new JLabel(rs.getString("text"));
					tmp.add(txtLabel);	
					txtLabel.addMouseListener(this);
					vBox.add(tmp);						
				}
				GridLayout grid = new GridLayout(layoutH,1);
				tmp.setLayout(grid);
				p.add(vBox);
					
		}catch(Exception e){
				e.printStackTrace();
		}
		
		p.add(comboP);
		JScrollPane scrollPane = new JScrollPane(p);	
		this.add(scrollPane);
	
		this.setSize(600, (layoutH + 1) * 50);

		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		JLabel src = (JLabel)me.getSource();
		String s = "" + colorCB.getSelectedItem();
		switch(s){
		case "RED" : new NoteWindow(src.getText(), Color.RED); pushToDb(src.getText()); break;
		case "BLUE" : new NoteWindow(src.getText(), Color.BLUE); pushToDb(src.getText()); break;
		case "GREEN" : new NoteWindow(src.getText(), Color.GREEN); pushToDb(src.getText()); break;
		}
	}

	private void pushToDb(String text){
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
			Statement st = conn.createStatement();){
				st.executeUpdate("INSERT INTO openNotes VALUES ('" + text + "')");		
			}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	//unused
	@Override
	public void mouseEntered(MouseEvent arg0) {	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {	
	}
}
