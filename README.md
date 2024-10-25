# 숫자야구 게임

## 목차
1. [프로젝트 소개](#프로젝트-소개)
2. [실행예시](#실행예시)
3. [트러블슈팅](#트러블슈팅)
---

## 프로젝트 소개
- 이 프로젝트는 랜덤으로 생성된 숫자를 숫자의 입력과 힌트를 통해 맞추는 게임이다.

## 실행예시
- <b>게임 시작 후 사용자 입력과 예외처리 예시</b>

![image](https://github.com/user-attachments/assets/6b859e0f-bca5-47e2-8040-4576fb4f35bc)


- <b>게임 기록 출력 예시</b>

![image](https://github.com/user-attachments/assets/f60547d9-99d7-4090-a075-55ec06d0cd23)

- <b>자리수 설정 예시</b>

![image](https://github.com/user-attachments/assets/eae07710-fed4-4a50-8a21-6737dc76725e)

## 트러블슈팅

<details>
  <summary>입력값의 예외처리</summary>

- 개요<br>
사용자에게 게임 숫자 입력 시 예외처리 문장을 "잘못된 입력입니다"로 통일함

- 문제 상황 <br>
예외처리로 출력된 "잘못된 입력입니다"로는 사용자가 무슨 문제가 있는지 명확하게 알아차리기 힘들다.

 ``` java
    try {
        String num_string = sc.nextLine();
    
        int num = Integer.parseInt(num_string);
    
        int[] digit = new int[10];
    
        for(int i=0; i<num_string.length(); i++){
            digit[num_string.charAt(i)-'0']++;
        }
    
        if(digit[0]>=1)
            throw  new Exception();
    
        int cnt=0;
    
        for(int val : digit){
            if(val>1)
                throw new Exception();
    
            cnt+=val;
        }
    
        if(cnt!=level)
            throw new Exception();
    
        result = num;
    }
    catch (Exception e) {
        System.out.println("잘못된 숫자를 입력했습니다.");
        result=-1;
    }

 ```

- 해결 <br>
예외 처리에 대한 내용을 명확하기 위해 chained exception과 getMessage() 함수를 사용했다.

``` java
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
```
- 결론 <br>
각기 다른 오류에 대해 통일해서 출력하는 것보다 명확하게 나누는 것이 실행 및 테스트시 사용자가 이해하기 쉽다 

</details>
