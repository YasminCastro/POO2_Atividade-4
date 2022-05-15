package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.DB;
import model.entities.Disciplina;

public class DisciplinaDAOImp implements DisciplinaDAO{

	private Connection conexao = DB.getConexao();
	
	public DisciplinaDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Disciplina obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "INSERT INTO disciplina (nomedisciplina, cargahoraria) VALUES (?, ?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeDisciplina());
			pst.setInt(2, obj.getCargaHoraria());
			
					
			int linhas = pst.executeUpdate();
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();
				
				obj.setIdDisciplina(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso!");
			}else {
				System.out.println("Não foi possível cadastra o disciplina!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Disciplina obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			
			
			String	sql = "UPDATE disciplina SET nomedisciplina = ? , cargahoraria = ? WHERE iddisciplina = ? ";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeDisciplina());
			pst.setInt(2, obj.getCargaHoraria());
			
			pst.setInt(3, obj.getIdDisciplina());
			
			int linhas = pst.executeUpdate();
			
			
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();			
				
				System.out.println(obj.toString() + " foi atualizado com sucesso!");
			}else {
				System.out.println("Não foi possível atualizar a disciplina!");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			String sql = "DELETE FROM disciplina WHERE iddisciplina = ?";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);
			
			int linhas = pst.executeUpdate();
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();
				
				System.out.println(id + " foi removido com sucesso!");
			}else {
				System.out.println("Não foi possível remover o disciplina!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Disciplina findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM disciplina WHERE iddisciplina = ?";
			
			pst = conexao.prepareStatement(sql);	
			pst.setInt(1, id);						
			rs = pst.executeQuery();
			rs.next();
			
			Disciplina d = new Disciplina(rs.getInt(1), rs.getString(2), rs.getInt(3));
					
			
			return d;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Disciplina> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<Disciplina> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM disciplina";
			
			pst = conexao.prepareStatement(sql);					
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Disciplina d = new Disciplina(rs.getInt(1), rs.getString(2), rs.getInt(3));
				
				lista.add(d);
			}
			
		
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return lista;
	}
	}


