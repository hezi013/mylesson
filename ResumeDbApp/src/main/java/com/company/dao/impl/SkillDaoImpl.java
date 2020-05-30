/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.Skill;
import com.company.entity.User;
import com.mysql.cj.x.protobuf.MysqlxSql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {
    
    @Override
    public List<Skill> getAll() {
        List<Skill> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();
            
            Statement stmt = conn.createStatement();
            stmt.execute("select * from skill");
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            
        }
        return list;
    }
    
    @Override
    public Skill getById(int Userid) {
        Skill usr = null;
        Connection conn;
        try {
            conn = connect();
            
            PreparedStatement stmt = conn.prepareStatement("select * from skill where id=?");
            stmt.setInt(1, Userid);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                usr = new Skill(id, name);
                
            }
        } catch (Exception ex) {
        }
        return usr;
    }
    
    @Override
    public boolean updateSkill(Skill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("update skill set name=? where id= ?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            b = stmt.execute();
            
        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean insertSkill(Skill skl) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("insert skill (name) values (?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getName());
            b = stmt.execute();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                skl.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }
    
    @Override
    public boolean removeSkill(int id) {
        Connection conn;
        try {
            conn = connect();
            
            PreparedStatement stmt = conn.prepareStatement("delete from skill where id=?;");
            stmt.setInt(1, id);
            return stmt.execute();
            
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }
    
    @Override
    public List<Skill> getByName(String sname) {
        List<Skill> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();
            
            PreparedStatement stmt = conn.prepareStatement("select * from skill where name like ?;");            
            stmt.setString(1, sname);
            stmt.execute();
            
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception ex) {
            System.err.println("Houston, we have a problem");
        }
        return list;
    }
    
}
