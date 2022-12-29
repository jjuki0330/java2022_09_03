package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql= DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info("login_auth check 3");
		return sql.selectOne(NS+"login", mvo);
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("register check 3");
		int isOk= sql.insert(NS+"insert", mvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int getPageCnt() {
		log.info("getPageCnt check 3");
		return sql.selectOne(NS+"pageCnt");
	}

	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		log.info("getList check 3");
		return sql.selectList(NS+"pagingList", pgvo);
	}

	@Override
	public MemberVO getDetail(String email) {
		log.info("getDetail check 3");
		return sql.selectOne(NS+"modify", email);
	}

	@Override
	public int edit(MemberVO mvo) {
		log.info("edit check 3");
		int isOk = sql.update(NS+"update", mvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int delete(String email) {
		log.info("delete check 3");
		int isOk = sql.delete(NS+"delete", email);
		if(isOk>0)sql.commit();
		return isOk;
	}


	
}
