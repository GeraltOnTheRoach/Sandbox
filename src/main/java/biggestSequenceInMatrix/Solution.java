package biggestSequenceInMatrix;

class Solution {
/*
    private int rows;
    private int cols;
    private int[][] array;
    public String solution(int[][] A) {
        rows = A.length-1;
        cols = A[0].length-1;
        array = A;
        return max_path_from(0,0, new StringBuilder(String.valueOf(A[0][0]))).toString();
    }

    private StringBuilder max_path_from (int row, int col, StringBuilder current){
        //System.out.println("Raw: " + row + " Column: " + col + " Result: " + current);
        while(row != rows && col != cols && array[row][col+1] != array[row+1][col]){
            if (array[row][col+1] > array[row+1][col]) current.append(array[row][++col]);
            else if ((array[row][col+1] < array[row+1][col])) current.append(array[++row][col]);
        }

        if(row == rows && col == cols) return current;
        else if(row == rows && col < cols) {
            while(col < cols) current.append(array[row][++col]);
            return current;
        }
        else if(col == cols && row < rows) {
            while(row < rows) current.append(array[++row][col]);
            return current;
        }
        else{
            StringBuilder try_right = max_path_from(row,col+1,new StringBuilder(String.valueOf(array[row][col+1])));
            StringBuilder try_down = max_path_from(row+1,col,new StringBuilder(String.valueOf(array[row+1][col])));
            return current.append(try_right.toString().compareTo(try_down.toString()) > 0 ? try_right : try_down);
        }
    }*/
    public String solution(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;

        long[][] dynamic = new long[rows][cols];
        byte[][] path = new byte[rows][cols];
        dynamic[0][0] = A[0][0];
        path [0][0] = 2;

        for(int row = 1; row < rows; row++){
            dynamic[row][0] = dynamic[row-1][0] + A[row][0];
            path[row][0] = 1;
        }
        for(int col = 1; col < cols; col++){
            dynamic[0][col] = dynamic[0][col-1] + A[0][col];
            path[0][col] = 0;
        }

        for(int row = 1; row < rows; row++){
            for(int col = 1; col < cols; col++){
                if(dynamic[row-1][col] > dynamic[row][col-1]){
                    dynamic[row][col] = dynamic[row-1][col] + A[row][col];
                    path[row][col] = 1;
                }
                else{
                    dynamic[row][col] = dynamic[row][col-1] + A[row][col];
                    path[row][col] = 0;
                }
            }
        }
        StringBuilder result = new StringBuilder(String.valueOf(A[rows-1][cols-1]));
        int row = rows-1, col = cols-1;
        while(path[row][col] != 2){
            if (path[row][col] == 0) result.append(A[row][--col]);
            if (path[row][col] == 1) result.append(A[--row][col]);
        }
        return result.reverse().toString();
    }
}
