import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class FunctionPanel extends JPanel implements ItemListener, ActionListener
{
  private JPanel f_panel;
  private JComboBox function;
  private JLabel function_label;
  private JPanel tmr_panel;
  private JComboBox tmr_channel;
  private JLabel tmr_channel_label;
  private JPanel TGR_panel;
  private JComboBox TGR_choice;
  private JLabel TGR_choice_label;
  private JPanel TIOCA_options_panel;
  private JComboBox TIOCA_settings;
  private JLabel TIOCA_label;
  private JPanel TIOCB_options_panel;
  private JComboBox TIOCB_settings;
  private JLabel TIOCB_label;
  private JPanel TIOCC_options_panel;
  private JComboBox TIOCC_settings;
  private JLabel TIOCC_label;
  private JPanel TIOCD_options_panel;
  private JComboBox TIOCD_settings;
  private JLabel TIOCD_label;
  private JPanel TGRA_panel;
  private JTextField TGRA_val;
  private JLabel TGRA_label;
  private JPanel TGRB_panel;
  private JTextField TGRB_val;
  private JLabel TGRB_label;
  private JPanel TGRC_panel;
  private JTextField TGRC_val;
  private JLabel TGRC_label;
  private JPanel TGRD_panel;
  private JTextField TGRD_val;
  private JLabel TGRD_label;
  private FunctionPanel.TGR_verifier verifier;
  private JComboBox INTR;
  private JLabel INTR_label;
  private JButton ret;
  private JButton ret_clr;
  private JPanel intr_button_panel;
  private JPanel INTR_panel;
  private JLabel TCNT_label;
  private JTextField TCNT_val;
  private JPanel TCNT_panel;
  private JPanel buttons_panel;
  private JLabel TIOCA_buttons_label;
  private JPanel TIOCA_buttons_panel;
  private JPanel TIOCA_panel;
  private JLabel TIOCB_buttons_label;
  private JPanel TIOCB_buttons_panel;
  private JPanel TIOCB_panel;
  private JLabel TIOCC_buttons_label;
  private JPanel TIOCC_buttons_panel;
  private JPanel TIOCC_panel;
  private JLabel TIOCD_buttons_label;
  private JPanel TIOCD_buttons_panel;
  private JPanel TIOCD_panel;
  private JButton TIOCA_posedge;
  private JButton TIOCB_posedge;
  private JButton TIOCC_posedge;
  private JButton TIOCD_posedge;
  private JButton TIOCA_negedge;
  private JButton TIOCB_negedge;
  private JButton TIOCC_negedge;
  private JButton TIOCD_negedge;
  URL negedgeUrl = getClass().getClassLoader().getResource("images/negedge.gif");
  ImageIcon nedge = new ImageIcon(this.negedgeUrl);
  URL posedgeUrl = getClass().getClassLoader().getResource("images/posedge.gif");
  ImageIcon pedge = new ImageIcon(this.posedgeUrl);
  
  boolean compareMatch;
  
  boolean inputCapture;
  
  public FunctionPanel()
  {
    init();
  }
  


  public void init()
  {
    Border loweredetched = BorderFactory.createEtchedBorder(1);
    
    this.compareMatch = false;
    this.inputCapture = false;
    this.verifier = new FunctionPanel.TGR_verifier();
    
    setLayout(new GridLayout(15, 1, 0, 0));
    
    this.f_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.function_label = new JLabel("Select Functionality:");
    this.function = new JComboBox();
    this.function.addItem("Select an Option");
    this.function.addItem("Compare Match");
    this.function.addItem("Input Capture");
    this.function.addItem("PWM");
    this.f_panel.add(this.function_label);
    this.f_panel.add(this.function);
    this.function.addItemListener(this);
    add(this.f_panel);
    
    this.tmr_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.tmr_channel_label = new JLabel("Select Timer Channel:");
    this.tmr_channel = new JComboBox();
    this.tmr_channel.addItem("Select an Option");
    this.tmr_panel.add(this.tmr_channel_label);
    this.tmr_panel.add(this.tmr_channel);
    this.tmr_channel.addItemListener(this);
    add(this.tmr_panel);
    
    this.TGR_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.TGR_choice_label = new JLabel("Select Counter Clearing Source:");
    this.TGR_choice = new JComboBox();
    this.TGR_choice.addItem("Select an Option");
    this.TGR_choice.addItem("Disable Clearing");
    this.TGR_panel.add(this.TGR_choice_label);
    this.TGR_panel.add(this.TGR_choice);
    this.TGR_choice.addItemListener(this);
    add(this.TGR_panel);
    
    this.TIOCA_options_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.TIOCA_label = new JLabel("Select TIOCA options:");
    this.TIOCA_settings = new JComboBox();
    this.TIOCA_settings.addItem("Select an Option");
    this.TIOCA_settings.addItem("Output Disabled");
    this.TIOCA_options_panel.add(this.TIOCA_label);
    this.TIOCA_options_panel.add(this.TIOCA_settings);
    this.TIOCA_settings.addItemListener(this);
    add(this.TIOCA_options_panel);
    this.TIOCA_options_panel.setVisible(false);
    
    this.TIOCB_options_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.TIOCB_label = new JLabel("Select TIOCB options:");
    this.TIOCB_settings = new JComboBox();
    this.TIOCB_settings.addItem("Select an Option");
    this.TIOCB_settings.addItem("Output Disabled");
    this.TIOCB_options_panel.add(this.TIOCB_label);
    this.TIOCB_options_panel.add(this.TIOCB_settings);
    this.TIOCB_settings.addItemListener(this);
    add(this.TIOCB_options_panel);
    this.TIOCB_options_panel.setVisible(false);
    
    this.TIOCC_options_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.TIOCC_label = new JLabel("Select TIOCC options:");
    this.TIOCC_settings = new JComboBox();
    this.TIOCC_settings.addItem("Select an Option");
    this.TIOCC_settings.addItem("Output Disabled");
    this.TIOCC_options_panel.add(this.TIOCC_label);
    this.TIOCC_options_panel.add(this.TIOCC_settings);
    this.TIOCC_settings.addItemListener(this);
    add(this.TIOCC_options_panel);
    this.TIOCC_options_panel.setVisible(false);
    
    this.TIOCD_options_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.TIOCD_label = new JLabel("Select TIOCD options:");
    this.TIOCD_settings = new JComboBox();
    this.TIOCD_settings.addItem("Select an Option");
    this.TIOCD_settings.addItem("Output Disabled");
    this.TIOCD_options_panel.add(this.TIOCD_label);
    this.TIOCD_options_panel.add(this.TIOCD_settings);
    this.TIOCD_settings.addItemListener(this);
    add(this.TIOCD_options_panel);
    this.TIOCD_options_panel.setVisible(false);
    
    this.TGRA_panel = new JPanel(new BorderLayout());
    this.TGRA_label = new JLabel("TGRA (0-65535):");
    this.TGRA_val = new JTextField("0", 5);
    this.TGRA_panel.add(this.TGRA_label, "West");
    this.TGRA_panel.add(this.TGRA_val, "Center");
    add(this.TGRA_panel);
    this.TGRA_panel.setVisible(false);
    this.TGRA_val.setInputVerifier(this.verifier);
    
    this.TGRB_panel = new JPanel(new BorderLayout());
    this.TGRB_label = new JLabel("TGRB (0-65535):");
    this.TGRB_val = new JTextField("0", 5);
    this.TGRB_panel.add(this.TGRB_label, "West");
    this.TGRB_panel.add(this.TGRB_val, "Center");
    add(this.TGRB_panel);
    this.TGRB_panel.setVisible(false);
    this.TGRB_val.setInputVerifier(this.verifier);
    
    this.TGRC_panel = new JPanel(new BorderLayout());
    this.TGRC_label = new JLabel("TGRC (0-65535):");
    this.TGRC_val = new JTextField("0", 5);
    this.TGRC_panel.add(this.TGRC_label, "West");
    this.TGRC_panel.add(this.TGRC_val, "Center");
    add(this.TGRC_panel);
    this.TGRC_panel.setVisible(false);
    this.TGRC_val.setInputVerifier(this.verifier);
    
    this.TGRD_panel = new JPanel(new BorderLayout());
    this.TGRD_label = new JLabel("TGRD (0-65535):");
    this.TGRD_val = new JTextField("0", 5);
    this.TGRD_panel.add(this.TGRD_label, "West");
    this.TGRD_panel.add(this.TGRD_val, "Center");
    add(this.TGRD_panel);
    this.TGRD_panel.setVisible(false);
    this.TGRD_val.setInputVerifier(this.verifier);
    
    this.TCNT_panel = new JPanel(new BorderLayout());
    this.TCNT_label = new JLabel("Initial Counter Value (0-65535):");
    this.TCNT_val = new JTextField("0", 5);
    this.TCNT_panel.add(this.TCNT_label, "West");
    this.TCNT_panel.add(this.TCNT_val, "Center");
    add(this.TCNT_panel);
    this.TCNT_panel.setVisible(false);
    this.TCNT_val.setInputVerifier(this.verifier);
    
    this.INTR_panel = new JPanel(new GridLayout(2, 1, 0, 0));
    this.INTR_label = new JLabel("Interrupt Options:");
    this.INTR = new JComboBox();
    this.INTR.addItem("Select an Option");
    this.INTR.addItemListener(this);
    this.INTR_panel.add(this.INTR_label);
    this.INTR_panel.add(this.INTR);
    add(this.INTR_panel);
    this.INTR_panel.setVisible(false);
    
    this.intr_button_panel = new JPanel(new GridLayout(1, 2, 5, 5));
    this.ret = new JButton("Return");
    this.ret.addActionListener(this);
    this.ret_clr = new JButton("Return and Clear flag");
    this.ret_clr.addActionListener(this);
    this.ret.setEnabled(false);
    this.ret_clr.setEnabled(false);
    this.intr_button_panel.add(this.ret);
    this.intr_button_panel.add(this.ret_clr);
    add(this.intr_button_panel);
    this.intr_button_panel.setVisible(false);
    
    this.buttons_panel = new JPanel(new GridLayout(1, 1, 5, 0));
    
    this.TIOCA_buttons_label = new JLabel("TIOCA");
    this.TIOCA_posedge = new JButton(this.pedge);
    this.TIOCA_negedge = new JButton(this.nedge);
    this.TIOCA_posedge.setEnabled(false);
    this.TIOCA_negedge.setEnabled(false);
    this.TIOCA_posedge.addActionListener(this);
    this.TIOCA_negedge.addActionListener(this);
    this.TIOCA_buttons_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    this.TIOCA_buttons_panel.add(this.TIOCA_posedge);
    this.TIOCA_buttons_panel.add(this.TIOCA_negedge);
    this.TIOCA_panel = new JPanel(new BorderLayout());
    this.TIOCA_panel.add(this.TIOCA_buttons_label, "North");
    this.TIOCA_panel.add(this.TIOCA_buttons_panel, "Center");
    this.buttons_panel.add(this.TIOCA_panel);
    this.TIOCA_panel.setVisible(false);
    
    this.TIOCB_buttons_label = new JLabel("TIOCB");
    this.TIOCB_posedge = new JButton(this.pedge);
    this.TIOCB_negedge = new JButton(this.nedge);
    this.TIOCB_posedge.setEnabled(false);
    this.TIOCB_negedge.setEnabled(false);
    this.TIOCB_posedge.addActionListener(this);
    this.TIOCB_negedge.addActionListener(this);
    this.TIOCB_buttons_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    this.TIOCB_buttons_panel.add(this.TIOCB_posedge);
    this.TIOCB_buttons_panel.add(this.TIOCB_negedge);
    this.TIOCB_panel = new JPanel(new BorderLayout());
    this.TIOCB_panel.add(this.TIOCB_buttons_label, "North");
    this.TIOCB_panel.add(this.TIOCB_buttons_panel, "Center");
    this.buttons_panel.add(this.TIOCB_panel);
    this.TIOCB_panel.setVisible(false);
    
    this.TIOCC_buttons_label = new JLabel("TIOCC");
    this.TIOCC_posedge = new JButton(this.pedge);
    this.TIOCC_negedge = new JButton(this.nedge);
    this.TIOCC_posedge.setEnabled(false);
    this.TIOCC_negedge.setEnabled(false);
    this.TIOCC_posedge.addActionListener(this);
    this.TIOCC_negedge.addActionListener(this);
    this.TIOCC_buttons_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    this.TIOCC_buttons_panel.add(this.TIOCC_posedge);
    this.TIOCC_buttons_panel.add(this.TIOCC_negedge);
    this.TIOCC_panel = new JPanel(new BorderLayout());
    this.TIOCC_panel.add(this.TIOCC_buttons_label, "North");
    this.TIOCC_panel.add(this.TIOCC_buttons_panel, "Center");
    this.buttons_panel.add(this.TIOCC_panel);
    this.TIOCC_panel.setVisible(false);
    
    this.TIOCD_buttons_label = new JLabel("TIOCD");
    this.TIOCD_posedge = new JButton(this.pedge);
    this.TIOCD_negedge = new JButton(this.nedge);
    this.TIOCD_posedge.setEnabled(false);
    this.TIOCD_negedge.setEnabled(false);
    this.TIOCD_posedge.addActionListener(this);
    this.TIOCD_negedge.addActionListener(this);
    this.TIOCD_buttons_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    this.TIOCD_buttons_panel.add(this.TIOCD_posedge);
    this.TIOCD_buttons_panel.add(this.TIOCD_negedge);
    this.TIOCD_panel = new JPanel(new BorderLayout());
    this.TIOCD_panel.add(this.TIOCD_buttons_label, "North");
    this.TIOCD_panel.add(this.TIOCD_buttons_panel, "Center");
    this.buttons_panel.add(this.TIOCD_panel);
    this.TIOCD_panel.setVisible(false);
    
    add(this.buttons_panel);
    this.buttons_panel.setVisible(true);
    

    TitledBorder tb = BorderFactory.createTitledBorder(loweredetched, "Functionality Options");
    
    setBorder(tb);
  }
  
  public void itemStateChanged(ItemEvent e)
  {
    String item = e.getItem().toString();
    
    JComboBox obj = (JComboBox)e.getItemSelectable();
    
    if (e.getStateChange() == 1)
    {
      if ((item.compareTo("Compare Match") == 0) || (item.compareTo("PWM") == 0))
      {
        Timer_Channel.resetRegisters();
        TimerSimulation.reg_panel.clear_background();
        
        level1_init();
        
        this.compareMatch = true;
        this.inputCapture = false;
        
        set_level1_options();
        
        setFunction(item);
        TimerSimulation.reg_panel.setTMDR();
        
        TimerSimulation.reg_panel.set_TMDR_modified(4);
        TimerSimulation.reg_panel.set_TMDR_modified(5);
        TimerSimulation.reg_panel.set_TMDR_modified(6);
        TimerSimulation.reg_panel.set_TMDR_modified(7);
      }
      
      if (item.compareTo("Input Capture") == 0)
      {
        Timer_Channel.resetRegisters();
        TimerSimulation.reg_panel.clear_background();
        
        level1_init();
        
        this.inputCapture = true;
        this.compareMatch = false;
        
        set_level1_options();
        
        setFunction(item);
        TimerSimulation.reg_panel.setTMDR();
        
        TimerSimulation.reg_panel.set_TMDR_modified(4);
        TimerSimulation.reg_panel.set_TMDR_modified(5);
        TimerSimulation.reg_panel.set_TMDR_modified(6);
        TimerSimulation.reg_panel.set_TMDR_modified(7);
      }
      
      if ((item.compareTo("Channel 0") == 0) || (item.compareTo("Channel 3") == 0))
      {
        TimerSimulation.reg_panel.clear_background();
        
        Timer_Channel.resetTCR();
        Timer_Channel.resetTIORH_higher();
        Timer_Channel.resetTIORH_lower();
        Timer_Channel.resetTIORL_higher();
        Timer_Channel.resetTIORL_lower();
        Timer_Channel.resetTIER();
        Timer_Channel.resetTSR();
        Timer_Channel.resetTGRA();
        Timer_Channel.resetTGRB();
        Timer_Channel.resetTGRC();
        Timer_Channel.resetTGRD();
        Timer_Channel.clearCounter();
        
        level2_init();
        
        set_level2_options_II();
        
        setChannel(item);
        
        TimerSimulation.reg_panel.setChannel();
      }
      
      if ((item.compareTo("Channel 1") == 0) || (item.compareTo("Channel 2") == 0) || (item.compareTo("Channel 4") == 0) || (item.compareTo("Channel 5") == 0))
      {
        TimerSimulation.reg_panel.clear_background();
        
        Timer_Channel.resetTCR();
        Timer_Channel.resetTIORH_higher();
        Timer_Channel.resetTIORH_lower();
        Timer_Channel.resetTIORL_higher();
        Timer_Channel.resetTIORL_lower();
        Timer_Channel.resetTIER();
        Timer_Channel.resetTSR();
        Timer_Channel.resetTGRA();
        Timer_Channel.resetTGRB();
        Timer_Channel.resetTGRC();
        Timer_Channel.resetTGRD();
        Timer_Channel.clearCounter();
        
        level2_init();
        
        set_level2_options_I();
        
        setChannel(item);
        
        TimerSimulation.reg_panel.setChannel();
      }
      
      if ((obj == this.TGR_choice) && (this.TGR_choice.isValid() == true))
      {
        TimerSimulation.reg_panel.clear_background();
        
        setTGR(item);
        
        TimerSimulation.reg_panel.setTCR();
        
        TimerSimulation.reg_panel.set_TCR_modified(0);
        TimerSimulation.reg_panel.set_TCR_modified(1);
        TimerSimulation.reg_panel.set_TCR_modified(2);
      }
      
      if ((obj == this.TIOCA_settings) && (this.TIOCA_settings.isValid() == true))
      {
        TimerSimulation.reg_panel.clear_background();
        
        setTIOCA_settings(item);
        
        TimerSimulation.reg_panel.setTIORH();
        
        TimerSimulation.reg_panel.set_TIORH_modified(4);
        TimerSimulation.reg_panel.set_TIORH_modified(5);
        TimerSimulation.reg_panel.set_TIORH_modified(6);
        TimerSimulation.reg_panel.set_TIORH_modified(7);
      }
      
      if ((obj == this.TIOCB_settings) && (this.TIOCB_settings.isValid() == true))
      {
        TimerSimulation.reg_panel.clear_background();
        
        setTIOCB_settings(item);
        
        TimerSimulation.reg_panel.setTIORH();
        
        TimerSimulation.reg_panel.set_TIORH_modified(0);
        TimerSimulation.reg_panel.set_TIORH_modified(1);
        TimerSimulation.reg_panel.set_TIORH_modified(2);
        TimerSimulation.reg_panel.set_TIORH_modified(3);
      }
      
      if ((obj == this.TIOCC_settings) && (this.TIOCC_settings.isValid() == true))
      {
        TimerSimulation.reg_panel.clear_background();
        
        setTIOCC_settings(item);
        
        TimerSimulation.reg_panel.setTIORL();
        
        TimerSimulation.reg_panel.set_TIORL_modified(4);
        TimerSimulation.reg_panel.set_TIORL_modified(5);
        TimerSimulation.reg_panel.set_TIORL_modified(6);
        TimerSimulation.reg_panel.set_TIORL_modified(7);
      }
      
      if ((obj == this.TIOCD_settings) && (this.TIOCD_settings.isValid() == true))
      {
        TimerSimulation.reg_panel.clear_background();
        
        setTIOCD_settings(item);
        
        TimerSimulation.reg_panel.setTIORL();
        
        TimerSimulation.reg_panel.set_TIORL_modified(0);
        TimerSimulation.reg_panel.set_TIORL_modified(1);
        TimerSimulation.reg_panel.set_TIORL_modified(2);
        TimerSimulation.reg_panel.set_TIORL_modified(3);
      }
      
      if ((obj == this.INTR) && (this.INTR.isValid() == true))
      {
        TimerSimulation.reg_panel.clear_background();
        
        setInterrupt(item);
        
        TimerSimulation.reg_panel.setTIER();
      }
      
      if ((obj == this.function) && (item.compareTo("Select an Option") == 0))
      {
        TimerSimulation.reg_panel.clear_background();
        
        level1_init();
        
        Timer_Channel.setChannel("-");
        TimerSimulation.reg_panel.setChannel();
      }
      
      if ((obj == this.tmr_channel) && (item.compareTo("Select an Option") == 0))
      {
        TimerSimulation.reg_panel.clear_background();
        
        level2_init();
        
        Timer_Channel.setChannel("-");
        TimerSimulation.reg_panel.setChannel();
      }
    }
  }
  
  public void set_level1_options()
  {
    this.tmr_channel.addItem("Channel 0");
    this.tmr_channel.addItem("Channel 1");
    this.tmr_channel.addItem("Channel 2");
    this.tmr_channel.addItem("Channel 3");
    this.tmr_channel.addItem("Channel 4");
    this.tmr_channel.addItem("Channel 5");
    
    repaint();
  }
  
  public void set_level2_options_I()
  {
    this.TGR_choice.addItem("TGRA");
    this.TGR_choice.addItem("TGRB");
    
    if (this.compareMatch)
    {
      this.TIOCA_settings.addItem("Initial o/p=0, clear at Compare Match");
      this.TIOCA_settings.addItem("Initial o/p=0, set at Compare Match");
      this.TIOCA_settings.addItem("Initial o/p=0, toggle at Compare Match");
      this.TIOCA_settings.addItem("Initial o/p=1, clear at Compare Match");
      this.TIOCA_settings.addItem("Initial o/p=1, set at Compare Match");
      this.TIOCA_settings.addItem("Initial o/p=1, toggle at Compare Match");
      
      this.TIOCB_settings.addItem("Initial o/p=0, clear at Compare Match");
      this.TIOCB_settings.addItem("Initial o/p=0, set at Compare Match");
      this.TIOCB_settings.addItem("Initial o/p=0, toggle at Compare Match");
      this.TIOCB_settings.addItem("Initial o/p=1, clear at Compare Match");
      this.TIOCB_settings.addItem("Initial o/p=1, set at Compare Match");
      this.TIOCB_settings.addItem("Initial o/p=1, toggle at Compare Match");

    }
    else if (this.inputCapture)
    {
      this.TIOCA_settings.addItem("Input Capture at rising edge");
      this.TIOCA_settings.addItem("Input Capture at falling edge");
      this.TIOCA_settings.addItem("Input Capture at both edges");
      
      this.TIOCB_settings.addItem("Input Capture at rising edge");
      this.TIOCB_settings.addItem("Input Capture at falling edge");
      this.TIOCB_settings.addItem("Input Capture at both edges");
      
      this.TIOCA_panel.setVisible(true);
      this.TIOCB_panel.setVisible(true);
    }
    
    this.TGRA_val.setText("0");
    this.TGRA_panel.setVisible(true);
    this.TGRB_val.setText("0");
    this.TGRB_panel.setVisible(true);
    this.TCNT_val.setText("0");
    this.TCNT_panel.setVisible(true);
    
    this.TIOCA_options_panel.setVisible(true);
    this.TIOCB_options_panel.setVisible(true);
    
    this.INTR.addItem("Disable Interrupts");
    this.INTR.addItem("Interrupt at TCNT Overflow");
    this.INTR.addItem("Interrupt at TGRA input capture/compare match");
    this.INTR.addItem("Interrupt at TGRB input capture/compare match");
    this.INTR_panel.setVisible(true);
    this.intr_button_panel.setVisible(true);
    
    repaint();
  }
  
  public void set_level2_options_II()
  {
    set_level2_options_I();
    
    this.TGR_choice.addItem("TGRC");
    this.TGR_choice.addItem("TGRD");
    
    if (this.compareMatch)
    {
      this.TIOCC_settings.addItem("Initial o/p=0, clear at Compare Match");
      this.TIOCC_settings.addItem("Initial o/p=0, set at Compare Match");
      this.TIOCC_settings.addItem("Initial o/p=0, toggle at Compare Match");
      this.TIOCC_settings.addItem("Initial o/p=1, clear at Compare Match");
      this.TIOCC_settings.addItem("Initial o/p=1, set at Compare Match");
      this.TIOCC_settings.addItem("Initial o/p=1, toggle at Compare Match");
      
      this.TIOCD_settings.addItem("Initial o/p=0, clear at Compare Match");
      this.TIOCD_settings.addItem("Initial o/p=0, set at Compare Match");
      this.TIOCD_settings.addItem("Initial o/p=0, toggle at Compare Match");
      this.TIOCD_settings.addItem("Initial o/p=1, clear at Compare Match");
      this.TIOCD_settings.addItem("Initial o/p=1, set at Compare Match");
      this.TIOCD_settings.addItem("Initial o/p=1, toggle at Compare Match");

    }
    else if (this.inputCapture)
    {
      this.TIOCC_settings.addItem("Input Capture at rising edge");
      this.TIOCC_settings.addItem("Input Capture at falling edge");
      this.TIOCC_settings.addItem("Input Capture at both edges");
      
      this.TIOCD_settings.addItem("Input Capture at rising edge");
      this.TIOCD_settings.addItem("Input Capture at falling edge");
      this.TIOCD_settings.addItem("Input Capture at both edges");
      
      this.TIOCC_panel.setVisible(true);
      this.TIOCD_panel.setVisible(true);
    }
    
    this.TGRC_val.setText("0");
    this.TGRC_panel.setVisible(true);
    this.TGRD_val.setText("0");
    this.TGRD_panel.setVisible(true);
    
    this.TIOCC_options_panel.setVisible(true);
    this.TIOCD_options_panel.setVisible(true);
    
    this.INTR.addItem("Interrupt at TGRC input capture/compare match");
    this.INTR.addItem("Interrupt at TGRD input capture/compare match");
    this.INTR_panel.setVisible(true);
    this.intr_button_panel.setVisible(true);
    
    repaint();
  }
  
  public void registerData()
  {
    String item = this.function.getSelectedItem().toString();
    setFunction(item);
    
    item = this.tmr_channel.getSelectedItem().toString();
    setChannel(item);
    
    item = this.TGR_choice.getSelectedItem().toString();
    setTGR(item);
    
    item = this.TIOCA_settings.getSelectedItem().toString();
    setTIOCA_settings(item);
    
    item = this.TIOCB_settings.getSelectedItem().toString();
    setTIOCB_settings(item);
    
    Timer_Channel.setTGRA(this.TGRA_val.getText());
    Timer_Channel.setTGRB(this.TGRB_val.getText());
    
    if ((Timer_Channel.getCHANNEL().compareTo("0") == 0) || (Timer_Channel.getCHANNEL().compareTo("3") == 0))
    {
      item = this.TIOCC_settings.getSelectedItem().toString();
      setTIOCC_settings(item);
      
      item = this.TIOCD_settings.getSelectedItem().toString();
      setTIOCD_settings(item);
      
      Timer_Channel.setTGRC(this.TGRC_val.getText());
      Timer_Channel.setTGRD(this.TGRD_val.getText());
    }
    
    item = this.INTR.getSelectedItem().toString();
    setInterrupt(item);
    
    Timer_Channel.setTCNT(this.TCNT_val.getText());
  }
  
  public void setInterrupt(String item)
  {
    if (item.compareTo("Disable Interrupts") == 0)
    {
      Timer_Channel.resetTIER();
      
      TimerSimulation.reg_panel.set_TIER_modified(2);
      TimerSimulation.reg_panel.set_TIER_modified(3);
      TimerSimulation.reg_panel.set_TIER_modified(4);
      TimerSimulation.reg_panel.set_TIER_modified(5);
      TimerSimulation.reg_panel.set_TIER_modified(6);
      TimerSimulation.reg_panel.set_TIER_modified(7);
    }
    
    if (item.compareTo("Interrupt at TCNT Overflow") == 0)
    {
      Timer_Channel.resetTIER();
      Timer_Channel.setTIER(3, '1');
      
      TimerSimulation.reg_panel.set_TIER_modified(3);
    }
    
    if (item.compareTo("Interrupt at TGRA input capture/compare match") == 0)
    {
      Timer_Channel.resetTIER();
      Timer_Channel.setTIER(7, '1');
      
      TimerSimulation.reg_panel.set_TIER_modified(7);
    }
    
    if (item.compareTo("Interrupt at TGRB input capture/compare match") == 0)
    {
      Timer_Channel.resetTIER();
      Timer_Channel.setTIER(6, '1');
      
      TimerSimulation.reg_panel.set_TIER_modified(6);
    }
    
    if (item.compareTo("Interrupt at TGRC input capture/compare match") == 0)
    {
      Timer_Channel.resetTIER();
      Timer_Channel.setTIER(5, '1');
      
      TimerSimulation.reg_panel.set_TIER_modified(5);
    }
    
    if (item.compareTo("Interrupt at TGRD input capture/compare match") == 0)
    {
      Timer_Channel.resetTIER();
      Timer_Channel.setTIER(4, '1');
      
      TimerSimulation.reg_panel.set_TIER_modified(4);
    }
  }
  
  public void setTGR(String item)
  {
    if (item.compareTo("TGRA") == 0)
    {
      Timer_Channel.setTCR(0, '0');
      Timer_Channel.setTCR(1, '0');
      Timer_Channel.setTCR(2, '1');
    }
    
    if (item.compareTo("TGRB") == 0)
    {
      Timer_Channel.setTCR(0, '0');
      Timer_Channel.setTCR(1, '1');
      Timer_Channel.setTCR(2, '0');
    }
    
    if (item.compareTo("TGRC") == 0)
    {
      Timer_Channel.setTCR(0, '1');
      Timer_Channel.setTCR(1, '0');
      Timer_Channel.setTCR(2, '1');
    }
    
    if (item.compareTo("TGRD") == 0)
    {
      Timer_Channel.setTCR(0, '1');
      Timer_Channel.setTCR(1, '1');
      Timer_Channel.setTCR(2, '0');
    }
    
    if (item.compareTo("Disable Clearing") == 0)
    {
      Timer_Channel.setTCR(0, '0');
      Timer_Channel.setTCR(1, '0');
      Timer_Channel.setTCR(2, '0');
    }
  }
  
  public void setTIOCD_settings(String item)
  {
    if (item.compareTo("Output Disabled") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '0');
      Timer_Channel.setTIORL(3, '0');
    }
    
    if (item.compareTo("Input Capture at rising edge") == 0)
    {
      Timer_Channel.setTIORL(0, '1');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '0');
      Timer_Channel.setTIORL(3, '0');
    }
    
    if (item.compareTo("Input Capture at falling edge") == 0)
    {
      Timer_Channel.setTIORL(0, '1');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '0');
      Timer_Channel.setTIORL(3, '1');
    }
    
    if (item.compareTo("Input Capture at both edges") == 0)
    {
      Timer_Channel.setTIORL(0, '1');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '1');
      Timer_Channel.setTIORL(3, '0');
    }
    
    if (item.compareTo("Initial o/p=0, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '0');
      Timer_Channel.setTIORL(3, '1');
    }
    if (item.compareTo("Initial o/p=0, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '1');
      Timer_Channel.setTIORL(3, '0');
    }
    if (item.compareTo("Initial o/p=0, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '0');
      Timer_Channel.setTIORL(2, '1');
      Timer_Channel.setTIORL(3, '1');
    }
    if (item.compareTo("Initial o/p=1, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '1');
      Timer_Channel.setTIORL(2, '0');
      Timer_Channel.setTIORL(3, '1');
    }
    if (item.compareTo("Initial o/p=1, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '1');
      Timer_Channel.setTIORL(2, '1');
      Timer_Channel.setTIORL(3, '0');
    }
    if (item.compareTo("Initial o/p=1, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(0, '0');
      Timer_Channel.setTIORL(1, '1');
      Timer_Channel.setTIORL(2, '1');
      Timer_Channel.setTIORL(3, '1');
    }
  }
  
  public void setTIOCC_settings(String item)
  {
    if (item.compareTo("Output Disabled") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '0');
      Timer_Channel.setTIORL(7, '0');
    }
    
    if (item.compareTo("Input Capture at rising edge") == 0)
    {
      Timer_Channel.setTIORL(4, '1');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '0');
      Timer_Channel.setTIORL(7, '0');
    }
    
    if (item.compareTo("Input Capture at falling edge") == 0)
    {
      Timer_Channel.setTIORL(4, '1');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '0');
      Timer_Channel.setTIORL(7, '1');
    }
    
    if (item.compareTo("Input Capture at both edges") == 0)
    {
      Timer_Channel.setTIORL(4, '1');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '1');
      Timer_Channel.setTIORL(7, '0');
    }
    
    if (item.compareTo("Initial o/p=0, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '0');
      Timer_Channel.setTIORL(7, '1');
    }
    if (item.compareTo("Initial o/p=0, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '1');
      Timer_Channel.setTIORL(7, '0');
    }
    if (item.compareTo("Initial o/p=0, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '0');
      Timer_Channel.setTIORL(6, '1');
      Timer_Channel.setTIORL(7, '1');
    }
    if (item.compareTo("Initial o/p=1, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '1');
      Timer_Channel.setTIORL(6, '0');
      Timer_Channel.setTIORL(7, '1');
    }
    if (item.compareTo("Initial o/p=1, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '1');
      Timer_Channel.setTIORL(6, '1');
      Timer_Channel.setTIORL(7, '0');
    }
    if (item.compareTo("Initial o/p=1, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORL(4, '0');
      Timer_Channel.setTIORL(5, '1');
      Timer_Channel.setTIORL(6, '1');
      Timer_Channel.setTIORL(7, '1');
    }
  }
  
  public void setTIOCB_settings(String item)
  {
    if (item.compareTo("Output Disabled") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '0');
      Timer_Channel.setTIORH(3, '0');
    }
    if (item.compareTo("Input Capture at rising edge") == 0)
    {
      Timer_Channel.setTIORH(0, '1');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '0');
      Timer_Channel.setTIORH(3, '0');
    }
    
    if (item.compareTo("Input Capture at falling edge") == 0)
    {
      Timer_Channel.setTIORH(0, '1');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '0');
      Timer_Channel.setTIORH(3, '1');
    }
    
    if (item.compareTo("Input Capture at both edges") == 0)
    {
      Timer_Channel.setTIORH(0, '1');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '1');
      Timer_Channel.setTIORH(3, '0');
    }
    if (item.compareTo("Initial o/p=0, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '0');
      Timer_Channel.setTIORH(3, '1');
    }
    if (item.compareTo("Initial o/p=0, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '1');
      Timer_Channel.setTIORH(3, '0');
    }
    if (item.compareTo("Initial o/p=0, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '0');
      Timer_Channel.setTIORH(2, '1');
      Timer_Channel.setTIORH(3, '1');
    }
    if (item.compareTo("Initial o/p=1, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '1');
      Timer_Channel.setTIORH(2, '0');
      Timer_Channel.setTIORH(3, '1');
    }
    if (item.compareTo("Initial o/p=1, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '1');
      Timer_Channel.setTIORH(2, '1');
      Timer_Channel.setTIORH(3, '0');
    }
    if (item.compareTo("Initial o/p=1, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(0, '0');
      Timer_Channel.setTIORH(1, '1');
      Timer_Channel.setTIORH(2, '1');
      Timer_Channel.setTIORH(3, '1');
    }
  }
  
  public void setTIOCA_settings(String item)
  {
    if (item.compareTo("Output Disabled") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '0');
      Timer_Channel.setTIORH(7, '0');
    }
    
    if (item.compareTo("Input Capture at rising edge") == 0)
    {
      Timer_Channel.setTIORH(4, '1');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '0');
      Timer_Channel.setTIORH(7, '0');
    }
    
    if (item.compareTo("Input Capture at falling edge") == 0)
    {
      Timer_Channel.setTIORH(4, '1');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '0');
      Timer_Channel.setTIORH(7, '1');
    }
    
    if (item.compareTo("Input Capture at both edges") == 0)
    {
      Timer_Channel.setTIORH(4, '1');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '1');
      Timer_Channel.setTIORH(7, '0');
    }
    
    if (item.compareTo("Initial o/p=0, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '0');
      Timer_Channel.setTIORH(7, '1');
    }
    if (item.compareTo("Initial o/p=0, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '1');
      Timer_Channel.setTIORH(7, '0');
    }
    if (item.compareTo("Initial o/p=0, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '0');
      Timer_Channel.setTIORH(6, '1');
      Timer_Channel.setTIORH(7, '1');
    }
    if (item.compareTo("Initial o/p=1, clear at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '1');
      Timer_Channel.setTIORH(6, '0');
      Timer_Channel.setTIORH(7, '1');
    }
    if (item.compareTo("Initial o/p=1, set at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '1');
      Timer_Channel.setTIORH(6, '1');
      Timer_Channel.setTIORH(7, '0');
    }
    if (item.compareTo("Initial o/p=1, toggle at Compare Match") == 0)
    {
      Timer_Channel.setTIORH(4, '0');
      Timer_Channel.setTIORH(5, '1');
      Timer_Channel.setTIORH(6, '1');
      Timer_Channel.setTIORH(7, '1');
    }
  }
  
  public void setChannel(String item)
  {
    Timer_Channel.setChannel(item.substring(8, 9));
  }
  
  public void setFunction(String item)
  {
    if (item.compareTo("PWM") == 0)
    {
      Timer_Channel.setTMDR(6, '1');
      TimerSimulation.canvas.setPWM();
    }
    else if (item.compareTo("Input Capture") == 0)
    {
      TimerSimulation.canvas.setInputCapture();
      Timer_Channel.setTMDR(6, '0');
    }
    else if (item.compareTo("Compare Match") == 0)
    {
      TimerSimulation.canvas.setCompareMatch();
      Timer_Channel.setTMDR(6, '0');
    }
  }
  
  public boolean isAllOptionsSelected()
  {
    if (this.function.getSelectedItem().toString().compareTo("Select an Option") == 0)
    {
      return false;
    }
    
    if (this.tmr_channel.getSelectedItem().toString().compareTo("Select an Option") == 0)
    {
      return false;
    }
    
    if (this.TGR_choice.getSelectedItem().toString().compareTo("Select an Option") == 0)
    {
      return false;
    }
    
    if ((Timer_Channel.getCHANNEL().compareTo("0") == 0) || (Timer_Channel.getCHANNEL().compareTo("3") == 0))
    {
      if ((this.TIOCA_settings.getSelectedItem().toString().compareTo("Select an Option") == 0) || (this.TIOCB_settings.getSelectedItem().toString().compareTo("Select an Option") == 0) || (this.TIOCC_settings.getSelectedItem().toString().compareTo("Select an Option") == 0) || (this.TIOCD_settings.getSelectedItem().toString().compareTo("Select an Option") == 0))
      {



        return false;
      }
    }
    
    if ((Timer_Channel.getCHANNEL().compareTo("1") == 0) || (Timer_Channel.getCHANNEL().compareTo("2") == 0) || (Timer_Channel.getCHANNEL().compareTo("4") == 0) || (Timer_Channel.getCHANNEL().compareTo("5") == 0))
    {
      if ((this.TIOCA_settings.getSelectedItem().toString().compareTo("Select an Option") == 0) || (this.TIOCB_settings.getSelectedItem().toString().compareTo("Select an Option") == 0))
      {

        return false;
      }
    }
    
    if (this.INTR.getSelectedItem().toString().compareTo("Select an Option") == 0)
    {
      return false;
    }
    
    return true;
  }
  
  public void disableInputs()
  {
    this.function.setEnabled(false);
    this.tmr_channel.setEnabled(false);
    this.TGR_choice.setEnabled(false);
    
    this.TIOCA_settings.setEnabled(false);
    this.TIOCB_settings.setEnabled(false);
    this.TIOCC_settings.setEnabled(false);
    this.TIOCD_settings.setEnabled(false);
    
    this.INTR.setEnabled(false);
    
    this.TGRA_val.setEditable(false);
    this.TGRB_val.setEditable(false);
    this.TGRC_val.setEditable(false);
    this.TGRD_val.setEditable(false);
    this.TCNT_val.setEditable(false);
  }
  
  public void enableInputs()
  {
    this.function.setEnabled(true);
    this.tmr_channel.setEnabled(true);
    this.TGR_choice.setEnabled(true);
    
    this.TIOCA_settings.setEnabled(true);
    this.TIOCB_settings.setEnabled(true);
    this.TIOCC_settings.setEnabled(true);
    this.TIOCD_settings.setEnabled(true);
    
    this.INTR.setEnabled(true);
    
    this.TGRA_val.setEditable(true);
    this.TGRB_val.setEditable(true);
    this.TGRC_val.setEditable(true);
    this.TGRD_val.setEditable(true);
    this.TCNT_val.setEditable(true);
  }
  
  public void TIOC_buttons_enable(boolean en)
  {
    this.TIOCA_posedge.setEnabled(en);
    this.TIOCA_negedge.setEnabled(en);
    this.TIOCB_posedge.setEnabled(en);
    this.TIOCB_negedge.setEnabled(en);
    this.TIOCC_posedge.setEnabled(en);
    this.TIOCC_negedge.setEnabled(en);
    this.TIOCD_posedge.setEnabled(en);
    this.TIOCD_negedge.setEnabled(en);
  }
  
  public void level1_init()
  {
    this.tmr_channel.removeAllItems();
    this.tmr_channel.addItem("Select an Option");
    
    level2_init();
  }
  
  public void level2_init()
  {
    this.TGR_choice.removeAllItems();
    this.TGR_choice.addItem("Select an Option");
    this.TGR_choice.addItem("Disable Clearing");
    
    this.TIOCA_settings.removeAllItems();
    this.TIOCA_settings.addItem("Select an Option");
    this.TIOCA_settings.addItem("Output Disabled");
    
    this.TIOCB_settings.removeAllItems();
    this.TIOCB_settings.addItem("Select an Option");
    this.TIOCB_settings.addItem("Output Disabled");
    
    this.TIOCC_settings.removeAllItems();
    this.TIOCC_settings.addItem("Select an Option");
    this.TIOCC_settings.addItem("Output Disabled");
    
    this.TIOCD_settings.removeAllItems();
    this.TIOCD_settings.addItem("Select an Option");
    this.TIOCD_settings.addItem("Output Disabled");
    
    this.INTR.removeAllItems();
    this.INTR.addItem("Select an Option");
    
    this.INTR_panel.setVisible(false);
    this.intr_button_panel.setVisible(false);
    
    this.TIOCA_options_panel.setVisible(false);
    this.TIOCB_options_panel.setVisible(false);
    this.TIOCC_options_panel.setVisible(false);
    this.TIOCD_options_panel.setVisible(false);
    
    this.TGRA_panel.setVisible(false);
    this.TGRB_panel.setVisible(false);
    this.TGRC_panel.setVisible(false);
    this.TGRD_panel.setVisible(false);
    this.TCNT_panel.setVisible(false);
    
    this.TIOCA_panel.setVisible(false);
    this.TIOCB_panel.setVisible(false);
    this.TIOCC_panel.setVisible(false);
    this.TIOCD_panel.setVisible(false);
    
    repaint();
  }
  
  public void setINTR_buttons_enable(boolean en)
  {
    this.ret.setEnabled(en);
    this.ret_clr.setEnabled(en);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().compareTo("Return") == 0)
    {
      TimerSimulation.canvas.set_intr_flag(false);
      
      setINTR_buttons_enable(false);
    }
    
    if (e.getActionCommand().compareTo("Return and Clear flag") == 0)
    {
      Timer_Channel.setTSR(Timer_Channel.getTIER().lastIndexOf('1'), '0');
      TimerSimulation.canvas.set_intr_flag(false);
      
      setINTR_buttons_enable(false);
    }
    
    if (e.getSource() == this.TIOCA_posedge)
    {
      if (TimerSimulation.canvas.getTIOCA() == 1) {
        TimerSimulation.canvas.TIOCA_high();
      }
      else
        TimerSimulation.canvas.TIOCA_posedge();
    }
    if (e.getSource() == this.TIOCA_negedge)
    {
      if (TimerSimulation.canvas.getTIOCA() == 0) {
        TimerSimulation.canvas.TIOCA_low();
      }
      else
        TimerSimulation.canvas.TIOCA_negedge();
    }
    if (e.getSource() == this.TIOCB_posedge)
    {
      if (TimerSimulation.canvas.getTIOCB() == 1) {
        TimerSimulation.canvas.TIOCB_high();
      }
      else
        TimerSimulation.canvas.TIOCB_posedge();
    }
    if (e.getSource() == this.TIOCB_negedge)
    {
      if (TimerSimulation.canvas.getTIOCB() == 0) {
        TimerSimulation.canvas.TIOCB_low();
      }
      else
        TimerSimulation.canvas.TIOCB_negedge();
    }
    if (e.getSource() == this.TIOCC_posedge)
    {
      if (TimerSimulation.canvas.getTIOCC() == 1) {
        TimerSimulation.canvas.TIOCC_high();
      }
      else
        TimerSimulation.canvas.TIOCC_posedge();
    }
    if (e.getSource() == this.TIOCC_negedge)
    {
      if (TimerSimulation.canvas.getTIOCC() == 0) {
        TimerSimulation.canvas.TIOCC_low();
      }
      else
        TimerSimulation.canvas.TIOCC_negedge();
    }
    if (e.getSource() == this.TIOCD_posedge)
    {
      if (TimerSimulation.canvas.getTIOCD() == 1) {
        TimerSimulation.canvas.TIOCD_high();
      }
      else
        TimerSimulation.canvas.TIOCD_posedge();
    }
    if (e.getSource() == this.TIOCD_negedge)
    {
      if (TimerSimulation.canvas.getTIOCD() == 0) {
        TimerSimulation.canvas.TIOCD_low();
      }
      else {
        TimerSimulation.canvas.TIOCD_negedge();
      }
    }
  }
  
  class TGR_verifier extends InputVerifier {
    private int MIN_VAL = 0;
    private int MAX_VAL = 65535;
    
    private DecimalFormat decimalFormat;
    
    public boolean shouldYieldFocus(JComponent input)
    {
      boolean inputOK = verify(input);
      makeItPretty(input);
      
      if (inputOK)
      {
        return true;
      }
      


      return false;
    }
    


    public boolean verify(JComponent input)
    {
      return checkField(input, false);
    }
    
    protected void makeItPretty(JComponent input)
    {
      checkField(input, true);
    }
    
    protected boolean checkField(JComponent input, boolean change)
    {
      boolean wasValid = true;
      int val = this.MIN_VAL;
      

      try
      {
        if (input == FunctionPanel.this.TGRA_val) {
          val = this.decimalFormat.parse(FunctionPanel.this.TGRA_val.getText()).intValue();
        }
        if (input == FunctionPanel.this.TGRB_val) {
          val = this.decimalFormat.parse(FunctionPanel.this.TGRB_val.getText()).intValue();
        }
        if (input == FunctionPanel.this.TGRC_val) {
          val = this.decimalFormat.parse(FunctionPanel.this.TGRC_val.getText()).intValue();
        }
        if (input == FunctionPanel.this.TGRD_val) {
          val = this.decimalFormat.parse(FunctionPanel.this.TGRD_val.getText()).intValue();
        }
        if (input == FunctionPanel.this.TCNT_val) {
          val = this.decimalFormat.parse(FunctionPanel.this.TCNT_val.getText()).intValue();
        }
      }
      catch (ParseException pe) {
        wasValid = false;
      }
      

      if ((val < this.MIN_VAL) || (val > this.MAX_VAL))
      {
        wasValid = false;
        if (change)
        {
          val = this.MIN_VAL;
        }
      }
      

      if (change)
      {
        Integer TGR = new Integer(val);
        
        if (input == FunctionPanel.this.TGRA_val)
        {
          FunctionPanel.this.TGRA_val.setText(TGR.toString());
        }
        if (input == FunctionPanel.this.TGRB_val)
        {
          FunctionPanel.this.TGRB_val.setText(TGR.toString());
        }
        if (input == FunctionPanel.this.TGRC_val)
        {
          FunctionPanel.this.TGRC_val.setText(TGR.toString());
        }
        if (input == FunctionPanel.this.TGRD_val)
        {
          FunctionPanel.this.TGRD_val.setText(TGR.toString());
        }
        if (input == FunctionPanel.this.TCNT_val)
        {
          FunctionPanel.this.TCNT_val.setText(TGR.toString());
        }
      }
      
      return wasValid;
    }
    

    public TGR_verifier()
    {
      this.decimalFormat = ((DecimalFormat)NumberFormat.getNumberInstance());
      this.decimalFormat.setParseIntegerOnly(true);
    }
  }
}
