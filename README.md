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

### Métodos a Implementar

| Método | Criterio | Tecnología | Descripción |
|--------|----------|------------|-------------|
| `readUsersFromCSV()` | CE1.f | BufferedReader, FileReader | Lee usuarios desde CSV |
| `writeUsersToCSV()` | CE1.f | PrintWriter, FileWriter | Escribe usuarios a CSV |
| `readUsersFromJSON()` | CE1.e | Jackson ObjectMapper | Lee usuarios desde JSON |
| `writeUsersToJSON()` | CE1.e | Jackson ObjectMapper | Escribe usuarios a JSON |
| `readUsersFromXML()` | CE1.d | DOM Parser | Lee usuarios desde XML |
| `writeUsersToXML()` | CE1.d | DOM + Transformer | Escribe usuarios a XML |
| `readUsersFromXMLSAX()` | CE1.d | SAX Parser | Lee usuarios con SAX |
| `listUserFiles()` | CE1.c | Files.list() | Lista archivos de usuario |
| `validateDirectoryStructure()` | CE1.c | Files API | Valida/crea directorios |

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

### Gestión de Archivos (CE1.c)
- `java.nio.file.Files` - Operaciones modernas
- `java.nio.file.Paths` - Construcción de rutas  
- `java.io.File` - Operaciones clásicas

### Flujos de Datos (CE1.b)
- `java.io.FileInputStream/FileOutputStream` - Flujos de bytes
- `java.io.FileReader/FileWriter` - Flujos de caracteres
- `java.io.BufferedReader/BufferedWriter` - Lectura/escritura eficiente

### Procesamiento XML (CE1.d)
- `javax.xml.parsers.DocumentBuilder` - DOM parsing
- `javax.xml.parsers.SAXParser` - SAX parsing
- `javax.xml.transform.Transformer` - Escritura XML

### Procesamiento JSON (CE1.e)
- `com.fasterxml.jackson.databind.ObjectMapper` - Serialización JSON

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

Una vez implementado, puedes probar las herramientas MCP:

```bash
# Ejecutar servidor MCP
mvn spring-boot:run

# Las herramientas estarán disponibles:
# - read_users_csv
# - write_users_csv  
# - read_users_json
# - write_users_json
# - read_users_xml_dom
# - write_users_xml
# - read_users_xml_sax
# - list_user_files
# - validate_directory_structure
```

## 🆘 Ayuda y Recursos

### Documentación Oficial
- [Java I/O Tutorial](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Jackson JSON Processing](https://github.com/FasterXML/jackson-docs)
- [Java XML Processing](https://docs.oracle.com/javase/tutorial/jaxp/)

### Archivos de Ejemplo
Revisa los archivos en `src/test/resources/examples/` para entender los formatos esperados.

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
