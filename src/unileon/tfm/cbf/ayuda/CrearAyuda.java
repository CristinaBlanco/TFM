package unileon.tfm.cbf.ayuda;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JButton;

/**
 * Created by Cristina Blanco Fernandez.
 */
public class CrearAyuda {

        public void crearAyuda(JButton boton)
        {
            try
            {
                File fichero = new File("./help/help_set.hs");
                URL hsURL = fichero.toURI().toURL();

                HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
                HelpBroker hb = helpset.createHelpBroker();

                hb.enableHelpOnButton(boton, "ayuda", helpset);
            }
            catch (MalformedURLException|HelpSetException|IllegalArgumentException e) {}
        }
}
