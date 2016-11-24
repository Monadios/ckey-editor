import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Server
{
    private ServerSocket server;
    private Socket connection = null;
    private BufferedReader in;
    private Object message;
    private int port;

    Server( int pn )
    {
        port = pn;
    }

    public void run()
    {
        //1. creating a server socket
        try {
            server = new ServerSocket( port );
            //2. Wait for connection
            System.out.println( "Waiting for connection" );
            connection = server.accept();
            System.out.println( "Connection received from " + connection.getInetAddress().getHostName() );
            //3. get Input and Output streams
            in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
            //4. The two parts communicate via the input and output streams

            try {
                message = in.readLine();
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String[]>>()
                {
                }.getType();
                LinkedTreeMap<String, String[]> map = gson.fromJson( message.toString(), type );
                List<String> cmds = Arrays.asList( map.get( "script" ) );
                Interpreter i = new Interpreter();
                List<Command> commands = cmds.stream().map(x -> new Command( x )).collect( Collectors.toList());
                commands.forEach( cmd -> i.run( cmd ) );
                server.close();
            } catch ( IOException ioException ) {
                server.close();
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
