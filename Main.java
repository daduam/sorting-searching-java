import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This program gets an array of numbers from a user and either searches the
 * array for a target element or sorts the array using searching or sorting
 * algorithm of the user's choice
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        promptLn("Choose the operation you want to perform:");
        promptLn("\t1. Searching");
        promptLn("\t2. Sorting");

        prompt("Enter an option: ");
        int operation = scanner.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        switch (operation) {
            case 1:
                promptLn("\nSEARCHING");
                arr = getNumbers();
                prompt("Enter number to search: ");
                int target = scanner.nextInt();

                // ask user to choose search algorithm
                promptLn("Choose the searching algorithm to use:");
                promptLn("\t1. Linear search");
                promptLn("\t2. Binary search");
                prompt("Enter an option: ");
                int searchOption = scanner.nextInt();
                int pos = -1;
                switch (searchOption) {
                    case 1:
                        pos = linearSearch(arr, target);
                        break;
                    case 2:
                        Collections.sort(arr);
                        pos = binarySearch(arr, target);
                        break;
                    default:
                        promptLn("ERROR: You entered a wrong option.");
                        return;
                }
                if (pos == -1) {
                    promptLn("" + target + " was not found in array.");
                } else {
                    promptLn("" + target + " was found in the array.");
                }
                break;
            case 2:
                promptLn("\nSORTING");
                arr = getNumbers();
                // ask user for order ASC/DESC
                promptLn("Choose the sorting algorithm to use:");
                promptLn("\t1. Bubble sort");
                promptLn("\t2. Merge sort");
                promptLn("\t3. Insertion sort");
                prompt("Enter an option: ");
                int sortOption = scanner.nextInt();

                ArrayList<Integer> sortedArray = new ArrayList<>();
                switch (sortOption) {
                    case 1:
                        sortedArray = bubbleSort(arr);
                        break;
                    case 2:
                        sortedArray = new ArrayList<>(arr);
                        mergeSort(sortedArray, 0, sortedArray.size() - 1);
                        break;
                    case 3:
                        sortedArray = insertionSort(arr);
                        break;
                    default:
                        promptLn("ERROR: You entered a wrong option.");
                        return;
                }
                prompt("The sorted array is:");
                printArray(sortedArray);
                break;
            default:
                promptLn("ERROR: You entered a wrong option.");
                return;
        }
    }

    /**
     * Reads an array of numbers from the command line.
     * 
     * @return a list of numbers
     */
    public static ArrayList<Integer> getNumbers() {
        prompt("Enter the size of the array: ");
        int count = scanner.nextInt();
        promptLn("Enter " + count + " numbers");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            prompt("Enter number: ");
            int num = scanner.nextInt();
            numbers.add(num);
        }
        return numbers;
    }

    /**
     * Prints msg followed by a newline character to stdin.
     * 
     * @param msg
     */
    public static void promptLn(String msg) {
        System.out.println(msg);
    }

    /**
     * Print msg without a newline character to stdin.
     * 
     * @param msg
     */
    public static void prompt(String msg) {
        System.out.print(msg);
    }

    /**
     * Prints the elements of arr separated by spaces.
     * 
     * @param arr
     */
    public static void printArray(ArrayList<Integer> arr) {
        for (Integer num : arr) {
            prompt(" " + num);
        }
        promptLn("");
    }

    /**
     * Performs a linear search over arr for the target element.
     * 
     * Complexity: O(n)
     * 
     * @param arr
     * @param target
     * @return index of target in arr else -1 if
     */
    public static int linearSearch(ArrayList<Integer> arr, int target) {
        int pos = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == target) {
                pos = i;
            }
        }
        return pos;
    }

    /**
     * Performs a binary search over arr for target
     * 
     * Complexity: O(log n)
     * 
     * @param arr
     * @param target
     * @return index of target if found in arr else -1
     */
    public static int binarySearch(ArrayList<Integer> arr, int target) {
        int pos = -1;
        int left = 0;
        int right = arr.size() - 1;

        while (right > left + 1) {
            int mid = (left + right) / 2;
            if (arr.get(mid) > target) {
                right = mid;
            } else if (arr.get(mid) < target) {
                left = mid;
            } else {
                pos = mid;
                break;
            }
        }

        return pos;
    }

    /**
     * Sorts arr using bubble sort
     * 
     * Complexity: O(n^2)
     * 
     * @param arr
     * @return arr sorted in ascending order
     */
    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
        ArrayList<Integer> sortedArray = new ArrayList<>(arr);

        int n = sortedArray.size();
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (sortedArray.get(j) < sortedArray.get(j - 1)) {
                    Collections.swap(sortedArray, j, j - 1);
                }
            }
        }

        return sortedArray;
    }

    /**
     * Sorts arr using insertion sort
     * 
     * Complexity: O(n^2)
     * 
     * @param arr
     * @return arr sorted in ascending order
     */
    public static ArrayList<Integer> insertionSort(ArrayList<Integer> arr) {
        ArrayList<Integer> sortedArray = new ArrayList<>(arr);

        int n = sortedArray.size();
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (sortedArray.get(i) > sortedArray.get(i + 1)) {
                    Collections.swap(sortedArray, i, i + 1);
                }
            }
        }

        return sortedArray;
    }

    /**
     * Sorts arr in-place using merge sort
     * 
     * Complexity: O(n log n)
     * 
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(ArrayList<Integer> arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges the two subarrays arr[left..mid] and arr[mid..right]
     * 
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(ArrayList<Integer> arr, int left, int mid, int right) {
        int nLeftSubArray = mid - left + 1;
        ArrayList<Integer> leftSubArray = new ArrayList<>();
        for (int i = 0; i < nLeftSubArray; i++) {
            leftSubArray.add(arr.get(left + i));
        }

        int nRightSubArray = right - mid;
        ArrayList<Integer> rightSubArray = new ArrayList<>();
        for (int i = 0; i < nRightSubArray; i++) {
            rightSubArray.add(arr.get(mid + 1 + i));
        }

        int leftIdx = 0, rightIdx = 0, mergedIdx = left;
        while (leftIdx < nLeftSubArray && rightIdx < nRightSubArray) {
            if (leftSubArray.get(leftIdx) <= rightSubArray.get(rightIdx)) {
                arr.set(mergedIdx, leftSubArray.get(leftIdx));
                leftIdx++;
            } else {
                arr.set(mergedIdx, rightSubArray.get(rightIdx));
                rightIdx++;
            }
            mergedIdx++;
        }

        while (leftIdx < nLeftSubArray) {
            arr.set(mergedIdx, leftSubArray.get(leftIdx));
            leftIdx++;
            mergedIdx++;
        }

        while (rightIdx < nRightSubArray) {
            arr.set(mergedIdx, rightSubArray.get(rightIdx));
            rightIdx++;
            mergedIdx++;
        }
    }

}