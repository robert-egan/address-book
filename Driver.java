import java.util.Scanner;

class Driver{
	
	static int menuChoice;
	static boolean run = true;
	static Scanner input = new Scanner(System.in);
	
	public static void sortByFirst(Contact[] book) {
		//Bubble sort to arrange the array by first name
		//Uses opencontact so it won't search through the empty slots in the array
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
		//Bubble sort to arrange the array by last name
		//Uses opencontact so it won't search through the empty slots in the array
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
		//compares the user's inputed value to the stored values in the array 
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
		//compares the user's inputed value to the stored values in the array 
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
		//compares the user's inputed value to the stored values in the array 
		for(int i=0;i<Contact.openContact;i++) {
			if(searchValue.equals(book[i].phoneNumber)) {
				book[i].printContact();
			}
		}
	}
	public static void getMenuChoice() {
		//Menu of functions for the user
		System.out.println("Please select what you would like to do from the menu.");
		System.out.println("Press 1 to add a new contact.");
		System.out.println("Press 2 to update an existing contact");
		System.out.println("Press 3 to delete a contact");
		System.out.println("Press 4 to view all contacts by first name");
		System.out.println("Press 5 to view all contacts by last name");
		System.out.println("Press 6 to search by first name");
		System.out.println("Press 7 to search by last name");
		System.out.println("Press 8 to search by phone number");
		System.out.println("Press 0 to exit");
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
				System.out.println("Enter 1 if that is the contact you would like to update, enter 0 to search by last name");
				confirm = input.nextInt();
				if(confirm == 1) {
					book[index].updateContact();
				}
				else if(confirm == 0) {
					System.out.println("Enter the last name of the contact to be updated: ");
					searchValue = input.next();
					index = searchByLast(book,searchValue);
					book[index].updateContact();
				}
				break;
			case 3: //delete a contact
				System.out.println("Enter the first name of the contact to be deleted: ");
				searchValue = input.next();
				index = searchByFirst(book, searchValue);
				System.out.println("Enter 1 if that is the contact you would like to delete, enter 0 to search by last name");
				confirm = input.nextInt();
				if(confirm == 1) {
					book[index].deleteContact();
				}
				else if(confirm == 0) {
					System.out.println("Enter the last name of the contact to be deleted: ");
					searchValue = input.next();
					index = searchByLast(book,searchValue);
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
				System.out.println("Exiting myContacts, goodbye.");
				input.close();
				run = false;
		}
	}
	
	
	
	
	public static void main(String [] args) {
		Contact[] myContacts = new Contact[25];
		for(int i = 0; i < 25; i++) {
			myContacts[i] = new Contact();
		}
		System.out.println("Hello and welcome to myContacts!");
		while(run == true) {
			getMenuChoice();
			performTask(myContacts);
		}
	}
}