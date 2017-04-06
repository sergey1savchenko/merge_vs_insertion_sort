import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataReader {

	public static String[] readFile(String file, int size) throws IOException{
		
		String [] data = new String [size];
		
		FileInputStream fstream1 = new FileInputStream(file);
	    DataInputStream in = new DataInputStream(fstream1);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
	    System.out.println("Size of file: "+br.readLine()+" | Programm will read first "+size+" lines");	// 1st info line
	    
		for (int i=0; i<size; i++){
			data[i] = br.readLine();
		}
		
		br.close();
		in.close();
		fstream1.close();
		
		return data;
	}

}
