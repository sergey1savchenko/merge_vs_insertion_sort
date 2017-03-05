import java.io.FileWriter;
import java.io.IOException;

public class _RunnerSinglethread {
	
	private static final int SIZE = 1000000;									// MAX = 500000
	static double st, en;
	
	public static void main(String[] args) throws IOException {
		
		String [] data = DataReader.readFile("500.000.txt", SIZE);
		st = System.nanoTime();
		InsertionStringSort.insertionSort(data);
		en = System.nanoTime();
        try(FileWriter writer = new FileWriter("InsertionSortOneThreadOutput.txt", false)){
        	for(String element: data){
        		writer.write(element);
        		writer.append('\n');
        	}
        	writer.write("Sorted in: "+((en-st)/1000000000)+" seconds");
        	writer.flush();
        }catch(IOException ex){
        	System.out.println(ex.getMessage());
        }
        System.out.println("Insertion Sort (0ne Thread) finished in: "+((en-st)/1000000000)+" seconds");
        
        st=0;
        en=0;
		
		String [] data1 = DataReader.readFile("1.000.000.txt", SIZE);
        st = System.nanoTime();
        MergeStringSort.mergeSort(data1);
        en = System.nanoTime();
        try(FileWriter writer = new FileWriter("MergeSortOneThreadOutput.txt", false)){
        	for(String element: data1){
        		writer.write(element);
        		writer.append('\n');
        	}
        	writer.write("Sorted in: "+((en-st)/1000000000)+" seconds");
        	writer.flush();
        }catch(IOException ex){
        	System.out.println(ex.getMessage());
        }
        System.out.println("Merge Sort (0ne Thread) finished in: "+((en-st)/1000000000)+" seconds");
        
        st=0;
        en=0;
        
	}

}
