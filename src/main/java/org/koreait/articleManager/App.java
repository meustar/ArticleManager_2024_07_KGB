package org.koreait.articleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;

    }

    public void run() {
        int lastArticleId = 0;
        String cmd;

        List<Article> articles = new ArrayList<>();

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
                System.out.println("== 게시글 작성 ==");
                int id = lastArticleId + 1;
                String regDate = Util.getNow();
                String updateDate = regDate;
                System.out.print("제목: ");
                String title = sc.nextLine();
                System.out.print("내용: ");
                String body = sc.nextLine();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id +"번 글이 작성되었습니다.");
                lastArticleId++;

            } else if (cmd.equals("article list")) {
                System.out.println("== 게시글 목록 ==");
                if(articles.size() == 0) {
                    System.out.println("게시글이 존재하지 않습니다.");
                } else {
                    System.out.println("번호//    제목  //      내용      //");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        System.out.printf(" %d //    %s  //      %s      //\n", article.getId(),article.getTitle(),article.getBody());
                    }
                }


            } else if (cmd.equals("article detail")) {
                System.out.println("== 게시글 상세보기 ==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for(Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());

            } else if (cmd.equals("article modify")) {
                System.out.println("== 게시글 수정 ==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for(Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }
                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);

                System.out.println(id + "해당 글이 수정되었습니다.");
            } else if (cmd.equals("article delete")) {
                System.out.println("== 게시글 삭제 ==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for(Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }
                articles.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다.");
            }
        }
        sc.close();
    }
}
