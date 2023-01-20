 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import social.accse.gui.SocialNpane;

/**
 * 
 */

/**
 * @author hatal
 *
 */
public class Main  extends Application
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
        launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		primaryStage.setTitle("Social Network");
		SocialNpane snp = new SocialNpane();
		Scene scn = new Scene(snp, 800, 800);

		primaryStage.setScene(scn);
		primaryStage.show();
	}

}
