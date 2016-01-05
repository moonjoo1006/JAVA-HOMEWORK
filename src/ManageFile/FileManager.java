package ManageFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import Object.Order;

public interface FileManager {

	public void prepareOrder(String input);
	public void showOrderList();
	public Order searchOrder(int orderNum);
	public void processOrder(int orderNum);
}
