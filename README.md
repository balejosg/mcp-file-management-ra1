# MCP Server - RA1: Gestión de Ficheros DAM

> **Esqueleto educativo para el Resultado de Aprendizaje 1 del módulo "Acceso a Datos"**  
> CFGS Desarrollo de Aplicaciones Multiplataforma (DAM)

## 📋 Descripción del Proyecto

Este proyecto es un **servidor MCP (Model Context Protocol)** educativo diseñado para que los estudiantes implementen el **RA1: Desarrolla aplicaciones que gestionan información almacenada en ficheros**.

### ¿Qué es MCP?
MCP es un protocolo que permite a las aplicaciones de IA interactuar con herramientas externas. En este caso, los estudiantes crearán herramientas que permiten a una IA gestionar archivos de usuarios en diferentes formatos.

## 🎯 Objetivos de Aprendizaje (RA1)

Al completar este proyecto, demostrarás:

- **CE1.a:** Análisis de clases relacionadas con el tratamiento de ficheros
- **CE1.b:** Utilización de flujos para el acceso a información en ficheros  
- **CE1.c:** Utilización de clases para gestión de ficheros y directorios
- **CE1.d:** Escritura y lectura de información en formato XML
- **CE1.e:** Escritura y lectura de información en formato JSON
- **CE1.f:** Escritura y lectura de información en otros formatos estándar (CSV)

## 🏗️ Estructura del Proyecto

```
src/
├── main/java/com/dam/accesodatos/
│   ├── McpAccesoDatosApplication.java     # Aplicación principal
│   ├── model/
│   │   ├── User.java                      # ✅ Modelo de datos (COMPLETO)
│   │   ├── UserCreateDto.java             # ✅ DTO para creación (COMPLETO)
│   │   └── UserQueryDto.java              # ✅ DTO para consultas (COMPLETO)
│   └── ra1/
│       ├── FileUserService.java           # ✅ Interfaz con herramientas MCP (COMPLETO)
│       └── FileUserServiceImpl.java       # ❌ PARA IMPLEMENTAR POR ESTUDIANTES
└── test/
    ├── java/com/dam/accesodatos/ra1/
    │   └── FileUserServiceTest.java        # ✅ Tests TDD (COMPLETOS)
    └── resources/examples/
        ├── sample_users.csv                # ✅ Ejemplo CSV
        ├── sample_users.json               # ✅ Ejemplo JSON
        └── sample_users.xml                # ✅ Ejemplo XML
```

## 🚀 Cómo Empezar

### 1. Prerrequisitos
- Java 17+
- Maven 3.8+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### 2. Clonar y Ejecutar
```bash
git clone https://github.com/balejosg/mcp-file-management-ra1
cd mcp-server-ra1-ficheros
mvn clean compile
```

### 3. Ejecutar Tests (Fallarán inicialmente)
```bash
mvn test
```
> ⚠️ **Esperado:** Todos los tests fallan porque necesitas implementar los métodos

## 📝 Tu Tarea: Implementar FileUserServiceImpl

Abre el archivo `src/main/java/com/dam/accesodatos/ra1/FileUserServiceImpl.java` y completa todos los métodos marcados con `TODO`.

### Métodos a Implementar (18 total)

#### CE1.a: Análisis de clases relacionadas con tratamiento de ficheros
| Método | Tecnología | Descripción |
|--------|------------|-------------|
| `getFileInfo()` | File.length(), canRead(), SimpleDateFormat | Información detallada de archivos (actividad 1 de la presentación vista en clase) |
| `compareIOPerformance()` | System.currentTimeMillis(), FileReader vs BufferedReader | Comparación de rendimiento I/O con y sin buffering |
| `compareNIOvsIO()` | Files.readAllLines() vs BufferedReader | Análisis comparativo java.nio vs java.io tradicional |

#### CE1.b: Utilización de flujos para acceso a información en ficheros
| Método | Tecnología | Descripción |
|--------|------------|-------------|
| `searchTextInFile()` | BufferedReader + String.contains() | Búsqueda de texto en archivos (actividad 4 de la presentación vista en clase) |
| `randomAccessRead()` | RandomAccessFile + seek() | Lectura desde posición específica |
| `randomAccessWrite()` | RandomAccessFile + seek() | Escritura en posición específica |
| `convertFileEncoding()` | InputStreamReader/OutputStreamWriter | Conversión entre codificaciones (UTF-8, ISO-8859-1) |

#### CE1.c: Utilización de clases para gestión de ficheros y directorios
| Método | Tecnología | Descripción |
|--------|------------|-------------|
| `listUserFiles()` | Files.list() | Lista archivos de usuario en directorio |
| `validateDirectoryStructure()` | Files API | Valida y crea estructura de directorios |
| `createTempFile()` | File.createTempFile() | Creación y gestión de archivos temporales |
| `formatTextFile()` | Character processing | Procesamiento de texto avanzado (basado en ejemplo ArreglarFichero de la presentación vista en clase) |

#### CE1.d: Escritura y lectura de información en formato XML
| Método | Tecnología | Descripción |
|--------|------------|-------------|
| `readUsersFromXML()` | DOM Parser | Lee usuarios desde XML usando DOM |
| `writeUsersToXML()` | DOM + Transformer | Escribe usuarios a XML usando DOM |
| `readUsersFromXMLSAX()` | SAX Parser | Lee usuarios desde XML usando SAX (alternativa eficiente) |

#### CE1.e: Escritura y lectura de información en formato JSON
| Método | Tecnología | Descripción |
|--------|------------|-------------|
| `readUsersFromJSON()` | Jackson ObjectMapper | Lee usuarios desde JSON |
| `writeUsersToJSON()` | Jackson ObjectMapper | Escribe usuarios a JSON con formato pretty-print |

#### CE1.f: Escritura y lectura de información en otros formatos estándar
| Método | Tecnología | Descripción |
|--------|------------|-------------|
| `readUsersFromCSV()` | BufferedReader, FileReader | Lee usuarios desde CSV con parsing manual |
| `writeUsersToCSV()` | PrintWriter, FileWriter | Escribe usuarios a CSV con formateo manual |

## 🧪 Metodología TDD (Test-Driven Development)

1. **🔴 ROJO:** Ejecuta tests → Fallan (estado inicial)
2. **🟢 VERDE:** Implementa código mínimo → Tests pasan
3. **🔵 REFACTOR:** Mejora código → Tests siguen pasando

```bash
# Ejecutar tests específicos
mvn test -Dtest=FileUserServiceTest#testReadUsersFromCSV_ReadsValidFile
```

## 💡 Ejemplos de Implementación

### CE1.f: Lectura CSV con BufferedReader
```java
@Override
public List<User> readUsersFromCSV(String filePath) {
    List<User> users = new ArrayList<>();
    
    // 1. Validar archivo existe
    if (!Files.exists(Paths.get(filePath))) {
        throw new RuntimeException("Archivo no encontrado: " + filePath);
    }
    
    // 2. Usar try-with-resources
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line = reader.readLine(); // Saltear cabeceras
        
        // 3. Leer línea por línea
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            
            // 4. Convertir a User
            User user = parseUserFromCSV(fields);
            users.add(user);
        }
    } catch (IOException e) {
        throw new RuntimeException("Error leyendo CSV: " + e.getMessage(), e);
    }
    
    return users;
}
```

### CE1.d: Escritura XML con DOM
```java
@Override
public boolean writeUsersToXML(List<User> users, String filePath) {
    try {
        // 1. Crear documento
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        
        // 2. Crear elemento raíz
        Element root = doc.createElement("users");
        doc.appendChild(root);
        
        // 3. Agregar usuarios
        for (User user : users) {
            Element userElement = doc.createElement("user");
            
            Element id = doc.createElement("id");
            id.setTextContent(user.getId().toString());
            userElement.appendChild(id);
            
            // ... más campos
            
            root.appendChild(userElement);
        }
        
        // 4. Escribir a archivo
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
        
        return true;
    } catch (Exception e) {
        throw new RuntimeException("Error escribiendo XML: " + e.getMessage(), e);
    }
}
```

## 📚 Clases Java I/O Requeridas

### Análisis de Clases (CE1.a)
- `java.io.File` - Operaciones clásicas, información de archivos
- `java.text.SimpleDateFormat` - Formateo de fechas
- `java.util.Date` - Representación de fechas y timestamps
- `java.lang.System` - Medición de tiempo (currentTimeMillis)

### Flujos de Datos (CE1.b)
- `java.io.FileInputStream/FileOutputStream` - Flujos de bytes
- `java.io.FileReader/FileWriter` - Flujos de caracteres básicos
- `java.io.BufferedReader/BufferedWriter` - Lectura/escritura con buffering
- `java.io.InputStreamReader/OutputStreamWriter` - Conversión con codificación
- `java.io.RandomAccessFile` - Acceso aleatorio a archivos
- `java.lang.String` - Métodos contains(), indexOf() para búsqueda

### Gestión de Archivos y Directorios (CE1.c)
- `java.nio.file.Files` - Operaciones modernas (readAllLines, list, exists)
- `java.nio.file.Paths` - Construcción de rutas
- `java.io.File` - Operaciones clásicas (createTempFile, listFiles)
- `java.lang.Character` - Análisis de caracteres (isWhitespace, isAlphabetic, toUpperCase)

### Procesamiento XML (CE1.d)
- `javax.xml.parsers.DocumentBuilder` - DOM parsing
- `javax.xml.parsers.DocumentBuilderFactory` - Creación de parsers DOM
- `javax.xml.parsers.SAXParser` - SAX parsing por eventos
- `javax.xml.parsers.SAXParserFactory` - Creación de parsers SAX
- `javax.xml.transform.Transformer` - Escritura XML con formato
- `org.xml.sax.helpers.DefaultHandler` - Handler personalizado para SAX

### Procesamiento JSON (CE1.e)
- `com.fasterxml.jackson.databind.ObjectMapper` - Serialización/deserialización JSON
- `com.fasterxml.jackson.core.type.TypeReference` - Tipos genéricos para deserialización

### Formatos Estándar (CE1.f)
- `java.io.PrintWriter` - Escritura formateada de texto
- `java.io.BufferedReader` - Lectura eficiente línea por línea
- `java.lang.String` - Métodos split(), trim() para parsing CSV

## ✅ Criterios de Evaluación

Para aprobar este RA1, debes:

- [ ] **Todos los tests pasan en verde**
- [ ] **Usar únicamente las clases Java I/O especificadas**
- [ ] **Implementar manejo robusto de excepciones con try-with-resources**
- [ ] **Crear directorios padre automáticamente cuando sea necesario**
- [ ] **Documentar decisiones técnicas en comentarios**
- [ ] **Código limpio y bien estructurado**
- [ ] **Explica el código a tu profesor en horario de clase**

## 🔧 Uso del Servidor MCP

Una vez implementado, puedes probar las **18 herramientas MCP** disponibles:

```bash
# Ejecutar servidor MCP
mvn spring-boot:run

# Herramientas disponibles organizadas por criterios de evaluación:

# CE1.a: Análisis de clases
# - get_file_info
# - compare_io_performance  
# - compare_nio_vs_io

# CE1.b: Flujos de datos
# - search_text_in_file
# - random_access_read
# - random_access_write
# - convert_file_encoding

# CE1.c: Gestión de archivos/directorios
# - list_user_files
# - validate_directory_structure
# - create_temp_file
# - format_text_file

# CE1.d: Procesamiento XML
# - read_users_xml_dom
# - write_users_xml
# - read_users_xml_sax

# CE1.e: Procesamiento JSON
# - read_users_json
# - write_users_json

# CE1.f: Formatos estándar
# - read_users_csv
# - write_users_csv
```

## 🆘 Ayuda y Recursos

### Documentación Oficial
- [Java I/O Tutorial](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Jackson JSON Processing](https://github.com/FasterXML/jackson-docs)
- [Java XML Processing](https://docs.oracle.com/javase/tutorial/jaxp/)

### Archivos de Ejemplo
Revisa los archivos en `src/test/resources/examples/` para practicar todos los conceptos:

**Archivos para formatos básicos (RA1):**
- `sample_users.csv` - Datos de usuarios en formato CSV
- `sample_users.json` - Datos de usuarios en formato JSON  
- `sample_users.xml` - Datos de usuarios en formato XML

**Archivos para conceptos de la presentación vista en clase:**
- `texto_para_buscar.txt` - Para practicar búsqueda de texto (actividad 4)
- `texto_con_espacios.txt` - Para procesamiento de texto con espacios irregulares
- `archivo_iso8859.txt` - Para conversión de codificaciones 
- `archivo_grande_performance.txt` - Para pruebas de rendimiento I/O
- `README_ejemplos.md` - Guía detallada de uso de todos los archivos

### Debugging
```bash
# Tests con logs detallados
mvn test -Dlogging.level.com.dam.accesodatos=DEBUG

# Test individual
mvn test -Dtest=FileUserServiceTest#testWriteUsersToCSV_CreatesValidFile
```

## 📞 Soporte

- **Profesor:** bruno.ag@educa.madrid.org
- **Documentación adicional:** Ver `package-info.java` en el paquete `ra1`
- **Issues:** Crear issue en el repositorio del curso

---

**¡Éxito en tu implementación del RA1! 🚀**

> Recuerda: Este proyecto no solo te enseña acceso a datos, sino que también te da experiencia con tecnologías modernas como MCP que se usan en aplicaciones de IA profesionales.
