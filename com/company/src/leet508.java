import java.util.*;

public class leet508 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode (int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = 0;
            this.left = left;
            this.right = right;
        }
    }

    private int max = 0;
    public int[] findFrequnetTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        List<Integer> list = new LinkedList<>();
        for(Integer i : map.keySet()) {
            if (map.get(i) == max) {
                list.add(i);
            }
        }
        int[] result = new int[list.size()];
        for(int i =0; i< list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private int helper(TreeNode root, Map<Integer,Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, map);
        int right = helper(root.right, map);
        int val = root.val + left + right;
        map.put(val, map.getOrDefault(val, 0)+1);
        max = Math.max(map.get(val),max);
        return val;
    }
}
