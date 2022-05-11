package model.entities;

public class Curso {
	private Integer idcurso;
	private String nomeCurso;
	
	public Curso(Integer idcurso, String nomeCurso) {
		
		this.idcurso = idcurso;
		this.nomeCurso = nomeCurso;
	}

	public Curso() {
		
	}
	

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	
	
	
}
