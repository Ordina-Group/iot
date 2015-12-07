package be.ordina.iot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApiDocumentation {

    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation("build/generated-snippets");

    private RestDocumentationResultHandler document;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.document = document("{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(this.document)
                .build();
    }

    @Test
    public void random() throws Exception {
        this.mockMvc.perform(get("/random")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .param("minimum", "0")
                                    .param("maximum", "100"))
                .andExpect(status().isOk())
                .andDo(document("random", requestParameters(
                        parameterWithName("minimum").description("The lower border. Default is 0"),
                        parameterWithName("maximum").description("The upper border. Default is 100")
                ), PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("minimum").type("Integer").description("The specified lower border."),
                        PayloadDocumentation.fieldWithPath("maximum").type("Integer").description("The specified upper border."),
                        PayloadDocumentation.fieldWithPath("randomValue").type("Integer").description("The random value.")
                )));
    }

    //TODO: For this test to work, RaspberryBoard chould be mocked (because it will only work when a it is ran on a raspberry pi!)
    /*Test
    public void pulse() throws Exception {
        this.mockMvc.perform(get("/pulse")).andExpect(status().isOk()).andDo(document("pulse"));
    }*/
}