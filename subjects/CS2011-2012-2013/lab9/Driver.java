package lab9;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Driver extends Application{
	
	MazeGUIPane map=new MazeGUIPane();
	
	public void start(Stage Primarystage)
	{
		Scene sc=new Scene(map);
		sc.getStylesheets().add("Style/Styles.css");
		map.start(Primarystage);
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	

}
