package service;

import java.util.List;

import domain.CommentVO;

public interface CommentService {

	int post(CommentVO cvo);

	List<CommentVO> getList(int bNo);

	int remove(int cNo);

	int update(CommentVO cvo);

}
