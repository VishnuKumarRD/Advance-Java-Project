package com.atm;

import java.util.Scanner;

public class ATM_Main {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		IAtm atm=new Atm_OperationImplementation();//upcasting

		while(true) {
			System.out.println("---WELCOME TO ATM MANAGEMENT SYSTEM---");
			System.out.println("OPERATIONS-->");
			System.out.println("1) CREATE AN ACCOUNT\n"
					+ "2) DEPOSIT AMOUNT\n"
					+ "3) WITHDRAW AMOUNT\n"
					+ "4) CHECK THE BALANCE\n"
					+ "5) UPDATION\n"
					+ "6) DISPLAY ACCOUNT DETAILS\n"
					+ "7) DELETE ACCOUNT\n"
					+ "8)EXIT");

			System.out.println("CHOOSE AN OPTION : ");
			int choice=scanner.nextInt();
			try {
				switch(choice) {
				case 1 :
					atm.createAccount();
					break;

				case 2 : 
					atm.depositAmount();
					break;

				case 3 : 
					atm.withdrawAmount();
					break;

				case 4 : 
					atm.checkBalance();
					break;


				case 5: 
					atm.accountUpdation();
					break;

				case 6 : 
					atm.accountDetails();
					break;

				case 7 : 
					atm.accountDeletion();
					break;

				case 8 : 
					System.out.println("Thank you for Visiting!");
					System.exit(0);

				default:
					throw new InvalidChoiceException("INVALID CHOICE\nPLEASE CHOOSE CORRECT OPTION BELOW!");

				}

			}
			catch(InvalidChoiceException e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
