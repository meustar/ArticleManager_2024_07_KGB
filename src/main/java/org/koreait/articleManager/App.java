package org.koreait.articleManager;

import org.koreait.controller.ArticleController;
import org.koreait.controller.Controller;
import org.koreait.controller.MemberController;
import org.koreait.system.SystemController;

import java.util.Scanner;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);
        System.out.println("== 프로그램 시작 ==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        Controller controller = null;

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
            String[] cmdBit = cmd.split(" ");

            String controllerName = cmdBit[0];

            if (cmdBit.length == 1) {
                System.out.println("명령어를 확인해주세요.");
                continue;
            }
            String actionMethodName = cmdBit[1];

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("사용불가 명령어 입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);
        }
        System.out.println("== 프로그램 종료 ==");

        sc.close();
    }
}
