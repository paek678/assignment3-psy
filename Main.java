package algorithm.lecture07;

import java.util.Arrays;

// 강의자료에 있는 각 문제의 첫 번째 예제를 실행한다.
public class Main {

    public static void main(String[] args) {
        // 7_1 예상 대진표: 기존 풀이와 추가 풀이의 기대값은 모두 3
        System.out.println("7_1 (formula): " + Problem7_1.solve(8, 4, 7));
        System.out.println("7_1 (tree): " + Problem7_1.Tree(8, 4, 7));

        // 7_2 미로 탈출: 기대값 16
        char[][] maps = {
            "SOOOL".toCharArray(),
            "XXXXO".toCharArray(),
            "OOOOO".toCharArray(),
            "OXXXX".toCharArray(),
            "OOOOE".toCharArray()
        };
        System.out.println("7_2: " + Problem7_2.solveFromLever(maps));

        // 7_3 다단계 칫솔 판매: enroll 순서대로 이익금을 출력한다.
        String[] enroll = {
            "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"
        };
        String[] referral = {
            "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"
        };
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] profit = Problem7_3.Solve(enroll, referral, seller, amount);
        System.out.println("7_3: " + Arrays.toString(profit));

        // 7_4 양과 늑대: 기대값 5
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {
            {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10},
            {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}
        };
        System.out.println("7_4: " + Problem7_4.solve(info, edges));
    }
}
