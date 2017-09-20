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
	 * 删除
	 * @param zk
	 * @throws Exception 
	 * @throws KeeperException 
	 */
	public   static void delete2(ZooKeeper zk) throws  Exception {
		zk.delete("/canis/0917", 0);
		Thread.sleep(5000);
		zk.delete("/canis/0915", 0, new VoidCallbackTest(), "删除成功");
	    Thread.sleep(1000000);
		System.out.println(zk.getSessionId());
		
	}
	
	public static Watcher getWatcher() {
		Watcher wather=new Watcher() {
			public void process(WatchedEvent event) {
				ZKConnection.c.countDown();
		       System.out.println("路径："+event.getPath()+"   类型："+ event.getType()+"  状态："+event.getState());
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
		
		System.out.println("开始");
		zk=ZKConnection.getzk(getWatcher());
		delete2(zk);
		System.out.println("结束");

	}

}
class VoidCallbackTest implements VoidCallback {

	public void processResult(int rc, String path, Object ctx) {
		System.out.println(rc+"--"+path+"--"+ctx);
		
	}


		
}