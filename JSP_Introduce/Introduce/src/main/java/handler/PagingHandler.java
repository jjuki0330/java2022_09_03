package handler;

import domain.PagingVO;

public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int pageQty;//페이지네이션을 몇개씩 출력할건지
	
	private int totalCount;//전체 글 수 
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo,int totalCount) {
		this.pgvo=pgvo;
		this.totalCount=totalCount;
		
		this.pageQty=5;
		this.endPage=(int)Math.ceil(pgvo.getPageNo()/(pageQty*1.0))*pageQty;
		
		this.startPage=this.endPage-(pageQty-1);
		int realEndPage=(int)(Math.ceil((totalCount*1.0)/pageQty));
		if(realEndPage<this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev=this.startPage>1;
		this.next=this.endPage<realEndPage;
			
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
	

}
