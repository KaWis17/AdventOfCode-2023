import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class Main {

    public static void main(String args[]) {

        BufferedReader reader;
        char[][] array2D;
        int rows = 0;
        int columns = 0;

		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();
            
            rows = 0;
            columns = line.length();

            BufferedReader r = new BufferedReader(new FileReader("data.txt"));
            while (r.readLine() != null) ++rows;
            r.close();

            array2D = new char[rows][columns];

            int i = 0;
			while (line != null) {
                array2D[i] = line.toCharArray();
                
				line = reader.readLine();
                i++;
			}

            if(args[0].equals("1"))
                System.out.println(partOne(array2D, rows, columns));
            if(args[0].equals("2"))
                System.out.println(partTwo(array2D, rows, columns));

        } catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static int partOne(char[][] array2D, int rows, int columns){

        int counter = 0;
        int[][] neighbors = {{1,1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};

        for(int row=0; row<rows; ++row){
            for(int column=0; column<columns; ++column){
                if(Character.isDigit(array2D[row][column])){
                    for(int[] n: neighbors){
                        if(indexInBound(array2D, row+n[0], column+n[1]) && !(Character.isDigit(array2D[row+n[0]][column+n[1]]) || array2D[row+n[0]][column+n[1]] == '.')){
                            int[] answer = findNumber(array2D, row, column);
                            counter += answer[0];
                            
                            column = answer[2];;
                            break;
                        }
                    }
                }
            }
        }
        return counter;

    }

    public static int partTwo(char[][] array2D, int rows, int columns){

        int counter = 0;
        int[][] neighbors = {{1,1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};

        for(int row=0; row<rows; ++row){
            for(int column=0; column<columns; ++column){
                if(array2D[row][column] == '*'){

                    HashSet<ArrayList<Integer>> neighborIntegers = new HashSet<>();

                    for(int[] n: neighbors){
                        if(indexInBound(array2D, row+n[0], column+n[1]) && Character.isDigit(array2D[row+n[0]][column+n[1]])){
                            ArrayList<Integer> arrayList = new ArrayList<>();
                            int[] array = findNumber(array2D, row+n[0], column+n[1]);
                            arrayList.add(array[0]);
                            arrayList.add(array[1]);
                            arrayList.add(array[2]);

                            neighborIntegers.add(arrayList);
                        }
                    }

                    if(neighborIntegers.size() ==2){
                        int multiplication = 1;
                        for(ArrayList<Integer> list : neighborIntegers){
                            multiplication *= list.get(0);
                        }
                        counter+=multiplication;
                    }                    

                }

            }
        }

        return counter;
    }

    public static boolean indexInBound(char[][] data, int index1, int index2){
        return data != null && index1 >= 0 && index1 < data.length && index2 >= 0 && index2 < data[0].length;
    }

    public static int[] findNumber(char[][] array2D, int row, int column){
        int startIndex = column;
        int endIndex = column;

        while(startIndex-1 >= 0 && Character.isDigit(array2D[row][startIndex-1])){
            startIndex--;
        }

        while(endIndex+1 < array2D[0].length && Character.isDigit(array2D[row][endIndex+1])){
            endIndex++;
        }

        char[] charArray = Arrays.copyOfRange(array2D[row], startIndex, endIndex+1);
        
        return new int[]{Integer.parseInt(new String(charArray)), startIndex, endIndex};
    }


}