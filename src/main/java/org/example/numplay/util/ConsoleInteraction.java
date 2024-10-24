package org.example.numplay.util;

import java.util.List;
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            menu = Menu.ERROR;
        }

        return menu;
    }

    public int getNum(int level) {

        int result;

        while (true) {
            System.out.println("숫자를 입력하세요");

            Scanner sc = new Scanner(System.in);

            try {
                String num_string = sc.nextLine();

                int num = Integer.parseInt(num_string);

                int[] digit = new int[10];

                for (int i = 0; i < num_string.length(); i++) {
                    digit[num_string.charAt(i) - '0']++;
                }

                if (digit[0] >= 1)
                    throw new Exception("0을 포함할 수 없습니다");

                int cnt = 0;

                for (int val : digit) {
                    if (val > 1)
                        throw new Exception("중복된 숫자를 사용할 수 없습니다");

                    cnt += val;
                }

                if (cnt != level)
                    throw new Exception(level + "자리 숫자를 입력해주세요");

                result = num;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요");
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            break;
        }

        return result;
    }

    public int changeLevel() {

        Scanner sc = new Scanner(System.in);

        int level;

        while (true) {

            System.out.println("설정하고자 하는 자리수를 입력하세요.");

            level = sc.nextInt();

            try {
                if (level < 3 || level > 5)
                    throw new Exception("난이도는 3,4,5 만 가능합니다 ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }

        return level;
    }

    public void showHistory(List<Integer> gameHistory) {
        int gameNum = 1;

        for (Integer result : gameHistory) {
            System.out.printf("%d번째 게임 : 시도횟수 - %d %n", gameNum++, result);
        }
    }

    public void showResult(int[] result,int level){
        int strike = result[0];

        if(strike==level){
            System.out.println("정답입니다!");
            return;
        }

        String[] mention = {"스트라이크","볼","아웃"};

        for(int i=0;i<result.length;i++){
            if(result[i]==0) continue;

            System.out.print(result[i]+mention[i]+" ");
        }

        System.out.println();
    }

    public void end(){
        System.out.println("< 숫자 야구 게임을 종료합니다 >");
    }
}
