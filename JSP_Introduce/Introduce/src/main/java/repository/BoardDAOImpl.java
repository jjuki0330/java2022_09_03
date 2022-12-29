package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {

	private static final Logger log= LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	private String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 3");
		int isOk = sql.insert(NS+"insert", bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int getPageCnt() {
		log.info("getPageCnt check 3");
		return sql.selectOne(NS+"getPageCnt");
	}

	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		log.info("getList check 3");
		return sql.selectList(NS+"getList", pgvo);
	}

	@Override
	public BoardVO getDetail(int bNo) {
		log.info("getDetail check 3");
		return sql.selectOne(NS+"getDetail", bNo);
	}

	@Override
	public int getEdit(BoardVO bvo) {
		log.info("getEdit check 3");
		int isOk = sql.update(NS+"edit",bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public String getFileName(int bNo) {
		log.info("getFileName check 3");
		return sql.selectOne(NS+"getFileName",bNo);
	}

	@Override
	public int delete(int bNo) {
		log.info("delete check 3");
		int isOk=sql.delete(NS+"delete", bNo);
		if(isOk>0)sql.commit();
		return isOk;
	}
}
