/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;

/**
 *
 * @author limbe
 */
public class GestorTorneo {

    //Métopdo auxiliar para central para ejecutar cualquier sentencia DDL (CREATE,ALTER,DROP)
      public void ejecutar(String sql) throws SQLException {

        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar();
            PreparedStatement ps = con.prepareStatement(sql);) {
            ps.executeUpdate();// Ejecuta la sentencia DDL en MySQL
        }
    }
    
    public void crearBaseDatos() throws SQLException {
        //1 .- Creamso la basse de dats en mySQL solo sin no existe

        ejecutar("CREATE DATABASE IF NOT EXISTS torneo");

        //2.- Abrimos la conexxion y le aplicamos setCatalog
        ConexionBBDD bd = new ConexionBBDD();
        try (Connection con = bd.conectar();) {
            con.setCatalog("torneo"); //con esto comenzamos a trabjar con torneo
        }
    }

    public void crearTablaEquipos() throws SQLException {
        //Definimos la sentencia Sql
        String sql = "CREATE TABLE IF NOT EXISTS equipos( "
                + "id_equipo INT PRIMARY KEY, "
                + "nombre VARCHAR(40) UNIQUE NOT NULL, "
                + "ciudad VARCHAR(40) NOT NULL DEFAULT 'Salamanca', "
                + "fecha_creacion DATE "
                + " )";
        //LE pasamos la setencia a nuestro metodo auxiliar SOLO LLAMAMOS  AEJECUTAR NO SET CATALOG
        ejecutar(sql);
    }

    public void crearTablaJugadores() throws SQLException {
        //Definimos la sentencia 
        String sql = "CREATE TABLE IF NOT EXISTS jugadores ( "
                + "nick VARCHAR(20) PRIMARY KEY, "
                + "nombre VARCHAR(30) NOT NULL, "
                + "edad INT NOT NULL CHECK(edad>=16), "
                + "rol VARCHAR(20) NOT NULL DEFAULT 'Suplente', "
                + "puntos INT NOT NULL CHECK (puntos>=0) DEFAULT 0, "
                + "id_equipo INT NOT NULL,"
                + "FOREIGN KEY (id_equipo) REFERENCES equipos (id_equipo) "
                + ")";
        //Mandamos la sentencia a nuestro metodo
        ejecutar(sql);
    }

    public int insertarDatosInicialesEquipos() throws SQLException {
        ConexionBBDD bd = new ConexionBBDD();
        int filasInsertadas = 0;

        // 1. Inserción omitiendo 'ciudad' (usa DEFAULT 'Salamanca')
        String sqlConDefault = "INSERT INTO equipos (id_equipo, nombre, fecha_creacion)"
                + " VALUES (?, ?, ?)";
        // 2. Inserción especificando 'ciudad'
        String sqlCompleto = "INSERT INTO equipos (id_equipo, nombre, ciudad, fecha_creacion) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = bd.conectar()) {
            
            //SET CATALOG NOS ASEGURAMOS DE TRABAJAR CON LA BASE DE DATPS
            con.setCatalog("torneo"); // nos aseguramos de trabajr con torneo BBDD

            //Insertamos el equipo 1 
            try (PreparedStatement ps = con.prepareStatement(sqlConDefault)) {
                ps.setInt(1, 1);
                ps.setString(2, "Lobos del Tormes");
                //PARA FECHAS
                ps.setDate(3, java.sql.Date.valueOf("2023-09-15"));

                //Añadimos 1 a las filas insertada
                filasInsertadas += ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(sqlCompleto)) {
                ps.setInt(1, 2);
                ps.setString(2, "Titanes de Zamora");
                ps.setString(3, "Zamora");
                //FECHA
                ps.setDate(4, java.sql.Date.valueOf("2024-01-20"));
                //AÑDAIMODS
                filasInsertadas += ps.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement(sqlConDefault)) {
                ps.setInt(1, 3);
                ps.setString(2, "Halcones Charros");
                ps.setNull(3, java.sql.Types.DATE); // Pasamos NULL en campos de tipo fecha
                
                filasInsertadas += ps.executeUpdate();
            }
        }
        return filasInsertadas;// Devuelve el total de equipos insertados (debería ser 3)
    }

    public int insertarInicialesJugadores() throws SQLException {

        ConexionBBDD bd = new ConexionBBDD();

        String sqlConDefault2 = "INSERT INTO jugadores (nick,nombre,edad,id_equipo) "
                + "VALUES (?,?,?,?) ";
        String sqlCompleto = "INSERT INTO jugadores (nick,nombre,edad,rol,puntos,id_equipo) "
                + "VALUES (?,?,?,?,?,?) ";
        String sqlConDefault1 = "INSERT INTO jugadores (nick,nombre,edad,puntos,id_equipo) "
                + "VALUES (?,?,?,?,?) ";
        int filasInsertadas = 0; //contador de filas
        try (Connection con = bd.conectar()) {
            
            
            con.setCatalog("torneo");  //CONFIRMAMOS QUE TRBAJAMOS CON TORNEO
            try (PreparedStatement ps = con.prepareStatement(sqlCompleto)) {
                ps.setString(1, "sombra");
                ps.setString(2, "Lucia");
                ps.setInt(3, 17);
                ps.setString(4, "Capitana");
                ps.setInt(5, 120);
                ps.setInt(6, 1);

                filasInsertadas += ps.executeUpdate();
            }
            
            try(PreparedStatement ps = con.prepareStatement(sqlConDefault2)){
               ps.setString(1, "rayo");
               ps.setString(2, "Mario");
               ps.setInt(3, 18);
               ps.setInt(4, 1);
               
               filasInsertadas+=ps.executeUpdate();
            }
            
            try(PreparedStatement ps = con.prepareStatement(sqlConDefault1)){
                ps.setString(1, "tanque");
                ps.setString(2,"Hugo");
                ps.setInt(3, 16);
                ps.setInt(4, 40);
                ps.setInt(5, 2);
                
                filasInsertadas+=ps.executeUpdate();
            }
        }
        return filasInsertadas;
    }
    
    public void añadirEmailJugadores()throws SQLException{
    
    //Definimos la sentencia
    String sql="ALTER TABLE jugadores ADD COLUMN email VARCHAR(80) NULL UNIQUE";
    
    // se la enviamos
        ejecutar(sql);
    }
    
    public void borrarTablaJugadore()throws SQLException{
        
        String sql ="DROP TABLE IF EXISTS jugadores";
        ejecutar(sql);
    }
    
    public void borrarTablaEquipo()throws SQLException{
        String sql= "DROP TABLE IF EXISTS equipos";
        ejecutar(sql);
    }
    
    public void borrarTodasTablas()throws SQLException{
    
    borrarTablaJugadore(); // 1er hijo
    borrarTablaEquipo(); // 2do padre
    }
    
    
    
}
