package com.example.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long MemberId;
    private String Name;
    private String NickName;
    private String User_id;
    private String Password;
    private String Email;
    private String Phone;
    private String Member_status;
    private String Created_time;
    private String Updated_time;
}
