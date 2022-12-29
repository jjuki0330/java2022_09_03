package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	
	
	   private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	   private SqlSession sql;
	   private final String NS = "MemberMapper.";
	   
	   public MemberDAOImpl() {
	      new DatabaseBuilder();
	      sql = DatabaseBuilder.getFactory().openSession();
	   }

	@Override 
	public MemberVO selectOne(MemberVO mvo) {
		log.info("login check 3");
		return sql.selectOne(NS+"login", mvo);
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("join check 3");
		int isOk = sql.insert(NS+"reg",mvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int update(String email) {
		log.info("lastlogin check 3");
		int isOk = sql.update(NS+"last",email);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public MemberVO modify(String email) {
		log.info("modify check 3");
		return sql.selectOne(NS+"modify", email);
	}

	@Override
	public int edit(MemberVO mvo) {
		log.info("edit check 3");
		int isOk= sql.update(NS+"update", mvo);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public int delete(String email) {
		log.info("delete check 3");
		int isOk=sql.delete(NS+"delete", email);
		if(isOk>0)sql.commit();
		return isOk;
	}

	@Override
	public List<MemberVO> list() {
		log.info("list check 3");
		return sql.selectList(NS+"list");
	}



}
