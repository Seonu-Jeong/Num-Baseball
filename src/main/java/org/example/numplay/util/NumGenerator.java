package org.example.numplay.util;

import java.util.*;

public class NumGenerator {
    List<Integer> answer;


    public void makeAnswer(int level){

        HashSet<Integer> selected = new HashSet<>();
        Random random = new Random();

        while(selected.size() < level){
            int num = random.nextInt(8)+1;

            if(selected.contains(num)) continue;

            selected.add(num);
        }

        answer = new ArrayList<>(selected);

        Collections.shuffle(answer);

    }
}
