/**
 * RA1: Gestión de información almacenada en ficheros
 * 
 * <h2>Descripción del Resultado de Aprendizaje</h2>
 * Este paquete contiene las clases necesarias para implementar el RA1 del módulo 
 * "Acceso a Datos" del CFGS DAM (Desarrollo de Aplicaciones Multiplataforma).
 * 
 * <h2>Criterios de Evaluación</h2>
 * <ul>
 *   <li><strong>CE1.a:</strong> Se han analizado las clases relacionadas con el tratamiento de ficheros</li>
 *   <li><strong>CE1.b:</strong> Se han utilizado flujos para el acceso a la información almacenada en ficheros</li>
 *   <li><strong>CE1.c:</strong> Se han utilizado las clases para gestión de ficheros y directorios</li>
 *   <li><strong>CE1.d:</strong> Se ha escrito y leído información en formato XML</li>
 *   <li><strong>CE1.e:</strong> Se ha escrito y leído información en formato JSON</li>
 *   <li><strong>CE1.f:</strong> Se ha escrito y leído información en otros formatos estándar</li>
 * </ul>
 * 
 * <h2>Estructura del Paquete</h2>
 * <ul>
 *   <li>{@link com.dam.accesodatos.ra1.FileUserService} - Interfaz con herramientas MCP</li>
 *   <li>{@link com.dam.accesodatos.ra1.FileUserServiceImpl} - Implementación para estudiantes</li>
 * </ul>
 * 
 * <h2>Tecnologías Java I/O Requeridas</h2>
 * 
 * <h3>CE1.f: Gestión de ficheros CSV</h3>
 * <ul>
 *   <li>{@link java.io.FileReader} - Lectura de archivos de texto</li>
 *   <li>{@link java.io.BufferedReader} - Lectura eficiente línea por línea</li>
 *   <li>{@link java.io.FileWriter} - Escritura de archivos de texto</li>
 *   <li>{@link java.io.PrintWriter} - Escritura formateada</li>
 *   <li>{@link java.lang.String#split(String)} - Parsing de campos CSV</li>
 * </ul>
 * 
 * <h3>CE1.e: Gestión de ficheros JSON</h3>
 * <ul>
 *   <li>{@link com.fasterxml.jackson.databind.ObjectMapper} - Serialización/deserialización JSON</li>
 *   <li>{@link com.fasterxml.jackson.core.type.TypeReference} - Referencias de tipos genéricos</li>
 * </ul>
 * 
 * <h3>CE1.d: Gestión de ficheros XML</h3>
 * <ul>
 *   <li>{@link javax.xml.parsers.DocumentBuilderFactory} - Factory para DOM parser</li>
 *   <li>{@link javax.xml.parsers.DocumentBuilder} - Constructor de documentos DOM</li>
 *   <li>{@link org.w3c.dom.Document} - Representación DOM del XML</li>
 *   <li>{@link org.w3c.dom.Element} - Elementos XML</li>
 *   <li>{@link javax.xml.parsers.SAXParserFactory} - Factory para SAX parser</li>
 *   <li>{@link javax.xml.parsers.SAXParser} - Parser SAX por eventos</li>
 *   <li>{@link org.xml.sax.helpers.DefaultHandler} - Handler SAX personalizado</li>
 *   <li>{@link javax.xml.transform.TransformerFactory} - Factory para transformadores</li>
 *   <li>{@link javax.xml.transform.Transformer} - Escritura DOM a archivo</li>
 * </ul>
 * 
 * <h3>CE1.c: Gestión de directorios</h3>
 * <ul>
 *   <li>{@link java.nio.file.Files} - Operaciones modernas de archivos</li>
 *   <li>{@link java.nio.file.Paths} - Construcción de rutas</li>
 *   <li>{@link java.nio.file.Path} - Representación de rutas</li>
 *   <li>{@link java.io.File} - Operaciones clásicas de archivos</li>
 * </ul>
 * 
 * <h3>CE1.b: Flujos de datos</h3>
 * <ul>
 *   <li>{@link java.io.FileInputStream} - Flujo de entrada de bytes</li>
 *   <li>{@link java.io.FileOutputStream} - Flujo de salida de bytes</li>
 *   <li>{@link java.io.InputStreamReader} - Conversión bytes a caracteres</li>
 *   <li>{@link java.io.OutputStreamWriter} - Conversión caracteres a bytes</li>
 * </ul>
 * 
 * <h2>Patrones de Implementación Requeridos</h2>
 * 
 * <h3>Try-with-resources (obligatorio)</h3>
 * <pre>{@code
 * try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
 *     // Operaciones con el archivo
 * } catch (IOException e) {
 *     throw new RuntimeException("Error procesando archivo: " + e.getMessage(), e);
 * }
 * }</pre>
 * 
 * <h3>Validación de archivos</h3>
 * <pre>{@code
 * if (!Files.exists(Paths.get(filePath))) {
 *     throw new RuntimeException("Archivo no encontrado: " + filePath);
 * }
 * }</pre>
 * 
 * <h3>Creación de directorios padre</h3>
 * <pre>{@code
 * Path parent = Paths.get(filePath).getParent();
 * if (parent != null && !Files.exists(parent)) {
 *     Files.createDirectories(parent);
 * }
 * }</pre>
 * 
 * <h2>Ejemplos de Uso MCP</h2>
 * 
 * <h3>Leer usuarios desde CSV</h3>
 * <pre>{@code
 * // Comando MCP
 * read_users_csv "/ruta/usuarios.csv"
 * 
 * // Retorna lista de usuarios en formato JSON
 * }</pre>
 * 
 * <h3>Escribir usuarios a XML</h3>
 * <pre>{@code
 * // Comando MCP
 * write_users_xml [lista_usuarios] "/ruta/salida.xml"
 * 
 * // Retorna true si exitoso
 * }</pre>
 * 
 * <h2>Evaluación</h2>
 * Los estudiantes deben:
 * <ol>
 *   <li>Completar todos los métodos en {@link FileUserServiceImpl}</li>
 *   <li>Hacer que todos los tests en {@code FileUserServiceTest} pasen</li>
 *   <li>Demostrar uso correcto de las clases Java I/O especificadas</li>
 *   <li>Implementar manejo robusto de excepciones</li>
 *   <li>Documentar decisiones técnicas en comentarios</li>
 * </ol>
 * 
 * @author Profesor DAM
 * @version 1.0
 * @since Java 17
 */
package com.dam.accesodatos.ra1;