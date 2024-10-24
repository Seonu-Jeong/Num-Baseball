package org.example.numplay.util;

import java.util.List;
import java.util.Scanner;

/**
 * 사용자의 입력과 게임 출력을 콘솔로 당담하는 클래스  
 */
public class ConsoleInteraction {

    /**
     * 최초 사용자 메뉴 출력 및 사용자 입력을 받는 함수
     * @return Menu 클래스의 상수 값 반환 
     */
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

    /**
     * 사용자에게 게임 입력 받는 함수
     * @param level
     * @return 사용자의 입력값 반환
     */
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
                    throw new Exception("0을 포함할 수 없습니다\n");

                int cnt = 0;

                for (int val : digit) {
                    if (val > 1)
                        throw new Exception("중복된 숫자를 사용할 수 없습니다\n");

                    cnt += val;
                }

                if (cnt != level)
                    throw new Exception(level + "자리 숫자를 입력해주세요\n");

                result = num;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해주세요\n");
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            break;
        }

        return result;
    }

    /**
     * 사용자에게 변경할 레벨 값 입력 받는 함수
     * @return 변경된 레벨 값 반환
     */
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

    /**
     * 사용자의 게임 히스토리 출력 함수
     * @param gameHistory
     */
    public void showHistory(List<Integer> gameHistory) {
        int gameNum = 1;

        for (Integer result : gameHistory) {
            System.out.printf("%d번째 게임 : 시도횟수 - %d %n", gameNum++, result);
        }
    }

    /**
     * 사용자의 입력에 대한 게임 결과 출력 함수
     * @param result
     * @param level
     */
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

        System.out.println("\n");
    }

    /**
     * 게임 종료 출력 함수
     */
    public void end(){
        System.out.println("< 숫자 야구 게임을 종료합니다 >");
    }
}
