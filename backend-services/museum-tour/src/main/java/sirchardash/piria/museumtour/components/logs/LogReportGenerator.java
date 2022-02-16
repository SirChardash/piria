package sirchardash.piria.museumtour.components.logs;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import sirchardash.piria.museumtour.jpa.TrackingLog;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class LogReportGenerator {

    private final TemplateEngine templateEngine;
    private final ITextRenderer renderer = new ITextRenderer();

    @SneakyThrows
    @Autowired
    LogReportGenerator() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @SneakyThrows
    public byte[] generatePdf(List<TrackingLog> logs) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        renderer.setDocumentFromString(generateHtml(logs));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
        return outputStream.toByteArray();
    }

    public String generateHtml(List<TrackingLog> logs) {
        Context context = new Context();
        context.setVariable("logs", logs);

        return templateEngine.process("logs", context);
    }

}
