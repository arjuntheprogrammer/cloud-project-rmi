

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.json.simple.JSONObject;




public interface ServerInterface extends Remote{
	public  JSONObject printFnames(String sDir) throws Exception;
	
	public  String print(String str) throws Exception;
	
	public byte[] fileDownload() throws RemoteException;
	
	
	
	public boolean recieveDataOnServer(byte[] data) throws RemoteException;

	public void setFileForDownload(String filePathOfServer) throws RemoteException;

	public void closeFileForDownload() throws RemoteException;

	public void setFileForUpload(String filePathOfServer) throws RemoteException;

	public void closeFileForUpload() throws RemoteException;

	
//	public byte[] downloadFile(String fileName) throws Exception;
//	public boolean uploadFile(byte[] fileData, String fileName) throws Exception;
}