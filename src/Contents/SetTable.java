/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contents;

import com.mysql.jdbc.Connection;
import java.security.Key;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        tbl.setDefaultRenderer(String.class, dtcr);

//        for(int x = 0; x < tbl.getColumnCount(); x++){
//            tbl
//        }
    }

    public void addCol() {
        mod.addColumn("Username");
        mod.addColumn("Password");
        mod.addColumn("Block Status");
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
                v.add(decrypt(rs.getString("password")));

                switch (rs.getInt("block")) {
                    case 3:
                        v.add("BLOCKED");
                        break;
                    default:
                        v.add("NOT BLOCKED");
                        break;

                }
                mod.addRow(v);
            }
        } catch (Exception e) {
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
}
