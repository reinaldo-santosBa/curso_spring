package dev.reinaldosantos.spring_curso_udemy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/{operation}/{numberOne}/{numberTwo}")
	private Double calculate(
			 @PathVariable(value="numberOne") String numberOne,
			 @PathVariable(value="numberTwo") String numberTwo,
			 @PathVariable(value="operation") String operation
	) throws Exception {
		if(!isOperation(operation)) {
			throw new Exception();
		}
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new Exception();
		}
		
		return convertAndCalculate(numberOne,numberTwo,operation);
	}

    private boolean isOperation(String operation) {
        return operation.equals("sum") || operation.equals("sub") || operation.equals("div") || operation.equals("mul");
    }
	
	private boolean isNumeric(String strNumber) throws Exception {
		if(strNumber == null) throw new Exception();
		String number =  strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
    private Double convertAndCalculate(String numberOne, String numberTwo, String operation) throws Exception {
        if (numberOne == null || numberTwo == null) throw new Exception();

        String number1 = numberOne.replaceAll(",", ".");
        String number2 = numberTwo.replaceAll(",", ".");
        if (isNumeric(number2) && isNumeric(number1)) {
            if (operation.equals("sum")) {
                return Double.parseDouble(number1) + Double.parseDouble(number2);
            } else if (operation.equals("sub")) {
                return Double.parseDouble(number1) - Double.parseDouble(number2);
            } else if (operation.equals("div")) {
                return Double.parseDouble(number1) / Double.parseDouble(number2);
            } else if (operation.equals("mul")) {
                return Double.parseDouble(number1) * Double.parseDouble(number2);
            }
        }
        return null;
    }

}