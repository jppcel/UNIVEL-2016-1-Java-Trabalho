package me.polles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

// Interface de Coluna
public @interface Coluna {
	
	// String para nome do campo
	String nome() default "";
	// int para o tamanho do campo(usado apenas em string)
	int tamanho() default 100;
	// boolean para saber se eh uma primary key ou nao...
	boolean pk() default false;
	
}
