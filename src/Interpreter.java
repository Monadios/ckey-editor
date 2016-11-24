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
    public static final String[] NO_ARGS = {};

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

    public void run( String cmd, String[] args )
    {
        if ( args == null ) {
            args = NO_ARGS;
        }
        script.invokeMethod( cmd, args );
    }
}
