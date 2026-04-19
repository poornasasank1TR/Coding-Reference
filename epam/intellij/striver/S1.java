package epam.intellij.striver;

public class S1
{
    public int mirrorDistance(int n) {
        int rev = 0;
        int temp=n;
        while(temp!=0)
        {
            rev+=temp%10;
            rev*=10;
            temp/=10;
        }
        return Math.abs(n-rev);
    }
}
