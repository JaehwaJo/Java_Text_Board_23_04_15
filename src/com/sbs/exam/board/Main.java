package com.sbs.exam.board;

import javax.swing.plaf.IconUIResource;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    List<Article> articles = new ArrayList<>();
    TestArticle(articles);

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("게시물 등록");
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int id = articles.size() + 1;

        articles.add(new Article(id, title, body));
        Article article = articles.get(id - 1);

        System.out.println("생성 된 게시물 객체 : " + article);
        System.out.println(id + "번 게시물이 등록되었습니다.");
      }
      else if (cmd.equals("/usr/article/detail")) {
        int id = articles.size() - 1;
        Article article = articles.get(id);

        System.out.println("== 게시물 상세 보기 ==");
        System.out.println("번호 : " + article.id);
        System.out.println("제목 : " + article.title);
        System.out.println("내용 : " + article.body);
      }
      else if (cmd.equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");
        System.out.println("--------------");
        System.out.println("번호  /  제목");
        System.out.println("--------------");
        for (int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d  /  %s\n", article.id, article.title);
        }
      }
      else if(cmd.equals("exit")) {
        System.out.println("== 프로그램 종료 ==");
        break;
      }
      else {
        System.out.println(cmd + "는 올바른 명령어가 아닙니다.");
      }
    }
    // System.out.println("입력 받은 명령어 : " + cmd);

    sc.close();
  }

  static void TestArticle(List<Article> articles) {
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "제목2", "내용2"));
    articles.add(new Article(3, "제목3", "내용3"));
  }
}

class Article {
  int id;
  String title;
  String body;
  public Article () {}

  public Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override
  public String toString() {
    return "{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}