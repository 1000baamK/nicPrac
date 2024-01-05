package test.account.model.vo;

public class Account {
	
	private int memberNo;			//회원번호
	private int bankCode;			//은행코드
	private String accountNumber;	//계좌번호
	private int balance;			//잔액
	
	
	public Account() {
		super();
	}

	public Account(int memberNo, int bankCode, String accountNumber, int balance) {
		super();
		this.memberNo = memberNo;
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBankCode() {
		return bankCode;
	}

	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	@Override
	public String toString() {
		return "Account [memberNo=" + memberNo + ", bankCode=" + bankCode + ", accountNumber=" + accountNumber
				+ ", balance=" + balance + "]";
	}
	
	
	
}
