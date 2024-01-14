import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


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
        int toReturn = 0;

        for(int i=0; i<s.length(); ++i)
            if(Character.isDigit(s.charAt(i))) {
                toReturn += 10 * (s.charAt(i) - '0');
                break;
            }

        for(int i=s.length()-1; i>=0; --i) 
            if(Character.isDigit(s.charAt(i))) {
                toReturn += (s.charAt(i) -'0');
                break;
            }

        System.out.println(toReturn);
        return toReturn;
    }

    static int partTwo(String s){
        int toReturn = 0;
        int indexOfFirstDigit = s.length();

        for(int i=0; i<s.length(); ++i){
            if(Character.isDigit(s.charAt(i))) {
                toReturn += 10 * (s.charAt(i) - '0');
                break;
            }
            if(s.substring(i, Math.min(i+4, s.length()-1)).equals("zero")){break;}
            if(s.substring(i, Math.min(i+3, s.length()-1)).equals("one")){toReturn += 10 * 1; break;}
            if(s.substring(i, Math.min(i+3, s.length()-1)).equals("two")){toReturn += 10 * 2; break;}
            if(s.substring(i, Math.min(i+5, s.length()-1)).equals("three")){toReturn += 10 * 3; break;}
            if(s.substring(i, Math.min(i+4, s.length()-1)).equals("four")){toReturn += 10 * 4; break;}
            if(s.substring(i, Math.min(i+4, s.length()-1)).equals("five")){toReturn += 10 * 5; break;}
            if(s.substring(i, Math.min(i+3, s.length()-1)).equals("six")){toReturn += 10 * 6; break;}
            if(s.substring(i, Math.min(i+5, s.length()-1)).equals("seven")){toReturn += 10 * 7; break;}
            if(s.substring(i, Math.min(i+5, s.length()-1)).equals("eight")){toReturn += 10 * 8; break;}
            if(s.substring(i, Math.min(i+4, s.length()-1)).equals("nine")){toReturn += 10 * 9; break;}
        }

         for(int i=s.length()-1; i>=0; --i) {
            if(Character.isDigit(s.charAt(i))) {
                toReturn += (s.charAt(i) - '0');
                break;
            }
            if(s.substring(Math.max(0, i-3), i+1).equals("zero")){break;}
            if(s.substring(Math.max(0, i-2), i+1).equals("one")){toReturn += 1; break;}
            if(s.substring(Math.max(0, i-2), i+1).equals("two")){toReturn += 2; break;}
            if(s.substring(Math.max(0, i-4), i+1).equals("three")){toReturn += 3; break;}
            if(s.substring(Math.max(0, i-3), i+1).equals("four")){toReturn += 4; break;}
            if(s.substring(Math.max(0, i-3), i+1).equals("five")){toReturn += 5; break;}
            if(s.substring(Math.max(0, i-2), i+1).equals("six")){toReturn += 6; break;}
            if(s.substring(Math.max(0, i-4), i+1).equals("seven")){toReturn += 7; break;}
            if(s.substring(Math.max(0, i-4), i+1).equals("eight")){toReturn += 8; break;}
            if(s.substring(Math.max(0, i-3), i+1).equals("nine")){toReturn += 9; break;}
        }
        System.out.println(toReturn);

        return toReturn;
    }
}