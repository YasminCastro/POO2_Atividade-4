package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.DB;
import model.entities.Curso;

public class CursoDAOImp implements CursoDAO{
	
	private Connection conexao = DB.getConexao();
	
	

	public CursoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		
		try {
			
			String sql = "INSERT INTO curso (nomecurso) VALUES (?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeCurso());
			
			int linhas = pst.executeUpdate();
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();
				
				obj.setIdcurso(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso!");
			}else {
				System.out.println("Não foi possível cadastra o curso!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Curso obj) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			
			
			String	sql = "UPDATE curso SET nomeCurso = ? WHERE Idcurso = ? ";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeCurso());
			pst.setInt(2, obj.getIdcurso());
			
			int linhas = pst.executeUpdate();
			
			
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();			
				
				System.out.println(obj.toString() + " foi atualizado com sucesso!");
			}else {
				System.out.println("Não foi possível atualizar o curso!");
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
			
			String sql = "DELETE FROM curso WHERE Idcurso = ?";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);
			
			int linhas = pst.executeUpdate();
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();
				
				System.out.println(id + " foi removido com sucesso!");
			}else {
				System.out.println("Não foi possível remover o curso!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Curso findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM curso WHERE Idcurso = ?";
			
			pst = conexao.prepareStatement(sql);	
			pst.setInt(1, id);						
			rs = pst.executeQuery();
			rs.next();
			
			Curso c = new Curso(rs.getInt(1), rs.getString(2));
					
			
			return c;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Curso> findAll() {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<Curso> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM curso";
			
			pst = conexao.prepareStatement(sql);					
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Curso c = new Curso(rs.getInt(1), rs.getString(2));
				
				lista.add(c);
			}
			
		
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return lista;
	
	
	
	

}
}
