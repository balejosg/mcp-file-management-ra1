package com.dam.accesodatos.ra1;

import com.dam.accesodatos.model.User;
import org.springframework.ai.mcp.server.annotation.Tool;

import java.util.List;

/**
 * Interfaz para el RA1: Gestión de información almacenada en ficheros
 * 
 * Los estudiantes deben implementar esta interfaz para demostrar:
 * - CE1.a: Análisis de clases de tratamiento de ficheros
 * - CE1.b: Uso de flujos para acceso a información
 * - CE1.c: Gestión de ficheros y directorios
 * - CE1.d: Lectura/escritura en formato XML
 * - CE1.e: Lectura/escritura en formato JSON
 * - CE1.f: Lectura/escritura en otros formatos estándar (CSV)
 */
public interface FileUserService {

    /**
     * CE1.f: Lee usuarios desde un archivo CSV
     * 
     * Implementación requerida:
     * - Usar BufferedReader y FileInputStream
     * - Parsing manual con String.split(",")
     * - Manejo de excepciones con try-with-resources
     * - Validar estructura del archivo
     * 
     * Formato CSV esperado:
     * id,name,email,department,role,active,createdAt,updatedAt
     * 1,Juan Pérez,juan@example.com,IT,Developer,true,2024-01-01T10:00:00,2024-01-01T10:00:00
     * 
     * @param filePath Ruta absoluta del archivo CSV
     * @return Lista de usuarios leídos
     * @throws RuntimeException si hay error de lectura o formato inválido
     */
    @Tool(name = "read_users_csv", 
          description = "Lee usuarios desde archivo CSV usando Java I/O vanilla")
    List<User> readUsersFromCSV(String filePath);

    /**
     * CE1.f: Escribe usuarios a un archivo CSV
     * 
     * Implementación requerida:
     * - Usar PrintWriter y FileOutputStream
     * - Formateo manual de campos con comas
     * - Crear directorios padre si no existen
     * - Manejo de excepciones con try-with-resources
     * 
     * @param users Lista de usuarios a escribir
     * @param filePath Ruta absoluta del archivo CSV destino
     * @return true si la operación fue exitosa
     * @throws RuntimeException si hay error de escritura
     */
    @Tool(name = "write_users_csv", 
          description = "Escribe usuarios a archivo CSV usando Java I/O vanilla")
    boolean writeUsersToCSV(List<User> users, String filePath);

    /**
     * CE1.e: Lee usuarios desde un archivo JSON
     * 
     * Implementación requerida:
     * - Usar Jackson ObjectMapper (permitido en currículum)
     * - Deserialización de array JSON a List<User>
     * - Manejo de excepciones JSON
     * - Validar estructura JSON
     * 
     * @param filePath Ruta absoluta del archivo JSON
     * @return Lista de usuarios leídos
     * @throws RuntimeException si hay error de lectura o JSON inválido
     */
    @Tool(name = "read_users_json", 
          description = "Lee usuarios desde archivo JSON usando Jackson")
    List<User> readUsersFromJSON(String filePath);

    /**
     * CE1.e: Escribe usuarios a un archivo JSON
     * 
     * Implementación requerida:
     * - Usar Jackson ObjectMapper para serialización
     * - Formateo pretty-print del JSON
     * - Crear directorios padre si no existen
     * 
     * @param users Lista de usuarios a escribir
     * @param filePath Ruta absoluta del archivo JSON destino
     * @return true si la operación fue exitosa
     * @throws RuntimeException si hay error de escritura
     */
    @Tool(name = "write_users_json", 
          description = "Escribe usuarios a archivo JSON usando Jackson")
    boolean writeUsersToJSON(List<User> users, String filePath);

    /**
     * CE1.d: Lee usuarios desde un archivo XML usando DOM
     * 
     * Implementación requerida:
     * - Usar DocumentBuilder para parsing DOM
     * - Navegación manual del árbol XML
     * - Conversión de nodos a objetos User
     * - NO usar JAXB automático (para aprender DOM)
     * 
     * Estructura XML esperada:
     * <users>
     *   <user>
     *     <id>1</id>
     *     <name>Juan Pérez</name>
     *     <email>juan@example.com</email>
     *     <department>IT</department>
     *     <role>Developer</role>
     *     <active>true</active>
     *     <createdAt>2024-01-01T10:00:00</createdAt>
     *     <updatedAt>2024-01-01T10:00:00</updatedAt>
     *   </user>
     * </users>
     * 
     * @param filePath Ruta absoluta del archivo XML
     * @return Lista de usuarios leídos
     * @throws RuntimeException si hay error de parsing XML
     */
    @Tool(name = "read_users_xml_dom", 
          description = "Lee usuarios desde archivo XML usando DOM parser")
    List<User> readUsersFromXML(String filePath);

    /**
     * CE1.d: Escribe usuarios a un archivo XML usando Transformer
     * 
     * Implementación requerida:
     * - Usar DocumentBuilder para crear documento
     * - Construcción manual del árbol XML
     * - Usar Transformer para escribir a archivo
     * - Formateo pretty-print del XML
     * 
     * @param users Lista de usuarios a escribir
     * @param filePath Ruta absoluta del archivo XML destino
     * @return true si la operación fue exitosa
     * @throws RuntimeException si hay error de escritura XML
     */
    @Tool(name = "write_users_xml", 
          description = "Escribe usuarios a archivo XML usando DOM y Transformer")
    boolean writeUsersToXML(List<User> users, String filePath);

    /**
     * CE1.d: Lee usuarios desde archivo XML usando SAX parser (alternativa a DOM)
     * 
     * Implementación requerida:
     * - Usar SAXParser para parsing por eventos
     * - Implementar DefaultHandler personalizado
     * - Gestión de estado durante parsing
     * - Más eficiente para archivos grandes
     * 
     * @param filePath Ruta absoluta del archivo XML
     * @return Lista de usuarios leídos
     * @throws RuntimeException si hay error de parsing SAX
     */
    @Tool(name = "read_users_xml_sax", 
          description = "Lee usuarios desde archivo XML usando SAX parser")
    List<User> readUsersFromXMLSAX(String filePath);

    /**
     * CE1.c: Lista archivos de usuario en un directorio
     * 
     * Implementación requerida:
     * - Usar File.listFiles() o Files.list()
     * - Filtrar por extensiones (.csv, .json, .xml)
     * - Devolver información de archivos encontrados
     * - Validar que el directorio existe
     * 
     * @param directoryPath Ruta del directorio a examinar
     * @return Lista de nombres de archivos encontrados
     * @throws RuntimeException si el directorio no existe o no es accesible
     */
    @Tool(name = "list_user_files", 
          description = "Lista archivos de usuario en directorio (CSV, JSON, XML)")
    List<String> listUserFiles(String directoryPath);

    /**
     * CE1.c: Valida la estructura de un directorio de datos
     * 
     * Implementación requerida:
     * - Verificar que directorios existen
     * - Crear directorios faltantes
     * - Validar permisos de lectura/escritura
     * - Reportar estado del sistema de archivos
     * 
     * @param basePath Ruta base donde validar estructura
     * @return true si la estructura es válida o fue creada correctamente
     * @throws RuntimeException si hay problemas de permisos
     */
    @Tool(name = "validate_directory_structure", 
          description = "Valida y crea estructura de directorios para datos de usuario")
    boolean validateDirectoryStructure(String basePath);

    // ========== NUEVAS HERRAMIENTAS BASADAS EN CONCEPTOS TEÓRICOS DEL PDF ==========

    /**
     * ACTIVIDAD 1 DEL PDF: Obtiene información detallada de un archivo o directorio
     * 
     * Implementación requerida:
     * - Usar métodos de File: length(), lastModified(), canRead(), canWrite(), canExecute()
     * - Formatear fecha con SimpleDateFormat
     * - Mostrar permisos en formato "rwx"
     * - Distinguir entre archivo y directorio
     * 
     * @param filePath Ruta del archivo o directorio
     * @return String con información formateada (tamaño, permisos, fecha)
     * @throws RuntimeException si el archivo no existe
     */
    @Tool(name = "get_file_info", 
          description = "Obtiene información detallada de archivo (tamaño, permisos, fecha)")
    String getFileInfo(String filePath);

    /**
     * ACTIVIDAD 4 DEL PDF: Busca texto en un archivo línea por línea
     * 
     * Implementación requerida:
     * - Usar BufferedReader para leer línea por línea
     * - Usar String.contains() o String.indexOf() para buscar
     * - Mostrar número de línea donde aparece
     * - Contar total de ocurrencias
     * 
     * @param filePath Ruta del archivo de texto
     * @param searchText Texto a buscar
     * @return String con resultados de búsqueda (líneas encontradas y total)
     * @throws RuntimeException si hay error de lectura
     */
    @Tool(name = "search_text_in_file", 
          description = "Busca texto en archivo y muestra líneas donde aparece")
    String searchTextInFile(String filePath, String searchText);

    /**
     * EJEMPLO DEL PDF: Compara rendimiento entre BufferedReader vs FileReader
     * 
     * Implementación requerida:
     * - Leer el mismo archivo con FileReader y con BufferedReader
     * - Medir tiempo de ejecución con System.currentTimeMillis()
     * - Comparar velocidad de lectura
     * - Mostrar diferencias de rendimiento
     * 
     * @param filePath Ruta del archivo a leer
     * @return String con comparación de tiempos de ejecución
     * @throws RuntimeException si hay error de lectura
     */
    @Tool(name = "compare_io_performance", 
          description = "Compara rendimiento BufferedReader vs FileReader")
    String compareIOPerformance(String filePath);

    /**
     * ACCESO ALEATORIO: Lee usuarios desde posición específica usando RandomAccessFile
     * 
     * Implementación requerida:
     * - Usar RandomAccessFile en modo "r"
     * - Usar seek() para posicionarse
     * - Leer datos desde posición específica
     * - Manejar EOF correctamente
     * 
     * @param filePath Ruta del archivo
     * @param position Posición en bytes desde donde leer
     * @param length Número de bytes a leer
     * @return String con contenido leído
     * @throws RuntimeException si hay error de acceso
     */
    @Tool(name = "random_access_read", 
          description = "Lee desde posición específica usando RandomAccessFile")
    String randomAccessRead(String filePath, long position, int length);

    /**
     * ACCESO ALEATORIO: Escribe en posición específica usando RandomAccessFile
     * 
     * Implementación requerida:
     * - Usar RandomAccessFile en modo "rw"
     * - Usar seek() para posicionarse
     * - Escribir datos en posición específica
     * - Manejar extensión de archivo si es necesario
     * 
     * @param filePath Ruta del archivo
     * @param position Posición en bytes donde escribir
     * @param content Contenido a escribir
     * @return true si la operación fue exitosa
     * @throws RuntimeException si hay error de acceso
     */
    @Tool(name = "random_access_write", 
          description = "Escribe en posición específica usando RandomAccessFile")
    boolean randomAccessWrite(String filePath, long position, String content);

    /**
     * CODIFICACIÓN: Convierte archivo entre diferentes codificaciones
     * 
     * Implementación requerida:
     * - Usar InputStreamReader con charset origen
     * - Usar OutputStreamWriter con charset destino
     * - Leer con codificación específica y escribir con otra
     * - Manejar caracteres especiales correctamente
     * 
     * @param sourceFile Archivo origen
     * @param targetFile Archivo destino
     * @param sourceCharset Codificación origen (ej: "ISO-8859-1")
     * @param targetCharset Codificación destino (ej: "UTF-8")
     * @return true si la conversión fue exitosa
     * @throws RuntimeException si hay error de codificación
     */
    @Tool(name = "convert_file_encoding", 
          description = "Convierte archivo entre codificaciones (ISO-8859-1, UTF-8)")
    boolean convertFileEncoding(String sourceFile, String targetFile, 
                               String sourceCharset, String targetCharset);

    /**
     * ARCHIVOS TEMPORALES: Crea archivo temporal y devuelve información
     * 
     * Implementación requerida:
     * - Usar File.createTempFile()
     * - Escribir contenido en archivo temporal
     * - Mostrar ruta del archivo temporal creado
     * - Manejar limpieza de recursos
     * 
     * @param prefix Prefijo del nombre del archivo temporal
     * @param content Contenido a escribir en el archivo
     * @return String con ruta del archivo temporal creado
     * @throws RuntimeException si hay error de creación
     */
    @Tool(name = "create_temp_file", 
          description = "Crea archivo temporal y devuelve su ubicación")
    String createTempFile(String prefix, String content);

    /**
     * NIO vs IO: Compara operaciones usando java.nio.file.Files vs java.io.File
     * 
     * Implementación requerida:
     * - Realizar misma operación con Files.readAllLines() y BufferedReader
     * - Comparar sintaxis y rendimiento
     * - Mostrar diferencias en manejo de errores
     * - Usar Path vs File para operaciones
     * 
     * @param filePath Ruta del archivo a procesar
     * @return String con comparación de ambos enfoques
     * @throws RuntimeException si hay error en operaciones
     */
    @Tool(name = "compare_nio_vs_io", 
          description = "Compara java.nio.file.Files vs java.io tradicional")
    String compareNIOvsIO(String filePath);

    /**
     * PROCESAMIENTO DE TEXTO: Formatea archivo eliminando espacios y aplicando mayúsculas
     * Basado en el ejemplo del PDF (ArreglarFichero)
     * 
     * Implementación requerida:
     * - Leer archivo línea por línea
     * - Eliminar espacios al inicio de línea
     * - Sustituir múltiples espacios por uno solo
     * - Convertir primera letra de línea a mayúscula
     * - Crear archivo temporal con resultado
     * 
     * @param sourceFile Archivo origen a formatear
     * @return String con ruta del archivo temporal formateado
     * @throws RuntimeException si hay error de procesamiento
     */
    @Tool(name = "format_text_file", 
          description = "Formatea texto eliminando espacios extra y aplicando mayúsculas")
    String formatTextFile(String sourceFile);
}