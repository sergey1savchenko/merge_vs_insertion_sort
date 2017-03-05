
public class InsertionStringSort {
	
	static void insertionSort(Comparable[] array){
		Comparable temp;
		for(int i = 1; i < array.length; i++){			// ar[i] is element to insert
			temp = array[i];
			int j = 0;
			for(j = i; j > 0; j--)
				if(temp.compareTo(array[j - 1]) < 0)
					array[j] = array[j - 1];
				else
					break;
			array[j] = temp;
		}
    }
	
}