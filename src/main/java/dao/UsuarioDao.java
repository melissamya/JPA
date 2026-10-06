/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Usuario;
import java.util.List;

public interface UsuarioDao {
    void insertar(Usuario u);
    List<Usuario> listar();
    Usuario buscarPorId(int id);
    void actualizar(Usuario u);
    void eliminar(int id);
}
