package assets;

public class GameLogic {
    public boolean locked;
    public boolean tripletHint;
    public boolean edHint;
    public int edTalk;
    public boolean chadHint;
    public boolean leslieHint;

    public String currentRoom;
    public String ROOM_INTRO = "intro";
    public String ROOM_SEARCH = "searchRoom";
    public String ROOM_INTERVIEW = "interview";
    public String ROOM_CRIME_SCENE = "crimeScene";

    public GameLogic() {
        this.locked = true;
        this.tripletHint = false;
        this.edHint = false;
        this.edTalk = 0;
        this.chadHint = false;
        this.leslieHint = false;
        this.currentRoom = ROOM_INTRO;
    }

}
