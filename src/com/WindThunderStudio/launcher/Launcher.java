package com.WindThunderStudio.launcher;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.WindThunderStudio.UI.MainFrame;
import com.WindThunderStudio.util.Constants;
import com.WindThunderStudio.util.PropertiesLoader;
import com.WindThunderStudio.util.Tool;

public class Launcher {

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						ResourceBundle bundle = Tool.loadLanguageAndLocale();
						//bundle must be passed upon creation of the object,
						//because in initialize() method it is used already.
						//cannot set bundle after constructor.
						MainFrame window = new MainFrame(bundle, bundle.getLocale());
							
					} catch (Exception e) {
							e.printStackTrace();
					}
			}
		});
	}
}
