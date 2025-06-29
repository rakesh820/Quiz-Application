package demo.question_service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class GlobalExceptionHandler implements ErrorController {
	@RequestMapping("/error")
	public ResponseEntity<String> handleError(HttpServletRequest request) {
		Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(statusCode != null) {
			int status = Integer.parseInt(statusCode.toString());
			if (status == 404) {
				return new ResponseEntity<>("Invalid Request",HttpStatus.NOT_FOUND);
			} else if (status == 500) {
				return new ResponseEntity<>("Server error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>("Server error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
