import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Reader {

    HashMap<Character,Integer> charList = new HashMap<>();

    public Reader(){

    }

    public static void main(String[] args){
        Reader read = new Reader();
        read.checkUpperCase('A');
    }

    public void read(){

        try (FileReader fileReader = new FileReader("src/textFiles/Sample.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {

            // When there are no more lines, readLine() return null.
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //Test code to see ascii conversion works
    public void checkUpperCase(Character character){
        for (int i = 65; i <= 90; i++ ){
            int ascii = i;
            if((char)ascii == character){
                System.out.println("Works " + (char)ascii);
            }
        }
    }

    public void checkLowerCase(){

    }

}
