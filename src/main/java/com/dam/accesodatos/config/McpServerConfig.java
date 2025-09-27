package com.dam.accesodatos.config;

import org.springframework.ai.mcp.server.annotation.EnableMcpServer;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración del servidor MCP (Model Context Protocol)
 * 
 * Esta configuración habilita el servidor MCP que permite la integración
 * con LLMs como Claude, GPT, etc. para que puedan usar las herramientas
 * definidas en FileUserService.
 */
@Configuration
@EnableMcpServer
public class McpServerConfig {
    
    // La configuración específica se encuentra en application.yml
    // Esta clase simplemente habilita el servidor MCP
}