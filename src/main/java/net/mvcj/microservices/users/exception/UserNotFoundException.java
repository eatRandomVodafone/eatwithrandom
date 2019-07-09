package net.mvcj.microservices.users.exception;

import org.springframework.core.NestedRuntimeException;

public class UserNotFoundException extends NestedRuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String userId) {
        super(String.format("User with  Id '%s' not founded", userId));
    }

}
