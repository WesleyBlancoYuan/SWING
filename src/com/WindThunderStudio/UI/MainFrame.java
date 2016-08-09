package com.WindThunderStudio.UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.WindThunderStudio.logic.Calculator;
import com.WindThunderStudio.util.Constants;
import com.WindThunderStudio.util.PropertiesLoader;
import com.WindThunderStudio.util.Tool;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame{

	private JFrame frame;
	private Locale localeInSettings;
	public Locale getLocaleInSettings() {
		return localeInSettings;
	}

	public void setLocaleInSettings(Locale localeInSettings) {
		this.localeInSettings = localeInSettings;
	}

	private Properties prop;
	private JMenuBar menuBar;
	private JMenu mnFunctions;
	private JMenuItem mntmCalcular;
	private JMenuItem mntmExit;
	private JMenu mnNewMenu;
	//panel cabecera
	private JLabel lblCabecera;
	//panel izquierda
	private JLabel lblHyM1;
	private JLabel lblHyM2;
	private JLabel lblTiempoTotal;
	private JLabel lbl2p;
	private JLabel lblLunes;
	private JTextField lunesHorasEn;
	private JTextField lunesMinsEn;
	private JTextField lunesHorasSa;
	private JTextField lunesMinsSa;
	private JLabel lblTotalLunes;
	private JLabel lblMartes;
	private JTextField martesHorasEn;
	private JTextField martesMinsEn;
	private JTextField martesHorasSa;
	private JTextField martesMinsSa;
	private JLabel lblTotalMartes;
	private JLabel lblMiercoles;
	private JTextField mierHorasEn;
	private JTextField mierMinsEn;
	private JTextField mierHorasSa;
	private JTextField mierMinsSa;
	private JLabel lblTotalMier;
	private JLabel lblJueves;
	private JTextField juevesHorasEn;
	private JTextField juevesMinsEn;
	private JTextField juevesHorasSa;
	private JTextField juevesMinsSa;
	private JLabel lblTotalJueves;
	private JLabel lblViernes;
	private JTextField viernesHorasEn;
	private JTextField viernesMinsEn;
	private JTextField viernesHorasSa;
	private JTextField viernesMinsSa;
	private JLabel lblTotalVier;
	private JLabel lblSabado;
	private JTextField sabadoHorasEn;
	private JTextField sabadoMinsEn;
	private JTextField sabadoHorasSa;
	private JTextField sabadoMinsSa;
	private JLabel lblTotalSab;
	private JTextField domingoHorasEn;
	private JTextField domingoMinsEn;
	private JTextField domingoHorasSa;
	private JTextField domingoMinsSa;
	private JLabel lblTotalDomingo;
	private JLabel lblHorasTotales;
	private JComboBox<String> comboTotal;
	
	//panel derecha
	private JLabel lblEstacion;
	private JLabel lblHorasImp;
	private JLabel lblHorasImpRes;
	private JLabel lblDife;
	private JLabel lblDifeRes;

	private JButton btnCalcular;
	private Calculator calculator;
	
	private ResourceBundle bundle;
	private FocusAdapter adapter;
	
	protected ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		
	}
	public MainFrame(ResourceBundle bundle, Locale locale) {
		this();
		setBundle(bundle);
		setLocaleInSettings(locale);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle(bundle.getString(Constants.UI_TITLE));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//el label de dos puntos que va a usar en todos los sitios
		lbl2p = new JLabel(" : ");
		//las letras comunes
		Font tahoma15normal = new Font("Tahoma", Font.PLAIN, 15);
		Font tahoma15bold = new Font("Tahoma", Font.BOLD, 15);
		lbl2p.setFont(tahoma15normal);
		
		//FocusAdapter para todos los textfield.
		adapter = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (e.getComponent() != null){
					JTextField tf = (JTextField) e.getComponent();
					tf.selectAll();
				}
			}
		};

//		menuBar = new JMenuBar();
//		getFrame().setJMenuBar(menuBar);
//		menuBar.add(getMnFunctions());
//		menuBar.add(getMnNewMenu());
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("fill, insets 5 5 5 5","[]","[center, 10%]10[top, 70%]20[center, 20%]"));
		//matrix of 1 column and 2 rows
		
		//el panel de header
		JPanel pHeader = new JPanel();
		pHeader.setLayout(new MigLayout("fill","[]","[center]"));
		
		lblCabecera = new JLabel(getBundle().getString(Constants.TITLE_HEADER));
		lblCabecera.setFont(tahoma15normal);
		pHeader.add(lblCabecera,"align left, grow");
		
		panel.add(pHeader,"cell 0 0");
		
		
		//panel de contenido, lo de los texifield y resultados
		JPanel pContent = new JPanel();
		pContent.setLayout(new MigLayout("fill","[75%, align left]10[25%, align left]","[center]"));
		
		//el panel de las horas y los minutos, abajo izquierda
		final JPanel pIzq = new JPanel();
		pIzq.setLayout(new MigLayout("fill","[right]10[center, 25%, shrink]5[center, 25%, shrink]10[center]","[]10[]5[]5[]5[]5[]5[]5[]10[]"));
		
		

		//titulo de "horas : minutos"
		lblHyM1 = new JLabel(getBundle().getString(Constants.TITLE_HOURS_AND_MINUTES));
		lblHyM1.setFont(tahoma15normal);
		lblHyM2 = new JLabel(getBundle().getString(Constants.TITLE_HOURS_AND_MINUTES));
		lblHyM2.setFont(tahoma15normal);
		pIzq.add(lblHyM1, "cell 1 0");
		pIzq.add(lblHyM2, "cell 2 0, align center");
		
		//titulo de "horas imputadas"
		lblTiempoTotal = new JLabel(getBundle().getString(Constants.TITLE_DAILY_TOTAL));
		lblTiempoTotal.setFont(tahoma15normal);
		pIzq.add(lblTiempoTotal, "cell 3 0");
		
		//linea de lunes
		lblLunes = new JLabel(getBundle().getString(Constants.TITLE_MONDAY));
		lblLunes.setFont(tahoma15normal);
		pIzq.add(lblLunes, "cell 0 1");
		
		
		lunesHorasEn = createTextField();
		
		lunesMinsEn = createTextField();
		
		lunesHorasSa = createTextField();
		
		lunesMinsSa = createTextField();
		
		pIzq.add(lunesHorasEn, "cell 1 1, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 1, align 50%"); //mid
		pIzq.add(lunesMinsEn, "cell 1 1, align 100%, grow"); //right
		
		pIzq.add(lunesHorasSa, "cell 2 1, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 1, align 50%"); //mid
		pIzq.add(lunesMinsSa, "cell 2 1, align 100%, grow"); //right
		
		//las horas imputadas totales de lunes, resultado de calculo.
		lblTotalLunes = new JLabel();
		lblTotalLunes.setFont(tahoma15normal);
		pIzq.add(lblTotalLunes, "cell 3 1");
		
		//-- fin de lunes --
		
		//linea de martes
		lblMartes = new JLabel(getBundle().getString(Constants.TITLE_THUESDAY));
		lblMartes.setFont(tahoma15normal);
		pIzq.add(lblMartes, "cell 0 2");
		
		martesHorasEn = createTextField();
		
		martesMinsEn = createTextField();
		
		martesHorasSa = createTextField();
		
		martesMinsSa = createTextField();
		
		pIzq.add(martesHorasEn, "cell 1 2, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 2, align 50%"); //mid
		pIzq.add(martesMinsEn, "cell 1 2, align 100%, grow"); //right
		
		pIzq.add(martesHorasSa, "cell 2 2, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 2, align 50%"); //mid
		pIzq.add(martesMinsSa, "cell 2 2, align 100%, grow"); //right
		
		//las horas imputadas totales de martes, como resultado de calculo.
		lblTotalMartes = new JLabel();
		lblTotalMartes.setFont(tahoma15normal);
		pIzq.add(lblTotalMartes, "cell 3 2");
		
		//-- fin de martes --
		
		//linea de miercoles
		lblMiercoles = new JLabel(getBundle().getString(Constants.TITLE_WENESDAY));
		lblMiercoles.setFont(tahoma15normal);
		pIzq.add(lblMiercoles, "cell 0 3");
		
		mierHorasEn = createTextField();
		
		mierMinsEn = createTextField();
		
		mierHorasSa = createTextField();
		
		mierMinsSa = createTextField();
		
		pIzq.add(mierHorasEn, "cell 1 3, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 3, align 50%"); //mid
		pIzq.add(mierMinsEn, "cell 1 3, align 100%, grow"); //right
		
		pIzq.add(mierHorasSa, "cell 2 3, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 3, align 50%"); //mid
		pIzq.add(mierMinsSa, "cell 2 3, align 100%, grow"); //right
		
		//las horas imputadas totales de miercoles, como resultado de calculo.
		lblTotalMier = new JLabel();
		lblTotalMier.setFont(tahoma15normal);
		pIzq.add(lblTotalMier, "cell 3 3");
		//-- fin de miercoles --
		
		//linea de jueves
		lblJueves = new JLabel(getBundle().getString(Constants.TITLE_THURSDAY));
		lblJueves.setFont(tahoma15normal);
		pIzq.add(lblJueves, "cell 0 4");
		
		juevesHorasEn = createTextField();
		
		juevesMinsEn = createTextField();
		
		juevesHorasSa = createTextField();
		
		juevesMinsSa = createTextField();
		
		pIzq.add(juevesHorasEn, "cell 1 4, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 4, align 50%"); //mid
		pIzq.add(juevesMinsEn, "cell 1 4, align 100%, grow"); //right
		
		pIzq.add(juevesHorasSa, "cell 2 4, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 4, align 50%"); //mid
		pIzq.add(juevesMinsSa, "cell 2 4, align 100%, grow"); //right
		
		//las horas imputadas totales de jueves, como resultado de calculo.
		lblTotalJueves = new JLabel();
		lblTotalJueves.setFont(tahoma15normal);
		pIzq.add(lblTotalJueves, "cell 3 4");
		//-- fin de jueves --
		
		//linea de viernes
		lblViernes = new JLabel(getBundle().getString(Constants.TITLE_FRIDAY));
		lblViernes.setFont(tahoma15normal);
		pIzq.add(lblViernes, "cell 0 5");
		
		viernesHorasEn = createTextField();
		
		viernesMinsEn = createTextField();
		
		viernesHorasSa = createTextField();
		
		viernesMinsSa = createTextField();
		
		pIzq.add(viernesHorasEn, "cell 1 5, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 5, align 50%"); //mid
		pIzq.add(viernesMinsEn, "cell 1 5, align 100%, grow"); //right
		
		pIzq.add(viernesHorasSa, "cell 2 5, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 5, align 50%"); //mid
		pIzq.add(viernesMinsSa, "cell 2 5, align 100%, grow"); //right
		
		//las horas imputadas totales de viernes, como resultado de calculo.
		lblTotalVier = new JLabel();
		lblTotalVier.setFont(tahoma15normal);
		pIzq.add(lblTotalVier, "cell 3 5");
		//-- fin de viernes --
		
		//linea de sabado
		lblSabado = new JLabel(getBundle().getString(Constants.TITLE_SATURDAY));
		lblSabado.setFont(tahoma15normal);
		pIzq.add(lblSabado, "cell 0 6");
		
		sabadoHorasEn = createTextField();
		
		sabadoMinsEn = createTextField();
		
		sabadoHorasSa = createTextField();
		
		sabadoMinsSa = createTextField();
		
		pIzq.add(sabadoHorasEn, "cell 1 6, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 6, align 50%"); //mid
		pIzq.add(sabadoMinsEn, "cell 1 6, align 100%, grow"); //right
		
		pIzq.add(sabadoHorasSa, "cell 2 6, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 6, align 50%"); //mid
		pIzq.add(sabadoMinsSa, "cell 2 6, align 100%, grow"); //right
		
		//las horas imputadas totales de sabado, como resultado de calculo.
		lblTotalSab = new JLabel();
		lblTotalSab.setFont(tahoma15normal);
		pIzq.add(lblTotalSab, "cell 3 6");
		//-- fin de sabado --
		
		//linea de domingo
		lblTotalDomingo = new JLabel(getBundle().getString(Constants.TITLE_SUNDAY));
		lblTotalDomingo.setFont(tahoma15normal);
		pIzq.add(lblTotalDomingo, "cell 0 7");
		
		domingoHorasEn = createTextField();
		
		domingoMinsEn = createTextField();
		
		domingoHorasSa = createTextField();
		
		domingoMinsSa = createTextField();
		
		pIzq.add(domingoHorasEn, "cell 1 7, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 1 7, align 50%"); //mid
		pIzq.add(domingoMinsEn, "cell 1 7, align 100%, grow"); //right
		
		pIzq.add(domingoHorasSa, "cell 2 7, align 0%, grow"); //left
		pIzq.add(createLabel(" : "), "cell 2 7, align 50%"); //mid
		pIzq.add(domingoMinsSa, "cell 2 7, align 100%, grow"); //right
		
		//las horas imputadas totales de domingo, como resultado de calculo.
		lblTotalDomingo = new JLabel();
		lblTotalDomingo.setFont(tahoma15normal);
		pIzq.add(lblTotalDomingo, "cell 3 7");
		//-- fin de domingo --
		
		//configuracion con un combo, el numero de horas a trabajar en una semana
		lblHorasTotales = new JLabel(getBundle().getString(Constants.TITLE_TOTAL_IN_WEEK));
		lblHorasTotales.setFont(tahoma15normal);
		pIzq.add(lblHorasTotales, "cell 0 8, span 3, align right");
		
		comboTotal = new JComboBox<String>();
		comboTotal.setEditable(true);
		comboTotal.setMaximumRowCount(3);
		comboTotal.setFont(tahoma15normal);
		comboTotal.setModel(new DefaultComboBoxModel<String>(new String[] {"40", "35"}));
		comboTotal.setSelectedIndex(0);
		pIzq.add(comboTotal, "cell 3 8, span 2, align left");
		
		pContent.add(pIzq, "cell 0 0, grow");
		//add to row 2 of panel, split this row, so the next 1 component can be added to this row
		
		//fin de panel de izquierda
		
		//panel a la derecha, para resultados de calculo de saldo de horas,
		//y el boton de calcular.
		final JPanel panelDer = new JPanel();
		panelDer.setLayout(new MigLayout("fill","[left]","[]15[]15[]"));
		
		if (Tool.horarioVerano(localeInSettings)) {
			lblEstacion = new JLabel(getBundle().getString(Constants.TEXT_SEASON_SUMMER));
		} else {
			lblEstacion = new JLabel(getBundle().getString(Constants.TEXT_SEASON_WINTER));
		}
		lblEstacion.setFont(tahoma15normal);
		panelDer.add(lblEstacion,"cell 0 0, align left");
		
		
		lblHorasImp = new JLabel(getBundle().getString(Constants.TITLE_CLOCK_IN_HOURS));
		lblHorasImp.setFont(tahoma15normal);
		panelDer.add(lblHorasImp, "cell 0 1, align left, split 1");
		//dividir el cell 0 1 a dos columnas, permitir el siguiente 1 elemento agregado a la celda.
		lblHorasImpRes = new JLabel("00:00");
		lblHorasImpRes.setFont(tahoma15normal);
		panelDer.add(lblHorasImpRes, "cell 0 1, align 40%");
		//anadir el resultado de horas imputadas a la misma celda.
		
		lblDife = new JLabel(getBundle().getString(Constants.TITLE_DIFFERENCE));
		lblDife.setFont(tahoma15normal);
		panelDer.add(lblDife, "cell 0 2, split 1, align left");
		
		lblDifeRes = new JLabel();
		lblDifeRes.setFont(tahoma15normal);
		panelDer.add(lblDifeRes, "cell 0 2, wrap");
		// fin de config de panel a la derecha.
		
		//agregar el de derecha y de izq al panel contenido
		pContent.add(panelDer, "cell 1 0, grow");
		//agregar el panel contenido al panel principal
		panel.add(pContent, "cell 0 1, grow");
		
		//button de calculacion. linea 2, row 0 del panel principal.
		btnCalcular = new JButton(getBundle().getString(Constants.BTN_CALCULATE));
		btnCalcular.setFont(tahoma15normal);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				calculator = new Calculator();
				String[] horas1 = new String[7];
				String[] horas2 = new String[7];
				String[] mins1 = new String[7];
				String[] mins2 = new String[7];
				
				horas1[0] = lunesHorasEn.getText();
				horas1[1] = martesHorasEn.getText();
				horas1[2] = mierHorasEn.getText();
				horas1[3] = juevesHorasEn.getText();
				horas1[4] = viernesHorasEn.getText();
				horas1[5] = sabadoHorasEn.getText();
				horas1[6] = domingoHorasEn.getText();
				mins1[0] = lunesMinsEn.getText();
				mins1[1] = martesMinsEn.getText();
				mins1[2] = mierMinsEn.getText();
				mins1[3] = juevesMinsEn.getText();
				mins1[4] = viernesMinsEn.getText();
				mins1[5] = sabadoMinsEn.getText();
				mins1[6] = domingoMinsEn.getText();
				
				horas2[0] = lunesHorasSa.getText();
				horas2[1] = martesHorasSa.getText();
				horas2[2] = mierHorasSa.getText();
				horas2[3] = juevesHorasSa.getText();
				horas2[4] = viernesHorasSa.getText();
				horas2[5] = sabadoHorasSa.getText();
				horas2[6] = domingoHorasSa.getText();
				mins2[0] = lunesMinsSa.getText();
				mins2[1] = martesMinsSa.getText();
				mins2[2] = mierMinsSa.getText();
				mins2[3] = juevesMinsSa.getText();
				mins2[4] = viernesMinsSa.getText();
				mins2[5] = sabadoMinsSa.getText();
				mins2[6] = domingoMinsSa.getText();
				
				
				calculator.setHoras1(horas1);
				calculator.setMinutos1(mins1);
				calculator.setHoras2(horas2);
				calculator.setMinutos2(mins2);
				
				//guardar este array de minutos, para calcular el saldo de la semana.
				int[] diffEnMins = new int[7];
				diffEnMins = calculator.calHorasImputadas(localeInSettings);
				
				int hourPart = 0;
				int minutePart = 0;
				
				// you cannot use (int) (((double)hour) / 60)
				// if it's 15:59, the hour part will be 16. Must always round down.
				// so, you can minus mod, and then divide.
				minutePart = diffEnMins[0] % 60;
				hourPart = (diffEnMins[0] - minutePart) / 60;
				lblTotalLunes.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				minutePart = diffEnMins[1] % 60;
				hourPart = (diffEnMins[1] - minutePart) / 60;
				lblTotalMartes.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				minutePart = diffEnMins[2] % 60;
				hourPart = (diffEnMins[2] - minutePart) / 60;
				lblTotalMier.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				minutePart = diffEnMins[3] % 60;
				hourPart = (diffEnMins[3] - minutePart) / 60;
				lblTotalJueves.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				minutePart = diffEnMins[4] % 60;
				hourPart = (diffEnMins[4] - minutePart) / 60;
				lblTotalVier.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				minutePart = diffEnMins[5] % 60;
				hourPart = (diffEnMins[5] - minutePart) / 60;
				lblTotalSab.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				minutePart = diffEnMins[6] % 60;
				hourPart = (diffEnMins[6] - minutePart) / 60;
				lblTotalDomingo.setText(String.valueOf(hourPart) + " : " + ((minutePart < 10)?"0":"") + String.valueOf(minutePart));
				
				lblDifeRes.setText(saldoSemanal(diffEnMins));
				
				panelDer.updateUI();
			}
		});
		
		panel.add(btnCalcular, "cell 0 2, align right, w 100!");
		
		//fin de config de panel principal
		
		//anadir el panel principal al contentPane del frame
		getContentPane().add(panel);
		setResizable(true);
		setVisible(true);
		pack();
		setBounds(0,0,900,450);
		setLocationRelativeTo(null);
	}
	
	
	

	private String saldoSemanal(int[] diffEnMins) {
		int totalSemanal = 0;
		for (int i=0; i<diffEnMins.length; i++){
			totalSemanal += diffEnMins[i];
		}
		
		String impResult = "";
		impResult = Tool.convertMinsToHours(totalSemanal);
		//refresh el view
		lblHorasImpRes.setText(impResult);
		//en el combo es horas. hay que ser minutos.
		int totalDebido = Integer.valueOf(comboTotal.getSelectedItem().toString()) * 60;
		return Tool.convertMinsToHours(totalDebido - totalSemanal);
	}
	
	private JLabel createLabel(String name) {
		JLabel label = new JLabel(name);
		return label;
	}
	
	private JTextField createTextField() {
		JTextField tf = new JTextField();
		if (adapter != null) {
			tf.addFocusListener(adapter);
		}
		tf.setAlignmentX(RIGHT_ALIGNMENT);
		return tf;
	}
//	private JMenu getMnFunctions() {
//		if (mnFunctions == null) {
//			mnFunctions = new JMenu("Functions");
//			mnFunctions.add(getMntmCalcular());
//			mnFunctions.add(getMntmExit());
//		}
//		return mnFunctions;
//	}
//	private JMenuItem getMntmCalcular() {
//		if (mntmCalcular == null) {
//			mntmCalcular = new JMenuItem("Calcular");
//			mntmCalcular.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					calculator = new Calculator();
//					String[] horasEn = new String[7];
//					String[] minsEn = new String[7];
////					
////					horas[0] = textFieldLunesHorasEn.getText();
////					horas[1] = martesHorasEn.getText();
////					horas[2] = mierHorasEn.getText();
////					horas[3] = juevesHorasEn.getText();
////					horas[4] = viernesHorasEn.getText();
////					horas[5] = sabadoHorasEn.getText();
////					horas[6] = domingoHorasEn.getText();
////					
////					mins[0] = lunesMinsEn.getText();
////					mins[1] = martesMinsEn.getText();
////					mins[2] = mierMinsEn.getText();
////					mins[3] = juevesMinsEn.getText();
////					mins[4] = viernesMinsEn.getText();
////					mins[5] = sabadoMinsEn.getText();
////					mins[6] = domingoMinsEn.getText();
////					
////					calculator.setHoras(horas);
////					calculator.setMinutos(mins);
//					
//					int[] horasYMins = new int[2];
//					horasYMins = calculator.calHorasImputadas(localeInSettings);
////					horasImpuResult.setText(String.valueOf(horasYMins[0]) + ":" + ((horasYMins[1] < 10) ? "0" : "") + String.valueOf(horasYMins[1]));
////					difResult.setText(totalMenosHorasImputadas(textFieldHorasTotales.getSelectedItem().toString(), horasYMins[0], horasYMins[1]));
//				}
//			});
//		}
//		return mntmCalcular;
//	}
//	private JMenuItem getMntmExit() {
//		if (mntmExit == null) {
//			mntmExit = new JMenuItem("Exit");
//			mntmCalcular.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					System.exit(0);
//				}
//			});
//		}
//		return mntmExit;
//	}
//	private JMenu getMnNewMenu() {
//		if (mnNewMenu == null) {
//			mnNewMenu = new JMenu("About...");
//		}
//		return mnNewMenu;
//	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
