package com.mycompany.saam.teste.pratico.dao;

import com.mycompany.saam.teste.pratico.model.Funcionario;
import com.mycompany.saam.teste.pratico.model.Usuario;
import com.mycompany.saam.teste.pratico.util.PostgresDBConnect;
import com.mycompany.saam.teste.pratico.util.Secao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioDaoTest {
    private FuncionarioDao funcionarioDao;

    private UsuarioDao usuarioDao;
    private Usuario usuario;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        // Estabelecer conexão com o banco de dados de teste
        connection = PostgresDBConnect.connect();
        funcionarioDao = new FuncionarioDao();
        usuarioDao = new UsuarioDao();
        usuario = new Usuario("Jonathas", "Jonathas@example.com", "senha1");
        usuarioDao.insert(usuario);
        Secao.getInstancia().setUsuarioLogado(usuario);
        limparTabelaFuncionarios();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        limparTabelaFuncionarios();
        usuarioDao.delete(usuario.getId());
        if (connection != null) {
            connection.close();
        }
    }

    private void limparTabelaFuncionarios() throws SQLException {
        String sql = "DELETE FROM funcionario";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

    @Test
    void testInsert() {
        String userUuid = usuario.getId();
        Funcionario funcionario = new Funcionario("Jonathas", Date.valueOf("2024-11-05"), 2500.0, true, userUuid);

        Boolean result = funcionarioDao.insert(funcionario);
        assertTrue(result);

        // Verificar se o funcionario foi inserido corretamente
        List<Funcionario> funcionarios = funcionarioDao.select();
        assertEquals(1, funcionarios.size());
        Funcionario funcionarioInserido = funcionarios.get(0);
        assertEquals(funcionarioInserido, funcionario);
        assertEquals(userUuid, funcionarioInserido.getIdUsuario());
    }

    @Test
    void testSelect() {
        String userUuid = usuario.getId();
        Funcionario funcionario1 = new Funcionario("Jonathas", Date.valueOf("2024-11-05"), 2500.0, true, userUuid);
        Funcionario funcionario2 = new Funcionario("Maria", Date.valueOf("2024-11-12"), 2600.0, true, userUuid);

        funcionarioDao.insert(funcionario1);
        funcionarioDao.insert(funcionario2);

        List<Funcionario> funcionarios = funcionarioDao.select();
        assertEquals(2, funcionarios.size());
    }

    @Test
    void testUpdate() {
        String userUuid = usuario.getId();
        Funcionario funcionario = new Funcionario("Jonathas", Date.valueOf("2024-11-05"), 2500.0, true, userUuid);
        funcionarioDao.insert(funcionario);

        // Atualizar o usuário
        Funcionario funcionarioAtualizado = new Funcionario("Jonathas Atualizado", Date.valueOf("2024-11-04"), 2600.0, true, userUuid);

        Boolean result = funcionarioDao.update(funcionarioAtualizado, funcionario.getId());
        assertTrue(result);

        Funcionario funcionarioBuscado = funcionarioDao.selectById(funcionario.getId());
        assertEquals("Jonathas Atualizado", funcionarioBuscado.getNome());
        assertEquals(Date.valueOf("2024-11-04"),funcionarioBuscado.getDataAdmissao());
        assertEquals(2600.0, funcionarioBuscado.getValorSalario());
        assertTrue(funcionarioBuscado.getStatus());
        assertEquals(userUuid, funcionarioBuscado.getIdUsuario());
    }

    @Test
    void testDelete() {
        String userUuid = usuario.getId();
        Funcionario funcionario = new Funcionario("Jonathas", Date.valueOf("2024-11-05"), 2500.0, true, userUuid);
        funcionarioDao.insert(funcionario);

        Boolean result = funcionarioDao.delete(funcionario.getId());
        assertTrue(result);

        List<Funcionario> funcionarios = funcionarioDao.select();
        assertTrue(funcionarios.isEmpty());
    }

    @Test
    public void testSelectById() {
        String userUuid = usuario.getId();
        Funcionario funcionario = new Funcionario("Jonathas", Date.valueOf("2024-11-05"), 2500.0, true, userUuid);
        funcionarioDao.insert(funcionario);

        Funcionario funcionarioBuscado = funcionarioDao.selectById(funcionario.getId());
        assertNotNull(funcionarioBuscado);
        assertEquals(funcionario,funcionarioBuscado);
    }
}
