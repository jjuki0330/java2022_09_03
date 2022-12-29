package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	MemberVO selectOne(MemberVO mvo);

	int insert(MemberVO mvo);

	int update(String email);

	MemberVO modify(String email);

	int edit(MemberVO mvo);

	int delete(String email);

	List<MemberVO> list();


}
