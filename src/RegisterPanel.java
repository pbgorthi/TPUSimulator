import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class RegisterPanel extends JPanel
{
  private JLabel channel;
  private JLabel channel_val;
  private JLabel TSTR;
  private JLabel TSYR;
  private JLabel TCR;
  private JLabel TMDR;
  private JLabel TIORH;
  private JLabel TIORL;
  private JLabel TIER;
  private JLabel TSR;
  private JLabel TCNT;
  private JLabel TGRA;
  private JLabel TGRB;
  private JLabel TGRC;
  private JLabel TGRD;
  private JLabel[] TSTR_val;
  private JLabel[] TSYR_val;
  private JLabel[] TCR_val;
  private JLabel[] TMDR_val;
  private JLabel[] TIORH_val;
  private JLabel[] TIORL_val;
  private JLabel[] TIER_val;
  private JLabel[] TSR_val;
  private JLabel TCNT_val;
  private JLabel TGRA_val;
  private JLabel TGRB_val;
  private JLabel TGRC_val;
  private JLabel TGRD_val;
  DecimalFormat regFormatter = new DecimalFormat("00000");
  Integer val = new Integer(0);
  Color transparent = new Color(0, 0, 0, 0);
  

  public RegisterPanel()
  {
    this.TSTR_val = new JLabel[8];
    this.TSYR_val = new JLabel[8];
    
    this.TCR_val = new JLabel[8];
    this.TMDR_val = new JLabel[8];
    this.TIORH_val = new JLabel[8];
    this.TIORL_val = new JLabel[8];
    this.TIER_val = new JLabel[8];
    this.TSR_val = new JLabel[8];
    
    init();
  }
  
  public void init()
  {
    int j = 0;
    
    Border loweredetched = BorderFactory.createEtchedBorder(1);
    Border blackline = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
    
    setLayout(new BorderLayout(5, 5));
    JPanel gridPanel = new JPanel(new GridLayout(1, 3, 2, 2));
    
    JPanel north_panel = new JPanel();
    
    this.channel = new JLabel("CHANNEL:", 0);
    this.channel_val = new JLabel(Timer_Channel.getCHANNEL(), 0);
    north_panel.add(this.channel);
    north_panel.add(this.channel_val);
    
    add(north_panel, "North");
    
    JPanel centre_panel = new JPanel(new GridLayout(2, 1, 0, 10));
    
    JPanel common_panel = new JPanel(new GridLayout(2, 1, 0, 10));
    JPanel counter_panel = new JPanel(new GridLayout(1, 1, 0, 10));
    
    JPanel TSTR_panel = new JPanel(new BorderLayout());
    JPanel TSTR_val_panel = new JPanel(new GridLayout(1, 8, 0, 0));
    
    JPanel TSYR_panel = new JPanel(new BorderLayout());
    JPanel TSYR_val_panel = new JPanel(new GridLayout(1, 8, 0, 0));
    
    JPanel TCNT_panel = new JPanel(new BorderLayout(10, 10));
    

    Font cf = new Font("Courier New", 1, 24);
    this.TSTR = new JLabel("TSTR:");
    this.TSTR.setFont(cf);
    this.TSTR_val = initLabel(this.TSTR_val, blackline);
    setTSTR();
    TSTR_panel.add(this.TSTR, "West");
    for (j = 0; j < this.TSTR_val.length; j++)
      TSTR_val_panel.add(this.TSTR_val[j]);
    TSTR_panel.add(TSTR_val_panel, "Center");
    
    this.TSYR = new JLabel("TSYR:");
    this.TSYR.setFont(cf);
    this.TSYR_val = initLabel(this.TSYR_val, blackline);
    setTSYR();
    TSYR_panel.add(this.TSYR, "West");
    for (j = 0; j < this.TSYR_val.length; j++)
      TSYR_val_panel.add(this.TSYR_val[j]);
    TSYR_panel.add(TSYR_val_panel, "Center");
    
    common_panel.add(TSTR_panel);
    common_panel.add(TSYR_panel);
    

    TitledBorder tb = BorderFactory.createTitledBorder(loweredetched, "Common Registers");
    common_panel.setBorder(tb);
    
    centre_panel.add(common_panel, "Center");
    
    this.TCNT = new JLabel("TCNT:");
    this.TCNT.setFont(cf);
    this.TCNT_val = new JLabel();
    setTCNT();
    TCNT_panel.add(this.TCNT, "West");
    TCNT_panel.add(this.TCNT_val, "Center");
    
    counter_panel.add(TCNT_panel);
    
    tb = BorderFactory.createTitledBorder(loweredetched, "Counter");
    counter_panel.setBorder(tb);
    
    centre_panel.add(counter_panel, "South");
    
    Font f = new Font("Courier New", 0, 14);
    JPanel r_panel = new JPanel(new GridLayout(5, 1, 0, 10));
    
    JPanel TCR_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TCR_val_panel = new JPanel(new GridLayout(1, 8, 0, 0));
    
    JPanel TMDR_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TMDR_val_panel = new JPanel(new GridLayout(1, 8));
    
    JPanel TIORH_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TIORH_val_panel = new JPanel(new GridLayout(1, 8));
    
    JPanel TIORL_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TIORL_val_panel = new JPanel(new GridLayout(1, 8));
    
    JPanel TIER_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TIER_val_panel = new JPanel(new GridLayout(1, 8));
    
    this.TCR = new JLabel("TCR:");
    this.TCR.setFont(f);
    this.TCR_val = initLabel(this.TCR_val, blackline);
    setTCR();
    TCR_panel.add(this.TCR);
    for (j = 0; j < this.TCR_val.length; j++)
      TCR_val_panel.add(this.TCR_val[j]);
    TCR_panel.add(TCR_val_panel);
    
    this.TMDR = new JLabel("TMDR:");
    this.TMDR.setFont(f);
    this.TMDR_val = initLabel(this.TMDR_val, blackline);
    setTMDR();
    TMDR_panel.add(this.TMDR);
    for (j = 0; j < this.TMDR_val.length; j++)
      TMDR_val_panel.add(this.TMDR_val[j]);
    TMDR_panel.add(TMDR_val_panel);
    
    this.TIORH = new JLabel("TIOR(H):");
    this.TIORH.setFont(f);
    this.TIORH_val = initLabel(this.TIORH_val, blackline);
    setTIORH();
    TIORH_panel.add(this.TIORH);
    for (j = 0; j < this.TIORH_val.length; j++)
      TIORH_val_panel.add(this.TIORH_val[j]);
    TIORH_panel.add(TIORH_val_panel);
    
    this.TIORL = new JLabel("TIORL:");
    this.TIORL.setFont(f);
    this.TIORL_val = initLabel(this.TIORL_val, blackline);
    setTIORL();
    TIORL_panel.add(this.TIORL);
    for (j = 0; j < this.TIORL_val.length; j++)
      TIORL_val_panel.add(this.TIORL_val[j]);
    TIORL_panel.add(TIORL_val_panel);
    
    this.TIER = new JLabel("TIER:");
    this.TIER.setFont(f);
    this.TIER_val = initLabel(this.TIER_val, blackline);
    setTIER();
    TIER_panel.add(this.TIER);
    for (j = 0; j < this.TIER_val.length; j++)
      TIER_val_panel.add(this.TIER_val[j]);
    TIER_panel.add(TIER_val_panel);
    
    r_panel.add(TCR_panel);
    r_panel.add(TMDR_panel);
    r_panel.add(TIORH_panel);
    r_panel.add(TIORL_panel);
    r_panel.add(TIER_panel);
    
    tb = BorderFactory.createTitledBorder(loweredetched, "Channel Specific Registers");
    r_panel.setBorder(tb);
    
    JPanel l_panel = new JPanel(new GridLayout(5, 1, 0, 10));
    
    JPanel TSR_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TSR_val_panel = new JPanel(new GridLayout(1, 8, 0, 0));
    
    JPanel TGRA_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TGRB_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TGRC_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    JPanel TGRD_panel = new JPanel(new GridLayout(1, 2, 0, 0));
    
    this.TSR = new JLabel("TSR:");
    this.TSR.setFont(f);
    this.TSR_val = initLabel(this.TSR_val, blackline);
    setTSR();
    TSR_panel.add(this.TSR);
    for (j = 0; j < this.TSR_val.length; j++)
      TSR_val_panel.add(this.TSR_val[j]);
    TSR_panel.add(TSR_val_panel);
    
    this.TGRA = new JLabel("TGRA:");
    this.TGRA.setFont(f);
    this.TGRA_val = new JLabel();
    this.TGRA_val.setOpaque(true);
    setTGRA();
    this.TGRA_val.setBorder(blackline);
    TGRA_panel.add(this.TGRA);
    TGRA_panel.add(this.TGRA_val);
    
    this.TGRB = new JLabel("TGRB:");
    this.TGRB.setFont(f);
    this.TGRB_val = new JLabel();
    this.TGRB_val.setOpaque(true);
    setTGRB();
    this.TGRB_val.setBorder(blackline);
    TGRB_panel.add(this.TGRB);
    TGRB_panel.add(this.TGRB_val);
    
    this.TGRC = new JLabel("TGRC:");
    this.TGRC.setFont(f);
    this.TGRC_val = new JLabel();
    this.TGRC_val.setOpaque(true);
    setTGRC();
    this.TGRC_val.setBorder(blackline);
    TGRC_panel.add(this.TGRC);
    TGRC_panel.add(this.TGRC_val);
    
    this.TGRD = new JLabel("TGRD:");
    this.TGRD.setFont(f);
    this.TGRD_val = new JLabel();
    this.TGRD_val.setOpaque(true);
    setTGRD();
    this.TGRD_val.setBorder(blackline);
    TGRD_panel.add(this.TGRD);
    TGRD_panel.add(this.TGRD_val);
    

    l_panel.add(TSR_panel);
    l_panel.add(TGRA_panel);
    l_panel.add(TGRB_panel);
    l_panel.add(TGRC_panel);
    l_panel.add(TGRD_panel);
    
    tb = BorderFactory.createTitledBorder(loweredetched, "Channel Specific Registers");
    l_panel.setBorder(tb);
    
    gridPanel.add(r_panel);
    gridPanel.add(centre_panel);
    gridPanel.add(l_panel);
    
    add(gridPanel, "Center");
    
    tb = BorderFactory.createTitledBorder(loweredetched, "TPU Registers");
    setBorder(tb);
  }
  
  public void setChannel()
  {
    this.channel_val.setText(Timer_Channel.getCHANNEL());
    repaint();
  }
  
  public void setTSTR()
  {
    for (int j = 0; j < this.TSTR_val.length; j++) {
      this.TSTR_val[j].setText(TPU.getTSTR().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTSYR()
  {
    for (int j = 0; j < this.TSYR_val.length; j++) {
      this.TSYR_val[j].setText(TPU.getTSYR().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTCR()
  {
    for (int j = 0; j < this.TCR_val.length; j++) {
      this.TCR_val[j].setText(Timer_Channel.getTCR().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTMDR()
  {
    for (int j = 0; j < this.TMDR_val.length; j++) {
      this.TMDR_val[j].setText(Timer_Channel.getTMDR().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTIORH()
  {
    for (int j = 0; j < this.TIORH_val.length; j++) {
      this.TIORH_val[j].setText(Timer_Channel.getTIORH().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTIORL()
  {
    for (int j = 0; j < this.TIORL_val.length; j++) {
      this.TIORL_val[j].setText(Timer_Channel.getTIORL().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTIER()
  {
    for (int j = 0; j < this.TIER_val.length; j++) {
      this.TIER_val[j].setText(Timer_Channel.getTIER().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTSR()
  {
    for (int j = 0; j < this.TSR_val.length; j++) {
      this.TSR_val[j].setText(Timer_Channel.getTSR().substring(j, j + 1));
    }
    repaint();
  }
  
  public void setTGRA()
  {
    String s = this.regFormatter.format(Integer.parseInt(Timer_Channel.getTGRA()));
    
    this.TGRA_val.setText(s);
    
    repaint();
  }
  
  public void setTGRB()
  {
    String s = this.regFormatter.format(Integer.parseInt(Timer_Channel.getTGRB()));
    
    this.TGRB_val.setText(s);
    
    repaint();
  }
  
  public void setTGRC()
  {
    String s = this.regFormatter.format(Integer.parseInt(Timer_Channel.getTGRC()));
    
    this.TGRC_val.setText(s);
    
    repaint();
  }
  
  public void setTGRD()
  {
    String s = this.regFormatter.format(Integer.parseInt(Timer_Channel.getTGRD()));
    
    this.TGRD_val.setText(s);
    
    repaint();
  }
  
  public void setTCNT()
  {
    String s = this.regFormatter.format(Integer.parseInt(Timer_Channel.getTCNT()));
    
    this.TCNT_val.setText(s);
    
    repaint();
  }
  
  public JLabel[] initLabel(JLabel[] l, Border b)
  {
    for (int i = 0; i < l.length; i++)
    {
      l[i] = new JLabel("", 0);
      l[i].setOpaque(true);
      l[i].setBorder(b);
    }
    return l;
  }
  
  public void set_TSTR_modified(int bit)
  {
    this.TSTR_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TSYR_modified(int bit)
  {
    this.TSYR_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TCR_modified(int bit)
  {
    this.TCR_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TMDR_modified(int bit)
  {
    this.TMDR_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TIORH_modified(int bit)
  {
    this.TIORH_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TIORL_modified(int bit)
  {
    this.TIORL_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TIER_modified(int bit)
  {
    this.TIER_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TSR_modified(int bit)
  {
    this.TSR_val[bit].setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TGRA_modified()
  {
    this.TGRA_val.setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TGRB_modified()
  {
    this.TGRB_val.setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TGRC_modified()
  {
    this.TGRC_val.setBackground(Color.RED);
    
    repaint();
  }
  
  public void set_TGRD_modified()
  {
    this.TGRD_val.setBackground(Color.RED);
    
    repaint();
  }
  
  public void clear_TSTR_modified(int bit)
  {
    this.TSTR_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TSYR_modified(int bit) {
    this.TSYR_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TCR_modified(int bit) {
    this.TCR_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TMDR_modified(int bit) {
    this.TMDR_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TIORH_modified(int bit) {
    this.TIORH_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TIORL_modified(int bit) {
    this.TIORL_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TSR_modified(int bit) {
    this.TSR_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TIER_modified(int bit) {
    this.TIER_val[bit].setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TGRA_modified() {
    this.TGRA_val.setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TGRB_modified() {
    this.TGRB_val.setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TGRC_modified() {
    this.TGRC_val.setBackground(this.transparent);
    repaint();
  }
  
  public void clear_TGRD_modified() {
    this.TGRD_val.setBackground(this.transparent);
    repaint();
  }
  
  public void clear_background()
  {
    int i = 0;
    
    for (i = 0; i < this.TCR_val.length; i++) {
      clear_TCR_modified(i);
    }
    for (i = 0; i < this.TMDR_val.length; i++) {
      clear_TMDR_modified(i);
    }
    for (i = 0; i < this.TIORH_val.length; i++) {
      clear_TIORH_modified(i);
    }
    for (i = 0; i < this.TIORL_val.length; i++) {
      clear_TIORL_modified(i);
    }
    for (i = 0; i < this.TIER_val.length; i++) {
      clear_TIER_modified(i);
    }
    for (i = 0; i < this.TSR_val.length; i++) {
      clear_TSR_modified(i);
    }
    for (i = 0; i < this.TSTR_val.length; i++) {
      clear_TSTR_modified(i);
    }
    for (i = 0; i < this.TSYR_val.length; i++) {
      clear_TSYR_modified(i);
    }
  }
}
