package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;
        List<Article> articles = new ArrayList();

        System.out.println("== 프로그램 시작 ==");


        while(true){
            System.out.print("명령어) ");
            String cmd = sc.nextLine();
            cmd = cmd.trim();


            if(cmd.isEmpty()){
                continue;
            }

            if(cmd.equals("exit")){
                break;
            }

            if(cmd.equals("article write")){

                int id = lastArticleId + 1;
                lastArticleId = id;
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);
                articles.add(article);

                System.out.printf("%d번 글이 생성되었습니다.", id);
                System.out.println();

            }
            else if(cmd.equals("article list")){
                if(articles.size() == 0){
                    System.out.println("계시물이 없습니다.");
                    continue;
                }

                System.out.println("번호 | 제목");
                for(int i = articles.size() -1;  i <= 0 ; i++){
                    Article article = articles.get(i);

                    System.out.printf("%d | %s\n",article.id, article.title);
                }
            }

            else{
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", cmd);
            }
        }
        sc.close();
        System.out.println("== 프로그램 끝 ==");

    }
}

class Article{
    int id;
    String title;
    String body;

    public Article(int id, String title, String body) {
        this.id = id;
        this.body = body;
        this.title = title;
    }

}