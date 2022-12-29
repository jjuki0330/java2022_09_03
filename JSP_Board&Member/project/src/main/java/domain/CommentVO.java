package domain;

public class CommentVO {

	private int cNo;
	private int bNo;
	private String writer;
	private String content;
	private String reg_at;
	
	public CommentVO() {}
	
	//POST(bNo, writer, content)
	public CommentVO(int bNo, String writer, String content) {
		this.bNo=bNo;
		this.writer=writer;
		this.content=content;
	}
	
	//LIST: bNo, cNo, writer, content, reg_at
	public CommentVO(int bNo, int cNo, String writer, String content, String reg_at) {
		this(bNo, writer, content);
		this.cNo=cNo;
		this.reg_at=reg_at;
		
	}
	
	//MODIFY: cNo, content
	public CommentVO(int cNo, String content) {
		this.cNo=cNo;
		this.content=content;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}
	
	
	
}
