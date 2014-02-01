package zl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Retrieve {

	static int order = 1;

	static String getEssence(String initial, int length) {

		// Remove all non alphabetical symbols but one space
		initial = initial.replaceAll("[^A-Za-z ]", "");
		initial = initial.trim().replaceAll(" +", " ");
		System.out.println("Removed all the non alphabetical symbols.\n");

		// Check length
		if (length > initial.length()) {
			try {
				throw new Exception("Initial string: '" + initial
						+ "' of length " + initial.length()
						+ " is less then expected length: '" + length + "'\n");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		}

		// Split by space
		String[] initialArray = initial.split(" ");
		System.out.println("Splitted initial string into array.\n");

		ArrayList<String> finalArray = new ArrayList<String>();

		// Remove duplicates and non alphabetical chars
		for (int i = 0; i < initialArray.length; i++) {
			String current = initialArray[i];
			if (!finalArray.contains(current)) {
				finalArray.add(current);
			}
		}
		System.out.println("Removed duplicates.\n");

		// Sorting final array
		Collections.sort(finalArray, new CustomComparator());
		System.out.println("Sorted final array by size.\n");

		String finalString = "";
		int finalStringSize = 0;
		ArrayList<String> list_2 = new ArrayList<String>();
		
		for (int i = 0; i < finalArray.size(); i++) {
			String current = finalArray.get(i);
			int currentSize = current.length();

			// Check that current final string is less then expected length and
			if (length < finalStringSize + currentSize) {
				System.out.println("Current array member '" + current
						+ "' makes a final string '" + finalString.trim()
						+ "' greater then " + length + ". Skipping...");
				continue;
			}

			// Check that array members left are not better then curren
			String leftString = getFromPosition(finalArray, i + 1);
			int itemsLeftSize = leftString.length();
			if (length > finalStringSize + itemsLeftSize) {
				if (currentSize > itemsLeftSize + finalArray.size() - i - 1) {
					System.out
							.println("Current array member '"
									+ currentSize
									+ "' of size '"
									+ length
									+ "' is less suitable to use then next combinations of length "
									+ itemsLeftSize + ". Skipping...");
					continue;
				}
			}

			// if (!check(finalArray, length, currentSize, i + 1)) {
			// System.out
			// .println("Current array member '"
			// + currentSize
			// + "' of size '"
			// + length
			// + "' is less suitable to use then next combinations of length "
			// + itemsLeftSize + ". Skipping...");
			// continue;
			// }

			if (length == finalStringSize + itemsLeftSize) {
				return finalString + leftString;
			}

			finalString = finalString + current;
			if (i < finalArray.size() - 1) {
				finalString = finalString + " ";
			}
			finalStringSize = finalString.length();

		}

		return finalString.trim();
	}

	public static boolean check(ArrayList<String> list, int length,
			int currentSize, int position) {
		String finalString = "";

		String leftString = getFromPosition(list, position);
		int itemsLeftSize = leftString.length();

		ArrayList<String> list_2 = getArrayFromPosition(list, position);

		order = 1;
		for (int i = position; i < list.size(); i++) {
			String current = list.get(i);
			finalString = finalString + current;
			if (i < list.size() - 1) {
				finalString = finalString + " ";
			}
		}

		return false;
	}

	public static ArrayList<String> getArrayFromPosition(
			ArrayList<String> list, int position) {
		ArrayList<String> list_2 = new ArrayList<String>();

		for (int i = position; i < list.size(); i++) {
			String current = list.get(i);
			list_2.add(current);
		}
		return list_2;
	}

	public static ArrayList<String> deleteFromArray(ArrayList<String> list,
			String member) {
		ArrayList<String> list_2 = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			String current = list.get(i);
			if (current.length() != member.length()) {
				list_2.add(current);
			}
		}

		return list_2;
	}

	public static String getFromPosition(ArrayList<String> list, int position) {
		String finalString = "";

		for (int i = position; i < list.size(); i++) {
			String current = list.get(i);
			finalString = finalString + current;
			if (i < list.size() - 1) {
				finalString = finalString + " ";
			}
		}

		return finalString;
	}

	public static boolean isMemberOfLengthExist(ArrayList<String> list,
			String member) {

		for (int i = 0; i < list.size(); i++) {
			String current = list.get(i);
			if (current.length() == member.length()) {
				return true;
			}
		}

		return false;
	}

	private static class CustomComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return order * (o1.length() - o2.length());
		}
	}

}
