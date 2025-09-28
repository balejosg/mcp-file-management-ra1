package com.dam.accesodatos.mcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dam.accesodatos.ra1.FileUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador REST que expone las herramientas MCP via HTTP.
 * 
 * Proporciona endpoints para que los LLMs puedan:
 * - Listar herramientas disponibles
 * - Ejecutar herramientas específicas
 * - Obtener información sobre el servidor MCP
 */
@RestController
@RequestMapping("/mcp")
@CrossOrigin(origins = "*")
public class McpServerController {
    
    private static final Logger logger = LoggerFactory.getLogger(McpServerController.class);
    
    @Autowired
    private McpToolRegistry toolRegistry;
    
    @Autowired
    private FileUserService fileUserService;
    
    /**
     * Endpoint para listar todas las herramientas MCP disponibles
     */
    @GetMapping("/tools")
    public ResponseEntity<Map<String, Object>> getTools() {
        logger.debug("Solicitadas herramientas MCP disponibles");
        
        List<McpToolRegistry.McpToolInfo> tools = toolRegistry.getRegisteredTools();
        
        List<Map<String, String>> toolsList = tools.stream()
                .map(tool -> {
                    Map<String, String> toolMap = new HashMap<>();
                    toolMap.put("name", tool.getName());
                    toolMap.put("description", tool.getDescription());
                    return toolMap;
                })
                .collect(Collectors.toList());
        
        Map<String, Object> response = new HashMap<>();
        response.put("tools", toolsList);
        response.put("count", toolsList.size());
        response.put("server", "MCP Server - RA1 Ficheros DAM");
        response.put("version", "1.0.0");
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Endpoint para obtener información del servidor MCP
     */
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getServerInfo() {
        logger.debug("Solicitada información del servidor MCP");
        
        Map<String, Object> info = new HashMap<>();
        info.put("name", "MCP Server - RA1 Gestión de Ficheros DAM");
        info.put("version", "1.0.0");
        info.put("description", "Servidor MCP para gestión de ficheros con herramientas CSV, JSON y XML");
        info.put("protocol", "Model Context Protocol (MCP)");
        info.put("tools_count", toolRegistry.getRegisteredTools().size());
        info.put("supported_formats", List.of("CSV", "JSON", "XML"));
        
        return ResponseEntity.ok(info);
    }
    
    /**
     * Endpoint de health check
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "MCP Server");
        
        return ResponseEntity.ok(health);
    }
    
    /**
     * Endpoint para obtener documentación de uso
     */
    @GetMapping("/docs")
    public ResponseEntity<Map<String, Object>> getDocs() {
        Map<String, Object> docs = new HashMap<>();
        docs.put("usage", "Este servidor MCP proporciona herramientas para gestión de archivos de usuarios");
        docs.put("endpoints", Map.of(
            "GET /mcp/tools", "Lista todas las herramientas disponibles",
            "GET /mcp/info", "Información del servidor",
            "GET /mcp/health", "Estado del servidor",
            "GET /mcp/docs", "Esta documentación"
        ));
        docs.put("note", "Las herramientas están implementadas como esqueletos educativos para que los estudiantes las completen");
        
        return ResponseEntity.ok(docs);
    }
    
    /**
     * Endpoint de prueba para ejecutar getFileInfo (implementado por estudiante)
     */
    @PostMapping("/test/get_file_info")
    public ResponseEntity<Map<String, Object>> testGetFileInfo(@RequestBody Map<String, String> request) {
        logger.debug("Ejecutando prueba de getFileInfo");
        
        String filePath = request.get("filePath");
        if (filePath == null || filePath.trim().isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "El parámetro 'filePath' es requerido");
            return ResponseEntity.badRequest().body(error);
        }
        
        try {
            String result = fileUserService.getFileInfo(filePath);
            
            Map<String, Object> response = new HashMap<>();
            response.put("tool", "get_file_info");
            response.put("input", filePath);
            response.put("result", result);
            response.put("status", "success");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error ejecutando getFileInfo para archivo: " + filePath, e);
            
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Error ejecutando getFileInfo: " + e.getMessage());
            error.put("tool", "get_file_info");
            error.put("input", filePath);
            error.put("status", "error");
            
            return ResponseEntity.status(500).body(error);
        }
    }
}