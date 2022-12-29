package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	private final String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert check 3");
		int isOk= sql.insert(NS+"insert", bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("list check 3");
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO getdetail(int bNo) {
		log.info("detail check 3");
		return sql.selectOne(NS+"detail", bNo);
	}

	@Override
	public int addcount(int bNo) {
		log.info("addcount check 3");
		int isOk= sql.update(NS+"addcount", bNo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info("edit check 3");
		int isOk = sql.update(NS+"update", bvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int remove(int bNo) {
		log.info("remove check 3");
		int isOk= sql.delete(NS+"delete", bNo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"cnt");
	}

	@Override
	public List<BoardVO> selectPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"pagingList", pgvo);
	}

	@Override
	public List<BoardVO> getList2(String email) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"emailList", email);
	}

	@Override
	public String selectgetFileName(int bNo) {
		return sql.selectOne(NS+"getFileName", bNo);
	}


}
