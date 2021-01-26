package com.aurora.backend.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aurora.backend.config.MessageGeneratorConfig;
import com.aurora.backend.dto.ICustomDTO;
import com.aurora.backend.dto.ProductOutDTO;
import com.aurora.backend.enums.CustomExceptionKeyEnum;
import com.aurora.backend.enums.MessageEncoderEnum;
import com.aurora.backend.exception.CustomException;
import com.aurora.backend.model.ICustomEntity;
import com.aurora.backend.model.Product;

public abstract class CustomAbstractRestController {
	
	@Autowired
	public ModelMapper modelMapper;
	 
	@Autowired
	protected Environment env;
	
	@Autowired
	protected MessageGeneratorConfig messageGenerator;
	
//	public abstract Class<? extends ICustomDTO> convertToDto(Class<? extends ICustomEntity> entity );

	

	/**
	 * Metodo para centralizar gestion de excepciones en la capa controller
	 * @param mapResponse Mapa construido para estandarizar la respuesta.
	 * @param ex Excepcion lanzada por la aplicacion.
	 * @param log Referencia de log en el que se escribira la excepcion.
	 * @return 
	 */
	protected ResponseEntity<Map<String, Object>> managerException(Map<String, Object> mapResponse, Exception ex, Logger log){		
		
		if(ex instanceof CustomException) {			
			CustomException exception = ((CustomException) ex); 
			this.responseAddErrorResponse(mapResponse, exception .getCustomExceptionEnum().getValue());
			this.responseAddMessage(mapResponse, exception.getUserMessage());
			return new ResponseEntity<Map<String, Object>>(mapResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			log.error(ex.getMessage());
			this.responseAddMessageException(mapResponse);
			return new ResponseEntity<Map<String, Object>>(mapResponse, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}	
	
	/**
	 * Metodo para agregar DTO a la respuesta que se enviara al front
	 * @param map Mapa construido para estandarizar la respuesta
	 * @param object DTO que se desea agregar a la respuesta
	 */
	protected void responseAddObject(Map<String, Object> map, ICustomDTO customDTO) {
		map.put(CustomExceptionKeyEnum.RESULT.toString(), customDTO);
	}
	
	/**
	 * Metodo para agregar un objeto de éxito a la respuesta de guardar archivo
	 * que se enviara al front.
	 * @param map Mapa construido para estandarizar la respuesta
	 */
	protected void responseAddObjectFile(Map<String, Object> map) {
		map.put(CustomExceptionKeyEnum.RESULT.toString(), MessageEncoderEnum.FILE_UPLOAD_LOADED);
	}
	
	/**
	 * Metodo para agregar mensaje a la respuesta que se dara al front
	 * @param map Mapa construido para estandarizar la respuesta
	 * @param msg Texto que se desea enviar
	 */
	protected void responseAddMessage(Map<String, Object> map, String msg) {
		map.put(CustomExceptionKeyEnum.MESSAGE_RESPONSE.toString(), msg);
	}
	
	/**
	 * Metodo para agregar mensaje a la respuesta que se dara al front
	 * @param map Mapa construido para estandarizar la respuesta
	 * @param msg Texto que se desea enviar
	 */
	protected void responseAddErrorResponse(Map<String, Object> map, String msg) {
		map.put(CustomExceptionKeyEnum.ERROR_RESPONSE.toString(), msg);
	}
	
	/**
	 * Metodo para agregar mensaje a la respuesta que se dara al front
	 * @param map Mapa construido para estandarizar la respuesta
	 * @param enumMessageEnconder Obtener codigo nemotecnico del mensaje que se desea mostrar
	 */
	protected void responseAddMessage(Map<String, Object> map, MessageEncoderEnum enumMessageEnconder) {
		String message = messageGenerator.getMessage(enumMessageEnconder.getCode());
		map.put(CustomExceptionKeyEnum.ERROR_RESPONSE.toString(), message);
	}
	
	/**
	 * Metodo para agregar mensaje de registro exitoso al front
	 * @param map Mapa construido para estandarizar la respuesta
	 */
	protected void responseAddMessageSuccess(Map<String, Object> map) {
		this.responseAddMessage(map, messageGenerator.getMessage(MessageEncoderEnum.GENERAL_SUCCESS));
	}
	
	/**
	 * Metodo para agregar mensaje de excepcion a la respuesta que se enviara al front
	 * @param map
	 */
	protected void responseAddMessageException(Map<String, Object> map) {
		this.responseAddMessage(map, messageGenerator.getMessage(MessageEncoderEnum.GENERAL_EXCEPTION));
	}
	
	
	/**
	 * Metodo para agregar DTO a la respuesta que se enviara al front
	 * @param map Mapa construido para estandarizar la respuesta
	 * @param object DTO que se desea agregar a la respuesta
	 */
	protected void responseAddObject(Map<String, Object> map, List<? extends  ICustomDTO> customDTO) {
		map.put(CustomExceptionKeyEnum.RESULT.toString(), customDTO);
	}
	
	/**
	 * Metodo que retorna el responseEntity de status 200
	 * @param mapResponse
	 * @return
	 */
	protected ResponseEntity<?> responseReturnOK(Map<String, Object> mapResponse ) {
		return new ResponseEntity<Map<String, Object>>(mapResponse, HttpStatus.OK);
	}
	
	protected ResponseEntity<?> responseReturnFailed(Map<String, Object> mapResponse ) {
		return new ResponseEntity<Map<String, Object>>(mapResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Metodo que retorna el responseEntity de status 200
	 * @param mapResponse
	 * @return
	 */
	protected ResponseEntity<?> responseReturnOKWithMessageDefault(Map<String, Object> mapResponse ) {
		this.responseAddMessageSuccess(mapResponse);
		return new ResponseEntity<Map<String, Object>>(mapResponse, HttpStatus.OK);
	}

}
