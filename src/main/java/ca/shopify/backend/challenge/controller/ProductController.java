package ca.shopify.backend.challenge.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.shopify.backend.challenge.controller.dto.ProductConverter;
import ca.shopify.backend.challenge.controller.dto.ProductDTO;
import ca.shopify.backend.challenge.controller.response.Response;
import ca.shopify.backend.challenge.model.Product;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.PageableException;
import ca.shopify.backend.challenge.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/product")
@Api(value = "product_controller")
public class ProductController {

	@Autowired
	private ProductService productService;

	private ProductConverter converter = new ProductConverter();

	@ApiOperation(value = "View a list of available products", response = ProductDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 400, message = "You are not authorized to view the resource") })
	@GetMapping(value = "all/paginated/{page}/{count}", produces = "application/json")
	public ResponseEntity<Object> getAllPaginated(HttpServletRequest request, @PathVariable int page,
			@PathVariable int count) {

		Response<Object> response = new Response<Object>();
		try {
			response.setData(
					this.productService.getAllPaginated(page, count).stream().map(sh -> this.converter.apply(sh)));

		} catch (PageableException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Object> count(Model model) {
		Response<Object> response = new Response<Object>();
		response.setData(this.productService.countAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Object> findById(Model model, @PathVariable("id") Long id) {
		Response<Object> response = new Response<Object>();

		try {
			response.setData(this.converter.apply(this.productService.findById(id)));

		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}
	
	@GetMapping(value = "/byShopId/{shopId}", produces = "application/json")
	public ResponseEntity<Object> findByShopId(Model model, @PathVariable("shopId") Long shopId) {
		Response<Object> response = new Response<Object>();

		try {
			List<ProductDTO> prods = this.productService.getProductsByShopId(shopId)
					.stream()
					.map(this.converter::apply)
					.collect(Collectors.toList());
			prods.forEach(p -> p.setShop(null));
			response.setData(prods);

		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody ProductDTO productDTO,
			BindingResult result) {

		Response<Product> response = new Response<Product>();
		try {
			Product product = this.converter.unapply(productDTO);
			Product productPersisted = this.productService.create(product);
			response.setData(productPersisted);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping(produces = "application/json")
	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody ProductDTO productDTO,
			BindingResult result) {

		Response<Product> response = new Response<Product>();
		try {
			Product product = this.converter.unapply(productDTO);
			Product productPersisted = this.productService.update(product);
			response.setData(productPersisted);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id, Model model) {
		Response<Object> response = new Response<Object>();
		try {
			this.productService.delete(id);
		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
