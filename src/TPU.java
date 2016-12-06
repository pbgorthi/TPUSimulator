public class TPU
{
  private static StringBuffer TSTR;
  private static StringBuffer TSYR;
  
  public static void init()
  {
    TSYR = new StringBuffer("00000000");
    TSTR = new StringBuffer("00000000");
  }
  
  public static String getTSTR()
  {
    return TSTR.toString();
  }
  
  public static String getTSYR()
  {
    return TSYR.toString();
  }
  
  public static void resetRegisters()
  {
    TSTR.replace(0, TSTR.length(), "00000000");
    TSYR.replace(0, TSYR.length(), "00000000");
  }
  
  public static void setTSTR(int i, char c)
  {
    TSTR.setCharAt(i, c);
  }
}