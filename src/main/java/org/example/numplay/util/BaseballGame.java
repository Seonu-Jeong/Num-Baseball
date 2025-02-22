package org.example.numplay.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 숫자 야국의 주요 로직을 실행하는 클래스
 */
public class BaseballGame {

    ConsoleInteraction consoleInteraction = new ConsoleInteraction();
    NumGenerator numGenerator = new NumGenerator();

    int level=3;
    List<Integer> gameHistory = new ArrayList<Integer>();

    /**
     * 게임 실행의 메인 로직
     */
    public void play() {

        outer:
        while (true) {

            switch (consoleInteraction.selectMenu()) {
                case START -> start();
                case SET_LEVEL -> level = consoleInteraction.changeLevel();
                case SHOW_HISTORY -> consoleInteraction.showHistory(gameHistory);
                case END -> { consoleInteraction.end(); break outer; }
            }

            System.out.println();
        }

    }

    /**
     * 인자와 정답을 비교하는 함수
     * @param num
     * @return 스트라이크, 볼, 아웃을 배열로 반환
     */
    public int[] compare(int num) {

        List<Integer> answer = numGenerator.getAnswer();

        int strike = 0, ball = 0, out = 0;

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < answer.size(); i++) {
            hashMap.put(answer.get(i), i);
        }

        char[] ch_num = String.valueOf(num).toCharArray();

        for (int i = 0; i < ch_num.length; i++) {

            if (hashMap.containsKey(ch_num[i]-'0')) {
                if (hashMap.get(ch_num[i]-'0') == i)
                    strike++;
                else
                    ball++;
            } else
                out++;

        }

        return new int[]{strike, ball, out};
    }

    /**
     * 게임 실행 함수
     */
    private void start(){

        numGenerator.makeAnswer(level);
        int cnt=0;

        while(true) {
            int num = consoleInteraction.getNum(level);
            cnt++;

            int[] result = compare(num);

            consoleInteraction.showResult(result, level);

            if(result[0]==level)
                break;

        }
        gameHistory.add(cnt);
    }


}
