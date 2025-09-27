package com.dam.accesodatos.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Configuración para el servicio de archivos
 * 
 * Esta configuración se encarga de:
 * - Crear directorios necesarios al inicio
 * - Configurar rutas base para archivos de datos
 * - Validar estructura de directorios
 */
@Configuration
public class FileServiceConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(FileServiceConfig.class);
    
    @Value("${app.file-service.base-path:./data}")
    private String basePath;
    
    @Value("${app.file-service.temp-path:./data/temp}")
    private String tempPath;
    
    @Value("${app.file-service.export-path:./data/exports}")
    private String exportPath;
    
    @Value("${app.file-service.create-directories-on-startup:true}")
    private boolean createDirectoriesOnStartup;
    
    @PostConstruct
    public void initializeDirectories() {
        if (createDirectoriesOnStartup) {
            logger.info("Inicializando estructura de directorios para MCP Server...");
            
            try {
                createDirectoryIfNotExists(basePath);
                createDirectoryIfNotExists(tempPath);
                createDirectoryIfNotExists(exportPath);
                
                logger.info("Estructura de directorios creada exitosamente:");
                logger.info("  - Base: {}", basePath);
                logger.info("  - Temp: {}", tempPath);
                logger.info("  - Export: {}", exportPath);
                
            } catch (IOException e) {
                logger.error("Error creando estructura de directorios: {}", e.getMessage(), e);
                throw new RuntimeException("No se pudo inicializar la estructura de directorios", e);
            }
        }
    }
    
    private void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            logger.debug("Directorio creado: {}", directoryPath);
        } else {
            logger.debug("Directorio ya existe: {}", directoryPath);
        }
    }
    
    public String getBasePath() {
        return basePath;
    }
    
    public String getTempPath() {
        return tempPath;
    }
    
    public String getExportPath() {
        return exportPath;
    }
}