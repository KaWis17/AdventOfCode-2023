import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


class Main {

    public static void main(String args[]) {

        BufferedReader reader;

		try {

			reader = new BufferedReader(new FileReader("data.txt"));
            
            int rows = 0;

            while (reader.readLine() != null) ++rows;
            reader.close();

			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();
            
            int counter = 0;
			int[] arrayOfCards = new int[rows];
			Arrays.fill(arrayOfCards, 1);

			while (line != null) {

                if(args[0].equals("1")){
					int winningCounter = partOne(line);
					counter += (winningCounter != 0) ? (int) Math.pow(2, winningCounter-1) : 0;
				}
                if(args[0].equals("2")){
                    counter += partTwo(arrayOfCards, line);
				}
                
				line = reader.readLine();
			}
            System.out.println(counter);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

	static int partOne(String s) {
		String[] division = s.split(": ")[1].replaceAll("  ", " ").split("\\| ");

		int[] winningNumbers = stringToIntArray(division[0].replaceAll("  ", " ").replaceAll("  ", " ").split(" "));
		int[] myNumbers = stringToIntArray(division[1].replaceAll("  ", " ").split(" "));

		int winningCounter = 0;
		HashSet<Integer> map = new HashSet<Integer>();

		for (int i : winningNumbers)
			map.add(i);
		for (int i : myNumbers) 
			if (map.contains(i))
				winningCounter++;
			
		return winningCounter;
	}

	static int partTwo(int[] arrayOfCards, String s) {
		int lineNumber = Integer.parseInt(s.split(":")[0].replaceAll("  ", " ").replaceAll("  ", " ").split(" ")[1]);

		int winningCounter = partOne(s);
		System.out.println(winningCounter);
		for(int i=lineNumber; i<lineNumber+winningCounter; ++i)
			arrayOfCards[i] += arrayOfCards[lineNumber-1];

		return arrayOfCards[lineNumber-1];
	}

	static int[] stringToIntArray(String[] array){
		int[] arrayToReturn = new int[array.length];
		for(int i=0; i<array.length; ++i)
			if(!array[i].equals(""))
				arrayToReturn[i] = Integer.parseInt(array[i]);
		
		return arrayToReturn;
	}

}