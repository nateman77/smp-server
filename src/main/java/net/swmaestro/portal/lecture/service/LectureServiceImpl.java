package net.swmaestro.portal.lecture.service;


import net.swmaestro.portal.auth.TokenUtil;
import net.swmaestro.portal.lecture.dao.LectureDAO;
import net.swmaestro.portal.lecture.vo.Lecture;
import net.swmaestro.portal.user.dao.UserDAO;
import net.swmaestro.portal.user.vo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("lectureService")
public class LectureServiceImpl implements LectureService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="lectureDAO")
	private LectureDAO lectureDAO;

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Override
	public Lecture selectLecture(int lectureId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lecture_id", lectureId);

		return lectureDAO.selectLecture(map);
	}

	@Override
	public List selectAllLectures() throws Exception {
		return lectureDAO.selectAllLectures();
	}

	@Override
	public void insertLecture(String token, Integer articleGenerationId,
							  String articleSubject, String articleContent, Integer lectureTeacherId, Date lectureBeginAt, Date lectureEndAt) throws Exception {

		int userId = TokenUtil.parse(token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		User user = userDAO.selectUser(map);


		map = new HashMap<String, Object>();
		map.put("article_writer_id", user.getUserId());
		map.put("article_modifier_id", user.getUserId());
		map.put("article_generation_id", "7");
		map.put("article_content", articleContent);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		map.put("article_created_at", dateFormat.format(date));
		map.put("article_updated_at", dateFormat.format(date));
		map.put("article_type", "0");
		map.put("article_status", "0");

		map.put("lecture_teacher_id", lectureTeacherId);
		map.put("lecture_begin_at", lectureBeginAt);
		map.put("lecture_end_at", lectureEndAt);

		lectureDAO.insertLecture(map);

	}

	@Override
	public void deleteLecture(Integer lectureId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lecture_id", lectureId);


		lectureDAO.deleteLecture(map);
	}

}