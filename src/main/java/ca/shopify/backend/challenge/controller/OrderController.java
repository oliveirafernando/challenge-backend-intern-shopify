package ca.shopify.backend.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.shopify.backend.challenge.controller.dto.OrderDTO;
import ca.shopify.backend.challenge.controller.dto.converter.OrderConverter;
import ca.shopify.backend.challenge.controller.response.Response;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/order")
@Api(value = "order_controller")
public class OrderController {

	@Autowired
	private OrderService orderService;

	private OrderConverter converter = new OrderConverter();

	@ApiOperation(value = "Description", response = OrderDTO.class)
//	@ApiResponses(value = { 
//			@ApiResponse(code = 200, message = "Successfully retrieved list"),
//			@ApiResponse(code = 400, message = "You are not authorized to view the resource") 
//		})
//	@GetMapping(value = "all/paginated/{page}/{count}", produces = "application/json")
//	public ResponseEntity<Object> getAllPaginated(HttpServletRequest request, @PathVariable int page,
//			@PathVariable int count) {
//
//		Response<Object> response = new Response<Object>();
//		try {
//			response.setData(
//					this.productService.getAllPaginated(page, count).stream().map(sh -> this.converter.apply(sh)));
//
//		} catch (PageableException e) {
//			response.addError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
//		}
//		return ResponseEntity.ok(response);
//	}

	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Object> count(Model model) {
		Response<Object> response = new Response<Object>();
		response.setData(this.orderService.countAll());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Object> findById(Model model, @PathVariable("id") Long id) {
		Response<Object> response = new Response<Object>();

		try {
			response.setData(converter.apply(this.orderService.findById(id)));
		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}
	
	

//	@PostMapping(produces = "application/json")
//	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody ProductDTO productDTO,
//			BindingResult result) {
//
//		Response<Product> response = new Response<Product>();
//		try {
//			Product product = this.converter.unapply(productDTO);
//			Product productPersisted = this.productService.create(product);
//			response.setData(productPersisted);
//		} catch (Exception e) {
//			response.addError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
//		}
//		return ResponseEntity.ok(response);
//	}
//
//	@PutMapping(produces = "application/json")
//	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody ProductDTO productDTO,
//			BindingResult result) {
//
//		Response<Product> response = new Response<Product>();
//		try {
//			Product product = this.converter.unapply(productDTO);
//			Product productPersisted = this.productService.update(product);
//			response.setData(productPersisted);
//		} catch (Exception e) {
//			response.addError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
//		}
//		return ResponseEntity.ok(response);
//	}
//
//	@DeleteMapping(value = "{id}")
//	public ResponseEntity<Object> delete(@PathVariable("id") Long id, Model model) {
//		Response<Object> response = new Response<Object>();
//		try {
//			this.productService.delete(id);
//		} catch (EntityValidationException e) {
//			response.addError(e.getMessage());
//			return ResponseEntity.badRequest().body(response);
//		}
//		return ResponseEntity.ok(response);
//	}

}
