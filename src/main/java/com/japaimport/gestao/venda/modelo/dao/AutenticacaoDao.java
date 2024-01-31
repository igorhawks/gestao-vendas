/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.japaimport.gestao.venda.modelo.dao;

import com.japaimport.gestao.venda.modelo.dominio.Perfil;
import com.japaimport.gestao.venda.modelo.dominio.Usuario;
import com.japaimport.gestao.venda.modelo.exception.NegocioException;
import com.japaimport.gestao.venda.view.modelo.LoginDTO;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author IGORHAWKS
 */
public class AutenticacaoDao {
    
    private final UsuarioDao usuarioDao;
    
    public AutenticacaoDao()
    {
        this.usuarioDao = new UsuarioDao();
        
    }
    
    public boolean temPremissao(Usuario usuario)
    {
        try{
            permissao(usuario);
            return true;
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Usuario sem permissão",0);
            return false;
        }
    }
    
    private void permissao(Usuario usuario)
    {
        if(!Perfil.ADMIN.equals(usuario.getPrefil()))
        {
           throw new NegocioException("Sem permissao para realizar essa acao");
        }
    }
    
    public Usuario login(LoginDTO login)
    {
        Usuario usuario = usuarioDao.buscarUsuarioPeloUsuario(login.getUsuario());
        if(usuario == null || !usuario.isEstado())
        {
            return null;
        }
        
        if(usuario.isEstado() && validarSenha(usuario.getSenha(),login.getSenha()))
        {
          return usuario;
        }
        return null;
    }

    /*private boolean validarSenha(String senhaUsuario, String senhaLogin) {
        return senhaUsuario.equals(senhaLogin);
    }*/
    
    private boolean validarSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senhaLogin, senhaUsuario);
               
    }
    
}
