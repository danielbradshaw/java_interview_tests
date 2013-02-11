import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("splitString -> " + splitString("Hello world I am Daniel!"));
		
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		Collections.addAll(testArray, 123, 2, 10, 3, 0, -12, 234, 1, 7, 9);
		//Collections.addAll(testArray, 0, 1, 2);
		System.out.println("largestFourInts -> " + largestFourInts(testArray));
	}
	
	/**
	 * Split String
	 */
	private static ArrayList<String> splitString(String input)
	{
		ArrayList<String> output = new ArrayList<String>();
		int lastIndex = 0;
		
		String inputTrimmed = input.trim();
		
		for (int i = 0; i < inputTrimmed.length(); i++) {
			if (inputTrimmed.charAt(i) == ' ') {
				output.add(inputTrimmed.substring(lastIndex, i));
				lastIndex = i + 1;
			}
		}
		
		if (inputTrimmed.length() > lastIndex) {
			output.add(input.substring(lastIndex + 1, inputTrimmed.length()));
		}
		
		return output;
	}
	
	/**
	 * STRSTR
	 */
	private static int findSubstring(String strToSearch, String strToFind)
	{
		return 0;
	}
	
	/**
	 * Finds the largest 4 integers in a given array.
	 */
	private static ArrayList<Integer> largestFourInts(ArrayList<Integer> input)
	{
		// PriorityQueue ensures an order, and will allow us to continually bump the smallest
		PriorityQueue<Integer> largestIntQueue = new PriorityQueue<Integer>(5);

		if (input == null) { 
			// if null input, return empty array
			return new ArrayList<Integer>(); 
		}
		else if (input.size() <= 4) { 
			// if small set of integers, just return input
			return input; 
		}
		else {
			// initialize the array with the first 4 items which later will likely be replaced by larger integers
			Collections.addAll(largestIntQueue, input.get(0), input.get(1), input.get(2), input.get(3));
		}
		
		for (int i = 4; i < input.size(); i++) 
		{
			boolean foundLarger = false;
			
			// if the current integer is larger than one in the set, we can insert and 
			// remove the smallest
			for (Integer largeInt : largestIntQueue) {
				if (input.get(i) > largeInt) {
					foundLarger = true;
					break;
				}
			}
			
			if (foundLarger) {
				// adds the newly found largest number to the queue
				largestIntQueue.add(input.get(i));
				
				// removes item at the head of the queue, the smallest
				largestIntQueue.remove();
			}
		}
		
		return new ArrayList<Integer>(largestIntQueue);
	}
	
	private static String getMostFrequentWord(String filename)
	{
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream));
			String line = null;
			StringBuilder strBuilder = new StringBuilder();
			
			while ((line = br.readLine()) != null) {
				strBuilder.append(line);
			}
		
			HashMap hashMap = new HashMap<String, Integer>();
			String[] words = strBuilder.toString().split(" ");
			for (int i = 0; i < words.length; i++) {
				hashMap.put(words[i], (Integer)hashMap.get(words[i]) + 1);
			}
			
			 // find max from set of values, the key is the word with the highest count
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Writes the image at the given URL out to the given filename.
	 * @param imageUrl the URL to an image resource
	 * @param outputFilename the output filename for the downloaded image
	 * @throws IOException thrown if error occurs during the download or write actions
	 */
	public static void saveImageFromURL(String imageUrl, String outputFilename) throws IOException 
	{
		URL url = new URL(imageUrl);
		InputStream inputStream = url.openStream();
		OutputStream outputStream = new FileOutputStream(outputFilename);

		byte[] bytes = new byte[2048];
		int length;

		while ((length = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, length);
		}

		inputStream.close();
		outputStream.close();
	}
}
