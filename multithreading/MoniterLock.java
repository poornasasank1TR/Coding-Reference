package multithreading;


class MoniterLockExample
{
    public synchronized void test1() throws InterruptedException {
        System.out.println("Starting Task 1");
        Thread.sleep(10000);
        System.out.println("Finished Task 1");
    }
}
public class MoniterLock
{
    public static void main(String[] args) {
    }
}
