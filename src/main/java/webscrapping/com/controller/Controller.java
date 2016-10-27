package webscrapping.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webscrapping.com.model.Result;
import webscrapping.com.service.ResultService;

@RestController
public class Controller {
	
	@Autowired
	private ResultService resultService;
	
    @RequestMapping("/")
    public Result index() {
        return resultService.getResult();
    }

}
