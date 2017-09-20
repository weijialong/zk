package query;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import connetion.ZKConnection;

public class QueryZkData {
	/**
	 * 获取子节点 不监控
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public static void qery1(ZooKeeper zk) throws Exception {
           
			List<String> list = zk.getChildren("/canis", true);
			for (String s : list) {
				System.out.println("子节点：" + s);
			}
	}
	/**
	 * 获取子节点 不监控
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public static void qery2(ZooKeeper zk) throws Exception {
           
			List<String> list = zk.getChildren("/canis", true);
			for (String s : list) {
				System.out.println("子节点：" + s);
			}
	}
	
	

	public static Watcher getWatcher() {
		Watcher wather = new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println("路径：" + event.getPath() + "   类型：" + event.getType() + "  状态：" + event.getState());

			}
		};

		return wather;
	}

	public static void main(String[] args) throws Exception {

		System.out.println("开始");
		qery1(ZKConnection.getzk(getWatcher()));
		System.out.println("结束");

	}

}
