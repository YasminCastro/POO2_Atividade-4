package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.db.DB;
import model.entities.Aluno;

public class AlunoDAOImp implements AlunoDAO {
	
	private Connection conexao = DB.getConexao();

	public AlunoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Aluno obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "INSERT INTO aluno (nome, sexo, dt_nasc, nota) VALUES (?, ?, ?, ?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeAluno());
			pst.setString(2, obj.getSexo());
			
			Date dataSql = new Date(obj.getDt_nasc().getTime());			
			pst.setDate(3, dataSql);
			
			pst.setFloat(4, obj.getNota());
			
			int linhas = pst.executeUpdate();
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();
				
				obj.setIdAluno(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso!");
			}else {
				System.out.println("Não foi possível cadastra o aluno!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Aluno obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			
			
			String	sql = "UPDATE aluno SET nome = ? , sexo = ? ,  dt_nasc = ? ,  nota = ? WHERE IdAluno = ? ";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeAluno());
			pst.setString(2, obj.getSexo());
			
			Date dataSql = new Date(obj.getDt_nasc().getTime());			
			pst.setDate(3, dataSql);
			
			pst.setFloat(4, obj.getNota());
			pst.setInt(5, obj.getIdAluno());
			
			int linhas = pst.executeUpdate();
			
			
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();			
				
				System.out.println(obj.toString() + " foi atualizado com sucesso!");
			}else {
				System.out.println("Não foi possível atualizar o aluno!");
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
			
			String sql = "DELETE FROM aluno WHERE IdAluno = ?";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);
			
			int linhas = pst.executeUpdate();
			
			if(linhas > 0) {
				rs  = pst.getGeneratedKeys();
				rs.next();
				
				System.out.println(id + " foi removido com sucesso!");
			}else {
				System.out.println("Não foi possível remover o aluno!");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Aluno findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM aluno WHERE IdAluno = ?";
			
			pst = conexao.prepareStatement(sql);	
			pst.setInt(1, id);						
			rs = pst.executeQuery();
			rs.next();
			
			Aluno a = new Aluno(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getFloat(5));
					
			
			return a;
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Aluno> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		List<Aluno> lista = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM aluno";
			
			pst = conexao.prepareStatement(sql);					
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Aluno a = new Aluno(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getFloat(5));
				
				lista.add(a);
			}
			
		
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return lista;
	}

	

}
