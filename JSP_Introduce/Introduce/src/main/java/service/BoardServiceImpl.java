package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao=new BoardDAOImpl();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 2");
		return bdao.insert(bvo);
	}

	@Override
	public int getPageCnt() {
		log.info("getPageCnt check 2");
		return bdao.getPageCnt();
	}

	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		log.info("getList check 2");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bNo) {
		log.info("getDetail check 2");
		return bdao.getDetail(bNo);
	}

	@Override
	public int getEdit(BoardVO bvo) {
		log.info("getEdit check 2");
		return bdao.getEdit(bvo);
	}

	@Override
	public String getFileName(int bNo) {
		log.info("getFileName check 2");
		return bdao.getFileName(bNo);
	}

	@Override
	public int delete(int bNo) {
		log.info("delete check 2");
		return bdao.delete(bNo);
	}

}
