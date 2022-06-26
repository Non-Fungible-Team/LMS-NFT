package kr.co.nft.lms.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCategory;
	private String memberId;
	private String noticeBlind;
	private String noticeCreateDate;
	private String noticeUpdateDate;
	//setter 자동으로 안되게 설정 후  setter 생성
	@Setter(value = AccessLevel.NONE)
	private int noticePrivilege;
	
	//noticePrivilege set시 set 되는 필드
	//setter 자동으로 안되게 설정 후  setter 생성
	@Setter(value = AccessLevel.NONE)
	private String PrivilegeName;
	
	public void setNoticePrivilege(int noticePrivilege) {
		this.noticePrivilege = noticePrivilege;
		if(noticePrivilege == 4) {
			this.PrivilegeName = "학생만";
		}else if (noticePrivilege == 5) {
			this.PrivilegeName = "강사이상";
		}else if (noticePrivilege == 6) {
			this.PrivilegeName = "운영자만";
		}
	}
	
	
}
