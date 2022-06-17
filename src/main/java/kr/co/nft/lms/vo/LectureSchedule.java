package kr.co.nft.lms.vo;

import lombok.Data;

@Data
public class LectureSchedule {
	private String lectureScheduleDate;
	private int lectureNo;
	private String lectureScheduleStartDate;
	private String lectureScheduleEndDate;
	private String lectureScheduleCreateDate;
}
