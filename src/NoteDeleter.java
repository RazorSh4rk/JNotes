import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JLabel;

public class NoteDeleter extends NoteSelector{
	
	public NoteDeleter(){
		comboP.setVisible(false);
		colorCB.setVisible(false);
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		JLabel src = (JLabel)me.getSource();
		String s = src.getText();
		try(Connection conn = DriverManager.getConnection("jdbc:sqlite:notes.db");
				Statement st = conn.createStatement();){
				st.executeUpdate("DELETE FROM notes WHERE text LIKE '" + s + "'");
			
		}catch(Exception e){
				new PopupWindow("Something went wrong, try again. \n\n" + e.getMessage());
				e.printStackTrace();
		}
	}
}

