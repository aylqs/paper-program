package cc.purplepopo;

import cc.purplepopo.character.CharacterGlyph;
import cc.purplepopo.character.CharacterStroke;
import cc.purplepopo.character.CharacterTree;
import javafx.geometry.Point2D;

import java.io.*;
import java.util.Scanner;

public class ReadCharacter {
    public static void Read(File file,String characterTreeName,CharacterTree characterTree) throws FileNotFoundException {
        characterTree.setcharacterTreeName(characterTreeName);
        Scanner scanner1 = new Scanner(file);
        int ID = 1;
        while (scanner1.hasNext()){
            Scanner scanner = new Scanner(scanner1.nextLine()).useDelimiter(",");
            while (scanner.hasNextInt()){
                CharacterGlyph characterGlyph = new CharacterGlyph(ID++,String.valueOf(scanner.nextInt()));
                Point2D point2D;
                int flag=0;
                CharacterStroke characterStroke = new CharacterStroke("");
                while (true){
                    point2D = new Point2D(scanner.nextInt(),scanner.nextInt());
                    flag++;
                    if (point2D.equals(new Point2D(-64,-64))||flag>512){
                        characterGlyph.addCharacterStroke(characterStroke);
                        break;
                    }
                    if (point2D.equals(new Point2D(-64,0))){
                        if(flag>1)
                        characterGlyph.addCharacterStroke(characterStroke);
                        characterStroke=new CharacterStroke("");
                    }else {
                        characterStroke.addPoint2D(point2D);
                    }
                }
                characterTree.addCharacterStroke(characterGlyph);
            }
        }
        //showCharacter(characterTree);
    }

    private static void showCharacter(CharacterTree characterTree){
        System.out.println(characterTree.getcharacterTreeName()+"\n");
        for (CharacterGlyph cg:characterTree.getCharacterGlyphArrayList()) {
            System.out.println("Character："+cg.getCharacterName());
            for (CharacterStroke cs:cg.getCharacterStrokeArrayList()) {
                System.out.println("No："+cs.getCharacterStrokeName());
                for (Point2D p:cs.getPoint2DArrayList()) {
                    System.out.print(p.toString());
                }
                System.out.println();
            }
        }
    }
}
