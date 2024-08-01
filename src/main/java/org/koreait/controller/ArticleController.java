package org.koreait.controller;

import org.koreait.articleManager.Article;
import org.koreait.articleManager.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController {
    Scanner sc;
    List<Article> articles;

    private int lastArticleId = 0;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = new ArrayList<>();
    }


    public void doWrite() {

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
    }

    public void showList(String cmd) {
        System.out.println("== 게시글 목록 ==");
        if(articles.size() == 0) {
            System.out.println("게시글이 존재하지 않습니다.");
        }
        String searchKeyword = cmd.substring("article list ".length()).trim();

        List<Article> forPrintArticles = articles;

        for ( Article article : articles) {
            if(article.getTitle().contains(searchKeyword)) {
                forPrintArticles.add(article);
            }
            if (forPrintArticles.size() == 0) {
                System.out.println("번호//    제목  //      내용      //");
                System.out.println("검색 결과가 없습니다.");
                return;
            }
        }
        System.out.println("번호//    제목  //      내용      //");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article article = articles.get(i);

            if ( Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                System.out.printf(" %d //    %s  //      %s      //\n", article.getId(),article.getTitle(),article.getBody());
            } else {
                System.out.printf(" %d //    %s  //      %s      //\n", article.getId(),article.getTitle(),article.getBody());
            }
        }

    }

    public void showDetail(String cmd) {
        System.out.println("== 게시글 상세보기 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticlebyId(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글이 없습니다.");
            return;
        }
        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성날짜 : " + foundArticle.getRegDate());
        System.out.println("수정날짜 : "+ foundArticle.getUpdateDate());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());
    }

    public void doModify(String cmd) {
        System.out.println("== 게시글 수정 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticlebyId(id);


        if (foundArticle == null) {
            System.out.println("해당 게시글이 없습니다.");
            return;
        }
        System.out.println("기존 제목 : " + foundArticle.getTitle());
        System.out.println("기존 내용 : " + foundArticle.getBody());
        System.out.print("새 제목 : ");
        String newTitle = sc.nextLine();
        System.out.print("새 내용 : ");
        String newBody = sc.nextLine();

        foundArticle.setTitle(newTitle);
        foundArticle.setBody(newBody);
        foundArticle.setUpdateDate(Util.getNow());

        System.out.println(foundArticle.getId() + "해당 글이 수정되었습니다.");
    }

    public void doDelete(String cmd) {
        System.out.println("== 게시글 삭제 ==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticlebyId(id);


        if (foundArticle == null) {
            System.out.println("해당 게시글이 없습니다.");
            return;
        }
        articles.remove(foundArticle);
        System.out.println(foundArticle.getId() + "번 게시글이 삭제되었습니다.");
    }
    private Article getArticlebyId(int id) {

        for (Article article : articles ) {
            if(article.getId() == id) {
                return article;
            }
        }
        return null;
    }
}
