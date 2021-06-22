// @author David Galstyan

package matrix;
import java.util.Arrays;
import java.util.stream.*;


public class PMatrix extends Matrix
{
    PMatrix()
    {
        super();
    }

    PMatrix(int[][]input)
    {
        super(input);
    }

    PMatrix(int row, int col)
    {
        super(row,col);
    }

    @Override
    Matrix add(Matrix input)
    {
        return(Matrix) new PMatrix(IntStream.range(0,this.row)
                .mapToObj(x -> IntStream.range(0,this.col)
                        .map(y -> this.element[x][y] + input.element[x][y])
                        .toArray()).toArray(int[][]::new));
    }

    @Override
    Matrix subtract(Matrix input)
    {
        return(Matrix) new PMatrix(IntStream.range(0,this.row)
                .mapToObj(x -> IntStream.range(0,this.col)
                        .map(y -> this.element[x][y] - input.element[x][y])
                        .toArray()).toArray(int[][]::new));
    }

    @Override
    Matrix dot(Matrix input)
    {
        return (Matrix) new PMatrix(IntStream.range(0, this.row).parallel().mapToObj(i
                        -> IntStream.range(0, input.col).parallel().map(j
                        -> IntStream.range(0, this.col).parallel().map(k
                        -> input.element[k][j] * this.element[i][k]).reduce(0,(a,b) -> a+b)
                ).toArray()
        ).toArray(int[][]::new));


    }

    boolean equal(Object input)
    {
        if(input instanceof Matrix)
        {
            Matrix myMatrix = (Matrix)input;
            if(Matrix.equalDimension(this, myMatrix))
            {
                IntStream.range(0,this.row).parallel().mapToObj(x
                        ->IntStream.range(0, this.col).parallel().mapToObj(y
                        ->this.element[x][y] == myMatrix.element[x][y]
                ).reduce(true,(a,b) -> a && b));
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return (Arrays.stream(this.element).map(
                r -> Arrays.stream(r).mapToObj(
                        e -> String.format ("%6d", e)
                ).reduce("\n", (A, B) -> A + B)
        ).reduce("", (A, B) -> A + B));


    }

}
