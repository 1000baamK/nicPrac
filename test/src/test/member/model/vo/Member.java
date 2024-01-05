package test.member.model.vo;

public class Member {

	private int memberNo;		//회원번호
	private String memberId;	//회원아이디
	private String memberPwd;	//비밀번호
	private String email;		//이메일
	private String name;		//이름
	
	public Member() {
		super();
	}

	public Member(int memberNo, String memberId, String memberPwd, String email, String name) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.email = email;
		this.name = name;
	}

	public Member(String memberId, String memberPwd, String email, String name) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.email = email;
		this.name = name;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", email="
				+ email + ", name=" + name + "]";
	}
	
	
	
}
