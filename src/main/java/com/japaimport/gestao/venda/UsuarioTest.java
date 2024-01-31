/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.japaimport.gestao.venda;

import com.japaimport.gestao.venda.modelo.dao.UsuarioDao;
import com.japaimport.gestao.venda.modelo.dominio.Perfil;
import com.japaimport.gestao.venda.modelo.dominio.Usuario;
import java.time.LocalDateTime;

/**
 *
 * @author IGORHAWKS
 */
public class UsuarioTest {
    public static void main(String[] args) {
        Usuario usuario = new Usuario(0L, "Maria", "MariaBonita", "456789", Perfil.ADMIN, null, null);
        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuarioDao.salvar(usuario);
        System.out.println(mensagem);
        
    }
}
