package application;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class GuiController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private ImageView raceIcon;
	@FXML
	private Button a;
	@FXML
	private ImageView xIcon;
	
	private Point p;
	
	public void pushButton(){
		System.out.println("pushButton "+ raceIcon.getImage().getHeight());
		a.getScene().getWindow().setX(1);
		
	}
	
	public void doExit(){
		System.out.println("#doExit");
		Platform.exit();
	}
	
	public void grabP(MouseEvent me){
		System.out.println("a");
		p = new Point((int)me.getSceneX(),(int)me.getSceneY());
	}
	
	public void moveWindow(MouseEvent me) {
		raceIcon.getScene().getWindow().setX(me.getScreenX()-p.x);
		raceIcon.getScene().getWindow().setY(me.getScreenY()-p.y);
	}
	
	public void test(){
		System.out.println("ok");
		
	}
}
