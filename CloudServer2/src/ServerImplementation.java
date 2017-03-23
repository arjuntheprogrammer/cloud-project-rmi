import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {
	File f1 = null;
	FileInputStream in = null;
	
	
	private String fname = "", fpath = "";

	protected ServerImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setFile(String path, String name) {
		fname = name;
		fpath = path;
	}

	@Override
	public String print(String str) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject printFnames(String sDir) throws Exception {
		File dir = new File(sDir);
		JSONObject obj = new JSONObject();
		// System.out.println(dir.getAbsolutePath());
		if (dir.isFile()) {
			obj.put("type", "file");
			obj.put("name", dir.getName());

		} else if (dir.isDirectory()) {
			JSONArray ja = new JSONArray();

			File[] faFiles = dir.listFiles();

			for (File file : faFiles) {
				try {
					//ja.put(printFnames(file.getAbsolutePath()));
					
					ja.add(printFnames(file.getAbsolutePath()));
				} catch (Exception e) {

				}
			}

			obj.put("type", "directory");
			obj.put("name", dir.getName());
			obj.put("subDir", ja);
		}
		System.out.println(obj);
		
		return obj;

	}
	@Override
	public int setFile(String name){
		f1 = new File(name);
		try {
			in = new FileInputStream(f1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int)(f1.length());
		
		
	}
	
	@Override
	public void closeFile(){
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public byte[] fileDownload() throws RemoteException {
		byte[] mydata = null;
		try {
			//f1 = new File(filePathOfServer);
			//FileInputStream in = new FileInputStream(f1);
			
			mydata = new byte[1024];
			int mylen = in.read(mydata);
			
			if(mylen<=0){
				return null;
			}
			
			//in.close();
			
			return mydata;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mydata;

		
	}

	@Override
	public boolean recieveDataOnServer(String filename, byte[] data, int len) throws RemoteException {
		try {
			String path_where_server_saves="/home/arjun/kiet1/";
			File f= new File(path_where_server_saves+filename);
			f.createNewFile();
			FileOutputStream out=new FileOutputStream(f, true);
			out.write(data, 0, len);
			out.flush();
			out.close();
			System.out.println("Done writing data...");
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return true;
	}

}

//
//public boolean recieveDataOnClient(String filename, byte[] data, int len) throws RemoteException {
//	// TODO Auto-generated method stub
//	try {
//		String path_where_client_saves="/home/arjun/";
//		File f= new File(path_where_client_saves+filename);
//		f.createNewFile();
//		FileOutputStream out=new FileOutputStream(f, true);
//		out.write(data, 0, len);
//		out.flush();
//		out.close();
//		System.out.println("Done writing data...");
//	}
//	catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	
//	
//	
//	return true;
//}
//
//
//
//@Override
//public boolean fileUpload(ServerInterface serverImpl) throws RemoteException {
//	try {
//		
//		File f1 = new File(fpath + fname);
//		FileInputStream in = new FileInputStream(f1);
//		byte[] mydata = new byte[1024 * 1024];
//		int mylen = in.read(mydata);
//		while (mylen > 0) {
//
//			serverImpl.recieveDataOnServer(fname, mydata, mylen);
//			mylen = in.read(mydata);
//
//		}
//
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	return true;
//}

