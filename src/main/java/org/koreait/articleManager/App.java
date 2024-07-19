package org.koreait.articleManager;

import java.util.Scanner;

public class App {

    Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;

    }

    public void run() {
        int id = 1;
        String cmd;


        while (true) {

            System.out.print("명령어 : ");
            cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요.");
                continue;
            }

            if(cmd.equals("article write")){
                System.out.print("제목: ");
                cmd = sc.nextLine();
                System.out.print("내용: ");
                cmd = sc.nextLine();
                System.out.printf("%d번 글이 작성되었습니다.",id);
                id++;

            } else if (cmd.equals("article list")) {
                System.out.println("번호//    제목  //      내용      //");

            } else if (cmd.equals("article detail")) {
                System.out.println();
            } else if (cmd.equals("article modify")) {
                System.out.println();
            } else if (cmd.equals("article delete")) {
                System.out.println();
            }
        }
    }
}
