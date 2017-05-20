import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class TrayMenu extends TrayIcon implements ActionListener{
	static ImageIcon icon = new ImageIcon("image.png");
	static Image image = icon.getImage();

	MenuItem exitItem = new MenuItem("Exit");
	public TrayMenu() {
		super(image, "JNotes");

		final SystemTray tray = SystemTray.getSystemTray();
		final PopupMenu popup = new PopupMenu();
		
		exitItem.addActionListener(this);

		//Add components to pop-up menu
		
		popup.add(exitItem);

		this.setPopupMenu(popup);

		try {
			tray.add(this);
		} catch (Exception e) {
			System.out.println("TrayIcon could not be added.");
		}
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == exitItem){
			System.exit(0);
		}

	}

}
