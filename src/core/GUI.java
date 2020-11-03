package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.plaf.metal.MetalToggleButtonUI;

public class GUI {

	JFrame frame;
	JPanel panel;
	JButton resetBTN, confirmBTN, settingsBTN;
	Font font;

	// Label size: 6 x 2
	int rows = 2, columns = 6;
	JTextArea label[] = new JTextArea[rows * columns];
	JTextField textfield[] = new JTextField[rows * columns];

	// Settings
	static boolean add = true;
	static boolean subtract = true;
	static boolean multiply = true;
	static boolean divide = true;

	static boolean negativeNumbers = false;
	
	static int fontsize = 28;

	Color cGreen = Color.decode("#037d50");
	Color cRed = Color.decode("#8b0000");
	Color cOrange = Color.decode("#ff7b00");

	public GUI(int frameHeight) {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize((int) (frameHeight * 1.5), frameHeight);
		frame.setTitle(Teacher.frame_title);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);

		Teacher.cfgL.loadConfig();
		
		font = new Font("Trebuchet MS", Font.BOLD, fontsize);

	}

	public void drawMatrix() {
		drawPanel();
		drawLabels();
		drawTextfields();
		drawReset();
		drawConfirm();
		drawSettings();
	}

	void showSettings() {
		JFrame settings = new JFrame();

		settings.setSize(320, 470);
		settings.setTitle(Teacher.frame_title);
		settings.setVisible(true);
		settings.setResizable(false);
		settings.setLocationRelativeTo(null);

		JPanel pane = (JPanel) settings.getContentPane();
		pane.setLayout(null);
		pane.setBackground(Color.DARK_GRAY);

		int x = 20;
		int y = 0;
		int yHeader = 15;

		int yInc = 60;
		int width = 260;
		int height = 50;
		int heightHeader = 40;
		
		JTextArea header = new JTextArea("Settings");
		pane.add(header);
		header.setVisible(true);
		header.setBounds(x,yHeader,width,heightHeader);
		header.setFont(font);
		header.setBackground(Color.DARK_GRAY);
		header.setForeground(Color.white);
		header.setEditable(false);

		y += yInc;
		JToggleButton negativeCheck = new JToggleButton("Hard Mode");
		addSettingsButton(pane, negativeCheck, x, y, width, height, negativeNumbers);
		negativeCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				negativeNumbers = !negativeNumbers;

			}
		});

		y += yInc;
		JToggleButton addCheck = new JToggleButton("Additions");
		addSettingsButton(pane, addCheck, x, y, width, height, add);
		addCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				add = !add;

			}
		});

		y += yInc;
		JToggleButton subCheck = new JToggleButton("Subtractions");
		addSettingsButton(pane, subCheck, x, y, width, height, subtract);
		subCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				subtract = !subtract;

			}
		});

		y += yInc;
		JToggleButton multiplyCheck = new JToggleButton("Multiply");
		addSettingsButton(pane, multiplyCheck, x, y, width, height, multiply);
		multiplyCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				multiply = !multiply;

			}
		});

		y += yInc;
		JToggleButton divideCheck = new JToggleButton("Division");
		addSettingsButton(pane, divideCheck, x, y, width, height, divide);
		divideCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				divide = !divide;

			}
		});
		
		y += yInc;
		JButton saveBTN = new JButton("Save");
		pane.add(saveBTN);
		saveBTN.setBounds(x, y, width, height);
		saveBTN.setFont(font);
		saveBTN.setBackground(cOrange);
		saveBTN.setForeground(Color.white);
		saveBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Teacher.cfgL.saveConfig();

			}
		});

	}

	void addSettingsButton(JPanel pane, JToggleButton btn, int x, int y, int width, int height, boolean selected) {
		pane.add(btn);
		btn.setBounds(x, y, width, height);
		btn.setFont(font);
		btn.setSelected(selected);
		btn.setBackground(cRed);
		btn.setForeground(Color.white);
		btn.setUI(new MetalToggleButtonUI() {

			protected Color getSelectColor() {
				return cGreen;
			}
		});
	}

	void drawSettings() {

		int x = 920;
		int y = 50;

		int width = 200;
		int height = 50;

		settingsBTN = new JButton();
		panel.add(settingsBTN);

		setButton(settingsBTN);

		settingsBTN.setBounds(x, y, width, height);
		settingsBTN.setText("Settings");
		settingsBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Show Settings GUI
				showSettings();

			}
		});
	}

	void drawConfirm() {

		int x = 500;
		int y = 50;

		int width = 200;
		int height = 50;

		confirmBTN = new JButton();
		panel.add(confirmBTN);

		setButton(confirmBTN);

		confirmBTN.setBounds(x, y, width, height);
		confirmBTN.setText("Get Results");
		confirmBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				confirmInputs();

			}
		});
	}

	void drawReset() {

		int x = 80;
		int y = 50;

		int width = 200;
		int height = 50;

		resetBTN = new JButton();
		panel.add(resetBTN);

		setButton(resetBTN);

		resetBTN.setBounds(x, y, width, height);
		resetBTN.setText("Reset");
		resetBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				resetCalculations();

			}
		});
	}

	void setButton(JButton button) {
		button.setVisible(true);
		button.setBackground(Color.DARK_GRAY.brighter().brighter());
		button.setFont(font);
	}

	void confirmInputs() {
		for (int i = 0; i < textfield.length; i++) {
			if (correctResult(i)) {
				textfield[i].setBackground(cGreen);
			} else {
				textfield[i].setBackground(cRed);
			}
		}
	}

	boolean correctResult(int index) {

		String labelText = label[index].getText();
		String[] split = labelText.split(" ");

		float n1 = Integer.parseInt(split[0]);
		float n2 = Integer.parseInt(split[2]);

		String input = textfield[index].getText();
		float myResult = Integer.MIN_VALUE;

		if (!(input.equals(""))) {
			myResult = Float.parseFloat(input);
		}

		float correctResult = Integer.MAX_VALUE;

		if (split[1].equals("+")) {
			correctResult = round(n1 + n2, 2);
			if (correctResult == myResult) {
				return true;
			}
		} else if (split[1].equals("-")) {
			correctResult = round(n1 - n2, 2);
			if (correctResult == myResult) {
				return true;
			}
		} else if (split[1].equals("*")) {
			correctResult = round(n1 * n2, 2);
			if (correctResult == myResult) {
				return true;
			}
		} else if (split[1].equals("/")) {
			correctResult = round(n1 / n2, 2);
			if (correctResult == myResult) {
				return true;
			}
		}

		return false;
	}

	public static float round(float d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	void resetCalculations() {

		if (!add && !subtract && !multiply && !divide) {
			System.out.println("ERROR: No possible calculations can be made");
			return;
		}

		for (int i = 0; i < label.length; i++) {
			label[i].setText(getRandomCalculation());
			label[i].setBackground(Color.LIGHT_GRAY);
		}

		for (int i = 0; i < textfield.length; i++) {
			textfield[i].setText("");
			textfield[i].setBackground(Color.DARK_GRAY.brighter().brighter());
		}

	}

	String getRandomCalculation() {
		int rand;

		String symbol = "";
		String finalC = "";
		int n1 = -1;
		int n2 = -1;

		boolean found = false;

		while (!found) {
			rand = getRandomNumber(1, 4);

			if (rand == 1) {
				symbol = "+";
				if (add) {
					found = true;
				}
			} else if (rand == 2) {
				symbol = "-";
				if (subtract) {
					found = true;
				}
			} else if (rand == 3) {
				symbol = "*";
				if (multiply) {
					found = true;
				}
			} else if (rand == 4) {
				symbol = "/";
				if (divide) {
					found = true;
				}
			}
		}

		if (!negativeNumbers) {
			boolean foundPositiveNumber = false;
			while (!foundPositiveNumber) {
				n1 = getRandomNumber(1, 20);
				n2 = getRandomNumber(1, 20);
				if (n1 >= n2) {
					foundPositiveNumber = true;
				}
			}
		} else {
			n1 = getRandomNumber(-20, 20);
			n2 = getRandomNumber(-20, 20);
		}

		finalC = n1 + " " + symbol + " " + n2;
		return finalC;

	}

	int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);

	}

	void drawPanel() {
		panel.setBackground(Color.DARK_GRAY);
	}

	void drawLabels() {

		int xStart = 80;
		int yStart = 140;

		int x = xStart;
		int y = yStart;

		int xInc = 550;
		int yInc = 100;

		int width = 400;
		int height = 50;

		for (int i = 0; i < label.length; i++) {

			if (i != 0) {
				y += yInc;

				if (i % columns == 0) {
					x += xInc;
					y = yStart;
				}
			}

			label[i] = new JTextArea("NO POSSIBLE CALCULATION");
			panel.add(label[i]);
			label[i].setBounds(x, y, width, height);
			label[i].setVisible(true);
			label[i].setOpaque(true);
			label[i].setBackground(Color.LIGHT_GRAY);
			label[i].setEditable(false);
			label[i].setFont(font);		

		}

	}

	void drawTextfields() {

		int xStart = 490;
		int yStart = 140;

		int x = xStart;
		int y = yStart;

		int xInc = 550;
		int yInc = 100;

		int width = 80;
		int height = 50;

		for (int i = 0; i < textfield.length; i++) {

			if (i != 0) {
				y += yInc;

				if (i % columns == 0) {
					x += xInc;
					y = yStart;
				}
			}

			textfield[i] = new JTextField();
			panel.add(textfield[i]);
			textfield[i].setBounds(x, y, width, height);
			textfield[i].setVisible(true);
			textfield[i].setOpaque(true);
			textfield[i].setBackground(Color.DARK_GRAY.brighter().brighter());
			textfield[i].setForeground(Color.white);
			textfield[i].setFont(font);

			textfield[i].setFocusTraversalKeysEnabled(false);
			textfield[i].addKeyListener(new KeyHandler());

		}
	}

	public JTextField[] getTextfield() {
		return textfield;
	}

}
