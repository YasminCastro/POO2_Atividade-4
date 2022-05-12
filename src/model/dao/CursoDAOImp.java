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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curso findById(Integer id) {
		// TODO Auto-generated method stub
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
