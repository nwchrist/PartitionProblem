
public class partitionProblem {

	/* @author Nick Christensen
	 * @date 2/15/2018
	 * 
	 */
	
	/* Challenge: Consider the partition problem: given n positive integers, partition them into
		two disjoint subsets with the same sum of their elements. (Of course, the problem
		does not always have a solution.) Design an exhaustive-search algorithm
		for this problem. Try to minimize the number of subsets the algorithm needs
		to generate.
	*/
	
	/* My solution: In order to solve this problem, our algorithm will first assume that all n integers start out in the second
		subset, and will iterate through the binary numbers from 1 to n. The 1s in the binary number will represent the integers
		from the second subset that will be moved to the first subset, and then the subsets will be checked to see if they are
		equal. If the subsets are equal, the algorithm will print out what integers are in both sets, if they are not equal then
		the algorithm will iterate to the next binary number.
	*/
	
	public static void main(String [] args) {
		
		//This is the set of n positive integers that is passed into the program
		int [] integers = {2, 3, 4, 10, 7, 5, 8, 3, 1};
		
		//Create variable to store number of integers
				int n = integers.length;
		
		//If the integers add to an odd number, we know there can't be an equal split
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += integers[i];
		}
		if ((sum % 2) == 1) {
			System.out.println("There are no possible combinations of subsets of these integers (odd sum of integers), why have you forsaken me?");
			System.exit(0);
		}
		
		//Array to store the binary number
		int [] binaryArray = new int [n];
		
		//Initialize the binary array to be all 0s
		for (int i = 0; i < n; i++) {
			binaryArray[i] = 0;
		}
		
		int max = 2;
		//quickly calculate 2^n
		for (int i = 0; i < n - 1; i++) {
			max = max * 2;
		}
	
		//Iterate through the binary numbers from 1 to n
		for (int decimalCounter = 1; decimalCounter < max; decimalCounter++) {
			
			//Convert our decimal number to a binary string
			String binaryString = Integer.toBinaryString(decimalCounter);
			
			//Convert our binary string to a binary array
			for (int i = 0; i < binaryString.length(); i++) {
				int j = n - 1;
				binaryArray[j + (i - binaryString.length()) + 1] = Character.getNumericValue(binaryString.charAt(i));
			}
			
			int sumA = 0;
			int sumB = 0;
			
			//Now we need to check if the sums are equal
			for (int i = binaryArray.length - 1; i >= 0; i--) {
				if (binaryArray[i] == 0) {
					sumB = sumB + integers[i];
				} else {
					sumA = sumA + integers[i];
				}
			}
			
			if (sumB == sumA) {
				System.out.println("The Sums Are equal:\n Set A Included:");
				for (int i = 0; i < n; i++) {
					if (binaryArray[i] == 1) {
						System.out.print(integers[i] + " ");
					}
				}
				
				System.out.println("\n Set B Included: ");
				for (int i = 0; i < n; i++) {
					if (binaryArray[i] == 0) {
						System.out.print(integers[i] + " ");
					}
				}
				
				System.exit(0);
			}
		}
		
		System.out.println("There are no possible combinations of subsets of these integers, why have you forsaken me?");
		System.exit(0);
	}
	
}
