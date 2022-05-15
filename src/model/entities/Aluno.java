package model.entities;

import java.util.Date;

public class Aluno {

	private Integer idAluno;
	private String nomeAluno;
	private String sexo;
	private Date dt_nasc;
	private Float nota;
	
	
	@Override
	public String toString() {
		return "Aluno [idAluno=" + idAluno + ", nomeAluno=" + nomeAluno + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc
				+ ", nota=" + nota + "]";
	}

	public Aluno() {
		
	}

	public Aluno(Integer idAluno, String nomeAluno, String sexo, Date dt_nasc, Float nota) {
		super();
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.sexo = sexo;
		this.dt_nasc = dt_nasc;
		this.nota = nota;
	}
	
	public Integer getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDt_nasc() {
		return dt_nasc;
	}
	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
	
	
	
	
	
}
