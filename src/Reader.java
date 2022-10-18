import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Reader {

    private HashMap<Character,Integer> charList;
    private Boolean countCaps = false;
    private int charCount = 0;

    public Reader(){
        charList = new HashMap<>();

    }

    public static void main(String[] args){
        Reader reader = new Reader();
        reader.capsOption();
        reader.read();

    }

    /**
     * Will read in text line by line.
     * each line will have its characters counted
     * if countCaps is set to true the method will also count uppercase characters
     */
    public void read(){

        try (FileReader fileReader = new FileReader("CountCharacters/src/textFiles/Sample.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {
                
            // When there are no more lines, readLine() return null
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                for(char chr : line.toCharArray()){
                    if(Character.isWhitespace(chr)){
                        continue;
                    }
                    if(countCaps) {
                        addToMap(chr);
                    } else{
                        addToMap(Character.toLowerCase(chr));
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("could not find file");
        }

        this.sortMap();
        this.printMap();
    }

    /**
     * prompts user for caps option
     */
    public void capsOption(){
        System.out.println("Do you wish to include caps? y/n");
        Scanner input = new Scanner(System.in);
        if(input.nextLine().equals("y")){
            this.countCaps = true;
        }
    }

    /**
     * sorts map through use of map entry
     * Comparator reverse order is called in order to get in descending order
     */
    public void sortMap(){
        Map<Character, Integer> sortedMap = this.getCharList().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        this.charList = (HashMap<Character, Integer>) sortedMap;
    }


    /**
     * prints each key and its value
     */
    public void printMap(){
        int counter = 0;
        System.out.println("Total Characters: " + this.charCount);
        for (Character keys: this.getCharList().keySet()) {
            //if (counter == 10){
              //  break;
            //}
            String value = this.getCharList().get(keys).toString();
            System.out.println(keys + " (" + value + ")");
            counter++;
        }


    }

    /**
     * adds character to map if it is not already present. If it is present it will increment the value by 1 AKA the 'count'.
     * @param character key to add to map
     */
    public void addToMap(Character character){
            if(!checkPresence(character)){
                this.getCharList().put(character,1);
            } else {
                this.getCharList().put(character, this.getCharList().get(character) + 1);
            }
            this.charCount++;
    }

    /**
     *
     * @param character character to be checked against hashmap
     * @return true if hashmap already has a key equal to character
     */
    public boolean checkPresence(Character character){
        charList = this.getCharList();
        if(charList.containsKey(character)){
            return true;
        } else{
            return false;
        }
    }

    /**
     *
     * @return hashmap with character count
     */
    public HashMap<Character, Integer> getCharList() {
        return charList;
    }



}
