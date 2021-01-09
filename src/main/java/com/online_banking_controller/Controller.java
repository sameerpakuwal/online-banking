package com.online_banking_controller;

import javax.swing.JOptionPane;

import com.online_banking_dao.OpDao;
import com.online_banking_dao.OpDaoImpl;
import com.online_banking_model.AccountTable;
import com.online_banking_model.TransactionTable;
import com.online_banking_service.OpService;
import com.online_banking_service.OpServiceImpl;

public class Controller {

	OpService opService = new OpServiceImpl();
	static OpDao opDao = new OpDaoImpl();

	public static void main(String[] args) {

		OpService opService = new OpServiceImpl();

		String choice = JOptionPane.showInputDialog("Enter the choice: create | delete | list | transaction");
		switch (choice) {

		case "create":
			AccountTable accountTable = getInfo();
			TransactionTable transactionTable = getBalance();
			
			int saved_1 = opService.insertAccount(accountTable, transactionTable);

			if (saved_1 >= 1) {

				System.out.println("Account created sucessfully!");
			} else {
				System.out.println("something wrong in code");
			}
			break;

		case "delete":

			int del_id = Integer.parseInt(JOptionPane.showInputDialog("Enter the account id: "));

			int delete = opService.deleteAccount(del_id);

			if (delete >= 1) {

				System.out.println("Account deleleted!");
			} else {
				System.out.println("something wrong in code");
			}
			break;
		case "transaction":
			
			Controller.tranInfo();
			break;
		case "list":
			AccountTable accountTable1 = new AccountTable();
			TransactionTable transactionTable1 = new TransactionTable();
			opDao.listTable( accountTable1, transactionTable1);
			break;

		default:
			System.out.println("Wrong choice");
			break;
		}
	}

	public static AccountTable getInfo() {
		AccountTable accountTable = new AccountTable();

		String accountName = JOptionPane.showInputDialog("Enter the name:");
		long accountNo = Long.parseLong(JOptionPane.showInputDialog("Enter the account number: "));
		String email = JOptionPane.showInputDialog("Enter the email:");
		long mobileNo = Long.parseLong(JOptionPane.showInputDialog("Enter the mobile number"));

		accountTable.setAccountName(accountName);
		accountTable.setAccountNo(accountNo);
		accountTable.setEmail(email);
		accountTable.setMobileNo(mobileNo);

		return accountTable;
	}
	
	public static TransactionTable getBalance() {
		
		TransactionTable getBalance = new TransactionTable();
		double balance =Double.parseDouble(JOptionPane.showInputDialog("Enter the amount you want to deposit: "));
		getBalance.setBalance(balance);
		
		return getBalance;
		
	}

	public static void tranInfo() {
		OpService opService = new OpServiceImpl();
		//TransactionTable transactionTable = new TransactionTable();
		String choice = JOptionPane
				.showInputDialog("What operation do you want to perform?" + "check | deposit | withdraw");

		switch (choice) {
		case "check":
			int check_id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id you want to check balance:"));
			int check = opService.checkBalance(check_id);
			
			if (check>=1) {
				System.out.println("Check info sucess");
			}
			break;
			
		case "deposit":
			int depo_id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id you want to deposit balance to:"));
			double depo_am = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to deposit:"));
			if (depo_am<0) {
				System.out.println("enter positive value.");
			}else {
			int deposit = opService.deposit(depo_id, depo_am);
			if(deposit>1) {
				
				JOptionPane.showInternalMessageDialog(null, "Deposit sucessfull.");
			}
			}
			
			break;
		case "withdraw":
			int with_id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id number to withdraw balance:"));
			double with_am = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to withdraw:"));
			
			 check = opService.withdrawAmount(with_id,with_am);

			break;

		default:
			break;
		}

	}

}
