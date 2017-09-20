package connetion;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKConnection {
	public static  CountDownLatch c=new CountDownLatch(1);

	public  static  ZooKeeper getzk(Watcher watcher) {

		try {
			ZooKeeper zk = new ZooKeeper("127.0.0.1:8581,127.0.0.1:8582,127.0.0.1:8583", 1000000, watcher);
			c.await();
			return zk;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
