import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}

	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setGreen(255 - pixelObj.getGreen() );
				pixelObj.setBlue(255 - pixelObj.getBlue());
			}
		}
	}

	public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int avg = (int) pixelObj.getAverage();
				pixelObj.setRed(avg);
				pixelObj.setGreen(avg);
				pixelObj.setBlue(avg);
			}
		}
	}

	public void fixUnderwater() {
		Pixel[][] pixels = this.getPixels2D();
		double r = (259 * (120 + 255)) / (255 * (259 - 120));
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setGreen((int)(r * (pixelObj.getGreen()   - 128) + 128));
				pixelObj.setRed((int)(r * (pixelObj.getRed()   - 128) + 128));
				pixelObj.setBlue((int)(r * (pixelObj.getBlue() - 128) + 128));
			}
		}
	}
	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorVerticalRightToLeft(){
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel top = null;
		Pixel bottom = null;
		int width = pixels[0].length;
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < pixels.length/2; row++) {
				top = pixels[row][col];
				bottom = pixels[pixels.length - 1 - row][col];
				bottom.setColor(top.getColor());
			}
		}
	}

	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel top = null;
		Pixel bottom = null;
		int width = pixels[0].length;
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < pixels.length/2; row++) {
				top = pixels[row][col];
				bottom = pixels[pixels.length - 1 - row][col];
				top.setColor(bottom.getColor());
			}
		}
	}

 public void mirrorDiagonal()
 {
	Pixel[][] pixels = this.getPixels2D();
	Pixel left = null;
	Pixel right = null;

	// calculate the max area to mirror (min of width or height)
	int max = pixels.length;
	if (pixels[0].length < max) {
		max = pixels[0].length;
	}

	// loop through to the left of the diagonal line (row=col)
	for (int row = 1; row < max; row++)	{
		for (int col = 0; col < row; col++) {
			left = pixels[row][col];
			right = pixels[col][row];
			right.setColor(left.getColor());
		}
	}
 }

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {
				count++;
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
		System.out.println(count);
	}

	public void mirrorArms() {
		Pixel top = null;
		Pixel bottom = null;
		Pixel[][] pixels = this.getPixels2D();

		for (int row = 155; row < 191; row++) {
			for (int col = 98; col < 170; col++) {
				top = pixels[row][col];
				bottom = pixels[191-row+191][col];
				bottom.setColor(top.getColor());
			}
		}

		for (int row = 155; row < 191; row++) {
			for (int col = 238; col < 296; col++) {
				top = pixels[row][col];
				bottom = pixels[191-row+191][col];
				bottom.setColor(top.getColor());
			}
		}
	}

	public void mirrorGull() {
		int reference = 350;
		Pixel left = null;
		Pixel right = null;
		Pixel[][] pixels = this.getPixels2D();
	
		for (int row = 235; row < 319; row++) {
			for (int col = 237; col < reference; col++) {
				left = pixels[row][col];
				right = pixels[row][reference - col +
				reference];
				right.setColor(left.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void copy(Picture fromPic, int startToRow, int startToCol, int startFromRow,int startFromCol,int endFromRow, int endFromCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = startFromRow, toRow = startToRow; fromRow < endFromRow
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = startFromCol, toCol = startToCol; fromCol < endFromCol
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}                                                                                                                                                              

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0,30,0, 70,80);
		this.copy(flower2, 100, 0,0,0, 100,80);
		this.copy(flower1, 200, 0,0,0, 90,80);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	public void myCollage() {
		Picture gorge = new Picture("gorge.jpg");
		Picture moon = new Picture("moon-surface.jpg");
		Picture temple = new Picture("temple.jpg");
		gorge.keepOnlyBlue();
		moon.negate();
		temple.zeroBlue();
		this.copy(gorge,0,0,0,0,150,175);
		this.copy(moon,0,150,0,0,150,175);
		this.copy(temple,0,300,0,0,150,175);
		this.copy(gorge,175,0,0,0,150,175);
		this.mirrorHorizontal();
		this.copy(moon,175,150,0,0,150,175);
		this.copy(temple,175,300,0,0,150,175);
		this.copy(gorge,175,0,0,0,150,175);
		this.copy(moon,175,150,0,0,150,175);
		this.copy(temple,175,300,0,0,150,175);
	}
	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
