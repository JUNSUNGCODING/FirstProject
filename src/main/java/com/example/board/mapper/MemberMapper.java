package com.example.board.mapper;

import com.example.board.dto.MemberPatchDto;
import com.example.board.dto.MemberPostDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.entity.Member;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
