package controller;

import java.sql.Connection;

import model.db.DB;



public class Program {

	public static void main(String[] args) {
		Connection con = DB.getConexao();

	}

}
