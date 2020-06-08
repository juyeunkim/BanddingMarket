package com.ssafy.groupbuying.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.groupbuying.repository.BoardRepository;
import com.ssafy.groupbuying.repository.CommentRepository;
import com.ssafy.groupbuying.repository.ParticipantsRepository;
import com.ssafy.groupbuying.repository.ReputationRepository;
import com.ssafy.groupbuying.repository.UserRepository;
import com.ssafy.groupbuying.vo.Board;
import com.ssafy.groupbuying.vo.Comment;
import com.ssafy.groupbuying.vo.Participants;
import com.ssafy.groupbuying.vo.Reputation;
import com.ssafy.groupbuying.vo.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repo;
	
	@Autowired
	ParticipantsRepository prepo;

	@Autowired
	UserRepository urepo;
	
	@Autowired
	CommentRepository crepo;
	
	@Autowired
	ReputationRepository rrepo;
	
	@Override
	public boolean insert(Board board) {
		// 게시판을 작성하고, 작성한 유저를 참가신청에 넣는다
		repo.save(board);
		
		long uid = board.getUser().getUser_id();
		long bid = board.getBoard_id();
		
		apply(bid, uid);
		
		return true;
	}

	@Override
	public boolean update(Board board) {
		// limit 넘는지 확인
		if(board.getLimit_num() < board.getParticipants()) return false;
		else repo.save(board);
		
		return true;
	}

	@Override
	public boolean delete(long id) {
		// is_deleted 값에 true
		Board board = repo.findById(id);
		board.setDeleted(true);
		repo.save(board);
		
		// Participants DB에서 board_id에 있는 값 모두 삭제
		List<Participants> delete = prepo.findByBoard(board);
		for (int i = 0; i < delete.size(); i++) {
			prepo.delete(delete.get(i));
		}
		return true;
	}

	@Override
	public Board getBoard(long id) {
		return repo.findById(id);
	}

	@Override
	public List<Board> getBoards() {
		// delete된거는 제외하고 불러온다
		return repo.findbyIsDeleted();
	}

	@Override
	public int apply(long bid, long uid) {
		Board board = getBoard(bid);
		User user = urepo.findById(uid);

		// 마감됐는지 체크
		if(board.isDeleted()) return 1;
		
		// 중복 체크
		Participants dul = prepo.findByUserAndBoard(user, board);
		if(dul != null) {
			// 이미 참가 신청한거이므로 false
			return 2;
		}

		// 기존에 있는 참가자수에 +1 후, UPDATE
		int attend = board.getParticipants();
		board.setParticipants(attend+1);
		if(attend > board.getLimit_num()) return 3;
		update(board);
		
		// Participants 테이블에 저장
		Participants newUser =new Participants();
		newUser.setUser(user);
		newUser.setBoard(board);
		
		prepo.save(newUser);
		
		return 0;
	}

	@Override
	public int cancel(long bid, long uid) {
		// 기존에 있는 참가자수에 -1 후, UPDATE
		Board board = getBoard(bid);
		User user = urepo.findById(uid);
		
		// 마감됐는지 체크
		if(board.isDeleted()) return 1;
				
		// 중복 체크
		Participants dul = prepo.findByUserAndBoard(user, board);
		if(dul == null) {
			// DB에 없는 참가신청
			return 2;
		}
		
		// 호스트가 참가 신청한 경우
		if(user == board.getUser()) return 3;
		
		int attend = board.getParticipants();
		board.setParticipants(attend-1);
		update(board);
		
		Participants newUser = new Participants();
		newUser.setUser(user);
		newUser.setBoard(board);
		
		prepo.delete(newUser);
		
		return 0;
	}

	@Override
	public boolean insertComment(Comment com) {
		crepo.save(com);
		return true;
	}

	@Override
	public Comment deleteComment(long cid) {
		Comment com = crepo.findById(cid);
		crepo.delete(com);
		return com;
	}

	@Override
	public boolean updateComment(Comment com) {
		crepo.save(com);
		return true;
	}

	@Override
	public List<Comment> getComments(long bid) {
		// bid -> board -> CommentList
		Board board = repo.findById(bid);
		return crepo.findByBoard(board);
	}

	@Override
	public boolean rate(Reputation rep) {
		rrepo.save(rep);
		return true;
	}

//	@Override
//	public List<Board> getCategoryBoard(String category) {
////	public List<Board> getCategoryBoard(int type, String category) {
////		if (category.equals("")) {
////			return repo.findByType(type);
////		} else {
////			String[] str = category.split(",");
////			String temp = "%";
////			for (int i = 0, size = str.length; i < size; i++) {
////				temp += str[i];
////				if (i != size - 1)
////					temp += "% or category like %";
////			}
////			temp += "%";
////			System.out.println(temp);
////			return repo.findByCategoryLike(temp);
////		}
////		String[] categoryList = category.split(",");
////		return findByCategoryLike(categoryList);
//		
//		String[] str = new String[3];
//		String[] temp = category.split(",");
//		for (int i = 0; i < 3; i++) {
//			if (temp.length > i) str[i] = temp[i];
//			else str[i] = "100";
//		}
//		return repo.findByCategoryLike(str[0], str[1], str[2]);
//	}

	@Override

	public List<Board> search(long user_id) {
		// email로 유저 id 찾기
//		System.out.println(email);
//		List<User> list = urepo.findAll();
//		for (User user : list) {
//			System.out.println(user);
//		}
		User user = urepo.findById(user_id);
//		User user = urepo.findByMail(email);
		
		System.out.println("******");
		System.out.println(user);
		if(user == null) return null; // 존재하지 않는 유저
		
		
		// 모든 게시판들 가져오기
		return repo.findByUser(user);
	}

	public List<Board> getKeywordSearch(String keyword) {
		String[] str = new String[3];
		String[] temp = keyword.substring(2, keyword.length() - 1).split("#");
		System.out.println(keyword);
		for (int i = 0; i < 3; i++) {
			if (temp.length > i) str[i] = temp[i];
			else str[i] = "검색불가";
		}
		System.out.println(Arrays.toString(str));
		List<Board> list = repo.findByKeywordLike(str[0], str[1], str[2]);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getKeyword());
		}
		
		
		return repo.findByKeywordLike(str[0], str[1], str[2]);

	}

	@Override
	public List<Board> searchByYear() {
		return repo.findByYear();
	}

	@Override
	public List<Board> searchByMonth() {
		return repo.findByMonth();
	}

	@Override
	public List<Board> searchByWeek() {
		return repo.findByWeek();
	}
}
