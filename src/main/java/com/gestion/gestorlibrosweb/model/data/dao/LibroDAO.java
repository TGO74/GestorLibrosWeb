package com.gestion.gestorlibrosweb.model.data.dao;


import com.gestion.gestorlibrosweb.model.Categoria;
import com.gestion.gestorlibrosweb.model.Libro;
import com.gestion.gestorlibrosweb.model.TipoLibro;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.impl.DSL;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class LibroDAO {
    public static void agregarLibro(DSLContext query, Libro libro) {
        Table<?> tablaLibro = table(name("Libro"));
        Field<?>[] columnas = tablaLibro.fields("ISBN", "nombre", "editorial", "categoria", "anio", "tipoLibro");
        query.insertInto(tablaLibro, columnas)
                .values(libro.getISBN(), libro.getTitulo(), libro.getEditorial(), libro.getCategoria().getIdCategoria(), libro.getAno(), libro.getTipoLibro().toString())
                .execute();
    }

}