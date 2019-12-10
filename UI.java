import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
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

import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
 
/** 
 * @author Rudra, Mariana, Payal
 * This class takes care of the interface
 * This interface consists of a menu bar, text field for the url, a fetch button, 
 * text filed for website content, a Save to Text button, and Save to JSON button
 */
public class UI extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void setupMenu() {
    	/**
    	 * File is a menu item in the menu bar
    	 * Exit will follow when the File option is clicked
    	 */
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
        /**
    	 * Help is a menu item in the menu bar
    	 * About will follow when the Help option is clicked
    	 */
        JMenu mnuHelp = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About");
        /**
         * About will perform an action that brings up a dialog box,
         * this contains author info.
         */
        miAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null,"Authors:  "
            			+ "Rudra Patel"
            			+ "\n                 Mariana Hernandez"
            			+ "\n                 Payal Ahuja");
            }
        });
        mnuHelp.add(miAbout);
        mbar.add(mnuHelp);
    }
    /**
     * This is public UI()
     * It takes care of how the user interface looks, and which actions are performed
     * when a certain button is clicked.
     */
    public UI() {
        // set up the look inside the constructor
    	setTitle("Web Scraper");
        setBounds(50,100,400,300);  // left = 50, top=100, width=400, height= 300
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /**
         * These panels will make up the interface.
         */
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
        /**
         * Takes care of the monospaced font required for the assignment
         */
        JTextArea txt = new JTextArea();
        Font font = new Font("Monospaced", Font.BOLD,20);
        txt.setFont(font);
        JScrollPane sp = new JScrollPane(txt);
        sp.setPreferredSize(new Dimension(400,300));
        /**
         * JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED adds a scroll bar as needed
         */
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(sp, BorderLayout.CENTER);
        /**
         * This will take the content from the website, and put it in the text field
         * in our UI instead of putting it in the Console.
         */
        
        
        btnFetch.addActionListener(new ActionListener() {
        
        	public void actionPerformed(ActionEvent e) {
                String text = txtURL.getText();
                @SuppressWarnings("resource")
				Scanner sc = new Scanner(text);
                String addr = sc.nextLine();
                String line;
                String linesCollection = "";
                ArrayList<Course> courses = new ArrayList<Course>();
                //ArrayList<Course> courses = new ArrayList<Course>();
                /**
                 * @return website content
                 * if there is an err
                 * then it'll print "Could not connect to the website."
                 */
                try {
                    URL link = new URL(addr);
                    Scanner linksc = new Scanner(link.openStream());
                    while (linksc.hasNextLine()) {
                    	//convertLinesToCourses(linesCollection);
                        line = linksc.nextLine();
                        txt.setText(txt.getText() + line + "\n");
                        linesCollection += line;
                    }
                    courses = convertLinesToCourses(linesCollection);
                    linksc.close();
                	} catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Could not connect to the website.");
                	}
            	}
            });
        /**
         * Button location south
         */
        btmPnl.add(btnSaveToText);
        
        btnSaveToText.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ScreenScraper s = new ScreenScraper();
        		Writer w = new Writer();
        		
        		try {
        			w.writeToText(s.getCourses());
        		} catch(Exception ex) {
        			ex.printStackTrace();
        			System.out.println("Could not save to text.");
        		}
        	}
        });
        	
        c.add(btmPnl,BorderLayout.SOUTH);
        
        btmPnl.add(btnSaveToJSON);
        btnSaveToJSON.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ScreenScraper s = new ScreenScraper();
        		Writer w = new Writer();
        		
        		try {
        			w.writeToJSON(s.getCourses());
        		} catch(Exception ex) {
        			ex.printStackTrace();
        			System.out.println("Could not save to JSON.");
        		}
        	}
        });
        
        c.add(btmPnl,BorderLayout.SOUTH);
        
        setupMenu();
    }
    private static ArrayList<Course> convertLinesToCourses(String linesCollection){
		String[] tableStrings = linesCollection.split("<table>");
		String[] courseStrings = tableStrings[0].split("<tr>");
		for(String course : courseStrings) {
			String[] elementStrings = course.split("<td");
			Course courseObj = new Course();
			for(String elementString : elementStrings) {
				String[] elements = elementString.split("\">");
				if(elements[0].contains("coursenumber")) {
				int endOfCourseNameIndex = elements[3].indexOf("</a></td><td");
				String classId = elements[3].substring(0, endOfCourseNameIndex);
				courseObj.setCourseNumber(classId);
				}
				if(elements[0].contains("coursetitle")) {
				int endOfCourseTitleIndex = elements[2].indexOf("</td><td");
				String classTitle = elements[2].substring(0, endOfCourseTitleIndex);
				courseObj.setCourseTitle(classTitle);
				}
				if(elements[0].contains("credits")) {
				String credits = elements[2].substring(0, 1);
				courseObj.setCourseCredits(credits);
				}
			}
		}
		return null;
		
	}
    public void actionPerformed(ActionEvent e) {
    /**
     * This is used for debugging purposes for our code
     */
    }
    public static void main(String[] args) {
        UI frm = new UI();
        frm.setVisible(true);  // show the frame
        ScreenScraper s = new ScreenScraper();
        s.processURL();
    }
}