/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.Scanner;

import java.sql.*;
/**
 *
 * @author limbe
 */
public class GestorTorneo {

    // Guardamos una única conexión abierta para trabajar con setCatalog()
    private Connection conexion;
    // Abre la conexión con el servidor si aún no está abierta
    private Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            ConexionBBDD bd = new ConexionBBDD();
            conexion = bd.conectar();
        }
        return conexion;
    }

    // Metodo comun exigido en el enunciado para ejecutar CREATE, ALTER y DROP
    public void ejecutar(String sql) throws SQLException {
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    // Opcion a) Crear la base de datos 'torneo' y seleccionarla con setCatalog()
    public void crearBaseDatos() throws SQLException {
        ejecutar("CREATE DATABASE IF NOT EXISTS torneo");
        getConexion().setCatalog("torneo"); // Selecciona 'torneo' para las siguientes operaciones
    }

    // Opcion b) Crear las tablas 'equipos' (padre) y 'jugadores' (hija)
    public void crearTablas() throws SQLException {
        // 1 Creamos la tabla padre 'equipos' con todas sus restricciones
        String sqlEquipos = "CREATE TABLE IF NOT EXISTS equipos ("
                + "id_equipo INT PRIMARY KEY, "
                + "nombre VARCHAR(40) NOT NULL UNIQUE, "
                + "ciudad VARCHAR(40) NOT NULL DEFAULT 'Salamanca', "
                + "fecha_creacion DATE"
                + ")";
        ejecutar(sqlEquipos);

        // 22 Creamos la tabla hija 'jugadores' con CHECK y FOREIGN KEY
        String sqlJugadores = "CREATE TABLE IF NOT EXISTS jugadores ("
                + "nick VARCHAR(20) PRIMARY KEY, "
                + "nombre VARCHAR(30) NOT NULL, "
                + "edad INT NOT NULL CHECK (edad >= 16), "
                + "rol VARCHAR(20) NOT NULL DEFAULT 'Suplente', "
                + "puntos INT NOT NULL DEFAULT 0 CHECK (puntos >= 0), "
                + "id_equipo INT NOT NULL, "
                + "FOREIGN KEY (id_equipo) REFERENCES equipos(id_equipo)"
                + ")";
        ejecutar(sqlJugadores);
    }

    // Opción c) Cargar los datos iniciales usando PreparedStatement
    // Devuelve un array de 2 posiciones: [filas de equipos, filas de jugadores]
    public int[] cargarDatosIniciales() throws SQLException {
        int filasEquipos = 0;
        int filasJugadores = 0;
        Connection con = getConexion();

        // --- 1. INSERTAR EQUIPOS ---
        // Cuando ciudad es '(por defecto)', NO la ponemos en el INSERT para que MySQL aplique 'Salamanca'
        String sqlEquipoDefecto = "INSERT INTO equipos (id_equipo, nombre, fecha_creacion) VALUES (?, ?, ?)";
        String sqlEquipoCompleto = "INSERT INTO equipos (id_equipo, nombre, ciudad, fecha_creacion) VALUES (?, ?, ?, ?)";

        // Equipo 1: (1, 'Lobos del Tormes', (por defecto), 2023-09-15)
        try (PreparedStatement ps = con.prepareStatement(sqlEquipoDefecto)) {
            ps.setInt(1, 1);
            ps.setString(2, "Lobos del Tormes");
            ps.setDate(3, Date.valueOf("2023-09-15"));
            filasEquipos += ps.executeUpdate();
        }

        // Equipo 2: (2, 'Titanes de Zamora', 'Zamora', 2024-01-20)
        try (PreparedStatement ps = con.prepareStatement(sqlEquipoCompleto)) {
            ps.setInt(1, 2);
            ps.setString(2, "Titanes de Zamora");
            ps.setString(3, "Zamora");
            ps.setDate(4, Date.valueOf("2024-01-20"));
            filasEquipos += ps.executeUpdate();
        }

        // Equipo 3: (3, 'Halcones Charros', (por defecto), NULL)
        try (PreparedStatement ps = con.prepareStatement(sqlEquipoDefecto)) {
            ps.setInt(1, 3);
            ps.setString(2, "Halcones Charros");
            ps.setNull(3, Types.DATE); // Metemos un NULL de SQL en la fecha
            filasEquipos += ps.executeUpdate();
        }

        // --- 2. INSERTAR JUGADORES ---
        // Jugador 1: ('sombra', 'Lucía', 17, 'Capitana', 120, 1) -> Lleva todos los datos
        String sqlJugador1 = "INSERT INTO jugadores (nick, nombre, edad, rol, puntos, id_equipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sqlJugador1)) {
            ps.setString(1, "sombra");
            ps.setString(2, "Lucía");
            ps.setInt(3, 17);
            ps.setString(4, "Capitana");
            ps.setInt(5, 120);
            ps.setInt(6, 1);
            filasJugadores += ps.executeUpdate();
        }

        // Jugador 2: ('rayo', 'Mario', 18, (por defecto), (por defecto), 1) -> Omitimos rol y puntos
        String sqlJugador2 = "INSERT INTO jugadores (nick, nombre, edad, id_equipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sqlJugador2)) {
            ps.setString(1, "rayo");
            ps.setString(2, "Mario");
            ps.setInt(3, 18);
            ps.setInt(4, 1);
            filasJugadores += ps.executeUpdate();
        }

        // Jugador 3: ('tanque', 'Hugo', 16, (por defecto), 40, 2) -> Omitimos rol
        String sqlJugador3 = "INSERT INTO jugadores (nick, nombre, edad, puntos, id_equipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sqlJugador3)) {
            ps.setString(1, "tanque");
            ps.setString(2, "Hugo");
            ps.setInt(3, 16);
            ps.setInt(4, 40);
            ps.setInt(5, 2);
            filasJugadores += ps.executeUpdate();
        }

        return new int[]{filasEquipos, filasJugadores};
    }

    // Opción d) Añadir columna 'email' (admite nulos y no se puede repetir)
    public void anadirColumnaEmail() throws SQLException {
        String sql = "ALTER TABLE jugadores ADD COLUMN email VARCHAR(80) NULL UNIQUE";
        ejecutar(sql);
    }

    // Opción e) Borrar las tablas en orden correcto (primero la hija y luego la padre)
    public void borrarTablas() throws SQLException {
        ejecutar("DROP TABLE IF EXISTS jugadores"); // 1 la hija para que no falle la clave ajena
        ejecutar("DROP TABLE IF EXISTS equipos");   // 2 la padre
    }

    // Cierra la conexión al salir del programa
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            // Ignoramos errores al cerrar al final
        }
    }
}
