package com.test.springboot.microservice.example.forex.springbootmicroserviceforexexampleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {
  
  @Autowired
  private Environment environment;
  
  @Autowired
  private ExchangeValueRepository repository;
  
   /**
	 * @api {GET} /currency-exchange/from/{from}/to/{to} Example to retrieve exchange value
	 * 
	 * @apiName retrieveExchangeValue
	 * 
	 * @apiParam {String} from Message that you want to be echoed
         * @apiParam {String} to Message that you want to be echoed
	 */
    
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue
    (@PathVariable String from, @PathVariable String to){
    
    ExchangeValue exchangeValue = 
        repository.findByFromAndTo(from, to);
    
    exchangeValue.setPort(
        Integer.parseInt(environment.getProperty("local.server.port")));
    
    return exchangeValue;
  }
}
