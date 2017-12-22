package square;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Degreematrix 
{
	int row=1000,domain=60,m=15,n=4;
	
	String savedarray = "square/"+m+","+n+"squareafter.txt";	
	int[][] label = new int[row][row];
	int[][]adj=new int [domain+1][domain+1];
	int[][]degree=new int [domain+1][domain+1];
	
	private void read() throws NumberFormatException, IOException
	{
		// TODO Auto-generated method stub
		
		BufferedReader reader = new BufferedReader(new FileReader(savedarray));
		String line = "";
		int row = 0;
		while((line = reader.readLine()) != null)
		{
		   String[] cols = line.split(","); 
		   int col = 0;
		   for(String  c : cols)
		   {
		      label[row][col] = Integer.parseInt(c);
		      col++;
		   }
		   row++;
		}
		reader.close();

	}
	//The operation of filling adjacency matrix starts here
			private void operation()
			{
				// TODO Auto-generated method stub
				for (int i = 0; i <row; i++)
				{
					for (int j = 0; j < row; j++)
					{ 
						if (i==0 &&j==0) // if it is first element of array
						{
							
						}
						else if(label[i][j]!=0) // if label is not 0
						{
						    if (j==0 && i!=0)// condition for only above elements
							{
								above(i,j);
							}
							else if (i==0 && j!=0)// condition for only left elements
							{
								left(i,j);
							}
							else
							{
								aboveleft(i,j);
							}
						}}
						
					}
					
			}
			private void aboveleft(int i, int j)
			{
				// TODO Auto-generated method stub
				if (label[i][j]==label[i][j-1] && label[i][j]==label[i-1][j])// if above and left element is same as current
				{
					adj[label[i][j]][label[i][j]]=0;// fill 0 at positions according to label
				}
				else if (label[i][j]!=label[i-1][j])//if above element is not same as current
				{
					adj[label[i-1][j]][label[i][j]]=1;// put 1 at positions according
					adj[label[i][j]][label[i-1][j]]=1;// to label.
				}
				else//if left element is not same as current
				{
					adj[label[i][j-1]][label[i][j]]=1;// put 1 at positions according
					adj[label[i][j]][label[i][j-1]]=1;// to label.
				
				}
			}
			private void left(int i, int j)
			{
				// TODO Auto-generated method stub
				if (label[i][j-1]==label[i][j]) //if left element is same as current
				{
					adj[label[i][j]][label[i][j]]=0;// fill 0 at positions according to label
				}
				else
				{
					adj[label[i][j-1]][label[i][j]]=1;// put 1 at positions according
					adj[label[i][j]][label[i][j-1]]=1;// to label.
				}
				
			}
			private void above(int i, int j) 
			{
				// TODO Auto-generated method stub
				if (label[i][j]==label[i-1][j])//if above element is same as current
				{
					adj[label[i][j]][label[i][j]]=0;// fill 0 at positions according to label
				} 
				else 
				{
					adj[label[i-1][j]][label[i][j]]=1;// put 1 at positions according
					adj[label[i][j]][label[i-1][j]]=1;// to label.
				}
				
			}
			// this method generates degree matrix from adjacency matrix
		private void degree() 
		{
			// TODO Auto-generated method stub
			for (int i = 1; i <= domain; i++)
			{
				for (int j = 1; j <=domain; j++) 
				{
					if (adj[i][j]==1)// adjacency matrix has 1
					{
						degree[i][i]=degree[i][i]+1;// add 1 at same place in degree matrix
					}
				}			
			}
		}
	private void write() throws IOException 
	{
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();

		for(int i = 1; i <=domain; i++)//for each row
		{//builder.append("{");
		for(int j = 1; j <=domain; j++)//for each column
		{
			
		builder.append(degree[i][j]+"");//append to the output string
		if(j < domain)//if this is not the last row element
		builder.append(" ");//then add comma (if you don't like commas you can use spaces)
		}
		builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("square/"+m+","+n+"squaredeg.txt"));
		writer.write(builder.toString());//save the string representation of the board
		writer.close();
		System.out.println("saved");
	}
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		// TODO Auto-generated method stub
		Degreematrix n=new Degreematrix();
		n.read();
		n.operation();
		n.degree();
		n.write();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	}

}
