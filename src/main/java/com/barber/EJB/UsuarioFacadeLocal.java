/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barber.EJB;

import com.barber.model.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author juan
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    //Registrar Usuario esta en UsuarioSesion con findAll();
    
    Usuario validarUsuario(String correoIn, String claveIn);

    Usuario recuperarClave(String correoIn);
    
    public List<Usuario> leerTodos();
    
    public Usuario encontrarUsuarioCorreo(String correo);

    public Usuario listarBarbero();

    public Usuario validarSiExiste(String correoIn);

    public boolean crearUsuario(Usuario usuIn);
    
}
