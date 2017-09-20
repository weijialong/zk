package delete;

import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import connetion.ZKConnection;

public class DeleteNode {
private static ZooKeeper zk=null;
	

	

	/**
	 * ɾ��
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public   static void delete2(ZooKeeper zk) throws  Exception {
		zk.delete("/canis/0917", 0);
		Thread.sleep(5000);
		zk.delete("/canis/0915", 0, new VoidCallbackTest(), "ɾ���ɹ�");
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
		delete2(zk);
		System.out.println("����");

	}

}
class VoidCallbackTest implements VoidCallback {

	public void processResult(int rc, String path, Object ctx) {
		System.out.println(rc+"--"+path+"--"+ctx);
		
	}


		
}