package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lenda.marcin.wzb.dto.MessageDTO;
import pl.lenda.marcin.wzb.dto.MessageType;

import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class ValidationHandlerController {
  @Autowired
  private MessageSource msgSource;
  private List<MessageDTO> list = new LinkedList<>();


  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public List<MessageDTO> processValidationError(MethodArgumentNotValidException ex) {


    BindingResult result = ex.getBindingResult();
    FieldError error = result.getFieldError();

    return processFieldError(result);
  }

  private List<MessageDTO> processFieldError(BindingResult error) {

    MessageDTO message = null;


    List<FieldError> errors = error.getFieldErrors();
    for (FieldError err : errors ) {
        System.out.println (err.getDefaultMessage());
        message = new MessageDTO(MessageType.ERROR, err.getDefaultMessage());
        list.add(message);
    }
    return list;
  }
}
