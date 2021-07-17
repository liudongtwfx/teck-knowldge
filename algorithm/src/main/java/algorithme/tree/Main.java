package algorithme.tree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.getProperties().forEach((k, v) -> {
            System.out.println("key:" + k + ",value:" + v);
        });
    }

    private List<Node> mergeSort(List<Node> nodes) {
        return dfs(nodes, 0, nodes.size());
    }

    private List<Node> dfs(List<Node> nodes, int left, int right) {
        int half = (left + right) / 2;
        if (right > left) {
            List<Node> leftPart = dfs(nodes, left, half);
            List<Node> rightPart = dfs(nodes, half + 1, right);
            return innerMerge(leftPart, rightPart);
        }
        return new ArrayList<>();
    }

    private List<Node> innerMerge(List<Node> left, List<Node> right) {
        List<Node> res = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).value <= right.get(j).value) {
                res.add(left.get(i));
                i++;
            } else {
                res.add(right.get(j));
                j++;
            }
        }
        while (i < left.size()) {
            res.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            res.add(right.get(j));
            j++;
        }
        return res;
    }

    static class Node {
        int value;
        Node next;
    }
}
