import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulatorCanvas extends JPanel implements ActionListener
{
  private BufferedImage backgroundGraphics;
  private BufferedImage clockGraphics;
  private final Color clrBackground = new Color(4426);
  
  private final Color clrGridLines = new Color(16777215);
  private final Color clrTransparent = new Color(0, 0, 0, 0);
  private final BasicStroke stkGridLines = new BasicStroke(6.0F, 2, 2);
  
  private int[] clk_xpoints;
  
  private int[] clk_ypoints;
  
  private int[] TIOCA_xpoints;
  
  private int[] TIOCA_ypoints;
  
  private int[] TIOCB_xpoints;
  
  private int[] TIOCB_ypoints;
  
  private int[] TIOCC_xpoints;
  
  private int[] TIOCC_ypoints;
  
  private int[] TIOCD_xpoints;
  
  private int[] TIOCD_ypoints;
  
  private int TIOCA;
  
  private int TIOCB;
  private int TIOCC;
  private int TIOCD;
  private boolean compare_match;
  private boolean input_capture;
  private boolean pwm;
  private boolean counter_clear;
  private boolean TIOCA_rising_edge;
  private boolean TIOCB_rising_edge;
  private boolean TIOCC_rising_edge;
  private boolean TIOCD_rising_edge;
  private boolean TIOCA_falling_edge;
  private boolean TIOCB_falling_edge;
  private boolean TIOCC_falling_edge;
  private boolean TIOCD_falling_edge;
  private int width;
  private int height;
  public Timer tmr;
  private boolean intr_flag = false;
  
  private boolean start_flag = false;
  

  public SimulatorCanvas()
  {
    this.clk_xpoints = new int[5];
    this.clk_ypoints = new int[5];
    
    this.TIOCA_xpoints = new int[3];
    this.TIOCA_ypoints = new int[3];
    
    this.TIOCB_xpoints = new int[3];
    this.TIOCB_ypoints = new int[3];
    
    this.TIOCC_xpoints = new int[3];
    this.TIOCC_ypoints = new int[3];
    
    this.TIOCD_xpoints = new int[3];
    this.TIOCD_ypoints = new int[3];
    
    this.TIOCA = 0;
    this.TIOCB = 0;
    this.TIOCC = 0;
    this.TIOCD = 0;
    
    this.compare_match = false;
    this.input_capture = false;
    this.pwm = false;
    this.counter_clear = false;
    
    this.TIOCA_rising_edge = false;
    this.TIOCB_rising_edge = false;
    this.TIOCC_rising_edge = false;
    this.TIOCD_rising_edge = false;
    
    this.TIOCA_falling_edge = false;
    this.TIOCB_falling_edge = false;
    this.TIOCC_falling_edge = false;
    this.TIOCD_falling_edge = false;
    
    this.tmr = new Timer(200, this);
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    g.drawImage(this.backgroundGraphics, 0, 0, this);
  }
  

  public void init()
  {
    initClockPoints();
    initTIOCAPoints();
    initTIOCBPoints();
    initTIOCCPoints();
    initTIOCDPoints();
    
    setBackground(this.clrBackground);
    
    this.width = 600;
    this.height = 300;
    
    Dimension d = new Dimension(this.width, this.height);
    setPreferredSize(d);
    setSize(d);
    

    this.backgroundGraphics = new BufferedImage(this.width, this.height, 2);
    


    Graphics2D g = this.backgroundGraphics.createGraphics();
    g.setBackground(this.clrBackground);
    g.clearRect(0, 0, this.width, this.height);
    
    g.drawString("Clock", 30, 50);
    g.drawString("TIOCA", 30, 100);
    g.drawString("TIOCB", 30, 150);
    g.drawString("TIOCC", 30, 200);
    g.drawString("TIOCD", 30, 250);
    
    this.intr_flag = false;
  }
  
  public void resetBackground()
  {
    setBackground(this.clrBackground);
    
    this.width = 600;
    this.height = 300;
    

    this.backgroundGraphics = new BufferedImage(this.width, this.height, 2);
    


    Graphics2D g = this.backgroundGraphics.createGraphics();
    g.setBackground(this.clrTransparent);
    g.clearRect(0, 0, this.width, this.height);
    g.drawString("Clock", 30, 50);
    g.drawString("TIOCA", 30, 100);
    g.drawString("TIOCB", 30, 150);
    g.drawString("TIOCC", 30, 200);
    g.drawString("TIOCD", 30, 250);
    
    this.clk_xpoints[0] = 80;
    this.clk_xpoints[1] = 80;
    this.clk_xpoints[2] = 100;
    this.clk_xpoints[3] = 100;
    this.clk_xpoints[4] = 120;
    
    this.TIOCA_xpoints[0] = 80;
    this.TIOCA_xpoints[1] = 80;
    this.TIOCA_xpoints[2] = 120;
    
    this.TIOCB_xpoints[0] = 80;
    this.TIOCB_xpoints[1] = 80;
    this.TIOCB_xpoints[2] = 120;
    
    this.TIOCC_xpoints[0] = 80;
    this.TIOCC_xpoints[1] = 80;
    this.TIOCC_xpoints[2] = 120;
    
    this.TIOCD_xpoints[0] = 80;
    this.TIOCD_xpoints[1] = 80;
    this.TIOCD_xpoints[2] = 120;
  }
  


  public void drawClockCycle()
  {
    Graphics2D g = this.backgroundGraphics.createGraphics();
    
    g.setColor(this.clrGridLines);
    g.setStroke(this.stkGridLines);
    
    g.drawLine(this.clk_xpoints[0], this.clk_ypoints[0], this.clk_xpoints[1], this.clk_ypoints[1]);
    g.drawLine(this.clk_xpoints[1], this.clk_ypoints[1], this.clk_xpoints[2], this.clk_ypoints[2]);
    g.drawLine(this.clk_xpoints[2], this.clk_ypoints[2], this.clk_xpoints[3], this.clk_ypoints[3]);
    g.drawLine(this.clk_xpoints[3], this.clk_ypoints[3], this.clk_xpoints[4], this.clk_ypoints[4]);
    
    repaint();
  }
  
  public void initClockPoints()
  {
    this.clk_xpoints[0] = 80;
    this.clk_xpoints[1] = 80;
    this.clk_xpoints[2] = 100;
    this.clk_xpoints[3] = 100;
    this.clk_xpoints[4] = 120;
    
    this.clk_ypoints[0] = 50;
    this.clk_ypoints[1] = 30;
    this.clk_ypoints[2] = 30;
    this.clk_ypoints[3] = 50;
    this.clk_ypoints[4] = 50;
  }
  
  public void initTIOCAPoints()
  {
    this.TIOCA_xpoints[0] = 80;
    this.TIOCA_xpoints[1] = 80;
    this.TIOCA_xpoints[2] = 120;
    
    this.TIOCA_ypoints[0] = 100;
    this.TIOCA_ypoints[1] = 100;
    this.TIOCA_ypoints[2] = 100;
  }
  
  public void initTIOCBPoints()
  {
    this.TIOCB_xpoints[0] = 80;
    this.TIOCB_xpoints[1] = 80;
    this.TIOCB_xpoints[2] = 120;
    
    this.TIOCB_ypoints[0] = 150;
    this.TIOCB_ypoints[1] = 150;
    this.TIOCB_ypoints[2] = 150;
  }
  
  public void initTIOCCPoints()
  {
    this.TIOCC_xpoints[0] = 80;
    this.TIOCC_xpoints[1] = 80;
    this.TIOCC_xpoints[2] = 120;
    
    this.TIOCC_ypoints[0] = 200;
    this.TIOCC_ypoints[1] = 200;
    this.TIOCC_ypoints[2] = 200;
  }
  
  public void initTIOCDPoints()
  {
    this.TIOCD_xpoints[0] = 80;
    this.TIOCD_xpoints[1] = 80;
    this.TIOCD_xpoints[2] = 120;
    
    this.TIOCD_ypoints[0] = 250;
    this.TIOCD_ypoints[1] = 250;
    this.TIOCD_ypoints[2] = 250;
  }
  
  public void incrementClockPoints()
  {
    int i = 0;
    
    for (i = 0; i < 5; i++)
    {
      this.clk_xpoints[i] += 40;
    }
  }
  
  public void drawClockSignal()
  {
    this.tmr.start();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (this.clk_xpoints[0] >= 600)
    {
      resetBackground();
    }
    
    TimerSimulation.reg_panel.setTCNT();
    TimerSimulation.reg_panel.setTSR();
    TimerSimulation.reg_panel.setTGRA();
    TimerSimulation.reg_panel.setTGRB();
    TimerSimulation.reg_panel.setTGRC();
    TimerSimulation.reg_panel.setTGRD();
    
    drawClockCycle();
    
    if (!this.intr_flag)
    {
      mcu_decision();
      
      check_interrupt();
    }
    
    drawTIOCA();
    drawTIOCB();
    drawTIOCC();
    drawTIOCD();
    
    if (this.TIOCA_rising_edge)
    {
      TIOCA_high();
      this.TIOCA_rising_edge = false;
    }
    if (this.TIOCB_rising_edge)
    {
      TIOCB_high();
      this.TIOCB_rising_edge = false;
    }
    if (this.TIOCC_rising_edge)
    {
      TIOCC_high();
      this.TIOCC_rising_edge = false;
    }
    if (this.TIOCD_rising_edge)
    {
      TIOCD_high();
      this.TIOCD_rising_edge = false;
    }
    
    if (this.TIOCA_falling_edge)
    {
      TIOCA_low();
      this.TIOCA_falling_edge = false;
    }
    if (this.TIOCB_falling_edge)
    {
      TIOCB_low();
      this.TIOCB_falling_edge = false;
    }
    if (this.TIOCC_falling_edge)
    {
      TIOCC_low();
      this.TIOCC_falling_edge = false;
    }
    if (this.TIOCD_falling_edge)
    {
      TIOCD_low();
      this.TIOCD_falling_edge = false;
    }
    
    incrementClockPoints();
    incrementTIOCApoints();
    incrementTIOCBpoints();
    incrementTIOCCpoints();
    incrementTIOCDpoints();
    
    if (this.start_flag == true) {
      this.start_flag = false;
    }
  }
  
  public void check_interrupt() {
    if ((Timer_Channel.getTIER().charAt(7) == '1') && (Timer_Channel.getTSR().charAt(7) == '1'))
    {
      TimerSimulation.ip.append("MCU Interrupted by TGRA compare match / input capture \n", true);
      TimerSimulation.ip.append("Click on return to return from ISR without clearing interrupt enable flag \n", false);
      TimerSimulation.ip.append("Click on return and clear flag to clear interrupt enable flag and teturn from ISR \n", false);
      
      this.intr_flag = true;
      
      TimerSimulation.r_panel.enable_intr_buttons(true);

    }
    else if ((Timer_Channel.getTIER().charAt(6) == '1') && (Timer_Channel.getTSR().charAt(6) == '1'))
    {
      TimerSimulation.ip.append("MCU Interrupted by TGRB compare match / input capture \n", true);
      TimerSimulation.ip.append("Click on return to return from ISR without clearing interrupt enable flag \n", false);
      TimerSimulation.ip.append("Click on return and clear flag to clear interrupt enable flag and teturn from ISR \n", false);
      
      this.intr_flag = true;
      
      TimerSimulation.r_panel.enable_intr_buttons(true);

    }
    else if ((Timer_Channel.getTIER().charAt(5) == '1') && (Timer_Channel.getTSR().charAt(5) == '1'))
    {
      TimerSimulation.ip.append("MCU Interrupted by TGRC compare match / input capture \n", true);
      TimerSimulation.ip.append("Click on return to return from ISR without clearing interrupt enable flag \n", false);
      TimerSimulation.ip.append("Click on return and clear flag to clear interrupt enable flag and teturn from ISR \n", false);
      
      this.intr_flag = true;
      
      TimerSimulation.r_panel.enable_intr_buttons(true);

    }
    else if ((Timer_Channel.getTIER().charAt(4) == '1') && (Timer_Channel.getTSR().charAt(4) == '1'))
    {
      TimerSimulation.ip.append("MCU Interrupted by TGRD compare match / input capture \n", true);
      TimerSimulation.ip.append("Click on return to return from ISR without clearing interrupt enable flag \n", false);
      TimerSimulation.ip.append("Click on return and clear flag to clear interrupt enable flag and teturn from ISR \n", false);
      
      this.intr_flag = true;
      
      TimerSimulation.r_panel.enable_intr_buttons(true);

    }
    else if ((Timer_Channel.getTIER().charAt(3) == '1') && (Timer_Channel.getTSR().charAt(3) == '1'))
    {
      TimerSimulation.ip.append("MCU Interrupted by by TCNT Overflow \n", true);
      TimerSimulation.ip.append("Click on return to return from ISR without clearing interrupt enable flag \n", false);
      TimerSimulation.ip.append("Click on return and clear flag to clear interrupt enable flag and teturn from ISR \n", false);
      
      this.intr_flag = true;
      
      TimerSimulation.r_panel.enable_intr_buttons(true);
    }
  }
  
  public void mcu_decision()
  {
    if (!this.start_flag)
    {
      if (this.counter_clear == true)
      {
        Timer_Channel.clearCounter();
        this.counter_clear = false;
        
        TimerSimulation.ip.append("TCNT Cleared \n", true);
      }
      else
      {
        Timer_Channel.increment_counter();
      }
    }
    if (this.compare_match == true)
    {
      compare_match_decision();

    }
    else if (this.input_capture == true)
    {
      input_capture_decision();

    }
    else if (this.pwm)
    {
      pwm_decision();
    }
  }
  
  public void compare_match_decision()
  {
    if (Timer_Channel.getTGRA().compareTo(Timer_Channel.getTCNT()) == 0)
    {
      TimerSimulation.ip.append("Compare Match at TGRA \n", true);
      
      if (Timer_Channel.getTSR().substring(7, 8).compareTo("0") == 0)
      {
        TimerSimulation.reg_panel.clear_background();
        
        TimerSimulation.reg_panel.set_TSR_modified(7);
        
        Timer_Channel.setTSR(7, '1');
        
        TimerSimulation.ip.append("TGFA bit in TSR set \n", false);
      }
      
      if (Timer_Channel.getTCR().substring(0, 3).compareTo("001") == 0)
      {
        this.counter_clear = true;
      }
      
      if (Timer_Channel.getTIORH().substring(6, 8).compareTo("01") == 0)
      {
        if (this.TIOCA == 0)
        {
          TIOCA_low();
        }
        else if (this.TIOCA == 1)
        {
          TIOCA_negedge();
        }
      }
      if (Timer_Channel.getTIORH().substring(6, 8).compareTo("10") == 0)
      {
        if (this.TIOCA == 0)
        {
          TIOCA_posedge();
        }
        else if (this.TIOCA == 1)
        {
          TIOCA_high();
        }
      }
      if (Timer_Channel.getTIORH().substring(6, 8).compareTo("11") == 0)
      {
        if (this.TIOCA == 1)
        {
          TIOCA_negedge();
        }
        else if (this.TIOCA == 0)
        {
          TIOCA_posedge();
        }
      }
    }
    
    if (Timer_Channel.getTGRB().compareTo(Timer_Channel.getTCNT()) == 0)
    {
      TimerSimulation.ip.append("Compare Match at TGRB \n", true);
      
      if (Timer_Channel.getTSR().substring(6, 7).compareTo("0") == 0)
      {
        Timer_Channel.setTSR(6, '1');
        
        TimerSimulation.reg_panel.clear_background();
        
        TimerSimulation.reg_panel.set_TSR_modified(6);
        
        TimerSimulation.ip.append("TGFB bit in TSR set \n", false);
      }
      
      if (Timer_Channel.getTCR().substring(0, 3).compareTo("010") == 0)
      {
        this.counter_clear = true;
      }
      
      if (Timer_Channel.getTIORH().substring(2, 4).compareTo("01") == 0)
      {
        if (this.TIOCB == 0)
        {
          TIOCB_low();
        }
        else if (this.TIOCB == 1)
        {
          TIOCB_negedge();
        }
      }
      if (Timer_Channel.getTIORH().substring(2, 4).compareTo("10") == 0)
      {

        if (this.TIOCB == 0)
        {
          TIOCB_posedge();
        }
        else if (this.TIOCB == 1)
        {
          TIOCB_high();
        }
      }
      if (Timer_Channel.getTIORH().substring(2, 4).compareTo("11") == 0)
      {
        if (this.TIOCB == 1)
        {
          TIOCB_negedge();
        }
        else if (this.TIOCB == 0)
        {
          TIOCB_posedge();
        }
      }
    }
    
    if ((Timer_Channel.getCHANNEL().compareTo("0") == 0) || (Timer_Channel.getCHANNEL().compareTo("3") == 0))
    {
      if (Timer_Channel.getTGRC().compareTo(Timer_Channel.getTCNT()) == 0)
      {
        TimerSimulation.ip.append("Compare Match at TGRC \n", true);
        
        if (Timer_Channel.getTSR().substring(5, 6).compareTo("0") == 0)
        {
          Timer_Channel.setTSR(5, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(5);
          
          TimerSimulation.ip.append("TGFC bit in TSR set \n", false);
        }
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("101") == 0)
        {
          this.counter_clear = true;
        }
        
        if (Timer_Channel.getTIORL().substring(6, 8).compareTo("01") == 0)
        {
          if (this.TIOCC == 0)
          {
            TIOCC_low();
          }
          else if (this.TIOCC == 1)
          {
            TIOCC_negedge();
          }
        }
        if (Timer_Channel.getTIORL().substring(6, 8).compareTo("10") == 0)
        {
          if (this.TIOCC == 0)
          {
            TIOCC_posedge();
          }
          else if (this.TIOCC == 1)
          {
            TIOCC_high();
          }
        }
        if (Timer_Channel.getTIORL().substring(6, 8).compareTo("11") == 0)
        {
          if (this.TIOCC == 1)
          {
            TIOCC_negedge();
          }
          else if (this.TIOCC == 0)
          {
            TIOCC_posedge();
          }
        }
      }
      
      if (Timer_Channel.getTGRD().compareTo(Timer_Channel.getTCNT()) == 0)
      {
        TimerSimulation.ip.append("Compare Match at TGRD \n", true);
        
        if (Timer_Channel.getTSR().substring(4, 5).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(4, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(4);
          
          TimerSimulation.ip.append("TGFD bit in TSR set \n", true);
        }
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("110") == 0)
        {
          this.counter_clear = true;
        }
        
        if (Timer_Channel.getTIORL().substring(2, 4).compareTo("01") == 0)
        {
          if (this.TIOCD == 0)
          {
            TIOCD_low();
          }
          else if (this.TIOCD == 1)
          {
            TIOCD_negedge();
          }
        }
        if (Timer_Channel.getTIORL().substring(2, 4).compareTo("10") == 0)
        {
          if (this.TIOCD == 0)
          {
            TIOCD_posedge();
          }
          else if (this.TIOCD == 1)
          {
            TIOCD_high();
          }
        }
        if (Timer_Channel.getTIORL().substring(2, 4).compareTo("11") == 0)
        {
          if (this.TIOCD == 1)
          {
            TIOCD_negedge();
          }
          else if (this.TIOCD == 0)
          {
            TIOCD_posedge();
          }
        }
      }
    }
  }
  
  public void input_capture_decision()
  {
    if (Timer_Channel.getTIORH().substring(4, 8).compareTo("1000") == 0)
    {
      if (this.TIOCA_rising_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCA rising edge \n", true);
        
        if (Timer_Channel.getTSR().substring(7, 8).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(7, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(7);
          
          TimerSimulation.ip.append("TGFA bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRA(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("001") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORH().substring(4, 8).compareTo("1001") == 0)
    {
      if (this.TIOCA_falling_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCA falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(7, 8).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(7, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(7);
          
          TimerSimulation.ip.append("TGFA bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRA(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("001") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORH().substring(4, 7).compareTo("101") == 0)
    {
      if ((this.TIOCA_rising_edge) || (this.TIOCA_falling_edge))
      {
        TimerSimulation.ip.append("Input Capture at TIOCA rising/falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(7, 8).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(7, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(7);
          
          TimerSimulation.ip.append("TGFA bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRA(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("001") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    
    if (Timer_Channel.getTIORH().substring(0, 4).compareTo("1000") == 0)
    {
      if (this.TIOCB_rising_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCA rising edge \n", true);
        
        if (Timer_Channel.getTSR().substring(6, 7).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(6, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(6);
          
          TimerSimulation.ip.append("TGFB bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRB(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("010") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORH().substring(0, 4).compareTo("1001") == 0)
    {
      if (this.TIOCB_falling_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCB falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(6, 7).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(6, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(6);
          
          TimerSimulation.ip.append("TGFB bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRB(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("010") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORH().substring(0, 3).compareTo("101") == 0)
    {
      if ((this.TIOCB_rising_edge) || (this.TIOCB_falling_edge))
      {
        TimerSimulation.ip.append("Input Capture at TIOCB rising/falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(6, 7).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(6, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(6);
          
          TimerSimulation.ip.append("TGFB bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRB(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("010") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    
    if (Timer_Channel.getTIORL().substring(4, 8).compareTo("1000") == 0)
    {
      if (this.TIOCC_rising_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCC rising edge \n", true);
        
        if (Timer_Channel.getTSR().substring(5, 6).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(5, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(5);
        }
        
        Timer_Channel.setTGRC(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("101") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORL().substring(4, 8).compareTo("1001") == 0)
    {
      if (this.TIOCC_falling_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCC falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(5, 6).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(5, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(5);
          
          TimerSimulation.ip.append("TGFC bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRC(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("101") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORL().substring(4, 7).compareTo("101") == 0)
    {
      if ((this.TIOCC_rising_edge) || (this.TIOCC_falling_edge))
      {
        TimerSimulation.ip.append("Input Capture at TIOCC rising/falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(5, 6).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(5, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(5);
          
          TimerSimulation.ip.append("TGFC bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRC(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("101") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    
    if (Timer_Channel.getTIORL().substring(0, 4).compareTo("1000") == 0)
    {
      if (this.TIOCD_rising_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCD rising edge \n", true);
        
        if (Timer_Channel.getTSR().substring(4, 5).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(4, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(4);
          
          TimerSimulation.ip.append("TGFD bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRD(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("110") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORL().substring(0, 4).compareTo("1001") == 0)
    {
      if (this.TIOCD_falling_edge)
      {
        TimerSimulation.ip.append("Input Capture at TIOCD falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(4, 5).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(4, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(4);
          
          TimerSimulation.ip.append("TGFD bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRD(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("110") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
    if (Timer_Channel.getTIORH().substring(0, 3).compareTo("101") == 0)
    {
      if ((this.TIOCD_rising_edge) || (this.TIOCD_falling_edge))
      {
        TimerSimulation.ip.append("Input Capture at TIOCD rising/falling edge \n", true);
        
        if (Timer_Channel.getTSR().substring(4, 5).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(4, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(4);
          
          TimerSimulation.ip.append("TGFD bit in TSR set \n", false);
        }
        
        Timer_Channel.setTGRD(Timer_Channel.getTCNT());
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("110") == 0)
        {
          this.counter_clear = true;
        }
      }
    }
  }
  
  public void pwm_decision()
  {
    if (Timer_Channel.getTGRA().compareTo(Timer_Channel.getTCNT()) == 0)
    {
      TimerSimulation.ip.append("Compare Match at TGRA \n", true);
      
      if (Timer_Channel.getTSR().substring(7, 8).compareToIgnoreCase("0") == 0)
      {
        Timer_Channel.setTSR(7, '1');
        
        TimerSimulation.reg_panel.clear_background();
        
        TimerSimulation.reg_panel.set_TSR_modified(7);
        
        TimerSimulation.ip.append("TGFA bit in TSR set \n", true);
      }
      
      if (Timer_Channel.getTCR().substring(0, 3).compareTo("001") == 0)
      {
        this.counter_clear = true;
      }
      
      if (Timer_Channel.getTIORH().substring(6, 8).compareTo("01") == 0)
      {
        if (this.TIOCA == 0)
        {
          TIOCA_low();
        }
        else if (this.TIOCA == 1)
        {
          TIOCA_negedge();
        }
      }
      if (Timer_Channel.getTIORH().substring(6, 8).compareTo("10") == 0)
      {
        if (this.TIOCA == 0)
        {
          TIOCA_posedge();
        }
        else if (this.TIOCA == 1)
        {
          TIOCA_high();
        }
      }
      if (Timer_Channel.getTIORH().substring(6, 8).compareTo("11") == 0)
      {
        if (this.TIOCA == 1)
        {
          TIOCA_negedge();
        }
        else if (this.TIOCA == 0)
        {
          TIOCA_posedge();
        }
      }
    }
    
    if (Timer_Channel.getTGRB().compareTo(Timer_Channel.getTCNT()) == 0)
    {
      TimerSimulation.ip.append("Compare Match at TGRB \n", true);
      
      if (Timer_Channel.getTSR().substring(6, 7).compareToIgnoreCase("0") == 0)
      {
        Timer_Channel.setTSR(6, '1');
        
        TimerSimulation.reg_panel.clear_background();
        
        TimerSimulation.reg_panel.set_TSR_modified(6);
        
        TimerSimulation.ip.append("TGFB bit in TSR set \n", true);
      }
      
      if (Timer_Channel.getTCR().substring(0, 3).compareTo("010") == 0)
      {
        this.counter_clear = true;
      }
      
      if (Timer_Channel.getTIORH().substring(2, 4).compareTo("01") == 0)
      {
        if (this.TIOCA == 0)
        {
          TIOCA_low();
        }
        else if (this.TIOCA == 1)
        {
          TIOCA_negedge();
        }
      }
      if (Timer_Channel.getTIORH().substring(2, 4).compareTo("10") == 0)
      {
        if (this.TIOCA == 0)
        {
          TIOCA_posedge();
        }
        else if (this.TIOCA == 1)
        {
          TIOCA_high();
        }
      }
      if (Timer_Channel.getTIORH().substring(2, 4).compareTo("11") == 0)
      {
        if (this.TIOCA == 1)
        {
          TIOCA_negedge();
        }
        else if (this.TIOCA == 0)
        {
          TIOCA_posedge();
        }
      }
    }
    
    if ((Timer_Channel.getCHANNEL().compareTo("0") == 0) || (Timer_Channel.getCHANNEL().compareTo("3") == 0))
    {
      if (Timer_Channel.getTGRC().compareTo(Timer_Channel.getTCNT()) == 0)
      {
        TimerSimulation.ip.append("Compare Match at TGRC \n", true);
        
        if (Timer_Channel.getTSR().substring(5, 6).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(5, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(5);
          
          TimerSimulation.ip.append("TGFC bit in TSR set \n", true);
        }
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("101") == 0)
        {
          this.counter_clear = true;
        }
        
        if (Timer_Channel.getTIORL().substring(6, 8).compareTo("01") == 0)
        {
          if (this.TIOCC == 0)
          {
            TIOCC_low();
          }
          else if (this.TIOCC == 1)
          {
            TIOCC_negedge();
          }
        }
        if (Timer_Channel.getTIORL().substring(6, 8).compareTo("10") == 0)
        {
          if (this.TIOCC == 0)
          {
            TIOCC_posedge();
          }
          else if (this.TIOCC == 1)
          {
            TIOCC_high();
          }
        }
        if (Timer_Channel.getTIORL().substring(6, 8).compareTo("11") == 0)
        {
          if (this.TIOCC == 1)
          {
            TIOCC_negedge();
          }
          else if (this.TIOCC == 0)
          {
            TIOCC_posedge();
          }
        }
      }
      
      if (Timer_Channel.getTGRD().compareTo(Timer_Channel.getTCNT()) == 0)
      {
        TimerSimulation.ip.append("Compare Match at TGRD \n", true);
        
        if (Timer_Channel.getTSR().substring(4, 5).compareToIgnoreCase("0") == 0)
        {
          Timer_Channel.setTSR(4, '1');
          
          TimerSimulation.reg_panel.clear_background();
          
          TimerSimulation.reg_panel.set_TSR_modified(4);
          
          TimerSimulation.ip.append("TGFD bit in TSR set \n", true);
        }
        
        if (Timer_Channel.getTCR().substring(0, 3).compareTo("110") == 0)
        {
          this.counter_clear = true;
        }
        
        if (Timer_Channel.getTIORL().substring(2, 4).compareTo("01") == 0)
        {
          if (this.TIOCC == 0)
          {
            TIOCC_low();
          }
          else if (this.TIOCC == 1)
          {
            TIOCC_negedge();
          }
        }
        if (Timer_Channel.getTIORL().substring(2, 4).compareTo("10") == 0)
        {
          if (this.TIOCC == 0)
          {
            TIOCC_posedge();
          }
          else if (this.TIOCC == 1)
          {
            TIOCC_high();
          }
        }
        if (Timer_Channel.getTIORL().substring(2, 4).compareTo("11") == 0)
        {
          if (this.TIOCC == 1)
          {
            TIOCC_negedge();
          }
          else if (this.TIOCC == 0)
          {
            TIOCC_posedge();
          }
        }
      }
    }
  }
  
  public void TIOCA_posedge()
  {
    this.TIOCA_rising_edge = true;
    this.TIOCA_ypoints[0] = 100;
    this.TIOCA_ypoints[1] = 80;
    this.TIOCA_ypoints[2] = 80;
    this.TIOCA = 1;
  }
  
  public void TIOCA_negedge()
  {
    this.TIOCA_falling_edge = true;
    this.TIOCA_ypoints[0] = 80;
    this.TIOCA_ypoints[1] = 100;
    this.TIOCA_ypoints[2] = 100;
    this.TIOCA = 0;
  }
  
  public void TIOCA_high()
  {
    this.TIOCA_ypoints[0] = 80;
    this.TIOCA_ypoints[1] = 80;
    this.TIOCA_ypoints[2] = 80;
  }
  
  public void TIOCA_low()
  {
    this.TIOCA_ypoints[0] = 100;
    this.TIOCA_ypoints[1] = 100;
    this.TIOCA_ypoints[2] = 100;
  }
  
  public void incrementTIOCApoints()
  {
    int i = 0;
    
    for (i = 0; i < 3; i++)
    {
      this.TIOCA_xpoints[i] += 40;
    }
  }
  
  public void TIOCB_posedge()
  {
    this.TIOCB_rising_edge = true;
    this.TIOCB_ypoints[0] = 150;
    this.TIOCB_ypoints[1] = 130;
    this.TIOCB_ypoints[2] = 130;
    this.TIOCB = 1;
  }
  
  public void TIOCB_negedge()
  {
    this.TIOCB_falling_edge = true;
    this.TIOCB_ypoints[0] = 130;
    this.TIOCB_ypoints[1] = 150;
    this.TIOCB_ypoints[2] = 150;
    this.TIOCB = 0;
  }
  
  public void TIOCB_high()
  {
    this.TIOCB_ypoints[0] = 130;
    this.TIOCB_ypoints[1] = 130;
    this.TIOCB_ypoints[2] = 130;
  }
  
  public void TIOCB_low()
  {
    this.TIOCB_ypoints[0] = 150;
    this.TIOCB_ypoints[1] = 150;
    this.TIOCB_ypoints[2] = 150;
  }
  
  public void incrementTIOCBpoints()
  {
    int i = 0;
    
    for (i = 0; i < 3; i++)
    {
      this.TIOCB_xpoints[i] += 40;
    }
  }
  
  public void TIOCC_posedge()
  {
    this.TIOCC_rising_edge = true;
    this.TIOCC_ypoints[0] = 200;
    this.TIOCC_ypoints[1] = 180;
    this.TIOCC_ypoints[2] = 180;
    this.TIOCC = 1;
  }
  
  public void TIOCC_negedge()
  {
    this.TIOCC_falling_edge = true;
    this.TIOCC_ypoints[0] = 180;
    this.TIOCC_ypoints[1] = 200;
    this.TIOCC_ypoints[2] = 200;
    this.TIOCC = 0;
  }
  
  public void TIOCC_high()
  {
    this.TIOCC_ypoints[0] = 180;
    this.TIOCC_ypoints[1] = 180;
    this.TIOCC_ypoints[2] = 180;
  }
  
  public void TIOCC_low()
  {
    this.TIOCC_ypoints[0] = 200;
    this.TIOCC_ypoints[1] = 200;
    this.TIOCC_ypoints[2] = 200;
  }
  
  public void incrementTIOCCpoints()
  {
    int i = 0;
    
    for (i = 0; i < 3; i++)
    {
      this.TIOCC_xpoints[i] += 40;
    }
  }
  
  public void TIOCD_posedge()
  {
    this.TIOCD_rising_edge = true;
    this.TIOCD_ypoints[0] = 250;
    this.TIOCD_ypoints[1] = 230;
    this.TIOCD_ypoints[2] = 230;
    this.TIOCD = 1;
  }
  
  public void TIOCD_negedge()
  {
    this.TIOCD_falling_edge = true;
    this.TIOCD_ypoints[0] = 230;
    this.TIOCD_ypoints[1] = 250;
    this.TIOCD_ypoints[2] = 250;
    this.TIOCD = 0;
  }
  
  public void TIOCD_high()
  {
    this.TIOCD_ypoints[0] = 230;
    this.TIOCD_ypoints[1] = 230;
    this.TIOCD_ypoints[2] = 230;
  }
  
  public void TIOCD_low()
  {
    this.TIOCD_ypoints[0] = 250;
    this.TIOCD_ypoints[1] = 250;
    this.TIOCD_ypoints[2] = 250;
  }
  
  public void incrementTIOCDpoints()
  {
    int i = 0;
    
    for (i = 0; i < 3; i++)
    {
      this.TIOCD_xpoints[i] += 40;
    }
  }
  
  public void drawTIOCA()
  {
    Graphics2D g = this.backgroundGraphics.createGraphics();
    
    g.setColor(this.clrGridLines);
    g.setStroke(this.stkGridLines);
    
    g.drawLine(this.TIOCA_xpoints[0], this.TIOCA_ypoints[0], this.TIOCA_xpoints[1], this.TIOCA_ypoints[1]);
    g.drawLine(this.TIOCA_xpoints[1], this.TIOCA_ypoints[1], this.TIOCA_xpoints[2], this.TIOCA_ypoints[2]);
    
    repaint();
  }
  
  public void drawTIOCB()
  {
    Graphics2D g = this.backgroundGraphics.createGraphics();
    
    g.setColor(this.clrGridLines);
    g.setStroke(this.stkGridLines);
    
    g.drawLine(this.TIOCB_xpoints[0], this.TIOCB_ypoints[0], this.TIOCB_xpoints[1], this.TIOCB_ypoints[1]);
    g.drawLine(this.TIOCB_xpoints[1], this.TIOCB_ypoints[1], this.TIOCB_xpoints[2], this.TIOCB_ypoints[2]);
    
    repaint();
  }
  
  public void drawTIOCC()
  {
    Graphics2D g = this.backgroundGraphics.createGraphics();
    
    g.setColor(this.clrGridLines);
    g.setStroke(this.stkGridLines);
    
    g.drawLine(this.TIOCC_xpoints[0], this.TIOCC_ypoints[0], this.TIOCC_xpoints[1], this.TIOCC_ypoints[1]);
    g.drawLine(this.TIOCC_xpoints[1], this.TIOCC_ypoints[1], this.TIOCC_xpoints[2], this.TIOCC_ypoints[2]);
    
    repaint();
  }
  
  public void drawTIOCD()
  {
    Graphics2D g = this.backgroundGraphics.createGraphics();
    
    g.setColor(this.clrGridLines);
    g.setStroke(this.stkGridLines);
    
    g.drawLine(this.TIOCD_xpoints[0], this.TIOCD_ypoints[0], this.TIOCD_xpoints[1], this.TIOCD_ypoints[1]);
    g.drawLine(this.TIOCD_xpoints[1], this.TIOCD_ypoints[1], this.TIOCD_xpoints[2], this.TIOCD_ypoints[2]);
    
    repaint();
  }
  
  public void startTimer(boolean isStopped)
  {
    if (isStopped)
    {
      this.TIOCA = 0;
      this.TIOCB = 0;
      this.TIOCC = 0;
      this.TIOCD = 0;
      this.counter_clear = false;
      setInitialOutput();
    }
    
    this.tmr.start();
  }
  
  public void stopTimer()
  {
    this.tmr.stop();
  }
  
  public void setInitialOutput()
  {
    if (this.compare_match == true)
    {

      if (Timer_Channel.getTIORH().charAt(1) == '1')
      {

        TIOCB_posedge();
      }
      if (Timer_Channel.getTIORH().charAt(5) == '1')
      {

        TIOCA_posedge();
      }
      if (Timer_Channel.getTIORL().charAt(1) == '1')
      {
        TIOCD_posedge();
      }
      if (Timer_Channel.getTIORL().charAt(5) == '1')
      {
        TIOCC_posedge();
      }
    }
    if (this.pwm == true)
    {
      if (Timer_Channel.getTIORH().charAt(5) == '1')
      {
        TIOCA_posedge();
      }
      if (Timer_Channel.getTIORL().charAt(5) == '1')
      {
        TIOCC_posedge();
      }
    }
  }
  
  public void setPWM()
  {
    this.pwm = true;
    this.input_capture = false;
    this.compare_match = false;
  }
  
  public void setInputCapture()
  {
    this.input_capture = true;
    this.pwm = false;
    this.compare_match = false;
  }
  
  public void setCompareMatch()
  {
    this.compare_match = true;
    this.pwm = false;
    this.input_capture = false;
  }
  
  public int getTIOCA() {
    return this.TIOCA;
  }
  
  public int getTIOCB() {
    return this.TIOCB;
  }
  
  public int getTIOCC() {
    return this.TIOCC;
  }
  
  public int getTIOCD() {
    return this.TIOCD;
  }
  
  public void set_intr_flag(boolean b)
  {
    this.intr_flag = b;
  }
  
  public boolean get_intr_flag() {
    return this.intr_flag;
  }
  
  public void set_start_flag(boolean b)
  {
    this.start_flag = b;
  }
  
  public boolean get_start_flag()
  {
    return this.start_flag;
  }
}
