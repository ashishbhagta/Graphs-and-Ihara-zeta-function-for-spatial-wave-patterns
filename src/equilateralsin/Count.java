package equilateralsin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* This program count the number of domains present in the system and gives us the count.
 * This is based on hoshen-kopelman algorithm.
 * 
 *  In this code inputs are row,m,n.
 *  row stands for size of array
 *  m and n are parameters used in function.
 *  
 *  This code depends on Writerbefore so execute that before executing this one */

public class Count 
{
		int row=1000,m=30,n=13;
		
		String savedarray = "sin/"+m+","+n+"sinbefore.txt";
		int count=0,regcount=0,i,j;
		 double p=Math.PI,t=Math.sqrt(3),pi=(p/(row+1));
	    double mul,a,b,c,d,e,f,xx,yy;
	    int[][] label = new int [row][row];
	  int[][] matrix = new int [row][row];
	   
	  // This function reads the file saved by Writerbefore.
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
			      matrix[row][col] = Integer.parseInt(c);
			      col++;
			   }
			   row++;
			}
			reader.close();

		}
	// The counting starts in this method.
		private void operation()
		{
			// TODO Auto-generated method stub
			for (int i = 0; i < row; i++)
			{
				for (int j = 0; j <row; j++) 
				{
					 xx=(i+1)*pi;
				     yy=(j+1)*pi;
				if((xx*t)>yy&& yy<(t*(p-xx)))// Condition to work on only equilateral traingle elements.
				{
					if(i==0 && j==0)//1st element of the array
					{
						count=count+1;regcount=regcount+1;
						label[i][j]=count;
					}
					else if (((i-1)>=0) && j==0)  // Condition for above elements only.
					{
					    above(i,j);	
					    
					}
					else if (((j-1)>=0)&& i==0)// Condition for left elements only.
					{
						left(i,j);
					
					}
					else //If both is there above as well as left.
					{
					aboveleft(i,j);	
				
					}
				}
				
				}
			}
			
			
			

		}
		// This function tackles the element of 1st column only
		private void above(int a,int b)
		{
			// TODO Auto-generated method stub
			if(matrix[a-1][b]==matrix[a][b])// If number is same as above.
			{
				label[a][b]=label[a-1][b];// Copy the number.
			}
			else
			{
				count=count+1;regcount=regcount+1;//assign new count
				label[a][b]=count;
			}

		}
		// this function tackles the element of 1st row only
		private void left(int a,int b) 
		{
			// TODO Auto-generated method stub
	          if (matrix[a][b-1]==matrix[a][b])// If number is same as left.
	          {
	        	  label[a][b]=label[a][b-1];// Copy the number.
	          }
	          else
	          {
	        	count=count+1;regcount=regcount+1;//assign new count
	  			label[a][b]=count;
	          }
		}
		// this function tackles the all remaining elements 
		private void aboveleft(int a,int b)
		{
			// TODO Auto-generated method stub
			if (matrix[a][b-1]==matrix[a][b] && matrix[a-1][b]!=matrix[a][b])//If left element is same but above is different.
			{
				label[a][b]=label[a][b-1];// copy label of left to new.
			}
			else if (matrix[a-1][b]==matrix[a][b] && matrix[a][b-1]!=matrix[a][b])// If above is same but left is different.
			{
				label[a][b]=label[a-1][b];//Copy label of above to new.
			}
			else if (matrix[a][b-1]!=matrix[a][b] && matrix[a-1][b]!=matrix[a][b])//If none is same neither above nor left.
			{
				count=count+1;regcount=regcount+1;// Assign new count
				label[a][b]=count;
			}
			else//If element of above and left is same.
			{
				checklabel(a, b);
			}

		}
		// this function works when above and left element of a element are same
		private void checklabel(int a, int b)
		{
			// TODO Auto-generated method stub

			if(label[a-1][b]>label[a][b-1])//If above label is greater than left.
			{
				label[a][b]=label[a][b-1];//copy label of left to current
				int neww=label[a][b-1];//copy left label into neww
				int old=label[a-1][b];//copy above label into old
				changelabel(old,neww,a);
				regcount=regcount-1;
			
			}
			else if (label[a-1][b]==label[a][b-1])//if above label is equal to left label
			{
				label[a][b]=label[a-1][b];//copy above to current
				
			}
			else // if left > above
			{
				label[a][b]=label[a-1][b];//copy label of above to current
				int neww=label[a-1][b];//copy above label into neww
				int old=label[a][b-1];//copy left label into old
				changelabel(old,neww,a);
				regcount=regcount-1;
				
			}

		}
		// this function ensures accuracy by checking while a new labeling all get labeled according to new or not
		private void changelabel(int ol,int nw,int end)
		{
			// TODO Auto-generated method stub
			for (int i = 0; i <=end; i++) 
			{
				for (int j = 0; j < row; j++)
				{
				   	if (label[i][j]==ol)// if any label has old label
				   	{
				   		
						label[i][j]=nw;// change it to new label
					}
				}
				
		    }
		}	
		// this method gives us the output of domains count.
		private void output() 
		{
			System.out.println(regcount);
		}
	public static void main(String[] args) throws NumberFormatException, IOException 
	{    
		Count a=new Count();
		a.read();
		a.operation();
	   a.output();
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	}
	
}
