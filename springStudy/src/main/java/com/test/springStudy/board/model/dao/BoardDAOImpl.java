package com.test.springStudy.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.test.springStudy.board.model.dto.BoardDTO;
import com.test.springStudy.board.model.dto.CommentBoardDTO;

public class BoardDAOImpl implements BoardDAO {
	@Inject
	SqlSession SqlSession;
	
	@Override
	public int getMaxNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxRefNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxNoticeNo(String tbl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setInsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> getList(HashMap<String, Object> map, int startRecord, int lastRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalRecord(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalRecordComment(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setCommmentInsert(CommentBoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CommentBoardDTO> getCommentListAll(int no, int startRecord, int lastRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO getView(Map<String, Object> map, BoardDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUpdatHit(int no) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpdateRelevel(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUpdate(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public int deleteBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCM(CommentBoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCM(CommentBoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int boardChk(String tbl) {
		// TODO Auto-generated method stub
		return 0;
	}

}
