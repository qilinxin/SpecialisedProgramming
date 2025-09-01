public class QuickSums {

    public int minSums(String numbers, int sum) {
        if (numbers == null || numbers.isEmpty()) return -1; // 题面默认非空，这里做保护
        this.s = numbers;
        this.target = sum;
        this.best = Integer.MAX_VALUE;

        dfs(0, 0L, 0); // idx=0, 当前和=0, 加号数=0
        return (best == Integer.MAX_VALUE) ? -1 : best;
    }

    private String s;
    private int target;
    private int best;

    private void dfs(int idx, long cur, int plus) {
        // 剪枝：加号数已不优
        if (plus >= best) return;

        // 用完所有字符
        if (idx == s.length()) {
            if (cur == target) best = Math.min(best, plus);
            return;
        }

        long val = 0L;
        // 从 idx 开始取任意长度的一段作为下一项
        for (int j = idx; j < s.length(); j++) {
            // 增量构造数字，等价于 Long.parseLong(s.substring(idx, j+1)) 但更快
            val = val * 10 + (s.charAt(j) - '0');

            long next = cur + val;

            // 非负累加，超过目标就没必要再延长这段了
            if (next > target) break;

            // 第一段不增加加号，后续每段进入前都增加一个加号
            dfs(j + 1, next, plus + (idx == 0 ? 0 : 1));
        }
    }

    // 简单自测
    public static void main(String[] args) {
        QuickSums qs = new QuickSums();
        System.out.println(qs.minSums("12", 3));     // 1 -> "1+2"
        System.out.println(qs.minSums("12", 12));    // 0
        System.out.println(qs.minSums("303", 6));    // 1 -> "3+03"
        System.out.println(qs.minSums("99999", 45)); // 4 -> "9+9+9+9+9"
        System.out.println(qs.minSums("0000", 0));   // 0 -> "0000"
    }
}
