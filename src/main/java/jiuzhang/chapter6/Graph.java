package jiuzhang.chapter6;

import basic.Bag;
import basic.In;

/**
 * 这份 Graph 的实现使用了一个由顶点索引的整型链表数组。
 * 每条边都会出现两次，即当存在一条连接 v 与 w 的边时，w 会出现在 v 的链表中，v 也会出现在 w 的链表中。
 * 第二个构造函数从输入流中读取一幅图，开头是 V，然后是 E，再然后是一列整数对，大小在 0 到 V-1 之间。
 * 
 * 使用的空间和 V+E 成正比；
 * 添加一条边所需的时间为常数；
 * 遍历顶点 v 的所有相邻顶点所需的时间和 v 的度数成正比（处理每个相邻顶点所需的时间为常数）。
 * @Author: SelectBook
 * @Date: 2022/8/18 2:16
 */
public class Graph {
    
    private final int V; // 顶点数目
    private int E; // 边的数目
    private Bag<Integer>[] adj; // 邻接表

    /**
     * 创建一个含有V个顶点但不含边的图
     * @param V
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // 创建邻接表
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>(); // 将所有链表初始化为空
        }
    }

    /**
     * 从标准输入流In读入一幅图
     * @param in
     */
    public Graph(In in)
    {
        this(in.readInt());          // 读取V并将图初始化
        int E = in.readInt();        // 读取E
        for (int i = 0; i < E; i++)
        {  // 添加一条边
            int v = in.readInt();        // 读取一个顶点
            int w = in.readInt();        // 读取另一个顶点
            addEdge(v, w);               // 添加一条连接它们的边
        }
    }

    /**
     * 顶点数(vertex)
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 边数(edge)
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * 向图中添加一条边(v-w)
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);  // 将w添加到v的链表中
        adj[w].add(v); // 将v添加到w的链表中
        E++;
    }

    /**
     * 和 v 相邻的所有顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
}
