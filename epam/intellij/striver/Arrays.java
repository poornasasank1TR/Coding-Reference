package epam.intellij.striver;

public class Arrays
{
    public int maxProduct(int[] nums)
    {
        int max_product=Integer.MIN_VALUE;
        int i=0,j=nums.length-1;
        int l=1,r=1;
        while (i<nums.length)
        {
            l=nums[i++]*l;
            max_product=Integer.max(max_product,l);
            if (l==0)
                l=1;
            r=nums[j--]*r;
            max_product=Integer.max(max_product,r);
            if(r==0)
                r=1;
        }

        return max_product;
    }

    public int findMin(int[] nums)
    {
//        if (nums.length == 1 ) return nums[0];
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i]>nums[i+1])
//            {
//                return nums[i+1];
//            }
//            if ()
//        }
        int n=nums.length;
        int low=0,high=n-1;
        while (low<=high)
        {
            int mid=(low+high)/2;
            if (nums[mid]<nums[mid-1] && nums[mid]>nums[mid+1])
            {
                return nums[mid];
            }
            
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Hi");
    }
}
