package algorithme.dp;

public class KthAncestorOfATreeNode {
    public static void main(String[] args) {
        new KthAncestorOfATreeNode().bridge();
    }

    private void bridge() {
        int n = 50000;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i - 1;
        }
        TreeAncestor treeAncestor = new TreeAncestor(n, parent);
        for (int i = 0; i < n; i++) {
            System.out.println(treeAncestor.getKthAncestor(i, i));
        }
    }

    class TreeAncestor {
        public TreeAncestor(int n, int[] parent) {
        }

        public int getKthAncestor(int node, int k) {
            return 0;
        }
    }
}
