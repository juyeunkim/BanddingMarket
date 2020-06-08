package com.ssafy.groupbuying.dto;

import com.ssafy.groupbuying.vo.User;



public  class UserAndBoardCount extends User {
	
	private int boardCount ;

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	} 
	
	

}
