package com.boaglio.apivmvp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag( name="Cliente Controller", description = "Operações relacionadas a clientes")
public class ClienteAPI {

    private final ClienteRepository clienteRepository;

    public ClienteAPI(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public ResponseEntity<List> getAllClientes() {
        var clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @Operation( summary = "Obtém um cliente pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") @Parameter(example = "2") Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
    @Operation(summary = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Operation( summary = "Atualiza um cliente existente")
    @PutMapping
    public Cliente atualizarCliente ( @RequestBody  Cliente novoCliente) {

        Cliente clienteExistente = clienteRepository.findById(novoCliente.getId())
                .orElseThrow(() -> new  CustomerNotFoundException(novoCliente.getId()));

        clienteExistente.setCpf(novoCliente.getCpf());
        clienteExistente.setNome(novoCliente.getNome());
        clienteExistente.setDataNascimento(novoCliente.getDataNascimento());
        clienteExistente.setCidadeNascimento(novoCliente.getCidadeNascimento());

        return clienteRepository.save(clienteExistente);
    }

    @Operation(summary = "Exclui um cliente pelo ID")
    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable("id") @Parameter(example = "2")  Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow( () -> new  CustomerNotFoundException(id) );

         clienteRepository.delete(cliente);
    }

}