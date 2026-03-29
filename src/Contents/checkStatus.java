/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contents;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Kurt
 */
public class checkStatus {

    int x = 0;
    Connection con;

    public void sqlconnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql:///dbAccount", "root", "");

            if (!con.isClosed()) {
                System.out.println("System Online!");
            }
        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
        }
    }

    public void CheckStatus() {
        sqlconnect();

        int i = 0;
        String sql = "select * from infologin";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                if (rs.getInt("status") == 1) {
                    x = 1;
                    break;
                } else {
                    x = 0;
                }
            }

            opener();
        } catch (Exception e) {
            System.out.println("Error CheckStatus: " + e);
        }
    }

    public void opener() {
        if (x == 0) {
            LogIn li = new LogIn();
            li.setVisible(true);
        } else {
            MainFrameStudent mf = new MainFrameStudent();
            mf.setVisible(true);
        }
    }
}
