package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class MemberPatchDto {
    private long memberId;
    private String Name;
    private String NickName;
    private String User_id;
    private String Password;
    @Email
    private String Email;
    private String Phone;
    private String Member_status;
    private String Created_time;
    private String Updated_time;

}
