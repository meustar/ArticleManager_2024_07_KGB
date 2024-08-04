package org.koreait.controller;

import org.koreait.dto.Member;
import org.koreait.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    private Scanner sc;
    private List<Member> members;
    private String cmd;

    private int lastMemberId = 0;

    private Member loginedMember = null;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join" :
                doJoin();
                break;
            case "login" :
                doLogin();
                break;
            case "logout" :
                doLogout();
                break;
            default:
                System.out.println("명령어 확인 (actionMethodName) 오류");
                break;
        }
    }

    private void doLogout() {
    }

    private void doLogin() {
        if(isLogined()) {
            System.out.println("이미 로그인 중입니다.");
            return;
        }
        System.out.println("== 로그인 ==");
        System.out.print("로그인 아이디 : ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호 : ");
        String loginPw = sc.nextLine();

        Member member = getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
            return;
        }

        if (member.getLoginPw().equals(loginPw)== false) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }

        loginedMember = member;

        System.out.printf("%s님 로그인 성공\n", member.getName());
    }

    private Member getMemberByLoginId(String loginId) {
        for (Member member : members) {
            if(member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    private boolean isLogined() {
        return loginedMember != null;
    }

    private void doJoin() {
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
