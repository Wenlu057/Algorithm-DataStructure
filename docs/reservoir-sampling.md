## Reservoir Sampling

[Reservoir sampling](http://en.wikipedia.org/wiki/Reservoir_sampling) is a family of randomized algorithms for randomly choosing _k_ samples from a list of _n_ items, where _n_ is either a very large or unknown number. Typically n is large enough that the list doesn’t fit into main memory.

It can be solved in O(n) time. Following are the steps.

1) Create an array reservoir[0..k-1] and copy first k items of stream[] to it.
2) Now one by one consider all items from (k+1)th item to nth item.
…a) Generate a random number from 0 to i where i is index of current item in stream[]. Let the generated random number is j.
…b) If j is in range 0 to k-1, replace reservoir[j] with arr[i].



```
// An efficient Java program to randomly
// select k items from a stream of items
import java.util.Arrays;
import java.util.Random;
public class ReservoirSampling 
{
	// A function to randomly select k items from stream[0..n-1].
	static void selectKItems(int stream[], int n, int k)
	{
		int i; // index for elements in stream[]
		
		// reservoir[] is the output array. Initialize it with
		// first k elements from stream[]
		int reservoir[] = new int[k];
		for (i = 0; i < k; i++)
			reservoir[i] = stream[i]; // put first k elements into reservorir arr.
		
		Random r = new Random();
		
		// Iterate from the (k+1)th element to nth element
		for (; i < n; i++)
		{
			// Pick a random index from 0 to i.
			int j = r.nextInt(i + 1);
			
			// If the randomly picked index is smaller than k,
			// then replace the element present at the index
			// with new element from stream
			if(j < k)
				reservoir[j] = stream[i];		 
		}
		
		System.out.println("Following are k randomly selected items");
		System.out.println(Arrays.toString(reservoir));
	}
	
	//Driver Program to test above method
	public static void main(String[] args) {
		int stream[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int n = stream.length;
		int k = 5;
		selectKItems(stream, n, k);
	}
}
//This code is contributed by Sumit Ghosh
```

