public class Main
{
    public static void  main(String[] args)
    {
        IO.println(SumFrom1Ton(10));

        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };

        int[][] newMatrix = mirrorMatricHorizontally(matrix);
        print2DArray(newMatrix);
        mirrorMatrixVertically(newMatrix);

        print2DArray(newMatrix);
    }

    public static int SumFrom1Ton(int n)
    {
        if (n == 1)
        {
            return 1;
        }
        return n + SumFrom1Ton(n - 1);
    }

    public static int[][] mirrorMatricHorizontally(int[][] matrix)
    {
        // assume matrix is rectangular
        int[][] newMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++)
        {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }

        for (int[] ints : newMatrix)
        {
            mirrorArray(ints);
        }

        return newMatrix;
    }

    public static void mirrorArray(int[] array)
    {
        for (int i = 0; i < array.length / 2; i++)
        {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    public static void mirrorMatrixVertically(int[][] matrix)
    {
        for (int i = 0; i < matrix.length / 2; i++)
        {
            int[] temp = matrix[i];
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = temp;
        }
    }

    public static void print2DArray(int[][] array)
    {
        for (int[] ints : array) {
            for (int anInt : ints) {
                IO.print(anInt + " ");
            }
            IO.println("");
        }
    }
}
