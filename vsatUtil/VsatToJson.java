package vsatUtil;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vsatUtil.VsatLocation;

public class VsatToJson
{
	public static void fileToJson(String inputFile) throws IOException {
		Pattern pattern = Pattern.compile(",");
		final long startTime = System.currentTimeMillis();

		try (BufferedReader in = new BufferedReader(new FileReader(inputFile));){
			Map<String,Map<Integer,List<VsatLocation>>> vsatMap = in
				.lines()
				.skip(1)
				.map(line -> {
						String[] arr = pattern.split(line);
						return new VsatLocation(arr[0],
										  arr[1],
										  Integer.parseInt(arr[2]),
										  Double.parseDouble(arr[3]),
										  Double.parseDouble(arr[4]));
					})
				.collect(Collectors.groupingBy(VsatLocation::getDate,Collectors.groupingBy(VsatLocation::getBeamId)));
				
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
		String jsonStr = gson.toJson(vsatMap);
		//System.out.println(jsonStr);
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
		}
	}
	
	public static void main(String[] args) throws IOException {
		String fileName = args[0];
		fileToJson (fileName);
	}
}