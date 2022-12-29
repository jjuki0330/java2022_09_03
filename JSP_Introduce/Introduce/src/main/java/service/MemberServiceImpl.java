package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import domain.PagingVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	
	private static final Logger log= LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao= new MemberDAOImpl();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login_auth check 2");
		return mdao.selectOne(mvo);
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("register check 2");
		return mdao.insert(mvo);
	}

	@Override
	public int getPageCnt() {
		log.info("getPageCnt check 2");
		return mdao.getPageCnt();
	}

	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		log.info("getList check 2");
		return mdao.getList(pgvo);
	}

	@Override
	public MemberVO getDetail(String email) {
		log.info("getDetail check 2");
		return mdao.getDetail(email);
	}

	@Override
	public int edit(MemberVO mvo) {
		log.info("edit check 2");
		return mdao.edit(mvo);
	}

	@Override
	public int delete(String email) {
		log.info("delete check 2");
		return mdao.delete(email);
	}


}
