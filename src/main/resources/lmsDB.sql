-- --------------------------------------------------------
-- 호스트:                          13.209.80.77
-- 서버 버전:                        10.3.35-MariaDB-1:10.3.35+maria~bionic - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- lms 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `lms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lms`;

-- 테이블 lms.address 구조 내보내기
CREATE TABLE IF NOT EXISTS `address` (
  `member_id` varchar(100) NOT NULL,
  `zip_code` char(5) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `town` varchar(30) DEFAULT NULL,
  `roadAddr` varchar(255) DEFAULT NULL,
  `addr_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FK_address_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.attend 구조 내보내기
CREATE TABLE IF NOT EXISTS `attend` (
  `member_id` varchar(100) NOT NULL,
  `lecture_no` int(11) NOT NULL,
  `attend_date` date NOT NULL,
  `attend_start` datetime NOT NULL,
  `attend_end` datetime NOT NULL,
  `attend_status` enum('출석','지각','결석','병결','공결') NOT NULL,
  `attend_reason` text NOT NULL,
  `attend_create_date` datetime NOT NULL,
  `attend_update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`,`lecture_no`,`attend_date`),
  KEY `FK_attend_lecture_schedule` (`attend_date`),
  KEY `FK_attend_lecture_schedule_2` (`lecture_no`),
  CONSTRAINT `FK_attend_lecture_schedule` FOREIGN KEY (`attend_date`) REFERENCES `lecture_schedule` (`lecture_schedule_date`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_attend_lecture_schedule_2` FOREIGN KEY (`lecture_no`) REFERENCES `lecture_schedule` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `board_no` int(11) NOT NULL AUTO_INCREMENT,
  `board_title` varchar(100) NOT NULL,
  `board_content` varchar(255) NOT NULL,
  `board_category` enum('공지','건의','강의') NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `board_privilege` varchar(100) NOT NULL,
  `board_blind` enum('Y','N') NOT NULL,
  `board_create_date` datetime NOT NULL,
  `board_update_date` datetime NOT NULL,
  PRIMARY KEY (`board_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.board_authority 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_authority` (
  `board_no` int(11) NOT NULL,
  `board_category` varchar(100) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `board_authority_read` enum('Y','N') NOT NULL,
  `board_authority_update` enum('Y','N') NOT NULL,
  `board_authority_create` enum('Y','N') NOT NULL,
  `board_authority_delete` enum('Y','N') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.board_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_file` (
  `board_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `board_no` int(11) NOT NULL,
  `board_file_name` varchar(100) NOT NULL,
  `board_file_origin_name` varchar(100) NOT NULL,
  `board_file_type` varchar(100) NOT NULL,
  `board_file_size` int(11) NOT NULL,
  PRIMARY KEY (`board_file_no`),
  KEY `FK_board_file_board` (`board_no`),
  CONSTRAINT `FK_board_file_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `comment` (
  `comment_no` int(11) NOT NULL,
  `board_no` int(11) NOT NULL,
  `comment_title` varchar(100) NOT NULL,
  `comment_content` text NOT NULL,
  `comment_blind` enum('Y','N') NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `comment_create_date` datetime NOT NULL,
  `comment_update_date` datetime NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `FK_comment_board` (`board_no`),
  CONSTRAINT `FK_comment_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.exam 구조 내보내기
CREATE TABLE IF NOT EXISTS `exam` (
  `exam_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(100) NOT NULL,
  `lecture_no` int(11) NOT NULL,
  `exam_title` varchar(100) NOT NULL,
  `exam_count` int(11) NOT NULL,
  `exam_max_score` int(11) NOT NULL,
  `exam_start_date` datetime NOT NULL,
  `exam_end_date` datetime NOT NULL,
  `exam_create_date` datetime NOT NULL,
  `exam_update_date` datetime NOT NULL,
  PRIMARY KEY (`exam_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.exam_answer 구조 내보내기
CREATE TABLE IF NOT EXISTS `exam_answer` (
  `exam_no` int(11) NOT NULL,
  `exam_question_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `exam_answer` varchar(100) NOT NULL,
  `exam_score` int(11) NOT NULL,
  `exam_start_date` datetime NOT NULL,
  `exam_end_date` datetime NOT NULL,
  PRIMARY KEY (`exam_no`,`exam_question_no`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.exam_example 구조 내보내기
CREATE TABLE IF NOT EXISTS `exam_example` (
  `exam_no` int(11) NOT NULL,
  `exam_question_no` int(11) NOT NULL,
  `example_no` int(11) NOT NULL,
  `example_content` varchar(100) NOT NULL,
  `example_create_date` datetime NOT NULL,
  `example_update_date` datetime NOT NULL,
  PRIMARY KEY (`exam_no`,`exam_question_no`,`example_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.exam_question 구조 내보내기
CREATE TABLE IF NOT EXISTS `exam_question` (
  `exam_no` int(11) NOT NULL,
  `exam_question_no` int(11) NOT NULL,
  `exam_content` text NOT NULL,
  `exam_correct_answer` varchar(100) NOT NULL,
  `exam_point` int(11) NOT NULL,
  `exam_type` varchar(100) NOT NULL,
  `exam_create_date` datetime NOT NULL,
  `exam_update_date` datetime NOT NULL,
  PRIMARY KEY (`exam_no`,`exam_question_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.homework 구조 내보내기
CREATE TABLE IF NOT EXISTS `homework` (
  `homework_no` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(100) NOT NULL,
  `lecture_no` int(11) NOT NULL,
  `homework_title` varchar(100) NOT NULL,
  `homework_content` text NOT NULL,
  `homework_start_date` datetime NOT NULL,
  `homework_end_date` datetime NOT NULL,
  `homework_create_date` datetime NOT NULL,
  `homework_update_date` datetime NOT NULL,
  PRIMARY KEY (`homework_no`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.homework_submit 구조 내보내기
CREATE TABLE IF NOT EXISTS `homework_submit` (
  `homework_submit_no` int(11) NOT NULL,
  `homework_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `homework_submit_title` varchar(100) NOT NULL,
  `homework_submit_content` text NOT NULL,
  `homework_submit_score` int(11) NOT NULL,
  `homework_submit_feedback` varchar(255) NOT NULL,
  `homework_submit_create_date` datetime NOT NULL,
  `homework_submit_update_date` datetime NOT NULL,
  PRIMARY KEY (`homework_submit_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.homework_submit_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `homework_submit_file` (
  `homework_submit_file_no` int(11) NOT NULL,
  `homework_submit_no` int(11) NOT NULL,
  `homework_submit_file_original` varchar(100) NOT NULL,
  `homework_submit_file_size` int(11) NOT NULL,
  `homework_submit_file_type` varchar(100) NOT NULL,
  `homework_submit_file_create_date` datetime NOT NULL,
  `homework_submit_file_update_date` datetime NOT NULL,
  PRIMARY KEY (`homework_submit_file_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture` (
  `lecture_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_no` int(11) NOT NULL,
  `lecture_name` varchar(100) NOT NULL,
  `lecture_start_date` date NOT NULL,
  `lecture_end_date` date NOT NULL,
  `lecture_create_date` datetime NOT NULL,
  `lecture_update_date` datetime NOT NULL,
  `lecture_room_name` varchar(100) NOT NULL,
  PRIMARY KEY (`lecture_no`),
  KEY `FK_lecture_lecture_room` (`lecture_room_name`),
  KEY `FK_lecture_subject` (`subject_no`),
  CONSTRAINT `FK_lecture_lecture_room` FOREIGN KEY (`lecture_room_name`) REFERENCES `lecture_room` (`lecture_room_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_lecture_subject` FOREIGN KEY (`subject_no`) REFERENCES `subject` (`subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_board` (
  `lecture_no` int(11) NOT NULL,
  `board_no` int(11) NOT NULL,
  KEY `FK_lecture_board_lecture` (`lecture_no`),
  KEY `FK_lecture_board_board` (`board_no`),
  CONSTRAINT `FK_lecture_board_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_lecture_board_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_file` (
  `lecture_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_no` int(11) NOT NULL,
  `lecture_file_name` varchar(100) NOT NULL,
  `lecture_file_type` varchar(50) NOT NULL,
  `lecture_file_size` int(11) NOT NULL,
  `lecture_file_create_date` datetime NOT NULL,
  `lecture_file_category` varchar(100) NOT NULL,
  PRIMARY KEY (`lecture_file_no`) USING BTREE,
  KEY `FK_teacher_storage_lecture` (`lecture_no`),
  CONSTRAINT `FK_teacher_storage_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture_room 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_room` (
  `lecture_room_name` varchar(100) NOT NULL,
  `lecture_room_location` varchar(100) NOT NULL,
  `lecture_room_people` int(11) NOT NULL,
  PRIMARY KEY (`lecture_room_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture_schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_schedule` (
  `lecture_schedule_date` date NOT NULL,
  `lecture_no` int(11) NOT NULL,
  `lecture_schedule_start_date` datetime NOT NULL,
  `lecture_schedule_end_date` datetime NOT NULL,
  `lecture_schedule_create_date` datetime NOT NULL,
  PRIMARY KEY (`lecture_schedule_date`,`lecture_no`),
  KEY `FK_lecture_schedule_lecture` (`lecture_no`),
  CONSTRAINT `FK_lecture_schedule_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.level_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `level_history` (
  `member_id` varchar(100) NOT NULL,
  `level_history_new_level` int(11) DEFAULT NULL,
  `level_history_reason` varchar(255) DEFAULT NULL,
  `member_id2` varchar(100) DEFAULT NULL,
  `level_history_update_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FK_level_history_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.manager 구조 내보내기
CREATE TABLE IF NOT EXISTS `manager` (
  `member_id` varchar(100) NOT NULL,
  `manager_entry_date` date NOT NULL,
  `manager_name` varchar(100) NOT NULL,
  `manager_birth` date NOT NULL,
  `manager_gender` enum('M','F') NOT NULL,
  `manager_email` varchar(100) NOT NULL,
  `photo_no` int(11) DEFAULT NULL,
  `address_detail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK_manager_member_photo` (`photo_no`),
  CONSTRAINT `FK_manager_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_manager_member_photo` FOREIGN KEY (`photo_no`) REFERENCES `member_photo` (`photo_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.manager_lecture 구조 내보내기
CREATE TABLE IF NOT EXISTS `manager_lecture` (
  `lecture_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  PRIMARY KEY (`lecture_no`,`member_id`),
  KEY `FK_manager_lecture_manager` (`member_id`),
  CONSTRAINT `FK_manager_lecture_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_manager_lecture_manager` FOREIGN KEY (`member_id`) REFERENCES `manager` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `member_id` varchar(100) NOT NULL,
  `member_pw` varchar(255) DEFAULT NULL,
  `member_pw_update_date` datetime DEFAULT NULL,
  `member_phone_no` varchar(100) DEFAULT NULL,
  `member_level` int(11) DEFAULT NULL,
  `member_last_login_date` datetime DEFAULT NULL,
  `member_create_date` datetime DEFAULT NULL,
  `member_update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.member_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_board` (
  `board_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  KEY `FK_member_board_board` (`board_no`),
  KEY `FK_member_board_member` (`member_id`),
  CONSTRAINT `FK_member_board_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_member_board_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.member_photo 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_photo` (
  `photo_no` int(11) NOT NULL AUTO_INCREMENT,
  `photo_name` varchar(100) DEFAULT NULL,
  `photo_origin_name` varchar(100) DEFAULT NULL,
  `photo_type` varchar(100) DEFAULT NULL,
  `photo_size` int(11) DEFAULT NULL,
  `photo_create_date` datetime DEFAULT NULL,
  `photo_update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`photo_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `board_no` int(11) NOT NULL,
  UNIQUE KEY `board_no` (`board_no`),
  CONSTRAINT `FK_notice_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.pw_history 구조 내보내기
CREATE TABLE IF NOT EXISTS `pw_history` (
  `member_id` varchar(100) NOT NULL,
  `pw_history_new_pw` varchar(255) DEFAULT NULL,
  `pw_history_date` datetime NOT NULL,
  PRIMARY KEY (`member_id`),
  CONSTRAINT `FK_pw_history_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.report_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_board` (
  `report_board_no` int(11) NOT NULL,
  `board_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `report_board_title` varchar(100) NOT NULL,
  `report_board_content` text NOT NULL,
  `report_board_category` varchar(100) NOT NULL,
  `report_board_status` enum('처리대기','처리중','처리완료') NOT NULL,
  `report_board_create_date` datetime NOT NULL,
  `report_board_update_date` datetime NOT NULL,
  PRIMARY KEY (`report_board_no`) USING BTREE,
  KEY `FK_board_report_board` (`board_no`),
  CONSTRAINT `FK_board_report_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.report_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_comment` (
  `report_comment_no` int(11) NOT NULL,
  `comment_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `report_comment_title` varchar(100) NOT NULL,
  `report_comment_content` text NOT NULL,
  `report_comment_category` varchar(100) NOT NULL,
  `report_comment_status` enum('처리대기','처리중','처리완료') NOT NULL,
  `report_comment_create_date` datetime NOT NULL,
  `report_comment_update_date` datetime NOT NULL,
  PRIMARY KEY (`report_comment_no`) USING BTREE,
  KEY `FK_comment_report_comment` (`comment_no`),
  CONSTRAINT `FK_comment_report_comment` FOREIGN KEY (`comment_no`) REFERENCES `comment` (`comment_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.report_save_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_save_board` (
  `report_save_board_no` int(11) NOT NULL,
  `board_no` int(11) NOT NULL,
  `reported_board_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `report_save_board_create_date` datetime NOT NULL,
  `report_save_board_update_date` datetime NOT NULL,
  PRIMARY KEY (`report_save_board_no`),
  KEY `FK_reported_board_report_board` (`board_no`),
  KEY `FK_reported_board_report_board_2` (`reported_board_no`),
  CONSTRAINT `FK_reported_board_report_board` FOREIGN KEY (`board_no`) REFERENCES `report_board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_reported_board_report_board_2` FOREIGN KEY (`reported_board_no`) REFERENCES `report_board` (`report_board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.report_save_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `report_save_comment` (
  `report_save_comment_no` int(11) NOT NULL,
  `comment_no` int(11) NOT NULL,
  `report_comment_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `report_save_comment_create_date` datetime NOT NULL,
  `report_save_comment_update_date` datetime NOT NULL,
  PRIMARY KEY (`report_save_comment_no`),
  KEY `FK_report_save_comment_report_comment` (`comment_no`),
  KEY `FK_report_save_comment_report_comment_2` (`report_comment_no`),
  CONSTRAINT `FK_report_save_comment_report_comment` FOREIGN KEY (`comment_no`) REFERENCES `report_comment` (`comment_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_report_save_comment_report_comment_2` FOREIGN KEY (`report_comment_no`) REFERENCES `report_comment` (`report_comment_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `schedule` (
  `schedule_no` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_title` varchar(100) NOT NULL,
  `schedule_content` text NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `schedule_date` date NOT NULL,
  `schedule_create_date` datetime NOT NULL,
  `schedule_update_date` datetime NOT NULL,
  PRIMARY KEY (`schedule_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.student 구조 내보내기
CREATE TABLE IF NOT EXISTS `student` (
  `member_id` varchar(100) NOT NULL,
  `student_education` varchar(100) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `student_birth` date DEFAULT NULL,
  `student_gender` enum('M','F') DEFAULT NULL,
  `student_email` varchar(100) DEFAULT NULL,
  `photo_no` int(11) DEFAULT NULL,
  `address_detail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK_student_member_photo` (`photo_no`),
  CONSTRAINT `FK_student_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_member_photo` FOREIGN KEY (`photo_no`) REFERENCES `member_photo` (`photo_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.student_lecture 구조 내보내기
CREATE TABLE IF NOT EXISTS `student_lecture` (
  `lecture_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `student_lecture_job` varchar(100) NOT NULL,
  `student_lecture_legistration_date` datetime NOT NULL,
  `student_lecture_end_date` datetime NOT NULL,
  `student_lecture_score` int(11) NOT NULL,
  PRIMARY KEY (`lecture_no`,`member_id`),
  KEY `FK_student_lecture_student` (`member_id`),
  CONSTRAINT `FK_student_lecture_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_lecture_student` FOREIGN KEY (`member_id`) REFERENCES `student` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject` (
  `subject_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(100) NOT NULL,
  `subject_content` text NOT NULL,
  `subject_create_date` datetime NOT NULL,
  `subject_update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.suggest 구조 내보내기
CREATE TABLE IF NOT EXISTS `suggest` (
  `board_no` int(11) NOT NULL,
  `root` int(11) DEFAULT NULL,
  `board_secret` enum('Y','N') NOT NULL,
  `board_status` varchar(100) NOT NULL,
  KEY `FK_suggest_board` (`board_no`),
  CONSTRAINT `FK_suggest_board` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.survey 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey` (
  `survey_no` int(11) NOT NULL AUTO_INCREMENT,
  `survey_title` varchar(50) NOT NULL,
  `survey_content` text NOT NULL,
  `survey_create_date` datetime NOT NULL,
  `survey_update_date` datetime NOT NULL,
  `survey_startline_date` date NOT NULL,
  `survey_deadline_date` date NOT NULL,
  `lecture_no` int(11) DEFAULT NULL,
  `member_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`survey_no`),
  KEY `FK_survey_manager_lecture` (`lecture_no`),
  KEY `FK_survey_manager_lecture_2` (`member_id`),
  CONSTRAINT `FK_survey_manager_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `manager_lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_survey_manager_lecture_2` FOREIGN KEY (`member_id`) REFERENCES `manager_lecture` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.survey_multiple_answer 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey_multiple_answer` (
  `survey_question_no` int(11) NOT NULL,
  `survey_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `survey_multiple_answer_content` int(11) DEFAULT NULL,
  `survey_multiple_answer_create_date` datetime DEFAULT NULL,
  `survey_multiple_answer_update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`survey_question_no`,`survey_no`,`member_id`),
  KEY `FK_survey_multiple_answer_survey_question_2` (`survey_no`),
  KEY `FK_survey_multiple_answer_student` (`member_id`),
  CONSTRAINT `FK_survey_multiple_answer_student` FOREIGN KEY (`member_id`) REFERENCES `student` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_survey_multiple_answer_survey_question` FOREIGN KEY (`survey_question_no`) REFERENCES `survey_question` (`survey_question_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_survey_multiple_answer_survey_question_2` FOREIGN KEY (`survey_no`) REFERENCES `survey_question` (`survey_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.survey_question 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey_question` (
  `survey_question_no` int(11) NOT NULL AUTO_INCREMENT,
  `survey_no` int(11) DEFAULT NULL,
  `survey_question_list_no` int(11) NOT NULL,
  `survey_question_type` enum('주관식','객관식') NOT NULL,
  `survey_question_create_date` datetime NOT NULL,
  `survey_question_update_date` datetime NOT NULL,
  PRIMARY KEY (`survey_question_no`),
  KEY `FK_survey_question_survey` (`survey_no`),
  KEY `FK_survey_question_survey_question_list` (`survey_question_list_no`),
  CONSTRAINT `FK_survey_question_survey` FOREIGN KEY (`survey_no`) REFERENCES `survey` (`survey_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_survey_question_survey_question_list` FOREIGN KEY (`survey_question_list_no`) REFERENCES `survey_question_list` (`survey_question_list_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.survey_question_list 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey_question_list` (
  `survey_question_list_no` int(11) NOT NULL AUTO_INCREMENT,
  `survey_question_list_name` varchar(100) NOT NULL,
  `survey_question_list_content` varchar(255) DEFAULT NULL,
  `survey_question_list_create_date` datetime NOT NULL,
  `survey_question_list_update_date` datetime NOT NULL,
  PRIMARY KEY (`survey_question_list_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.survey_short_answer 구조 내보내기
CREATE TABLE IF NOT EXISTS `survey_short_answer` (
  `survey_no` int(11) NOT NULL,
  `survey_question_no` int(11) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `survey_short_answer_content` text DEFAULT NULL,
  `survey_short_answer_create_date` datetime DEFAULT NULL,
  `survey_short_answer_update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`survey_question_no`,`survey_no`,`member_id`),
  KEY `FK_survey_short_answer_survey_question_2` (`survey_no`),
  KEY `FK_survey_short_answer_student` (`member_id`),
  CONSTRAINT `FK_survey_short_answer_student` FOREIGN KEY (`member_id`) REFERENCES `student` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_survey_short_answer_survey_question` FOREIGN KEY (`survey_question_no`) REFERENCES `survey_question` (`survey_question_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_survey_short_answer_survey_question_2` FOREIGN KEY (`survey_no`) REFERENCES `survey_question` (`survey_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.teacher 구조 내보내기
CREATE TABLE IF NOT EXISTS `teacher` (
  `member_id` varchar(100) NOT NULL,
  `teacher_entry_date` date DEFAULT NULL,
  `teacher_subject` varchar(100) DEFAULT NULL,
  `teacher_name` varchar(100) DEFAULT NULL,
  `teacher_birth` date DEFAULT NULL,
  `teacher_gender` enum('M','F') DEFAULT NULL,
  `teacher_email` varchar(100) DEFAULT NULL,
  `photo_no` int(11) NOT NULL,
  `address_detail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `FK_teacher_member_photo` (`photo_no`),
  CONSTRAINT `FK_teacher_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_teacher_member_photo` FOREIGN KEY (`photo_no`) REFERENCES `member_photo` (`photo_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.teacher_lecutre 구조 내보내기
CREATE TABLE IF NOT EXISTS `teacher_lecutre` (
  `member_id` varchar(100) NOT NULL,
  `lecture_no` int(11) NOT NULL,
  PRIMARY KEY (`member_id`,`lecture_no`),
  KEY `FK_teacher_lecutre_lecture` (`lecture_no`),
  CONSTRAINT `FK_teacher_lecutre_lecture` FOREIGN KEY (`lecture_no`) REFERENCES `lecture` (`lecture_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_teacher_lecutre_teacher` FOREIGN KEY (`member_id`) REFERENCES `teacher` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
