import java.util.List;
import java.util.Scanner;

import controller.PetListItemHelper;

import model.PetList;

public class StartPetsProgram {
	
	static Scanner in = new Scanner(System.in);
	static PetListItemHelper plih = new PetListItemHelper();
	
	
    //Method to add a pet
	private static void addPet() {
		// TODO Auto-generated method stub
		System.out.print("Enter the type of pet: ");
		String type = in.nextLine();
		System.out.print("Enter the pet name: ");
		String name = in.nextLine();
		System.out.print("Enter the owner name: ");
		String owner = in.nextLine();
		
		PetList toAdd = new PetList(type,name,owner);
		plih.insertPet(toAdd);
		

	}

	//Method to delete a pet
	private static void deletePet() {
		// TODO Auto-generated method stub
		System.out.print("Enter the type of pet: ");
		String type = in.nextLine();
		System.out.print("Enter the pet name: ");
		String name = in.nextLine();
		System.out.print("Enter the owner name: ");
		String owner = in.nextLine();
		
		PetList toDelete = new PetList(type,name,owner);
		plih.deletePet(toDelete);

	}

	//Edit method: can find pets by Type, Name or Owner
	private static void editPet() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Type");
		System.out.println("2 : Search by Name");
		System.out.println("3 : Search by Owner");
		int searchBy = in.nextInt();
		in.nextLine();
		List<PetList> foundPets;
		if (searchBy == 1) {
			System.out.print("Enter the pet type: ");
			String petType = in.nextLine();
			foundPets = plih.searchForPetByType(petType);
			
		} else if (searchBy == 2) {
			System.out.print("Enter the pet name: ");
			String petName = in.nextLine();
			foundPets = plih.searchForPetByName(petName);
		} else  {
			System.out.print("Enter the owner name: ");
			String ownerName = in.nextLine();
			foundPets = plih.searchForPetByOwner(ownerName);
			

		}

		//If statement to return list of found pets by ID and provide a method to update the record
		if (!foundPets.isEmpty()) {
			System.out.println("Found Results.");
			for (PetList l : foundPets) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			PetList toEdit = plih.searchForPetById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName() + " of type " + toEdit.getType() + " that belongs to " +  toEdit.getOwner());
			System.out.println("1 : Update Type");
			System.out.println("2 : Update Name");
			System.out.println("3 : Update Owner");
			int update = in.nextInt();
			in.nextLine();

			//If statement to update ID by Type, Name, or Owner
			if (update == 1) {
				System.out.print("New Type: ");
				String newType = in.nextLine();
				toEdit.setType(newType);
			} else if (update == 2) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else 
			{
				System.out.print("New Owner: ");
				String newOwner = in.nextLine();
				toEdit.setOwner(newOwner);
			}

			plih.updatePetItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}

	//calls runMenu method
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	//Menu seen when program runs
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to the pet database! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a Pet");
			System.out.println("*  2 -- Edit a Pet");
			System.out.println("*  3 -- Delete a Pet");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addPet();
			} else if (selection == 2) {
				editPet();
			} else if (selection == 3) {
				deletePet();
			} else if (selection == 4) {
				viewTheList();
			} else {
				plih.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	//Gets the list of pets in the Database and returns it to the console
	private static void viewTheList() {
		List<PetList> allPets = plih.showAllPets();
		for(PetList singlePet : allPets) {
			System.out.println(singlePet.returnPetDetails());
		}
		

	}

}
