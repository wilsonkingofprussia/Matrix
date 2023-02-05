package linearalgebra;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;
public class Matrix {
	private double[][] mArray;
	private int rows, columns;
	
	public Matrix() { //default constructor
		rows = 0;
		columns = 0;
		mArray = new double[rows][columns];
	}
	public Matrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		mArray = new double[rows][columns];
	}
	public static Matrix loadFromFile(String filename) {
		//open file
		FileInputStream fileDataStream = null;
		try {
		fileDataStream = new FileInputStream(filename);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Unable to open file");
		}
		//read in data
		Scanner fileReader = new Scanner(fileDataStream);
		int fileRow = fileReader.nextInt();; //read # rows
		if (fileRow < 0 || fileRow > 100) {
			fileRow = setRowCount(fileRow);
		}
		int fileCol = fileReader.nextInt(); //read # columns
		if (fileCol < 0 || fileCol > 100) {
			fileCol = setColCount(fileCol);
		}
		Matrix readMatrix = new Matrix(fileRow, fileCol);
		for (int i=0; i < fileRow; i++) //read matrix entries into the array
		{
			for(int j=0; j<fileCol; j++)
	        {
				readMatrix.mArray[i][j] = fileReader.nextDouble();
			}

		}
		try { //close file
			fileDataStream.close(); 
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		fileReader.close();
		return readMatrix;
	}
	public void saveToFile(String filename) {
		FileOutputStream fileWrite = null;
		PrintWriter writer = null;
		try { //open file
			fileWrite = new FileOutputStream(filename);
			writer = new PrintWriter(fileWrite);
		} catch(FileNotFoundException fnfe) {
			System.out.println("Unable to write to file");
		}
		writer.println(rows);
		writer.println(columns);
		for (int i = 0; i < rows; i++) { //traverse the transposed array, writing each element to the file
			for (int j = 0; j < columns; j++) {
				writer.print(" " + mArray[i][j]);
				if ((j+1) == columns) { //if it reached the rightmost column
					writer.println();
				}
			}
		}
		writer.println("");
		writer.close();
	}
	public static int setRowCount(int size) {
		if (size > 100) {
			size = 100;
		}
		if (size < 0) {
			size = 0;
		}
		return size;
	}
	public static int setColCount(int size) {
		if (size > 100) {
			size = 100;
		}
		if (size < 0) {
			size = 0;
		}
		return size;
	}
	public boolean setEntry(int row, int col, double value) {
		if (row <= rows) { //if the entry is in the array
			if (col <= columns) {
				mArray[row][col] = value;
				return true;
			}
		}
		return false; //if the entry is outside the array
	}
	public void display() { //display matrix
		for (int i = 0; i < rows; i ++) {
			System.out.print(" |");
			for (int j = 0; j < columns; j++) {
				System.out.printf(" %6.2f", mArray[i][j]);
				if ((j+1) == columns) { //if it reached the rightmost column, output pipe and new line
					System.out.println(" | ");
				}
			}
		}
	}
	public double getNorm() {
		double norm = 0;
		double copy[][] = new double[rows][columns];
		//copy array, square all elements while counting their total
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				copy[i][j] = Math.pow(mArray[i][j], 2);
				norm += copy[i][j];
			}
		}
		//calculate and return norm
		norm = Math.sqrt(norm);
		return norm;
	}
	public Matrix transpose() {
		//declare new matrix using myMatrix's column # as invert's row #, & vice versa
		Matrix invert = new Matrix(columns, rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				invert.mArray[j][i] = this.mArray[i][j];
			}
		}
		return invert;
	}
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		//get filename
		String filename = null;
	    System.out.println("Enter the file to load: ");
	    filename = scnr.next();
	    
	    //make a matrix
	    Matrix myMatrix = new Matrix();
	    myMatrix = loadFromFile(filename);
	    myMatrix.display();
	    
	    //Frobenius Norm
	    System.out.println("The Frobenius Norm is " + myMatrix.getNorm());
	    
	    //change entry
	    int userRow = 0, userCol = 0;
	    double userVal = 0;
	    System.out.println("Enter the row: ");
	    userRow = scnr.nextInt();
	    System.out.println("Enter the col: ");
	    userCol = scnr.nextInt();
	    System.out.println("Enter the value: ");
	    userVal = scnr.nextDouble();
	    myMatrix.setEntry(userRow, userCol, userVal);
	    
	    //transpose matrix
	    Matrix tMatrix = new Matrix(); 
	    tMatrix = myMatrix.transpose();
	    
	    //display untransposed matrix
	    myMatrix.display();
	    
	    //save transposed matrix to file
	    tMatrix.saveToFile("A1.txt");
	    
	    //end of main
	    scnr.close();
	}
}
