package com.hzyazilimci.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hzyazilimci
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnmExceptionMessages {

    USER_NOT_FOUND_EXCEPTION("User not found!");

    private String message;
}
