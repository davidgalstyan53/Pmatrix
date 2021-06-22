// @author David Galstyan

package matrix;

import java.util.Arrays;

public class Matrix
{


    public final int row;
    public final int col;
    public final int[][] element;

    Matrix(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.element = new int[row][col];

        for(int i = 0 ; i < row ; i++) for(int j = 0; j < col; j++)
        {
            this.element[i][j]=(int)(Math.random()*200)-100;
        }
    }

    Matrix()
    {
        this(2,2);
    }

    Matrix(int[][] input)
    {
        this.row = input.length;
        this.col = input[0].length;
        this.element = new int [row][col];

        for(int i =0 ; i < row ; i++)
        {
            this.element[i] = Arrays.copyOf(input[i], col);
        }
    }

    Matrix add(Matrix input)
    {
        int[][]result = new int[row][col];
        for(int i = 0; i < row; i++) for (int j = 0; j < col; j++)
        {
            result[i][j]= this.element[i][j]+input.element[i][j];
        }
        return new Matrix(result);
    }

    Matrix subtract(Matrix input)
    {
        int[][]result = new int[row][col];
        for(int i = 0; i < row; i++) for (int j = 0; j < col; j++)
        {
            result[i][j]= this.element[i][j]-input.element[i][j];
        }
        return new Matrix(result);
    }

    Matrix dot(Matrix input)
    {
        int[][]result = new int[this.row][this.col];
        for(int i = 0; i < row; i++) for(int j = 0; j < col; j++)
            for(int k =0; k < col; k++)
            {
                result[i][j] += this.element[i][k] + input.element[k][j];
            }
        return new Matrix(result);
    }

    @Override
    protected Matrix clone()
    {
        return new Matrix(this.element);
    }

    public boolean equals(Object O)
    {
        if(O instanceof Matrix)
        {
            Matrix M2 = (Matrix)O;
            if(this.row == M2.row && this.col == M2.col)
            {
                for(int i = 0; i < this.row; i++)for(int j = 0; j <this.col ; j++)
                {
                    if(this.element !=M2.element) return false;
                }

            }
            return true;
        }
        return false;
    }

    static boolean equalDimension(Matrix M1, Matrix M2)
    {
        return(M1.row == M2.row && M1.col == M2.col);
    }

    static boolean checkDot(Matrix M1, Matrix M2)
    {
        if(M1.col == M2.row) return true;
        else return false;
    }
}