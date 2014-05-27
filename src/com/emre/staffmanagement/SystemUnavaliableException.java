package com.emre.staffmanagement;

import javax.ejb.ApplicationException;

// Tells rollback whenever this occurs! for all cases!
@ApplicationException(rollback=true)
public class SystemUnavaliableException extends Exception {
	private static final long serialVersionUID = 1L;
}
