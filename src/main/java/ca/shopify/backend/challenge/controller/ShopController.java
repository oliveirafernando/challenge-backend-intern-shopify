package ca.shopify.backend.challenge.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.shopify.backend.challenge.controller.response.Response;
import ca.shopify.backend.challenge.model.Shop;
import ca.shopify.backend.challenge.service.PageableException;
import ca.shopify.backend.challenge.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/shop")
@Api(value = "shopcontroller", description = "Operations pertaining to Shop in Online Store")
public class ShopController {

	private static final String SHOP_NOT_FOUND = "Shop not found.";

	@Autowired
	private ShopService shopService;

	@ApiOperation(value = "View a list of available shops", response = Shop.class)
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 400, message = "You are not authorized to view the resource")
	})
	@GetMapping(value = "all/paginated/{page}/{count}", produces = "application/json")
	public ResponseEntity<Object> findAll(HttpServletRequest request, @PathVariable int page, @PathVariable int count) {
		Response<Object> response = new Response<Object>();
		try {
			response.setData(this.shopService.getAllShops(page, count));
			return ResponseEntity.ok(response);
		} catch (PageableException e) {
			response.addError(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Object> findById(Model model, @PathVariable("id") Long id) {
		Response<Object> response = new Response<Object>();

		Optional<Shop> shop = this.shopService.findById(id);
		if (!shop.isPresent()) {
			response.addError(SHOP_NOT_FOUND);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(shop.get());
		return ResponseEntity.ok(response);
	}
}
