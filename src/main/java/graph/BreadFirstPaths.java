package graph;

import basic.Queue;
import java.util.Stack;

/**
 * 使用广度优先搜索查找图中的路径
 */
public class BreadFirstPaths {
    private boolean[] marked; // 这个顶点上调用过dfs()了吗
    private int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s; // 起点

    public BreadFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true; // 标记起点
        queue.enqueue(s); //  将它加入队列
        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // 从队列中删去下一顶点
            for (int w : G.adj(v)) {
                if (!marked[w])       // 对于每个未被标记的相邻顶点
                {
                    edgeTo[w] = v;     // 保存最短路径的最后一条边
                    marked[w] = true;  // 标记它，因为最短路径已知
                    queue.enqueue(w);  // 并将它添加到队列中
                }
            }
        }
        
    }

    public boolean hasPath(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPath(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(s);
        }
        path.push(s);
        return path;
    }
}
