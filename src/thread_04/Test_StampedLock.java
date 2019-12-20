package thread_04;

import java.util.concurrent.locks.StampedLock;

public class Test_StampedLock {

	private static double x, y;
	static StampedLock stampedLock = new StampedLock();
	
	// 排它锁， 写锁
	static void move(double deltaX, double deltaY) {
		long writeLock = stampedLock.writeLock();
		try {
			x += deltaX;
			y += deltaX;
		} finally {
			stampedLock.unlockWrite(writeLock);
		}
	}
	
    // 乐观读锁（tryOptimisticRead）
    double distanceFromOrigin() {
        // 尝试获取乐观读锁（1）
        long stamp = stampedLock.tryOptimisticRead();
        // 将全部变量拷贝到方法体栈内（2）
        // 之所以要全部拷贝，是怕之后有线程把x给改了，还没来得及改y，导致当前线程用了一组不匹配的x，y
        double currentX = x, currentY = y;
        // 检查在（1）获取到读锁票据后，锁有没被其它写线程排它性抢占（3）
        if (!stampedLock.validate(stamp)) {
            // 如果被抢占则获取一个共享读锁（悲观获取）（4）
            stamp = stampedLock.readLock();
            try {
                // 将全部变量拷贝到方法体栈内（5）
                currentX = x;
                currentY = y;
            } finally {
                // 释放共享读锁（6）
            	stampedLock.unlockRead(stamp);
            }
        }
        // 返回计算结果（7）, 此处存在问题，currentX和currentY不一定是最新值
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
	

    // 使用悲观锁获取读锁，并尝试转换为写锁
    void moveIfAtOrigin(double newX, double newY) {
        // 这里可以使用乐观读锁替换（1）
        long stamp = stampedLock.readLock();
        try {
            // 如果当前点在原点则移动（2）
            while (x == 0.0 && y == 0.0) {
                // 尝试将获取的读锁升级为写锁（3）
                long ws = stampedLock.tryConvertToWriteLock(stamp);
                // 升级成功，则更新票据，并设置坐标值，然后退出循环（4）
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁，然后循环重试（5）
                	stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        } finally {
            // 释放锁（6）
        	stampedLock.unlock(stamp);
        }
    }
    
	public static void main(String[] args) {
		
	}

}
