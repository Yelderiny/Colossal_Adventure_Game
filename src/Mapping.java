import java.util.HashMap;
import java.util.Scanner;

public class Mapping
{
    public static final int INITIAL_LOCATION = 95;

    static LocationMap locMap = new LocationMap();
    private final HashMap<String, String> vocab = new HashMap<>();
    private final FileLogger flog = new FileLogger();
    private final ConsoleLogger clog = new ConsoleLogger();

    public Mapping()
    {
        vocab.put("QUIT", "Q");
        vocab.put("NORTH", "N");      vocab.put("SOUTH", "S");
        vocab.put("NORTHEAST", "NE"); vocab.put("SOUTHWEST", "SW");
        vocab.put("NORTHWEST", "NW"); vocab.put("SOUTHEAST", "SE");
        vocab.put("EAST", "E");       vocab.put("WEST", "W");
        vocab.put("UP", "U");         vocab.put("DOWN", "D");
    }

    public void mapping()
    {
        Scanner scan = new Scanner(System.in); //create a Scanner object
        int location = INITIAL_LOCATION; //initialize a location variable with the INITIAL_LOCATION

        while (true)
        {
            Location curLoc = locMap.get(location); //get the current location

            //log the location
            flog.log(curLoc.getDescription());
            clog.log(curLoc.getDescription());

            if (location == 0) break; //verify if the location is quit

            HashMap<String, Integer> exits = (HashMap<String, Integer>) curLoc.getExits(); //get a map of the exits for the location

            //build available exits string
            StringBuilder availExits = new StringBuilder("Available exits are ");
            exits.keySet().forEach(exit -> availExits.append(exit).append(", "));

            //log the available exits
            flog.log(availExits.toString());
            clog.log(availExits.toString());

            String line = scan.nextLine().toUpperCase(); //get the new direction from the user

            boolean validInput = false; //a boolean to check if the input is valid

            //input is a letter or two
            if (line.length() <= 2 && !line.equals("UP"))
            {
                //the input is part of the vocab and a current valid exit
                if (vocab.containsValue(line) && exits.containsKey(line))
                {
                    validInput = true;
                    location = exits.get(line); //set the location to be the chosen exit
                }
            }
            else
            {
                String[] divLine = line.split(" "); //split the line into words

                //input is one word
                if (divLine.length == 1)
                {
                    String word = divLine[0]; //extract the word into a variable I can manipulate

                    String[] dirs = vocab.keySet().toArray(new String[0]); //add the vocab key set to a String array

                    //if the word is incorrect but one of the key set words is within the input word, allow it
                    if (!vocab.containsKey(word)) for (String dir : dirs) if (divLine[0].contains(dir)) word = dir;

                    //the input is part of the vocab and a current valid exit
                    if (vocab.containsKey(word) && exits.containsKey(vocab.get(word)))
                    {
                        validInput = true;
                        location = exits.get(vocab.get(word)); //set the location to be the chosen exit
                    }
                }

                //input is a sentence
                else
                {
                    //loop over every word
                    for (String word : divLine)
                    {
                        //the input is part of the vocab and a current valid exit
                        if (vocab.containsKey(word) && exits.containsKey(vocab.get(word)))
                        {
                            validInput = true;
                            location = exits.get(vocab.get(word)); //set the location to be the chosen exit
                        }
                    }
                }
            }

            //check incorrect inputs
            if (!validInput)
            {
                flog.log("You cannot go in that direction");
                clog.log("You cannot go in that direction");
            }
        }
    }

    public static void main(String[] args)
    {
        Mapping m = new Mapping();
        m.mapping();
    }
}
