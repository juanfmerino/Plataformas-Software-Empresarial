/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.jaas;

import com.mycompany.infotrabajogrupo12.entities.Users;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juand
 */
@Named 
@SessionScoped
public class LoginView implements Serializable{
    
    private String email;
    private String password;
    
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            request.login(email, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login incorrecto!", null));
            return "login";
        }
        
        this.user = userEJB.findByEmail(request.getUserPrincipal().getName());
        if (request.isUserInRole("admin")) {
            return "/admin/privatepage?faces-redirect=true";
        } else if (request.isUserInRole("empresas")) {
            return "/empresas/privatepage?faces-redirect=true";
        } else if (request.isUserInRole("users")) {
            return "/users/privatepage?faces-redirect=true";
        } else {
            return "login";
        }
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            System.out.println("Fallo durante el proceso de logout!");
        }
        return "/index?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserEJB getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(UserEJB userEJB) {
        this.userEJB = userEJB;
    }
    private Users user;
    
    @Inject
    private UserEJB userEJB;
    
    public Users getAuthenticatedUser() {
        return user;
    }
}
