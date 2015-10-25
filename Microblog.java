import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class Microblog {

	private JFrame frame;
	private JTextField search_tab_txt;
	private String post1; 
	private JTabbedPane tabbedPane;
	private JPanel panel_2;
	private JPanel panel;
	private JPanel panel_1;
	private JTextPane text_activities;
	private JTextArea text_Post;
	private JButton post_btn;
	private JTextField search_msg;
	private JTextPane current_msg;
	private JButton msg_send_btn;
	private JTextPane cnvrstn_msg;
	private JTextPane msg_list;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Microblog window = new Microblog();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Microblog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menubar();
		tabs();
						
	}
	
	private void menubar()
	{	JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu logout_menu = new JMenu("Log Out");
		menuBar.add(logout_menu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sure?");
		logout_menu.add(mntmNewMenuItem);			
	}
	
	private void tabs()
	{	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		Activities_tab();
		Search_tab();
		Messages_tab();	
	}
	
	private void Activities_tab()
	{	panel_2 = new JPanel();
		tabbedPane.addTab("Activities", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();		
		JPanel panel_4 = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(6)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addGap(9))
		);
		
		text_activities = new JTextPane();		
		text_activities.setEditable(false);
		scrollPane.setViewportView(text_activities);

		Activity_thread();
		

		text_Post = new JTextArea();	
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		text_Post.setBorder(BorderFactory.createCompoundBorder(border, 
		BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		post_btn = new JButton("Post");
		
		post_btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					 try{ 
							post1 = text_Post.getText();
							JOptionPane.showMessageDialog(null, post1);
							post_thread();
						}
						catch(Exception e)
						{	JOptionPane.showMessageDialog(null, "Try Again");						
						}
					}
				});

				GroupLayout gl_panel_4 = new GroupLayout(panel_4);
				gl_panel_4.setHorizontalGroup(
					gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(text_Post, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(post_btn, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_panel_4.setVerticalGroup(
					gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(text_Post, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(post_btn, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addContainerGap())
				);
				panel_4.setLayout(gl_panel_4);
				
				JLabel username = new JLabel("Name");
				
				JLabel num_of_followers = new JLabel("Follower");
				GroupLayout gl_panel_3 = new GroupLayout(panel_3);
				gl_panel_3.setHorizontalGroup(
					gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(username)
							.addPreferredGap(ComponentPlacement.RELATED, 525, Short.MAX_VALUE)
							.addComponent(num_of_followers)
							.addContainerGap())
				);
				gl_panel_3.setVerticalGroup(
					gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(username)
								.addComponent(num_of_followers))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				panel_3.setLayout(gl_panel_3);
				panel_2.setLayout(gl_panel_2);
		
		
	}

	//Activity thread
	private void Activity_thread()
	{	//	
	}

	//Post
	private void post_thread()
	{
	}
	
	private void Search_tab()
	{
		panel = new JPanel();
		tabbedPane.addTab("Search", null, panel, null);
		
		search_tab_txt = new JTextField();
		search_tab_txt.setColumns(10);
		
		JButton search_tab_btn = new JButton("Search");
		search_tab_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//close old thread and open new thread
				search_Thread();
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(search_tab_txt, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(search_tab_btn))
						.addComponent(scrollPane_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(search_tab_txt, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(search_tab_btn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}

	private void search_Thread()
	{	//open and then regularly update searched_profile
	}

	private void Messages_tab()
	{	panel_1 = new JPanel();
		tabbedPane.addTab("Message", null, panel_1, null);
		
		JPanel panel_5 = new JPanel();		
		JPanel panel_6 = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);

		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
					.addGap(4))
		);
		
		JPanel panel_7 = new JPanel();		
		JPanel panel_8 = new JPanel();
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_8, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_7, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 481, Short.MAX_VALUE))
					.addContainerGap(2, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 272, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		current_msg = new JTextPane();		
		msg_send_btn = new JButton("New button");
		msg_send_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				send_msg();
			}
		});
		
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(current_msg, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(msg_send_btn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addComponent(current_msg, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_panel_8.createSequentialGroup()
					.addComponent(msg_send_btn, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
		);
		
		cnvrstn_msg = new JTextPane();
		cnvrstn_msg.setEditable(false);
		scrollPane_2.setViewportView(cnvrstn_msg);
		panel_7.setLayout(gl_panel_7);
		panel_6.setLayout(gl_panel_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		msg_list = new JTextPane();
		scrollPane_1.setViewportView(msg_list);
		panel_3.setLayout(null);
		
		search_msg = new JTextField();
		search_msg.setBounds(0, 3, 112, 23);
		panel_3.add(search_msg);
		search_msg.setColumns(10);
		
		JButton search_msg_btn = new JButton("New button");
		search_msg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search_acc_msg();
			}
		});
		search_msg_btn.setBounds(113, 3, 32, 23);
		panel_3.add(search_msg_btn);
		panel_5.setLayout(gl_panel_5);
		panel_1.setLayout(gl_panel_1);

		all_msg_thread();
		
	}
	
	private void search_acc_msg()
	{	//Open the acc, last 5 msg, make input area accesible,start current_msg_thread
		//else keep input area sealed, and ask for valid username
		
		
	}
	
	private void all_msg_thread()
	{	//Update regularly msg_list


	}

	private void current_msg_thread()
	{	//Update regularly cnvrstn_msg

	}

	private void send_msg()
	{	// send msg from current_msg		
	}


}
