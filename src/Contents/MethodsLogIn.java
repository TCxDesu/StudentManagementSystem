/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contents;

import com.mysql.jdbc.Connection;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author user1
 */
public class MethodsLogIn {

    Connection con;
    final String ALGORITHM = "AES";
    final String KEY = "1Hbfh667adfDEJ78";
    String userName = "";
    int choiceNumber;
    int x = 0;
    int flag = 0;

    public static void startUp() {
        int i = 0;
        String sql = "select * from infologin";

        try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql:///dbAccount", "root", "");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (rs.getInt("status") == 1) {
                    if (rs.getInt("pChange") == 0) {
                        ChangePassword cp = new ChangePassword();
                        cp.setVisible(true);
                        new LogIn().setVisible(false);
                        i = 0;
                        break;
                    } else {
                        MainFrameUser mf = new MainFrameUser();
                        mf.setVisible(true);
                        new LogIn().setVisible(false);
                        i = 0;
                        break;
                    }

                } else {
                    i = 1;

                }
            }
            if (i == 1) {
                new LogIn().setVisible(true);
            }

        } catch (Exception e) {
            System.out.println("Error CheckStatus: " + e);
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

    public void login(String username, String password, String password2) {
        try {
            Statement st = con.createStatement();
            String sql = "select * from infologin";
            ResultSet rs = st.executeQuery(sql);

            if (username.equals(userName)) {
                if (username.trim().length() != 0 && !username.equals("Enter Username")) {

                    if (password2.trim().length() != 0 && !password.equals("Enter Password")) {

                        while (rs.next()) {
                            if (username.equals(rs.getString("username")) && password.equals(decrypt(rs.getString("password")))) {
                                if (rs.getInt("block") == 3) {
                                    blockable(username);
                                    break;
                                } else {
                                    checkAdmin(username, password);
                                    resetBlock();
                                    break;
                                }
                            } else if (username.equals(rs.getString("username")) && !password.equals(decrypt(rs.getString("password")))) {
                                blockable(username);
                                break;

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Password must not be blank!");
                        password = "";
                        username = "";
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username must not be blank!");
                    username = "";
                }
            } else {
                userName = username;
                System.out.println("he");
                resetBlock();
                login(username, password, password2);
            }
        } catch (Exception e) {
            System.out.println("Error in Login: " + e);
        }

    }

    public void checkAdmin(String username2, String password) {
        try {
            Statement st = con.createStatement();
            String sql = "select * from infologin";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (username2.equals(rs.getString("username"))) {
                    if (rs.getInt("status") == 2) {
                        JOptionPane.showMessageDialog(null, "The Admin Account That You Are Trying To Sign In \n Is Already Signed In!");
                    } else if (rs.getInt("status") == 1) {
                        JOptionPane.showMessageDialog(null, "The Account That You Are Trying To Sign In \n Is Already Signed In!");
                    } else {
                        choiceNumber = UpdateStatus1(rs.getInt("isAdmin"), username2, password);
                    }
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println("Error in checkAdmin: " + e);
        }
    }

    public int UpdateStatus1(int x, String username, String password) {
        int y = 0;
        try {
            if (x == 1) {
                String sql = "update infologin set status = ? where username = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, 2);
                pst.setString(2, username);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Welcome Admin");
                MainFrameAdmin mfa = new MainFrameAdmin();
                mfa.setVisible(true);
                y = 2;
            } else {
                String sql = "update infologin set status = ? where username = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, 1);
                pst.setString(2, username);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully Logged In \n       Welcome " + username + "!");
                changePassword(username, password);
                y = 1;
            }

        } catch (Exception e) {
            System.out.println("Error in UpdateStatus1: " + e);
        }
        return y;
    }

    public void resetBlock() {
        try {
            String sql = "update infologin set block = ? where block < ?";
            PreparedStatement pst;
            pst = con.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setInt(2, 3);

            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error in resetBlock: " + e);
        }

    }

    public void resetBlock2() {
        try {
            String sql = "update infologin set block = ?";
            PreparedStatement pst;
            pst = con.prepareStatement(sql);
            pst.setInt(1, 0);

            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error in resetBlock: " + e);
        }

    }

    public void updateDate(String date) {
        try {
            String sql = "update infologin set date = ?";
            PreparedStatement pst;
            pst = con.prepareStatement(sql);
            pst.setString(1, date);

            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error date:" + e);
        }
    }

    public void checkDay() {
        String sql = "select date from infologin";
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (!rs.getString(1).equals(ft.format(date))) {
                    resetBlock2();
                    updateDate(ft.format(date));
                    System.out.println(rs.getString(1));
                    JOptionPane.showMessageDialog(null, "All Blocks From Yesterday Has Been Reverted");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error checkDay: " + e);
        }
    }

    public void checkStatus(String username, String password, String password2) {
        int i = 0;
        sqlconnect();
        String sql = "select status from infologin";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    i = 1;
                    break;
                } else {
                    i = 0;
                }
            }

            if (i == 0) {
                login(username, password, password2);
            } else if (i == 1) {
                checkAdmin(username, password);

            }

        } catch (Exception e) {
            System.out.println("Error checkStatus: " + e);
        }
    }

//    public char showPassword(int flag) {
//char c = ' ';
//        if (flag == 0) {
////                Icon ImgIcon = new ImageIcon(getClass().getResource("/Image/1667188461554126440-128.png"));
////                lblEye.setIcon(ImgIcon);
//            c = ((char) 0);
//        } else if (flag == 1) {
////                Icon ImgIcon = new ImageIcon(getClass().getResource("/Image/19318253741554126440-128.png"));
////                lblEye.setIcon(ImgIcon);
//            c = ('\u25cf');
//        }
//        return c;
//    }
    public void blockable(String username2) {
        try {
            Statement st = con.createStatement();
            String sql = "select * from infologin";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (username2.equals(rs.getString("username"))) {
                    if (rs.getInt("block") == 3) {
                        JOptionPane.showMessageDialog(null, "This Account Is Already Blocked!");
                        break;
                    } else if (rs.getInt("block") + 1 == 3) {
                        JOptionPane.showMessageDialog(null, "This Account Has Been Blocked!");
                        blockable2(rs.getInt("block") + 1, username2);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Username and Password did not match");
                        blockable2(rs.getInt("block") + 1, username2);
                        break;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Error in blockable: " + e);
        }
    }

    public void blockable2(int x, String username2) {

        try {
            String sql = "update infologin set block = ? where username = ?";
            PreparedStatement pst;
            pst = con.prepareStatement(sql);
            pst.setInt(1, x);
            pst.setString(2, username2);

            pst.executeUpdate();
            System.out.println("hi");
        } catch (Exception e) {
            System.out.println("Error in blockable2: " + e);
        }
    }

    public void changePassword(String username, String password) {

        try {
            Statement st = con.createStatement();
            String sql = "select * from infologin";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    if (rs.getInt("pChange") == 0) {
                        LogIn li = new LogIn();
                        li.dispose();
                        ChangePassword cp = new ChangePassword();
                        cp.setUsername(username);
                        cp.setVisible(true);

                        break;
                    } else {
                        LogIn li = new LogIn();
                        li.dispose();
                        MainFrameUser mfu = new MainFrameUser();
                        mfu.setPassword(password);
                        mfu.getUsername(username);
                        mfu.setVisible(true);

                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in changePassword: " + e);
        }
    }

    public int choice() {
        return choiceNumber;
    }

    public void existing(String username, String password, String password2) {
        int x = 0;

        String sql = "select username from infologin";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (username.equals(rs.getString("username"))) {
                    checkStatus(username, password, password2);
                    x = 0;
                    break;
                } else {
                    x = 1;
                }

            }

            if (x == 1) {
                JOptionPane.showMessageDialog(null, "Username does not exist");
            }

        } catch (Exception e) {
            System.out.println("Error in blockable: " + e);
        }
    }

    public void showPass(JLabel lbl1, JPasswordField pass) {
        if (flag == 0) {

            pass.setEchoChar((char) 0);
            lbl1.setText("Hide Password");
            flag = 1;
        } else if (flag == 1) {

            pass.setEchoChar('\u25cf');
            lbl1.setText("Show Password");
            flag = 0;
        }
    }

    public void powerUser(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ALT && x == 0) {
            x++;

        }
        if (evt.getKeyCode() == KeyEvent.VK_F1 && x == 1) {
            x++;

        }
        if (x == 2) {
            JOptionPane.showMessageDialog(null, "Power User....");
            new MainFrameAdmin().setVisible(true);
            LogIn li = new LogIn();
            li.dispose();
            x = 0;
        }
    }

    public void powerUserReleased(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ALT && x > 0) {
            x--;

        }
    }

    public void loginKeyPress(java.awt.event.KeyEvent evt, String username, String password, String password2) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            existing(username, password, password2);
            if (choice() > 0) {
                LogIn li = new LogIn();
                li.dispose();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ALT && x == 0) {
            x++;

        }
        if (evt.getKeyCode() == KeyEvent.VK_F1 && x == 1) {
            x++;

        }
        if (x == 2) {
            JOptionPane.showMessageDialog(null, "Power User....");
            new MainFrameAdmin().setVisible(true);
            LogIn li = new LogIn();
            li.dispose();
            x = 0;
        }

    }
}
