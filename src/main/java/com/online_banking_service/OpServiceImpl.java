package com.online_banking_service;

import com.online_banking_dao.OpDao;
import com.online_banking_dao.OpDaoImpl;
import com.online_banking_model.AccountTable;
import com.online_banking_model.TransactionTable;

public class OpServiceImpl implements OpService {

	OpDao opDao = new OpDaoImpl();
	

	@Override
	public int insertAccount(AccountTable accountTable, TransactionTable transactionTable) {
		
		return opDao.insertTable(accountTable, transactionTable);
	}

	@Override
	public int deleteAccount(int del_id) {
		
		return opDao.deleteAccount(del_id);
	}

	@Override
	public int checkBalance(int check_id) {
		
		return opDao.checkBalance(check_id);
	}

	@Override
	public int deposit(int depo_id, double depo_am) {
		
		return opDao.deposit(depo_id,depo_am );
	}

	@Override
	public int withdrawAmount(int with_id, double with_am) {
		
		return opDao.withdrawAmount(with_id,with_am);
	}

	


}
