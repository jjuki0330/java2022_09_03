package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {

	MemberVO login(MemberVO mvo);

	int register(MemberVO mvo);

	int lastLogin(String email);

	MemberVO getDetail(String email);

	int getEdit(MemberVO mvo);

	int delete(String email);

	List<MemberVO> getList();



}
