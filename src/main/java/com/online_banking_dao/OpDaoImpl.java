package com.online_banking_dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.online_banking_model.AccountTable;
import com.online_banking_model.TransactionTable;
import com.vastika.sr.util.DbUtil;

public class OpDaoImpl implements OpDao {

	Date date = new Date();
	String dd = "yyyy-MM-dd";
	SimpleDateFormat sf = new SimpleDateFormat(dd);
	String dD = sf.format(date);

	public static final String INSERT_SQL = "call insert_into_twoTables(?,?,?,?,?)";

	public static final String DELETE_SQL = "DELETE bank_db.account_tbl, bank_db.transaction_tbl from account_tbl inner join transaction_tbl where account_tbl.account_id =transaction_tbl.account_id and transaction_tbl.account_id =? ";

	public static final String BALANCE_SQL = "select  balance from transaction_tbl where account_id = ?";

	public static final String BALANCE_SQL1 = "select * from bank_db.account_tbl inner join bank_db.transaction_tbl using(account_id)\n"
			+ "where account_id =?";

	public static final String DEPOSIT_SQL = "select  balance from transaction_tbl where account_id = ?";

	public static final String LIST_SQL = "select * from bank_db.account_tbl inner join bank_db.transaction_tbl using(account_id)";

	@Override
	public int insertTable(AccountTable accountTable, TransactionTable transactionTable) {
		int insert = 0;

		try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(INSERT_SQL);) {

			ps.setString(1, accountTable.getAccountName());
			ps.setLong(2, accountTable.getAccountNo());
			ps.setString(3, accountTable.getEmail());
			ps.setLong(4, accountTable.getMobileNo());
			ps.setDouble(5, transactionTable.getBalance());
			insert = ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return insert;
	}

	@Override
	public int deleteAccount(int del_id) {
		int delete = 0;

		try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(DELETE_SQL);) {
			ps.setInt(1, del_id);
			delete = ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return delete;
	}

	@Override
	public int checkBalance(int check_id) {
		int check = 0;
		try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(BALANCE_SQL1);) {
			ps.setInt(1, check_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				check = 1;
				double bal = rs.getDouble("balance");
				String name = rs.getString("account_name");
				System.out.println("The balance for the id " + check_id + " is $ " + bal + " name " + name);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int deposit(int depo_id, double depo_am) {
		int check = 0;
		try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(BALANCE_SQL);) {
			ps.setInt(1, depo_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double bal = rs.getDouble("balance");
				bal += depo_am;
				check = ps.executeUpdate("update transaction_tbl set balance ='" + bal + "',deposit_amount='" + depo_am
						+ "',deposited_date ='" + dD + "' where account_id ='" + depo_id + "'");
				System.out.println("The balance for the id " + depo_id + " is $ " + bal);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public int withdrawAmount(int with_id, double with_am) {
		int check = 0;
		try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(BALANCE_SQL);) {
			ps.setInt(1, with_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				double bal = rs.getDouble("balance");
				System.out.println("Available balance is: " + bal);
				if (bal > with_am) {
					bal -= with_am;
					check = ps.executeUpdate("update transaction_tbl set balance ='" + bal + "',withdrawn_amount='"
							+ with_am + "',deposited_date ='" + dD + "' where account_id ='" + with_id + "'");
					System.out.println("Withdrawn Amount: " + with_am);
					System.out.println("The new updated balance for the id " + with_id + " is $ " + bal);
				} else {
					System.out.println("invalid transaction: Withdraw amount request is > Available Amount");
					System.out.println("Try Again!!");
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public void listTable(AccountTable accountTable, TransactionTable transactionTable) {
		try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(LIST_SQL);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AccountTable acc = new AccountTable();
				TransactionTable tran = new TransactionTable();
				acc.setAccountName(rs.getString("account_name"));
				acc.setAccountId(rs.getInt("account_id"));
				acc.setAccountNo(rs.getInt("account_no"));
				acc.setEmail(rs.getString("email"));
				acc.setMobileNo(rs.getLong("mobile_no"));
				tran.setBalance(rs.getDouble("balance"));

				System.out.println("The name of the account holder is: " + acc.getAccountName());
				System.out.println("The account holder id is: " + acc.getAccountId());
				System.out.println("The account no of the account holder is: " + acc.getAccountNo());
				System.out.println("The email id of the account holder is: " + acc.getEmail());
				System.out.println("The mobile no of the account holder is: " + acc.getMobileNo());
				System.out.println("The balance of the account holder is:" + tran.getBalance());
				System.out.println();
				System.out.println("+++++++++++++++++++++++++++++++");
				System.out.println();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
