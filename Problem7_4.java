package algorithm.lecture07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

// 7_4
public final class Problem7_4 {



    public static int solve(int[] info, int[][] edges) {
        Queue<State> queue = new ArrayDeque<>();

        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            children.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            children.get(edge[0]).add(edge[1]);
        }

        HashMap<String, Boolean> checked = new HashMap<>();
        boolean[] visited = new boolean[info.length];
        visited[0] = true;

        // 자식들을 다음 후보로 지정
        List<Integer> nextNodes = new ArrayList<>(children.get(0));
        queue.add(new State(1, 0, visited, nextNodes));
        // 시작상태 입력
        checked.put(Arrays.toString(visited), true);
        // 0번 노드 양
        int answer = 1;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            answer = Math.max(answer, current.sheep);

            for (int i = 0; i < current.nextNodes.size(); i++) {
                int node = current.nextNodes.get(i);
                int nextSheep = current.sheep + (info[node] == 0 ? 1 : 0);
                int nextWolves = current.wolves + (info[node] == 1 ? 1 : 0);

                if (nextWolves >= nextSheep) continue;

                boolean[] nextVisited = current.visited.clone();
                nextVisited[node] = true;
                String stateKey = Arrays.toString(nextVisited);

                if (checked.containsKey(stateKey)) continue;

                checked.put(stateKey, true);
                List<Integer> candidates = new ArrayList<>(current.nextNodes);
                candidates.remove(i);
                candidates.addAll(children.get(node));
                queue.add(new State(nextSheep, nextWolves, nextVisited, candidates));
            }
        }

        return answer;
    }


    private static class State {
        int sheep;
        int wolves;
        boolean[] visited;
        List<Integer> nextNodes;

        State(int sheep, int wolves, boolean[] visited, List<Integer> nextNodes) {
            this.sheep = sheep;
            this.wolves = wolves;
            this.visited = visited;
            this.nextNodes = nextNodes;
        }
    }
}
