package com.gestion.gestorlibrosweb.controller;

import com.gestion.gestorlibrosweb.model.Categoria;
import com.gestion.gestorlibrosweb.model.Libro;
import com.gestion.gestorlibrosweb.model.TipoLibro;
import com.gestion.gestorlibrosweb.model.data.DBGenerator;
import com.gestion.gestorlibrosweb.model.data.dao.CategoriaDAO;
import com.gestion.gestorlibrosweb.model.data.dao.LibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "registroLibroServlet", value = "/registroLibro")
public class RegistroLibroServlet extends HttpServlet {
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
        try {
            DSLContext query = DBGenerator.conectarBD("librosbd");
            List<Categoria> categorias = CategoriaDAO.obtenerCategoriaLibros(query);

            // Depuración: imprimir las categorías en la consola del servidor
            System.out.println("Categorías obtenidas:");
            for (Categoria categoria : categorias) {
                System.out.println("ID: " + categoria.getIdCategoria() + ", Categoria: " + categoria.getCategoria());
            }

            req.setAttribute("categorias", categorias);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroLibro.jsp");
        respuesta.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if(req.getParameter("ISBN").length()!=0 && req.getParameter("nombre").length()!=0 && req.getParameter("editorial").length()!=0 && req.getParameter("anio").length()!=0 && req.getParameter("categoria").length()!=0 && req.getParameter("tipoLibro").length()!=0) {
            String ISBN = req.getParameter("ISBN");
            String nombre = req.getParameter("nombre");
            String editorial = req.getParameter("editorial");
            int anio = Integer.parseInt(req.getParameter("anio"));
            int categoriaId = Integer.parseInt(req.getParameter("categoria"));
            TipoLibro tipoLibro = TipoLibro.valueOf(req.getParameter("tipoLibro").toUpperCase());

            Categoria categoriaLibro = new Categoria(categoriaId, "");
            Libro libro = new Libro(ISBN, nombre, editorial, categoriaLibro, anio, tipoLibro);
            try {
                DSLContext query = DBGenerator.conectarBD("librosbd");
                LibroDAO.agregarLibro(query, libro);
                req.setAttribute("libro", libro);
                respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req, resp);
    }
}