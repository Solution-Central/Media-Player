package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

public class MediaBar extends HBox {
	
	Slider time = new Slider();
	Slider vol = new Slider();
	
	Button playButton;
	
	Label volume = new Label("Volume");
	
	MediaPlayer player;
	
	public MediaBar(MediaPlayer play){
		
		ImageView playImage = new ImageView(new Image("file:src/application/Images/Play.png"));
		playImage.setFitHeight(32);
		playImage.setFitWidth(32);
		
		ImageView pauseImage = new ImageView(new Image("file:src/application/Images/Pause.png"));
		pauseImage.setFitHeight(32);
		pauseImage.setFitWidth(32);
		playButton = new Button("", pauseImage);
		playButton.setStyle(" -fx-background-color: TRANSPARENT");
		
		player = play;
		
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5,10,5,10));
		
		vol.setPrefWidth(70);
		vol.setMinWidth(30);
		vol.setValue(100);
		
		volume.setTextFill(Color.WHITE);
		
		HBox.setHgrow(time, Priority.ALWAYS);
		
		playButton.setPrefWidth(30);
		playButton.setContentDisplay(ContentDisplay.TOP);
		
		getChildren().add(playButton);
		getChildren().add(time);
		getChildren().add(volume);
		getChildren().add(vol);
		
		playButton.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				Status stat = player.getStatus();
				
				if(stat == Status.PLAYING){
					if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
						player.seek(player.getStartTime());
						player.play();
					}
					else {
						player.pause();
						playButton.setGraphic(playImage);
					}
				}
				
				if(stat == Status.PAUSED || stat == Status.HALTED || stat == Status.STOPPED)
				{
					player.play();
					playButton.setGraphic(pauseImage);
				}
			}
		});
		
		player.currentTimeProperty().addListener(new InvalidationListener(){
			public void invalidated(Observable ob){
				updateValue();
			}
		});
		
		time.valueProperty().addListener(new InvalidationListener(){
			
			public void invalidated(Observable ob){
				if(time.isPressed()){
					player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
				}
			}
		});
		
		vol.valueProperty().addListener(new InvalidationListener(){
			
			public void invalidated(Observable ob){
				if(vol.isPressed()){
					player.setVolume(vol.getValue()/100);
				}
			}
		});
		
	}
	
	public void updateValue()
	{
		Platform.runLater(new Runnable(){
			public void run(){
				time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
			}
		});
	}
	
	

}
