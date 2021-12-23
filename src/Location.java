import java.util.HashMap;
import java.util.Map;

public class Location
{
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    //constructor
    public Location(int locationId, String description, Map<String, Integer> exits)
    {
        this.locationID = locationId;
        this.description = description;

        if (exits.isEmpty())
        {
            this.exits = new HashMap<>();
            this.exits.put("Q", 0);
        }
        else this.exits = exits;
    }

    //accessors
    public int getLocationId() { return locationID; }
    public String getDescription() { return description; }
    public Map<String, Integer> getExits() { return exits; }

    //mutators
    protected void addExit(String direction, int location) { exits.put(direction, location); }
}
