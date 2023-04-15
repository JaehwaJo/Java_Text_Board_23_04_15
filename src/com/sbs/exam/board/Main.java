package com.sbs.exam.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");
    int articleLastId = 1;

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        Article article = new Article();

        System.out.println("게시물 등록");
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int id = articleLastId;
        articleLastId++;

        article.id = id;
        article.title = title;
        article.body = body;

        System.out.println(id + "번 게시물이 등록되었습니다.");
      }
      else if(cmd.equals("exit")) {
        System.out.println("== 프로그램 종료 ==");
        break;
      }
    }
    // System.out.println("입력 받은 명령어 : " + cmd);

    sc.close();
  }
}

class Article {
  int id;
  String title;
  String body;
}