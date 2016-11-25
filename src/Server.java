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
import java.util.Optional;
import java.util.stream.Collectors;

public class Server
{
    private ServerSocket server;
    private Socket connection = null;
    private BufferedReader in;
    public String result;
    private int port;

    Server( int pn )
    {
        port = pn;
    }

    public void run()
    {
        Optional<String> recMsg = readMsg();
        // TODO: Replace this entire code block with write- and readObject functions
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String[]>>()
        {
        }.getType();
        LinkedTreeMap<String, String[]> map = new LinkedTreeMap<>();

        if(recMsg.isPresent()) {
            map = gson.fromJson( recMsg.get(), type );
        }else{
            //?
        }

        List<String> cmds = Arrays.asList( map.get( "script" ) );
        result = String.join( " ", cmds );
        Interpreter i = new Interpreter();
        List<Command> commands = cmds.stream().map( x -> new Command( x ) ).collect( Collectors.toList() );
        commands.forEach( cmd -> i.run( cmd ) );

        try {
            server.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private Optional<String> readMsg()
    {
        Optional<String> msg = Optional.empty();
        String message;
        try {
            server = new ServerSocket( port );
            System.out.println( "Waiting for connection" );
            connection = server.accept();
            System.out.println( "Connection received from " + connection.getInetAddress().getHostName() );
            in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
            message = in.readLine();
            msg = Optional.of(message);
        } catch ( IOException e ) {
            e.printStackTrace();
        }finally {
            return msg;
        }
    }

    private class NoMessageReceivedException extends Exception
    {
        public NoMessageReceivedException(String msg)
        {
            super(msg);
        }
    }
}