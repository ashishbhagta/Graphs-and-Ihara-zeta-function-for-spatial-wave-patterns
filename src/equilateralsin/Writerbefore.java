package equilateralsin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* This program writes an array to a text file generated by a given function before doing counting.
 * The programs depends on this program generated file is Count,Writerafter,Createimage.So,
 * before running them it is necessary to execute this one .
 * 
 *  In this code inputs are row,m,n.
 *  row stands for size of array
 *  m and n are parameters used in function
 *  
 *  You also have to change the file path according to your system*/

public class Writerbefore
{
	static int row=1000,m=20,n=9;
	
	int i,j,count=0;
    double p=Math.PI,t=Math.sqrt(3),pi=(p/(row+1));
    double mul,a,b,c,d,e,f,xx,yy;
    int aa=2*m,bb=aa-n,cc=2*n,dd=cc-m,ee=-(m+n),ff=m-n;
    int[][] matrix = new int [row][row];
    
    //This method calculates the given function and accordingly store output into an array in the form of 0 and 1. 
    private void sin()
	{
		// TODO Auto-generated method stub
		for (int i =0; i <row; i++)
		{
			for (int j = 0; j <row; j++) 
			{
				xx=((i+1)*pi);// this is used to give range of function in terms of pi
				yy=((j+1)*pi);// this is used to give range of function in terms of pi
				a=Math.sin((bb)*((2*(xx))/3));
				b=Math.sin(((2*(n*(yy)))/t));
				c=Math.sin(((dd*(2*(xx)))/3));
				d=Math.sin((aa*(yy)/t));
				e=Math.sin(((ee*(2*(xx)))/3));
				f=Math.sin(((ff*(2*(yy)))/t));
				
                   mul=(a*b)-(c*d)+(e*f);
				
				if(mul<0)
				{
					matrix[i][j]=0;
				}
				else
				{
					matrix[i][j]=1;
				}
			}
		}
		

	} 
 // This method is used to cut down the equilateral triangle from the matrix generated by sin() method. 
    private void eqltri()
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j <row; j++) 
			{
				xx=(i+1)*pi;// this is used to give range of function in terms of pi
				yy=(j+1)*pi;// this is used to give range of function in terms of pi
				if((xx*t)>yy&& yy<(t*(p-xx)))
				
				{
					matrix[i][j]=matrix[i][j];
				}
				else
				{
				matrix[i][j]=4;	
				}
				
			}
		
		}

	}
    // This method writes the generated matrix to a text file.
	private void write() throws IOException 
	{
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < row; i++)
		{
		for(int j = 0; j < row; j++)
		{
		builder.append(matrix[i][j]+"");//append to the output string
		if(j < row - 1 )//if this is not the last row element
		builder.append(",");// separator used in between elements 
		}
		builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("sin/"+m+","+n+"sinbefore.txt"));// file name for generated file
		writer.write(builder.toString());//save the string representation of the board
		writer.close();

		System.out.println("saved");

	}
	public static void main(String[] args) throws IOException 
	{
		Writerbefore n=new Writerbefore();
		n.sin();
		n.eqltri();
		n.write();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
	
	}

}
