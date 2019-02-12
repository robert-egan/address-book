import java.util.Scanner;

class Driver{
	
	static int menuChoice;
	static boolean run = true;
	static Scanner input = new Scanner(System.in);
	
	public static void sortByFirst(Contact[] book) {
		Contact temp = new Contact();
		for(int i=0; i<book.length;i++) {
			for(int j=0;j<book.length-1;j++) {
				String firstString = book[j].firstName;
				String secondString = book[j+1].firstName;
				int diff = firstString.compareTo(secondString);
				if(diff > 0) {
					temp.setContact(book[j].firstName, book[j].lastName, book[j].phoneNumber, book[j].address);
					book[j].setContact(book[j+1].firstName, book[j+1].lastName, book[j+1].phoneNumber, book[j+1].address);
					book[j+1].setContact(temp.firstName, temp.lastName, temp.phoneNumber, temp.address);
				}
			}
		}
	}
	public static void sortByLast(Contact[] book) {
		Contact temp = new Contact();
		for(int i=0; i<book.length;i++) {
			for(int j=0;j<book.length-1;j++) {
				String firstString = book[j].lastName;
				String secondString = book[j+1].lastName;
				int diff = firstString.compareTo(secondString);
				if(diff > 0) {
					temp.setContact(book[j].firstName, book[j].lastName, book[j].phoneNumber, book[j].address);
					book[j].setContact(book[j+1].firstName, book[j+1].lastName, book[j+1].phoneNumber, book[j+1].address);
					book[j+1].setContact(temp.firstName, temp.lastName, temp.phoneNumber, temp.address);
				}
			}
		}
	}
	
	public static int searchByFirst(Contact[] book, String searchName) {
		int index = -1;
		for(int i=0;i<book.length;i++) {
			if(searchName == book[i].firstName) {
				book[i].printContact();
				index = i;
			}
		}
		return index;
	}
	public static int searchByLast(Contact[] book, String searchName) {
		int index = -1;
		for(int i=0;i<book.length;i++) {
			if(searchName == book[i].lastName) {
				book[i].printContact();
				index = i;
			}
		}
		return index;
	}
	public static void searchByPhone(Contact[] book, String searchValue) {
		for(int i=0;i<book.length;i++) {
			if(searchValue == book[i].phoneNumber) {
				book[i].printContact();
			}
		}
	}
	public static void getMenuChoice() {
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
		System.out.println();
		scanMenu.close();
	}
	
	static void performTask(Contact[] book) {
		switch(menuChoice) {
			case 1: //add a new contact
				int newPage = Contact.openContact;
				String newFirst, newLast, newPhone, newAdd;
				//Scanner scan1 = new Scanner(System.in);
				input.useDelimiter("\n");
				System.out.println("Enter the first name, then press return: ");
				newFirst = input.next();
				System.out.println("Enter the last name, then press return: ");
				newLast = input.next();
				System.out.println("Enter the phone number(xxx-xxx-xxxx), then press return: ");
				newPhone = input.next();
				System.out.println("Enter the address, then press return: ");
				newAdd = input.next();
				book[newPage].setContact(newFirst, newLast, newPhone, newAdd);
				//scan1.close();
				break;
			case 2: //update a contact
				Scanner scan2 = new Scanner(System.in);
				String searchValue;
				int confirm, index;
				System.out.println("Enter the first name of the contact to be updated: ");
				searchValue = scan2.next();
				index = searchByFirst(book, searchValue);
				System.out.println("Enter 1 if that is the contact you would like to update, enter 0 to search by last name");
				confirm = scan2.nextInt();
				if(confirm == 1) {
					book[index].updateContact();
				}
				else if(confirm == 0) {
					System.out.println("Enter the last name of the contact to be updated: ");
					searchValue = scan2.next();
					index = searchByLast(book,searchValue);
					book[index].updateContact();
				}
				scan2.close();
				break;
			case 3: //delete a contact
				Scanner scan3 = new Scanner(System.in);
				System.out.println("Enter the first name of the contact to be deleted: ");
				searchValue = scan3.next();
				index = searchByFirst(book, searchValue);
				System.out.println("Enter 1 if that is the contact you would like to delete, enter 0 to search by last name");
				confirm = scan3.nextInt();
				if(confirm == 1) {
					book[index].deleteContact();
				}
				else if(confirm == 0) {
					System.out.println("Enter the last name of the contact to be deleted: ");
					searchValue = scan3.next();
					index = searchByLast(book,searchValue);
					book[index].deleteContact();
				}
				scan3.close();
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
				Scanner scan6 = new Scanner(System.in);
				System.out.println("Enter the first name of the contact to search for: ");
				searchValue = scan6.next();
				searchByFirst(book, searchValue);
				scan6.close();
				break;
			case 7: //search by last
				Scanner scan7 = new Scanner(System.in);
				System.out.println("Enter the last name of the contact to search for: ");
				searchValue = scan7.next();
				searchByLast(book, searchValue);
				scan7.close();
				break;
			case 8: //search by phone number
				Scanner scan8 = new Scanner(System.in);
				System.out.println("Enter the phone number to search for in format (xxx-xxx-xxxx) ");
				searchValue = scan8.next();
				searchByPhone(book,searchValue);
				scan8.close();
				break;
			case 0:
				System.out.println("Exitting myContacts, goodbye.");
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