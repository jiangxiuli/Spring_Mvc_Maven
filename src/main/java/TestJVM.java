import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;

public class TestJVM {
    static final long MB = 1024 * 1024;
    
    public static void main(String[] args) {
        int i = 10;
        while (i-- > 0) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });
            thread.start();
            getJVM();
            getThread();
            System.out.println("===========打印vm各内存区信息==========");
            printMemoryPoolInfo();
        }

    }

    public static void getJVM() {
        // 获取内存情况
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();

        System.out.println("\n================================================================================================================");
        /*
         * System.out.println("Heap Memory Usage: " +
         * memorymbean.getHeapMemoryUsage());
         * System.out.println("Non-Heap Memory Usage: " +
         * memorymbean.getNonHeapMemoryUsage());
         */

        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("\nINIT HEAP: " + usage.getInit() / 1024 / 1024 + "m");
        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "m");
        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "m");

        usage = memorymbean.getNonHeapMemoryUsage();
        System.out.println("\nINIT NOHEAP: " + usage.getInit() / 1024 / 1024 + "m");
        System.out.println("MAX NOHEAP: " + usage.getMax() / 1024 / 1024 + "m");
        System.out.println("USE NOHEAP: " + usage.getUsed() / 1024 / 1024 + "m");
    }

    public static void getThread() {
        // 获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        System.out.println("\nThreadMXBean：");
        ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount " + tm.getThreadCount());
        System.out.println("getPeakThreadCount " + tm.getPeakThreadCount());
        System.out.println("getDaemonThreadCount " + tm.getDaemonThreadCount());
    }
    
    private static void printMemoryPoolInfo() {
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        if (pools != null && !pools.isEmpty()) {
            for (MemoryPoolMXBean pool : pools) {
                // 只打印一些各个内存区都有的属性，一些区的特殊属性，可看文档或百度
                // 最大值，初始值，如果没有定义的话，返回-1，所以真正使用时，要注意
                System.out.println("vm内存区:\n\t名称=" + pool.getName() + "\n\t所属内存管理者=" + Arrays.deepToString(pool.getMemoryManagerNames()) + "\n\t ObjectName=" + pool.getObjectName() + "\n\t初始大小(M)="
                        + pool.getUsage().getInit() / MB + "\n\t最大(上限)(M)=" + pool.getUsage().getMax() / MB + "\n\t已用大小(M)=" + pool.getUsage().getUsed() / MB + "\n\t已提交(已申请)(M)="
                        + pool.getUsage().getCommitted() / MB + "\n\t使用率=" + (pool.getUsage().getUsed() * 100 / pool.getUsage().getCommitted()) + "%");

            }
        }
    }
}
