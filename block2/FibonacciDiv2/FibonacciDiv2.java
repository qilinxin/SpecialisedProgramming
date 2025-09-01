public class FibonacciDiv2 {
    public int find(int N) {
        // initialize data
        int a = 0, b = 1;
        // create fibonacci number, until >= N
        while (b < N) {
            int c = a + b;
            a = b;
            b = c;
        }
        return Math.min(N - a, b - N);
    }

    public static void main(String[] args) {
        FibonacciDiv2 fib = new FibonacciDiv2();
//        System.out.println(fib.find(1));   // 0 (1 已经是斐波那契数)
//        System.out.println(fib.find(13));  // 0
        System.out.println(fib.find(15));  // 2 (最近的斐波那契数是 13)
        System.out.println(fib.find(19));  // 2 (最近的是 21)
    }
}