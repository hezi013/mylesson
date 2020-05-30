package com.company.main;

import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.User;

public class Main {

    public static void main(String[] args) throws Exception {

        UserDaoInter empDao = Context.instanceUserDao();
        User u = new User(6, "Sarkhan2", "Rasullu", "5555555", null, null, null, null, null);
       
      
        
        
        System.out.println(empDao.updateUser(u));
    }
}
