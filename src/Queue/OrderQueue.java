package Queue;

import Object.Order;
import ManageFile.FileManager;
import ManageFile.FileManagement;

public class OrderQueue implements Queue{

	private Order mOrderList[];
	private int ListHeaderIndex;
	private int ListCurrentSize;
	private FileManager fileManager;
	
	public OrderQueue(){
		this.mOrderList = new Order[50];
		this.ListHeaderIndex = 0;
		this.ListCurrentSize=50;
		this.fileManager = new FileManagement();
	}
	
	//상품 주문 큐에 삽입
	public void addOrder(Order Order)
	{
		this.mOrderList[this.ListHeaderIndex++] = Order;
		ExtendProductOrderList();
		return;
	}
	
	public void prepareOrder()
	{
		String fileStream="";
		//주문번호(int) 유저아이디(string) 유저주소(string) 상품번호(int) 상품가격(int) + 배송상태(boolean)
		
		for(int i=0; i<this.ListHeaderIndex; i++){
			fileStream+=(Integer.toString(mOrderList[i].getCurrentOrderNumber())+"@");
			fileStream+=(mOrderList[i].getOrderUser().getUserId()+"@");
			fileStream+=(mOrderList[i].getOrderUser().getUserAddress()+"@");
			fileStream+=(Integer.toString(mOrderList[i].getOrderProduct().getProductId())+"@");
			fileStream+=(Integer.toString(mOrderList[i].getOrderProduct().getProductPrice())+"@");
			fileStream+=(Boolean.toString(mOrderList[i].isOrderProcess())+"@$");
		}
		   
		fileManager.prepareOrder(fileStream);
		this.mOrderList = ReduceProductOrderList();
		
		return;
	}
	
	public void ExtendProductOrderList()
	{
		if(this.ListHeaderIndex >= this.ListCurrentSize/2)
		{
			Order tProductOrderList[] = new Order[this.ListCurrentSize*2];
			this.ListCurrentSize*=2;
			for(int i=0; i<= this.ListHeaderIndex; i++){
				tProductOrderList[i] = this.mOrderList[i];
			}
			this.mOrderList = tProductOrderList;
		}
		return;
	}
	
	public Order[] ReduceProductOrderList()
	{
		if(this.ListCurrentSize > 50) ListCurrentSize/=2;
		Order tProductOrderList[] = new Order[this.ListCurrentSize];
		this.ListHeaderIndex = 0;
		return tProductOrderList;
		
	}
}
