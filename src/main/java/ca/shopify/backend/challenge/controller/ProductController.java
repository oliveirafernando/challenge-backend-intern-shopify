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

import ca.shopify.backend.challenge.controller.dto.ProductDTO;
import ca.shopify.backend.challenge.controller.dto.converter.ProductConverter;
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
@Api
public class ProductController {

	@Autowired
	private ProductService productService;

	private ProductConverter converter = new ProductConverter();
	
	@ApiOperation(value = "Count of all Products", response = Integer.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Something was wrong") 
		})
	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Object> count(Model model) {
		Response<Object> response = new Response<Object>();
		response.setData(this.productService.countAll());
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Paginated list of Products", response = ProductDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@GetMapping(value = "all/page/{page}/size/{size}", produces = "application/json")
	public ResponseEntity<Object> getAllPaginated(HttpServletRequest request, @PathVariable int page, @PathVariable int size) {

		Response<Object> response = new Response<Object>();
		try {
			response.setData(
					this.productService.getAllPaginated(page, size).stream().map(sh -> this.converter.apply(sh)));

		} catch (PageableException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Product by Id", response = ProductDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Entity not found") 
		})
	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Object> getById(Model model, @PathVariable("id") Long id) {
		Response<Object> response = new Response<Object>();

		try {
			response.setData(this.converter.apply(this.productService.findById(id)));

		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}
	
	@ApiOperation(value = "Products by Shop Id", response = ProductDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Entity not found") 
		})
	@GetMapping(value = "/shop/{shopId}", produces = "application/json")
	public ResponseEntity<Object> getByShopId(Model model, @PathVariable("shopId") Long shopId) {
		Response<Object> response = new Response<Object>();

		try {
			List<ProductDTO> prods = this.productService.getProductsByShopId(shopId)
					.stream()
					.map(this.converter::apply)
					.collect(Collectors.toList());
			response.setData(prods);

		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Create Product", response = ProductDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@PostMapping(produces = "application/json")
	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody ProductDTO productDTO,
			BindingResult result) {

		Response<ProductDTO> response = new Response<ProductDTO>();
		try {
			Product product = this.converter.unapply(productDTO);
			ProductDTO productPersistedDTO = this.converter.apply(this.productService.create(product));
			response.setData(productPersistedDTO);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Update Product", response = ProductDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@PutMapping(produces = "application/json")
	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody ProductDTO productDTO,
			BindingResult result) {

		Response<ProductDTO> response = new Response<ProductDTO>();
		try {
			Product product = this.converter.unapply(productDTO);
			ProductDTO productUpdatedDTO = this.converter.apply(this.productService.update(product));
			response.setData(productUpdatedDTO);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Delete Product by id", response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id, Model model) {
		Response<Object> response = new Response<Object>();
		try {
			this.productService.delete(id);
			response.setData("OK");
		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
