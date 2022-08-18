/*
THIS CODE WAS MY OWN WORK , IT WAS WRITTEN WITHOUT CONSULTING ANY
SOURCES OUTSIDE OF THOSE APPROVED BY THE INSTRUCTOR. HyungSuk Lee
*/
import java.util.Stack;

public class NQueens {
 
  private static boolean isValid(int col, Stack<Integer> s) {//this method is cheking the current position is valid. If it's valid, it returns true.
		
		if(s.isEmpty()){//if the stack is empty, the current queen can be on any position.
      return true;
    }

		int j = s.size(); //the size of the stack indicates the current queen's row.

		for (int i = 0; i < j; i++) {
			int prevCol = s.get(i); //this shows us what the saved data for the previous queen is, and it represents the column of the previous queen.
			if (col == prevCol) // if the current queen on same column as the previous queen, it is not valid position so return false
				return false;
			else if (j - i == Math.abs(col - prevCol)) //j and i represents the rows of current queen and the previous queen. If j - i equals to col - prevCol, two queens are placed diagonally to each other.
				return false;                            //Using Math.abs to get an absolute value of col - prevCol so that both the right and left diagonals can be identified at once.
		}

		return true; //We only place one queen per row, so the current queen position is valid unless two different queens are placed diagonally or in the same column. (Don't need to check if those are in the same row)

	}
  //finds and prints out all solutions to the n-queens problem

  public static int solve(int n) {

    //***** fill in your code here *****
    Stack<Integer> s = new Stack<Integer>();
    int numSol = 0;
		int col = 0;

		while (true) {

			if (col < n && isValid(col, s)) { //if column is less than n and is valid position, we save the value in the stack. Then move to the next row with the first column.
				s.push(col);
				col = 0;
			} else if (col >= n) { //if there is no valid position in this row, remove the previous queen's position from the stack and move it to the next column.
				if (s.isEmpty()) //if the stack is empty, it indicates that there is no valid position in the first row, which means there is no solution. Therefore, we stop the loop.
					break;
				col = s.pop() + 1;

			} else
				col++;

			if (s.size() == n) { //if the size of the stack equals n, it means that we reach to the last row and we found a solution.
				printSolution(s);
				numSol++;
				col = s.pop() + 1;
			}
		}

		return numSol;
    
  }//solve()
  
  //this method prints out a solution from the current stack
  //(you should not need to modify this method)
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }//for
      System.out.println();
    }//for
    System.out.println();  
  }//printSolution()
  
  // ----- the main method -----
  // (you shouldn't need to change this method)
  public static void main(String[] args) {
  
  int n = 8;
  
  // pass in parameter n from command line
  if (args.length == 1) {
    n = Integer.parseInt(args[0].trim());
    if (n < 1) {
      System.out.println("Incorrect parameter");
      System.exit(-1);
    }//if   
  }//if
  
  int number = solve(n);
  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 }//main()
  
}