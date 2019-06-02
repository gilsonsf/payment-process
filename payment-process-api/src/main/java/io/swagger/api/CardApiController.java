package io.swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.ApiParam;
import io.swagger.api.response.Response;
import io.swagger.model.Card;
import io.swagger.service.CardService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-25T23:08:44.329Z")

@Controller
public class CardApiController implements CardApi {
	
	@Autowired
	private CardService cardService;

    public ResponseEntity<Response<Card>> validate(@ApiParam(value = "",required=true) @PathVariable("number") String number) {
    	try {
    		Response<Card> response = cardService.validate(number);
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return new ResponseEntity<Response<Card>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

}
