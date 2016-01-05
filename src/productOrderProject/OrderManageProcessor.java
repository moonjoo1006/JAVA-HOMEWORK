package productOrderProject;


import ManageFile.FileManagement;
import ManageFile.FileManager;
import Object.Order;
import Object.Product;
import Object.User;
import Queue.OrderQueue;
import Queue.Queue;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;




public class OrderManageProcessor {
	
	private static Queue queue;
	private static Scanner scanner;
	private static FileManager fileManager;
	private static Random random;
	
	public static void main(String args[]) throws IOException {
		
		queue = new OrderQueue();
		scanner = new Scanner(System.in);
		fileManager = new FileManagement();
		random = new Random();
		
		helloMoonjooShop();
		
		return;
	}
	
	public static void helloMoonjooShop(){
		int Menu;
		
		while(true){
			showMenu();
			Menu = scanner.nextInt();
			
			switch(Menu)
			{
			case 1:
				order();
				break;
			case 2:
				prepareOrder();
				break;
			case 3:
				searchOrder();
				break;
			case 4:
				showOrderList();
				break;
			case 5:
				processOrder();
				break;	
			default:
				System.out.println("Not exist Menu");
			}
		}
	}
	public static void order(){
		//userid, useradderss, productid를 입력받아야함
		System.out.println("input userid, useraddress, productid");
		String tUserid, tUserAddress;
		int tProductid;
		tUserid = scanner.next();
		tUserAddress = scanner.next();
		tProductid = scanner.nextInt();
		System.out.println(tUserid + " " + tUserAddress + " " + tProductid);
		
		//주문을 추가하는 부분..
		Order tOrder = new Order(new Product(tProductid,random.nextInt(50000)),new User(tUserid, tUserAddress));
		
		queue.addOrder(tOrder);
		return;
	}
	
	//큐에 있는 주문을 파일에 처
	public static void prepareOrder()
	{
		queue.prepareOrder();
		return;
	}
	
	public static void searchOrder()
	{
		int searchNumber;
		System.out.println("input the Order Id to search");
		searchNumber = scanner.nextInt();
		
		Order searchOrder = fileManager.searchOrder(searchNumber);
		if(searchOrder == null){
			System.out.println("not exist order");
		}
		else{
			System.out.println(searchOrder);
		}
		return;
	}
	
	public static void showOrderList()
	{
		fileManager.showOrderList();
	}
	
	public static void processOrder()
	{
		int OrderNumber;
		System.out.println("input the OrderId to process");
		OrderNumber = scanner.nextInt();
		fileManager.processOrder(OrderNumber);
		return;
	}
	
	public static void showMenu()
	{
		System.out.println("***************************");
		System.out.println("* welcome to moonjoo shop *");
		System.out.println("*   select the menu!      *");
		System.out.println("*    1. Order             *");
		System.out.println("*    2. Prepare Order     *");
		System.out.println("*    3. Search Order      *");
		System.out.println("*    4. Show Order List   *");
		System.out.println("*    5. Process Order     *");
		System.out.println("***************************");
	}
}
