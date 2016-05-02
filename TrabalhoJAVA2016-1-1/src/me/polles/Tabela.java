package me.polles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

// Interface de Tabela
public @interface Tabela {
	// Valor referente ao nome da tabela...
	String value()default "";
	
}
