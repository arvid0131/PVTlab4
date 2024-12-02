import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class search{
    public static void main(String[] args){
        String[] input;
        File file;
        Scanner scan = new Scanner(System.in);
        Scanner fileScanner;
        List<Integer> lines;
        boolean isEOF = false;
        String query;

        while (!isEOF) {
            try{
                System.out.println("Enter command: ");
                input = scan.nextLine().split(" ");
                if(input.length < 3){
                    System.out.println("Too few arguments!");
                }

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

            } catch(FileNotFoundException e){
                System.out.println("File not found!\n\n");
            } catch(NoSuchElementException e) {
                System.out.println("Exiting the program!");
                isEOF = true;
            }
        }

        scan.close();
    }

    private static List<Integer> searchQuery(String query, Scanner s){
        List<Integer> matches = new ArrayList<Integer>();
        for(int i = 1; s.hasNextLine(); i++){
            if(s.nextLine().contains(query)){
                matches.add(i);
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

    private static void printMatches(List<Integer> lines){
        System.out.print("Matches found at line(s): ");
        for(int i = 0; i < lines.size() - 1; i++){
            System.out.print(lines.get(i) + ", ");
        }
        if (lines.size() > 1) System.out.print("and ");
        System.out.print(lines.get(lines.size()-1) +"\n\n");
    }
}