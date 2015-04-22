package com.acme.spring.rest.bundle;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2
@ComponentScan("com.acme.spring.rest")
public class SwaggerConfig {

	public AlternateTypeRule getCollectionRule() {
		// replace Collection<Bundle> with List<Bundle>
		TypeResolver typeResolver = new TypeResolver();
		ResolvedType collectionOf = typeResolver.resolve(Collection.class,
				springfox.documentation.schema.WildcardType.class);
		ResolvedType listOf = typeResolver.resolve(List.class,
				WildcardType.class);
		AlternateTypeRule collectionRule = new AlternateTypeRule(collectionOf,
				listOf);
		return collectionRule;
	}

	Predicate<String> excludePath(final String path) {
		return new Predicate<String>() {
			public boolean apply(String input) {
				return !input.contains(path);
			}
		};
	}

	@Bean
	public Docket confDocContext() {
		DocumentationType docType = new DocumentationType("ACME", "1.0.0");
		Docket plugin = new Docket(docType);
		plugin.apiInfo(getApiInfo());
		plugin.useDefaultResponseMessages(false);
		plugin.alternateTypeRules(getCollectionRule());
		plugin.select().paths(excludePath("error"))
				.paths(excludePath("api-docs")).build();
		return plugin;
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("ACME API's", "ACME Company API's", "1.0", "",
				"https://github.com/rajeshkamal", "Apache License 2.0", "");
	}

}