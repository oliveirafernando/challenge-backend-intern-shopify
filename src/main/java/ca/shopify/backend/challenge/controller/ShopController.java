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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.shopify.backend.challenge.controller.dto.ShopDTO;
import ca.shopify.backend.challenge.controller.dto.converter.ShopConverter;
import ca.shopify.backend.challenge.controller.response.Response;
import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.service.EntityValidationException;
import ca.shopify.backend.challenge.service.PageableException;
import ca.shopify.backend.challenge.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/shop")
@Api
public class ShopController {

	@Autowired
	private ShopService shopService;

	private ShopConverter converter = new ShopConverter();
	
	@ApiOperation(value = "Count of all Shops", response = Integer.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Something was wrong") 
		})
	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Object> count(Model model) {
		Response<Object> response = new Response<Object>();
		response.setData(this.shopService.countAll());
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Paginated list of Shops", response = ShopDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@GetMapping(value = "all/page/{page}/size/{size}", produces = "application/json")
	public ResponseEntity<Object> getAllPaginated(HttpServletRequest request, @PathVariable int page, @PathVariable("size") int size) {

		Response<Object> response = new Response<Object>();
		try {
			response.setData(
					this.shopService.getAllPaginated(page, size).stream().map(sh -> this.converter.apply(sh)));

		} catch (PageableException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Shop by Id", response = ShopDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Entity not found") 
		})
	@GetMapping(value = "{id}", produces = "application/json")
	public ResponseEntity<Object> getById(Model model, @PathVariable("id") Long id) {
		Response<Object> response = new Response<Object>();

		try {
			response.setData(this.converter.apply(this.shopService.findById(id)));

		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);

	}

	@ApiOperation(value = "Create Shop", response = ShopDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@PostMapping(produces = "application/json")
	public ResponseEntity<Object> create(HttpServletRequest request, @RequestBody ShopDTO shopDTO, BindingResult result) {

		Response<Shop> response = new Response<Shop>();
		try {
			Shop shop = this.converter.unapply(shopDTO);
			Shop shopPersisted = this.shopService.create(shop);
			response.setData(shopPersisted);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Update Shop", response = ShopDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@PutMapping(produces = "application/json")
	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody ShopDTO shopDTO, BindingResult result) {
		Response<Shop> response = new Response<Shop>();
		try {
			Shop shop = this.converter.unapply(shopDTO);
			Shop shopUpdated = this.shopService.update(shop);
			response.setData(shopUpdated);
		} catch (Exception e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "Delete Shop by id", response = Response.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfull"),
			@ApiResponse(code = 400, message = "Some business rule was not attended") 
		})
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Long id, Model model) {
		Response<Object> response = new Response<Object>();
		try {
			this.shopService.delete(id);
			response.setData("OK");
		} catch (EntityValidationException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
