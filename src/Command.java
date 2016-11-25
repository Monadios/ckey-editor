import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gustav on 11/24/16.
 */

/*
* Perhaps this class should extend a hashmap?
* */

public class Command
{
     private String commandName;
    private String[] args;
    public static final String[] NO_ARGS = {};


    public Command(String cmd){
        String[] toks = parseCommand( cmd );
        commandName = toks[0];
        args = (toks.length < 2 ? NO_ARGS : Arrays.copyOfRange(toks,1,toks.length));
    }

    public String[] parseCommand(String cmd){
        String[] tokens = cmd.split(" ");
        return tokens;
    }

    public String getCommandName()
    {
        return commandName;
    }

    public void setCommandName( String commandName )
    {
        this.commandName = commandName;
    }

    public String[] getArgs()
    {
        return args;
    }

    public void setArgs( String[] args )
    {
        this.args = args;
    }
}
