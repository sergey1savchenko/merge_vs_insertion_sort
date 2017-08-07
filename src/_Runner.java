import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class _Runner {
	
	protected static final int SIZE = 10000;												// MAX = 500000
	protected static final int THREADS = 8;													// N
	private static double st, en;
	
	public static void main(String[] args) throws IOException {
		
		String [] data = DataReader.readFile("500.000.txt", SIZE);
		// data for Insertion Sort
		ArrayList <String[]> arrays= new ArrayList<String[]>(THREADS);
		int residue = SIZE%THREADS;
		int arrSize = (SIZE-residue)/THREADS;
		int counter = 0;										// general counter, in the end must = SIZE.
		
		// splitting to N arrays
		for(int i=0; i<THREADS; i++){
			String [] temp;
			if(residue>0){ 
				temp = new String [arrSize+1];
			}else{
				temp = new String [arrSize];
			}
			for(int j=0; j<arrSize; j++){
				temp[j]=data[counter];
				counter++;
			}
			if(residue>0){
				temp[temp.length-1]=data[counter];
				counter++;
				residue--;
			}
			arrays.add(temp);
		}
		
		// creates copy for Merge Sort
		ArrayList<String[]> arrays1 = new ArrayList<String[]>(arrays);			// fine only for IMMUTABLE objects
		
		// runs InsertionSort in N threads and waits until all done
		st = System.nanoTime();
		ExecutorService pool = Executors.newFixedThreadPool(THREADS);
        List<Callable<Object>> tasks = new ArrayList<>();
        try {
            for (String[] element:arrays) {
                tasks.add(new Callable<Object>() {								// adds tasks
                    public Object call() throws Exception {
                    	InsertionStringSort.insertionSort(element);
						return element;
                    }
                });
            }
            List<Future<Object>> invokeAll = pool.invokeAll(tasks);				// runs pool of threads and WAIT
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();													// the end
        }
        
        // merging
        String insertionSortResult [] = ArraysMerger.mergeMultithread(arrays);
        
        en = System.nanoTime();
        
        // writing to file
        try(FileWriter writer = new FileWriter("InsertionSortMultiThreadedOutput.txt", false)){
        	for(String element: insertionSortResult){
        		writer.write(element);
        		writer.append('\n');
        	}
        	writer.write("Sorted in: "+((en-st)/1000000000)+" seconds"); writer.append('\n');
        	writer.write("By: "+THREADS+" threads");
        	writer.flush();
        }catch(IOException ex){
        	System.out.println(ex.getMessage());
        }
        System.out.println("Insertion Sort ("+THREADS+" Thread(s)) finished in: "+((en-st)/1000000000)+" seconds");

        st=0;
        en=0;
        
    	// runs MergeSort in N threads and waits until all done
 		st = System.nanoTime();
 		ExecutorService pool1 = Executors.newFixedThreadPool(THREADS);
         List<Callable<Object>> tasks1 = new ArrayList<>();
         try {
             for (String[] element:arrays1) {
                 tasks1.add(new Callable<Object>() {								// adds tasks
                     public Object call() throws Exception {
                     	MergeStringSort.mergeSort(element);
 						return element;
                     }
                 });
             }
             List<Future<Object>> invokeAll1 = pool1.invokeAll(tasks1);				// runs pool of threads and WAIT
         } catch (InterruptedException e) {
             e.printStackTrace();
         } finally {
             pool1.shutdown();														// the end
         }
         
         // merging
         String mergeSortResult [] = ArraysMerger.mergeMultithread(arrays1);
         
         en = System.nanoTime();
         
         // writing to file
         try(FileWriter writer = new FileWriter("MergeSortMultiThreadedOutput.txt", false)){
         	for(String element: mergeSortResult){
         		writer.write(element);
         		writer.append('\n');
         	}
         	writer.write("Sorted in: "+((en-st)/1000000000)+" seconds"); writer.append('\n');
         	writer.write("By: "+THREADS+" threads");
         	writer.flush();
         }catch(IOException ex){
         	System.out.println(ex.getMessage());
         }
        
        System.out.println("Merge Sort ("+THREADS+" Thread(s)) finished in: "+((en-st)/1000000000)+" seconds");
        
	}

}
