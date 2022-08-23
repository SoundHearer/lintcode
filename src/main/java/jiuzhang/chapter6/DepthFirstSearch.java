package jiuzhang.chapter6;

/**
 * 用一个递归方法来遍历所有顶点。在访问其中一个顶点时：
 * 将它标记为已访问；
 * 递归地访问它的所有没有被标记过的邻居顶点。
 *
 * @Author: SelectBook
 * @Date: 2022/8/18 3:59
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        // 记录和起点连通的所有顶点
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /**
     * 递归方法会标记给定的顶点并调用自己来访问该顶点的相邻顶点列表中所有没有被标记过的顶点
     * 如果图是连通的，每个邻接链表中的元素都会被检查到
     * @param G
     * @param v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
