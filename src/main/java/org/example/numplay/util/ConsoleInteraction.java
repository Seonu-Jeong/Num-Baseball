package org.example.numplay.util;

import java.util.Scanner;

public class ConsoleInteraction {

    public Menu selectMenu() {
        System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
        System.out.println("0.자리수 설정 1.게임 시작하기 2.게임 기록보기 3.종료하기");

        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();

        Menu menu = null;
        try {
            menu = switch (choice) {
                case 0 -> Menu.SET_LEVEL;
                case 1 -> Menu.START;
                case 2 -> Menu.SHOW_HISTORY;
                case 3 -> Menu.END;
                default -> throw new Exception("잘못된 숫자입력");
            };
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            menu = Menu.ERROR;
        }

        return menu;
    }
}
