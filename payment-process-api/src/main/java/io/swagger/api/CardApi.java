/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.5).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.api.response.Response;
import io.swagger.model.Card;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-25T23:08:44.329Z")

@Api(value = "card", description = "the card API")
@CrossOrigin(origins = "*")
public interface CardApi {

    @ApiOperation(value = "Validate Card", nickname = "validate", notes = "Validate if the card is valid and who is the card issuer", response = Card.class, tags={ "Card", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Search was successful!", response = Card.class),
        @ApiResponse(code = 400, message = "Invalid request"),
        @ApiResponse(code = 401, message = "Unauthorized request"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/card/validate/{number}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Response<Card>> validate(@ApiParam(value = "",required=true) @PathVariable("number") String number);

}
