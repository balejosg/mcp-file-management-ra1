# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an educational MCP (Model Context Protocol) server for the RA1 module "Gestión de Ficheros" in DAM (Desarrollo de Aplicaciones Multiplataforma). The project serves as a skeleton where students implement file management operations using vanilla Java I/O to demonstrate learning outcomes related to file processing.

## Common Development Commands

### Build and Test
```bash
# Clean and compile
mvn clean compile

# Run all tests (will fail initially - this is expected)
mvn test

# Run specific test
mvn test -Dtest=FileUserServiceTest#testReadUsersFromCSV_ReadsValidFile

# Run with debug logging
mvn test -Dlogging.level.com.dam.accesodatos=DEBUG

# Start the MCP server
mvn spring-boot:run
```

### Project Structure Commands
```bash
# Verify directory structure exists
ls -la data/
ls -la data/exports/
ls -la data/temp/

# Check test resources
ls -la src/test/resources/examples/
```

## Architecture Overview

### Core Components

**Main Application**: `McpAccesoDatosApplication.java` - Spring Boot entry point with educational MCP server configuration.

**Service Layer**: 
- `FileUserService.java` - Interface defining MCP tools for file operations (complete)
- `FileUserServiceImpl.java` - **STUDENT IMPLEMENTATION TARGET** - Contains TODO methods for:
  - CSV reading/writing using BufferedReader/PrintWriter
  - JSON processing using Jackson ObjectMapper  
  - XML processing using DOM and SAX parsers
  - File/directory management using Files API

**Data Model**: `User.java` - Complete POJO with validation annotations for serialization across all formats.

**MCP Integration**: The service methods are annotated with `@Tool` to expose them as MCP tools for AI interaction.

### File Format Support
- **CSV**: Manual parsing with String.split() and BufferedReader/PrintWriter
- **JSON**: Jackson ObjectMapper with TypeReference for List<User>
- **XML**: DOM parser for read/write + SAX parser alternative
- **Directory Management**: Files API for validation and creation

### TDD Approach
The project uses Test-Driven Development:
1. Tests are complete and will fail initially
2. Students implement methods to make tests pass
3. Tests validate proper use of required Java I/O classes

## MCP Server Configuration

The server exposes **18 tools** via `application.yml`:

### Core File Processing Tools (Original RA1):
- `read_users_csv`, `write_users_csv` 
- `read_users_json`, `write_users_json`
- `read_users_xml_dom`, `write_users_xml`, `read_users_xml_sax`
- `list_user_files`, `validate_directory_structure`

### Extended Tools (Based on PDF Theoretical Concepts):
- `get_file_info` - File/directory information (Actividad 1 PDF)
- `search_text_in_file` - Text search in files (Actividad 4 PDF)
- `compare_io_performance` - BufferedReader vs FileReader comparison
- `random_access_read`, `random_access_write` - RandomAccessFile operations
- `convert_file_encoding` - Character encoding conversion (UTF-8, ISO-8859-1)
- `create_temp_file` - Temporary file creation
- `compare_nio_vs_io` - java.nio.file.Files vs traditional java.io comparison
- `format_text_file` - Text formatting (spaces, capitalization)

Server runs on `localhost:3000` when started.

## Development Constraints

**Required Java I/O Classes** (as specified in curriculum):
- Files, Paths (modern file operations)
- FileReader/FileWriter, BufferedReader/PrintWriter (character streams)
- DocumentBuilder/SAXParser (XML processing)
- Jackson ObjectMapper (JSON processing)
- **RandomAccessFile** (random access operations)
- **InputStreamReader/OutputStreamWriter** (character encoding)
- **File.createTempFile()** (temporary files)
- **SimpleDateFormat** (date formatting)

**Prohibited**: External libraries beyond those in pom.xml, JAXB automatic binding, copying implementations without understanding.

## Implementation Notes

- All methods must use try-with-resources for proper resource management
- Parent directories must be created automatically when writing files
- Proper exception handling with descriptive RuntimeException messages
- LocalDateTime parsing/formatting using ISO_LOCAL_DATE_TIME format
- The `data/`, `data/exports/`, and `data/temp/` directory structure should be validated/created

## Extended Learning Objectives (Based on PDF Activities)

### File Information and Analysis:
- **File.length()**, **File.lastModified()** - File size and timestamp analysis
- **File.canRead()**, **File.canWrite()**, **File.canExecute()** - Permission checking
- **SimpleDateFormat** - Date formatting and display

### Text Processing and Search:
- **String.contains()**, **String.indexOf()** - Text search algorithms
- **Character.isWhitespace()**, **Character.isAlphabetic()** - Character analysis
- **Character.toUpperCase()** - Text transformation

### Performance Analysis:
- **System.currentTimeMillis()** - Execution time measurement
- Comparison between buffered vs unbuffered I/O operations
- Analysis of java.nio vs traditional java.io approaches

### Random Access File Operations:
- **RandomAccessFile.seek()** - File pointer positioning
- **RandomAccessFile.read()/write()** - Random access operations
- **RandomAccessFile** modes: "r", "rw", "rwd", "rws"

### Character Encoding:
- **InputStreamReader/OutputStreamWriter** with charset parameters
- Conversion between different encodings (UTF-8, ISO-8859-1)
- Handling of special characters and localization