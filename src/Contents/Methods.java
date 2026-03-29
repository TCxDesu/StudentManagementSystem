/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contents;

import com.mysql.jdbc.Connection;
import java.security.Key;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Kurt
 */
public class Methods {

    final String ALGORITHM = "AES";
    final String KEY = "1Hbfh667adfDEJ78";
    Connection con;

    public Methods() {

    }

    public void adder(String user, String pass, int f) {
        sqlconnect();
        String username = user;
        String password = encrypt(pass);

        String sql = "insert into infologin values(?,?,0,?,0,0,CURRENT_TIMESTAMP())";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, f);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Added!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Account Already Exist!");
        }
    }

    public void sqlconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql:///dbAccount", "root", "");

        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
        }
    }

    public Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        return key;
    }

    public String encrypt(String value) {
        String b64value = "";
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            b64value = new BASE64Encoder().encode(encryptedByteValue);
        } catch (Exception e) {
            System.err.println("Error in Encrypt: " + e);
        }
        return b64value;
    }

    public int checker(String user, String pass, int f) {
        int a = 0;

        switch (f) {
            case 0:
            case 1:
                if (user.trim().length() != 0 && !user.equals("Enter Username")) {
                    if (pass.trim().length() != 0 && !pass.equals("Enter Password")) {
                        a = 1;
                    } else {
                        JOptionPane.showMessageDialog(null, "Password must not be blank!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username must not be blank!");
                }
                break;

            default:
                JOptionPane.showMessageDialog(null, "Please select an option");
        }

        return a;
    }
}
