package main;

import medicalStuff.Input;
import medicalStuff.Menu;
import medicalStuff.Pharmacy;

public class Main {

    public static void main(String[] args) throws InterruptedException {

		Menu[] menuArray=new Menu[50];
    	Pharmacy[] phArray=new Pharmacy[50];
		int nPhs=4;
		int choice;
		phArray[0]=new Pharmacy("Ilias' Pharmacy","Chania","Poor's Programmer's Boulevard",69,2821012345L);
		phArray[1]=new Pharmacy("Manos' Pharmacy","Chania","Kyrios Exw Kopela",0,2821000000L);
		phArray[2]=new Pharmacy("Bogdan's Pharmacy","Chania","4.5 Apo Maragoudaki",1,2821054321L);
		phArray[3]=new Pharmacy("Manolo's Pharmacy","Chania","Den exw grapsei grami kwdika edw kai 3mish mines",2,2821069069L);
		for(int i=0;i<nPhs;i++){
			menuArray[i]=new Menu();
		}
		menuArray[0].initialise(phArray[0]);
		menuArray[1].initialise(phArray[1]);
		menuArray[1].initialise(phArray[2]);
		menuArray[1].initialise(phArray[3]);
		while (true) {
			choice = printAndChoose(phArray, nPhs);

			switch (choice) {

				case 0: {
					System.out.println("PROGRAM TERMINATION...");
					break;
				}
				case 1: {
					menuArray[0].showMenu(phArray[0]);
					break;
				}
				case 3: {
					menuArray[1].showMenu(phArray[1]);
				}
			}
		}
    }

	private static int printAndChoose(Pharmacy[] array,int numOfPhs){

		Input scanner=new Input();
		int phChoice;
		System.out.println("_______________________________________________________");
		System.out.println("Select one of the following pharmacies(OR 0 TO QUIT):\n");
		for(int i=0;i<numOfPhs;i++){
			System.out.println((i+1)+") "+array[i].pharmacyName+" ,address: "+array[i].addressCity+ " ,"+array[i].addressRoad+" "+array[i].addressNo+" ,telephone: "+array[i].pharmacyPhone);
		}
		System.out.print("Enter choice(with No): ");
		do {
			phChoice=scanner.readInt();
			if(phChoice>numOfPhs||phChoice<0){
				System.out.print("Enter a valid choice: ");
			}
		}while (phChoice>numOfPhs||phChoice<0);
		return phChoice;
	}
}