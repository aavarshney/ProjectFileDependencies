package test;

//When you run this program it will wait for input. Please enter an empty line to stop entering input. 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class ProjectFileDependencies {
	static ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
	static LinkedList<Integer> result = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int lineNum = 0;
		int numOfTasks = 0;
		int numOfRules = 0;
		boolean error = false;
		while ((s = in.readLine()) != null && s.length() != 0) {
			if (lineNum == 0) {
				String[] arr = s.split(" ");
				try {
					numOfTasks = Integer.parseInt(arr[0]);
					numOfRules = Integer.parseInt(arr[1]);
				} catch (NumberFormatException ex) {
					System.out.println("Incorrect format of input");
					error = true;
					break;
				}
				for (int i = 0; i < numOfTasks; i++) {
					matrix.add(i, null);
				}
			} else {
				String[] arr = s.split(" ");
				if (arr.length >= 2) {
					int task = Integer.parseInt(arr[0]);
					ArrayList<Integer> dependents = new ArrayList<Integer>();
					try {
						int dependencies = Integer.parseInt(arr[1]);
						if (dependencies != arr.length - 2) {
							error = true;
							System.out
									.println("Incorrect input: Number of dependencies incorrect for task "
											+ task);
						}
						for (int y = 2; y < arr.length; y++) {
							dependents.add(Integer.parseInt(arr[y]));
						}
						matrix.set(task - 1, dependents);
					} catch (NumberFormatException ex) {
						System.out.println("Incorrect format of input");
						error = true;
						break;
					}
				} else {
					System.out.println("Incorrect input");
					error = true;
					break;
				}
			}
			lineNum++;
			if (lineNum > numOfRules) {
				break;
			}
		}
		if (lineNum - 1 != numOfRules) {
			System.out.println("Incorrect number of rules expected: "
					+ numOfRules + " but found: " + (lineNum - 1));
			error = true;
		}
		if (!error) {
			for (int y = 0; y < numOfTasks; y++) {
				if (matrix.get(y) == null) {
					result.addLast(y + 1);
					// System.out.println(result.toString());

				}
			}
			// System.out.println(matrix.toString());
			for (int y = 0; y < numOfTasks; y++) {
				ArrayList<Integer> dependents = matrix.get(y);
				if (dependents != null) {
					int taskNum = y + 1;
					if (!result.contains(taskNum)) {
						visitTask(taskNum);
					}
				}
			}

			for (int y = 0; y < result.size(); y++) {
				System.out.print(result.get(y) + " ");
			}
			// System.out.println(result.toString());
		}
	}

	private static int visitTask(int taskNum) {
		int max = -1;
		ArrayList<Integer> dependents = matrix.get(taskNum - 1);
		if (dependents != null) {
			for (int i = 0; i < dependents.size(); i++) {
				int depn = dependents.get(i);
				if (!result.contains(depn)) {
					int idx = visitTask(depn);
					if (max <= idx) {
						max = idx;
					} else {
						max++;
					}
				} else {
					int idx = result.indexOf(depn);
					if (max <= idx) {
						max = idx;
					}
				}
			}
		}

		if (!result.contains(taskNum)) {
			return addAfter(max, taskNum);
		}
		return -1;
	}

	private static int addAfter(int max, int taskNum) {
		for (int i = max + 1; i < result.size(); i++) {
			if (result.get(i) > taskNum) {
				result.add(i, taskNum);
				// System.out.println(result.toString());
				return i;
			}
		}
		result.add(taskNum);
		// System.out.println(result.toString());
		return result.size() - 1;
	}
}
