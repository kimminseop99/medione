package org.example.dao;

import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends Dao{
    public List<Article> articls;

    public ArticleDao(){
        articls = new ArrayList<>();
    }

    public void add(Article article) {
        articls.add(article);
        lastId++;
    }
}
