package squarerandom;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* This program generates adjacency matrix of the nodal pattern
 * 
 * In this code inputs are row,m,n and domain.
 *  row stands for size of array
 *  m and n are parameters used in function.
 *  domains is number of domains in the pattern
 *  
 *  This code depends on Writerafter so execute that before executing this one
 *  
 *   You also have to change the file path according to your system*/

public class Adjmatrix 
{
	int row=50,domain=636,m=2,n=1;
	
	String savedarray = "sqrrnd/"+m+","+n+"sqrrndaftr.txt";	
	int[][] label = new int[row][row];
	int[][]adj=new int [domain+1][domain+1];
	
	  // This function reads the file saved by Writerafter.
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
				if (i==0 &&j==0)// if it is first element of array
				{
					
				}
				else if(label[i][j]!=0)
				{
				    if (j==0 && i!=0)
					{
						above(i,j);
					}
					else if (i==0 && j!=0)
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
		if (label[i][j]==label[i][j-1]  && label[i][j]==label[i-1][j])// if above and left element is same as current
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
	// this function writes the adjacency matrix to a text file in mathematica readable format
	private void write() throws IOException 
	{
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();

		for(int i = 1; i <=domain; i++)
		{//builder.append("{");
		for(int j = 1; j <=domain; j++)
		{
			
		builder.append(adj[i][j]+"");//append to the output string
		if(j < domain)//if this is not the last row element
		builder.append(" ");//Separator of elements
		}
		builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("sqrrnd/"+m+","+n+"sqrrndadj.txt"));
		writer.write(builder.toString());//save the string representation of the board
		writer.close();
		System.out.println("saved");
	}
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		// TODO Auto-generated method stub
		Adjmatrix n=new Adjmatrix();
		n.read();
		n.operation();
		//n.outputadj();
		n.write();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));

	}

}