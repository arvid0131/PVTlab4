import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import com.google.common.base.Splitter; 

class search{
    public static void main(String[] args){
        String[] input;
        File file;
        Scanner scan = new Scanner(System.in);
        Scanner fileScanner;
        List<String> lines;
        boolean isEOF = false;
        String query;

        while (!isEOF) {
            try{
                System.out.println("Enter command: ");
                input = scan.nextLine().split(" ");
                //Splitter.on(" ").split(input);
                if(input.length < 3){
                    System.out.println("Too few arguments!\n");
                } else {

                    file = new File(input[input.length-1]);
                    fileScanner = new Scanner(file);

                    query = concatQuery(input);


                    System.out.println("Searching for: \"" + query + "\" in file " + input[input.length - 1] + "\n");

                    if(input[0].equals("search")){
                        lines = searchQuery(query, fileScanner);
                        if(lines.isEmpty()){
                            System.out.println("No matches found!");
                        } else {
                            printMatches(lines);
                        }
                    } else {
                        System.out.println("Unknown command: " + input[0]);
                    }
                }
            } catch(FileNotFoundException e){
                System.out.println("File not found!\n\n");
            } catch(NoSuchElementException e) {
                System.out.println("Exiting the program!");
                isEOF = true;
            }
        }

        scan.close();
    }

    private static List<String> searchQuery(String query, Scanner s){
        List<String> matches = new ArrayList<String>();
        for(int i = 1; s.hasNextLine(); i++){
            String l = s.nextLine();
            if(l.contains(query)){
                matches.add(i + ": " + l + "\n");
            }   
        }
        return matches;
    }

    private static String concatQuery(String[] input){
        for(int i = 2; i < input.length - 1; i++){
            input[1] += " " + input[i];
        }
        return input[1];
    }

    private static void printMatches(List<String> lines){
        System.out.print("Matches found at line(s): \n");
        for(int i = 0; i < lines.size() - 1; i++){
            System.out.print(lines.get(i));
        }
            System.out.println("\n");
    }
}