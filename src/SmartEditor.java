import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SmartEditor extends Application
{
    private Server server;


    @Override
    public void start( Stage stage ) throws Exception
    {
        server = new Server( 7216 );

        stage.setTitle( "SmartEditor" );
        final StackPane root = new StackPane();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        final Scene scene = new Scene( root, bounds.getWidth(), bounds.getHeight() );
        Color c = Color.TRANSPARENT; //new Color( 255,255,255,255 );
        scene.setFill( c );

        server.run();
        System.out.println(server.result);


        System.out.println( "Out of server" );

        stage.setScene( scene );
    }

    public static void main( String[] args )
    {
        launch( args );
    }
}
