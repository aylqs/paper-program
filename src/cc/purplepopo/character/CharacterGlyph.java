package cc.purplepopo.character;

import java.util.ArrayList;

public class CharacterGlyph {
    private int characterID;
    private String characterName;
    ArrayList<CharacterStroke> characterStrokeArrayList = new ArrayList<>();

    public CharacterGlyph(int ID, String name) {

        this.characterID = ID;
        this.characterName = name;
    }

    public int getCharacterID() {
        return characterID;
    }


    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public ArrayList<CharacterStroke> getCharacterStrokeArrayList() {
        return characterStrokeArrayList;
    }

    public void setCharacterStrokeArrayList(ArrayList<CharacterStroke> characterStrokeArrayList) {
        this.characterStrokeArrayList = characterStrokeArrayList;
    }

    public void addCharacterStroke(CharacterStroke characterStroke){
        characterStrokeArrayList.add(characterStroke);
    }
}
