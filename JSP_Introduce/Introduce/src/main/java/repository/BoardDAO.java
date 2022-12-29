package repository;

import java.util.List;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	int getPageCnt();

	List<MemberVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bNo);

	int getEdit(BoardVO bvo);

	String getFileName(int bNo);

	int delete(int bNo);

}
