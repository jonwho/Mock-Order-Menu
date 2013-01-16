//Jonathan Ho

/**The GUI will have the following components:
 	* Nine buttons representing different menu options and the associated prices
    * A text area which display the items currently ordered and the associated price
    * A clear button which clears the text area
    * A check out button which totals the meal and display the cost (assume no tax)
    * A title of "Flame Bay"
*/

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class FlameBay extends JFrame {
	MenuItem[] button = new MenuItem[9]; //To represent the MenuItem buttons
	private int counter; //Used in for loop
	private JTextArea area; //To hold text for MenuItem name and price
	private double acc; //To hold total of selected items
	private JButton clearButton; //Button to clear text area
	private JButton checkOutButton; //Button to total prices
	DecimalFormat df = new DecimalFormat("$0.00"); //Makes it possible to format to currency
	
	//Constructor to create JFrame content
	public FlameBay() {
		setTitle("Flame Bay");
		setSize(648, 406);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creating buttons and text area
		clearButton = new JButton("Clear");
		checkOutButton = new JButton("Check Out");
		area = new JTextArea(0, 20);
		
		//Registering action listeners
		clearButton.addActionListener(new clearButtonListener());
		checkOutButton.addActionListener(new checkOutButtonListener());
		
		//Adding buttons and text area to FlameBay JFrame
		add(clearButton, BorderLayout.NORTH);
		add(checkOutButton, BorderLayout.SOUTH);
		add(area, BorderLayout.EAST);
		
		//Create a JPanel with grid layout
		JPanel jp =  new JPanel(new GridLayout(3, 3));
		
		//Initializing buttons before adding
		button[0] = new MenuItem("Egg Noodles", 1); //Can't figure out why price shows as $1....... instead of $1.00
		button[1] = new MenuItem("Spaghetti", .95);
		button[2] = new MenuItem("Ramen", .50);
		button[3] = new MenuItem("Beef", 2);
		button[4] = new MenuItem("Chicken", 1.5);
		button[5] = new MenuItem("Pork", 1.25);
		button[6] = new MenuItem("Kung Pao", .5);
		button[7] = new MenuItem("Teriyaki", .25);
		button[8] = new MenuItem("Curry", .45);
		
		//Adding buttons to JPanel jp
		for(counter = 0; counter < 9; counter++) {
			button[counter].addActionListener(new buttonListener());
			jp.add(button[counter]);
		}
		
		//Adding JPanel to JFrame FlameBay
		add(jp, BorderLayout.CENTER);
		setVisible(true);
	}
	
	//Defining MenuItem qualities
	private class MenuItem extends JButton {
		//Variables to hold name and price
		private String name;
		private double price;
		
		//Constructor to get name and price and set text for MenuItem buttons
		public MenuItem(String name, double price) {
			this.name = name;
			this.price = price;
			setText(toString());
		}
		
		//Gets String
		public String getName() {
			return name;
		}
		
		//Gets price
		public double getPrice() {
			return price;
		}
		
		//String of name and formatted price
		public String toString() { 
			return (getName() + " " + df.format(getPrice()));
		}
	}
	
	//Performs task for button of object MenuItem
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MenuItem button = (MenuItem)e.getSource();
			area.append(button.toString() + "\n");				
			acc += button.getPrice();
		}
	}
	
	//Performs task for clearButton
	private class clearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			acc = 0;
			area.setText(" ");
		}
	}
	
	//Performs task for checkOutButton
	private class checkOutButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			area.append("--- Cost: " + df.format(acc) + "\n");
		}
	}

	//Create FlameBay GUI
	public static void main(String[] args) {
		new FlameBay();
	}
}
