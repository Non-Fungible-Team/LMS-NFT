package kr.co.nft.lms.dto;

import java.util.List;

import kr.co.nft.lms.vo.ExamExample;
import lombok.Data;

@Data
public class AddExam {
	private List<ExamExample> exampleList;
}
