package core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class KeyHandler extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
		
		JTextField[] txtfld = Teacher.getTextfield();
		
		if (e.getKeyChar() == KeyEvent.VK_TAB) {
			for (int i = 0; i < txtfld.length; i++) {
				if (txtfld[i].hasFocus()) {
					if (i == txtfld.length - 1) {
						txtfld[0].requestFocus();
					} else {
						txtfld[i + 1].requestFocus();
					}
				}
			}
			
		}
	}
	
}
