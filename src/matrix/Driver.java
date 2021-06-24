/**
 *
 * @author David Galstyan
 */
package matrix;
import java.util.Arrays;
import java.util.Scanner;


public class Driver
{
    public static void main(String[] args)
    {
        Matrix A = Driver.getMatrix("First Matrix");
        printMatrix(A.element);
        Matrix B = Driver.getMatrix("Second Matrix");
        printMatrix(B.element);
        Matrix C = Driver.performOP(A,B);
        System.out.println(C.toString());

    }

    static Matrix getMatrix(String MatrixName)
    {
        int row,col;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter dimensions of " + MatrixName + ":");
        System.out.println("\nHow many rows for " + MatrixName + "?");
        row = scan.nextInt();
        System.out.println("How many columns for " + MatrixName + "?");
        col = scan.nextInt();
        return new PMatrix(row,col);
    }

    static int PickOP()
    {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("\nSelect an operation: \n1.Add \n2.Subtract \n3.Dot\n");
        return Scanner.nextInt();
    }

    static boolean validateOP(Matrix A, Matrix B, int OP)
    {
        switch (OP) {
            case 1:
            case 2:
                return Matrix.equalDimension(A, B);
            case 3:
                return Matrix.checkDot(A, B);
            default:
                return false;
        }

    }

    static Matrix executeOP(int OP, Matrix A, Matrix B)
    {
        switch (OP)
        {
            case 1:
                return A.add(B);
            case 2:
                return A.subtract(B);
            default:
                return A.dot(B);
        }
    }

    static Matrix performOP(Matrix A, Matrix B)
    {
        int OP = PickOP();
        boolean isvalid;
        do{
            isvalid = validateOP(A,B,OP);
            if(!isvalid)
            {
                System.out.println("\nInvalid input? Tisk-tisk...\n"
                        + "Have yourself a Sugar-Free Red Bull, and let's start over...");
                A = getMatrix("1st Matrix");
                System.out.println(Arrays.deepToString(A.element)
                        .replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
                B = getMatrix("2nd Matrix");
                System.out.println(Arrays.deepToString(B.element)
                        .replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
                OP = PickOP();
            }
            else
            {
                return executeOP(OP,A,B);
            }
        }while(!isvalid);

        return executeOP(OP,A,B);
    }

    static void printMatrix(int[][] x)
    {
        System.out.println(Arrays.deepToString(x)
                .replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }



}