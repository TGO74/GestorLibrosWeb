package com.gestion.gestorlibrosweb.model.data.dao;

import com.gestion.gestorlibrosweb.model.Categoria;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class CategoriaDAO {

    public static void registrarCategoriaLibro(DSLContext query, Categoria categoriaLibro){
        Table<?> tablaCategoriaLibro = table(name("CategoriaLibro"));
        Field<String> columnaCategoria = DSL.field("categoria", String.class);
        query.insertInto(tablaCategoriaLibro, columnaCategoria)
                .values(categoriaLibro.getCategoria())
                .execute();
    }

    public static void modificarCategoriaLibro(DSLContext query, String categoria, String columnaTabla, Object dato){
        query.update(DSL.table("CategoriaLibro")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("categoria").eq(categoria)).execute();
    }

    public static List<Categoria> obtenerCategoriaLibro(DSLContext query, String columnaTabla, Object dato){
        Result<?> resultados = query.select().from(DSL.table("CategoriaLibro")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaCategoriaLibros(resultados);
    }

    public static List<Categoria> obtenerCategoriaLibros(DSLContext query){
        Result<?> resultados = query.select().from(DSL.table("CategoriaLibro")).fetch();
        return obtenerListaCategoriaLibros(resultados);
    }

    public static void eliminarCategoriaLibro(DSLContext query, Categoria categoria){
        Table<?> tablaCategoriaLibro = table(name("CategoriaLibro"));
        query.delete(DSL.table("CategoriaLibro")).where(DSL.field("categoria").eq(categoria)).execute();
    }

    private static List<Categoria> obtenerListaCategoriaLibros(Result<?> resultados){
        List<Categoria> categoriaLibros = new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            int id = (int) resultados.getValue(fila, "id");
            String categoria = (String) resultados.getValue(fila, "categoria");
            categoriaLibros.add(new Categoria(id, categoria));
        }
        return categoriaLibros;
    }
}