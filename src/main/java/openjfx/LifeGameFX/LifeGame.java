package openjfx.LifeGameFX;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LifeGame {

	private StackPane root=new StackPane();
	private GridPane grid=new GridPane();
	private Label[][] lbls;
	private int l;
	private Color a,b;
	
	private void sizelbls() {
		for (int i = 0; i/l < lbls.length; i++) {

			lbls[i/l][i%l].setMinHeight(root.getHeight()/l);
			lbls[i/l][i%l].setMinWidth(root.getHeight()/l);
		}
	}
	private boolean rule1(int y,int x) {

		int count=-1;
		for (int i = y-1; i < y+2; i++) {
			for (int j = x-1; j < x+2; j++) {
				if(lbls[i][j].getBackground().getFills().get(0).getFill()==b) {
					count++;
				}
			}
		}
		return count<2||count>3?true:false;
	}

	private boolean rule2(int y,int x) {

		int count=0;
		for (int i = y-1; i < y+2; i++) {
			for (int j = x-1; j < x+2; j++) {
				if(lbls[i][j].getBackground().getFills().get(0).getFill()==b) {
					count++;
				}
			}
		}
		return count==3?true:false;
	}

	private void drawGame() {

		for (int i = 1; i < lbls.length-1; i++) {
			for (int j = 1; j < lbls.length-1; j++) {

				if(rule1(i,j)&&lbls[i][j].getBackground().getFills().get(0).getFill()==b) {
					lbls[i][j].setBackground(new Background(new BackgroundFill(a, null, null)));
				}
				else if(rule2(i,j)&&lbls[i][j].getBackground().getFills().get(0).getFill()==a) {
					lbls[i][j].setBackground(new Background(new BackgroundFill(b, null, null)));
				}

			}
		}
	}

	LifeGame(int l,Color a,Color b){

		this.l=l;this.a=a;this.b=b;
		lbls=new Label[l][l];
		for (int i = 0; i/l < lbls.length; i++) {

			lbls[i/l][i%l]=new Label();
			lbls[i/l][i%l].setBackground(new Background(new BackgroundFill(a, null, null)));
			if((int)(Math.random()*4)==2) {
				lbls[i/l][i%l].setBackground(new Background(new BackgroundFill(b, null, null)));
			}

			grid.add(lbls[i/l][i%l], i%l, i/l);
		}
		root.setBackground(new Background(new BackgroundFill(a, null, null)));
		root.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);

		root.heightProperty().addListener(e->sizelbls());

		var timer=new Timeline(new KeyFrame(Duration.millis(160), e->{
			drawGame();
		}));timer.setCycleCount(Animation.INDEFINITE);timer.play();
		var holetimer=new Timeline(new KeyFrame(Duration.millis(1200), e->{
			makeHoles((int)(Math.random()*(l-50)+25),(int)(Math.random()*(l-50)+25));
		}));holetimer.setCycleCount(Animation.INDEFINITE);holetimer.play();
	
	}
	
	
	private void makeHoles(int y,int x) {
		
		for (int i = y-15; i < y+15; i++) {
			for (int j = x-15; j < x+15; j++) {
				lbls[i][j].setBackground(new Background(new BackgroundFill(a, null, null)));
			}
		}
	}

	public StackPane givGame() {
		return root;
	}

}
