import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Driver{
	/*
	 * 3/14/19
	 * The changes made to this file for assignment 6 were: 
	 * Two new methods, writeContactsToFile and recoverContactsFromFile.
	 * Wrapping the main method in a try-catch block.
	 * Calling recoverContactsFromFile in the main method
	 * Calling writeContactsToFile in case 0 of performTask.
	 */
	
	static int menuChoice;
	static boolean run = true;
	static Scanner input = new Scanner(System.in);
	static File file = new File("save.txt");
	
	
	public static void sortByFirst(Contact[] book) {
		/*Bubble sort to arrange the array by first name.
		*Uses openContact so it won't search through the empty slots in the array.
		*Calls move contact to rearrange the position of the contacts 
		*and uses the temp Contact to store information while rearranging
		*/
		Contact temp = new Contact();
		for(int i=0; i<Contact.openContact;i++) {
			for(int j=0;j<Contact.openContact-1;j++) {
				String firstString = book[j].firstName;
				String secondString = book[j+1].firstName;
				int diff = firstString.compareTo(secondString);
				if(diff > 0) {
					temp.moveContact(book[j].firstName, book[j].lastName, book[j].phoneNumber, book[j].address);
					book[j].moveContact(book[j+1].firstName, book[j+1].lastName, book[j+1].phoneNumber, book[j+1].address);
					book[j+1].moveContact(temp.firstName, temp.lastName, temp.phoneNumber, temp.address);
				}
			}
		}
	}
	public static void sortByLast(Contact[] book) {
		//Same as sortByFirst except by last name
		Contact temp = new Contact();
		for(int i=0; i<Contact.openContact;i++) {
			for(int j=0;j<Contact.openContact-1;j++) {
				String firstString = book[j].lastName;
				String secondString = book[j+1].lastName;
				int diff = firstString.compareTo(secondString);
				if(diff > 0) {
					temp.moveContact(book[j].firstName, book[j].lastName, book[j].phoneNumber, book[j].address);
					book[j].moveContact(book[j+1].firstName, book[j+1].lastName, book[j+1].phoneNumber, book[j+1].address);
					book[j+1].moveContact(temp.firstName, temp.lastName, temp.phoneNumber, temp.address);
				}
			}
		}
	}
	
	public static int searchByFirst(Contact[] book, String searchName) {
		/*
		 * Compares the user's inputed value to the stored values for firstName in 
		 * the array in a loop, if it is not found, the index remains at -1, and 
		 * the functions which call searchByFirst and searchByLast have checks 
		 * after the call which break if index is still -1
		 */
		int index = -1;
		for(int i=0;i<Contact.openContact;i++) {
			if(searchName.equals(book[i].firstName)) {
				book[i].printContact();
				index = i;
			}
		}
		return index;
	}
	public static int searchByLast(Contact[] book, String searchName) {
		//Similar to searchByFirst but on the lastName value 
		int index = -1;
		for(int i=0;i<Contact.openContact;i++) {
			if(searchName.equals(book[i].lastName)) {
				book[i].printContact();
				index = i;
			}
		}
		return index;
	}
	public static void searchByPhone(Contact[] book, String searchValue) {
		//Similar to searchByFirst but on the phoneNumber value 
		for(int i=0;i<Contact.openContact;i++) {
			if(searchValue.equals(book[i].phoneNumber)) {
				book[i].printContact();
			}
		}
	}
	public static void writeContactsToFile(Contact[] book) {
		/*
		 * Iterates through the address book using the built in openContact counter,
		 * writes each element of the contact to the save file delimited by , which
		 * will later be used by the recover method.
		 */
		try {
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			sortByFirst(book);
			for (int i=0; i<Contact.openContact;i++) {
				pw.write(book[i].firstName + "," + book[i].lastName + "," + 
					book[i].phoneNumber + "," + book[i].address + "\n");
				
			}
			pw.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
		
	}
	public static void recoverContactsFromFile(Contact[] book) throws IOException {
		/*
		 * Reads from the save file. For each line the split method is called to
		 * store the 4 parts of the contact in the elements array which is then
		 * used in the call for setContact
		 */
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			int c = 0;
			//c is the counter for the index in the addressbook array
			while ((line=br.readLine()) != null) {
				String[] elements = line.split(",");
				book[c].setContact(elements[0], elements[1], elements[2], elements[3]);
				c++;
			}
		br.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	public static void getMenuChoice() {
		/*
		 * Menu of functions for the user to be ran in the loop in the main method,
		 * stores the int picked up by the Scanner object in the menuChoice variable
		 * which will then be used in the switch-case statement of the 
		 * performTask function.
		 */
		System.out.println("Please select what you would like to do from the menu.");
		System.out.println("Press 1 to add a new contact.");
		System.out.println("Press 2 to update an existing contact");
		System.out.println("Press 3 to delete a contact");
		System.out.println("Press 4 to view all contacts by first name");
		System.out.println("Press 5 to view all contacts by last name");
		System.out.println("Press 6 to search by first name");
		System.out.println("Press 7 to search by last name");
		System.out.println("Press 8 to search by phone number");
		System.out.println("Press 0 to save and exit");
		Scanner scanMenu = new Scanner(System.in);
		menuChoice = scanMenu.nextInt();
		scanMenu.hasNextLine();
		System.out.println();
		
	}
	
	public static void performTask(Contact[] book) {
		/*
		 * Switch statement to call functions on the contact objects and call the
		 * static functions, also contains the only way to turn the run variable 
		 * in the main method to false
		 */
		input.useDelimiter("\n");
		switch(menuChoice) {
			case 1: //add a new contact
				int newPage = Contact.openContact;
				String newFirst, newLast, newPhone, newAdd;
				System.out.println("Enter the first name, then press return: ");
				newFirst = input.next();
				System.out.println("Enter the last name, then press return: ");
				newLast = input.next();
				System.out.println("Enter the phone number(xxx-xxx-xxxx), then press return: ");
				newPhone = input.next();
				System.out.println("Enter the address, then press return: ");
				newAdd = input.next();
				book[newPage].setContact(newFirst, newLast, newPhone, newAdd);
				break;
			case 2: //update a contact
				String searchValue;
				int confirm, index;
				System.out.println("Enter the first name of the contact to be updated: ");
				searchValue = input.next();
				index = searchByFirst(book, searchValue);
				if(index==-1) {
					System.out.println("Contact not found");
					break;
				}
				System.out.println("Enter 1 if that is the contact you would like to update, enter 0 to search by last name");
				confirm = input.nextInt();
				if(confirm == 1) {
					book[index].updateContact();
				}
				else if(confirm == 0) {
					System.out.println("Enter the last name of the contact to be updated: ");
					searchValue = input.next();
					index = searchByLast(book,searchValue);
					if(index==-1) {
						System.out.println("Contact not found");
						break;
					}
					book[index].updateContact();
				}
				break;
			case 3: //delete a contact
				System.out.println("Enter the first name of the contact to be deleted: ");
				searchValue = input.next();
				index = searchByFirst(book, searchValue);
				if(index==-1) {
					System.out.println("Contact not found");
					break;
				}
				System.out.println("Enter 1 if that is the contact you would like to delete, enter 0 to search by last name");
				confirm = input.nextInt();
				if(confirm == 1) {
					book[index].deleteContact();
				}
				else if(confirm == 0) {
					System.out.println("Enter the last name of the contact to be deleted: ");
					searchValue = input.next();
					index = searchByLast(book,searchValue);
					if(index==-1) {
						System.out.println("Contact not found");
						break;
					}
					book[index].deleteContact();
				}
				break;
			case 4: // view all contacts by first name
				sortByFirst(book);
				Contact.printBook(book);
				break;
			case 5: //view all contacts by last name
				sortByLast(book);
				Contact.printBook(book);
				break;
			case 6: //search by first
				System.out.println("Enter the first name of the contact to search for: ");
				searchValue = input.next();
				searchByFirst(book, searchValue);
				break;
			case 7: //search by last
				System.out.println("Enter the last name of the contact to search for: ");
				searchValue = input.next();
				searchByLast(book, searchValue);
				break;
			case 8: //search by phone number
				System.out.println("Enter the phone number to search for in format (xxx-xxx-xxxx) ");
				searchValue = input.next();
				searchByPhone(book,searchValue);
				break;
			case 0: //shutdown
				//Saves the contacts once the user opts to exit the program
				System.out.println("Exiting myContacts, goodbye.");
				input.close();
				writeContactsToFile(book);
				run = false;
		}
	}
	
	
	
	
	public static void main(String [] args) {
		/*
		 * The Contact array initialization has to be outside of the try
		 * in order for it to be possible to call the writeContactsToFile method
		 * in the catch.
		 */
		Contact[] myContacts = new Contact[25];
		for(int i = 0; i < 25; i++) {
			myContacts[i] = new Contact();
		}
		//Added a try-catch block to handle any errors that occur
		try {
			file.createNewFile();
			recoverContactsFromFile(myContacts);
			System.out.println("Hello and welcome to myContacts!");
			while(run == true) {
				getMenuChoice();
				performTask(myContacts);
			}
		}catch(Exception e) {
			//If an error occurs, it will attempt to save the contacts before shutting down.
			writeContactsToFile(myContacts);
			System.out.println(e);
			e.printStackTrace();
		}
	}
}