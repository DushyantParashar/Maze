package Rate_in_the_maze;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Maze {
	public static char[][] maze_generation(int n)
	{
		System.out.println("Generated Maze");
		char[][] maze=new char[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
			maze[i][j]='◌';
			//System.out.print(maze[i][j]+" ");
			}
			//System.out.println();
		}
		// placing walls
		int maxWall=(int)(0.25*n*n);// it will put maximum 25% of walls into the matrix
		Random ram=new Random();// Random is the class, which is present in the java.util package
		
		int wall=0;// initially no. of walls is 0
		
		while(wall<maxWall)// we are putting the wall of the basis of given condition
		{
			int ramRow=ram.nextInt(n);
			int ramCol=ram.nextInt(n);
			
			if(maze[ramRow][ramCol]=='◌')
			{
				maze[ramRow][ramCol]='▓';
				wall++;
			}
		}
		// now we are setting starting and end point
		maze[0][0]='S';
		maze[n-1][n-1]='E';
		
		return maze;
		
	}// maze_generation ends here
	
	
	
	
	
	// printing function will print the maze matrix after putting random walls on the basis of probability of 25% or less walls
	public static void printMaze(char[][] maze)
	{
	    for (int i = 0; i < maze.length; i++)
	    {
	        for (int j = 0; j < maze[i].length; j++)
	        {
	            System.out.print(maze[i][j] + " ");
	        }
	        System.out.println();  // Move to the next line after printing each row
	    }
	}// printmaze function ends here
	
	
	
	
	public static boolean findingPath(char[][] maze) {
        // Find the starting point ('S') coordinates
		int n = maze.length;

	    // Find the starting point ('S') coordinates
	    int startRow = -1, startCol = -1;
	    
	    for (int i = 0; i < n; i++) {//  this loop is to find the starting point
	        for (int j = 0; j < n; j++) {
	            if (maze[i][j] == 'S') {// to find out the starting point, until not found we iterate the matrix and once it found we break the loop
	                startRow = i;
	                startCol = j;
	                break;
	            }
	        }
	        if (startRow != -1) {
	            break;
	        }
	    }
	    
		 Stack<int[]> stack = new Stack<>();
		    stack.push(new int[]{startRow, startCol});

		    while (!stack.isEmpty()) {
		        int[] current = stack.pop();
		        int i = current[0];
		        int j = current[1];
		        if (i < 0 || i >= n || j < 0 || j >= n || maze[i][j] == '▓' || maze[i][j] == 'V') {
		            continue;
		        }
		        if (maze[i][j] == 'E') {
		            return true;
		        }

		        // Mark the cell as visited
		        maze[i][j] = 'V';

		        // Add neighbors to the stack
		        stack.push(new int[]{i - 1, j});
		        stack.push(new int[]{i + 1, j});
		        stack.push(new int[]{i, j - 1});
		        stack.push(new int[]{i, j + 1});
		    }

		    return false;
		
    }
	public static void markpath(char[][]maze,int row,int col )
	{
		for(int i=0;i<maze.length;i++)
		{
			for(int j=0;j<maze[i].length;j++)
			{
				if(maze[i][j]=='V'&&!(i == row && j == col))  {
					maze[i][j]='◍';
				}
			}
		}
	}
	
	
	public static void main(String[] args) 
	{	Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of the maze (nXn):");
		
		int n=sc.nextInt();
		
		char[][] maze=maze_generation(n);
	      printMaze(maze);
	      
	      
		
		while(true)
		{
			System.out.println("1. print the path");
			System.out.println("2. Generate another puzzle");
			System.out.println("3. Exit the game");
			System.out.println("Enter your choice 1/2/3: ");
			
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				if (findingPath(maze)) {
					markpath(maze,0,0);
                    System.out.println("Path found:");
                    printMaze(maze);
                } else {
                    System.out.println("No path found.");
                }
                break;
				
				
			case 2:
				System.out.println("Enter the size of the new maze (nXn):");
				n = sc.nextInt(); // get a new size
				maze=maze_generation(n);
				printMaze(maze);
				
				break;
				
			case 3:
				System.exit(0);
				default:
					System.out.println("Invalid choice");
					
			}
			
		}
	}// main function ends here

}
