``java
# 8. Topological Sort Pattern
Used for problems involving directed acyclic graphs (DAGs), where we need to order nodes based on dependencies.

Problem Example: Topological sort of a directed acyclic graph (DAG).


import java.util.*;

public class TopologicalSort {
    public List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        int[] inDegree = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        // Calculate in-degree of each vertex
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Add vertices with zero in-degree to the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform BFS to get topological order
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all vertices are processed, return the result
        return result.size() == V ? result : new ArrayList<>();
    }

    public static void main(String[] args) {
        TopologicalSort ts = new TopologicalSort();
        List<List<Integer>> adj = new ArrayList<>();
        adj.add(Arrays.asList(2, 3));  // 0 -> 2, 0 -> 3
        adj.add(Arrays.asList(3));     // 1 -> 3
        adj.add(Arrays.asList());      // 2 has no outgoing edges
        adj.add(Arrays.asList());      // 3 has no outgoing edges

        List<Integer> order = ts.topologicalSort(4, adj);
        System.out.println(order);  // Output: [0, 1, 2, 3] or similar valid topological orders
    }
}
9. Union-Find (Disjoint Set Union - DSU)
A data structure that efficiently handles the union and find operations, often used for connected components and cycle detection in graphs.

Problem Example: Determine if a graph has a cycle using union-find.

java
Copy code
public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        System.out.println(uf.find(0)); // Output: 2 (0 and 1 are connected)
        System.out.println(uf.find(3)); // Output: 4 (3 and 4 are connected)
    }
}
10. Breadth-First Search (BFS) Pattern
This is useful for finding the shortest path in an unweighted graph or tree.

Problem Example: Find the shortest path in an unweighted graph from a source node.

java
Copy code
import java.util.*;

public class BFS {
    public List<Integer> shortestPath(int[][] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);  // -1 means unvisited
        dist[start] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph[node]) {
                if (dist[neighbor] == -1) {  // If unvisited
                    dist[neighbor] = dist[node] + 1;
                    queue.offer(neighbor);
                }
            }
        }

        // Convert distances into a list of reachable nodes
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(dist[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        int[][] graph = {
            {1, 2},        // 0 -> 1, 0 -> 2
            {0, 2, 3},     // 1 -> 0, 1 -> 2, 1 -> 3
            {0, 1, 3},     // 2 -> 0, 2 -> 1, 2 -> 3
            {1, 2}         // 3 -> 1, 3 -> 2
        };

        List<Integer> distances = bfs.shortestPath(graph, 0);
        System.out.println(distances);  // Output: [0, 1, 1, 2] (distances from node 0)
    }
}
11. Depth-First Search (DFS) Pattern
DFS is used to explore as far as possible along each branch before backtracking.

Problem Example: Find all paths from a source node to a target node in a graph.

java
Copy code
import java.util.*;

public class DFS {
    public List<List<Integer>> allPaths(int[][] graph, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(graph, start, target, path, result);
        return result;
    }

    private void dfs(int[][] graph, int node, int target, List<Integer> path, List<List<Integer>> result) {
        path.add(node);
        if (node == target) {
            result.add(new ArrayList<>(path));
        } else {
            for (int neighbor : graph[node]) {
                dfs(graph, neighbor, target, path, result);
            }
        }
        path.remove(path.size() - 1);  // Backtrack
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        int[][] graph = {
            {1, 2},        // 0 -> 1, 0 -> 2
            {3},           // 1 -> 3
            {3},           // 2 -> 3
            {}             // 3 has no outgoing edges
        };

        List<List<Integer>> paths = dfs.allPaths(graph, 0, 3);
        System.out.println(paths);  // Output: [[0, 1, 3], [0, 2, 3]]
    }
}
12. Kadane’s Algorithm Pattern
This is used to find the maximum sum of a subarray in an array (or maximum subarray problem).

Problem Example: Find the maximum sum subarray in an array.

java
Copy code
public class KadaneAlgorithm {
    public int maxSubArraySum(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        KadaneAlgorithm ka = new KadaneAlgorithm();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(ka.maxSubArraySum(nums));  // Output: 6
    }
}
13. Divide and Conquer Pattern
This is used for problems where the solution involves dividing the problem into smaller subproblems and then combining their results.

Problem Example: Merge Sort (Divide and Conquer).

java
Copy code
import java.util.*;

public class MergeSort {
    public void mergeSort(int[] arr) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = {5, 2, 9, 1, 5, 6};
        ms.mergeSort(arr);
        System.out.println(Arrays.toString(arr));  // Output: [1, 2, 5, 5, 6, 9]
    }
}
14. Monotonic Stack Pattern
Used for problems involving finding the next greater or smaller element in an array.

Problem Example: Next greater element for each element in an array.

java
Copy code
import java.util.*;

public class MonotonicStack {
    public int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        MonotonicStack ms = new MonotonicStack();
        int[] nums = {4, 5, 2, 10};
        System.out.println(Arrays.toString(ms.nextGreaterElement(nums)));  // Output: [5, 10, 10, -1]
    }
}
These patterns can be adapted to solve a variety of problems and help in identifying optimal solutions efficiently.
