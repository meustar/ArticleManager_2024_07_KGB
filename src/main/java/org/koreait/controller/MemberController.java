package org.koreait.controller;

import org.koreait.articleManager.Member;
import org.koreait.articleManager.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {
    Scanner sc;
    List<Member> members;

    private int lastMemberId = 0;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doJoin() {
        System.out.println("== 회원가입 == ");
        int id = lastMemberId + 1;
        String regDate = Util.getNow();
        String loginId = null;

        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용중인 아이디 입니다.");
                continue;
            }
            break;
        }
        String loginPw = null;
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.print("비밀번호 확인 : ");
            String loginPwConfirm = sc.nextLine();

            if (loginPw.equals(loginPwConfirm) == false) {
                System.out.println("비밀번호를 다시 확인해주세요. : ");
                continue;
            }
            break;
        }

        System.out.print("이름 : ");
        String name = sc.nextLine();

        Member member = new Member(id, regDate, loginId, loginPw, name);
        members.add(member);

        System.out.println(name + "님 회원가입 되셨습니다.");
        lastMemberId ++;
    }
    private boolean isJoinableLoginId(String loginId) {
        for ( Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

}
