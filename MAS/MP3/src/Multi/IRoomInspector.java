package Multi;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface IRoomInspector{
    public List<String> getInspectedRooms();

    public void addInspectedRoom(String inspectedRoom);

    public void removeInspectedRoom(String inspectedRoom);
}
