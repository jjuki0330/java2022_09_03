package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectList(int bNo);

	int remove(int cNo);

	int update(CommentVO cvo);

	int removeAll(int bNo);

}
