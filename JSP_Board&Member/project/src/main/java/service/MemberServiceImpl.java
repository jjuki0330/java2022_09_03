package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");
		return mdao.selectOne(mvo);
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("join check2");
		return mdao.insert(mvo);
	}

	@Override
	public int lastLogin(String email) {
		log.info("lastLogin check2");
		return mdao.update(email);
	}

	@Override
	public MemberVO getDetail(String email) {
		log.info("modify check2");
		return mdao.modify(email);
	}

	@Override
	public int getEdit(MemberVO mvo) {
		log.info("edit check2");
		return mdao.edit(mvo);
	}

	@Override
	public int delete(String email) {
		log.info("delete check2");
		return mdao.delete(email);
	}

	@Override
	public List<MemberVO> getList() {
		log.info("list check 2");
		return mdao.list();
	}


	
}
