package com.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sistemacontatos.dao.ContatoDAO;
import com.sistemacontatos.model.Contato;
import com.sun.media.sound.RealTimeSequencerProvider;

/**
 * Servlet implementation class Servlet
 */
// @WebServlet(name="myservlet", urlPatterns={"servlet","servlets"}) problema
@WebServlet("/adicionaContato")
public class AdicionaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdicionaContato() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.sendRedirect("bem-vindo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");

		String dataTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;
		try {
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(data);
		} catch (ParseException e) {
			out.println("Erro na conversão de data!");
			return;
		}

		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);

		ContatoDAO cDAO = new ContatoDAO();
		cDAO.adicionaContato(contato);
		
		out.println("<html><head><title>Sucesso!</title></head><body>Contato '" + contato.getNome()
				+ "' adicionado com sucesso! </body>");
		
		RequestDispatcher rd = request.getRequestDispatcher("/adiciona-contato.html");
		rd.forward(request, response);
	}

}
