package service;

import java.util.List;

import domain.MemberVO;
import domain.PagingVO;

public interface MemberService {

	MemberVO login(MemberVO mvo);

	int insert(MemberVO mvo);

	int getPageCnt();

	List<MemberVO> getList(PagingVO pgvo);

	MemberVO getDetail(String email);

	int edit(MemberVO mvo);

	int delete(String email);



}
