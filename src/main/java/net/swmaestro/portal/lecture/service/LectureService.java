package net.swmaestro.portal.lecture.service;


import net.swmaestro.portal.lecture.vo.Lecture;

import java.util.Date;
import java.util.List;

public interface LectureService {

	Lecture selectLecture(int lectureId) throws Exception;
	List selectAllLectures() throws Exception;

	void insertLecture(String token, Integer articleGenerationId,
					   String articleSubject, String articleContent, Integer lectureTeacherId, Date lectureBeginAt, Date lectureEndAt) throws Exception;

	void deleteLecture(Integer lectureId);
}
