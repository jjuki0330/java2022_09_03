package domain;

public class BoardVO {

//	mysql> create table pboard(
//		    -> bNo int primary key,
//		    -> writer varchar(100),
//		    -> reg_at datetime default current_timestamp,
//		    -> content text,
//		    -> image_file varchar(100),
//		    -> heart int);
	
	private int bNo;
	private String writer;
	private String reg_at;
	private String content;
	private String image_file;
	private int heart;
	
	public BoardVO() {}
	
	//edit(content, image_file)
	public BoardVO(int bNo, String content, String image_file) {
		this.bNo=bNo;
		this.content=content;
		this.image_file=image_file;
	}
	//post(writer, content, image_file)
	public BoardVO(String writer, String content, String image_file) {
		this.content=content;
		this.image_file=image_file;
		this.writer=writer;
	}
	//detail(all)
	public BoardVO(int bNo, String writer, String reg_at, String content, String image_file, int heart) {
		this(bNo, content, image_file);
		this.writer=writer;
		this.heart=heart;
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

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	@Override
	public String toString() {
		return "BoardVO [bNo=" + bNo + ", writer=" + writer + ", reg_at=" + reg_at + ", content=" + content
				+ ", image_file=" + image_file + ", heart=" + heart + "]";
	}
	
	
	
}
