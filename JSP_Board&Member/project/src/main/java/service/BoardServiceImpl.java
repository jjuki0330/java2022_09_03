package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 2");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		log.info("list check 2");
		return bdao.getList();
	}

	@Override
	public BoardVO getdetail(int bNo) {
		log.info("detail check 2");
		return bdao.getdetail(bNo);
	}

	@Override
	public int addcount(int bNo) {
		log.info("addcount check 2");
		return bdao.addcount(bNo);
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info("edit check 2");
		return bdao.edit(bvo);
	}

	@Override
	public int remove(int bNo) {
		log.info("remove check 2");
		CommentServiceImpl csv = new CommentServiceImpl();
		
		int isOk = csv.removeAll(bNo);
		return bdao.remove(bNo);
	}

	@Override
	public int getPageCnt() {
		// TODO Auto-generated method stub
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getListPage(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.selectPageList(pgvo);
	}


	@Override
	public String getFileName(int bNo) {
		log.info("getFileName check 2");
		return bdao.selectgetFileName(bNo);
	}

	@Override
	public List<BoardVO> getMyList(String email) {
		// TODO Auto-generated method stub
		return bdao.getList2(email);
	}

}
