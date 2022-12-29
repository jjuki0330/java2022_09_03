package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getdetail(int bNo);

	int addcount(int bNo);

	int edit(BoardVO bvo);

	int remove(int bNo);

	int getPageCnt();

	List<BoardVO> getListPage(PagingVO pgvo);

	List<BoardVO> getMyList(String email);

	String getFileName(int bNo);


}
