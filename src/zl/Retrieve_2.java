package zl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Retrieve_2 {

	static int order = -1;

	static String getEssence(String initial, int length) {

		if (length <= 0) {
			return "";
		}

		// Remove all non alphabetical symbols but one space
		initial = initial.replaceAll("[^A-Za-z ]", "");
		initial = initial.trim().replaceAll(" +", " ");
		System.out.println("Removed all the non alphabetical symbols.\n");

		// Split by space
		String[] initialArray = initial.split(" ");
		System.out.println("Splitted initial string into array.\n");

		ArrayList<String> finalArray = new ArrayList<String>();

		// Remove duplicates if initial string is less then required
		for (int i = 0; i < initialArray.length; i++) {
			String current = initialArray[i];
			if (!finalArray.contains(current) || initial.length() < length) {
				finalArray.add(current);
			}
		}
		System.out.println("Removed duplicates.\n");

		// Sorting final array
		Collections.sort(finalArray, new CustomComparator());
		System.out.println("Sorted final array by size.\n");

		ArrayList<String> list_1 = new ArrayList<String>();
		String currentFinalString = "";

		for (int i = 0; i < finalArray.size(); i++) {
			String current = finalArray.get(i);
			currentFinalString = createExpectedString(list_1);
			int finalStringSize = currentFinalString.length();

			// Check that current final string is less then expected length and
			if (finalStringSize > length) {
				int difference = finalStringSize - length;
				// remove previous member from array
				// and check again

				Collections.sort(finalArray, new CustomComparator());
				ArrayList<String> list_2 = getArrayFromPosition(finalArray,
						i - 1);

				Collections.sort(list_1, new CustomComparator());
				list_1 = getArrayTillPosition(list_1, i);
				list_1.set(list_1.size() - 1, "");
				list_2.add("");

				for (int j = 0; j < list_1.size(); j++) {
					for (int j2 = 0; j2 < list_2.size(); j2++) {
						ArrayList<String> list11 = getArrayFromPosition(list_1,
								0);
						list11.set(j, list_2.get(j2));
						Collections.sort(list11, new CustomComparator());
						String result = createExpectedString(list11).trim();
						if (length - result.length() < difference
								&& result.length() < length) {
							System.out.println("Current string: '" + result
									+ "' of length: " + result.length()
									+ ", diff: " + difference + "\n");
							currentFinalString = result;
						}
						System.out.println(result.length() == length);
						System.out.println(String.valueOf(result.length())
								+ " : " + String.valueOf(length)
								+ "Comparing result.length() == length ");
						if (result.length() == length) {
							System.out.println(result);
							return result;
						}
					}
				}

				for (int j = 0; j < list_1.size(); j++) {
					for (int j2 = 0; j2 < list_2.size(); j2++) {
						ArrayList<String> list22 = getArrayFromPosition(list_2,
								0);
						list22.set(j2, list_1.get(j));
						Collections.sort(list22, new CustomComparator());
						String result = createExpectedString(list22).trim();
						if (length - result.length() < difference) {
							System.out.println("Current string: '" + result
									+ "' of length: " + result.length()
									+ ", diff: " + difference + "\n");
							currentFinalString = result;
						}
						System.out.println(result.length() == length);
						System.out.println(String.valueOf(result.length())
								+ " : " + String.valueOf(length)
								+ "Comparing result.length() == length ");
						if (result.length() == length) {
							System.out.println(result);
							return result;
						}
					}
				}

			}

			list_1.add(current);

		}
		if (initial.length() < length) {
			currentFinalString = createExpectedString(list_1);
		}
		return currentFinalString.trim();
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

	public static ArrayList<String> getArrayTillPosition(
			ArrayList<String> list, int position) {
		ArrayList<String> list_2 = new ArrayList<String>();

		for (int i = 0; i < position; i++) {
			String current = list.get(i);
			list_2.add(current);
		}
		return list_2;
	}

	public static String createExpectedString(ArrayList<String> list) {
		return getFromPosition(list, 0);
	}

	public static String createExpectedStringTillPosition(
			ArrayList<String> list, int position) {
		String finalString = "";

		for (int i = 0; i < position; i++) {
			String current = list.get(i);
			finalString = finalString + current;
			if (i < position - 1) {
				finalString = finalString + " ";
			}
		}

		return finalString;
	}

	public static String getFromPosition(ArrayList<String> list, int position) {
		String finalString = "";

		for (int i = position; i < list.size(); i++) {
			String current = list.get(i);
			finalString = finalString + current;
			if (i < list.size() - 1 && current != "") {
				finalString = finalString + " ";
			}
		}

		return finalString;
	}

	private static class CustomComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return order * (o1.length() - o2.length());
		}
	}

}
