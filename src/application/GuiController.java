package application;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

public class GuiController implements Initializable{
	private final String VERSION = "B0_01"; 
	private final String ABOUT1 = "<HTML>" 	//about text in HTML!
			+ "<P ALIGN=LEFT STYLE=\"margin-bottom: 0in\">Bardia Jedi<BR>"
			+ "ScRR - Starcraft race randomiser<BR>"
			+ VERSION
			+ "<BR>"
			+ "&#169;2013 Bardia \"Luviz\" Jedi<BR>"
			+ "java version 1.7.0_17<BR><BR>"
			+ "<A HREF='mailto:bardiajedi@gmail.com'>bardiajedi@gmail.com</A>"
			+ "</P>" + "</HTML>";
	
	private final String ABOUT = 
			""
			+ "Bardia Jedi\n"
			+ "\n"
			+ "Version" + VERSION + "\n"
			+ "ScRR - Starcraft Race Randomizer\n"
			+ "\u00A9 2015 Bardia \"Luviz\" Jedi\n"
			+ "java verson 1.8\n"
			+ "";
	
	ScrrCore sc;
	
	AnimationTimer timer;
	
	@FXML private ImageView raceIcon;
	@FXML private Button a;
	@FXML private ImageView xIcon;
	
	@FXML private StackedAreaChart<Integer, Integer> sac;
	
	@FXML private CheckMenuItem mTerran;
	@FXML private CheckMenuItem mZerg;
	@FXML private CheckMenuItem mProtoss;
	@FXML private CheckMenuItem mRandom;
	
	private Point p; //used to move the window
	
	private ArrayList<Image> imges;
	
	private int c = 0;	//counter for timer
	private int roll;	//
	
	/**
	 * call and initializes all member variables that needs to be called
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadImges();
		sc = new ScrrCore();
		setNewRaceSelection();
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (c < 3){
					setImgIcon(c++);
				}else{
					timer.stop();
					c =0;
					setImgIcon(roll);
				}
			}
		};
		
	}
	
	/**
	 * load all the images in gui/imges
	 */
	private void loadImges(){
		imges = new ArrayList<>(); 
		imges.add(new Image("gui/imges/t.png"));	//protuss
		imges.add(new Image("gui/imges/z.png"));	//tearrn
		imges.add(new Image("gui/imges/p.png"));	//Zerg
		imges.add(new Image("gui/imges/r.png"));	//Random
		imges.add(new Image("gui/imges/blank.png"));//blank
	}
	
	/**
	 * Events happens at pushing Randomise Race
	 */
	public void pushButton(){
		roll = sc.roll();
		if (roll != -1) {
			timer.start();
			setImgIcon(roll);
		}else{
			setImgIcon(4);
			
		}
		
	}
	
	/**
	 * shutdowns the application
	 */
	public void doExit(){
		System.out.println("#doExit");
		Platform.exit();
	}
	
	/**
	 * gets the current location of the window
	 * @param me
	 */
	public void grabP(MouseEvent me){
		System.out.println("a");
		p = new Point((int)me.getSceneX(),(int)me.getSceneY());
	}
	
	/**
	 * moves the application grabbing the menu
	 * @param me
	 */
	public void moveWindow(MouseEvent me) {
		raceIcon.getScene().getWindow().setX(me.getScreenX()-p.x);
		raceIcon.getScene().getWindow().setY(me.getScreenY()-p.y);
	}
	
	/**
	 * just a tester called my new Items remove this at release 
	 */
	public void test(){
		System.out.println("ok");
	}
	
	/**
	 * sets the on index of i
	 * @param i
	 */
	private void setImgIcon(int i){
		raceIcon.setImage(imges.get(i));
	}
	
	/**
	 * sets the selected races 
	 */
	public void setNewRaceSelection(){
		sc.setSkip(1, !mZerg.isSelected());
		sc.setSkip(0, !mTerran.isSelected());
		sc.setSkip(2, !mProtoss.isSelected());
		sc.setSkip(3, !mRandom.isSelected());
		System.out.println(sc.getSkip());
	}
	
	public void helpAbout(){
		System.out.println("help > about");
		Stage about = new Stage();
		about.initModality(Modality.APPLICATION_MODAL);
		
		
		
		Label text = new Label(ABOUT);
		text.setId("aboutLabel");
		

		Button okBtn = new Button("ok");
		okBtn.setAlignment(Pos.BOTTOM_CENTER);
		okBtn.setMaxSize(300, 300);
		
		okBtn.setOnAction((e)->about.close());
				
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		vBox.getChildren().addAll(text, okBtn);

		Scene s = new Scene(vBox);
		s.getStylesheets().add("/gui/style/main.css");
		about.initStyle(StageStyle.UNDECORATED);
		about.setScene(s);
		about.show();
		System.out.println(about.getWidth());
	}
}
