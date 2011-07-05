package net.i2p.i2pcontrol.servlets.jsonrpc2handlers;

import org.json.simple.JSONObject;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;


/** 
 * Represents a JSON-RPC 2.0 error that occured during the processing of a 
 * request.
 *
 * <p>The protocol expects error objects to be structured like this:
 *
 * <ul>
 *     <li>{@code code} An integer that indicates the error type.
 *     <li>{@code message} A string providing a short description of the 
 *         error. The message should be limited to a concise single sentence.
 *     <li>{@code data} Additional information, which may be omitted. Its 
 *         contents is entirely defined by the application.
 * </ul>
 * 
 * <p>Note that the "Error" word in the class name was put there solely to
 * comply with the parlance of the JSON-RPC spec. This class doesn't inherit 
 * from {@code java.lang.Error}. It's a regular subclass of 
 * {@code java.lang.Exception} and, if thrown, it's to indicate a condition 
 * that a reasonable application might want to catch.
 *
 * <p>This class also includes convenient final static instances for all 
 * standard JSON-RPC 2.0 errors:
 *
 * <ul>
 *     <li>{@link #PARSE_ERROR} JSON parse error (-32700)
 *     <li>{@link #INVALID_REQUEST} Invalid JSON-RPC 2.0 Request (-32600)
 *     <li>{@link #METHOD_NOT_FOUND} Method not found (-32601)
 *     <li>{@link #INVALID_PARAMS} Invalid parameters (-32602)
 *     <li>{@link #INTERNAL_ERROR} Internal error (-32603)
 * </ul>
 *
 * <p>Note that the range -32099..-32000 is reserved for additional server 
 * errors.
 *
 * <p id="map">The mapping between JSON and Java entities (as defined by the 
 * underlying JSON.simple library): 
 * <pre>
 *     true|false  <--->  java.lang.Boolean
 *     number      <--->  java.lang.Number
 *     string      <--->  java.lang.String
 *     array       <--->  java.util.List
 *     object      <--->  java.util.Map
 *     null        <--->  null
 * </pre>
 *
 * <p>The JSON-RPC 2.0 specification and user group forum can be found 
 * <a href="http://groups.google.com/group/json-rpc">here</a>.
 *
 * @author <a href="http://dzhuvinov.com">Vladimir Dzhuvinov</a>
 * @version 1.16 (2010-10-04)
 */
public class JSONRPC2ExtendedError extends JSONRPC2Error {	
	
	private static final long serialVersionUID = -6574632977222371077L;

	/** Invalid JSON-RPC 2.0, implementation defined error (-32099 .. -32000) */
	public static final int CODE_INVALID_PASSWORD = -32001;
	public static final JSONRPC2Error INVALID_PASSWORD = new JSONRPC2ExtendedError(CODE_INVALID_PASSWORD, "Invalid password provided.");

	/** Invalid JSON-RPC 2.0, implementation defined error (-32099 .. -32000) */
	public static final int CODE_NO_TOKEN = -32002;
	public static final JSONRPC2Error NO_TOKEN = new JSONRPC2ExtendedError(CODE_NO_TOKEN, "No authentication token presented.");

	/** Invalid JSON-RPC 2.0, implementation defined error (-32099 .. -32000) */
	public static final int CODE_INVALID_TOKEN = -32003;
	public static final JSONRPC2Error INVALID_TOKEN = new JSONRPC2ExtendedError(CODE_INVALID_TOKEN, "Authentication token doesn't exist.");

	/** Invalid JSON-RPC 2.0, implementation defined error (-32099 .. -32000) */
	public static final int CODE_TOKEN_EXPIRED = -32004;
	public static final JSONRPC2Error TOKEN_EXPIRED = new JSONRPC2ExtendedError(CODE_TOKEN_EXPIRED, "Provided authentication token was expired, will be removed.");
	
	/** Code used for invalid JSON-RPC 2.0, implementation defined error. Error describes missing parameter/parameters */
	public static final int CODE_MISSING_PARAMETER = -32005;
	public static final JSONRPC2Error MISSING_PARAMTER = new JSONRPC2ExtendedError(CODE_MISSING_PARAMETER, "Required parameter(s) is(/are) missing from the method call.");

	
	/** 
	 * Creates a new JSON-RPC 2.0 error with the specified code and 
	 * message. The optional data is omitted.
	 * 
	 * @param code    The error code (standard pre-defined or
	 *                application-specific).
	 * @param message The error message.
	 */
	public JSONRPC2ExtendedError(int code, String message) {		
		super(code, message);
	}
	
	
	/** 
	 * Creates a new JSON-RPC 2.0 error with the specified code,
	 * message and data.
	 * 
	 * @param code    The error code (standard pre-defined or
	 *                application-specific).
	 * @param message The error message.
	 * @param data    Optional error data, must <a href="#map">map</a>
	 *                to a valid JSON type.
	 */
	public JSONRPC2ExtendedError(int code, String message, Object data) {
		super(code, message, data);
	}
	
	
	/** 
	 * Gets the JSON-RPC 2.0 error code.
	 *
	 * @return The error code.
	 */
	public int getCode() {
		return code;
	}
	
	
	/**
	 * Gets the JSON-RPC 2.0 error data.
	 *
	 * @return The error data, {@code null} if none was specified.
	 */
	public Object getData() {
		return data;	
	}
	
	
	/** 
	 * Gets a JSON representation of the JSON-RPC 2.0 error.
	 *
	 * @return A JSON object representing this error object.
	 */
	public JSONObject toJSON() {
		JSONObject out = new JSONObject();
		
		out.put("code", code);
		out.put("message", super.getMessage());
		if (data != null)
			out.put("data", data);
		return out;
	}
	
	
	/** 
	 * Serialises the error object to a JSON string.
	 *
	 * @return A JSON-encoded string representing this error object.
	 */
	public String toString() {
		return toJSON().toString();
	}
}
