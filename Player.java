package application;

//import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {
	
	Media media;
	MediaPlayer player;
	MediaView view;
	Pane mp;
	MediaBar playBar;
	
	public Player(String file){
		
		media = new Media(file);
		player = new MediaPlayer(media);
		view = new MediaView(player);
		
		mp = new Pane();
		mp.getChildren().add(view);
		
		setCenter(mp);
				
		playBar = new MediaBar(player);
		setBottom(playBar);
		
		setStyle("-fx-background-color: transparent");
		
		player.play();
		
	}

}
