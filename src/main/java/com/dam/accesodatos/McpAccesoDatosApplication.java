package com.dam.accesodatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Esqueleto MCP Server para RA1: Gestión de Ficheros
 * 
 * RA1: Desarrolla aplicaciones que gestionan información almacenada en ficheros
 * 
 * Criterios de Evaluación:
 * - CE1.a: Analizar clases relacionadas con tratamiento de ficheros
 * - CE1.b: Utilizar flujos para acceso a información en ficheros
 * - CE1.c: Utilizar clases para gestión de ficheros y directorios
 * - CE1.d: Escribir y leer información en formato XML
 * - CE1.e: Escribir y leer información en formato JSON
 * - CE1.f: Escribir y leer información en otros formatos estándar
 * 
 * Los estudiantes deben implementar: FileUserServiceImpl
 */
@SpringBootApplication
public class McpAccesoDatosApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpAccesoDatosApplication.class, args);
    }
}