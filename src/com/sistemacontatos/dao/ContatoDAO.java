package com.sistemacontatos.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.sistemacontatos.connectiondb.ConnectionDatabase;
import com.sistemacontatos.model.Contato;

public class ContatoDAO {

	private ConnectionDatabase cdb = ConnectionDatabase.getInstance();
	private PreparedStatement stm;
	private String sql;

	public void adicionaContato(Contato c) {

		try {
			cdb.openConnection();
			sql = "INSERT INTO contatos (nome, email, endereco, dataNascimento) values (?,?,?,?)";

			stm = cdb.preparandoStatement(sql);
			stm.setString(1, c.getNome());
			stm.setString(2, c.getEmail());
			stm.setString(3, c.getEndereco());
			stm.setDate(4, new Date(c.getDataNascimento().getTimeInMillis()));
			cdb.executaUpdate(sql, stm);
			System.out.println("Contato " + c.getNome() + " inserido com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cdb.closeConnection();
		}
	}

	public ArrayList<Contato> getListarContatos() {
		ArrayList<Contato> listaContatos = new ArrayList<>();
		try {
			cdb.openConnection();
			sql = "SELECT * FROM contatos";
			stm = cdb.preparandoStatement(sql);
			ResultSet rs = cdb.executaQuery(sql, stm);
			while (rs.next()) {
				Contato c = new Contato();
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
				c.setEndereco(rs.getString("endereco"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				c.setDataNascimento(data);
				listaContatos.add(c);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cdb.closeConnection();
		}
		return listaContatos;
	}

	public Contato buscarContato(String nome, String email) {
		Contato contato = null;
		try {
			cdb.openConnection();
			sql = "SELECT * FROM contatos";
			stm = cdb.preparandoStatement(sql);
			ResultSet rs = cdb.executaQuery(sql, stm);

			while (rs.next()) {
				if (rs.getString("nome").equals(nome) && rs.getString("email").equals(email)) {
					contato = new Contato();
					contato.setNome(rs.getString("nome"));
					contato.setEmail(rs.getString("email"));
					contato.setEndereco(rs.getString("endereco"));

					Calendar data = Calendar.getInstance();
					data.setTime(rs.getDate("dataNascimento"));
					contato.setDataNascimento(data);
				}
			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			cdb.closeConnection();
		}
		return contato;
	}

	public void alterarDados(Contato c) {
		try {
			cdb.openConnection();
			sql = "UPDATE contatos SET nome=?, email=?, endereco=?, dataNascimento=? WHERE id=?";
			stm = cdb.preparandoStatement(sql);
			stm.setString(1, c.getNome());
			stm.setString(2, c.getEmail());
			stm.setString(3, c.getEndereco());
			stm.setDate(4, new Date(Calendar.getInstance().getTimeInMillis()));
			stm.setLong(5, c.getId());
			cdb.executaUpdate(sql, stm);
			System.out.println("Contato " + c.getNome() + " atualizado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cdb.closeConnection();
		}
	}

	public void removerContato(Contato c) {
		try {
			cdb.openConnection();
			sql = "DELETE FROM contatos WHERE id=?";
			stm = cdb.preparandoStatement(sql);
			stm.setLong(1, c.getId());
			cdb.executaUpdate(sql, stm);
			System.out.println("Contato " + c.getNome() + " deletado com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cdb.closeConnection();
		}
	}

}
