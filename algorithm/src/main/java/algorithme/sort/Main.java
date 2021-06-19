package algorithme.sort;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] A = {1, 3, 4, 0, 0, 0};
        int[] B = {2, 5, 6};
        int[] ans = merge(A, B, 3, 3);
        for (int a : ans) {
            System.out.println(a);
        }
    }


    private static int[] merge(int[] A, int[] B, int m, int n) {
        int aLen = A.length;
        int bLen = B.length;
        int ap = 0, bp = 0;
        while (ap < m) {
            if (A[ap] <= B[bp]) {
                ap++;
                continue;
            }
            int tmp = A[ap];
            A[ap] = B[bp];
            B[bp] = tmp;
        }
        if (n >= 0) System.arraycopy(B, 0, A, ap, n);
        return A;
    }
}
