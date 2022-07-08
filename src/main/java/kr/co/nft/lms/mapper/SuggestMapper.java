package kr.co.nft.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.nft.lms.vo.Suggest;

@Mapper
public interface SuggestMapper {
	//Suggest 입력 내용 중 board테이블에 해당하는 내용을 board테이블에 insert
	int insertSuggestInBoard(Suggest suggest);
	
	//Suggest 입력 내용중 suggest테이블에 해당하는 내용을 suggest테이블에 insert
	int insertSuggest(Suggest suggest);
	
	//전체select
	List<Suggest> selectSuggestListByPage(Map<String, Object> map);
	//전체 row
	int selectSuggestListTotalCount(int memberLevel);

	//답변전 상태 select
	List<Suggest> selectRootNullSuggestListByPage(Map<String, Object> map);
	//답변전 상태 row
	int selectRootNullSuggestListTotalCount(int memberLevel);
	
	//상세보기
	Suggest selectSuggestOne(int suggestNo);
	
	//update
	int updateSuggestInBoard(Suggest suggest);
	int updateSuggest(Suggest suggest);
	
	//updateSuggestStatus
	int updateSuggestStatus(Suggest suggest);
	
	//delete (블라인드 처리)
	int deleteSuggest(int suggestNo);
	List<Map<String,Object>> selectSuggestByRoot(int suggestNo);
	
}
