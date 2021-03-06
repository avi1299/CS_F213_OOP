/************************************************************************************************
*                                       READ THE INSTRUCTIONS BEFORE STARTING THE TEST
*                                       
* 1. Rename the file with your ID no. For eg. if your ID no is 2017A7PSXXXXP, rename the file as
* P2017A7PSXXXX.java. 
* 2. Solve the questions in chronological order i.e. Q1, Q2, Q3 etc. and not by the order of their appearance.
* 3. You are are NOT ALLOWED to 
* 		* Declare new instance variables
* 		* Modify the code which is provided except those methods for which you are explicitly asked to write the code.
* 		* Create new classes and/or methods
* 		* Import any new class to your solution 
* 4. Partial code is given to you. GUI design and a video of the necessary interaction with the GUI are provided for your reference.
* 5. Questions are given as comments. Write the code only for the portions that are intentionally left blank.
* 6. Periodically save your work to avoid any last minute mishap.
* 7. Fill in the following details before starting the test.
* 
* ID: 2016B5A70715P
* NAME: Bhavesh Ranjit Chand
* ROOM No.:	6116
* MACHINE No.: 30
************************************************************************************************/

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class P2016B5A70715 {

	private JFrame mainFrame;
	private JPanel panel1, panel2;
	private JTabbedPane tabPane;

	private JLabel lbl_name, lbl_ID, lbl_Name, lbl_ItemName, lbl_Qty, lbl_BillAmt;
	private JTextField txtField1_Name, txtField2_Name, txtField_Qty, txtField_BillAmt;
	
	private JComboBox<Integer> txtField_ID;
	private JComboBox<String> txtField_Item;
	private JButton savebtn, resetbtn, addbtn, proceedbtn ;
	
	public static void main(String args[]) {
		P2016B5A70715 pro = new P2016B5A70715();
		pro.displayAppGUI();
	}
	
	P2016B5A70715() {
		
		/************************************************************************************************
		 * 							DONOT DELETE/MODIFY THE FOLLOWING CODE
		 * After you run the application for the first time comment the following lines of code
		 * This method should be called only one time when the application is executed for the first time
		 ************************************************************************************************/
//		try {
//			createDatabase();
//		} catch (ClassNotFoundException | SQLException e2) {
//			e2.printStackTrace();
//		}
		/************************************************************************************************/

		/************************************************************************************************
		 * UNCOMMENT THE FOLLOWING CODE TO DELETE ALL THE RECORDS FROM ANYONE OR ALL THE TABLES  
		 ************************************************************************************************/
//		try {
//			deleteAllRecordsFromTable("PURCHASE");
//			deleteAllRecordsFromTable("ITEM");
//			deleteAllRecordsFromTable("CUSTOMER");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		
		/************************************************************************************************
		 * 							DONOT DELETE/MODIFY THE FOLLOWING CODE
		 * After you run the application for the first time comment the following lines of code
		 * This method should be called only one time when the application is executed for the first time
		 ************************************************************************************************/
//		try {
//			insertItemInItemTable();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		/************************************************************************************************/
		
		
		/************************************************************************************************
		 * 								DONOT DELETE/MODIFY THE FOLLOWING CODE
		 *  Function calls to initialize all the GUI components and to populate the drop down boxes. 
		 *  You have to write code for these method. Unimplemented method stubs are provided below: 
		 *  refer Q. 1 and Q. 6  
		 ************************************************************************************************/
		initializeAppGUI();       // function call
		
		populate_txtField_ID_And_txtField_Item();		//function call
		
	 	/************************************************************************************************
	 	 * Q.7 WRITE CODE FOR THE ACTION LISTNER OF THE resetbtn - this should reset the txtField1
	 	 ************************************************************************************************/
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txtField1_Name.setText("");
			}
		});
		
		/************************************************************************************************
	 	 * Q.8 WRITE CODE FOR THE ACTION LISTNER OF THE savebtn - this event should automatically generates 
	 	 * the customer id using the getCustomerID() and inserts the customer id and name into the CUSTOMER table.
	 	 ************************************************************************************************/
	 	savebtn.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent ae) {
	 			Connection con;
	 			PreparedStatement st;
	 			
	 			int customerid;   // used to store the customer id
	 			String value1;    //used to store the customer name
		    
	 			try {        
	 				con=getConnection();
	 				st=con.prepareStatement("insert into CUSTOMER(CID,NAME) values(?,?)");
	 				customerid=getCustomerID(con);
	 				value1=txtField1_Name.getText();
	 				st.setInt(1,customerid);
	 		        st.setString(2,value1);
	 		        st.executeUpdate();
	 				con.close();
	 				JOptionPane.showMessageDialog(panel1, "Customer was added successfully.");
	 				txtField_ID.addItem(customerid);
	 			} catch(ClassNotFoundException e){
		    		JOptionPane.showMessageDialog(panel1,"Error in submitting data!");
		    	} catch (SQLException ex) {
		    		Logger.getLogger(P2016B5A70715.class.getName()).log(Level.SEVERE, null, ex);
		    	}
	 		}
		});

	 	/************************************************************************************************
	 	 * Q.9 WRITE CODE FOR THE ITEM LISTNER OF THE txtField_ID - this event is used to retrieve the customer name
	 	 * from the CUSTOMER table, when the customer id is chosen from the drop down box. It should then display name
	 	 * in the txtField2_Name text box.
	 	 ************************************************************************************************/
	 	txtField_ID.addItemListener(new ItemListener() {
	 		public void itemStateChanged(ItemEvent ie) {   
	 			
	 			Connection con;
	 			PreparedStatement st;
	 			ResultSet res;
	 			
	 			int value;  //used to store the selected customer id
	 			try {
	 				value=(int)txtField_ID.getSelectedItem();
	 				
	 				con = getConnection();
	 				st=con.prepareStatement("select * from CUSTOMER where CID=?");
	 				st.setInt(1,value);
	 				res=st.executeQuery();
	 				if(res.next()){
	 					txtField2_Name.setText(res.getString(2));
	 				}
	 				con.close();
	 			} catch (Exception e) {
	 				e.printStackTrace();
	 			}
			}
		});
	 	
	 	/************************************************************************************************
	 	 * Q.10 WRITE CODE FOR THE ACTION LISTNER OF THE addbtn - The record from the ITEM table is retrieved
	 	 * for the item chosen from the drop down list. If required quantity is available in the ITEM table, 
	 	 * a record is inserted into the PURCHASE table (customer id, item id, quantity, price) and the quantity 
	 	 * field in the ITEM table is updated. Else, an error message is thrown. Then clear all the fields for
	 	 * adding the next item.
	 	 ************************************************************************************************/
	 	addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Connection con;
	 			PreparedStatement st;
	 			ResultSet res;
	 			
				String value1;   //used to store selected item name 
				Integer value2;	 //used to store the quantity 
				
				int iid;		//used to store the item id
		    	
				try {
					value1=(String)txtField_Item.getSelectedItem();
					value2=Integer.parseInt(txtField_Qty.getText());
					
					con = getConnection();
	 				st=con.prepareStatement("select * from ITEM where NAME=?");
	 				st.setString(1,value1);
	 				res=st.executeQuery();
	 				if(res.next()){
	 					iid=res.getInt(1);
	 						
	 					if(res.getInt(3)>=value2){
	 						st=con.prepareStatement("insert into PURCHASE(CID,IID,QTY,PRICE) values(?,?,?,?)");
		 					st.setInt(1, (int)txtField_ID.getSelectedItem());
		 					st.setInt(2, iid);
		 					st.setInt(3, value2);
		 					st.setFloat(4, res.getFloat(4));
		 					st.executeUpdate();
		 					
		 					st=con.prepareStatement("update ITEM set QTY=? where IID=?");
		 					st.setInt(1, res.getInt(3)-value2);
		 					st.setInt(2, iid);
		 					st.executeUpdate();
				    		JOptionPane.showMessageDialog(panel2,"Item is successfully added to cart.");

	 					}
	 					else{
	 			    		JOptionPane.showMessageDialog(panel2,"Required quantity not available!");

	 					}
	 					
	 				}
	 				con.close();
		    		
		    	} catch (Exception e) {
		    			e.printStackTrace();
		    	}
		    	
			}
	 	});
	 	
	 	/************************************************************************************************
	 	 * Q.11 WRITE CODE FOR THE ACTION LISTNER OF THE proceedbtn - All records pertaining to a customer
	 	 * is retrieved from the PURCHASE table, the total bill amount is computed for all the items purchased 
	 	 * by a customer. 
	 	 ************************************************************************************************/
	 	proceedbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Connection con;
	 			PreparedStatement st;
	 			ResultSet res;
	 			
				int value;       	//used to store customer id
				float bill = 0;		//used to store the bill amount
				try {
	 				value=(int)txtField_ID.getSelectedItem();

					con = getConnection();
	 				st=con.prepareStatement("select * from PURCHASE where CID=?");
	 				st.setInt(1,value);
	 				res=st.executeQuery();
	 				while(res.next()){
	 					bill+= res.getFloat(4)*res.getInt(3);
	 				}
	 				txtField_BillAmt.setText(Float.toString(bill));
	 				con.close();
		        } catch (Exception e) {
					e.printStackTrace();
				}
					
				}
			});
	 	
	}// end of constructor
	
	/************************************************************************************************
 	 * Q.6 WRITE CODE FOR THIS METHOD TO POPULATE txtField_ID and txtField_Item from the CUSTOMER and 
 	 * ITEM tables. 
 	 ************************************************************************************************/
	public void populate_txtField_ID_And_txtField_Item() {
			Connection con;
			Statement statement1, statement2;
			ResultSet rs1, rs2;
			String query1, query2;
		
		try {
			con = getConnection();
			query1 = "select * from CUSTOMER";
			query2 = "select * from ITEM";
			statement1=con.createStatement();
			statement2=con.createStatement();
			
			rs1=statement1.executeQuery(query1);
			rs2=statement2.executeQuery(query2);
			
			while(rs1.next()){
				txtField_ID.addItem(rs1.getInt(1));
			}
			while(rs2.next()){
				txtField_Item.addItem(rs2.getString(2));
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/************************************************************************************************
 	 * Q.1 WRITE CODE FOR THIS METHOD TO INITIALIZE APP GUI 
 	 ************************************************************************************************/
	public void initializeAppGUI(){
		mainFrame = new JFrame("Online purchase");
		panel1=new JPanel(new FlowLayout());
		panel2=new JPanel(new FlowLayout());
		tabPane=new JTabbedPane();

		lbl_name=new JLabel("Customer Name:");
		lbl_ID=new JLabel("Customer ID:");
		lbl_Name=new JLabel("Customer Name:");
		lbl_ItemName=new JLabel("Item Name:");
		lbl_Qty=new JLabel("Quantity:");
		lbl_BillAmt=new JLabel("Bill Amount:");
		
		txtField1_Name=new JTextField(15);
		txtField2_Name=new JTextField(15);
		txtField_Qty=new JTextField(15);
		txtField_BillAmt=new JTextField(15);
		
		txtField_ID=new JComboBox<Integer>();
		txtField_Item=new JComboBox<String>();
		                
		savebtn=new JButton("Add");
		resetbtn=new JButton("Reset");
		addbtn=new JButton("Add more Items");
		proceedbtn=new JButton("Check out");
		
		panel1.add(lbl_name);
		panel1.add(txtField1_Name);
		panel1.add(savebtn);
		panel1.add(resetbtn);
		panel2.add(lbl_ID);
		panel2.add(txtField_ID);
		panel2.add(lbl_Name);
		panel2.add(txtField2_Name);
		panel2.add(lbl_ItemName);
		panel2.add(txtField_Item);
		panel2.add(lbl_Qty);
		panel2.add(txtField_Qty);
		panel2.add(lbl_BillAmt);
		panel2.add(txtField_BillAmt);
		panel2.add(addbtn);
		panel2.add(proceedbtn);
		
	}
	
	/************************************************************************************************
 	 * Q.2 WRITE CODE FOR THIS METHOD TO DISPLAY APP GUI 
 	 ************************************************************************************************/
	void displayAppGUI() {
		mainFrame.add(tabPane);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabPane.addTab("New Customer",panel1);
		tabPane.addTab("Existing Customer",panel2);
	
		mainFrame.setSize(500,200);
		mainFrame.setVisible(true);
	}
	
	/************************************************************************************************
 	 * Q.3 WRITE CODE FOR THIS METHOD TO GET DATABASE CONNECTION 
 	 ************************************************************************************************/
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
  	  	con = DriverManager.getConnection("jdbc:derby:cust;create=true;user=app;password=app");
          
		return con;
	}
	
	/************************************************************************************************
 	 * Q.4 WRITE CODE FOR THIS METHOD TO CREATE CUSTOMER, ITEM, AND PURCHASE TABLES 
 	 * TABLE : CUSTOMER, ATTRIBUTES - CID (int), NAME (var char)  (Customer id and customer name)
 	 * TABLE: ITEM, ATTRIBUTES - IID (int), NAME (var char), QTY (int), PRICE (float) (Item id, item name, quantity and price)
 	 * TABLE: PURCHASE, ATTRIBUTES - CID (int), IID (int), QTY (int), PRICE (float) (Customer id, item id, quantity, and price) 
 	 ************************************************************************************************/
	public void createDatabase() throws ClassNotFoundException, SQLException {
		
			Connection con;
			Statement stmt;
			
			con=getConnection();
			stmt= con.createStatement();
			
			String createString = "create table CUSTOMER(CID INTEGER PRIMARY KEY, NAME VARCHAR(20))"; 
			stmt.executeUpdate(createString);
			
			createString = "create table ITEM(IID INTEGER PRIMARY KEY, NAME VARCHAR(20), QTY INTEGER, PRICE REAL)"; 
			stmt.executeUpdate(createString);
			
			createString = "create table PURCHASE(CID INTEGER, IID INTEGER, QTY INTEGER, PRICE REAL)"; 
			stmt.executeUpdate(createString);
			
			con.close();
	}
	
	/************************************************************************************************
 	 * Q.5 WRITE CODE FOR THIS METHOD TO INSERT THREE ITEMS IN THE ITEM TABLE 
 	 ************************************************************************************************/
	public void insertItemInItemTable() {
	
			String query;
			Connection con;
			Statement stmt;
			
			try {
				con = getConnection();
				stmt = con.createStatement();
		        query = "insert into ITEM(IID,NAME,QTY,PRICE) values(1,'Mobile',5,10000)";
		        stmt.executeUpdate(query);
		        query = "insert into ITEM(IID,NAME,QTY,PRICE) values(2,'Chocolates',100,1)";
		        stmt.executeUpdate(query);
		        query = "insert into ITEM(IID,NAME,QTY,PRICE) values(3,'Notebooks',30,30)";
		        stmt.executeUpdate(query);
		        con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/******************************************************************************************************
	 * 						HELPER METHODS - DONOT MODIFY THE CODE OF THE FOLLOWING TWO METHODS
	 ******************************************************************************************************/
	public int getCustomerID(Connection con) {
		
		int value = 0;
		ResultSet rs;
		Statement stmt;
	    
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select Max(CID) from CUSTOMER");
		    rs.next();
		    if(rs.getInt(1) == 0) value = 100;
		    else value = rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	    	           
	    return value;
	}
	
	public void deleteAllRecordsFromTable(String tableName) throws ClassNotFoundException, SQLException {
		String query;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		query = "DELETE FROM " + tableName;
		stmt.executeUpdate(query);
	}
}
