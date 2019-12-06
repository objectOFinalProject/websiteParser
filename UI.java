import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.net.URL;
 
/*class ButtonHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"I am the boring one.");
    }
}*/
public class UI extends JFrame implements ActionListener {
    public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miExit);
        mbar.add(mnuFile);
        setJMenuBar(mbar);
        JMenu mnuHelp = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About");
        miAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null,"Author: Rudra Patel");
            }
        });
        mnuHelp.add(miAbout);
        mbar.add(mnuHelp);
    }
    public UI() {
        // set up the look inside the constructor
    	setTitle("Web Scrapper");
        setBounds(50,100,400,300);  // left = 50, top=100, width=400, height= 300
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    	JPanel btmPnl = new JPanel();
    	btmPnl.setLayout(new FlowLayout());
    	JPanel topPnl = new JPanel();
        topPnl.setLayout(new FlowLayout());
        JButton btnSaveToText = new JButton("Save to Text");
        JButton btnSaveToJSON = new JButton("Save to JSON");
        JLabel label = new JLabel("Enter Website:");
        JTextField txtURL = new JTextField(20);
        JButton btnFetch = new JButton("Fetch");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        
        topPnl.add(label);
        topPnl.add(txtURL);
        topPnl.add(btnFetch);
        c.add(topPnl,BorderLayout.NORTH);
        
        JTextArea txt = new JTextArea();
        Font font = new Font("Monospaced", Font.BOLD,20);
        txt.setFont(font);
        //c.add(txt, BorderLayout.CENTER);
        JScrollPane sp = new JScrollPane(txt);
        sp.setPreferredSize(new Dimension(400,300));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(sp, BorderLayout.CENTER);
        
        btnFetch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = txtURL.getText();
                Scanner sc = new Scanner(text);
                String addr = sc.nextLine();
                String line;
                try {
                    URL link = new URL(addr);
                    Scanner linksc = new Scanner(link.openStream());
                    while (linksc.hasNextLine()) {
                        line = linksc.nextLine();
                        txt.setText(txt.getText() + line + "\n");
                    }
                    linksc.close();
                	} catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Could not connect to the website.");
                	}
                /*String txt = link.getText();
                textToShow = textToShow + "\n" + text;
                txaWords.setText(textToShow);*/
            	}
            });
        
        btmPnl.add(btnSaveToText);
        c.add(btmPnl,BorderLayout.SOUTH);
        
        btmPnl.add(btnSaveToJSON);
        c.add(btmPnl,BorderLayout.SOUTH);
        
        setupMenu();
    }
    public void actionPerformed(ActionEvent e) {
//      System.out.println("Hey. Hee haw. I'm from the South. Waffle House.");
        //JOptionPane.showMessageDialog(null,"");
    }
    public static void main(String[] args) {
        UI frm = new UI();
        frm.setVisible(true);  // show the frame
    }
}