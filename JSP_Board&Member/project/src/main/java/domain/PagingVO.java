package domain;

public class PagingVO {

	private int pageNo;//현재 화면에 출력되는 페이지네이션 번호
	private int qty;//몇개씩 나타낼지
	
	public PagingVO() {//페이지네이션을 클릭하기 전 기본 리스트 출력값
		this(1, 10);//값이 들어오는 것이 하나도 없으면 1페이지 10개로 세팅
	}
	

	public PagingVO(int pageNo, int qty) {
		this.pageNo=pageNo;
		this.qty=qty;
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty;//시작페이지 번호 구하기
	}
	
	public int getPageNo() {//혹시라도 pageNo를 가져와야할 때를 대비
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
