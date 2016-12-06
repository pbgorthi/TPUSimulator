import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RightPanel extends JPanel implements ActionListener
{
  URL startIconUrl = getClass().getClassLoader().getResource("images/start.jpg");
  ImageIcon start_icon = new ImageIcon(this.startIconUrl);
  URL stopIconUrl = getClass().getClassLoader().getResource("images/stop.jpg");
  ImageIcon stop_icon = new ImageIcon(this.stopIconUrl);
  URL pauseIconUrl = getClass().getClassLoader().getResource("images/pause.jpg");
  ImageIcon pause_icon = new ImageIcon(this.pauseIconUrl);
  
  FunctionPanel fp;
  
  private JButton start;
  
  private JButton stop;
  private JButton pause;
  private boolean isStopped = true;
  private boolean isPaused = false;
  
  Integer val = new Integer(0);
  
  public RightPanel()
  {
    setLayout(new BorderLayout());
    
    this.fp = new FunctionPanel();
    
    add(this.fp, "Center");
    
    JPanel bp = new JPanel(new GridLayout(1, 3, 0, 0));
    this.start = new JButton(this.start_icon);
    this.stop = new JButton(this.stop_icon);
    this.pause = new JButton(this.pause_icon);
    
    this.start.addActionListener(this);
    this.stop.addActionListener(this);
    this.pause.addActionListener(this);
    
    bp.add(this.start);
    bp.add(this.pause);
    bp.add(this.stop);
    
    this.pause.setEnabled(false);
    this.stop.setEnabled(false);
    
    add(bp, "South");
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.start)
    {
      if (!this.fp.isAllOptionsSelected())
      {
        JOptionPane.showMessageDialog(this, "Error: Select all Options", "Error", 2);

      }
      else if (this.fp.isAllOptionsSelected() == true)
      {
        if (this.isStopped)
        {
          TimerSimulation.reg_panel.clear_background();
          this.fp.registerData();
          TimerSimulation.canvas.init();
          TimerSimulation.canvas.set_start_flag(true);
          TimerSimulation.ip.clear_pane();
          
          TimerSimulation.ip.append("Simulation Stared \n", true);

        }
        else
        {
          TimerSimulation.ip.append("Simulation Resumed \n", true);
          
          if (TimerSimulation.canvas.get_intr_flag() == true)
          {
            this.fp.setINTR_buttons_enable(true);
          }
        }
        
        this.fp.disableInputs();
        this.fp.TIOC_buttons_enable(true);
        
        TPU.setTSTR(7 - Integer.parseInt(Timer_Channel.getCHANNEL()), '1');
        TimerSimulation.reg_panel.set_TSTR_modified(7 - Integer.parseInt(Timer_Channel.getCHANNEL()));
        
        TimerSimulation.start_sim(this.isStopped);
        
        this.start.setEnabled(false);
        this.pause.setEnabled(true);
        this.stop.setEnabled(true);
        
        this.isStopped = false;
      }
    }
    
    if (e.getSource() == this.pause)
    {
      this.start.setEnabled(true);
      this.pause.setEnabled(false);
      this.stop.setEnabled(true);
      
      TPU.setTSTR(7 - Integer.parseInt(Timer_Channel.getCHANNEL()), '0');
      TimerSimulation.reg_panel.set_TSTR_modified(7 - Integer.parseInt(Timer_Channel.getCHANNEL()));
      
      this.fp.TIOC_buttons_enable(false);
      this.fp.setINTR_buttons_enable(false);
      
      TimerSimulation.ip.append("Simulation Paused \n", true);
      
      TimerSimulation.pause_sim();
    }
    
    if (e.getSource() == this.stop)
    {
      this.start.setEnabled(true);
      this.pause.setEnabled(false);
      this.stop.setEnabled(false);
      
      TPU.resetRegisters();
      Timer_Channel.resetTSR();
      
      TimerSimulation.reg_panel.clear_background();
      TimerSimulation.reg_panel.setTSTR();
      TimerSimulation.reg_panel.set_TSTR_modified(7 - Integer.parseInt(Timer_Channel.getCHANNEL()));
      
      TimerSimulation.ip.append("Simulation Stopped \n", true);
      
      TimerSimulation.stop_sim();
      
      this.fp.enableInputs();
      this.fp.TIOC_buttons_enable(false);
      this.fp.setINTR_buttons_enable(false);
      
      this.isStopped = true;
    }
  }
  
  public void enable_intr_buttons(boolean en)
  {
    this.fp.setINTR_buttons_enable(en);
  }
}
