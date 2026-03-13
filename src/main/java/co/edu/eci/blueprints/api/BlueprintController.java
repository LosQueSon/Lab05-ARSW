package co.edu.eci.blueprints.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blueprints")
@Tag(name = "Blueprints", description = "Endpoints de negocio protegidos por JWT")
@SecurityRequirement(name = "bearer-jwt")
public class BlueprintController {

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_blueprints.read')")
    @Operation(summary = "Listar blueprints", description = "Requiere scope blueprints.read")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "401", description = "Token ausente o invalido"),
            @ApiResponse(responseCode = "403", description = "Sin permisos suficientes")
    })
    public List<Map<String, String>> list() {
        return List.of(
            Map.of("id", "b1", "name", "Casa de campo"),
            Map.of("id", "b2", "name", "Edificio urbano")
        );
    }

    @GetMapping("/{author}")
    @PreAuthorize("hasAuthority('SCOPE_blueprints.read')")
        @Operation(summary = "Listar blueprints por autor", description = "Requiere scope blueprints.read")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "401", description = "Token ausente o invalido"),
            @ApiResponse(responseCode = "403", description = "Sin permisos suficientes")
        })
    public List<Map<String, String>> byAuthor(@PathVariable String author) {
        return List.of(
            Map.of("author", author, "name", "Plano1"),
            Map.of("author", author, "name", "Plano2")
        );
    }

    @GetMapping("/{author}/{bpname}")
    @PreAuthorize("hasAuthority('SCOPE_blueprints.read')")
        @Operation(summary = "Consultar blueprint por autor y nombre", description = "Requiere scope blueprints.read")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "401", description = "Token ausente o invalido"),
            @ApiResponse(responseCode = "403", description = "Sin permisos suficientes")
        })
    public Map<String, String> byAuthorAndName(@PathVariable String author,
                                               @PathVariable String bpname) {
        return Map.of(
                "author", author,
                "name", bpname
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_blueprints.write')")
        @Operation(summary = "Crear blueprint", description = "Requiere scope blueprints.write")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creado correctamente"),
            @ApiResponse(responseCode = "401", description = "Token ausente o invalido"),
            @ApiResponse(responseCode = "403", description = "Sin permisos suficientes")
        })
    public Map<String, String> create(@RequestBody Map<String, String> in) {
        return Map.of(
                "id", "new",
                "name", in.getOrDefault("name", "nuevo")
        );
    }

    @PutMapping("/{author}/{bpname}/points")
    @PreAuthorize("hasAuthority('SCOPE_blueprints.write')")
        @Operation(summary = "Agregar punto a blueprint", description = "Requiere scope blueprints.write")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualizacion exitosa"),
            @ApiResponse(responseCode = "401", description = "Token ausente o invalido"),
            @ApiResponse(responseCode = "403", description = "Sin permisos suficientes")
        })
    public Map<String, String> addPoint(@PathVariable String author,
                                        @PathVariable String bpname,
                                        @RequestBody Map<String, Integer> point) {
        return Map.of(
                "author", author,
                "blueprint", bpname,
                "status", "point added"
        );
    }
}