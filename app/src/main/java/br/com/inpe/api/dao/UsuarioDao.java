package br.com.inpe.api.dao;

import java.util.List;

import br.com.inpe.api.model.Usuario;

/**
 * Created by junio on 04/11/15.
 */
public interface UsuarioDao {

    public Usuario save(Usuario usuario);

    public Usuario upgrade(Usuario usuario);

    public Boolean delete(Usuario usuario);

    public List<Usuario> listAll();

    public Usuario findById(Long id);

    public Usuario findByIdLoginAndPassword(String login, String password);


}
