/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
//ici doludrulacaq

    private Country getCountry(ResultSet rs) throws Exception {

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

        Country country = new Country(id, name, nationality);
        return country;

    }

    @Override
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * from country");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country cntr = getCountry(rs);
                list.add(cntr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Country getById(int Userid) {
        Country el = null;
        Connection conn;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("select * from country where id =?");
            stmt.setInt(1, Userid);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                el = getCountry(rs);
            }

        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateCountry(Country u) {
        Connection conn;
        boolean b = true;

        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("update country set name=?,nationality=? where id= ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeCountry(int id) {
        Connection conn;
        try{
        conn = connect();
        
        PreparedStatement stmt = conn.prepareStatement("delete from country where id=?;");
        stmt.setInt(1, id);
        
        return stmt.execute();    
        }catch(Exception ex){
        return false;
        }

    }

}
