# Archivos de Ejemplo para Prácticas Avanzadas

Este directorio contiene archivos de ejemplo para practicar los conceptos teóricos del PDF "UT2. Ficheros.pdf":

## Archivos para Búsqueda de Texto (Actividad 4 PDF)
- `texto_para_buscar.txt` - Archivo con múltiples ocurrencias de palabras para practicar búsqueda
  - Usar con herramienta MCP: `search_text_in_file`
  - Buscar palabras como "java", "programación", etc.

## Archivos para Procesamiento de Texto (Ejemplo ArreglarFichero PDF)
- `texto_con_espacios.txt` - Archivo con espacios irregulares para formatear
  - Usar con herramienta MCP: `format_text_file`
  - Practica eliminación de espacios y capitalización

## Archivos para Codificación de Caracteres
- `archivo_iso8859.txt` - Archivo con caracteres especiales para conversión
  - Usar con herramienta MCP: `convert_file_encoding`
  - Convertir entre ISO-8859-1 y UTF-8

## Archivos para Pruebas de Rendimiento
- `archivo_grande_performance.txt` - Archivo con 50 líneas para comparar I/O
  - Usar con herramienta MCP: `compare_io_performance`
  - Compara BufferedReader vs FileReader

## Archivos Originales RA1
- `sample_users.csv` - Datos de usuarios en formato CSV
- `sample_users.json` - Datos de usuarios en formato JSON  
- `sample_users.xml` - Datos de usuarios en formato XML

## Comandos de Ejemplo

### Información de Archivos (Actividad 1 PDF)
```bash
# Usar herramienta MCP: get_file_info
# Ejemplo: get_file_info("./src/test/resources/examples/texto_para_buscar.txt")
```

### Búsqueda de Texto (Actividad 4 PDF)
```bash
# Usar herramienta MCP: search_text_in_file  
# Ejemplo: search_text_in_file("./src/test/resources/examples/texto_para_buscar.txt", "java")
```

### Acceso Aleatorio
```bash
# Usar herramientas MCP: random_access_read, random_access_write
# Ejemplo: random_access_read("./src/test/resources/examples/archivo_grande_performance.txt", 100, 50)
```

### Archivos Temporales
```bash
# Usar herramienta MCP: create_temp_file
# Ejemplo: create_temp_file("test", "Contenido de prueba")
```