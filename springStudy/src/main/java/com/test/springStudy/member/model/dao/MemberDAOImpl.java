package com.test.springStudy.member.model.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.test.springStudy.member.model.dto.MemberDTO;


@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	SqlSession SqlSession;

	@Override
	public int insertMember(MemberDTO dto) {
		int result = 0;
		// TODO Auto-generated method stub
		result = SqlSession.insert("member.setInsert", dto);
		return result;
	}

	@Override
	public List<MemberDTO> getList(int startRecord, int lastRecord, Map<String, Object> map) {
		// TODO Auto-generated method stub
		map.put("startRecord",startRecord);
		map.put("lastRecord",lastRecord);
		return SqlSession.selectList("member.getList",map);
	}

	@Override
	public MemberDTO getSelectOne(Map<String, Object> map, MemberDTO dto) {
		// TODO Auto-generated method stub
		map.put("dto",dto);
		return SqlSession.selectOne("member.getView", map);
	}

	@Override
	public int updateMember(MemberDTO dto) {
		int result = 0;
		// TODO Auto-generated method stub
		result = SqlSession.update("member.setUpdate", dto);
		return result;
	}

	@Override
	public String deleteMember(MemberDTO dto) {
		String result = "";
		int deleteResult = 0;
		String dbPw = pwdCheck(dto);
		if(dto.getPasswd().equals(dbPw)) {
			deleteResult = SqlSession.delete("member.deleteMember", dto);
			
			if(deleteResult > 0) {
				result = "success";
			} else {
				result = "DBFail";
			}
		} else {
			result = "passwdFail";
		}
		return result;
	}

	@Override
	public int idCheck(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		int result = SqlSession.selectOne("member.idCheck", map);
		return result;
	}

	@Override
	public String idCheckResultString(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		String result = SqlSession.selectOne("member.idCheckResultString", map);
		return result;
	}

	@Override
	public String pwdCheck(MemberDTO dto) {
		String result = SqlSession.selectOne("member.pwdCheck", dto);
		return result;
	}

	@Override
	public MemberDTO login(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		dto = SqlSession.selectOne("member.getLogin", map);
		return dto;
	}

	@Override
	public int getTotalRecord(Map<String, Object> map) {
		int result = 0;
		// TODO Auto-generated method stub
		result = SqlSession.selectOne("member.getTotalRecord", map);
		return result;
	}

}
