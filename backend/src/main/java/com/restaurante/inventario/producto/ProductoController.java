package com.restaurante.inventario.producto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoRepository repository;

	public ProductoController(ProductoRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Producto> listar() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtener(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		producto.setId(null);
		return repository.save(producto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
