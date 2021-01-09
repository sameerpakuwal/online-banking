package com.online_banking_service;

import com.online_banking_model.AccountTable;
import com.online_banking_model.TransactionTable;

public interface OpService {


	int insertAccount(AccountTable accountTable, TransactionTable transactionTable );

	int deleteAccount(int del_id);

	int checkBalance(int check_id);

	int deposit(int depo_id, double depo_am);

	int withdrawAmount(int with_id, double with_am);
	
	
}
