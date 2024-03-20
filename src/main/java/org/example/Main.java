package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lastArticleId = 0;
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
                System.out.print("제목 : ");
                String title = sc.nextLine();
                int id = lastArticleId + 1;
                lastArticleId = id;
                System.out.print("내용 : ");
                String body = sc.nextLine();
                System.out.printf("%d번 글이 생성되었습니다.", id);

            }
            else if(cmd.equals("article list")){
                System.out.println("계시물이 없습니다.");
            }
            else{
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", cmd);
            }
        }

        System.out.println("== 프로그램 끝 ==");

    }
}