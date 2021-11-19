/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.infotrabajogrupo12.jaas;

import com.mycompany.infotrabajogrupo12.client.EmpresaBackingBean;
import com.mycompany.infotrabajogrupo12.entities.Users;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Juand
 */
@Named 
@SessionScoped
public class RegisterView implements Serializable {
    
    private String nombre;
    private String telefono;
    private String email;
    private String tarjeta;
    private String password;
    private String confirmPassword;
    private Date fecha = getMinAge();
    
    @Inject
    private UserEJB userEJB;
    
     @Inject
    EmpresaBackingBean bean;
    
    
    
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" :uiInputConfirmPassword.getLocalValue().toString();
        
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Las contraseñas no coinciden");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }
        
        UIInput uiInputEmail = (UIInput) components.findComponent("email");
        String email = uiInputEmail.getLocalValue() == null ? "" : uiInputEmail.getLocalValue().toString();
        if (userEJB.findByEmail(email) != null) {
            FacesMessage msg = new FacesMessage("Ya existe un usuario con ese email");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }
    }
    

    
    public Date getMinAge() {
	Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR, -16);
        return currentDate.getTime();
	}
    
    public Date getMaxAge() {
	Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR, -65);
        return currentDate.getTime();
	}
    
    public String register() {

        Users user = new Users(email,nombre,password,telefono,tarjeta,fecha);
        String tipo;

        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        if (request.isUserInRole("admin")) {
            tipo = "empresas";
        } else {
            tipo = "users";
        }
        userEJB.createUser(user,tipo);
        System.out.println("Nuevo usuario creado con e-mail: " + email + " y nombre:" + nombre);
        email="";
        nombre="";
        telefono="";
        tarjeta="";
        fecha=null;
        
        bean.init();
        bean.getEmpresas();
        return "regok";
    }

    
    public void validateEmail(ComponentSystemEvent event) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;
        pattern = Pattern.compile(EMAIL_PATTERN);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputPassword = (UIInput) components.findComponent("email");
        String email = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();

        if (!pattern.matcher(email).matches()) {
            FacesMessage msg = new FacesMessage("El email no es válido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputPassword.getClientId(), msg);
            facesContext.renderResponse();
        }

    }

    public void validateDni(ComponentSystemEvent event) {
        String DNI_PATTERN = "^[0-9]{8}[a-z]$";
        Pattern pattern;
        pattern = Pattern.compile(DNI_PATTERN);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiInputDni = (UIInput) components.findComponent("dni");
        String dni = uiInputDni.getLocalValue() == null ? "" : uiInputDni.getLocalValue().toString();

        if (!pattern.matcher(dni).matches()) {
            FacesMessage msg = new FacesMessage("El dni no es válido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(uiInputDni.getClientId(), msg);
            facesContext.renderResponse();
        }

    }
    

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }
   
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
