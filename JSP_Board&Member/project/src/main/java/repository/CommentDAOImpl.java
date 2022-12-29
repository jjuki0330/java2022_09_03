package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	
	private static final Logger log=LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS ="CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql=DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(CommentVO cvo) {
		log.info(">>>comment > post> check 3");
		isOk = sql.insert(NS+"add", cvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> selectList(int bNo) {
		log.info(">>> comment > list > check 3");
		return sql.selectList(NS+"list", bNo);
	}

	@Override
	public int remove(int cNo) {
		log.info(">>> comment > remove > check 3");
		isOk= sql.delete(NS+"delete", cNo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int update(CommentVO cvo) {
		log.info(">>> comment > modify > check 3");
		isOk= sql.delete(NS+"update", cvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int removeAll(int bNo) {
		log.info(">>> comment > removeAll > check 3");
		isOk= sql.delete(NS+"removeAll", bNo);
		if(isOk>0)sql.commit();
		return isOk;
	}
	
	
}
