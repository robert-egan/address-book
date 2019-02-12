import java.util.Scanner;

class Contact{
	String firstName, lastName, phoneNumber, address;
	boolean display = false;
	//Becomes true when a contact is set so empty contacts will not print.
	static int storageSpace = 25;
	 //keeps track of the remaining space in the array
	static int openContact = 0;
	//tracks the next open spot in the array
	
	void setContact(String fName, String lName, String pNum, String loc) {
		firstName = fName;
		lastName = lName;
		phoneNumber = pNum;
		address = loc;
		display = true;
		storageSpace -= 1;
		openContact += 1;
		System.out.println("There is space for " + storageSpace + " additional contacts");
	}
	public static int getOpenContact() {
		return openContact;
	}
	int getStorageSpace() {
		return storageSpace;
	}
	void deleteContact() {
		int delChoice;
		Scanner scanDel = new Scanner(System.in);
		System.out.println("Are you sure you want to delete this contact? Enter 1 for yes.");
		delChoice = scanDel.nextInt();
		if(delChoice==1) {
			this.setContact("", "", "", "");
		}
		scanDel.close();
	}
	
	void updateContact() {
		this.printContact();
		int updateChoice;
		String newFirst, newLast, newPhone, newAddress;
		Scanner scanUpdate = new Scanner(System.in);
		scanUpdate.useDelimiter("\n");
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
		this.setContact(newFirst, newLast, newPhone, newAddress);
		this.printContact();
		scanUpdate.close();
	}
	
	void printContact() {
		System.out.println(this.firstName + " " + this.lastName);
		System.out.println(this.phoneNumber);
		System.out.println(this.address);
		System.out.println();
	}
	public static void printBook(Contact[] book) {
		System.out.println("Your Contacts: ");
		for(int i=0; i < book.length; i++) {
			if(book[i].display) {
				book[i].printContact();
			}	
		}
		System.out.println();
	}
}