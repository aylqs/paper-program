package cc.purplepopo.character;

import java.util.ArrayList;

public class CharacterTree {

    String characterTreeName;
    ArrayList<CharacterGlyph> characterGlyphArrayList = new ArrayList<CharacterGlyph>();

    public CharacterTree() {
        characterTreeName="New group";
    }

    public CharacterTree(String characterTreeName) {
        this.characterTreeName=characterTreeName;
    }

    public String getcharacterTreeName() {
        return characterTreeName;
    }

    public void setcharacterTreeName(String characterTreeName) {
        this.characterTreeName=characterTreeName;
    }

    public ArrayList<CharacterGlyph> getCharacterGlyphArrayList() {
        return characterGlyphArrayList;
    }

    public void addCharacterStroke(CharacterGlyph characterGlyph) {
        this.characterGlyphArrayList.add(characterGlyph);
    }

}
