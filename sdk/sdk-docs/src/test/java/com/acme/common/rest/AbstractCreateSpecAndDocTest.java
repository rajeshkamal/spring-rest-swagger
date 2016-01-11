package com.acme.common.rest;

import static org.asciidoctor.Asciidoctor.Factory.create;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.github.robwin.swagger.test.SwaggerAssertions;
import io.swagger.parser.Swagger20Parser;

import java.io.File;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;
import springfox.documentation.staticdocs.SwaggerResultHandler;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractCreateSpecAndDocTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private Asciidoctor asciiDoctor;
    private File outputSpecDir;
    private File sourceAsciiDir;
    private File outputAsciiDir;
    private File outputHtmlDir;
    private File outputPdfDir;

    protected abstract String getFeatureName();

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.asciiDoctor = create();
        this.outputSpecDir = new File("target/spec/");
        this.sourceAsciiDir = new File("src/docs/asciidoc/");
        this.outputAsciiDir = new File("target/asciidoc/" + getFeatureName());
        this.outputHtmlDir = new File("target/html/");
        this.outputPdfDir = new File("target/pdf/");
    }

    @Test
    public void checkIfSpecIsUpdated() throws Exception {
        String checkedInSwaggerSpecLocation = "src/main/resources/" + getFeatureName() + ".json";

        MvcResult mvcResult =
                this.mockMvc.perform(get("/spec").accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()).andReturn();

        String springfoxSwaggerJson = mvcResult.getResponse().getContentAsString();
        SwaggerAssertions.assertThat(new Swagger20Parser().parse(springfoxSwaggerJson)).isEqualTo(
                checkedInSwaggerSpecLocation);
    }

    @Test
    public void convertToAsciiDoc() throws Exception {
        this.mockMvc
                .perform(get("/spec").accept(MediaType.APPLICATION_JSON))
                .andDo(Swagger2MarkupResultHandler
                        .outputDirectory(outputAsciiDir.getAbsolutePath()).build())
                .andExpect(status().isOk());
    }

    @Test
    public void createSpec() throws Exception {
        this.mockMvc
                .perform(get("/spec").accept(MediaType.APPLICATION_JSON))
                .andDo(SwaggerResultHandler.outputDirectory(outputSpecDir.getAbsolutePath())
                        .withFileName(getFeatureName() + ".json").build())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void convertAsciiDocToHtml() {

        Assert.assertNull("rendered feature html output is not written to a file.",
                this.asciiDoctor.renderFile(
                        new File(sourceAsciiDir.getAbsolutePath() + "/feature.adoc"),
                        getOptions("html5", getFeatureName() + ".html", outputHtmlDir,
                                getAttributes())));

        Assert.assertNull(
                "rendered feature pdf output is not written to a file.",
                this.asciiDoctor.renderFile(new File(sourceAsciiDir.getAbsolutePath()
                        + "/feature.adoc"),
                        getOptions("pdf", getFeatureName() + ".pdf", outputPdfDir, getAttributes())));

        Assert.assertNull(
                "rendered feature html output is not written to a file.",
                this.asciiDoctor.renderFile(new File(sourceAsciiDir.getAbsolutePath()
                        + "/index.adoc"),
                        getOptions("html5", "index.html", outputHtmlDir, new Attributes())));

        Assert.assertNull(
                "rendered feature pdf output is not written to a file.",
                this.asciiDoctor.renderFile(new File(sourceAsciiDir.getAbsolutePath()
                        + "/index.adoc"),
                        getOptions("pdf", "index.pdf", outputPdfDir, new Attributes())));
    }

    private Options getOptions(String formatType, String fileName, File outputDir,
            Attributes attributes) {
        Options options =
                OptionsBuilder.options().destinationDir(outputAsciiDir).baseDir(sourceAsciiDir)
                        .toDir(outputDir).safe(SafeMode.UNSAFE).docType("article")
                        .backend(formatType).mkDirs(true).toFile(new File(fileName))
                        .attributes(attributes).get();
        return options;
    }

    private Attributes getAttributes() {
        return AttributesBuilder.attributes().attribute("doctype", "book")
                .attribute("attribute-missing", "skip")
                .attribute("generated", outputAsciiDir.getAbsolutePath())
                .attribute("toclevels", "2").attribute("toc", "left")
                .attribute("source-highlighter", "true")
                .attribute("attribute-undefined", "drop-line").get();
    }

}