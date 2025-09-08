import java.util.*;

public class ForestGarbage {
  static class Node {
    int r, c, g, near;
    Node(int r, int c, int g, int near) { this.r=r; this.c=c; this.g=g; this.near=near; }
  }

  public int[] bestWay(String[] forest) {
    int H = forest.length, W = forest[0].length();
    int sr=-1, sc=-1, fr=-1, fc=-1;

    // 找 S 和 F
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        char ch = forest[i].charAt(j);
        if (ch == 'S') { sr = i; sc = j; }
        else if (ch == 'F') { fr = i; fc = j; }
      }
    }

    // 预处理：标记每个格子是否是“邻垃圾”（四邻有 'g'）
    boolean[][] nearG = new boolean[H][W];
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    for (int r = 0; r < H; r++) {
      for (int c = 0; c < W; c++) {
        for (int k = 0; k < 4; k++) {
          int nr = r + dr[k], nc = c + dc[k];
          if (0 <= nr && nr < H && 0 <= nc && nc < W) {
            if (forest[nr].charAt(nc) == 'g') {
              nearG[r][c] = true;
              break;
            }
          }
        }
      }
    }

    // Dijkstra：distG/distN 记录到达该点的最优 (g, near)
    final int INF = 1 << 29;
    int[][] distG = new int[H][W];
    int[][] distN = new int[H][W];
    for (int i = 0; i < H; i++) {
      Arrays.fill(distG[i], INF);
      Arrays.fill(distN[i], INF);
    }

    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
      if (a.g != b.g) return a.g - b.g;           // 先比垃圾数
      return a.near - b.near;                     // 再比邻垃圾空地数
    });

    distG[sr][sc] = 0;
    distN[sr][sc] = 0;
    pq.add(new Node(sr, sc, 0, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.poll();
      if (cur.r == fr && cur.c == fc) break; // 提前结束：字典序最优已弹出
      if (cur.g > distG[cur.r][cur.c]) continue;
      if (cur.g == distG[cur.r][cur.c] && cur.near > distN[cur.r][cur.c]) continue;

      for (int k = 0; k < 4; k++) {
        int nr = cur.r + dr[k], nc = cur.c + dc[k];
        if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;

        char ch = forest[nr].charAt(nc);
        int addG = (ch == 'g') ? 1 : 0;
        // 第二项只统计走到 '.' 且该 '.' 四邻存在 'g'；S/F 不算“空地”
        int addNear = (ch == '.') && nearG[nr][nc] ? 1 : 0;

        int ng = cur.g + addG;
        int nn = cur.near + addNear;

        // 字典序松弛
        if (ng < distG[nr][nc] || (ng == distG[nr][nc] && nn < distN[nr][nc])) {
          distG[nr][nc] = ng;
          distN[nr][nc] = nn;
          pq.add(new Node(nr, nc, ng, nn));
        }
      }
    }

    return new int[]{distG[fr][fc], distN[fr][fc]};
  }
}
