package com.abyss.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Lazysoul on 2017. 7. 17..
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

}
