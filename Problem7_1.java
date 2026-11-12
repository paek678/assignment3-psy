package algorithm.lecture07;

// 7_1
public final class Problem7_1 {

    public static int solve(int n, int a, int b) {
        int round = 0;

        while (a != b) {
            a = (a / 2) + (a % 2); // 1, 2 -> 1 / 3, 4 -> 2
            b = (b / 2) + (b % 2);
            round++;
        }

        return round;
    }

    private static class player {
        player parent;
    }

    public static int Tree(int n, int a, int b) {
        player[] cur = new player[n];
        for (int i = 0; i < n; i++) {
            cur[i] = new player();
        }

        player playerA = cur[a - 1];
        player playerB = cur[b - 1];

        while (cur.length > 1) {
            player[] next = new player[cur.length / 2];

            for (int i = 0; i < cur.length; i += 2) {
                player match = new player();
                cur[i].parent = match;
                cur[i + 1].parent = match;
                next[i / 2] = match;
            }

            cur = next;
        }

        int round = 0;
        while (playerA != playerB) {
            playerA = playerA.parent;
            playerB = playerB.parent;
            round++;
        }

        return round;
    }
}
