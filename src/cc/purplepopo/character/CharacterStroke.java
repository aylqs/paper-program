package cc.purplepopo.character;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class CharacterStroke {
    private String characterStrokeName;
    ArrayList<Point2D> point2DArrayList = new ArrayList<>();

    public CharacterStroke() {
    }
    public CharacterStroke(String name) {
        this.characterStrokeName = name;
    }
    public String getCharacterStrokeName() {
        return characterStrokeName;
    }

    public void setCharacterStrokeName(String characterStrokeName) {
        this.characterStrokeName = characterStrokeName;
    }

    public ArrayList<Point2D> getPoint2DArrayList() {
        return point2DArrayList;
    }

    public void setPoint2DArrayList(ArrayList<Point2D> point2DArrayList) {
        this.point2DArrayList = point2DArrayList;
    }

    public void addPoint2D(Point2D point2D){
        point2DArrayList.add(point2D);
    }
}
