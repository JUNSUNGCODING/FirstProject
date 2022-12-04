package com.example.board.service;

import com.example.board.entity.Member;
import com.example.board.exception.BusinessLogicException;
import com.example.board.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public Member createMember(Member member){

        Member createMember = member;

        return createMember;
    }

    public Member updateMember(Member member){

        Member updateMember = member;

        return updateMember;
    }

    public Member findMember(long memberId){

        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);

    }

    public List<Member> findMembers(){

        return null;
    }

    public void deleteMember(long memberId){

    }

}
