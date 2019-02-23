import java.util.Scanner;

class Contact{
	String firstName, lastName, phoneNumber, address;
	boolean display = false;
	//Becomes true when a contact is set so empty contacts will not print.
	static int storageSpace = 25;
	 //counter to keep track of the remaining space in the array
	static int openContact = 0;
	//counter to track the next open spot in the array and where the array ends for sorting and searching
	
	void setContact(String fName, String lName, String pNum, String loc) {
		//sets the contact initially, increments and decrements the built in counters
		firstName = fName;
		lastName = lName;
		phoneNumber = pNum;
		address = loc;
		display = true;
		storageSpace -= 1;
		openContact += 1;
		System.out.println("There is space for " + storageSpace + " additional contacts");
	}
	void moveContact(String fName, String lName, String pNum, String loc) {
		//resets contacts any time they need to be moved in the array for sorting
		firstName = fName;
		lastName = lName;
		phoneNumber = pNum;
		address = loc;
	}
	public static int getOpenContact() {
		//finds the lowest open index in the array
		return openContact;
	}
	int getStorageSpace() {
		//finds the amount of spaces remaining in the array
		return storageSpace;
	}
	void deleteContact() {
		int delChoice;
		Scanner scanDel = new Scanner(System.in);
		System.out.println("Are you sure you want to delete this contact? Enter 1 for yes.");
		delChoice = scanDel.nextInt();
		if(delChoice==1) {
			/*
			 * When the contact is deleted, it's value is set such that it will go 
			 * to the end of either sort, not be displayed or searched through,
			 * and because openContact is decremented, it will be overwritten the 
			 * next time the user goes to create a contact. 
			 */
			this.moveContact("Zzz", "Zzz", "", "");
			this.display = false;
			storageSpace +=1;
			openContact -=1;
		}
		
	}
	
	void updateContact() {
		/*
		 * Goes through the four elements of the contact prompting the user on
		 * if they would like to change each one, if not the existing value is
		 * set equal to the new value variable
		 */
		this.printContact();
		int updateChoice;
		String newFirst, newLast, newPhone, newAddress;
		Scanner scanUpdate = new Scanner(System.in);
		scanUpdate.useDelimiter("\n");
		//scanner uses the enter key as the delimiter
		System.out.println("Enter 1 to update the first name, enter 0 to skip");
		updateChoice = scanUpdate.nextInt();
		if(updateChoice == 1) {
			System.out.println("Enter the new first name: ");
			newFirst = scanUpdate.next();
		}
		else {
			newFirst = this.firstName;
		}
		System.out.println("Enter 1 to update the last name, enter 0 to skip");
		updateChoice = scanUpdate.nextInt();
		if(updateChoice == 1) {
			System.out.println("Enter the new last name: ");
			newLast = scanUpdate.next();
		}
		else {
			newLast = this.lastName;
		}
		System.out.println("Enter 1 to update the phone number, enter 0 to skip");
		updateChoice = scanUpdate.nextInt();
		if(updateChoice == 1) {
			System.out.println("Enter the new phone number in format(xxx-xxx-xxxx): ");
			newPhone = scanUpdate.next();
		}
		else {
			newPhone = this.phoneNumber;
		}
		System.out.println("Enter 1 to update the address, enter 0 to skip");
		updateChoice = scanUpdate.nextInt();
		if(updateChoice == 1) {
			System.out.println("Enter the new address: ");
			newAddress = scanUpdate.next();
		}
		else {
			newAddress = this.address;
		}
		this.moveContact(newFirst, newLast, newPhone, newAddress);
		this.printContact();
		
	}
	
	void printContact() {
		//prints the contact
		System.out.println(this.firstName + " " + this.lastName);
		System.out.println(this.phoneNumber);
		System.out.println(this.address);
		System.out.println();
	}
	public static void printBook(Contact[] book) {
		/*
		 * static method for looping through the array and printing only the contacts
		 * which have display set to true
		 */
		System.out.println("Your Contacts: ");
		for(int i=0; i < book.length; i++) {
			if(book[i].display) {
				book[i].printContact();
			}	
		}
		System.out.println();
	}
}