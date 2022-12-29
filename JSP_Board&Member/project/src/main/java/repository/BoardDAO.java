package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getdetail(int bNo);

	int addcount(int bNo);

	int edit(BoardVO bvo);

	int remove(int bNo);

	int selectCount();

	List<BoardVO> selectPageList(PagingVO pgvo);

	List<BoardVO> getList2(String email);

	String selectgetFileName(int bNo);


}
