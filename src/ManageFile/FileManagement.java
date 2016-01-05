package ManageFile;

import java.util.List;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Object.Order;

public class FileManagement implements FileManager{

	static String FilePath="text";
	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	
	public FileManagement(){}

	public List<Order> parsingOrderlist(String stringInput){
		List<Order> retOrderList = new ArrayList<Order>();
		
		StringTokenizer stringTokenizer = new StringTokenizer(stringInput,"$");
		List<String> tOrderList = new ArrayList<String>();
		
		while(stringTokenizer.hasMoreTokens()){
			String tString = stringTokenizer.nextToken();
			tOrderList.add(tString);
		}
		
		for(int i=0; i<tOrderList.size(); i++){
			List<String> tOrderDetail = new ArrayList<String>();
			StringTokenizer orderDetailToken = new StringTokenizer(tOrderList.get(i), "@");
			while(orderDetailToken.hasMoreTokens()){
				String tDetail = orderDetailToken.nextToken();
				tOrderDetail.add(tDetail);
			}
			
			//주문번호(int) 유저아이디(string) 유저주소(string) 상품번호(int) 상품가격(int) + 배송상태(boolean)
			Order tOrder = new Order(Integer.parseInt(tOrderDetail.get(0)), tOrderDetail.get(1),
					tOrderDetail.get(2), Integer.parseInt(tOrderDetail.get(3)),
					Integer.parseInt(tOrderDetail.get(4)), Boolean.parseBoolean(tOrderDetail.get(5)));
			
			retOrderList.add(tOrder);
		}
		
		return retOrderList;
	}
	
	
	//file open, file read 두 번 체크가 필요
	public String fileInputStream() {
		String stringInput = "";
		int fileStream;
		
		try {
			fileInputStream = new FileInputStream(FilePath);
			try {
				while((fileStream=fileInputStream.read()) != -1){
					stringInput = stringInput + (char)fileStream;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		}	
		return stringInput;
	}
	
	public void fileOutputStream(String orderList, boolean overWritten) {
		// TODO Auto-generated method stub
		
		//file open
		try {
			this.fileOutputStream = new FileOutputStream(this.FilePath, overWritten);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//file write
		try {
			this.fileOutputStream.write(orderList.getBytes());
		} catch (IOException e) {
			System.out.println("write error");
			e.printStackTrace();
		}
		
		
		//file close
		try {
			this.fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("write file close error");
			e.printStackTrace();
		}
		
		return ;
	}

	@Override
	public void prepareOrder(String input) {
		// TODO Auto-generated method stub
		fileOutputStream(input, true);
	}

	@Override
	public void showOrderList() {
		// TODO Auto-generated method stub
		
		String tShowOrderListString = fileInputStream();
		List<Order> tShowOrderList = parsingOrderlist(tShowOrderListString);
		for(int i=0; i<tShowOrderList.size(); i++){
			System.out.println(tShowOrderList.get(i));
		}
		return;
	}


	@Override
	public Order searchOrder(int orderNum) {
		// TODO Auto-generated method stub
		String tShowOrderListString = fileInputStream();
		List<Order> tShowOrderList = parsingOrderlist(tShowOrderListString);
		
		for(int i=0; i < tShowOrderList.size(); i++){
			Order currentOrder = tShowOrderList.get(i);
			if(currentOrder.getCurrentOrderNumber() == orderNum){
				return currentOrder;
			}
		}
		return null;
	}


	@Override
	public void processOrder(int orderNum) {
		// TODO Auto-generated method stub
		
		String fileStream="";
		String tOrderListString = fileInputStream();
		List<Order> tOrderList = parsingOrderlist(tOrderListString);
		for(int i=0; i<tOrderList.size(); i++){
			if(tOrderList.get(i).getCurrentOrderNumber() == orderNum)
			{
				fileStream+=(tOrderList.get(i).getCurrentOrderNumber()+""+"@");
				fileStream+=(tOrderList.get(i).getOrderUser().getUserId()+"@");
				fileStream+=(tOrderList.get(i).getOrderUser().getUserAddress()+"@");
				fileStream+=(tOrderList.get(i).getOrderProduct().getProductId()+""+"@");
				fileStream+=(tOrderList.get(i).getOrderProduct().getProductPrice()+""+"@");
				fileStream+=((Boolean)true+"@$");
			}
			else
			{
				fileStream+=(tOrderList.get(i).getCurrentOrderNumber()+""+"@");
				fileStream+=(tOrderList.get(i).getOrderUser().getUserId()+"@");
				fileStream+=(tOrderList.get(i).getOrderUser().getUserAddress()+"@");
				fileStream+=(tOrderList.get(i).getOrderProduct().getProductId()+""+"@");
				fileStream+=(tOrderList.get(i).getOrderProduct().getProductPrice()+""+"@");
				fileStream+=(tOrderList.get(i).isOrderProcess()+"@$");
			}
		}
		fileOutputStream(fileStream, false);
	}
}
