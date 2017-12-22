package equilateralcos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Area 
{
	int m=8,n=3,row=2000,domain=7;
	
	String savedarray = "cos/"+m+","+n+"cosafter.txt";
	int[][] label= new int [row][row];
	double[][] area=new double [domain+1][1];
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
	private void operation()
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < row; j++) 
			{
				int a=label[i][j];
				area[a][0]=area[a][0]+1;
			}
		}

	}
	private void write() throws IOException 
	{
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i <=domain; i++)
		{//builder.append("{");
		for(int j = 0; j <1; j++)
		{
		builder.append(i+" "+(area[i][j]/(row*row))+"");//append to the output string
		if(j < 2)//if this is not the last row element
		builder.append(" ");//Separator of elements
		}
		builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("cos/"+m+","+n+"cosarea.txt"));
		writer.write(builder.toString());//save the string representation of the board
		writer.close();
		System.out.println("saved");
	}
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		
		// TODO Auto-generated method stub
		Area n=new Area();
		n.read();
		n.operation();
		n.write();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	}

}