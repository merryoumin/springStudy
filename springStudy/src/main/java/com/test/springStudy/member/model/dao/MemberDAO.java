package com.test.springStudy.member.model.dao;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.test.springStudy.member.model.dto.MemberDTO;

public interface MemberDAO {
	public int insertMember(MemberDTO dto); 
	public List<MemberDTO> getList(int startRecord ,int lastRecord, Map<String, Object> map);
	public MemberDTO getSelectOne(Map<String, Object> map,MemberDTO dto);
	public int updateMember(MemberDTO dto);
	public String deleteMember(MemberDTO dto);
	public int idCheck(String id);
	public String idCheckResultString(String id);
	public String pwdCheck(MemberDTO dto);
	public MemberDTO login(MemberDTO dto);
	public int getTotalRecord(Map<String, Object> map);

}