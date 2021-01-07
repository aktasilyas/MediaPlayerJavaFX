package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;

public class SampleController implements Initializable {
	
	@FXML private MediaView mv;
	private MediaPlayer mp;
	private Media me;
	@FXML Slider slider;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		String yolString=new File("src/media/Ahmet Kaya- Gül teninde güller bitmez...mp4").getAbsolutePath();
		me=new Media(new File(yolString).toURI().toString());
		mp=new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		//mp.setAutoPlay(true);
		
		DoubleProperty width=mv.fitWidthProperty();
		DoubleProperty height=mv.fitHeightProperty();
		width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
		slider.setValue(mp.getVolume()*100);
		slider.valueProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable arg0) {
			  mp.setVolume(slider.getValue()/100);
				
			}
		});
	}
	
	public void play(ActionEvent event) {
		mp.play();
		mp.setRate(1);
		
	}
	public void pause(ActionEvent event) {
		mp.pause();
		
	}
	public void fast(ActionEvent event) {
		mp.setRate(2);
		
	}
	public void slow(ActionEvent event) {
		mp.setRate(5);
		
	}
	public void reload(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.play();
		
	}
	public void start(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
		
	}	public void last(ActionEvent event) {
		mp.seek(mp.getTotalDuration());
		mp.stop();
		
	}
	
}
