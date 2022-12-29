package domain;

public class PagingVO {
	
	private int pageNo;
	private int qty;
	
	public PagingVO() {
		this(1,6);//값이 들어오는 것이 하나도 없으면 1페이지 10개씩 세팅
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo=pageNo;
		this.qty=qty;
	}

	public int getPageStart() {
		return (this.pageNo-1)*qty;//시작페이지 번호 구하기
	}
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
