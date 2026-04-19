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
        if(map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int index = map.get(val);
        int newVal = list.getLast();
        list.set(index, newVal);
        list.removeLast();
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
        ans[0]=1;ans[nums.length-1]=1;
        for(int i = 1; i < nums.length; i++)
        {
            ans[i] = ans[i-1]*  nums[i-1];
        }
        int postfix=1;
        for (int i = nums.length-1; i >=0; i--) {
            ans[i] = ans[i]*postfix;
            postfix = postfix*nums[i];
        }
        return ans;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int ans=0;
        int i=0,j=0;
        while(i<nums1.length && j<nums2.length)
        {
            if(i<=j && nums1[i]<= nums2[j])
            {
                int dis = j-i;
                ans=Math.max(ans,dis);
                j++;
            }
            else
            {
                i++;
                j++;
            }
        }
        return ans;

    }

}
