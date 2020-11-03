package core;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.ini4j.*;

public class ConfigLoader {

	File config;
	String FileFolderName = "MathTT";
	String FileFolder = System.getenv("APPDATA") + "\\" + FileFolderName;
	String configPATH = FileFolder + "\\" + "config.ini";
	Wini configIni;

	public ConfigLoader() {
		createConfig();
		loadConfig();

	}

	public void saveConfig() {

		try {
			configIni.put("Settings", "Resolution", Teacher.frame_res);
			configIni.put("Settings", "negNum", GUI.negativeNumbers);
			configIni.put("Settings", "add", GUI.add);
			configIni.put("Settings", "sub", GUI.subtract);
			configIni.put("Settings", "mul", GUI.multiply);
			configIni.put("Settings", "div", GUI.divide);
			configIni.put("Settings", "fontsize", GUI.fontsize);
			configIni.store();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void loadConfig() {

		// ########## Resolution
		if (configIni.get("Settings", "Resolution") == null) {
			Teacher.frame_res = 800;
		} else {
			Teacher.frame_res = Integer.parseInt(configIni.get("Settings", "Resolution"));
		}

		// ########## Resolution
		if (configIni.get("Settings", "fontsize") == null) {
			GUI.fontsize = 28;
		} else {
			GUI.fontsize = Integer.parseInt(configIni.get("Settings", "fontsize"));
		}

		// ########## Negative Numbers
		if (configIni.get("Settings", "negNum") == null) {
			GUI.negativeNumbers = false;
		} else {
			GUI.negativeNumbers = Boolean.parseBoolean(configIni.get("Settings", "negNum"));
		}

		// ########## Add
		if (configIni.get("Settings", "add") == null) {
			GUI.add = true;
		} else {
			GUI.add = Boolean.parseBoolean(configIni.get("Settings", "add"));
		}

		// ########## Sub
		if (configIni.get("Settings", "sub") == null) {
			GUI.subtract = true;
		} else {
			GUI.subtract = Boolean.parseBoolean(configIni.get("Settings", "sub"));
		}

		// ########## Mul
		if (configIni.get("Settings", "mul") == null) {
			GUI.multiply = true;
		} else {
			GUI.multiply = Boolean.parseBoolean(configIni.get("Settings", "mul"));
		}

		// ########## Div
		if (configIni.get("Settings", "div") == null) {
			GUI.divide = true;
		} else {
			GUI.divide = Boolean.parseBoolean(configIni.get("Settings", "div"));
		}

	}

	private void createConfig() {
		System.out.println("Searching for system");
		config = new File(configPATH);

		try {
			configIni = new Wini(config);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String os = System.getProperty("os.name").toUpperCase();
		if (os.contains("WIN")) {
			FileFolder = System.getenv("APPDATA") + "\\" + FileFolderName;
			System.out.println("Found windows");
		}
		if (os.contains("MAC")) {
			FileFolder = System.getProperty("user.home") + "/Library/Application " + "Support" + FileFolderName;
			System.out.println("Found mac");
		}
		if (os.contains("NUX")) {
			FileFolder = System.getProperty("user.dir") + "." + FileFolderName;
			System.out.println("Found linux");
		}

		System.out.println("Searching for resource folder");
		File directory = new File(FileFolder);

		try {
			config.createNewFile();
			System.out.println("config File has been created or already exists");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("config File couldnt be created or found");
		}

		if (directory.exists()) {
			System.out.println("Found folder");
		} else {
			directory.mkdir();
			infoBox("Welcome to " + Teacher.program_name + "! \n" + "It seems like this is your first start. \n"
					+ "A config file has been created at " + FileFolder + " \n"
					+ "Excuse the inconveniences, but you need to restart the program once.", "Setting up...");
			System.exit(0);
		}

	}

	public void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, Teacher.frame_title + " " + titleBar,
				JOptionPane.INFORMATION_MESSAGE);
	}

}
