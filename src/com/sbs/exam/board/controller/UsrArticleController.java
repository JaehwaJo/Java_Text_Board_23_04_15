package com.sbs.exam.board.controller;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Article;
import com.sbs.exam.board.dto.Rq;
import com.sbs.exam.board.service.ArticleService;

import java.util.List;

public class UsrArticleController {
  private ArticleService articleService;

  public UsrArticleController() {
    articleService = Container.getArticleService();
    articleService.makeTestData();
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String orderBy = rq.getParam("orderBy", "idDesc");

    System.out.println("== 게시물 리스트 ==");
    System.out.println("--------------------");
    System.out.println("번호 / 제목");
    System.out.println("--------------------");

    List<Article> articles = articleService.getArticles(searchKeyword, orderBy);

    for (Article article : articles) {
      System.out.printf("%d / %s\n", article.getId(), article.getTitle());
    }
  }

  public void actionWrite(Rq rq) {
    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.getSc().nextLine();
    System.out.printf("내용 : ");
    String body = Container.getSc().nextLine();

    int id = articleService.write(title, body);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getBody());
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("새 내용 : ");
    article.setTitle(Container.getSc().nextLine());
    System.out.printf("새 제목 : ");
    article.setBody(Container.getSc().nextLine());

    System.out.printf("%d번 게시물을 수정하였습니다.\n", article.getId());
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articleService.deleteArticleById(article.getId());

    System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
  }
}