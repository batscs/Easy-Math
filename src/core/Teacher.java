package core;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

public class Teacher {

	static Timer focus;

	static String version = "1.0.0";

	// TODO Settings GUI
	// TODO Settings file saving
	// TODO Wenn man buchstaben in textfield eingibt = error

	static String program_name = "Easy Math";
	static String frame_title = program_name + " | Version: " + version;

	static int frame_res = 800;

	static GUI gui;
	
	static ConfigLoader cfgL;

	public static void main(String[] args) {

		cfgL = new ConfigLoader();
		cfgL.loadConfig();

		gui = new GUI(frame_res);

		gui.drawMatrix();
		gui.resetCalculations();

		focus = new Timer();

		focus.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				

			}
		}, 0, 100);

	}

	public static JTextField[] getTextfield() {
		return gui.getTextfield();
	}

}
