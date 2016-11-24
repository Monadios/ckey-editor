import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;
import java.io.IOException;

/**
 * Created by gustav on 11/24/16.
 */
public class Interpreter
{
    private Script script;
    private GroovyShell shell;

    public Interpreter()
    {
        shell = new GroovyShell();
        File p = new File( "src/Scripting.groovy" );
        try {
            script = shell.parse( p );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public void run( Command cmd )
    {
        script.invokeMethod( cmd.getCommandName(), cmd.getArgs() );
    }
}
