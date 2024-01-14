import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Main {

    public static void main(String args[]) {

        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("data.txt"));
			String line = reader.readLine();
            
            int counter = 0;
			while (line != null) {

                if(args[0].equals("1"))
                    counter += partOne(line);
                if(args[0].equals("2"))
                    counter += partTwo(line);
                
				line = reader.readLine();

			}

            System.out.println(counter);

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

	static int partOne(String s) {

		int RED = 12;
		int GREEN = 13;
		int BLUE = 14;

		String[] division = s.split(": ");
		int gameNumber = Integer.parseInt(division[0].split(" ")[1]);
		String[] showings = division[1].split("; ");

		for(String showing: showings) {
			String[] cubes = showing.split(", ");
			for(String cube: cubes){
				String[] cubeSplit = cube.split(" ");
				if(cubeSplit[1].equals("red"))
					if(Integer.parseInt(cubeSplit[0]) > RED){
						return 0;
					}

				if(cubeSplit[1].equals("green"))
					if(Integer.parseInt(cubeSplit[0]) > GREEN){
						return 0;
					}

				if(cubeSplit[1].equals("blue"))
					if(Integer.parseInt(cubeSplit[0]) > BLUE){
						return 0;
					}
			}
		}

		return gameNumber;
	}

	static int partTwo(String s) {

		int minimumRed = 0;
		int minimumGreen = 0;
		int minimumBlue = 0;

		String[] division = s.split(": ");
		String[] showings = division[1].split("; ");

		for(String showing: showings) {
			String[] cubes = showing.split(", ");
			for(String cube: cubes){
				String[] cubeSplit = cube.split(" ");
				if(cubeSplit[1].equals("red"))
					minimumRed = Math.max(minimumRed, Integer.parseInt(cubeSplit[0]));

				if(cubeSplit[1].equals("green"))
					minimumGreen = Math.max(minimumGreen, Integer.parseInt(cubeSplit[0]));

				if(cubeSplit[1].equals("blue"))
					minimumBlue = Math.max(minimumBlue, Integer.parseInt(cubeSplit[0]));
			}
		}

		return minimumRed * minimumGreen * minimumBlue;
	}



}