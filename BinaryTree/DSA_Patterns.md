# 1. Sliding Window Pattern
## This pattern is useful for problems involving contiguous subarrays or substrings.

### Problem Example: Find the maximum sum of a subarray of size k in an array.

public class SlidingWindow {
    public int maxSum(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return -1;  // Edge case: k cannot be greater than array size
        int maxSum = 0;
        int currentSum = 0;

        // Calculate the sum of the first window
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }

        maxSum = currentSum;

        // Slide the window, update the sum by adding the next element and removing the first element
        for (int i = k; i < n; i++) {
            currentSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println(sw.maxSum(arr, k)); // Output: 9
    }
}
# 2. Two Pointers Pattern
## This pattern is often used when working with problems involving sorted arrays or linked lists.

### Problem Example: Given a sorted array, find two numbers that sum up to a target.

java
Copy code
public class TwoPointers {
    public int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[] {arr[left], arr[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {};  // Return empty array if no such pair exists
    }

    public static void main(String[] args) {
        TwoPointers tp = new TwoPointers();
        int[] arr = {1, 2, 3, 4, 6};
        int target = 10;
        int[] result = tp.twoSum(arr, target);
        System.out.println("Pair: " + Arrays.toString(result));  // Output: [4, 6]
    }
}
# 3. Fast and Slow Pointers (Tortoise and Hare)
## This pattern is commonly used to detect cycles in linked lists or arrays.

### Problem Example: Detect a cycle in a linked list.


public class CycleDetection {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;  // Cycle detected
            }
        }
        return false;  // No cycle
    }

    public static void main(String[] args) {
        // Example usage for linked list: [3 -> 2 -> 0 -> -4 -> 2]
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;  // Creating a cycle

        CycleDetection detector = new CycleDetection();
        System.out.println(detector.hasCycle(head));  // Output: true
    }
}
# 4. Binary Search Pattern
## This pattern is used for searching in sorted arrays or solving problems related to divide and conquer.

## Problem Example: Find the index of a target element in a sorted array.


public class BinarySearch {
    public int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;  // Target not found
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 4;
        System.out.println(bs.binarySearch(arr, target));  // Output: 3
    }
}
# 5. Backtracking Pattern
## Backtracking is often used for solving problems like permutations, combinations, or solving constraint-based problems.

### Problem Example: Generate all possible permutations of a given string.


import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    public List<String> generatePermutations(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), s, new boolean[s.length()]);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String s, boolean[] used) {
        if (current.length() == s.length()) {
            result.add(current.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (used[i]) continue;  // Skip if already used
            used[i] = true;
            current.append(s.charAt(i));

            backtrack(result, current, s, used);

            // Backtrack: remove the last character and mark as unused
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Backtracking bt = new Backtracking();
        String s = "abc";
        List<String> permutations = bt.generatePermutations(s);
        System.out.println(permutations);  // Output: [abc, acb, bac, bca, cab, cba]
    }
}
# 6. Merge Intervals Pattern
## Used for merging overlapping intervals, often applicable to scheduling or range-based problems.

### Problem Example: Merge overlapping intervals.

import java.util.*;

public class MergeIntervals {
    public List<int[]> mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));  // Sort intervals based on start time
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            if (result.isEmpty() || result.get(result.size() - 1)[1] < interval[0]) {
                result.add(interval);
            } else {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], interval[1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] intervals = {{1, 3}, {2, 4}, {5, 7}, {6, 8}};
        List<int[]> merged = mi.mergeIntervals(intervals);
        for (int[] interval : merged) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
# 7. Dynamic Programming (DP) Pattern
## Dynamic programming is a powerful tool for solving optimization problems.

### Problem Example: Find the minimum number of coins required to make a given amount.

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);  // Initialize with a large number
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(cc.coinChange(coins, amount));  // Output: 3 (because 5 + 5 + 1 = 11)
    }
}
### Conclusion
### These patterns form the backbone of many common algorithmic problems. Understanding and implementing these templates will help you in solving problems across various DSA topics.











