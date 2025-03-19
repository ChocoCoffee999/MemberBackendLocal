package edu.ssafy.service;

import java.util.List;

import edu.ssafy.dto.MemberDto;
import edu.ssafy.repository.MemberRepository;
import edu.ssafy.repository.MemberRepositoryImpl;

public class MemberServiceImpl implements MemberService{
	MemberRepository repo;
	public MemberServiceImpl() {
		repo = new MemberRepositoryImpl();
	}
	@Override
	public int MemberInsert(MemberDto m) throws Exception {
		// TODO Auto-generated method stub
		return repo.MemberInsert(m);
	}

	@Override
	public int MemberUpdate(MemberDto m) throws Exception {
		// TODO Auto-generated method stub
		return repo.MemberUpdate(m);
	}

	@Override
	public int MemberDelete(String id) throws Exception {
		// TODO Auto-generated method stub
		return repo.MemberDelete(id);
	}

	@Override
	public List<MemberDto> MemberSelectAll() throws Exception {
		// TODO Auto-generated method stub
		return repo.MemberSelectAll();
	}

	@Override
	public MemberDto MemberSelect(String id) throws Exception {
		// TODO Auto-generated method stub
		return repo.MemberSelect(id);
	}

}
