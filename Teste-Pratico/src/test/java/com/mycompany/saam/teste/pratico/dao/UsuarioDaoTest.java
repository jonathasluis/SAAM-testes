package com.mycompany.saam.teste.pratico.dao;

import com.mycompany.saam.teste.pratico.model.Usuario;
import com.mycompany.saam.teste.pratico.util.PostgresDBConnect;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao usuarioDao;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        // Estabelecer conexão com o banco de dados de teste
        connection = PostgresDBConnect.connect();
        usuarioDao = new UsuarioDao();
        limparTabelaUsuarios();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        limparTabelaUsuarios();
        if (connection != null) {
            connection.close();
        }
    }

    private void limparTabelaUsuarios() throws SQLException {
        String sql = "DELETE FROM usuario";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

    @Test
    void testInsert() {
        Usuario usuario = new Usuario();
        usuario.setNome("Jonathas");
        usuario.setEmail("Jonathas@example.com");
        usuario.setSenha("senhaCriptografada");

        Boolean result = usuarioDao.insert(usuario);
        assertTrue(result);

        // Verificar se o usuário foi inserido corretamente
        List<Usuario> usuarios = usuarioDao.select();
        assertEquals(1, usuarios.size());
        Usuario usuarioInserido = usuarios.get(0);
        assertEquals("Jonathas", usuarioInserido.getNome());
        assertEquals("Jonathas@example.com", usuarioInserido.getEmail());
        assertEquals("senhaCriptografada", usuarioInserido.getSenha());
    }

    @Test
    void testSelect() {
        Usuario usuario1 = new Usuario("Jonathas", "Jonathas@example.com", "senha1");
        Usuario usuario2 = new Usuario("Maria", "maria@example.com", "senha2");

        usuarioDao.insert(usuario1);
        usuarioDao.insert(usuario2);

        List<Usuario> usuarios = usuarioDao.select();
        assertEquals(2, usuarios.size());
    }

    @Test
    void testUpdate() {
        Usuario usuario = new Usuario();
        usuario.setNome("Jonathas");
        usuario.setEmail("Jonathas@example.com");
        usuario.setSenha("senhaOriginal");
        usuarioDao.insert(usuario);

        // Atualizar o usuário
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Jonathas Atualizado");
        usuarioAtualizado.setEmail("Jonathas_atualizado@example.com");
        usuarioAtualizado.setSenha("novaSenha");

        Boolean result = usuarioDao.update(usuarioAtualizado, usuario.getId());
        assertTrue(result);

        Usuario usuarioBuscado = usuarioDao.selectById(usuario.getId());
        assertEquals("Jonathas Atualizado", usuarioBuscado.getNome());
        assertEquals("Jonathas_atualizado@example.com", usuarioBuscado.getEmail());
    }

    @Test
    void testDelete() {
        Usuario usuario = new Usuario();
        usuario.setNome("Jonathas");
        usuario.setEmail("Jonathas@example.com");
        usuario.setSenha("senhaCriptografada");
        usuarioDao.insert(usuario);

        Boolean result = usuarioDao.delete(usuario.getId());
        assertTrue(result);

        List<Usuario> usuarios = usuarioDao.select();
        assertTrue(usuarios.isEmpty());
    }

    @Test
    void testSelectById() {
        Usuario usuario = new Usuario();
        usuario.setNome("Jonathas");
        usuario.setEmail("Jonathas@example.com");
        usuario.setSenha("senhaCriptografada");
        usuarioDao.insert(usuario);

        Usuario usuarioBuscado = usuarioDao.selectById(usuario.getId());
        assertNotNull(usuarioBuscado);
        assertEquals("Jonathas", usuarioBuscado.getNome());
    }
}
