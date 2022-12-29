package domain;

public class BoardVO {
	private int bNo;
	private String title;
	private String writer;
	private String content;
	private String reg_date;
	private int read_count;
	private String image_file;
	
	public BoardVO() {}
	
	//insert: title, writer, content
	public BoardVO(String title, String writer, String content) {
		this.title=title;
		this.writer=writer;
		this.content=content;
	}
	//list: bNo, title, writer, reg_date, read_count
	public BoardVO(int bNo, String title, String writer, String reg_date, int read_count) {
		this.bNo=bNo;
		this.title=title;
		this.writer=writer;
		this.reg_date=reg_date;
		this.read_count=read_count;
	}
	//detail: all
	public BoardVO(int bNo, String title, String writer, String content, String reg_date, int read_count, String image_file) {
		this(bNo, title, writer, reg_date, read_count);
		this.content=content;
		this.image_file=image_file;
	}
	//update: bNo, title, content
	public BoardVO(int bNo, String title, String content) {
		this.bNo=bNo;
		this.title=title;
		this.content=content;
	}
	


	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	
	
}
