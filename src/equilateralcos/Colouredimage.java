package equilateralcos;



import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

/*This program generates an image of pattern and to store it in a file.
 * 
 *  In this code inputs are row,m,n.
 *  row stands for size of array
 *  m and n are parameters used in function.
 *  
 *  This code depends on Writerbefore so execute that before executing this one
 *  
 *   You also have to change the file path according to your system
 *   
 *   you can also change the colors and format of image*/
public class Colouredimage extends Applet
{
	int row =2000,m=17,n=3,domains=31;
	
	String savedarray = "E:/project/Nodaldomains/cos/"+m+","+n+"cosafter.txt";
	String imageformat="png",path="E:/Graphs/equitri/";
    double p=Math.PI,xx,yy,t=Math.sqrt(3);
    double pi=(p/(row+1));
	 int[][] matrix = new int [row][row];
	  private final GraphicsConfiguration gConfig = GraphicsEnvironment
	      .getLocalGraphicsEnvironment().getDefaultScreenDevice()
	      .getDefaultConfiguration();
//*************************************************************
	
	  // this function almost works as mains in this code
public void paint(Graphics g)
	{
		try {
			read();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		storeImage();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
	}
//*******************************************

//This function reads the file saved by Writerbefore.
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
// this code draw image
	void drawimage(Graphics g)
	{
Graphics2D g2=(Graphics2D) g;
Random rand = new Random();

		for (int k = 1; k <=domains; k++) 
		{
			int rgb = rand.nextInt(256);
			int grb = rand.nextInt(256);
			int brg = rand.nextInt(256);
		for (int i = 0; i < row; i++)
		{
			for (int j = 0; j < row; j++) 
			{
				xx=(i+1)*pi;
				yy=(j+1)*pi;
				if((xx*t)>yy&& yy<(t*(p-xx)))
				{
				if (i>0)
				{
					if (matrix[i][j]==k)
					{
						Color myorange=new Color(rgb,grb,brg);
						g2.setColor(myorange);
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.draw(new Rectangle2D.Double(i,j,1,1));
					}
				}}
				}}}}

// this method store generated image in desired format
	 public void storeImage()
	  {
	    BufferedImage image = create(row, row, true);
	    Graphics2D g = image.createGraphics();
	    // you can disable this if you don't want smooth graphics
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    drawimage(g);
	    g.dispose();
	    try 
	    {
	      ImageIO.write(image, imageformat, new File(path+""+m+","+n+"image."+imageformat+""));
	      System.out.println(" SAVED");
	    } 
	    catch (IOException e)
	    {
	    }
	    }
	 public BufferedImage create(final int width, final int height,
		      final boolean alpha) {
		    BufferedImage buffer = gConfig.createCompatibleImage(width, height,
		              alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
		    return buffer;
		  }
	
}

