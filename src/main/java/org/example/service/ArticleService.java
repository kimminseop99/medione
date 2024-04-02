package org.example.service;


import org.example.container.Container;
import org.example.dao.ArticleDao;
import org.example.dto.Article;
import org.example.dto.Board;

import java.util.List;

public class ArticleService {
    private ArticleDao articleDao;

    public ArticleService(){
        articleDao = Container.articleDao;
    }

    public List<Article> getForPrintArticles(String searchKeyword) {
       return articleDao.getForPrintArticles(searchKeyword);

    }

    public List<Article> getForPrintArticles() {
        return articleDao.getForPrintArticles(null);

    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }

    public int add(int memberId, int boardId, String title,String body) {
        Article article = new Article(memberId, boardId, title,body);
        return articleDao.add(article);

    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public Board getBoard(int id){
        return articleDao.getBoard(id);
    }
}
