package com.atm;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Atm_OperationImplementation implements IAtm{
	Scanner scanner=new Scanner(System.in);
	//	1st and 2nd step is common for all the operations

	//here method should return the connection object 
	public Connection createConnection() {
		String url="jdbc:postgresql://localhost:5432/atm_management?user=postgres&password=root";
		Connection connection=null;
		try {
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void createAccount() {
		Connection connection=createConnection();
		String query="INSERT INTO account VALUES(?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
			preparedStatement.setString(1, scanner.nextLine());
			System.out.println("ENTER YOUR NAME : ");
			preparedStatement.setString(2, scanner.nextLine());
			System.out.println("ENTER YOUR DATE-OF-BIRTH : ");
			preparedStatement.setString(3, scanner.nextLine());
			System.out.println("ENTER BRANCH : ");
			preparedStatement.setString(4, scanner.nextLine());
			System.out.println("ENTER YOUR PASSWORD : ");
			preparedStatement.setString(5, scanner.nextLine());
			preparedStatement.setDouble(6,0.0);//set balance explicitly
			System.out.println("ENTER YOUR EMAIL ID : ");
			preparedStatement.setString(7, scanner.nextLine());
			System.out.println("ENTER YOUR MOBILE NUMBER : ");
			preparedStatement.setString(8, scanner.nextLine());

			int rows=preparedStatement.executeUpdate();

			if(rows!=0) {
				System.out.println("Account Created Successfully!");
			}
			else {
				System.out.println("Oops...Account is already Existed");
			}

			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	@Override
	public void depositAmount() {
		// TODO Auto-generated method stub
		Connection connection=createConnection();
		String query="UPDATE account SET balance=balance+? WHERE account_number=? AND pin_number=?;";

		System.out.println("ENTER DEPOSIT AMOUNT : ");
		Double depositAmount=scanner.nextDouble();
		scanner.nextLine();
		System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
		String accountNumber=scanner.nextLine();
		System.out.println("ENTER YOUR PASSWORD : ");
		String password=scanner.nextLine();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);

			preparedStatement.setDouble(1,depositAmount);
			preparedStatement.setString(2, accountNumber);
			preparedStatement.setString(3, password);

			int rows=preparedStatement.executeUpdate();

			if(rows!=0) {
				System.out.println("Amount Creditted Successfully!");
			}
			else {
				System.out.println("Invalid Account/password");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void withdrawAmount() {
		// TODO Auto-generated method stub
		Connection connection=createConnection();
		String fetchBalanceQuery="SELECT balance FROM account WHERE account_number=? AND pin_number=?;";

		System.out.println("ENTER WITHDRAW AMOUNT : ");
		Double withdrawAmount=scanner.nextDouble();
		scanner.nextLine();
		System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
		String accountNumber=scanner.nextLine();
		System.out.println("ENTER YOUR PASSWORD : ");
		String password=scanner.nextLine();

		try {
			PreparedStatement fetchBalancePreparedStatement=connection.prepareStatement(fetchBalanceQuery);
			fetchBalancePreparedStatement.setString(1, accountNumber);
			fetchBalancePreparedStatement.setString(2, password);

			ResultSet resultSet=fetchBalancePreparedStatement.executeQuery();
			if(resultSet.next()) {
				double currentBalance=resultSet.getDouble("balance");
				if(currentBalance>=withdrawAmount) {
					String withdrawQuery="UPDATE account SET balance=balance-? WHERE account_number=? AND pin_number=?;";
					PreparedStatement withdrawPreparedStatement=connection.prepareStatement(withdrawQuery);
					withdrawPreparedStatement.setDouble(1, withdrawAmount);
					withdrawPreparedStatement.setString(2, accountNumber);
					withdrawPreparedStatement.setString(3, password);

					withdrawPreparedStatement.executeUpdate();
					connection.close();
					System.out.println("Withdraw Operation is Done!");

				}
				else {
					System.out.println("Insufficient Balance!");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void checkBalance() {
		// TODO Auto-generated method stub
		Connection connection=createConnection();
		String query="SELECT balance FROM account WHERE account_number=? AND pin_number=?;";
		System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
		String checkBalance=scanner.nextLine();
		System.out.println("ENTEHR YOUR PASSWORD : ");
		String password=scanner.nextLine();
		try {
			PreparedStatement checkBalancepreparedStatement=connection.prepareStatement(query);
			checkBalancepreparedStatement.setString(1,checkBalance);
			checkBalancepreparedStatement.setString(2, password);

			ResultSet resultSet=checkBalancepreparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.println("AVAILABLE BALANCE : "+resultSet.getString("balance"));
			}else {
				System.out.println("Account is not Found / Invalid Account or Password!");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void accountUpdation() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("---UPDATE OPERATIONS---");
			System.out.println("1) HOLDER NAME\n2) E-MAIL\n3) DATE-OF-BIRTH\n4) BRANCH\n5) ATM-PIN NUMBER\n6) PHONE NUMBER\n7) Exit");
			System.out.println("CHOOSE AN OPTION : ");
			int choice=scanner.nextInt();
			scanner.nextLine();


			Connection connection=createConnection();
			System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
			String accountNumber=scanner.nextLine();
			System.out.println("ENTER YOUR PASSWORD : ");
			String password=scanner.nextLine();


			switch(choice) {
			case 1:
				System.out.println("UPDATION : NAME");
				System.out.println("ENTER YOUR NEW HOLDER NAME : ");
				String newName=scanner.nextLine();

				try {
					String fetchQuery="SELECT account_number,pin_number,holder_name FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2,password);

					ResultSet resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						String existingAccountNumber=resultSet.getString("account_number");
						String existingPassword=resultSet.getString("pin_number");
						String existingName=resultSet.getString("holder_name");
						if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
							if(!newName.equals(existingName)){
								String nameQuery="UPDATE account SET holder_name=? WHERE account_number=? AND pin_number=?;";
								PreparedStatement newNamePreparedStatement=connection.prepareStatement(nameQuery);
								newNamePreparedStatement.setString(1,newName);
								newNamePreparedStatement.setString(2,accountNumber);
								newNamePreparedStatement.setString(3,password);

								newNamePreparedStatement.executeUpdate();
								connection.close();
								System.out.println("Account Holder Name Updated Successfully!");
							}
							else {
								System.out.println("Same Name already Exist");
							}
						}
						else {
							System.out.println("Account is not Found / Incorrect Password");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 2:

				System.out.println("UPDATION : MAIL");
				System.out.println("ENTER YOUR NEW EMAIL-ID : ");
				String newMail=scanner.nextLine();
				try {
					String fetchQuery="SELECT account_number,pin_number,email FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2,password);

					ResultSet resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						String existingAccountNumber=resultSet.getString("account_number");
						String existingPassword=resultSet.getString("pin_number");
						String existingMail=resultSet.getString("email");
						if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
							if(!newMail.equals(existingMail)) {
								String nameQuery="UPDATE account SET email=? WHERE account_number=? AND pin_number=?;";
								PreparedStatement newNamePreparedStatement=connection.prepareStatement(nameQuery);
								newNamePreparedStatement.setString(1,newMail);
								newNamePreparedStatement.setString(2,accountNumber);
								newNamePreparedStatement.setString(3,password);

								newNamePreparedStatement.executeUpdate();
								connection.close();
								System.out.println("Account Holder E-Mail Updated Successfully!");
							}
							else {
								System.out.println("Same Mail already Exist");
							}
						}
						else {
							System.out.println("Account is not Found / Incorrect Password");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 3: 
				System.out.println("DATE OF BIRTH UPDATION");
				System.out.println("ENTER YOUR NEW DOB : ");
				String newDob=scanner.nextLine();
				try {
					String fetchQuery="SELECT account_number,pin_number,date_of_birth FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2,password);

					ResultSet resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						String existingAccountNumber=resultSet.getString("account_number");
						String existingPassword=resultSet.getString("pin_number");
						String existingDob=resultSet.getString("date_of_birth");
						if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
							if(!newDob.equals(existingDob)) {
								String nameQuery="UPDATE account SET date_of_birth=? WHERE account_number=? AND pin_number=?;";
								PreparedStatement newNamePreparedStatement=connection.prepareStatement(nameQuery);
								newNamePreparedStatement.setString(1,newDob);
								newNamePreparedStatement.setString(2,accountNumber);
								newNamePreparedStatement.setString(3,password);

								newNamePreparedStatement.executeUpdate();
								connection.close();
								System.out.println("Account Holder Date of Birth Updated Successfully!");
							}
							else {
								System.out.println("Same date of birth already Exist");
							}
						}
						else {
							System.out.println("Account is not Found / Incorrect Password");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 4:
				System.out.println("UPDATION : BRANCH");
				System.out.println("ENTER YOUR NEW BRANCH : ");
				String newBranch=scanner.nextLine();
				try {
					String fetchQuery="SELECT account_number,pin_number,branch FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2,password);

					ResultSet resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						String existingAccountNumber=resultSet.getString("account_number");
						String existingPassword=resultSet.getString("pin_number");
						String existingBranch=resultSet.getString("branch");
						if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
							if(!newBranch.equals(existingBranch)) {
								String nameQuery="UPDATE account SET branch=? WHERE account_number=? AND pin_number=?;";
								PreparedStatement newNamePreparedStatement=connection.prepareStatement(nameQuery);
								newNamePreparedStatement.setString(1,newBranch);
								newNamePreparedStatement.setString(2,accountNumber);
								newNamePreparedStatement.setString(3,password);

								newNamePreparedStatement.executeUpdate();
								connection.close();
								System.out.println("Account Holder Branch Updated Successfully!");
							}
							else {
								System.out.println("Same branch already Exist");
							}
						}
						else {
							System.out.println("Account is not Found / Incorrect Password");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 5:
				System.out.println("UPDATION : ATM-PIN NUMBER ");
				System.out.println("ENTER YOUR NEW ATM-PIN NUMBER : ");
				String newPin=scanner.nextLine();
				try {
					String fetchQuery="SELECT account_number,pin_number FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2,password);

					ResultSet resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						String existingAccountNumber=resultSet.getString("account_number");
						String existingPassword=resultSet.getString("pin_number");
						String existingPin=resultSet.getString("pin_number");
						if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
							if(!newPin.equals(existingPin)) {
								String nameQuery="UPDATE account SET pin_number=? WHERE account_number=? AND pin_number=?;";
								PreparedStatement newNamePreparedStatement=connection.prepareStatement(nameQuery);
								newNamePreparedStatement.setString(1,newPin);
								newNamePreparedStatement.setString(2,accountNumber);
								newNamePreparedStatement.setString(3,password);

								newNamePreparedStatement.executeUpdate();
								connection.close();
								System.out.println("Account Holder ATM-pin number Updated Successfully!");
							}
							else {
								System.out.println("Same ATM-Pin already Exist");
							}
						}
						else {
							System.out.println("Account is not Found / Incorrect Password");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 6:
				System.out.println("UPDATION : PHONE NUMBER ");
				System.out.println("ENTER YOUR NEW PHONE NUMBER : ");
				String newPhno=scanner.nextLine();
				try {
					String fetchQuery="SELECT account_number,pin_number,phone_number FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2,password);

					ResultSet resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						String existingAccountNumber=resultSet.getString("account_number");
						String existingPassword=resultSet.getString("pin_number");
						String existingPhno=resultSet.getString("phone_number");
						if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
							if(!newPhno.equals(existingPhno)) {
								String nameQuery="UPDATE account SET phone_number=? WHERE account_number=? AND pin_number=?;";
								PreparedStatement newNamePreparedStatement=connection.prepareStatement(nameQuery);
								newNamePreparedStatement.setString(1,newPhno);
								newNamePreparedStatement.setString(2,accountNumber);
								newNamePreparedStatement.setString(3,password);

								newNamePreparedStatement.executeUpdate();
								connection.close();
								System.out.println("Account Holder Phone number Updated Successfully!");
							}
							else {
								System.out.println("Same Phone number already Exist");
							}
						}
						else {
							System.out.println("Account is not Found / Incorrect Password");
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;


			case 7:
				System.out.println("THANKYOU FOR YOUR UPDATION!");
				return;

			default:
				System.out.println("INVALID CHOICE");
			}
		}

	}

	@Override
	public void accountDetails() {
		// TODO Auto-generated method stub
		Connection connection=createConnection();
		String query="SELECT account_number,pin_number FROM account WHERE account_number=? AND pin_number=?;";
		System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
		String accountNumber=scanner.nextLine();
		System.out.println("ENTER YOUR ATM-PIN NUMBER : ");
		String password=scanner.nextLine();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1,accountNumber);
			preparedStatement.setString(2, password);

			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				String existingAccountNumber=resultSet.getString("account_number");
				String existingPassword=resultSet.getString("pin_number");
				if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
					String fetchQuery="SELECT * FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement fetchPreparedStatement=connection.prepareStatement(fetchQuery);
					fetchPreparedStatement.setString(1,accountNumber);
					fetchPreparedStatement.setString(2, password);
					resultSet=fetchPreparedStatement.executeQuery();

					if(resultSet.next()) {
						System.out.println("ACCOUNT HOLDER'S NAME : "+resultSet.getString("holder_name")+"\n"
								+ "DATE OF BIRTH : "+resultSet.getString("date_of_birth")+"\n"
								+ "E-MAIL : "+resultSet.getString("email")+"\n"
								+ "MOBILE NUMBER : "+resultSet.getString("phone_number")+"\n"
								+ "BRANCH : "+resultSet.getString("branch")+"\n"
								+ "AVAILABLE BALANCE : "+resultSet.getDouble("balance"));
					}
					connection.close();
					System.out.println("Account Details displayed Successfully!");
				}
				else {
					System.out.println("Account is not Found/Incorrect Pin Number");
				}
			}
			else {
				System.out.println("Invalid Account");
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void accountDeletion() {
		// TODO Auto-generated method stub
		Connection connection=createConnection();
		String query="SELECT account_number,pin_number FROM account WHERE account_number=? AND pin_number=?;";
		System.out.println("ENTER YOUR ACCOUNT NUMBER : ");
		String accountNumber=scanner.nextLine();
		System.out.println("ENTER YOUR ATM-PIN NUMBER : ");
		String password=scanner.nextLine();

		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1,accountNumber);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				String existingAccountNumber=resultSet.getString("account_number");
				String existingPassword=resultSet.getString("pin_number");
				if(accountNumber.equals(existingAccountNumber) && password.equals(existingPassword)) {
					String deleteQuery="DELETE FROM account WHERE account_number=? AND pin_number=?;";
					PreparedStatement deletePreparedStatement=connection.prepareStatement(deleteQuery);
					deletePreparedStatement.setString(1,accountNumber);
					deletePreparedStatement.setString(2, password);

					deletePreparedStatement.executeUpdate();
					connection.close();
					System.out.println("Account deleted Successfully!");

				}
				else {
					System.out.println("Account is not Found/Incorrect Pin Number");
				}
			}
			else {
				System.out.println("Invalid Account");
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}



