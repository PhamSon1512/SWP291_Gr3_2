/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import jakarta.servlet.http.Part;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

/**
 *
 * @author Khuong Hung
 */
public class AccountDAO extends DBContext {

    public Account getAccountByUP(String email, String password) {
        try {
            String sql = "select * from account where email = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account s = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getString(8));
                return s;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AccountDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccountByEmail(String email) {
        Account account = null;
        try {
            String sql = "SELECT * FROM account WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                account = new Account(
                        rs.getInt("acc_ID"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getBoolean("roleID"),
                        rs.getInt("acc_status"),
                        rs.getString("avatar_url")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
    public ArrayList<Account> listAcc() {
        ArrayList acc = new ArrayList();

        try {
            String sql = "select * from account ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account s = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getString(8));
                acc.add(s);

            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*public Account checkAcc(String email, String username) throws SQLException {
        String sql = "select * from users where email=? or username=?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(8);
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    base64Image = "default";
                }
                Role r = new Role(rs.getInt(2));
                return new Account(rs.getString(1), r, rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getInt(6), rs.getString(7), base64Image, rs.getBoolean(9));
            }
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }*/

     public void insertAccount(String name, String email, String phone_number, String password, int roleID, int acc_status,String avatar_url) {
        int role = 0;
        try {
            String sql = "INSERT INTO account"
                    + " VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone_number);
            statement.setString(4, password);
            statement.setInt(5, roleID);
            statement.setInt(6, acc_status);
            statement.setString(7, avatar_url);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePassword(String email, String password) {
        try {
            String sql = "UPDATE account\n"
                    + "SET password = '" + password
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeInformations(String email, String name, String phone) {
        try {
            String sql = "UPDATE account\n"
                    + "SET name = N'" + name
                    + "', phone = '" + phone
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePasswordByEmail(String email, String newPassword) {
        try {
            String sql = "UPDATE account\n"
                    + "SET password = '" + newPassword
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public void UpdateImage(String username, Part img) throws SQLException {
        String sql = "UPDATE `doctris_system`.`users` SET `img` = ? WHERE (`username` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            InputStream image = img.getInputStream();
            ps.setBlob(1, image);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Account> getFilterByRole(String role_id) throws SQLException, IOException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT u.username,u.name,u.gender,u.email,u.phone,r.name,u.status,u.img "
                + "FROM doctris_system.users u "
                + "inner join doctris_system.role r "
                + "on u.role_id = r.id where u.role_id = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, role_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(8);
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    base64Image = "default";
                }
                Role r = new Role(rs.getString(6));
                list.add(new Account(rs.getString(1), r, rs.getString(2), rs.getBoolean(3), rs.getInt(5), rs.getString(4), base64Image, rs.getBoolean(7)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Account> getFilterByStatus(String status) throws SQLException, IOException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT u.username,u.name,u.gender,u.email,u.phone,r.name,u.status,u.img "
                + "FROM doctris_system.users u "
                + "inner join doctris_system.role r "
                + "on u.role_id = r.id where u.status = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(8);
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    base64Image = "default";
                }
                Role r = new Role(rs.getString(6));
                list.add(new Account(rs.getString(1), r, rs.getString(2), rs.getBoolean(3), rs.getInt(5), rs.getString(4), base64Image, rs.getBoolean(7)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Account> getFilter(String status, String role_id) throws SQLException, IOException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT u.username,u.name,u.gender,u.email,u.phone,r.name,u.status,u.img "
                + "FROM doctris_system.users u "
                + "inner join doctris_system.role r "
                + "on u.role_id = r.id where u.role_id = ? AND u.status = ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, role_id);
            ps.setString(2, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(8);
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    base64Image = "default";
                }
                Role r = new Role(rs.getString(6));
                list.add(new Account(rs.getString(1), r, rs.getString(2), rs.getBoolean(3), rs.getInt(5), rs.getString(4), base64Image, rs.getBoolean(7)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<Account> SearchALl(String text) throws SQLException, IOException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT DISTINCT u.username,u.name,u.gender,u.email,u.phone,r.name,u.status,u.img "
                + "FROM doctris_system.users u "
                + "inner join doctris_system.role r "
                + "on u.role_id = r.id where u.name LIKE ? OR u.email LIKE ? OR u.phone LIKE ? OR u.username LIKE ?";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            ps.setString(3, "%" + text + "%");
            ps.setString(4, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String base64Image = null;
                Blob blob = rs.getBlob(8);
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    byte[] imageBytes = outputStream.toByteArray();
                    base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                } else {
                    base64Image = "default";
                }
                Role r = new Role(rs.getString(6));
                list.add(new Account(rs.getString(1), r, rs.getString(2), rs.getBoolean(3), rs.getInt(5), rs.getString(4), base64Image, rs.getBoolean(7)));
            }
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public void UpdateRoleStatus(String username, int role_id, boolean status) throws SQLException {
        String sql = "UPDATE `doctris_system`.`users` SET `role_id` = ?, `status` = ? WHERE (`username` = ?)";
        try {
            connection = dbc.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(3, username);
            ps.setInt(1, role_id);
            ps.setBoolean(2, status);
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    public List<Account> getListByPage(List<Account> list,
            int start, int end) {
        ArrayList<Account> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
*/
}


