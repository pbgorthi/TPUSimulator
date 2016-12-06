import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class TimerSimulation extends JFrame
{
  public static RegisterPanel reg_panel;
  public static RightPanel r_panel;
  public static SimulatorCanvas canvas;
  public static InformationPane ip;
  
  public TimerSimulation()
  {
    setLayout(new BorderLayout());
    
    Timer_Channel.init();
    TPU.init();
    
    Border loweredetched = BorderFactory.createEtchedBorder(1);

    JLabel titleLabel = new JLabel("H8S/2377 TPU Simulator", 0);
    
    JPanel titlepanel = new JPanel(new GridLayout(2, 1, 10, 10));
    titlepanel.add(titleLabel);
    
    JPanel northpanel = new JPanel(new FlowLayout(1, 10, 10));
    northpanel.add(titlepanel);
    northpanel.setBorder(loweredetched);
    
    add(northpanel, "North");
    
    reg_panel = new RegisterPanel();
    
    ip = new InformationPane();
    JScrollPane sp = new JScrollPane(ip);
    sp.setPreferredSize(new Dimension(200, 200));
    sp.setBorder(BorderFactory.createTitledBorder("Information Pane"));
    JPanel ip_panel = new JPanel(new BorderLayout());
    ip_panel.add(sp, "Center");
    
    r_panel = new RightPanel();
    canvas = new SimulatorCanvas();
    
    JPanel cp = new JPanel(new GridLayout(3, 1, 0, 10));
    
    cp.add(reg_panel);
    cp.add(ip_panel);
    cp.add(canvas);
    
    add(cp, "Center");
    add(r_panel, "East");
    
    init_infopane();
    canvas.init();
  }
  


  public static void main(String[] args)
  {
    TimerSimulation sim_frame = new TimerSimulation();
    
    sim_frame.setTitle("Timer Simulator v1.0");
    
    Toolkit t = Toolkit.getDefaultToolkit();
    Dimension d = t.getScreenSize();
    
    sim_frame.setSize(d.width, d.height - 40);
    sim_frame.setResizable(false);
    sim_frame.setLocation(d.width / 2 - sim_frame.getWidth() / 2, d.height / 2 - sim_frame.getHeight() / 2 - 10);
    


    sim_frame.setDefaultCloseOperation(3);
    
    sim_frame.setVisible(true);
  }
  
  public static void start_sim(boolean isStopped)
  {
    reg_panel.setChannel();
    reg_panel.setTSTR();
    reg_panel.setTSYR();
    reg_panel.setTCR();
    reg_panel.setTMDR();
    reg_panel.setTIORH();
    reg_panel.setTIORL();
    reg_panel.setTIER();
    reg_panel.setTSR();
    reg_panel.setTGRA();
    reg_panel.setTGRB();
    reg_panel.setTGRC();
    reg_panel.setTGRD();
    reg_panel.setTCNT();
    
    canvas.startTimer(isStopped);
  }
  
  public static void pause_sim()
  {
    canvas.stopTimer();
  }
  
  public static void stop_sim()
  {
    canvas.stopTimer();
  }
  
  public static void init_infopane()
  {
    ip.init_append("Welcome to the H8S 2377 TPU Simulator \n\n", true);
    ip.init_append("The following is the list of assumptions made: \n\n", false);
    ip.init_append("1. MCU is operating in the Advanced Mode \n\n", false);
    ip.init_append("2. The TPU uses internal clock source and counts on  Φ/1 \n\n", false);
    ip.init_append("3. The Timer Counter is an up counter \n\n", false);
    ip.init_append("4. The Simulation timescale is slower than the clock frequency \n\n", false);
    ip.init_append("5. For Purposes of Simplicity, the user can enable only 1 interrupt during the simulation \n\n", false);
    ip.init_append("6. The Simulator includes the following functionalities of the TPU module:\n\n", false);
    ip.init_append(" - Normal Mode Compare Match \n", false);
    ip.init_append(" - Normal Mode Input Capture \n", false);
    ip.init_append(" - PWM Mode 1", false);
  }
}
