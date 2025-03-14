import java.util.ArrayList;
import java.util.List;


public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {

        int len = values.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {

        int[] result = new int [rows * cols];

        int index = 0;

        int topRow = 0, bottomRow = rows - 1;
        int leftCol = 0, rightCol = cols - 1;

        while (topRow <= bottomRow && leftCol <= rightCol) {
            for (int i = leftCol; i <= rightCol; i++) {
                result[index++] = values[topRow][i];
            }
            topRow++;

            for (int i = topRow; i <= bottomRow; i++) {
                result[index++] = values[i][rightCol];
            }
            rightCol--;

            if (topRow <= bottomRow) {
                for (int i = rightCol; i >= leftCol; i--) {
                    result[index++] = values[bottomRow][i];
                }
                bottomRow--;
            }

            if (leftCol <= rightCol) {
                for (int i = bottomRow; i >= topRow; i--) {
                    result[index++] = values[i][leftCol];
                }
                leftCol++;
            }
        }

        return result;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {

        List<List<Integer>> partitions = generatePartitions(n);
        return convertToArray(partitions);
    }

    private List<List<Integer>> generatePartitions(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPartition = new ArrayList<>();
        generatePartitionsHelper(n, n, currentPartition, result);
        return result;
    }

    private void generatePartitionsHelper(int n, int max, List<Integer> currentPartition, List<List<Integer>> result) {
        if (n == 0) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        for (int i = Math.min(max, n); i >= 1; i--) {
            currentPartition.add(i);
            generatePartitionsHelper(n - i, i, currentPartition, result);
            currentPartition.remove(currentPartition.size() - 1);
        }
    }

    private int[][] convertToArray(List<List<Integer>> partitions) {
        int[][] result = new int[partitions.size()][];
        for (int i = 0; i < partitions.size(); i++) {
            List<Integer> partition = partitions.get(i);
            result[i] = new int[partition.size()];
            for (int j = 0; j < partition.size(); j++) {
                result[i][j] = partition.get(j);
            }
        }
        return result;
    }
}
