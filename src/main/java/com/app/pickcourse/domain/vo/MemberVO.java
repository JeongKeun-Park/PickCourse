package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberNickname;
    private String memberTell;
    private String memberBirth;
    private String memberGender;
    private int memberPoint;
    private char memberIsAct;
    private String memberFilePath;
    private String memberFileName;
    private String memberFileSize;
    private String createdDate;
    private String updatedDate;

    private String loginType; // "EMAIL" or "KAKAO"
}
