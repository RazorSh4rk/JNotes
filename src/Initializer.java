import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Initializer extends JFrame implements ActionListener{
	
	JButton reset = new JButton("Reset database");
	JButton cancel = new JButton("Cancel");
	JButton delete = new JButton("Delete a note");
	JButton startup = new JButton("Reset startup notes");
	JPanel panel = new JPanel();
	
	public Initializer(){
		
		reset.addActionListener(this);
		cancel.addActionListener(this);
		delete.addActionListener(this);
		startup.addActionListener(this);
		
		panel.setLayout(new FlowLayout());
		panel.add(reset);
		panel.add(delete);
		panel.add(startup);
		panel.add(cancel);
		this.add(panel);
		
		this.setSize(200, 200);
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
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == reset){
			try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
				Statement st = conn.createStatement();){
				st.executeUpdate("DROP TABLE IF EXISTS notes");
				st.executeUpdate("DROP TABLE IF EXISTS openNotes");
				st.executeUpdate("CREATE TABLE IF NOT EXISTS notes (id INTEGER, text STRING)");
				st.executeUpdate("CREATE TABLE IF NOT EXISTS openNotes (text String)");
				new PopupWindow("Database reset.");
			}catch(Exception e){
				new PopupWindow("Something went wrong, try again.\n\n" + e.getMessage());
				e.printStackTrace();
			}
		}else if(a.getSource() == cancel){
			this.dispose();
		}else if(a.getSource() == delete){
			new NoteDeleter();
		}else if(a.getSource() == startup){
			try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
				Statement st = conn.createStatement();){
				st.executeUpdate("DROP TABLE IF EXISTS openNotes");
				st.executeUpdate("CREATE TABLE IF NOT EXISTS openNotes (text String)");
			}catch(Exception e){
				new PopupWindow("Something went wrong, try again.\n\n" + e.getMessage());
			}
		}
	}

}
