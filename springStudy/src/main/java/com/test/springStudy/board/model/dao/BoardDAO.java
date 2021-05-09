package com.test.springStudy.board.model.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.test.springStudy.board.model.dto.BoardChkDTO;
import com.test.springStudy.board.model.dto.BoardDTO;
import com.test.springStudy.board.model.dto.CommentBoardDTO;
import com.test.springStudy.member.model.dto.MemberDTO;
import com.test.springStudy.memo.model.dto.MemoDTO;

public interface BoardDAO {
	public int getMaxNum();
	public int getMaxRefNo();
	public int getMaxNoticeNo(String tbl);
	public int setInsert(BoardDTO dto);
	public List<BoardDTO> getList(HashMap<String, Object> map, int startRecord, int lastRecord);
	public int getTotalRecord(HashMap<String, Object> map);
	public int getTotalRecordComment(int no);
	public int setCommmentInsert(CommentBoardDTO dto);
	public List<CommentBoardDTO> getCommentListAll(int no, int startRecord, int lastRecord);
	public BoardDTO getView(Map<String, Object> map,BoardDTO dto);
	public void setUpdatHit(int no);
	public void setUpdateRelevel(BoardDTO dto);
	public void setUpdate(BoardDTO dto);
	public int deleteBoard(BoardDTO dto);
	public int deleteCM(CommentBoardDTO dto);
	public int updateCM(CommentBoardDTO dto);
	public int boardChk(String tbl);
}
