package jiuzhang.chapter6;

import basic.Queue;
import basic.Stack;

/**
 * 使用一个队列来保存所有已经被标记过但其邻接表还未被检查过的顶点。
 * 先将起点加入队列，然后重复以下步骤直到队列为空：
 * 取队列中的下一个顶点 v 并标记它，将与 v 相邻的所有未被标记过的顶点加入队列。
 * @Author: SelectBook
 * @Date: 2022/8/18 6:07
 */
public class BreadthFirstPaths {

    /**
     * 到达该顶点的最短路径已知吗？
     */
    private boolean[] marked;
    
    /**
     * 到达该顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;
    
    /**
     * 起点
     */
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        // 标记起点
        marked[s] = true;
        // 将它加入队列
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            // 从队列中删去下一顶点
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                // 对于每个未被标记的相邻顶点
                if (!marked[w]) {
                    // 保存最短路径的最后一条边
                    edgeTo[w] = v;
                    // 标记它，因为最短路径已知
                    marked[w] = true;
                    // 并将它添加到队列中
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
