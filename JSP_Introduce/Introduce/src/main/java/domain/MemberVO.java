package domain;

public class MemberVO {

	private String email;
	private String password;
	private String nickname;
	private String reg_at;
	
	public MemberVO() {}
	
	//insert, modify(email, password, nickname)
	public MemberVO(String email, String password, String nickname) {
		this.email=email;
		this.password=password;
		this.nickname=nickname;
	}	
	//login(email, password)
	public MemberVO(String email, String password) {
		this.email=email;
		this.password=password;
	}
	//list(all)
	public MemberVO(String email, String password, String nickname, String reg_at) {
		this(email, password, nickname);
		this.reg_at=reg_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}
	
	
}
