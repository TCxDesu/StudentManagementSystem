/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contents;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.security.Key;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Kurt
 */
public class SetTable {

    DefaultTableModel mod = new DefaultTableModel();
    DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
    JTable tbl;
    ResultSet rs;
    Connection con;
    final String ALGORITHM = "AES";
    final String KEY = "1Hbfh667adfDEJ78";

    public SetTable() {
//        DefaultTableModel mod = new DefaultTableModel();
//        JTable tbl;
//        ResultSet rs;
//        Connection con;
    }

    public SetTable(JTable tblData) {
        tbl = tblData;
        tbl.setModel(mod);
        sqlConnect();
//        dtcr.setHorizontalAlignment(JLabel.CENTER);
//        tbl.setDefaultRenderer(String.class, dtcr);
        tbl.getTableHeader().setOpaque(false);
        tbl.getTableHeader().setDefaultRenderer(new HeaderColor());
        tbl.getTableHeader().setForeground(new Color(245, 245, 245));
        tbl.setRowHeight(25);
        tbl.setBackground(new Color(36, 56, 50));
        tbl.setForeground(Color.WHITE);

//        for(int x = 0; x < tbl.getColumnCount(); x++){
//            tbl
//        }
    }

    public void addCol() {
        mod.addColumn("Username");
//        mod.addColumn("Password");
        mod.addColumn("Account Status");
    }

    public void addCol2() {
        mod.addColumn("Username");
//        mod.addColumn("Password");
    }

    public void addRow() {
        if (tbl.getRowCount() > 0) {
            mod.setRowCount(0);
        }
        try {
            int x;
            Statement st = con.createStatement();
            String sql = "select * from infologin";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("username"));
//                v.add(hide(decrypt(rs.getString("password")).length()));

                switch (rs.getInt("block")) {
                    case 3:
                        v.add("DISABLED");
                        break;
                    default:
                        v.add("ENABLED");
                        break;

                }
                mod.addRow(v);
            }
        } catch (Exception e) {
            System.out.println("error:" + e);
        }
    }

    public void addRow2() {
        if (tbl.getRowCount() > 0) {
            mod.setRowCount(0);
        }
        try {
            int x;
            Statement st = con.createStatement();
            String sql = "select * from infologin";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("username"));
                
                mod.addRow(v);
            }
        } catch (Exception e) {
        }
        
    }
    
    
    
    public void addRowSearch(String sql) {
        if (tbl.getRowCount() > 0) {
            mod.setRowCount(0);
        }
        try {
            int x;
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("username"));
                switch (rs.getInt("block")) {
                    case 3:
                        v.add("DISABLED");
                        break;
                    default:
                        v.add("ENABLED");
                        break;

                }
                mod.addRow(v);
            }
        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
    }
        

    public void sqlConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql:///dbAccount", "root", "");

        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
        }
    }

    public static void main(String[] args) {
        new SetTable();
    }

    public Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        return key;
    }

    public String decrypt(String value) {
        String decryptedValue = "";
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            decryptedValue = new String(decryptedByteValue, "utf-8");
        } catch (Exception e) {
            System.err.println("Error in Decrypt: " + e);
        }
        return decryptedValue;
    }

    public String hide(int x) {

        String pass = "";
        for (int i = 0; i < x; i++) {
            pass = "•" + pass;
        }
        return pass;
    }

    public class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            setBackground(new Color(79, 92, 82));
            setHorizontalAlignment(CENTER);
            setFont(new Font("SansSerif", Font.BOLD, 16));
            return this;
        }

    }
    public void fixTable(JScrollPane scroll) {
        scroll.setVerticalScrollBar(new ScrollBarCustom());
    }
    
    public void SetTableForAudit(JTable tblData) {
        tbl = tblData;
        tbl.setModel(mod);
        sqlConnect();
//        dtcr.setHorizontalAlignment(JLabel.CENTER);
//        tbl.setDefaultRenderer(String.class, dtcr);
//        for(int x = 0; x < tbl.getColumnCount(); x++){
//            tbl
//        }
    }
    public void setTableAudit() {
        
        mod.addColumn("Time");
        mod.addColumn("User");
        mod.addColumn("Action");
        
        if (tbl.getRowCount() > 0) {
            mod.setRowCount(0);
        }
        try {
            int x;
            Statement st = con.createStatement();
            String sql = "select * from audit";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Vector v = new Vector();
                String date = rs.getString("Time");
                v.add(date.substring(0,date.length()-2));
                v.add(rs.getString("User"));
                v.add(rs.getString("Action"));
                mod.addRow(v);
            }
        } catch (Exception e) {
            System.out.println("error:" + e);
        }
    }
}
