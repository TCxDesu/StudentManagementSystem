# StudentManagementSystem 
Grade 12 System Development For Final Project

MEMBERS:
- Dela Rosa, Kurt Tristan
- Jacob, Angelica
- Offemaria, Luke Asher
- Vargas John Lloyd

System ChangeLog:

v1.0.0
    - Added Username TextField
    - Added Password PasswordField
    - Finished Designing LogIn Frame

v1.0.1
    - Added Close Button
    - Added Show Password Label
    - Refined Design
    - Added Function For The Buttons
    - Added Block Function
    - Added Log In Function
    - Added Function Where If The Date Changed,-
      The Block From Yesterday Will Be Reverted

v1.0.2
    - Bug Fix
    - Added Indication If A User Logged In Is An Admin
    - Added KeyEvent(Enter) For Username TextField
    - Added KeyEvent(Enter) For Password PasswordField
    - Added PowerUser Function (Alt + F1)

v1.0.3a
    - Bug Fix
    - Added A Log Out Function (Only For The User LogIn)

v1.0.3b
    - Bug Fix
    - Added A Change Password Frame
    - Added A Prototype User Main Frame
    - Added A Prototype Admin Main Frame

v1.0.4
    - Bug Fix
    - Finished The Change Password Frame
    - Added Function For Change Password Frame
    - Added Design For Admin Main Frame

v1.0.5
    - Bug Fix
    - Updated Admin Frame Background
    - Changed Admin Log Out Button To Back Button(Return To LogIn)

v1.0.5.1
    - Added Table In View Tab
    - Added A Table In Block Tab
    - Added A Table In Delete Tab

v1.0.5.2
    - Bug Fix
    - Working Add Account Function (Admin and User Accounts Can be Added)
    - Working Block/Unblock Function (right click, (ctrl + b), double click)
    - Working Delete Function (right click)

v1.0.5.3
    - Bug Fix
    - Optimized Admin Frame
    - Added Confirm Dialog For Blocking Account
    - Added Confirm Dialog For Delete Account
    - Added Search Bar For Account

v1.0.5.4
    - Bug Fix
    - Added Scroll Function On Tabbed Pane
    - Optimized Home Screen For User Account
    - Added Sort Function Via Search Bar
    - Added Change Username Frame

v1.0.5.5
    - Bug Fix
    - Optimized Codes In LogIn

v1.1.0
    - Recode of LogIn
    - Update On Layout Of MainFrameAdmin
    - Added Function on MainFrameAdmin
    - Added New Frame For Adding Account
    - Minor Fix On MainFrameUser(unfinished)
    - Minor Fix On UpdateInfo(unfinished)

v1.1.1
    - Added Audit Trail
    - Audit Trail Available for Log In
    - Audit Trail Available for Log Out
    - Audit Trail Available for Account Delete (Admin)
    - Audit Trail Available for Account Block/Unblock (Admin)
    - Added Search Bar For Audit Trail(no function)
    - Added Filter/Sort Box For Account Tab
    - Logged In Admin Account Indicated
    - Proper LogOut message for MainFrameAdmin

v1.1.2
    - Audit Trail Available for Adding Account
    - Audit Trail Available for Change Password
    - Audit Trail Available for Change Username
    - Minor Revision on Database Table (audit)
    - Edited Main Frame Layouts

v1.1.3
    - Added Sort Function For Audit Table And Account Table
    - If An Account Close The System, When They Open The System The Account Will
      Still Be Signed In(To Remove, Comment LogIn2/checkLogIn on the lower part 
      of the code)
    - Added Database Table For Student Info
    - Added UpdateStudentInfo Frame
    - Audit Trail For UpdateInfo
    - Table Can Be Saved To PDF

FOUND and FIXED BUGS
v1.0.1    - (Not Fixed) TextField And PasswordField Auto Clears 
                        If A User Attempts To Log In And Fails
v1.0.5.3  - (Not Fixed) Account That Is Logged In Or Is Being Used Can Be Deleted Or Blocked
v1.0.5.5  - (Not Fixed) UpdateInfo not working properly (currently under maintenance)
v1.1.0    - (Not Fixed) Logged In Account Won't Log In Properly 
v1.1.1    - (Not Fixed) Admin Username shows even if the account is being used
v1.1.1    - (Not Fixed) Multiple Block/Delete/Unblock
v1.1.1    - (Not Fixed) Admin LogOut Not on Audit Trail



v1.0.2    - (Fixed) A Bug With The Block Function
v1.0.3a   - (Fixed) A Minor Bug With The PowerUser Function
v1.0.3b   - (Fixed) A Bug With The PowerUser Function
v1.0.3b   - (Fixed) A Bug With The Block Function
v1.0.4    - (Fixed) A Bug From Tabbed Pane in Admin Main Frame
v1.0.4    - (Fixed) When An Admin Logged In And Close The Program, The Admin Won't Be Able To Log Back In
v1.0.4    - (Fixed) System Slows Down At First Attempt Log In
v1.0.5    - (Fixed) Validation Message When Admin Clicked Back On Admin Frame
v1.0.5.2  - (Fixed) Log In Where If An User Is Already Signed In And An Admin Tried To Log 
                    In, The Admin Won't Be Able To Log In
v1.0.5.2  - (Fixed) Can't Detect If An Added Account Is Existing
v1.0.5.3  - (Fixed) Block Count Still Stacked From Other Account
v1.0.5.4  - (Fixed) Wrong Colors On User Frame When User Enters Main Frame From Change Password Frame
v1.0.5.4  - (Fixed) Multiple Logged In User Account Get Affected When One Account Signed Out
v1.0.5.5  - (Fixed) "Username does not exist" validation does not show 
                    when user tried to log in with non existing account
v1.1.0    - (Fixed) Problem with system can't handle multiple user log in
v1.1.1    - (Fixed) "Username and Password must not be blank" validation doesn't show up


RECORD LOG
v1.0.0 - Start of creating system

v1.0.5.5 - Most Bugged System Version
    



SAMPLE TABLE ON MY DATABASE
as of(v1.0.0 -> v1.0.3a)
+----------+--------------------------+---------+--------+-------+------------+
| Username | Password                 | isAdmin | status | block | Date       |
+----------+--------------------------+---------+--------+-------+------------+
| user1    | lrZNofhNfwtCCupoVU9eJw== |       1 |      0 |     0 | 2023-03-06 |
| user2    | gDzNC/97HsLYVxeya6hRvQ== |       0 |      0 |     0 | 2023-03-06 |
| user3    | D2U06T4KBLxCkYFEbHwnqw== |       0 |      0 |     0 | 2023-03-06 |
+----------+--------------------------+---------+--------+-------+------------+

as of(v1.0.3b)
+----------+--------------------------+---------+--------+-------+------------+
| Username | Password                 | isAdmin | Status | Block | Date       |
+----------+--------------------------+---------+--------+-------+------------+
| Admin    | lrZNofhNfwtCCupoVU9eJw== |       1 |      0 |     0 | 2023-03-09 |
| user1    | lrZNofhNfwtCCupoVU9eJw== |       0 |      0 |     0 | 2023-03-09 |
+----------+--------------------------+---------+--------+-------+------------+

as of(v1.0.4 -> current)

infologin table 
|
V

+----------+--------------------------+---------+---------+--------+-------+------------+
| Username | Password                 | pChange | isAdmin | status | block | Date       |
+----------+--------------------------+---------+---------+--------+-------+-----------+
| admin    | PGytuvRI/Jmicfl8uOgxqQ== |       0 |       1 |      0 |     0 | 2023-03-16 |
| user1    | lrZNofhNfwtCCupoVU9eJw== |       0 |       1 |      0 |     0 | 2023-03-16 |
| user2    | lrZNofhNfwtCCupoVU9eJw== |       1 |       0 |      0 |     0 | 2023-03-16 |
| user3    | lrZNofhNfwtCCupoVU9eJw== |       1 |       0 |      0 |     0 | 2023-03-16 |
| user4    | lrZNofhNfwtCCupoVU9eJw== |       0 |       0 |      0 |     3 | 2023-03-16 |
+----------+--------------------------+---------+---------+--------+-------+-----------+

infouser table 
|
V

+----------+-----------+----------+------------+------+----------+--------+
| username | firstName | lastName | middleName | age  | birthday | gender |
+----------+-----------+----------+------------+------+----------+--------+
| admin1   | NULL      | NULL     | NULL       | NULL | NULL     | NULL   |
| user1    | NULL      | NULL     | NULL       | NULL | NULL     | NULL   |
| user2    | NULL      | NULL     | NULL       | NULL | NULL     | NULL   |
| user3    | NULL      | NULL     | NULL       | NULL | NULL     | NULL   |
+----------+-----------+----------+------------+------+----------+--------+

audit table
|
V
+---------------------+--------+------+--------------------------+
| Time                | User   |Admin | Action                   |
+---------------------+--------+------+--------------------------+
| 2023-05-15 07:16:48 | user2  |  0   | Logged In As A User      |
| 2023-05-15 07:19:21 | user2  |  0   | User Logged Out          |
| 2023-05-15 07:31:13 | admin1 |  1   | Logged In As An Admin    |
| 2023-05-16 01:54:17 | admin1 |  1   | Blocked An Account       |
| 2023-05-16 01:54:41 | admin1 |  1   | Unblocked An Account     |
| 2023-05-16 01:56:00 | admin1 |  1   | Deleted An Account       |
+---------------------+--------+------+--------------------------+

creating table is on another sysdevTables.txt file
