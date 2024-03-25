package org.example.dao;

import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    public List<Article> articls;

    public ArticleDao(){
        articls = new ArrayList<>();
    }
}
