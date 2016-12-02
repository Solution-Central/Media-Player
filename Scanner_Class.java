/** Program of Scanner Class Use with Getter and Setter Methods
 * author@Shubh Chaudhary
 */

import java.util.Scanner;

public class Scanner_Class {
	
	private String name = "";
	private int id = 0;
	private double percentage = 0.0;
	
	public Scanner_Class(Scanner getInput) {
		// Takes input and calls respective Getter and Setter Methods
		
		if(getInput.hasNextInt())
			setId(getInput.nextInt());
		
		if(getInput.hasNext())
			setName(getInput.next());
		
		if(getInput.hasNextDouble())
			setPercentage(getInput.nextDouble());		
			
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}	
	
	protected static void displayData(Scanner_Class[] sc)
	{
		System.out.println("Name is : " + sc[0].getName());
		System.out.println("Id is : " + sc[0].getId());
		System.out.println("Percentage is : " + sc[0].getPercentage());
		System.out.println("\n*******************************************\n");
		System.out.println("Name is : " + sc[1].getName());
		System.out.println("Id is : " + sc[1].getId());
		System.out.println("Percentage is : " + sc[1].getPercentage());
	}
	
	public void displayCredentials()
	{
		System.out.println("Name is : " + getName());
		System.out.println("Id is : " + getId());
		System.out.println("Percentage is : " + getPercentage());
	}
	public static void main(String[] args)
	{
		Scanner getInput = new Scanner(System.in);
		
		Scanner_Class obj[] = new Scanner_Class[2];
		obj[0] = new Scanner_Class(getInput);
		obj[1] = new Scanner_Class(getInput);
				
		// Passing on the object array and iterating it...
		displayData(obj);
		
		System.out.println("\n\nOther Way :\n");
		
		// Calling by that particular object...
		obj[0].displayCredentials();
		System.out.println("\n*******************************************\n");
		obj[1].displayCredentials();
		
		getInput.close();
	}
}
