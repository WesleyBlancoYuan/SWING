package com.WindThunderStudio.launcher;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.WindThunderStudio.UI.MainFrame;
import com.WindThunderStudio.util.Constants;
import com.WindThunderStudio.util.PropertiesLoader;

public class Launcher {
	private static String locale;
	private static Locale localeInSettings;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//read i18n file
					Properties prop = new PropertiesLoader().getSettings();
					String baseName = "";
					ResourceBundle bundle = null;
					if (prop != null) {
						try {
							locale = prop.getProperty(Constants.CONFIG_KEY_LOCALE);
							baseName = prop.getProperty(Constants.CONFIG_I18N_BASENAME);
							switch (locale) {
							case Constants.PROP_KEY_LOCALE_ES:
								localeInSettings = new Locale("es", "ES");
								break;
							case Constants.PROP_KEY_LOCALE_EN:
								localeInSettings = new Locale("en", "US");
								break;
							case Constants.PROP_KEY_LOCALE_CN:
								localeInSettings = new Locale("zh", "CN");
								break;
							default:
								localeInSettings = new Locale("es", "ES");
								break;
							}
							//base name should be fully qualified name, with ".". But, for backward compatility,
							// "/" is not descarted and can work.
							bundle = ResourceBundle.getBundle(baseName,localeInSettings);
							
							//bundle must be passed upon creation of the object,
							//because in initialize() method it is used already.
							//cannot set bundle after constructor.
							MainFrame window = new MainFrame(bundle, localeInSettings);
							
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
						
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
