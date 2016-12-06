public class Timer_Channel
{
  private static StringBuffer CHANNEL;
  private static StringBuffer TCR;
  private static StringBuffer TMDR;
  private static StringBuffer TIORH;
  private static StringBuffer TIORL;
  private static StringBuffer TIER;
  private static StringBuffer TSR;
  private static StringBuffer TCNT;
  private static StringBuffer TGRA;
  private static StringBuffer TGRB;
  private static StringBuffer TGRC;
  private static StringBuffer TGRD;
  private static Integer c = new Integer(0);
  private static int counter_val = 0;
  
  public static void init()
  {
    CHANNEL = new StringBuffer("-");
    
    TCR = new StringBuffer("00000000");
    TMDR = new StringBuffer("11000000");
    TIORH = new StringBuffer("00000000");
    TIORL = new StringBuffer("00000000");
    TIER = new StringBuffer("01000000");
    TSR = new StringBuffer("11000000");
    
    TGRA = new StringBuffer("0");
    TGRB = new StringBuffer("0");
    TGRC = new StringBuffer("0");
    TGRD = new StringBuffer("0");
    
    TCNT = new StringBuffer("0");
  }
  
  public static String getCHANNEL()
  {
    return CHANNEL.toString();
  }
  
  public static String getTCR()
  {
    return TCR.toString();
  }
  
  public static String getTMDR()
  {
    return TMDR.toString();
  }
  
  public static String getTIORH()
  {
    return TIORH.toString();
  }
  
  public static String getTIORL()
  {
    return TIORL.toString();
  }
  
  public static String getTIER()
  {
    return TIER.toString();
  }
  
  public static String getTSR()
  {
    return TSR.toString();
  }
  
  public static String getTGRA()
  {
    return TGRA.toString();
  }
  
  public static String getTGRB()
  {
    return TGRB.toString();
  }
  
  public static String getTGRC()
  {
    return TGRC.toString();
  }
  
  public static String getTGRD()
  {
    return TGRD.toString();
  }
  
  public static String getTCNT()
  {
    return TCNT.toString();
  }
  
  public static void setTMDR(int i, char ch)
  {
    TMDR.setCharAt(i, ch);
  }
  
  public static void setChannel(String string)
  {
    char ch = string.charAt(0);
    CHANNEL.setCharAt(0, ch);
  }
  
  public static void setTIORH(int i, char c)
  {
    TIORH.setCharAt(i, c);
  }
  
  public static void setTIORL(int i, char c)
  {
    TIORL.setCharAt(i, c);
  }
  
  public static void setTCR(int i, char c)
  {
    TCR.setCharAt(i, c);
  }
  
  public static void setTIER(int i, char c)
  {
    TIER.setCharAt(i, c);
  }
  
  public static void setTGRA(String s)
  {
    TGRA.replace(0, TGRA.length(), s);
  }
  
  public static void setTGRB(String s)
  {
    TGRB.replace(0, TGRB.length(), s);
  }
  
  public static void setTGRC(String s)
  {
    TGRC.replace(0, TGRC.length(), s);
  }
  
  public static void setTGRD(String s)
  {
    TGRD.replace(0, TGRD.length(), s);
  }
  
  public static void setTCNT(String s)
  {
    TCNT.replace(0, TCNT.length(), s);
  }
  
  public static void resetRegisters()
  {
    CHANNEL.setCharAt(0, '-');
    
    TCR.replace(0, TCR.length(), "00000000");
    TMDR.replace(0, TMDR.length(), "11000000");
    TIORH.replace(0, TIORH.length(), "00000000");
    TIORL.replace(0, TIORL.length(), "00000000");
    TIER.replace(0, TIER.length(), "01000000");
    TSR.replace(0, TSR.length(), "11000000");
    
    setTGRA("0");
    setTGRB("0");
    setTGRC("0");
    setTGRD("0");
    setTCNT("0");
  }
  
  public static void resetTCR()
  {
    TCR.replace(0, TCR.length(), "00000000");
  }
  
  public static void resetTMDR()
  {
    TMDR.replace(0, TMDR.length(), "11000000");
  }
  
  public static void resetTIORH_lower()
  {
    TIORH.replace(4, TIORH.length(), "0000");
  }
  
  public static void resetTIORH_higher()
  {
    TIORH.replace(0, 4, "0000");
  }
  
  public static void resetTIORL_lower()
  {
    TIORL.replace(4, TIORL.length(), "00000000");
  }
  
  public static void resetTIORL_higher()
  {
    TIORL.replace(0, 4, "0000");
  }
  
  public static void resetTIER()
  {
    TIER.replace(0, TIER.length(), "01000000");
  }
  
  public static void resetTSR()
  {
    TSR.replace(0, TSR.length(), "11000000");
  }
  
  public static void resetTGRA()
  {
    setTGRA("0");
  }
  
  public static void resetTGRB()
  {
    setTGRB("0");
  }
  
  public static void resetTGRC()
  {
    setTGRC("0");
  }
  
  public static void resetTGRD()
  {
    setTGRD("0");
  }
  
  public static void clearCounter()
  {
    TCNT.replace(0, TCNT.length(), "0");
  }
  
  public static void increment_counter()
  {
    counter_val = Integer.parseInt(TCNT.toString());
    counter_val += 1;
    
    if (counter_val == 65536)
    {
      if (TCR.substring(0, 3).compareTo("000") != 0)
      {
        counter_val = 0;
        TimerSimulation.ip.append("Timer Counter Overflow \n", true);
        setTSR(3, '1');
      }
      else
      {
        counter_val = 65535;
      }
    }
    TCNT.replace(0, TCNT.length(), "");
    TCNT.append(counter_val);
  }
  
  public static void setTSR(int i, char c)
  {
    TSR.setCharAt(i, c);
  }
}
