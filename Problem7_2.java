package algorithm.lecture07;

import java.util.ArrayDeque;
import java.util.Queue;


// 협업 실습 수정

// 7_2
public final class Problem7_2 {

    // 레버에서 양 시작과 끝 시간을 계산하고 합해서 return
    public static int solveFromLever(char[][] maps) {
        int[][] positions = findPositions(maps);
        return leverBfs(maps, positions[2], positions[0], positions[1]);
    }

    private static int leverBfs(char[][] maps, int[] lever, int[] start, int[] exit) {
        int rows = maps.length;
        int cols = maps[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int startCnt = -1;
        int exitCnt = -1;

        queue.add(new int[] {lever[0], lever[1], 0});
        visited[lever[0]][lever[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int row = current[0];
            int col = current[1];
            int time = current[2];

            //종료 조건
            if (row == start[0] && col == start[1]) startCnt = time;
            if (row == exit[0] && col == exit[1]) exitCnt = time;
            if (startCnt != -1 && exitCnt != -1) return startCnt + exitCnt;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + dr[direction];
                int nextCol = col + dc[direction];
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) continue;
                if (visited[nextRow][nextCol] || maps[nextRow][nextCol] == 'X') continue;
                visited[nextRow][nextCol] = true;
                queue.add(new int[] {nextRow, nextCol, time + 1});
            }
        }
        return -1;
    }

    private static int[][] findPositions(char[][] maps) {
        int[][] positions = new int[3][2]; // S, E, L의 행과 열
        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[row].length; col++) {
                char cell = maps[row][col];
                if (cell == 'S') {
                    positions[0][0] = row;
                    positions[0][1] = col;
                } else if (cell == 'E') {
                    positions[1][0] = row;
                    positions[1][1] = col;
                } else if (cell == 'L') {
                    positions[2][0] = row;
                    positions[2][1] = col;
                }
            }
        }
        return positions;
    }
}
