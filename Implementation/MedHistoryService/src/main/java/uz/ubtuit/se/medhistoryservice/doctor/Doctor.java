/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uz.ubtuit.se.medhistoryservice.doctor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import uz.ubtuit.se.medhistoryservice.config.DBConnection;

/**
 *
 * @author dilshod
 */

@Path(value = "Doctor")
public class Doctor {
    public String firstName;
    public String lastName;
    public String login;
    public String password;
    public Date birthDate;
    public String address;
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getLogin() {
        return this.login;
    }
    public String getPassword() {
        return this.password;
    }
    public Date getBirthDate() {
        return this.birthDate;
    }
    public String getAddress() {
        return this.address;
    }
     
    @GET
    @Path(value = "GetAllDoctors")
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        DBConnection mysqlConnect = new DBConnection();
        String sql = "SELECT * FROM doctor";
        List<Doctor> doctors = new ArrayList<>();
        try {
            ResultSet resultSet = mysqlConnect.connect().createStatement().executeQuery(sql);
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Doctor doc = new Doctor();
                doc.setFirstName(firstName);
                doc.setLastName(lastName);
                doc.setLogin(login);
                doc.setPassword(password);
                doctors.add(doc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        return doctors;
    }
}