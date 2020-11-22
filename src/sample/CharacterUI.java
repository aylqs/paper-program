package sample;

import cc.purplepopo.ReadCharacter;
import cc.purplepopo.character.CharacterGlyph;
import cc.purplepopo.character.CharacterStroke;
import cc.purplepopo.character.CharacterTree;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.primaryStage;

public class CharacterUI implements Initializable {
    @FXML
    private Pane drawPane;
    @FXML
    private ListView characterList;
    @FXML
    private TreeView characterView;
    @FXML
    private Label totalLable;
    @FXML
    private ToolBar toolbar;

    CharacterTree characterTree = new CharacterTree();
    CharacterGlyph characterGlyph = new CharacterGlyph(0,"");
    private Group characterGroup = new Group();
    private int currentNum = 0;
    private double perX = -20;
    private double perY = -20;
    private Timeline tl=new Timeline();
    private static int numId=-1;

    public void MenuOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("OpenFile");
        try {
            File file = fileChooser.showOpenDialog(primaryStage);
            ReadCharacter.Read(file,"newname", characterTree);
            characterView.setRoot(new TreeItem(file.getName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // add to itemView
        for (CharacterGlyph cg:characterTree.getCharacterGlyphArrayList()) {
            TreeItem treeItem = new TreeItem(String.valueOf(cg.getCharacterID()));
            characterView.getRoot().getChildren().add(treeItem);
        }
        //
        characterView.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();

                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String name = (String) ((TreeItem)characterView.getSelectionModel().getSelectedItem()).getValue();
                    //System.out.println("Node click: " + name);
                    //add to itemList
                    ObservableList<String> strList = FXCollections.observableArrayList();
                    strList.clear();
                    for (CharacterGlyph cg:characterTree.getCharacterGlyphArrayList()) {
                        //find
                        if (name.equals(String.valueOf(cg.getCharacterID()))) {
                            //search
                            for (CharacterStroke cs:cg.getCharacterStrokeArrayList()) {
                                //add to ListView
                                strList.add(cs.getPoint2DArrayList().get(0).toString());
                            }
                            //total strork element
                            totalLable.setText("Total:"+cg.getCharacterName());

                            characterGlyph=cg;
                            currentNum=characterGlyph.getCharacterStrokeArrayList().size();
                            System.out.println(currentNum);

                            //drawCharacter(cg);

                            numId=0;
                            tl.stop();
                            draw(characterGlyph);
                        }
                    }
                    characterList.setItems(strList);
                    return;
                }
            }
        });
    }


    public void drawCharacter(CharacterGlyph characterGlyph){
        drawPane.getChildren().clear();
        characterGroup.getChildren().clear();
        for (int i = 0; i <numId; i++) {
            drawCharacterStroke(characterGlyph.getCharacterStrokeArrayList().get(i));
        }
        drawPane.getChildren().add(characterGroup);
    }

    private void drawCharacterStroke(CharacterStroke characterStroke){
        ArrayList<Point2D> list = characterStroke.getPoint2DArrayList();
        Path path = new Path();
        path.getElements().add(new MoveTo(list.get(0).getX()*10+perX,list.get(0).getY()*10+perY));
        for (Point2D point:list) {
            path.getElements().add(new LineTo(point.getX()*10+perX,point.getY()*10+perY));
        }
        characterGroup.getChildren().add(path);
    }
    //Anamation
    public void draw(CharacterGlyph characterGlyph) {
         tl = new Timeline(
                new KeyFrame(Duration.millis(1000), e -> increase(characterGlyph)));
         tl.setCycleCount(currentNum);
         tl.play();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        perX = drawPane.getLayoutX()/2+drawPane.getWidth();
        perY = drawPane.getLayoutY()/2+drawPane.getHeight();
        drawPane.addEventHandler(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                characterGroup.setScaleX(characterGroup.getScaleX()+event.getDeltaY()/50);
                characterGroup.setScaleY(characterGroup.getScaleY()+event.getDeltaY()/50);
            }
        });
    }
    public int getNumId(){
        return numId;
    }

    public void tool_Clean(){
        currentNum = 0;
        numId=0;
        drawCharacter(characterGlyph);
    }

    public void tool_Last(){
//        if (currentNum>0)
//            currentNum--;
        if (numId>0)
            numId--;
        drawCharacter(characterGlyph);
    }

    public void tool_Next(){
//        if (currentNum<characterGlyph.getCharacterStrokeArrayList().size())
//            currentNum++;
        if (numId<characterGlyph.getCharacterStrokeArrayList().size())
            numId++;
        drawCharacter(characterGlyph);
    }
    //Add Glyph
    public void increase(CharacterGlyph characterGlyph){
        numId++;
        drawCharacter(characterGlyph);
        if(numId==currentNum)
            tl.stop();
        System.out.println(numId);
    }

    public void tool_Stream(){

    }

    public void tool_Complete(){
//        currentNum=characterGlyph.getCharacterStrokeArrayList().size();
        numId=characterGlyph.getCharacterStrokeArrayList().size();
        drawCharacter(characterGlyph);
    }
}
