import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class security extends JFrame
{
  public static void main(String arg[])
  {
    security application = new security();
    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }


  private ObjectInputStream input;
  private ObjectOutputStream output;
  private JTextArea source,result;
  private JTextField key;
  private JLabel label;
  private int kind = 1;
  public security()
  {
    super("Security ammar application");
    Container container = getContentPane();
    container.setLayout(new BorderLayout(3,3));
    //.....................................
    JMenu file = new JMenu("File");
    file.setMnemonic('f');
    JMenu algo = new JMenu("Algorithm");
    algo.setMnemonic('a');
    JMenu run = new JMenu("Run");
    run.setMnemonic('r');
    JMenu help = new JMenu("Help");
    help.setMnemonic('h');

    JMenuItem neew = new JMenuItem("New     ");
    neew.setMnemonic('n');
    neew.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
             if(!result.getText().equals(""))
             {
              int r = JOptionPane.showConfirmDialog(null,"Do You Want Save This File ?");
              if(r==0)
               save();
              if(r!=2)
              {
               close();
               source.setText("");
               result.setText("");
               key.setText("");
              }
             }
             else
             {
              close();
              source.setText("");
              key.setText("");
             }
            }});
    JMenuItem open = new JMenuItem("Open");
    open.setMnemonic('o');
    open.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               open();
            }});
    JMenuItem save = new JMenuItem("Save");
    save.setMnemonic('s');
    save.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               save();
            }});
    JMenuItem exit = new JMenuItem("Exit");
    exit.setMnemonic('e');
    exit.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
             if(!result.getText().equals(""))
             {
              int r = JOptionPane.showConfirmDialog(null,"Do You Want Save This File ?");
              if(r==0)
               save();
              if(r!=2)
              {
               System.exit(0);
              }
             }
             else
              System.exit(0);
            }});
    JMenuItem multi = new JMenuItem("3 Multiple Letter");
    multi.setMnemonic('m');
    multi.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               kind = 1 ;
               label.setText("3 multiple letter Algo. is used");
            }});
    JMenuItem rsa = new JMenuItem("RSA algorithm");
    rsa.setMnemonic('r');
    rsa.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               kind = 2 ;
               label.setText("RSA Algo. is used");
            }});
    JMenuItem generation = new JMenuItem("Generation Key for RSA");
    generation.setMnemonic('g');
    generation.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               key_generation();
            }});
    JMenuItem encryption = new JMenuItem("Encryption");
    encryption.setMnemonic('e');
    encryption.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               if(kind==1)
                result.setText(encryption1(source.getText(),key(1)));
               else
                result.setText(encryption2(source.getText(),key(1)));
            }});
    JMenuItem decryption = new JMenuItem("Decryption");
    decryption.setMnemonic('d');
    decryption.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               if(kind==1)
                result.setText(encryption1(source.getText(),key(0)));
               else
                result.setText(encryption2(source.getText(),key(1)));
            }});
    JMenuItem about = new JMenuItem("Help About");
    about.setMnemonic('a');
    about.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog(null,"This program written by :\n    - ST.Ammar 'Mohamed Basem' Jawdet Al-natsheh\n For Security Class\n    - ID# : 2050854\nSupervisor :\n    - Dr.Khaleel Alhinde\nEmail: ammar_2050854@yahoo.com\n                                thank you."," Help _ About",JOptionPane.PLAIN_MESSAGE);
            }});
    JMenuItem topic = new JMenuItem("Help Topic");
    topic.setMnemonic('t');
    topic.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               JOptionPane.showMessageDialog(null,"This program use to encrypte a plain text or to decrypte\n acipher text by using three multiple letter encryption way\nor the RSA way as the user want. the advance is the ability\nis choosing the algorithem which you want to use,\nand use any key including numbersor character or both.\n by writting in source area the user can chose to decrypte\n or encrypte the source text by clicking on the button but\n he should input the key first.\n\nNote:This program work only for\n1  A-z and a-z letters\n2  0-9 numbers\n                                      thank you."," Help _ Topic",JOptionPane.INFORMATION_MESSAGE);
            }});
    file.add(neew);
    file.add(open);
    file.add(save);
    file.addSeparator();
    file.add(exit);
    algo.add(multi);
    algo.addSeparator();
    algo.add(rsa);
    algo.add(generation);
    run.add(encryption);
    run.add(decryption);
    help.add(topic);
    help.add(about);

    JMenuBar bar = new JMenuBar();
    bar.add(file);
    bar.add(algo);
    bar.add(run);
    bar.add(help);
    bar.setBorder(BorderFactory.createRaisedBevelBorder());
    setJMenuBar(bar);
    //.....................................

    key = new JTextField("key",20);
    key.setToolTipText("This field use to write user key");
    key.setBorder(BorderFactory.createTitledBorder("Key Field"));
    source = new JTextArea();
    source.setBorder(BorderFactory.createTitledBorder("Source Text"));
    result = new JTextArea();
    result.setEditable(false);
    result.setBorder(BorderFactory.createTitledBorder("Result Text"));
    label = new JLabel("3 multiple letter Algo. is used");
    label.setEnabled(false);
    label.setBackground(Color.white);
    JButton en = new JButton("  Encryption  ");
    en.setBorder(BorderFactory.createRaisedBevelBorder());
    en.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               if(kind==1)
                result.setText(encryption1(source.getText(),key(1)));
               else
                result.setText(encryption2(source.getText(),key(1)));
            }});
    JButton de = new JButton("  Decryption  ");
    de.setBorder(BorderFactory.createRaisedBevelBorder());
    de.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent event )
            {
               if(kind==1)
                result.setText(encryption1(source.getText(),key(0)));
               else
                result.setText(encryption2(source.getText(),key(1)));
            }});

    Box box = Box.createHorizontalBox();
    box.add(new JScrollPane(source));
    box.add(Box.createRigidArea(new Dimension(3,0)));
    box.add(new JScrollPane(result));

    JPanel c = new JPanel();
    c.setLayout(new GridLayout(2,4,3,5));


    c.add(en);
    c.add(new JLabel(" "));
    c.add(key);
    c.add(new JLabel(" "));
    c.add(de);
    c.add(new JLabel(" "));
    c.add(label);


    container.add(new JLabel(" "),BorderLayout.NORTH);
    container.add(box,BorderLayout.CENTER);
    container.add(c,BorderLayout.SOUTH);
    setSize(700,500);
    setVisible(true);
    setLocation(100,100);
  }

  private int reflection(char c)
  {
   int n=(int)(c);
   if(n<58)
    return n-47;
   else
    return n-54;
  }


  private char dereflection(int c)
  {
   if(c < 10)
    return (char)(c+47);
   else
    return (char)(c+54);
  }


  private int key(int c)
  {
   char k[]=(key.getText().toUpperCase()).toCharArray();
   String s="";
   int n;
   for(int i=0;i<k.length;i++)
   {
    s+=(reflection(k[i])-1);
   }
   try
   {
    n = Integer.parseInt(s);
   }
   catch(Exception e)
   {
    JOptionPane.showMessageDialog(null,"The key field take the normal letter or/and integers number(no more four char)\n the spaces are not allawed unless in the beginning\nElse the system take 1 as key valu","Error key",JOptionPane.ERROR_MESSAGE);
    return 1;    
   }
   if(c==1)
    return n;
   else
   {
     int q=1;
     while((n*q)%46663!=1)
     {
      q++;
     }
     return q;
   }
  }


  private void key_generation()
  {
     int x = (int)(Math.random()*50+100);
     int y=1;
     if(x%2==0)
      x++;
     while(!prime(x))
     {
      x+=2;
     }
     while((x*y)%46620!=1)
     {
      y++;
     }
    JOptionPane.showMessageDialog(null,"Public key is : "+x+"\nPrivate key is : "+y+"\nModuler number (n) is : 47053","key generation",JOptionPane.INFORMATION_MESSAGE);
  }

  private boolean prime(int x)
  {
    for(int i=2;i<x/2+1;i++)
    {
      if(x%i==0)
       return false;
    }
    return true;
  }


  private String encryption1(String a,int k)
  {
    if(a.length()==0)
     return "" ;
    a=a.toUpperCase();
    if(a.length()%3==1)
    {
     a+="XX";
    }
    if(a.length()%3==2)
    {
     a+="X";
    }
    char s []= a.toCharArray();
    int p;
    String output="";
    for(int i=0;i<s.length;i+=3)
    {
     p = ( (reflection(s[i])-1)*1296 + (reflection(s[i+1])-1)*36 + (reflection(s[i+2])-1) +1 );
     p = (p*k)%46663;
     p --;
     output+=dereflection((p/1296)+1);
     p=p%1296;
     output+=dereflection((p/36)+1);
     p=p%36;
     output+=dereflection(p+1);
     if(i%40==0 && i/40>0)
      output+="\n";
    }
    return output;
  }


  private String encryption2(String a,int k)
  {
    if(a.length()==0)
     return "" ;
    a=a.toUpperCase();
    if(a.length()%3==1)
    {
     a+="XX";
    }
    if(a.length()%3==2)
    {
     a+="X";
    }
    char s []= a.toCharArray();
    int p;
    String output="";
    for(int i=0;i<s.length;i+=3)
    {
     p = ( (reflection(s[i])-1)*1296 + (reflection(s[i+1])-1)*36 + (reflection(s[i+2])-1) +1 );
     p = (analysis(p,k))%47053;
     p --;
     output+=dereflection((p/1296)+1);
     p=p%1296;
     output+=dereflection((p/36)+1);
     p=p%36;
     output+=dereflection(p+1);
     if(i%40==0 && i/40>0)
      output+="\n";
    }
    return output;
  }

  private int analysis(int x,int y)
  {
    if( y == 2)
     return (x*x)%47053;
    else
    {
     if(y % 2 == 0)
      return (analysis((x*x)%47053,y/2)%47053);
     else
      return (x*analysis(x,y-1))%47053;
    }
  }

  private void open()
  {
    JFileChooser filechooser=new JFileChooser();
    filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int r = filechooser.showOpenDialog(this);
    if(r==JFileChooser.CANCEL_OPTION)
      return;
    File file = filechooser.getSelectedFile();
    if(file==null||file.getName().equals(""))
      JOptionPane.showMessageDialog(null,"Invalid file name","Invalid file name",JOptionPane.ERROR_MESSAGE);
    else
    {
      try
      {
        input = new ObjectInputStream(new FileInputStream(file));
        source.setText((String)input.readObject());

      }
      catch(Exception e)
      {
        JOptionPane.showMessageDialog(null,"Error opinig file","Error opinig file",JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void save()
  {
    JFileChooser filechooser=new JFileChooser();
    filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int r = filechooser.showSaveDialog(null);
    if(r==JFileChooser.CANCEL_OPTION)
      return;
    File file = filechooser.getSelectedFile();
    if(file==null||file.getName().equals(""))
      JOptionPane.showMessageDialog(null,"Invalid file name","Invalid file name",JOptionPane.ERROR_MESSAGE);
    else
    {
      try
      {
        output = new ObjectOutputStream(new FileOutputStream(file));
        output.writeObject(result.getText());
        output.flush();
      }
      catch(Exception e)
      {
        JOptionPane.showMessageDialog(null,"Error saving file","Error saving file",JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void close()
  {
   try
   {
    if(input!=null)
     input.close();
    if(output!=null)
     output.close();
   }
   catch(Exception e)
   {
    JOptionPane.showMessageDialog(null,"Error closing file","Error closing file",JOptionPane.ERROR_MESSAGE);
   }
  }
}
