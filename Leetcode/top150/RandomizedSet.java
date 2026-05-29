package Leetcode.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RandomizedSet {
    HashMap<Integer, Integer> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        int newVal = 0;
        list.set(index, newVal);
        list.remove(list.size() - 1);
        map.remove(val);
        map.replace(newVal, index);
        return true;
    }

    public int getRandom() {
        int randomIndex = (int) (list.size() * Math.random());
        return list.get(randomIndex);
    }

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        ans[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int postfix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] = ans[i] * postfix;
            postfix = postfix * nums[i];
        }
        return ans;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int ans = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (i <= j && nums1[i] <= nums2[j]) {
                int dis = j - i;
                ans = Math.max(ans, dis);
                j++;
            } else {
                i++;
                j++;
            }
        }
        return ans;

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int t = 0;
        for (int i = 0; i < gas.length; i++) {
            t += gas[i];
            t -= cost[i];
        }
        if (t < 0) return -1;
        int ans = -1, tank = 0;
        for (int i = 0; i < gas.length; i++) {
            if (tank <= 0 && gas[i] > cost[i]) {
                tank = 0;
                tank += gas[i] - cost[i];
                ans = i;
            } else {
                tank += gas[i] - cost[i];
            }
        }
        return ans;
    }

    public int candy(int[] ratings) {
        int ans = 0;
        int candy[] = new int[ratings.length];
        candy[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            candy[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i > 0; i++) {
            if (ratings[i] > ratings[i + 1]) {
                ans += Math.max(candy[i], candy[i + 1] + 1);
            } else {
                ans += candy[i];
            }
        }
        return ans;
    }

    public int trap(int[] height) {
        int ans = 0;
////        int prefixMax[] = new int[height.length];
//        int suffixMax[] = new int[height.length];
////        prefixMax[0]=height[0];
//        suffixMax[height.length-1]=height[height.length-1];
////        for (int i = 1; i < height.length; i++) {
////            prefixMax[i]=Math.max(prefixMax[i-1],height[i]);
////        }
//        for(int i = height.length-2; i > 0; i--) {
//            suffixMax[i]=Math.max(suffixMax[i+1],height[i]);
//        }
//        int prefixMax = height[0];
//        for (int i = 0; i <height.length; i++) {
//            int water=Math.min(prefixMax,suffixMax[i])-height[i];
//            if(water>0 && prefixMax>height[i] && suffixMax[i]>height[i])
//                ans+=water;
//            prefixMax=Math.max(prefixMax,height[i]);
//        }
//        return ans;
        int l = 0, r = height.length - 1;
        int lMax = 0, rMax = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                if (lMax > height[l]) {
                    ans += lMax - height[l];

                }
                lMax = Math.max(lMax, height[l]);
                l++;
            } else {
                if (rMax > height[r]) {
                    ans += rMax - height[r];

                }
                rMax = Math.max(rMax, height[r]);
                r--;
            }
        }
        return ans;
    }

    public int romanToInt(String s) {
        int ans = 0;
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int i = 0;
        while (i < s.length()) {
            if (i + 1 < len && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                ans -= map.get(s.charAt(i));
            } else {
                ans += map.get(s.charAt(i));
            }
            i++;
        }
        return ans;
    }

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return sb.toString();
                }

            }
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        String[] words = s.split("\\s+");
        for (int i = words.length - 1; i > 0; i--) {
            sb.append(words[i]);
            sb.append(' ');
        }
        sb.append(words[0]);
        return sb.toString();
    }

    public int[] concatWithReverse(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[ans.length - 1 - i] = nums[i];
        }
        return ans;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        int increment = 2 * (numRows - 1);
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < s.length(); j += increment) {
                sb.append(s.charAt(j));
                if (i > 0 && i < numRows - 1 && j + increment - 2 * i < s.length()) {
                    sb.append(s.charAt(j + increment - 2 * i));
                }

            }
        }
        return sb.toString();
    }

    public int strStr(String haystack, String needle) {
        int ans = -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = i + 1;
                int t = 1;
                while (j < haystack.length() && t < needle.length() && haystack.charAt(j) == needle.charAt(t)) {
                    j++;
                    t++;
                }
                if (t == needle.length()) {
                    return i;
                }

            }
        }
        return ans;

    }

//    public boolean check(int[] nums) {
//        // We need to check if array is increasing or decreasing
//        // only twice if checking for increasing and only once if checking for decreasing
//        boolean flag = true;
//        int noOfChanges =0;
//        for (int i = 0; i < nums.length; i++) {
//            if(flag)
//            {
//
//            }
//        }
//    }


    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !isValidChar(s.charAt(l))) l++;
            while (l < r && !isValidChar(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;
            r--;
        }
        return true;
    }

    public boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    public boolean isSubsequence(String s, String t) {
        int i=0,j=0;
        while (i<s.length() && j<t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;

        }
        return i == s.length();
    }

    public int[] twoSum(int[] numbers, int target) {
        int l=0,r=numbers.length-1;
        while (l<r) {
            if (numbers[l]+numbers[r]==target) {
                return new int[]{l+1,r+1};
            }
            else if (numbers[l]+numbers[r]>target) {
                r--;
            }
            else {
                l++;
            }
        }
        return new int[]{-1,-1};
    }

    public int maxArea(int[] height) {
        return 0;

    }
}
