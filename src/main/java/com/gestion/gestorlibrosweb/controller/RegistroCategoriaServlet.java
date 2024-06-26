package com.gestion.gestorlibrosweb.controller;

import com.gestion.gestorlibrosweb.model.Categoria;
import com.gestion.gestorlibrosweb.model.data.DBGenerator;
import com.gestion.gestorlibrosweb.model.data.dao.CategoriaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;

import java.io.IOException;

@WebServlet(name = "registroCategoriaServlet", value = "/registroCategoria")
public class RegistroCategoriaServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("librosbd");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error initializing database", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroCategoria.jsp");
        respuesta.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoria = req.getParameter("categoria");

        if (categoria != null && !categoria.trim().isEmpty()) {
            try {
                DSLContext query = DBGenerator.conectarBD("librosbd");
                Categoria categoriaLibro = new Categoria(0, categoria);
                CategoriaDAO.registrarCategoriaLibro(query, categoriaLibro);
                req.setAttribute("categoriaLibro", categoriaLibro);
                req.getRequestDispatcher("/registroExitoso.jsp").forward(req, resp);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                req.setAttribute("error", "Error registrando la categoría.");
                req.getRequestDispatcher("/registroErroneo.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "El campo categoría no puede estar vacío.");
            req.getRequestDispatcher("/registroErroneo.jsp").forward(req, resp);
        }
    }
}