import java.util.Stack;

class BinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        
        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    // Preorder Traversal (Root, Left, Right)
    public void preorderTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            // Push right first, then left (so left is processed first)
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    // Inorder Traversal (Left, Root, Right)
    public void inorderTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current is null, pop from stack
            current = stack.pop();
            System.out.print(current.val + " ");

            // Visit the right subtree
            current = current.right;
        }
    }

    // Postorder Traversal (Left, Right, Root)
    public void postorderTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        // Process nodes in the reverse order (Right, Left, Root)
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            // Push left and right children to stack1 (right first)
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }

        // The stack2 will contain nodes in postorder
        while (!stack2.isEmpty()) {
            TreeNode node = stack2.pop();
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.print("Preorder Traversal: ");
        tree.preorderTraversal(root);
        System.out.println();

        System.out.print("Inorder Traversal: ");
        tree.inorderTraversal(root);
        System.out.println();

        System.out.print("Postorder Traversal: ");
        tree.postorderTraversal(root);
    }
}
