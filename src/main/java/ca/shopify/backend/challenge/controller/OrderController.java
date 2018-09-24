package ca.shopify.backend.challenge.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.shopify.backend.challenge.controller.dto.OrderDTO;
import ca.shopify.backend.challenge.controller.dto.converter.OrderConverter;
import ca.shopify.backend.challenge.controller.response.Response;
import ca.shopify.backend.challenge.model.Order;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/order")
@Api
public class OrderController {

	@Autowired
	private OrderService orderService;

	private OrderConverter converter = new OrderConverter();

	@ApiOperation(value = "Count of all Orders", response = Integer.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Something was wrong") })
	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Object> count(Model model) {
		Response<Object> response = new Response<Object>();
		response.setData(this.orderService.countAll());
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Order by Id", response = OrderDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Entity not found") })
	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Object> getById(Model model, @PathVariable("id") Long id) {
		Response<Object> response = new Response<Object>();
		try {
			response.setData(converter.apply(this.orderService.findById(id)));
		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Create Order", response = OrderDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") })
	@PostMapping(produces = "application/json")
	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody OrderDTO orderDTO,
			BindingResult result) {

		Response<OrderDTO> response = new Response<OrderDTO>();
		try {
			Order order = this.converter.unapply(orderDTO);
			OrderDTO orderPersistedDTO = this.converter.apply(this.orderService.create(order));
			response.setData(orderPersistedDTO);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Cancel Order by id", response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id, Model model) {
		Response<Object> response = new Response<Object>();
		try {
			this.orderService.cancelOrder(id);
			response.setData("OK");
		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
