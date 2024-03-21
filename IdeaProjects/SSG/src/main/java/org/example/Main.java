package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Article> articles = new ArrayList();
        int lastIndexId = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String date = formatter.format(now);

        System.out.println("== 게시판 프로그램 시작 ==");
        while (true) {
            System.out.print("입력란>");
            String cmd = sc.nextLine();

            if (cmd.equals("나가기")) {
                break;
            }


            if (cmd.equals("게시물 추가")) {
                while (true) {
                    System.out.println("게시물을 추가 하시겠습니까?");
                    String answer = sc.nextLine();
                    if (answer.equals("네")) {
                        int id = lastIndexId + 1;
                        lastIndexId = id;
                        System.out.print("작성자 : ");
                        String admin = sc.nextLine();
                        System.out.print("제목 : ");
                        String title = sc.nextLine();
                        System.out.print("내용 : ");
                        String body = sc.nextLine();

                        Article article = new Article(id, admin, title, body, date);
                        articles.add(article);

                        System.out.println(id + "번에 저장되었습니다.");
                    } else if (answer.equals("아니요")) {
                        break;
                    }
                }

            }
            else if (cmd.equals("게시판 목록")) {
                if (articles.isEmpty()) {
                    System.out.println("게시판에 내용이 존재하지 않습니다.");
                    continue;
                }

                System.out.println("== 게시판 목록 ==");
                System.out.println("번호 | 작성자 | 제목 | 내용 | 작성일 | 조회수");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);

                    System.out.printf("%d | %s  | %s  | %s | %s | %d\n", article.id, article.admin, article.title, article.body, article.date, article.hit);
                }


            }
            else if(cmd.startsWith("게시물 찾기")){
                String[] cmdIndex = cmd.split(" ");
                if(cmdIndex.length != 3){
                    System.out.println("게시물 찾기는 \"게시물 찾기 (번호)\"의 형태로 입력해주세요");

                }
                else {
                    int id = Integer.parseInt(cmdIndex[2]);



                    Article foundArticle = null;

                    for (int i = 0; i < articles.size(); i++) {
                        Article article = articles.get(i);


                        if (id == article.id) {
                            foundArticle = article;
                            break;
                        }

                    }

                    if (foundArticle == null) {
                        System.out.println("게시물이 존재하지 않습니다.");
                        continue;
                    }

                    foundArticle.increaseHit();

                    System.out.printf("번호 : %d\n", foundArticle.id);
                    System.out.printf("작성자 : %s\n", foundArticle.admin);
                    System.out.printf("작성일 : %s\n", foundArticle.date);
                    System.out.printf("제목 : %s\n", foundArticle.title);
                    System.out.printf("내용 : %s\n", foundArticle.body);
                    System.out.printf("조회수 : %s\n", foundArticle.hit);
                }
            }
            else if(cmd.startsWith("게시물 수정")){
                String[] cmdIndex = cmd.split(" ");
                if(cmdIndex.length != 3){
                    System.out.println("게시물 수정은 \"게시물 수정 (번호)\"의 형태로 입력해주세요");

                }
                else {
                    int id = Integer.parseInt(cmdIndex[2]);


                    Article foundArticle = null;

                    for (int i = 0; i < articles.size(); i++) {
                        Article article = articles.get(i);


                        if (id == article.id) {
                            foundArticle = article;
                            break;
                        }

                    }

                    if (foundArticle == null) {
                        System.out.println(id+"게시물이 존재하지 않습니다.");
                        continue;
                    }

                    System.out.print("제목 : ");
                    String title = sc.nextLine();
                    System.out.print("내용 : ");
                    String body = sc.nextLine();

                    foundArticle.title = title;
                    foundArticle.body = body;

                    System.out.println(id+"게시물이 수정되었습니다.");
                }
            }
            else if (cmd.startsWith("게시물 삭제")) {
                String[] cmdIndex = cmd.split(" ");
                if(cmdIndex.length != 3){
                    System.out.println("게시물 삭제는 \"게시물 삭제 (번호)\"의 형태로 입력해주세요");

                }
                else {
                    int id = Integer.parseInt(cmdIndex[2]);


                    int foundIndex = -1;

                    for (int i = 0; i < articles.size(); i++) {
                        Article article = articles.get(i);

                        if (id == article.id) {
                            foundIndex = i;
                            break;
                        }

                    }

                    if (foundIndex == -1) {
                        System.out.println("게시물이 존재하지 않습니다.");
                        continue;
                    }

                    articles.remove(foundIndex);
                    System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);
                }
            }
            else{
                System.out.printf("%s 명령어는 존재하지 않습니다.\n", cmd);
                System.out.println("== 명령어 목록 ==");
                System.out.println("1. 게시판 목록 ");
                System.out.println("2. 게시물 추가 ");
                System.out.println("3. 게시물 찾기 (번호)");
                System.out.println("4. 게시물 삭제 (번호)");
            }


        }

        sc.close();
        System.out.println("== 게시판 종료 ==");

    }
}

class Article {
    int id;
    String admin;
    String title;
    String body;

    String date;
    int hit;

    Article(int id, String admin, String title, String body, String date) {
        this.id = id;
        this.admin = admin;
        this.title = title;
        this.body = body;
        this.date = date;
        this.hit = 0;
    }

    public void increaseHit() {
        hit++;
    }
}