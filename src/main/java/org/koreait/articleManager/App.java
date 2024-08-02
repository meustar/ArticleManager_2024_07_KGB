package org.koreait.articleManager;

import org.koreait.controller.ArticleController;
import org.koreait.controller.MemberController;
import org.koreait.system.SystemController;

import java.util.Scanner;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);
        System.out.println("== 프로그램 시작 ==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        while (true) {

            System.out.print("명령어 : ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                SystemController.exit();            // SystemController 를 static으로 만들어서 new 하지 않고 바로 쓸수 있다.
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요.");
                continue;
            }
            if(cmd.equals("article write")){
                articleController.doWrite();
            } else if (cmd.equals("article list")) {
                articleController.showList(cmd);
            } else if (cmd.equals("article detail")) {
                articleController.showDetail(cmd);
            } else if (cmd.equals("article modify")) {
                articleController.doModify(cmd);
            } else if (cmd.equals("article delete")) {
                articleController.doDelete(cmd);
            } else if (cmd.equals("member join")) {
                memberController.doJoin();
            } else {
                System.out.println("사용할 수 없는 명령어 입니다.");
            }
        }
        System.out.println("== 프로그램 종료 ==");

        sc.close();
    }
}
