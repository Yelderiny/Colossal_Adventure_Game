import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location>
{
    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";
    private static final HashMap<Integer, Location> locations = new HashMap<>();

    static
    {
        var flog = new FileLogger();
        var clog = new ConsoleLogger();

        var locFile = new File(LOCATIONS_FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(locFile)))
        {
            String line;

            flog.log("Available locations:");
            clog.log("Available locations:");

            while ((line = reader.readLine()) != null)
            {
                //read the line
                String[] divLine = line.split(",", 2); //divide the line using the first comma
                int id = Integer.parseInt(divLine[0]); //get location ID
                String description = divLine[1]; //get the description

                //log it using the loggers
                flog.log(line);
                clog.log(line);

                var loc = new Location(id, description, new HashMap<>()); //create a Location object

                locations.put(id, loc); //add object to locations hashmap
            }
        }
        catch (IOException e) { e.printStackTrace(); }

        var dirFile = new File(DIRECTIONS_FILE_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(dirFile)))
        {
            String line;

            flog.log("Available directions:");
            clog.log("Available directions:");

            while ((line = reader.readLine()) != null)
            {
                String[] divLine = line.split(","); //divide by the lines with commas
                int curLoc = Integer.parseInt(divLine[0]); //get the location
                String direction = divLine[1]; //get the direction
                int destination = Integer.parseInt(divLine[2]); //get the destination

                //log it using the loggers
                flog.log(line);
                clog.log(line);

                locations.get(curLoc).addExit(direction, destination); //add the exits to the hashmap of the current location
            }
        } catch (IOException e) { e.printStackTrace(); }
    }


    //all methods for Map
    @Override
    public int size() { return locations.size(); }
    @Override
    public boolean isEmpty() { return locations.isEmpty(); }
    @Override
    public boolean containsKey(Object key) { return locations.containsKey(key); }
    @Override
    public boolean containsValue(Object value) { return locations.containsValue(value); }
    @Override
    public Location get(Object key) { return locations.get(key); }
    @Override
    public Location put(Integer key, Location value) { return locations.put(key, value); }
    @Override
    public Location remove(Object key) { return locations.remove(key); }
    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) { locations.putAll(m); }
    @Override
    public void clear() { locations.clear(); }
    @Override
    public Set<Integer> keySet() { return locations.keySet(); }
    @Override
    public Collection<Location> values() { return locations.values(); }
    @Override
    public Set<Entry<Integer, Location>> entrySet() { return locations.entrySet(); }
}
