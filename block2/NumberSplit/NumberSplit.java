import java.util.HashMap;
import java.util.Map;

public class NumberSplit {

    private Map<Integer, Integer> memo = new HashMap<>();

    public int longestSequence(int start) {
        if (start < 10) return 1; // 单个数字
        if (memo.containsKey(start)) return memo.get(start);

        String s = String.valueOf(start);
        int n = s.length();
        int best = 1; // 至少包含自己

        // 枚举切分：用掩码表示哪些位置切开
        // n-1 个空隙，每个空隙可以切或不断开
        int gaps = n - 1;
        for (int mask = 1; mask < (1 << gaps); mask++) { // 至少要切一次
            long product = 1;
            int prev = 0;
            for (int i = 0; i < gaps; i++) {
                if (((mask >> i) & 1) == 1) {
                    // 切到 i 处
                    String part = s.substring(prev, i + 1);
                    product *= Long.parseLong(part);
                    prev = i + 1;
                }
            }
            // 最后一段
            String part = s.substring(prev);
            product *= Long.parseLong(part);

            // successor 必须是正整数
            if (product >= 0 && product <= Integer.MAX_VALUE) {
                int next = (int) product;
                int candidate = 1 + longestSequence(next);
                best = Math.max(best, candidate);
            }
        }

        memo.put(start, best);
        return best;
    }

    // 简单自测
    public static void main(String[] args) {
        NumberSplit ns = new NumberSplit();
        System.out.println(ns.longestSequence(99999));  // 5
//        System.out.println(ns.longestSequence(92));   // 4 -> 92,18,8
//        System.out.println(ns.longestSequence(24));   // 2 -> 24,8
    }
}
