package com.capstone.uniculture.controller;

import com.capstone.uniculture.config.SecurityUtil;
import com.capstone.uniculture.dto.MemberRequestDto;
import com.capstone.uniculture.dto.TokenDto;
import com.capstone.uniculture.dto.UpdateMemberDto;
import com.capstone.uniculture.jwt.TokenProvider;
import com.capstone.uniculture.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;


    @GetMapping("/auth/sec/home")
    public ResponseEntity signOk(){
        return ResponseEntity.ok().build();
    }
    // 회원가입
    @PostMapping("/sec/signup")
    public ResponseEntity signup(@RequestBody MemberRequestDto requestDto){
        System.out.println("requestDto = " + requestDto);
        return ResponseEntity.ok(memberService.signup(requestDto));
    }

    // 닉네임 중복 확인
    @GetMapping("/sec/check")
    public ResponseEntity<?> checkNicknameValid(@RequestParam("nickname") String nickname) throws BadRequestException {
        System.out.println("nickname = " + nickname);
        if(memberService.checkNicknameUnique(nickname) == true){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok("사용 가능한 아이디 입니다.");
    }

    // 로그인
    @PostMapping("/sec/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto){
        System.out.println("requestDto = " + requestDto);
        return ResponseEntity.ok(memberService.login(requestDto));
    }

    // 로그아웃
    @GetMapping("/auth/sec/logout")
    public ResponseEntity logout(){
        return ResponseEntity.ok(memberService.logout());
    }


}

