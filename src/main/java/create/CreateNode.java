package create;

import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import connetion.ZKConnection;
import lombok.extern.slf4j.XSlf4j;
@XSlf4j
public class CreateNode {
	private static ZooKeeper zk=null;
	

	/**
	 * �����ص�����
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public   static void create1(ZooKeeper zk) throws  Exception {
		
		String path =zk.create("/canis/0915", "creatTest1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("���������"+path);
		String path1 =zk.create("/canis/0916", "creatTest1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("���������"+path1);
	}

	/**
	 * ���ص�����
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public   static void create2(ZooKeeper zk) throws  Exception {
		zk.create("/canis/0927", "creatTest1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new StCallBack(),"���Խ��������ô��1");
		Thread.sleep(5000);
		zk.create("/canis/0928", "creatTest1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new StCallBack(),"���Խ��������ô��2");
	    Thread.sleep(1000000);
		System.out.println(zk.getSessionId());
		
	}
	
	public static Watcher getWatcher() {
		Watcher wather=new Watcher() {
			public void process(WatchedEvent event) {
				ZKConnection.c.countDown();
		       System.out.println("·����"+event.getPath()+"   ���ͣ�"+ event.getType()+"  ״̬��"+event.getState());
				try {
					System.out.println(zk.getChildren("/canis", true));
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}};
		
		return wather ;
	}
	

	public static void main(String[] args) throws  Exception{
		
		System.out.println("��ʼ");
		zk=ZKConnection.getzk(getWatcher());
		CreateNode.create2(zk);
		System.out.println("����");

	}

}
class StCallBack implements StringCallback {

	public void processResult(int rc, String path, Object ctx, String name) {
		//re 0 �������ɹ� -110����ʧ��
		System.out.println(rc+"--"+path+"--"+ctx+"--"+name);
	}
	
	
	
}