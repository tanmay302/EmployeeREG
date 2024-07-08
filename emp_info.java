import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Vector;

class  swingTemp extends JFrame implements ActionListener {
        JTextField eid, enm, edesg, esal, display;
        JButton b1, b2,clear,next1;
        JLabel l1, l2, l3, l4;

        swingTemp() {
            setTitle("Employee INFORMATION");
            l1 = new JLabel("Employee ID:");
            l2 = new JLabel("Employee Name:");
            l3 = new JLabel("Employee Designation:");
            l4 = new JLabel("Employee Salary:");
            eid = new JTextField(20);
            enm = new JTextField(20);
            edesg = new JTextField(20);
            esal = new JTextField(20);
            display = new JTextField(100);

            b1 = new JButton("SUBMIT");
            b2 = new JButton("VIEW RECENTS");
            clear=new JButton("Clear");
            next1=new JButton("VIEW ALL");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(8,5));
            setSize(800, 800);
            add(l1);
            add(eid);
            add(l2);
            add(enm);
            add(l3);
            add(edesg);
            add(l4);
            add(esal);
            add(b1);
            add(b2);
            add(clear);
            add(display, BorderLayout.CENTER);
            add(next1);
            b1.addActionListener(this);
            //b1.setSize(100,100);
            b2.addActionListener(this);
            clear.addActionListener(this);
            next1.addActionListener(this);
            setVisible(true);

        }

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == b1) {
                int id = Integer.parseInt(eid.getText());
                String name = enm.getText();
                String desg = edesg.getText();
                int sal = Integer.parseInt(esal.getText());
                Connection con = null;
                PreparedStatement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection("jdbc:postgresql://localhost/student", "postgres", "");
                    stmt = con.prepareStatement("insert into emp values(?,?,?,?)");
                    stmt.setInt(1, id);
                    stmt.setString(2, name);
                    stmt.setString(3, desg);
                    stmt.setInt(4, sal);
                    int result = stmt.executeUpdate();
                    if (result == 1) {
                        JOptionPane.showMessageDialog(null, "Succssfully Inserted", name, JOptionPane.INFORMATION_MESSAGE);

                    }
                    stmt.close();
                    con.close();


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e, "Error Occured", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (ae.getSource() == b2) {
                Connection con = null;
                ResultSet rs = null;
                Statement stmt = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection("jdbc:postgresql://localhost/student", "postgres", "");
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("select * from emp");
                    int id1,sal1;
                    String name1,desg1;
                    while (rs.next()) {
                              id1  =rs.getInt(1);
                         name1 = rs.getString(2);
                         desg1 = rs.getString(3);
                         sal1 = rs.getInt(4);
                        System.out.println("Id:" + id1 + "Name:" + name1 + "Designation:" + desg1 + "Salary:" + sal1);
                        display.setText("Id:\n" + id1 + "\nName:\n" + name1 + "\nDesignation:\n" + desg1 + "\nSalary:\n" + sal1);

                    }

                    //display.setText("Id:" + id1 + "Name:" + name1 + "Designation:" + desg1 + "Salary:" + sal1);
                } catch (Exception ae1) {
                    JOptionPane.showMessageDialog(null, ae1, "Error Occured", JOptionPane.ERROR_MESSAGE);
                }

            }

            if(ae.getSource()==clear)
            {
                eid.setText("");
                enm.setText("");
                edesg.setText("");
                esal.setText("");
                display.setText("");

            }
            if(ae.getSource()==next1)
            {
                try{
                //JOptionPane.showMessageDialog(null,"Clicekd");
                JFrame f;
                f = new JFrame("Welcome");
                JLabel l1=new JLabel("Welcome");
                JTable t;
                JScrollPane js;
                Statement stmt =null;
                Connection con=null;
                ResultSet rs=null;
                ResultSetMetaData rsmd=null ;
                int columns;
                Vector columnNames = new Vector();
                Vector data = new Vector(); Class.forName("org.postgresql.Driver");
                con=DriverManager.getConnection("jdbc:postgresql://localhost/student","postgres","");
                System.out.println("Connected");
                stmt=con.createStatement();
                rs = stmt.executeQuery("select * from emp");
                rsmd = rs.getMetaData();
                columns = rsmd.getColumnCount();
                JPanel p=new JPanel();
                JPanel p1=new JPanel();
                for(int i = 1; i <= columns; i++)
                {
                    columnNames.addElement(rsmd.getColumnName(i));  
                }

                //Get row data
                while(rs.next())
                {
                    Vector row = new Vector(columns);
                    for(int i = 1; i <= columns; i++)
                    {
                        row.addElement(rs.getObject(i));
                    }
                    data.addElement(row);
                }

                t = new JTable(data, columnNames);
                js = new JScrollPane(t);

                p1.add(js);
                add(p1);

                setSize(600, 600);
                setVisible(true);
            }
            catch(Exception e3)
            {
                System.out.println(e3);
            }
        }

            }

        }

        class finalSwing{
        public static void main(String args[])
        {
            swingTemp st=new swingTemp();
        }
    }



