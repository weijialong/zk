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
	 * ��ȡ�ӽڵ� �����
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public static void qery1(ZooKeeper zk) throws Exception {
           
			List<String> list = zk.getChildren("/canis", true);
			for (String s : list) {
				System.out.println("�ӽڵ㣺" + s);
			}
	}
	/**
	 * ��ȡ�ӽڵ� �����
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public static void qery2(ZooKeeper zk) throws Exception {
           
			List<String> list = zk.getChildren("/canis", true);
			for (String s : list) {
				System.out.println("�ӽڵ㣺" + s);
			}
	}
	
	

	public static Watcher getWatcher() {
		Watcher wather = new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println("·����" + event.getPath() + "   ���ͣ�" + event.getType() + "  ״̬��" + event.getState());

			}
		};

		return wather;
	}

	public static void main(String[] args) throws Exception {

		System.out.println("��ʼ");
		qery1(ZKConnection.getzk(getWatcher()));
		System.out.println("����");

	}

}
