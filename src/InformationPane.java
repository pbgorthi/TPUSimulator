import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class InformationPane extends JTextPane
{
  private long lngCounter;
  private int blockStart;
  SimpleAttributeSet[] attrs;
  
  public InformationPane()
  {
    this.blockStart = 0;
    init();
  }
  
  public InformationPane(StyledDocument arg0)
  {
    super(arg0);
    this.blockStart = 0;
    init();
  }
  
  private void init()
  {
    this.lngCounter = 0L;
    this.attrs = new SimpleAttributeSet[2];
    this.attrs[0] = new SimpleAttributeSet();
    this.attrs[1] = new SimpleAttributeSet();
    StyleConstants.setBold(this.attrs[1], true);
    setSize(10, 50);
    
    setEditable(false);
  }
  
  public void append(String data, boolean newData)
  {
    StyledDocument doc = getStyledDocument();
    if (newData)
    {
      doc.setCharacterAttributes(this.blockStart, doc.getLength(), this.attrs[0], true);
      this.blockStart = doc.getLength();
    }
    try
    {
      doc.insertString(doc.getLength(), "[" + this.lngCounter++ + "] " + data, this.attrs[1]);
    }
    catch (BadLocationException ble)
    {
      ble.printStackTrace();
    }
    setCaretPosition(getDocument().getLength());
  }
  
  public void init_append(String data, boolean newData)
  {
    StyledDocument doc = getStyledDocument();
    if (newData)
    {
      doc.setCharacterAttributes(this.blockStart, doc.getLength(), this.attrs[0], true);
      this.blockStart = doc.getLength();
    }
    try
    {
      doc.insertString(doc.getLength(), data, this.attrs[1]);
    }
    catch (BadLocationException ble)
    {
      ble.printStackTrace();
    }
    setCaretPosition(getDocument().getLength());
  }
  
  public void clear_pane()
  {
    setText("");
    this.lngCounter = 0L;
  }
}
