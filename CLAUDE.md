# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an educational MCP (Model Context Protocol) server for the RA1 module "Gestión de Ficheros" in DAM (Desarrollo de Aplicaciones Multiplataforma). The project serves as a skeleton where students implement file management operations using vanilla Java I/O to demonstrate learning outcomes related to file processing.

## Common Development Commands

### Build and Test

**Desde IntelliJ IDEA:**
- **Build**: Botón "Build Project" (Ctrl+F9) o panel Gradle → Tasks → build → build
- **Run tests**: Botón "Run Tests" o panel Gradle → Tasks → verification → test
- **Run server**: Botón "Run" junto a McpAccesoDatosApplication.java o panel Gradle → Tasks → application → bootRun
- **Gradle panel**: View → Tool Windows → Gradle

**Desde línea de comandos (usando Gradle de IntelliJ):**
```bash
# Clean and compile
gradle clean compileJava

# Run all tests (will fail initially - this is expected)
gradle test

# Run specific test
gradle test --tests FileUserServiceTest.testReadUsersFromCSV_ReadsValidFile

# Run with debug logging
gradle test --debug

# Start the MCP server
gradle bootRun
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

The server exposes **18 tools** via `application.yml` (13 essential + 5 optional):

### ✅ Core Tools - REQUIRED (13 essential methods)

Students must implement these to demonstrate mastery of RA1 fundamentals:

**File Formats (CE1.d, CE1.e, CE1.f):**
- `read_users_csv`, `write_users_csv` - CSV parsing and formatting
- `read_users_json`, `write_users_json` - JSON with Jackson
- `read_users_xml_dom`, `write_users_xml` - XML with DOM parser

**File Analysis (CE1.a):**
- `get_file_info` - File metadata (Actividad 1 PDF) - **IMPLEMENTED EXAMPLE**
- `compare_io_performance` - BufferedReader vs FileReader comparison

**Stream Operations (CE1.b):**
- `search_text_in_file` - Text search with BufferedReader (Actividad 4 PDF)
- `random_access_read`, `random_access_write` - RandomAccessFile operations
- `convert_file_encoding` - InputStreamReader/OutputStreamWriter with charsets

**File Management (CE1.c):**
- `list_user_files` - Directory navigation and filtering

### ⚠️ Optional Tools - ADVANCED (5 additional methods)

These methods are less important didactically. Prioritize core tools first:

- `read_users_xml_sax` - [OPCIONAL] SAX parser (advanced alternative to DOM)
- `validate_directory_structure` - [OPCIONAL] Utility method, little pedagogical value
- `create_temp_file` - [OPCIONAL] Specific utility, teaches little about streams
- `compare_nio_vs_io` - [OPCIONAL] Advanced NIO concepts
- `format_text_file` - [OPCIONAL] More String processing than I/O

**Pedagogical Note:** Optional methods are for advanced students who have mastered core concepts.
They provide less educational value for learning Java I/O fundamentals compared to the 13 essential methods.

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

**Prohibited**: External libraries beyond those in build.gradle, JAXB automatic binding, copying implementations without understanding.

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