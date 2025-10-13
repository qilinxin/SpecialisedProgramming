import java.util.ArrayList;
import java.util.List;

public class CellRemoval {

  /** Simple multi-ary tree node. */
  static class Node {
    final int id;
    final List<Node> children = new ArrayList<>();
    Node(int id) { this.id = id; }
  }

  static class Result {
    final int leaves;
    final boolean exists;
    Result(int leaves, boolean exists) { this.leaves = leaves; this.exists = exists; }
  }

  public static int cellsLeft(int[] parent, int deletedCell) {
    // Step 1: build all nodes (id = index), then attach children by parent pointers
    BuildResult br = buildTree(parent);

    // If the root itself is deleted, nothing remains
    if (deletedCell == br.rootId) return 0;

    // Step 2: DFS from root, skipping the deleted subtree, count leaves
    return dfs(br.nodes[br.rootId], deletedCell).leaves;
  }

  static class BuildResult {
    final Node[] nodes;
    final int rootId;

    BuildResult(Node[] nodes, int rootId) { this.nodes = nodes; this.rootId = rootId; }
  }


  static BuildResult buildTree(int[] parent) {
    final int n = parent.length;
    Node[] nodes = new Node[n];
    for (int i = 0; i < n; i++) nodes[i] = new Node(i);

    int root = -1;
    for (int i = 0; i < n; i++) {
      int p = parent[i];
      if (p == -1) root = i;                    // the unique root
      else nodes[p].children.add(nodes[i]);     // attach child to its parent
    }
    return new BuildResult(nodes, root);
  }
  
  static Result dfs(Node node, int deleted) {
    if (node == null || node.id == deleted) return new Result(0, false);

    int leavesSum = 0;
    boolean hasExistingChild = false;

    for (Node c : node.children) {
      Result r = dfs(c, deleted);
      if (r.exists) {
        hasExistingChild = true;
        leavesSum += r.leaves;
      }
    }
    // If no existing child remains, current node becomes a leaf
    if (!hasExistingChild) return new Result(1, true);
    return new Result(leavesSum, true);
  }

  // --- quick demo ---
  public static void main(String[] args) {

    int[] parent1 = {-1, 0, 0, 1, 1};
    System.out.println(cellsLeft(parent1, 2)); // expect 2

    int[] parent2 = {
        26,2,32,36,40,19,43,24,30,13,21,14,24,21,19,4,30,10,44,12,7,32,17,43,
        35,18,7,36,10,16,5,38,35,4,13,-1,16,26,1,12,2,5,18,40,1,17,38,44,14
    };
    System.out.println(cellsLeft(parent2, 24)); // expect 14
  }
}
