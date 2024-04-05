package org.example;

import org.example.container.Container;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.ExportController;
import org.example.controller.MemberController;
import org.example.db.DBConnection;



public class App {
    public static final String RESET = "\u001B[0m";
    public static final String FONT_BLACK = "\u001B[30m";
    public static final String FONT_RED = "\u001B[31m";
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_YELLOW = "\u001B[33m";
    public static final String FONT_BLUE = "\u001B[34m";
    public static final String FONT_PURPLE = "\u001B[35m";
    public static final String FONT_CYAN = "\u001B[36m";
    public static final String FONT_WHITE = "\u001B[37m";
    public static final String BACKGROUND_BLACK = "\u001B[40m";
    public static final String BACKGROUND_RED = "\u001B[41m";
    public static final String BACKGROUND_GREEN = "\u001B[42m";
    public static final String BACKGROUND_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_BLUE = "\u001B[44m";
    public static final String BACKGROUND_PURPLE = "\u001B[45m";
    public static final String BACKGROUND_CYAN = "\u001B[46m";
    public static final String BACKGROUND_WHITE = "\u001B[47m";
    public App() {
        DBConnection.DB_NAME = "sbs_proj";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();

        // 현재 게시판을 1번 게시판으로 선택
        Container.getSession().setCurrentBoard(Container.articleService.getBoard(2));
    }
    public void start() {

    System.out.println(FONT_CYAN + "== 프로그램 시작 ==" + RESET);
        System.out.println( "= 명령어 모음 =" );
        System.out.println(FONT_BLUE +"1. 회원가입 : member join" + RESET );
        System.out.println(FONT_BLUE +"2. 로그인/로그아웃 : member login/logout" + RESET );
        System.out.println(FONT_BLUE +"3. 현재 게시판 확인 : article currentBoard" + RESET );
        System.out.println(FONT_BLUE +"4. 게시판 변경 : article changeBoard" + RESET );
        System.out.println(FONT_BLUE +"5. 게시물 리스트 : article list" + RESET );
        System.out.println(FONT_BLUE +"6. 게시물 상세 : article detail" + RESET );
        System.out.println(FONT_BLUE +"7. 게시물 작성(로그인 후 이용가능) : article write" + RESET );
        System.out.println(FONT_BLUE +"8. 게시물 수정/삭제(로그인 후 이용가능) : article modify/delete" + RESET );

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ExportController exportController = new ExportController();

        while ( true ) {
            System.out.print(FONT_GREEN + "명령어) "+ RESET);
            String cmd = Container.getScanner().nextLine();
            cmd = cmd.trim();

            if (cmd.isEmpty()) {
                continue;
            }

            if ( cmd.equals("exit") ) {
                break;
            }

            String[] cmdBits = cmd.split(" "); // article write / member join

            if ( cmdBits.length == 1 ) {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String controllerName = cmdBits[0]; // article / member
            String actionMethodName = cmdBits[1]; // write / join

            Controller controller = null;

            if ( controllerName.equals("article") ) {
                controller = articleController;
            }
            else if ( controllerName.equals("member") ) {
                controller = memberController;
            }
            else if ( controllerName.equals("export") ) {
                controller = exportController;
            }
            else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }

            String actionName = controllerName + "/" + actionMethodName;

            switch ( actionName ) {
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "member/logout":
                    if (!Container.getSession().isLogined()) {
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            switch ( actionName ) {
                case "member/login":
                case "member/join":
                    if (Container.getSession().isLogined() ) {
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);
        }

        Container.getDBConnection().close();
        Container.getScanner().close();

        System.out.println("== 프로그램 끝 ==");
    }
}