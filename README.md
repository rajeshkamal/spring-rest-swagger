EVO SDDC REST API - Sample Tutorial

Maven projects
**************
rest/
	rest-api-config/ - Contains API Version Converters, Swagger Config etc
	rest-sample-services/ - Sample REST Services depends on the rest-api-config
		rest-service-common/
		rest-bringup-service/
		rest-vdi-service/
		
sdk/
	sdk-docs/ - Integration tests for generating the Swagger Spec and Static Docs
	sdk-java/ - Generating java bindings from Swagger Spec
	sdk-python/ - Generating python bindings from Swagger Spec
	
Running
*******
cd rest
mvn -s ../settings.xml clean install
cd rest-sample-services/rest-vdi-service/
mvn spring-boot:run
	Goto - http://localhost:5051/domain/vdis - Get VDI's API call.
	Goto - http://localhost:5051/domain/spec - Swagger Spec
	Goto - http://localhost:5051/domain/apidocs/index.html - Swagger UI

cd ../../../sdk/
mvn -s ../settings.xml clean install
	Docs
		sdk-docs/target/html/index.html - Static API documentation Index. Navigable to the individual services.
		sdk-docs/target/html/vdi.html - VDI Service - Static documentation.
		sdk-docs/target/html/bringup.html - Bringup Service - Static documentation.
	Spec
		sdk-docs/target/spec/vdi.json - VDI Service - Swagger API Spec.
		sdk-docs/target/spec/bringup.json - Bringup Service - Swagger API Spec.
	Asciidoc
		sdk-docs/target/asciidoc/vdi/
		sdk-docs/target/asciidoc/bringup/
	Java
		sdk-java/target/generated-sources/java/
	Python
		sdk-python/target/generated-sources/python/evosddc_python_sdk/
	