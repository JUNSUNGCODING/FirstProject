package com.example.board.controller;

import com.example.board.dto.MemberPatchDto;
import com.example.board.dto.MemberPostDto;
import com.example.board.dto.MemberResponseDto;
import com.example.board.entity.Member;
import com.example.board.mapper.MemberMapper;
import com.example.board.response.ErrorResponse;
import com.example.board.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/B1/members")
@Slf4j
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }


    //회원가입
    @PostMapping
    public ResponseEntity<MemberResponseDto> postMember(@Valid @RequestBody MemberPostDto memberPostDto){

        Member member = mapper.memberPostDtoToMember(memberPostDto);

        Member response = memberService.createMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);


    }

    //회원수정
    @PatchMapping("/{member-id}")
    public ResponseEntity<MemberResponseDto> patchMember(@PathVariable("member-id") @Positive long memberId, @RequestBody MemberPatchDto memberPatchDto){

        Member member = mapper.memberPatchDtoToMember(memberPatchDto);

        Member response = memberService.updateMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }

    //회원한명 검색
    @GetMapping("/{member-id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("member-id") @Positive long memberId){

        Member response = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    //회원전체 검색
    @GetMapping("/all")
    public ResponseEntity<List> getMembers(){

        List<Member> members = memberService.findMembers();
        List<MemberResponseDto> response = members.stream()
                .map(member -> mapper.memberToMemberResponseDto(member))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //회원탈퇴
    @DeleteMapping("/del/{member-id}")
    public ResponseEntity delMember(@PathVariable("member-id") @Positive long memberId){

        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
